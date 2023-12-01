package net.rockgiant.bettertools.item;


import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.rockgiant.bettertools.util.ToolGenerationUtils.getHandleToolTip;

public class BetterHoeItem extends HoeItem {

    public BetterHoeItem(ToolMaterial material, int attackDamage, float attackSpeed, Item.Settings settings) {

        super(material, attackDamage, attackSpeed, settings);

    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return stack.getDamage() == stack.getMaxDamage() - 1 ? 0.1f : super.getMiningSpeedMultiplier(stack, state);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return context.getStack().getMaxDamage() - 1 == context.getStack().getDamage() ? ActionResult.PASS : super.useOnBlock(context);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal(getHandleToolTip(stack)).formatted(Formatting.GRAY));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
