package net.rockgiant.bettertools.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.rockgiant.bettertools.BetterTools;
import net.rockgiant.bettertools.item.tools.BetterToolItem;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static final String[] WOOD_TYPES = {
            "oak", "birch", "spruce",
            "acacia", "dark_oak", "mangrove",
//            "cherry", "bamboo", "jungle",
//            "crimson", "warped"
    };

    public static final String[] TOOL_MATERIALS = {
            "stone", "gold", "iron",
            "diamond", "netherite"
    };

    public static final String[] TOOL_TYPES = {
            "pickaxe", "axe", "hoe",
            "sword", "shovel"
    };

    public static List<Item> swords = new ArrayList<>();
    public static List<Item> axes = new ArrayList<>();
    public static List<Item> shovels = new ArrayList<>();
    public static List<Item> pickaxes = new ArrayList<>();
    public static List<Item> hoes = new ArrayList<>();

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

//    public static final Item OAK_DIAMOND_PICKAXE = registerItem( "oak_diamond_pickaxe",
//            new PickaxeItem(ToolMaterials.DIAMOND,3,4, new FabricItemSettings()));
//
//    public static final Item BIRCH_DIAMOND_PICKAXE = registerItem( "birch_diamond_pickaxe",
//            new PickaxeItem(ToolMaterials.DIAMOND,3,4, new FabricItemSettings()));



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
                case "netherite":
                case "diamond":
                    tm = ToolMaterials.DIAMOND;
                    break;
                case "iron":
                    tm = ToolMaterials.IRON;
                    break;
                case "gold":
                    tm = ToolMaterials.GOLD;
                    break;
                case "stone":
                    tm = ToolMaterials.STONE;
                    break;
                default:
                    tm = ToolMaterials.WOOD;
                    break;
            }
            
            for ( String wood_type : WOOD_TYPES )
            {
                swords.add(registerItem(
                        wood_type + "_" + tool_material + "_sword",
                        new SwordItem(tm, (int)
                                getToolAttackDamage("sword", tool_material ),
                                getToolAttackSpeed( "sword" ),
                                new FabricItemSettings())));
                axes.add(registerItem(
                        wood_type + "_" + tool_material + "_axe",
                        new AxeItem(tm,
                                (int) getToolAttackDamage("axe", tool_material ),
                                getToolAttackSpeed( "axe" ),
                                new FabricItemSettings())));
                pickaxes.add( registerItem(
                        wood_type + "_" + tool_material + "_pickaxe",
                        new PickaxeItem(tm,
                                (int) getToolAttackDamage("pickaxe", tool_material ),
                                getToolAttackSpeed( "pickaxe" ),
                                new FabricItemSettings())));
                shovels.add( registerItem(
                        wood_type + "_" + tool_material + "_shovel",
                        new ShovelItem(tm,
                                getToolAttackDamage("shovel", tool_material ),
                                getToolAttackSpeed( "shovel" ),
                                new FabricItemSettings())));
                hoes.add( registerItem(
                        wood_type + "_" + tool_material + "_hoe",
                        new HoeItem(tm,
                                (int) getToolAttackDamage("hoe", tool_material ),
                                getToolAttackSpeed( "hoe" ),
                                new FabricItemSettings())));
            }
        }


        // ADDING THE EVENTS to the tools and combat tabs.
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
            for ( Item item : pickaxes )
                content.addAfter( Items.NETHERITE_PICKAXE, item );
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
            for ( Item item : shovels )
                content.addAfter( Items.NETHERITE_SHOVEL, item );
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
            for ( Item item : hoes )
                content.addAfter( Items.NETHERITE_HOE, item );
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
            for ( Item item : axes )
                content.addAfter( Items.NETHERITE_AXE, item );
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
            for ( Item item : swords )
                content.addAfter( Items.NETHERITE_SWORD, item );
        });
    }

    public static float getToolAttackSpeed(String tool_type ) {
//        switch (tool_type) {
//            case "sword":
//                return 1.6f;
//            case "axe":
//                return .8f;
//            case "pickaxe":
//                return 1.2f;
//            default:
//                return 1;
//        }
        return .2f;
    }

    public static float getToolAttackDamage( String tool_type, String material_type ) {
        float base = 1;
//        switch ( material_type ) {
//            case "netherite":
//                base += 1;
//            case "diamond":
//                base += 1;
//            case "iron":
//                base += 1;
//            case "stone":
//                base += 1;
//        }
//
//        switch ( tool_type ) {
//            case "shovel":
//                base += 1.5;
//                break;
//            case "pickaxe":
//                base += 1;
//                break;
//            case "axe":
//                base += 6;
//                break;
//            case "sword":
//                base += 3;
//                break;
//        }

        return base;
    }
}
