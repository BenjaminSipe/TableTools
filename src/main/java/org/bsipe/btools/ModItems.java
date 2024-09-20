package org.bsipe.btools;

import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.bsipe.btools.items.*;

import static org.bsipe.btools.BetterToolsModInitializer.MOD_ID;

public class ModItems {
    public static Item AXE = register( new ModAxeItem( new Item.Settings()), "axe" );
    public static Item HOE = register( new ModHoeItem( new Item.Settings()), "hoe" );
    public static Item PICKAXE = register( new ModPickaxeItem( new Item.Settings()), "pickaxe" );
    public static Item SHOVEL = register( new ModShovelItem( new Item.Settings()), "shovel" );
    public static Item SWORD = register( new ModSwordItem( new Item.Settings()), "sword" );

    public static Item TOOL_HANDLE = register( new Item( new Item.Settings() ), "tool_handle" );

    public static void initialize() {
    }

    public static Item register(Item item, String name )
    {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), item);
    }
}
