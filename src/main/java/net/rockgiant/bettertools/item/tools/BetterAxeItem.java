package net.rockgiant.bettertools.item.tools;

import net.minecraft.item.AxeItem;
import net.minecraft.item.ToolMaterial;

public class BetterAxeItem extends AxeItem {

    private final String handleMaterial;
    private final String headMaterial;


    public BetterAxeItem(ToolMaterial material,String headMaterial, String handleMaterial, float attackDamage, float attackSpeed, Settings settings) {

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
