package org.bsipe.btools.block.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.bsipe.btools.ModBlockEntityTypes;
import org.bsipe.btools.block.ForgeBlock;
import org.bsipe.btools.network.BlockPosPayload;
import org.bsipe.btools.recipes.AbstractForgeRecipe;
import org.bsipe.btools.recipes.ForgeRecipeInput;
import org.bsipe.btools.recipes.ModRecipeTypes;
import org.bsipe.btools.screenhandler.ForgeScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

import static org.bsipe.btools.BetterToolsModInitializer.MOD_ID;

public class ForgeBlockEntity extends BlockEntity implements RecipeInputProvider, TickableBlockEntity, ExtendedScreenHandlerFactory<BlockPosPayload>, SidedInventory {

    private static final Text TITLE = Text.translatable( "container." + MOD_ID + ".forge_block" );

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

    public static boolean canUseAsFuel(ItemStack stack) {
        return createFuelTimeMap().containsKey(stack.getItem());
    }

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);


    // used to keep the client in sync.
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
            return PROPERTY_COUNT;
        }
    };

    public PropertyDelegate getPropertyDelegate() {
        return propertyDelegate;
    }

    // Used to calculate experience dropped.
    private final Object2IntOpenHashMap<Identifier> recipesUsed = new Object2IntOpenHashMap<>();
    private Identifier recipeInProgress = null;

    private final RecipeManager.MatchGetter<ForgeRecipeInput, AbstractForgeRecipe> matchGetter;

    public ForgeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.FORGE_BLOCK_ENTITY, pos, state);

        this.matchGetter = RecipeManager.createCachedMatchGetter(ModRecipeTypes.FORGE);
    }

    public static void clearFuelTimes() {
        fuelTimes = null;
    }

    public static void addFuel(Map<Item, Integer> map, ItemConvertible item, int time ) {
        map.put( item.asItem(), time );
    }

    private static void addFuel(Map<Item, Integer> fuelTimes, TagKey<Item> tag, int fuelTime) {
        for (RegistryEntry<Item> registryEntry : Registries.ITEM.iterateEntries(tag)) {
                fuelTimes.put(registryEntry.value(), fuelTime);
        }
    }

    public static Map<Item, Integer> createFuelTimeMap() {
        Map<Item, Integer> map = fuelTimes;
        if (map != null) {
            return map;
        } else {
            Map<Item, Integer> map2 = Maps.<Item, Integer>newLinkedHashMap();
            addFuel(map2, Items.LAVA_BUCKET, 20000);
            addFuel(map2, Blocks.COAL_BLOCK, 16000);
            addFuel(map2, Items.BLAZE_ROD, 2400);
            addFuel(map2, Items.COAL, 1600);
            addFuel(map2, Items.CHARCOAL, 1600);
            addFuel(map2, ItemTags.LOGS, 300);
            addFuel(map2, ItemTags.BAMBOO_BLOCKS, 300);
            addFuel(map2, ItemTags.PLANKS, 300);
            addFuel(map2, Blocks.BAMBOO_MOSAIC, 300);
            addFuel(map2, ItemTags.WOODEN_STAIRS, 300);
            addFuel(map2, Blocks.BAMBOO_MOSAIC_STAIRS, 300);
            addFuel(map2, ItemTags.WOODEN_SLABS, 150);
            addFuel(map2, Blocks.BAMBOO_MOSAIC_SLAB, 150);
            addFuel(map2, ItemTags.WOODEN_TRAPDOORS, 300);
            addFuel(map2, ItemTags.WOODEN_PRESSURE_PLATES, 300);
            addFuel(map2, ItemTags.WOODEN_FENCES, 300);
            addFuel(map2, ItemTags.FENCE_GATES, 300);
            addFuel(map2, Blocks.NOTE_BLOCK, 300);
            addFuel(map2, Blocks.BOOKSHELF, 300);
            addFuel(map2, Blocks.CHISELED_BOOKSHELF, 300);
            addFuel(map2, Blocks.LECTERN, 300);
            addFuel(map2, Blocks.JUKEBOX, 300);
            addFuel(map2, Blocks.CHEST, 300);
            addFuel(map2, Blocks.TRAPPED_CHEST, 300);
            addFuel(map2, Blocks.CRAFTING_TABLE, 300);
            addFuel(map2, Blocks.DAYLIGHT_DETECTOR, 300);
            addFuel(map2, ItemTags.BANNERS, 300);
            addFuel(map2, Items.BOW, 300);
            addFuel(map2, Items.FISHING_ROD, 300);
            addFuel(map2, Blocks.LADDER, 300);
            addFuel(map2, ItemTags.SIGNS, 200);
            addFuel(map2, ItemTags.HANGING_SIGNS, 800);
            addFuel(map2, Items.WOODEN_SHOVEL, 200);
            addFuel(map2, Items.WOODEN_SWORD, 200);
            addFuel(map2, Items.WOODEN_HOE, 200);
            addFuel(map2, Items.WOODEN_AXE, 200);
            addFuel(map2, Items.WOODEN_PICKAXE, 200);
            addFuel(map2, ItemTags.WOODEN_DOORS, 200);
            addFuel(map2, ItemTags.BOATS, 1200);
            addFuel(map2, ItemTags.WOOL, 100);
            addFuel(map2, ItemTags.WOODEN_BUTTONS, 100);
            addFuel(map2, Items.STICK, 100);
            addFuel(map2, ItemTags.SAPLINGS, 100);
            addFuel(map2, Items.BOWL, 100);
            addFuel(map2, ItemTags.WOOL_CARPETS, 67);
            addFuel(map2, Blocks.DRIED_KELP_BLOCK, 4001);
            addFuel(map2, Items.CROSSBOW, 300);
            addFuel(map2, Blocks.BAMBOO, 50);
            addFuel(map2, Blocks.DEAD_BUSH, 100);
            addFuel(map2, Blocks.SCAFFOLDING, 50);
            addFuel(map2, Blocks.LOOM, 300);
            addFuel(map2, Blocks.BARREL, 300);
            addFuel(map2, Blocks.CARTOGRAPHY_TABLE, 300);
            addFuel(map2, Blocks.FLETCHING_TABLE, 300);
            addFuel(map2, Blocks.SMITHING_TABLE, 300);
            addFuel(map2, Blocks.COMPOSTER, 300);
            addFuel(map2, Blocks.AZALEA, 100);
            addFuel(map2, Blocks.FLOWERING_AZALEA, 100);
            addFuel(map2, Blocks.MANGROVE_ROOTS, 300);
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
        Inventories.readNbt( nbt, inventory, registryLookup);
        this.burnTime = nbt.getShort( "BurnTime" );
        this.cookTime = nbt.getShort( "CookTime" );
        this.cookTimeTotal = nbt.getShort( "CookTimeTotal" );
        this.fuelTime = this.getFuelTime( getStack( FUEL_SLOT_INDEX ) );
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

        Inventories.writeNbt( nbt, inventory, registryLookup);
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
            return createFuelTimeMap().getOrDefault(item, 0);
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
        if ( this.world == null || this.world.isClient ) return;

        // I've laid some groundwork. Lets avoid doing any ticking right now while we get other stuff working.
        boolean wasBurningAtStartOfTick = isBurning();
        boolean shouldUpdate = false;
        BlockState state = getCachedState();
        if ( isBurning() ) {
            this.burnTime --;
        }

        ItemStack inputPrimaryItemStack = getStack(INPUT_PRIMARY_SLOT_INDEX),
                  inputSecondaryItemStack = getStack(INPUT_SECONDARY_SLOT_INDEX),
                  inputFuelItemStack = getStack( FUEL_SLOT_INDEX );

        boolean hasPrimaryInput = !inputPrimaryItemStack.isEmpty(),
                hasSecondaryInput = !inputSecondaryItemStack.isEmpty(),
                hasFuelItemStack = !inputFuelItemStack.isEmpty();

        // TODO: may need updating for other recipe types.

        // TODO: Optimize this code
        if ( isBurning() || ( hasPrimaryInput && hasSecondaryInput && hasFuelItemStack ) ) {
            RecipeEntry<?> recipeEntry = hasPrimaryInput && hasSecondaryInput ? getRecipe( world ) : null;

            int blockEntityStackSizeLimit = getMaxCountPerStack();
            if (!isBurning() && canAcceptRecipeOutput(world.getRegistryManager(), recipeEntry, blockEntityStackSizeLimit)) {
                this.burnTime = getFuelTime(inputFuelItemStack);
                this.fuelTime = this.burnTime;
                if (isBurning()) {
                    shouldUpdate = true;
                    if (hasFuelItemStack) {
                        Item item = inputFuelItemStack.getItem();
                        inputFuelItemStack.decrement(1);
                        if (inputFuelItemStack.isEmpty()) {
                            Item fuelConsumeRemainder = item.getRecipeRemainder();
                            setStack(FUEL_SLOT_INDEX, fuelConsumeRemainder == null ? ItemStack.EMPTY : new ItemStack(fuelConsumeRemainder));
                        }
                    }
                }
            }

            if (isBurning() && canAcceptRecipeOutput(world.getRegistryManager(), recipeEntry, blockEntityStackSizeLimit)) {
                cookTime++;
                if ( cookTime == cookTimeTotal ) {
                    cookTime=0;
                    cookTimeTotal = getCookTimeTotal( recipeEntry);
                    alloyCount++;
                    if ( alloyCount == alloyCountTotal ) {
                        alloyCount = 0;
                        alloyCountTotal = getAlloyCountTotal( recipeEntry );
                        // THIS IS WHERE I DO THE THING.
                        if (craftRecipe(world.getRegistryManager(), recipeEntry, blockEntityStackSizeLimit)) {
                            setLastRecipe(recipeEntry);
                        }
                    } else {
                        getStack( INPUT_SECONDARY_SLOT_INDEX ).decrement( 1 );
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

    private boolean craftRecipe(DynamicRegistryManager registryManager, RecipeEntry<?> recipeEntry, int blockEntityStackSizeLimit) {

        if ( recipeEntry != null && canAcceptRecipeOutput(registryManager, recipeEntry, blockEntityStackSizeLimit )) {
            ItemStack primaryInput = getStack( INPUT_PRIMARY_SLOT_INDEX ),
                      secondaryInput = getStack( INPUT_SECONDARY_SLOT_INDEX );
            ItemStack result = ((AbstractForgeRecipe)recipeEntry.value()).craft(new ForgeRecipeInput(primaryInput, secondaryInput), registryManager),
                      resultSlot = getStack( OUTPUT_SLOT_INDEX );
            if ( resultSlot.isEmpty() )
            {
                setStack( OUTPUT_SLOT_INDEX, result.copy() );
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
        ForgeRecipeInput input = getRecipeInput();

        return input == null ? null : matchGetter.getFirstMatch( input, world ).orElse( null );
    }

    private ForgeRecipeInput getRecipeInput() {
        ItemStack primary = getStack( INPUT_PRIMARY_SLOT_INDEX );
        ItemStack secondary = getStack( INPUT_SECONDARY_SLOT_INDEX );
        if ( primary.isEmpty() || (secondary.isEmpty() && recipeInProgress == null) ) return null;
        return new ForgeRecipeInput( primary, secondary );
    }
    private int getCookTimeTotal(RecipeEntry<?> recipeEntry ) {
        return recipeEntry == null ? 0 : ((AbstractForgeRecipe)recipeEntry.value()).getCookingTime( getRecipeInput() );
    }

    private int getAlloyCountTotal(RecipeEntry<?> recipeEntry ) {
        if ( recipeEntry != null )
            return ((AbstractForgeRecipe)recipeEntry.value()).getCount( getRecipeInput() );

        return recipeInProgress == null ? 0 : alloyCountTotal;
    }

    private int getAlloyCount( RecipeEntry<?> recipeEntry ) {
        if ( recipeInProgress == null )
            return 0;

        if ( getStack( INPUT_SECONDARY_SLOT_INDEX ).isEmpty() || recipeEntry == null )
            return alloyCount;

        return recipeEntry.id().equals( recipeInProgress ) ? alloyCount : 0;
    }

    private Identifier maybeResetRecipeInProgress( RecipeEntry<?> recipeEntry ) {
        return recipeEntry != null && recipeInProgress != null && ! recipeEntry.id().equals( recipeInProgress ) ? null : recipeInProgress;
    }

    private boolean canAcceptRecipeOutput(DynamicRegistryManager recipeManager, RecipeEntry<?> recipe, int blockEntityStackSizeLimit) {

        boolean isMissingRecipe =  recipe == null || getStack( INPUT_PRIMARY_SLOT_INDEX ).isEmpty();
        if ( isMissingRecipe ) return false;

        ItemStack result = recipe.value().getResult( recipeManager );
        ItemStack resultSlot = getStack( OUTPUT_SLOT_INDEX );
        int totalCount = resultSlot.getCount() + result.getCount();

        return ! result.isEmpty() &&
                ( resultSlot.isEmpty() ||
                        ( ItemStack.areItemsAndComponentsEqual( resultSlot, result ) &&
                                ( totalCount <= blockEntityStackSizeLimit
                               && totalCount <= resultSlot.getMaxCount() ) ) );
    }

    // -- START SCREEN HANDLER METHODS --
    @Override
    public BlockPosPayload getScreenOpeningData(ServerPlayerEntity player) {
        return new BlockPosPayload(this.pos);
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
        return InventoryStorage.of( this, direction );
    }

    @Override
    public void provideRecipeInputs(RecipeMatcher finder) {
        finder.addInput( inventory.get( INPUT_PRIMARY_SLOT_INDEX ));
        finder.addInput( inventory.get( INPUT_SECONDARY_SLOT_INDEX));
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        if ( side == Direction.UP ) return new int[] {1};
        if ( side == Direction.DOWN ) return new int[] {3};
        if ( side == getCachedState().get( HorizontalFacingBlock.FACING ).getOpposite() ) return new int[] {2};
        return new int[] {0};
    }


    // these will be used for redstone?
    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return true;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return true;
    }

    @Override
    public int size() {
        return 4;
    }

    @Override
    public boolean isEmpty() {
        return  inventory.get( INPUT_PRIMARY_SLOT_INDEX ).isEmpty() &&
                inventory.get( INPUT_SECONDARY_SLOT_INDEX ).isEmpty() &&
                inventory.get( FUEL_SLOT_INDEX ).isEmpty() &&
                inventory.get( OUTPUT_SLOT_INDEX ).isEmpty();
    }

    @Override
    public ItemStack getStack(int slot) {
        return slot >= 0 && slot < this.inventory.size() ? this.inventory.get(slot) : ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack itemStack = Inventories.splitStack(this.inventory, slot, amount);
        if (!itemStack.isEmpty()) {
            this.markDirty();
        }

        return itemStack;
    }

    @Override
    public ItemStack removeStack(int slot) {
        ItemStack itemStack = this.inventory.get( slot );
        if (!itemStack.isEmpty()) {
            this.markDirty();
        }

        return itemStack;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        ItemStack input = getStack(slot);

        this.inventory.set(slot, stack);
        stack.capCount(this.getMaxCount(stack));

        boolean canCombineStacks = !stack.isEmpty() && ItemStack.areItemsAndComponentsEqual(input, stack);

        RecipeEntry<?> recipeEntry = getRecipe(world);

        recipeInProgress = (slot == INPUT_PRIMARY_SLOT_INDEX && stack.isEmpty() ) ? null : maybeResetRecipeInProgress( recipeEntry );

        if ((slot == 0 || slot == 1) && !canCombineStacks ) {
            cookTimeTotal = getCookTimeTotal(recipeEntry);
            cookTime = 0;
            alloyCountTotal = getAlloyCountTotal(recipeEntry);
            alloyCount = getAlloyCount( recipeEntry );

            this.markDirty();
        }
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        this.inventory.clear();
        this.markDirty();
    }

    public void dropExperienceForRecipesUsed(ServerPlayerEntity player) {
        List<RecipeEntry<?>> list = this.getRecipesUsedAndDropExperience(player.getServerWorld(), player.getPos());
        player.unlockRecipes(list);

        for (RecipeEntry<?> recipeEntry : list) {
            if (recipeEntry != null) {
                player.onRecipeCrafted(recipeEntry, this.inventory);
            }
        }

        this.recipesUsed.clear();
    }

    public List<RecipeEntry<?>> getRecipesUsedAndDropExperience(ServerWorld world, Vec3d pos) {
        List<RecipeEntry<?>> list = Lists.<RecipeEntry<?>>newArrayList();

        for (Object2IntMap.Entry<Identifier> entry : this.recipesUsed.object2IntEntrySet()) {
            world.getRecipeManager().get((Identifier)entry.getKey()).ifPresent(recipe -> {
                list.add(recipe);
                dropExperience(world, pos, entry.getIntValue(), ((AbstractForgeRecipe)recipe.value()).getExperience( getRecipeInput() ));
            });
        }

        return list;
    }

    private static void dropExperience(ServerWorld world, Vec3d pos, int multiplier, float experience) {
        int i = MathHelper.floor((float)multiplier * experience);
        float f = MathHelper.fractionalPart((float)multiplier * experience);
        if (f != 0.0F && Math.random() < (double)f) {
            i++;
        }

        ExperienceOrbEntity.spawn(world, pos, i);
    }
}
