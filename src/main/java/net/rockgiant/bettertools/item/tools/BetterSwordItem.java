package net.rockgiant.bettertools.item.tools;

import net.minecraft.block.BlockState;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;

public class BetterSwordItem extends SwordItem {

    public BetterSwordItem(ToolMaterial material, int attackDamage, float attackSpeed, Item.Settings settings) {

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

}
