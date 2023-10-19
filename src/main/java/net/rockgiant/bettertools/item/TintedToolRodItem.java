package net.rockgiant.bettertools.item;

import net.minecraft.item.Item;

public class TintedToolRodItem extends Item {
    private final int tint;



    public TintedToolRodItem(int tint, Settings settings) {
        super(settings);
        this.tint = tint;
    }

    public int getTint() {
        return tint;
    }

}
