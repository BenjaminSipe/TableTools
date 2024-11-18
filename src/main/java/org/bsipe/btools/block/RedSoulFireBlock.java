package org.bsipe.btools.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.bsipe.btools.ModBlocks;

public class RedSoulFireBlock extends AbstractFireBlock {
    public static final MapCodec<RedSoulFireBlock> CODEC = createCodec(RedSoulFireBlock::new);

    @Override
    public MapCodec<RedSoulFireBlock> getCodec() {
        return CODEC;
    }

    public RedSoulFireBlock(AbstractBlock.Settings settings) {
        super(settings, 3.0f);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        return this.canPlaceAt(state, world, pos) ? this.getDefaultState() : Blocks.AIR.getDefaultState();
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return isSoulBase(world.getBlockState(pos.down()));
    }

    public static boolean isSoulBase(BlockState state) {
        return state.isOf( ModBlocks.RED_SOUL_SAND );
    }

    @Override
    protected boolean isFlammable(BlockState state) {
        return true;
    }

}
