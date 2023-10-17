package net.rockgiant.bettertools.item.tools;


import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

public class BetterHoeItem extends HoeItem {

    private final String handleMaterial;
    private final String headMaterial;


    public BetterHoeItem(ToolMaterial material, String headMaterial, String handleMaterial, int attackDamage, float attackSpeed, Item.Settings settings) {

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
