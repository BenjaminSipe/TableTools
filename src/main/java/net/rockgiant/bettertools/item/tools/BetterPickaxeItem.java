package net.rockgiant.bettertools.item.tools;

import net.minecraft.item.*;

public class BetterPickaxeItem extends PickaxeItem {

    private final String handleMaterial;
    private final String headMaterial;


    public BetterPickaxeItem(ToolMaterial material, String headMaterial, String handleMaterial, int attackDamage, float attackSpeed, Item.Settings settings) {

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
