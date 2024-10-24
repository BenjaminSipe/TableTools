package org.bsipe.btools.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.bsipe.btools.ModBlockEntityTypes;
import org.bsipe.btools.block.entity.ForgeBlockEntity;
import org.bsipe.btools.block.entity.TickableBlockEntity;
import org.jetbrains.annotations.Nullable;

public class ForgeBlock extends Block implements BlockEntityProvider {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty LIT = Properties.LIT;


    public ForgeBlock(Settings settings ) {
        super( settings );
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(LIT, Boolean.valueOf(true )));
    }

    @Override
    protected ActionResult onUse(BlockState state,
                                 World world,
                                 BlockPos pos,
                                 PlayerEntity player,
                                 BlockHitResult hit ) {

        if ( ! world.isClient ) {
            BlockEntity blockEntity = world.getBlockEntity( pos );
            if (blockEntity instanceof ForgeBlockEntity forgeBlockEntity && player != null) {
                player.openHandledScreen(forgeBlockEntity);
//                player.sendMessage( Text.of( forgeBlockEntity.getCounter() + "" ), true );

//                if ( ! player.isSneaking() ) forgeBlockEntity.incrementCounter();
            }
        }

        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntityTypes.FORGE_BLOCK_ENTITY.instantiate( pos, state );
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker( World world, BlockState state, BlockEntityType<T> type ) {
        // I believe a furnace checks if the furnace is lit before passing a ticker.
        return TickableBlockEntity.getTicker(world);
    }

    // -- START ROTATABLE BLOCK PROPERTIES --
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    // -- END ROTATABLE BLOCK PROPERTIES
}
