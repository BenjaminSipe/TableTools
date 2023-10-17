package net.rockgiant.bettertools.item.tools;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;

public class BetterShovelItem extends ShovelItem {

    private final String handleMaterial;
    private final String headMaterial;


    public BetterShovelItem(ToolMaterial material, String headMaterial, String handleMaterial, float attackDamage, float attackSpeed, Item.Settings settings) {

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
