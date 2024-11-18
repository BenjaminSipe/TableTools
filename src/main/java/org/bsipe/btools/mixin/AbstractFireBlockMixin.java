package org.bsipe.btools.mixin;

import net.minecraft.block.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.bsipe.btools.ModBlocks;
import org.bsipe.btools.block.RedSoulFireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin( AbstractFireBlock.class )
public class AbstractFireBlockMixin {

    @Inject( cancellable = true, at = @At("TAIL"), method="getState" )
    private static void getStateOverride(BlockView world, BlockPos pos, CallbackInfoReturnable<BlockState> cir) {
        BlockState blockState = world.getBlockState(pos.down());
        if (RedSoulFireBlock.isSoulBase( blockState ) ) {
            cir.setReturnValue(ModBlocks.RED_SOUL_FIRE.getDefaultState());
        }
    }
}
