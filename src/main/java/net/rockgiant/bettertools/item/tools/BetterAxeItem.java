package net.rockgiant.bettertools.item.tools;

import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.rockgiant.bettertools.util.ToolGenerationUtils.getHandleToolTip;

public class BetterAxeItem extends AxeItem {


    public BetterAxeItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    // when on last hit, speed will be very slow on last attack.
    // using on wrong material may result in breaking your tool if you are unlucky.
    // should be 50 % chance.
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
