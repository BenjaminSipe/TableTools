package org.bsipe.btools;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static org.bsipe.btools.BetterToolsModInitializer.MOD_ID;
import static org.bsipe.btools.ModTags.TOOL_HANDLES;

public class ModItems {


    public static Item AXE = register( new AxeItem(ToolMaterials.NETHERITE, new Item.Settings()), "axe" );
    public static Item HOE = register( new HoeItem(ToolMaterials.NETHERITE, new Item.Settings()), "hoe" );
    public static Item PICKAXE = register( new PickaxeItem(ToolMaterials.NETHERITE, new Item.Settings()), "pickaxe" );
    public static Item SHOVEL = register( new ShovelItem(ToolMaterials.NETHERITE, new Item.Settings()), "shovel" );
    public static Item SWORD = register( new SwordItem(ToolMaterials.NETHERITE, new Item.Settings()), "sword" );

    public static Item ACACIA_TOOL_HANDLE = register( new Item( new Item.Settings()), "acacia_tool_handle");
    public static Item BAMBOO_TOOL_HANDLE = register( new Item( new Item.Settings()), "bamboo_tool_handle");
    public static Item BIRCH_TOOL_HANDLE = register( new Item( new Item.Settings()), "birch_tool_handle");
    public static Item CHERRY_TOOL_HANDLE = register( new Item( new Item.Settings()), "cherry_tool_handle");
    public static Item CRIMSON_TOOL_HANDLE = register( new Item( new Item.Settings()), "crimson_tool_handle");
    public static Item DARK_OAK_TOOL_HANDLE = register( new Item( new Item.Settings()), "dark_oak_tool_handle");
    public static Item JUNGLE_TOOL_HANDLE = register( new Item( new Item.Settings()), "jungle_tool_handle");
    public static Item MANGROVE_TOOL_HANDLE = register( new Item( new Item.Settings()), "mangrove_tool_handle");
    public static Item OAK_TOOL_HANDLE = register( new Item( new Item.Settings()), "oak_tool_handle");
    public static Item SPRUCE_TOOL_HANDLE = register( new Item( new Item.Settings()), "spruce_tool_handle");
    public static Item WARPED_TOOL_HANDLE = register( new Item( new Item.Settings()), "warped_tool_handle");



    public static void initialize() {
        registerItemGroups();
        FuelRegistry.INSTANCE.add(TOOL_HANDLES, 15 * 20 );
    }

    public static Item register(Item item, String name )
    {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), item);
    }


    public static void registerItemGroups() {
        ItemGroupEvents.modifyEntriesEvent( ItemGroups.INGREDIENTS ).register( content -> {
            content.add( ACACIA_TOOL_HANDLE );
            content.add( BAMBOO_TOOL_HANDLE );
            content.add( BIRCH_TOOL_HANDLE );
            content.add( CHERRY_TOOL_HANDLE );
            content.add( CRIMSON_TOOL_HANDLE );
            content.add( DARK_OAK_TOOL_HANDLE );
            content.add( JUNGLE_TOOL_HANDLE );
            content.add( MANGROVE_TOOL_HANDLE );
            content.add( OAK_TOOL_HANDLE );
            content.add( SPRUCE_TOOL_HANDLE );
            content.add( WARPED_TOOL_HANDLE );
        });
    }
}
