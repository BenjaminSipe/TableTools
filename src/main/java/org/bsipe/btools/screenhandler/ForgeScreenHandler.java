package org.bsipe.btools.screenhandler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import org.bsipe.btools.ModBlocks;
import org.bsipe.btools.ModScreenHandlerTypes;
import org.bsipe.btools.block.entity.ForgeBlockEntity;
import org.bsipe.btools.network.BlockPosPayload;

public class ForgeScreenHandler extends ScreenHandler {
    private final ForgeBlockEntity forgeBlockEntity;
    private final ScreenHandlerContext context;

    // Server Constructor
    public ForgeScreenHandler(int syncId, PlayerInventory playerInventory, ForgeBlockEntity forgeBlockEntity) {
        super( ModScreenHandlerTypes.FORGE, syncId );
        this.forgeBlockEntity = forgeBlockEntity;
        this.context = ScreenHandlerContext.create( this.forgeBlockEntity.getWorld(), this.forgeBlockEntity.getPos());

        // player inventory slots.
        addPlayerInventory( playerInventory );
        addPlayerHotbar( playerInventory );
    }

    // Client Constructor
    public ForgeScreenHandler(int syncId, PlayerInventory playerInventory, BlockPosPayload payload ) {
        this( syncId, playerInventory, (ForgeBlockEntity) playerInventory.player.getWorld().getBlockEntity(payload.pos()));
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
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

    public ForgeBlockEntity getForgeBlockEntity() {
        return this.forgeBlockEntity;
    }

    public int getCounter() {
        return getForgeBlockEntity().getCounter();
    }
}
