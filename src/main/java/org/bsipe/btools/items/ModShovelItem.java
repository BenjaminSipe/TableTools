package org.bsipe.btools.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterials;
import org.bsipe.btools.data.DataComponentHelper;

public class ModShovelItem extends ShovelItem {
    public ModShovelItem(Item.Settings settings ) {
        super(ToolMaterials.NETHERITE, settings);
    }

    @Override
    public boolean canRepair(ItemStack item, ItemStack ingredient)
    {
        return DataComponentHelper.canRepair( item, ingredient );
    }
}
