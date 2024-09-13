package org.bsipe.btools;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static org.bsipe.btools.BetterToolsModInitializer.MOD_ID;

public class ModItems {
    public static Item AXE = register( new AxeItem(ToolMaterials.NETHERITE, new Item.Settings()), "axe" );
    public static Item HOE = register( new HoeItem(ToolMaterials.NETHERITE, new Item.Settings()), "hoe" );
    public static Item PICKAXE = register( new PickaxeItem(ToolMaterials.NETHERITE, new Item.Settings()), "pickaxe" );
    public static Item SHOVEL = register( new ShovelItem(ToolMaterials.NETHERITE, new Item.Settings()), "shovel" );
    public static Item SWORD = register( new SwordItem(ToolMaterials.NETHERITE, new Item.Settings()), "sword" );

    public static Item TOOL_HANDLE = register( new Item( new Item.Settings() ), "tool_handle" );

    public static void initialize() {
    }

    public static Item register(Item item, String name )
    {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), item);
    }
}
