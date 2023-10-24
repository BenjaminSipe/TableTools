package net.rockgiant.bettertools.toolmaterials;

import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public class BetterToolsMaterial implements ToolMaterial {

    // tools are directly based on vanilla tools with 1 variation:
    // STURDY tools have a 10% durability buff.
    // SWIFT tools have a 10% speed buff;
    private static final float STURDY_MODIFIER = 1.1f;


    public static final BetterToolsMaterial STURDY_WOOD = new BetterToolsMaterial(0, (int) (STURDY_MODIFIER * 59 ), 2.0f, 0.0f, 15, () -> Ingredient.fromTag( ItemTags.PLANKS ));

    public static final BetterToolsMaterial STURDY_STONE = new BetterToolsMaterial(1, (int) (STURDY_MODIFIER * 131), 4.0F, 1.0F, 5, () -> Ingredient.fromTag( ItemTags.STONE_TOOL_MATERIALS ));

    public static final BetterToolsMaterial STURDY_GOLD = new BetterToolsMaterial(0, (int) (STURDY_MODIFIER * 32), 12.0F, 0.0F, 22, () -> Ingredient.ofItems( Items.GOLD_INGOT ));

    public static final BetterToolsMaterial STURDY_IRON = new BetterToolsMaterial(2, (int) (STURDY_MODIFIER * 250), 6.0F, 2.0F, 14, () -> Ingredient.ofItems( Items.IRON_INGOT ));

    public static final BetterToolsMaterial STURDY_DIAMOND = new BetterToolsMaterial(3, (int) (STURDY_MODIFIER * 1561), 8.0F, 3.0F, 10, () -> Ingredient.ofItems( Items.DIAMOND ));

    public static final BetterToolsMaterial STURDY_NETHERITE = new BetterToolsMaterial(4, (int) (STURDY_MODIFIER * 2031), 9.0F, 4.0F, 15, () -> Ingredient.ofItems( Items.NETHERITE_INGOT ));

//    these may be removed due to a different method of implementation, but I will hang onto them for the time being.

//    private static final float SWIFT_MODIFIER = 1.1f;

//    public static final BetterToolsMaterial SWIFT_WOOD = new BetterToolsMaterial(0, 59, 2.0f * SWIFT_MODIFIER, 0.0f, 15, () -> Ingredient.fromTag( ItemTags.PLANKS ));
//    public static final BetterToolsMaterial SWIFT_STONE = new BetterToolsMaterial(1, 131, 4.0F * SWIFT_MODIFIER, 1.0F, 5, () -> Ingredient.fromTag( ItemTags.STONE_TOOL_MATERIALS ));
//    public static final BetterToolsMaterial SWIFT_GOLD = new BetterToolsMaterial(0, 32, 12.0F * SWIFT_MODIFIER, 0.0F, 22, () -> Ingredient.ofItems( Items.GOLD_INGOT ));
//    public static final BetterToolsMaterial SWIFT_IRON = new BetterToolsMaterial(2, 250, 6.0F * SWIFT_MODIFIER, 2.0F, 14, () -> Ingredient.ofItems( Items.IRON_INGOT ));
//    public static final BetterToolsMaterial SWIFT_DIAMOND = new BetterToolsMaterial(3, 1561, 8.0F * SWIFT_MODIFIER, 3.0F, 10, () -> Ingredient.ofItems( Items.DIAMOND ));
//    public static final BetterToolsMaterial SWIFT_NETHERITE = new BetterToolsMaterial(4, 2031, 9.0F * SWIFT_MODIFIER , 4.0F, 15, () -> Ingredient.ofItems( Items.NETHERITE_INGOT ));


    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Lazy<Ingredient> repairIngredient;

    private BetterToolsMaterial(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = new Lazy(repairIngredient);
    }

    @Override
    public int getDurability() {
        return itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return miningLevel;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
