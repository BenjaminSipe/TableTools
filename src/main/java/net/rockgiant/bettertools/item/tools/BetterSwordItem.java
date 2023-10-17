package net.rockgiant.bettertools.item.tools;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class BetterSwordItem extends SwordItem {

    private final String handleMaterial;
    private final String headMaterial;


    public BetterSwordItem(ToolMaterial material, String headMaterial, String handleMaterial, int attackDamage, float attackSpeed, Item.Settings settings) {

        super(material, attackDamage, attackSpeed, settings);

        this.headMaterial = headMaterial;
        this.handleMaterial = handleMaterial;
    }

    public String getHandle() {
        return handleMaterial;
    }

    public String getHead() {
        return headMaterial;
    }
}
