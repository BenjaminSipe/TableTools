package net.rockgiant.bettertools.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.rockgiant.bettertools.BetterTools;
import net.rockgiant.bettertools.item.tools.BetterToolItem;

public class ModItems {

    public static final String[] WOOD_TYPES = {
            "oak", "birch", "spruce",
//            "acacia", "dark_oak", "mangrove",
//            "cherry", "bamboo", "jungle",
//            "crimson", "warped"
    };

    public static final String[] TOOL_MATERIALS = {
//            "stone", "gold", "iron",
            "diamond", "netherite"
    };

    public static final String[] TOOL_TYPES = {
            "pickaxe", "axe", "hoe",
//            "sword", "shovel"
    };

    // I think I need to try this a different way.


//    public static final Item OAK_TOOL_ROD = registerItem("oak_tool_rod", new Item(new FabricItemSettings()));
//    public static final Item SPRUCE_TOOL_ROD = registerItem("spruce_tool_rod", new Item(new FabricItemSettings()));
//
//
//
//    public static final Item OAK_DIAMOND_PICKAXE = registerItem("oak_diamond_pickaxe", new PickaxeItem(ToolMaterials.DIAMOND, 3, 3, new FabricItemSettings().maxCount(1)));
//    public static final Item SPRUCE_DIAMOND_PICKAXE = registerItem("spruce_diamond_pickaxe", new PickaxeItem(ToolMaterials.DIAMOND, 3, 3, new FabricItemSettings().maxCount(1)));
//
//
//    // used for tool layering, not survival.
//    public static final Item DIAMOND_AXE_HEAD = registerItem("diamond_axe_head", new Item(new FabricItemSettings().maxCount(64)));

    public static final Item OAK_DIAMOND_PICKAXE = registerItem( "oak_diamond_pickaxe",
            new PickaxeItem(ToolMaterials.DIAMOND,3,4, new FabricItemSettings()));

    public static final Item BIRCH_DIAMOND_PICKAXE = registerItem( "birch_diamond_pickaxe",
            new PickaxeItem(ToolMaterials.DIAMOND,3,4, new FabricItemSettings()));



    private static Item registerItem(String name, Item item)
    {
        return Registry.register( Registries.ITEM, new Identifier(BetterTools.MOD_ID, name), item);
    }

    public static void registerModItems() {


        BetterTools.LOGGER.debug("Registering Mod Items for " + BetterTools.MOD_ID);

        // I think it will be better to do this this way.

        for ( String tool_material : TOOL_MATERIALS )
        {
            ToolMaterial tm;
            switch ( tool_material ) {
                case "diamond":
                    tm =
            }
            for ( String wood_type : WOOD_TYPES )
            {
                for ( String tool_type : TOOL_TYPES )
                {
                    if ( "sword".equals(tool_type)) {

                    }
                }
            }
        }



//        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
//            content.addAfter(Items.STICK, OAK_TOOL_ROD);
//            content.addAfter(Items.STICK, SPRUCE_TOOL_ROD);
//        });
//        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
//            content.addAfter(Items.DIAMOND_PICKAXE, OAK_DIAMOND_PICKAXE);
//            content.addAfter(Items.DIAMOND_PICKAXE, SPRUCE_DIAMOND_PICKAXE);
//        });
    }
}
