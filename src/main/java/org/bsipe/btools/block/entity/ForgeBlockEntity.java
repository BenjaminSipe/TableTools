package org.bsipe.btools.block.entity;

import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeInputProvider;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.bsipe.btools.BetterToolsModInitializer;
import org.bsipe.btools.ModBlockEntityTypes;
import org.bsipe.btools.block.ForgeBlock;
import org.bsipe.btools.network.BlockPosPayload;
import org.bsipe.btools.recipes.ForgeAlloyRecipe;
import org.bsipe.btools.recipes.ForgeRecipeInput;
import org.bsipe.btools.screenhandler.ForgeScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

import static org.bsipe.btools.BetterToolsModInitializer.MOD_ID;

public class ForgeBlockEntity extends BlockEntity implements RecipeInputProvider, TickableBlockEntity, ExtendedScreenHandlerFactory<BlockPosPayload> {

    private static final Text TITLE = Text.translatable( "container." + MOD_ID + ".forge_block" );

    private static final String COUNTER_NBT = MOD_ID + ":counter";

    protected static final int INPUT_PRIMARY_SLOT_INDEX = 0;
    protected static final int INPUT_SECONDARY_SLOT_INDEX = 1;
    protected static final int FUEL_SLOT_INDEX = 2;
    protected static final int OUTPUT_SLOT_INDEX = 3;
    // unknown
    public static final int BURN_TIME_PROPERTY_INDEX = 0;
    public static final int FUEL_TIME_PROPERTY_INDEX = 1;
    public static final int COOK_TIME_PROPERTY_INDEX = 2;
    public static final int COOK_TIME_TOTAL_PROPERTY_INDEX = 3;
    public static final int ALLOY_COUNT_PROPERTY_INDEX = 4;
    public static final int ALLOY_COUNT_TOTAL_PROPERTY_INDEX = 5;

    public static final int PROPERTY_COUNT = 6;

    public static final int DEFAULT_COOK_TIME = 200;

    // just so it is written down somewhere:
    // BURN TIME is how long the furnace has left before the fire goes out
    int burnTime;
    // FUEL TIME the amount of time the next fuel item will add to the BURN TIME
    // seems to be used for blast furnace and smokers to work properly.
    int fuelTime;
    // COOK TIME how long the current item has before it finishes
    int cookTime;
    // COOK TIME TOTAL how long it will take to cook that item
    int cookTimeTotal;
    // ALLOY COUNT ( CUSTOM ): how many secondary items have been consumed
    int alloyCount;
    // ALLOY COUNT TOTAL ( CUSTOM ): how many secondary items need to be consumed for the recipe to complete.
    int alloyCountTotal;


    @Nullable
    private static volatile Map<Item, Integer> fuelTimes;

    // this will definitely have to change to some kind of furnace inventory.
    private final SimpleInventory inventory = new SimpleInventory( 4 ) {
        @Override
        public void markDirty() {
            super.markDirty();
            update( getCachedState() );
        }

        @Override
        public void setStack(int slot, ItemStack stack) {

            ItemStack input = getStack(slot);

            super.setStack(slot, stack);
            stack.capCount(this.getMaxCount(stack));

//            boolean hasRecipeInput = !(getStack(INPUT_SECONDARY_SLOT_INDEX).isEmpty() || getStack(INPUT_PRIMARY_SLOT_INDEX).isEmpty());
            boolean canCombineStacks = !stack.isEmpty() && ItemStack.areItemsAndComponentsEqual(input, stack);

            RecipeEntry<?> recipeEntry = getRecipe(world);

            recipeInProgress = (slot == INPUT_PRIMARY_SLOT_INDEX && stack.isEmpty() ) ? null : maybeResetRecipeInProgress( recipeEntry );

            if ((slot == 0 || slot == 1) && !canCombineStacks ) {
                cookTimeTotal = getCookTimeTotal(recipeEntry);
                cookTime = 0;
                alloyCountTotal = getAlloyCountTotal(recipeEntry);
                alloyCount = getAlloyCount( recipeEntry );

                if (!world.isClient)
                    this.markDirty();
            }
        }
    };


    // no idea what this does.
    protected final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            switch (index) {
                case BURN_TIME_PROPERTY_INDEX:
                    return burnTime;
                case FUEL_TIME_PROPERTY_INDEX:
                    return fuelTime;
                case COOK_TIME_PROPERTY_INDEX:
                    return cookTime;
                case COOK_TIME_TOTAL_PROPERTY_INDEX:
                    return cookTimeTotal;
                case ALLOY_COUNT_PROPERTY_INDEX:
                    return alloyCount;
                case ALLOY_COUNT_TOTAL_PROPERTY_INDEX:
                    return alloyCountTotal;
                default:
                    return 0;
            }
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case BURN_TIME_PROPERTY_INDEX:
                    burnTime = value;
                    break;
                case FUEL_TIME_PROPERTY_INDEX:
                    fuelTime = value;
                    break;
                case COOK_TIME_PROPERTY_INDEX:
                    cookTime = value;
                    break;
                case COOK_TIME_TOTAL_PROPERTY_INDEX:
                    cookTimeTotal = value;
                    break;
                case ALLOY_COUNT_PROPERTY_INDEX:
                    alloyCount = value;
                    break;
                case ALLOY_COUNT_TOTAL_PROPERTY_INDEX:
                    alloyCountTotal = value;
            }
        }

        @Override
        public int size() {
            return 6;
        }
    };

    public PropertyDelegate getPropertyDelegate() {
        return propertyDelegate;
    }

    // Used to calculate experience dropped.
    private final Object2IntOpenHashMap<Identifier> recipesUsed = new Object2IntOpenHashMap<>();
    private Identifier recipeInProgress = null;

    // Fixed, but may be difficult long term
    private final RecipeManager.MatchGetter<ForgeRecipeInput, ForgeAlloyRecipe> matchGetter;
    // Seems to relate to droppers and hoppers.
    // TODO: Make hopper and dropper inputs directional.
    private final InventoryStorage inventoryStorage = InventoryStorage.of( inventory, null);

    public ForgeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.FORGE_BLOCK_ENTITY, pos, state);

        this.matchGetter = RecipeManager.createCachedMatchGetter(ForgeAlloyRecipe.Type.INSTANCE);
    }

    public static void clearFuelTimes() {
        fuelTimes = null;
    }

    public static Map<Item, Integer> createFuelTimeMap() {
        Map<Item, Integer> map = fuelTimes;
        if (map != null) {
            return map;
        } else {
            Map<Item, Integer> map2 = Maps.<Item, Integer>newLinkedHashMap();
            map2.put(Items.BLAZE_ROD.asItem(), 2400);
            map2.put(Items.COAL.asItem(), 1600);
            map2.put(Items.STICK.asItem(), 200);
            fuelTimes = map2;
            return map2;
        }
    }

    public boolean isBurning() {
        return this.burnTime > 0;
    }

    @Override
    protected void readNbt( NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup ) {
        super.readNbt( nbt, registryLookup);
        Inventories.readNbt( nbt, this.inventory.getHeldStacks(), registryLookup);
        this.burnTime = nbt.getShort( "BurnTime" );
        this.cookTime = nbt.getShort( "CookTime" );
        this.cookTimeTotal = nbt.getShort( "CookTimeTotal" );
        this.fuelTime = this.getFuelTime( this.inventory.getStack( 1 ) );
        this.alloyCount = nbt.getShort( "InfuseCount" );
        this.alloyCountTotal = nbt.getShort( "InfuseCountTotal" );
        NbtCompound nbtCompound = nbt.getCompound( "RecipesUsed" );

        for (String string : nbtCompound.getKeys()) {
            this.recipesUsed.put(Identifier.of(string), nbtCompound.getInt(string));
        }

        this.recipeInProgress = nbt.contains( "RecipeInProgress" ) ? Identifier.of( nbt.getString( "RecipeInProgress" ) ) : null;

    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup ) {

        super.writeNbt(nbt, registryLookup);
        nbt.putShort("BurnTime", (short)this.burnTime);
        nbt.putShort("CookTime", (short)this.cookTime);
        nbt.putShort("CookTimeTotal", (short)this.cookTimeTotal);
        nbt.putShort("InfuseCount", (short)this.alloyCount);
        nbt.putShort("InfuseCountTotal", (short)this.alloyCountTotal);

        Inventories.writeNbt( nbt, this.inventory.getHeldStacks(), registryLookup);
        NbtCompound nbtCompound = new NbtCompound();
        this.recipesUsed.forEach((identifier, count) -> nbtCompound.putInt(identifier.toString(), count));
        nbt.put("RecipesUsed", nbtCompound);
        if ( recipeInProgress != null ) {
            nbt.putString( "RecipeInProgress", recipeInProgress.toString() );
        }
    }

    protected int getFuelTime(ItemStack fuel) {
        if (fuel.isEmpty()) {
            return 0;
        } else {
            Item item = fuel.getItem();
            return (Integer)createFuelTimeMap().getOrDefault(item, 0);
        }
    }

    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create( this );
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        var nbt = super.toInitialChunkDataNbt(registryLookup);
        writeNbt( nbt, registryLookup );
        return nbt;
    }

    @Override
    public void tick() {

        // rather than running the full tick, or constantly receiving updates from the server just for progress bars, we just do our own math.
        if ( this.world == null ) return;
        if ( this.world.isClient ) {
//            if ( burnTime > 0 ) {
//                burnTime--;
//                if ( cookTime < cookTimeTotal ) cookTime++;
//            } else {
//                if ( cookTime < cookTimeTotal ) cookTime = MathHelper.clamp( cookTime - 2, 0, cookTimeTotal );
//            }
            return;
        }

        // I've laid some groundwork. Lets avoid doing any ticking right now while we get other stuff working.
        boolean wasBurningAtStartOfTick = isBurning();
        boolean shouldUpdate = false;
        BlockState state = getCachedState();
        if ( isBurning() ) {
            this.burnTime --;
        }

        ItemStack inputPrimaryItemStack = this.inventory.getStack(INPUT_PRIMARY_SLOT_INDEX),
                  inputSecondaryItemStack = this.inventory.getStack(INPUT_SECONDARY_SLOT_INDEX),
                  inputFuelItemStack = this.inventory.getStack( FUEL_SLOT_INDEX );

        boolean hasPrimaryInput = !inputPrimaryItemStack.isEmpty(),
                hasSecondaryInput = !inputSecondaryItemStack.isEmpty(),
                hasFuelItemStack = !inputFuelItemStack.isEmpty();

        // TODO: may need updating for other recipe types.
        if ( isBurning() || ( hasPrimaryInput && hasSecondaryInput && hasFuelItemStack ) ) {
            RecipeEntry<?> recipeEntry = hasPrimaryInput && hasSecondaryInput ? getRecipe( world ) : null;

            int blockEntityStackSizeLimit = inventory.getMaxCountPerStack();
            if (!isBurning() && canAcceptRecipeOutput(world.getRegistryManager(), recipeEntry, inventory, blockEntityStackSizeLimit)) {
                this.burnTime = getFuelTime(inputFuelItemStack);
                this.fuelTime = this.burnTime;
                if (isBurning()) {
                    shouldUpdate = true;
                    if (hasFuelItemStack) {
                        Item item = inputFuelItemStack.getItem();
                        inputFuelItemStack.decrement(1);
                        if (inputFuelItemStack.isEmpty()) {
                            Item fuelConsumeRemainder = item.getRecipeRemainder();
                            this.inventory.setStack(FUEL_SLOT_INDEX, fuelConsumeRemainder == null ? ItemStack.EMPTY : new ItemStack(fuelConsumeRemainder));
                        }
                    }
                }
            }

            if (isBurning() && canAcceptRecipeOutput(world.getRegistryManager(), recipeEntry, inventory, blockEntityStackSizeLimit)) {
                cookTime++;
                if ( cookTime == cookTimeTotal ) {
                    cookTime=0;
                    cookTimeTotal = getCookTimeTotal( recipeEntry);
                    alloyCount++;
                    if ( alloyCount == alloyCountTotal ) {
                        alloyCount = 0;
                        alloyCountTotal = getAlloyCountTotal( recipeEntry );
                        // THIS IS WHERE I DO THE THING.
                        if (craftRecipe(world.getRegistryManager(), recipeEntry, inventory, blockEntityStackSizeLimit)) {
                            setLastRecipe(recipeEntry);
                        }
                    } else {
                        inventory.getStack( INPUT_SECONDARY_SLOT_INDEX ).decrement( 1 );
                        recipeInProgress = recipeEntry.id();
                    }

                    shouldUpdate = true;
                }

            } else {
                cookTime = 0;
            }
        } else if ( ! isBurning() && cookTime > 0 ) {
            cookTime = MathHelper.clamp( cookTime - 2, 0, cookTimeTotal );
        }

        if ( wasBurningAtStartOfTick != isBurning() ) {
            shouldUpdate = true;
            state = state.with(ForgeBlock.LIT, Boolean.valueOf(isBurning()));
            world.setBlockState( pos, state, Block.NOTIFY_ALL );
        }

        if ( shouldUpdate ) {
            update( state );
        }
    }

    private void setLastRecipe(RecipeEntry<?> recipeEntry) {
        if ( recipeEntry != null ) {
            Identifier identifier = recipeEntry.id();
            this.recipesUsed.addTo( identifier, 1 );
            this.recipeInProgress = null;
        }
    }

    private boolean craftRecipe(DynamicRegistryManager registryManager, RecipeEntry<?> recipeEntry, SimpleInventory inventory, int blockEntityStackSizeLimit) {

        if ( recipeEntry != null && canAcceptRecipeOutput(registryManager, recipeEntry,inventory, blockEntityStackSizeLimit )) {
            ItemStack primaryInput = inventory.getStack( INPUT_PRIMARY_SLOT_INDEX ),
                      secondaryInput = inventory.getStack( INPUT_SECONDARY_SLOT_INDEX ),
                      result = recipeEntry.value().getResult(registryManager),
                      resultSlot = inventory.getStack( OUTPUT_SLOT_INDEX );
            if ( resultSlot.isEmpty() )
            {
                inventory.setStack( OUTPUT_SLOT_INDEX, result.copy() );
            } else if ( ItemStack.areItemsAndComponentsEqual( resultSlot, result )) {
                resultSlot.increment( result.getCount() );
            }

            primaryInput.decrement( 1 );
            secondaryInput.decrement( 1 );
            return true;
        } else {
            return false;
        }
    }

    private RecipeEntry<?> getRecipe(World world ) {
        ItemStack primary = inventory.getStack( INPUT_PRIMARY_SLOT_INDEX );
        ItemStack secondary = inventory.getStack( INPUT_SECONDARY_SLOT_INDEX );
        if ( primary.isEmpty() || (secondary.isEmpty() && recipeInProgress == null) ) return null;

        return matchGetter.getFirstMatch( new ForgeRecipeInput( primary, secondary ), world ).orElse( null );
    }

    private int getCookTimeTotal(RecipeEntry<?> recipeEntry ) {
        return recipeEntry == null ? 0 : ((ForgeAlloyRecipe)recipeEntry.value()).cookingTime;
    }

    private int getAlloyCountTotal(RecipeEntry<?> recipeEntry ) {
        if ( recipeEntry != null )
            return ((ForgeAlloyRecipe)recipeEntry.value()).count;

        return recipeInProgress == null ? 0 : alloyCountTotal;
    }

    private int getAlloyCount( RecipeEntry<?> recipeEntry ) {
        if ( recipeInProgress == null )
            return 0;

        if ( inventory.getStack( INPUT_SECONDARY_SLOT_INDEX ).isEmpty() || recipeEntry == null )
            return alloyCount;

        return recipeEntry.id().equals( recipeInProgress ) ? alloyCount : 0;
    }

    private Identifier maybeResetRecipeInProgress( RecipeEntry<?> recipeEntry ) {
        return recipeEntry != null && recipeInProgress != null && ! recipeEntry.id().equals( recipeInProgress ) ? null : recipeInProgress;
    }

    private boolean canAcceptRecipeOutput(DynamicRegistryManager recipeManager, RecipeEntry<?> recipe, SimpleInventory inventory, int blockEntityStackSizeLimit) {

//        boolean isMissingRecipe =  recipe == null || inventory.getStack( INPUT_PRIMARY_SLOT_INDEX ).isEmpty();
//        if ( isMissingRecipe ) return false;
//
//        ItemStack result = recipe.value().getResult( recipeManager );
//        ItemStack resultSlot = inventory.getStack( OUTPUT_SLOT_INDEX );
//        int totalCount = resultSlot.getCount() + result.getCount();
//
//        return ! result.isEmpty() &&
//                ( resultSlot.isEmpty() ||
//                        ( ItemStack.areItemsAndComponentsEqual( resultSlot, result ) &&
//                                ( totalCount <= blockEntityStackSizeLimit
//                               && totalCount <= resultSlot.getMaxCount() ) ) );

        if (!inventory.getStack(INPUT_PRIMARY_SLOT_INDEX).isEmpty() && recipe != null) {
            ItemStack itemStack = recipe.value().getResult(recipeManager);
            if (itemStack.isEmpty()) {
                return false;
            } else {
                ItemStack itemStack2 = inventory.getStack(OUTPUT_SLOT_INDEX);
                if (itemStack2.isEmpty()) {
                    return true;
                } else if (!ItemStack.areItemsAndComponentsEqual(itemStack2, itemStack)) {
                    return false;
                } else {
                    return itemStack2.getCount() < blockEntityStackSizeLimit && itemStack2.getCount() < itemStack2.getMaxCount() ? true : itemStack2.getCount() < itemStack.getMaxCount();
                }
            }
        } else {
            return false;
        }
    }

    // -- START SCREEN HANDLER METHODS --
    @Override
    public BlockPosPayload getScreenOpeningData(ServerPlayerEntity player) {
        return new BlockPosPayload(this.pos); // May want other data?
    }

    @Override
    public Text getDisplayName() {
        return TITLE;
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ForgeScreenHandler( syncId, playerInventory, this );
    }
    // -- END SCREEN HANDLER METHODS --

    private void update( BlockState state) {
        markDirty();
        if ( world != null)
            world.updateListeners( pos, getCachedState(), state, Block.NOTIFY_ALL);
    }

    public InventoryStorage getInventoryProvider( Direction direction ) {
        return inventoryStorage;
    }


    // I need a special inventory, because I need to track when we setStack
    public SimpleInventory getInventory() { return this.inventory; }

    @Override
    public void provideRecipeInputs(RecipeMatcher finder) {
        for (ItemStack itemStack : this.inventory.getHeldStacks()) {
            finder.addInput(itemStack);
        }
    }
}
