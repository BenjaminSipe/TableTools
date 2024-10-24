package org.bsipe.btools.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.bsipe.btools.ModBlockEntityTypes;
import org.bsipe.btools.network.BlockPosPayload;
import org.bsipe.btools.screenhandler.ForgeScreenHandler;
import org.jetbrains.annotations.Nullable;

import static org.bsipe.btools.BetterToolsModInitializer.MOD_ID;

public class ForgeBlockEntity extends BlockEntity implements TickableBlockEntity, ExtendedScreenHandlerFactory<BlockPosPayload> {

    private static final Text TITLE = Text.translatable( "container." + MOD_ID + ".forge_block" );

    private static final String COUNTER_NBT = MOD_ID + ":counter";

    private int counter;

    public ForgeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.FORGE_BLOCK_ENTITY, pos, state);
    }

    public int getCounter()
    {
        return this.counter;
    }

    public void incrementCounter() {
        this.counter = (this.counter + 1) % 100;
        markDirty();

        // invalidates the chunk so we reload the chunk.
        if ( world != null)
            world.updateListeners( pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup ) {
        nbt.putInt( COUNTER_NBT, this.counter );
    }

    @Override
    protected void readNbt( NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup ) {
        this.counter = nbt.getInt( COUNTER_NBT );
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
        incrementCounter();
        // this is now where I begin cooking. . . lol
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

}
