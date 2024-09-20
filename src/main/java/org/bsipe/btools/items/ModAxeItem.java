package org.bsipe.btools.items;

import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterials;
import org.bsipe.btools.data.DataComponentHelper;

public class ModAxeItem extends AxeItem {
    public ModAxeItem(Settings settings ) {
        super(ToolMaterials.NETHERITE, settings);
    }


    @Override
    public boolean canRepair(ItemStack item, ItemStack ingredient)
    {
        return DataComponentHelper.canRepair( item, ingredient );
    }

}
