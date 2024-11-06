package org.bsipe.btools.screenhandler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeInputProvider;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.MathHelper;
import org.bsipe.btools.ModBlocks;
import org.bsipe.btools.ModScreenHandlerTypes;
import org.bsipe.btools.block.ForgeBlock;
import org.bsipe.btools.block.entity.ForgeBlockEntity;
import org.bsipe.btools.network.BlockPosPayload;
import org.bsipe.btools.recipes.ForgeAlloyRecipe;
import org.bsipe.btools.recipes.ForgeRecipeInput;

public class ForgeScreenHandler extends AbstractRecipeScreenHandler<ForgeRecipeInput, ForgeAlloyRecipe> {
    private final ForgeBlockEntity forgeBlockEntity;
    private final ScreenHandlerContext context;
    private final PropertyDelegate propertyDelegate;

    private final int PRIMARY_INPUT_SLOT = 0;
    private final int SECONDARY_INPUT_SLOT = 1;
    private final int FUEL_INPUT_SLOT = 2;
    private final int OUTPUT_SLOT = 3;


    public static final int BURN_TIME_PROPERTY_INDEX = 0;
    public static final int FUEL_TIME_PROPERTY_INDEX = 1;
    public static final int COOK_TIME_PROPERTY_INDEX = 2;
    public static final int COOK_TIME_TOTAL_PROPERTY_INDEX = 3;
    public static final int ALLOY_COUNT_PROPERTY_INDEX = 4;
    public static final int ALLOY_COUNT_TOTAL_PROPERTY_INDEX = 5;

    // Server Constructor
    public ForgeScreenHandler(int syncId, PlayerInventory playerInventory, ForgeBlockEntity forgeBlockEntity ) {
        super( ModScreenHandlerTypes.FORGE, syncId );
        this.forgeBlockEntity = forgeBlockEntity;
        this.propertyDelegate = forgeBlockEntity.getPropertyDelegate();
        this.context = ScreenHandlerContext.create( this.forgeBlockEntity.getWorld(), this.forgeBlockEntity.getPos());
        SimpleInventory inventory = this.forgeBlockEntity.getInventory();
        checkSize( inventory, 4 );
        inventory.onOpen( playerInventory.player );


        // player inventory slots.
        addPlayerInventory( playerInventory );
        addPlayerHotbar( playerInventory );
        addBlockInventory( inventory );

        this.addProperties(propertyDelegate);
    }

    // Client Constructor
    public ForgeScreenHandler(int syncId, PlayerInventory playerInventory, BlockPosPayload payload ) {
        this(syncId,
                playerInventory,
                (ForgeBlockEntity) playerInventory.player.getWorld().getBlockEntity(payload.pos())
        );
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        // TODO: This will need to be done, but is kindof bottom of the barrel for this.
        ItemStack newStack = ItemStack.EMPTY;
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return canUse( this.context, player, ModBlocks.DEEPSLATE_FORGE_BLOCK);
    }

    private void addPlayerHotbar( PlayerInventory inventory ) {
        for ( int column = 0; column < 9 ; column ++ ) {
            addSlot( new Slot( inventory, column, 8 + column * 18, 142 ) );
        }
    }

    private void addPlayerInventory( PlayerInventory inventory ) {
        for ( int row = 0; row < 3 ; row ++ ) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(inventory, 9 + ( column + row * 9), 8 + column * 18, 84 + 18 * row));
            }
        }
    }
    // 44 16
    // 51 62
    // 80 40
    // 123 40

    private void addBlockInventory( SimpleInventory inventory ) {
        // I can't use a for-loopp. I only have 4 items.

        addSlot( new Slot(inventory, PRIMARY_INPUT_SLOT, 76, 40 ) );
        addSlot( new Slot(inventory, SECONDARY_INPUT_SLOT, 42, 17 ) );
        addSlot( new Slot(inventory, FUEL_INPUT_SLOT, 42, 52 ) );

        addSlot( new Slot(inventory, OUTPUT_SLOT, 127, 40 ) );

    }

    public ForgeBlockEntity getForgeBlockEntity() {
        return this.forgeBlockEntity;
    }

    @Override
    public void onClosed( PlayerEntity player ) {
        super.onClosed( player );
        this.forgeBlockEntity.getInventory().onClose( player );
    }

    @Override
    public void populateRecipeFinder(RecipeMatcher finder) {
        if ( forgeBlockEntity instanceof RecipeInputProvider )
            this.forgeBlockEntity.provideRecipeInputs( finder );
    }

    @Override
    public void clearCraftingSlots() {
        this.getSlot( PRIMARY_INPUT_SLOT ).setStackNoCallbacks( ItemStack.EMPTY );
        this.getSlot( SECONDARY_INPUT_SLOT ).setStackNoCallbacks( ItemStack.EMPTY );
        this.getSlot( OUTPUT_SLOT ).setStackNoCallbacks( ItemStack.EMPTY );
    }

    @Override
    public boolean matches(RecipeEntry<ForgeAlloyRecipe> recipe) {
        return recipe.value().matches( new ForgeRecipeInput(
                this.forgeBlockEntity.getInventory().getStack( PRIMARY_INPUT_SLOT ),
                this.forgeBlockEntity.getInventory().getStack( SECONDARY_INPUT_SLOT ) ), this.forgeBlockEntity.getWorld());
    }

    @Override
    public int getCraftingResultSlotIndex() {
        return OUTPUT_SLOT;
    }

    @Override
    public int getCraftingWidth() {
        return 2;
    }

    @Override
    public int getCraftingHeight() {
        return 1;
    }

    @Override
    public int getCraftingSlotCount() {
        return 4;
    }

    @Override
    public RecipeBookCategory getCategory() {
        return RecipeBookCategory.FURNACE;
    }

    @Override
    public boolean canInsertIntoSlot(int index) {
        return index != OUTPUT_SLOT;
    }

    public boolean isBurning() {
        return this.forgeBlockEntity.isBurning();
    }

    public float getCookProgress() {
        int i = this.propertyDelegate.get(COOK_TIME_PROPERTY_INDEX);
        int j = this.propertyDelegate.get(COOK_TIME_TOTAL_PROPERTY_INDEX);
        return j != 0 && i != 0 ? MathHelper.clamp((float)i / (float)j, 0.0F, 1.0F) : 0.0F;
    }

    public float getAlloyProgress() {
        int i = this.propertyDelegate.get(ALLOY_COUNT_PROPERTY_INDEX);
        int j = this.propertyDelegate.get(ALLOY_COUNT_TOTAL_PROPERTY_INDEX);
        return j != 0 && i != 0 ? MathHelper.clamp((float)i / (float)j, 0.0F, 1.0F) : 0.0F;
    }

    public float getFuelProgress() {
        int i = this.propertyDelegate.get( FUEL_TIME_PROPERTY_INDEX );
        if (i == 0) {
            i = 200;
        }

        return MathHelper.clamp((float)this.propertyDelegate.get(BURN_TIME_PROPERTY_INDEX) / (float)i, 0.0F, 1.0F);
    }

    // probably removed eventually:


    public PropertyDelegate getPropertyDelegate() {
        return propertyDelegate;
    }
}
