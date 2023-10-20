package net.rockgiant.bettertools.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.rockgiant.bettertools.BetterTools;
import net.rockgiant.bettertools.item.tools.*;
import net.rockgiant.bettertools.toolmaterials.BetterToolsMaterial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.rockgiant.bettertools.util.ToolGenerationUtils.getToolAttackDamage;
import static net.rockgiant.bettertools.util.ToolGenerationUtils.getToolAttackSpeed;

public class ModItems {

    public static final String[] WOOD_TYPES = {
            "oak", "birch", "spruce",
            "acacia", "dark_oak", "mangrove",
            "cherry", "bamboo", "jungle",
            "crimson", "warped"
    };

    public static final String[] TOOL_MATERIALS = {
            "wood", "stone", "gold", "iron",
            "diamond", "netherite"
    };

    public static final String[] TOOL_TYPES = {
            "pickaxe", "axe", "hoe",
            "sword", "shovel"
    };

    public static List<BetterSwordItem> SWORDS = new ArrayList<>();
    public static List<BetterAxeItem> AXES = new ArrayList<>();
    public static List<BetterShovelItem> SHOVELS = new ArrayList<>();
    public static List<BetterPickaxeItem> PICKAXES = new ArrayList<>();
    public static List<BetterHoeItem> HOES = new ArrayList<>();
    public static Map<String, Item> TOOL_RODS = new HashMap<>();
    public static Map<String, Item> SMITHING_RECIPE_ITEM_MAP = new HashMap<>();

    public static final Item TINTED_TOOL_ROD = registerItem( "tinted_tool_rod", new TintedToolRodItem(0x445566, new FabricItemSettings()));
    public static final Item BETTER_IRON_AXE = registerItem( "better_iron_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_IRON, "iron", "acacia",6, -3.2f, new FabricItemSettings() ));
    public static final Item BETTER_IRON_PICKAXE = registerItem( "better_iron_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_IRON, "iron", "acacia",6, -3.2f, new FabricItemSettings() ));
    /*
    ACACIA: 0xC26D3F,
    BAMBOO: 0xEFD97E,
    BIRCH: 0xD7CB8D,
    CHERRY: 0xE7CAC5,
    CRIMSON: 0x863E5A,
    DARK_OAK: 0x53381A,
    JUNGLE: 0xBF8E6B,
    MANGROVE: 0x8B4D3A,
    OAK: 0x896727,
    SPRUCE: 0x886539,
    WARPED: 0x3A8E8C,
     */

    public static final Item ACACIA_TINTED_TOOL_ROD = registerItem( "acacia_tinted_tool_rod", new TintedToolRodItem(0xC26D3F, new FabricItemSettings()));
    public static final Item BAMBOO_TINTED_TOOL_ROD = registerItem( "bamboo_tinted_tool_rod", new TintedToolRodItem(0xEFD97E, new FabricItemSettings()));
    public static final Item BIRCH_TINTED_TOOL_ROD = registerItem( "birch_tinted_tool_rod", new TintedToolRodItem(0xD7CB8D, new FabricItemSettings()));
    public static final Item CHERRY_TINTED_TOOL_ROD = registerItem( "cherry_tinted_tool_rod", new TintedToolRodItem(0xDD9D97, new FabricItemSettings()));
    public static final Item CRIMSON_TINTED_TOOL_ROD = registerItem( "crimson_tinted_tool_rod", new TintedToolRodItem(0xAA4072, new FabricItemSettings()));
    public static final Item DARK_OAK_TINTED_TOOL_ROD = registerItem( "dark_oak_tinted_tool_rod", new TintedToolRodItem(0x53381A, new FabricItemSettings()));
    public static final Item JUNGLE_TINTED_TOOL_ROD = registerItem( "jungle_tinted_tool_rod", new TintedToolRodItem(0xBF8E6B, new FabricItemSettings()));
    public static final Item MANGROVE_TINTED_TOOL_ROD = registerItem( "mangrove_tinted_tool_rod", new TintedToolRodItem(0x8C3539, new FabricItemSettings()));
    public static final Item OAK_TINTED_TOOL_ROD = registerItem( "oak_tinted_tool_rod", new TintedToolRodItem(0x896727, new FabricItemSettings()));
    public static final Item SPRUCE_TINTED_TOOL_ROD = registerItem( "spruce_tinted_tool_rod", new TintedToolRodItem(0x886539, new FabricItemSettings()));
    public static final Item WARPED_TINTED_TOOL_ROD = registerItem( "warped_tinted_tool_rod", new TintedToolRodItem(0x3A8E8C, new FabricItemSettings()));


    public static final Item[] TINTED_TOOL_RODS = new Item[]{
            ACACIA_TINTED_TOOL_ROD,
            BAMBOO_TINTED_TOOL_ROD,
            BIRCH_TINTED_TOOL_ROD,
            CHERRY_TINTED_TOOL_ROD,
            CRIMSON_TINTED_TOOL_ROD,
            DARK_OAK_TINTED_TOOL_ROD,
            JUNGLE_TINTED_TOOL_ROD,
            MANGROVE_TINTED_TOOL_ROD,
            OAK_TINTED_TOOL_ROD,
            SPRUCE_TINTED_TOOL_ROD,
            WARPED_TINTED_TOOL_ROD
    };

    private static Item registerItem(String name, Item item)
    {

        return Registry.register( Registries.ITEM, new Identifier(BetterTools.MOD_ID, name), item);
    }

    public static void registerModItems() {


        BetterTools.LOGGER.debug("Registering Mod Items for " + BetterTools.MOD_ID);

        // I think it will be better to do this this way.
        registerModToolRods();
        registerModTools();

    }

    private static void registerModToolRods() {
        for( String wood_type : WOOD_TYPES ) {
            TOOL_RODS.put(wood_type, registerItem(
                    wood_type + "_tool_rod",
                    new Item(new FabricItemSettings().maxCount(64))));
        }
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {

            for ( Item item : TINTED_TOOL_RODS )
                content.addAfter( Items.STICK, item );
        });
    }

    private static void registerModTools() {
        for ( String tool_material : TOOL_MATERIALS )
        {
            for ( String wood_type : WOOD_TYPES )
            {
                ToolMaterial toolMaterial = getToolMaterial( tool_material, wood_type );

                SWORDS.add((BetterSwordItem) registerItem(
                        wood_type + "_" + tool_material + "_sword",
                        new BetterSwordItem(toolMaterial, tool_material, wood_type, (int)
                                getToolAttackDamage("sword", tool_material ),
                                getToolAttackSpeed( "sword" ),
                                generateToolSettings( tool_material ))));
                AXES.add((BetterAxeItem) registerItem(
                        wood_type + "_" + tool_material + "_axe",
                        new BetterAxeItem(toolMaterial, tool_material, wood_type,
                                (int) getToolAttackDamage("axe", tool_material ),
                                getToolAttackSpeed( "axe" ),
                                generateToolSettings( tool_material ))));
                PICKAXES.add((BetterPickaxeItem) registerItem(
                        wood_type + "_" + tool_material + "_pickaxe",
                        new BetterPickaxeItem(toolMaterial, tool_material, wood_type,
                                (int) getToolAttackDamage("pickaxe", tool_material ),
                                getToolAttackSpeed( "pickaxe" ),
                                generateToolSettings( tool_material ))));
                SHOVELS.add( (BetterShovelItem) registerItem(
                        wood_type + "_" + tool_material + "_shovel",
                        new BetterShovelItem(toolMaterial, tool_material, wood_type,
                                getToolAttackDamage("shovel", tool_material ),
                                getToolAttackSpeed( "shovel" ),
                                generateToolSettings( tool_material ))));
                HOES.add( (BetterHoeItem) registerItem(
                        wood_type + "_" + tool_material + "_hoe",
                        new BetterHoeItem(toolMaterial, tool_material, wood_type,
                                (int) getToolAttackDamage("hoe", tool_material ),
                                getToolAttackSpeed( "hoe" ),
                                generateToolSettings( tool_material ))));

                if ( "diamond".equals( tool_material) ) {
                    // they will all be the same length right now,
                    // but that can easily change in the future.
                    SMITHING_RECIPE_ITEM_MAP.put(wood_type + "_sword", SWORDS.get( SWORDS.size() - 1));
                    SMITHING_RECIPE_ITEM_MAP.put(wood_type + "_pickaxe", PICKAXES.get( PICKAXES.size() - 1));
                    SMITHING_RECIPE_ITEM_MAP.put(wood_type + "_shovel", SHOVELS.get( SHOVELS.size() - 1));
                    SMITHING_RECIPE_ITEM_MAP.put(wood_type + "_axe", AXES.get( AXES.size() - 1));
                    SMITHING_RECIPE_ITEM_MAP.put(wood_type + "_hoe", HOES.get( HOES.size() - 1));

                }
            }
        }


        // ADDING THE EVENTS to the tools and combat tabs.
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
            for ( Item item : PICKAXES)
                content.add( item );
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
            for ( Item item : SHOVELS)
                content.add( item );
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
            for ( Item item : HOES)
                content.add( item );
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
            for ( Item item : AXES)
                content.add( item );
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
            for ( Item item : SWORDS)
                content.add( item );
        });
    }

    private static ToolMaterial getToolMaterial(String tool_material, String wood_type) {
        ToolMaterial toolMaterial;
        switch ( tool_material ) {
            case "netherite":
                toolMaterial = isSturdyMaterial(wood_type) ? BetterToolsMaterial.STURDY_NETHERITE : BetterToolsMaterial.SWIFT_NETHERITE;
                break;
            case "diamond":
                toolMaterial = isSturdyMaterial(wood_type) ? BetterToolsMaterial.STURDY_DIAMOND : BetterToolsMaterial.SWIFT_DIAMOND;
                break;
            case "iron":
                toolMaterial = isSturdyMaterial(wood_type) ? BetterToolsMaterial.STURDY_IRON : BetterToolsMaterial.SWIFT_IRON;
                break;
            case "gold":
                toolMaterial = isSturdyMaterial(wood_type) ? BetterToolsMaterial.STURDY_GOLD : BetterToolsMaterial.SWIFT_GOLD;
                break;
            case "stone":
                toolMaterial = isSturdyMaterial(wood_type) ? BetterToolsMaterial.STURDY_STONE : BetterToolsMaterial.SWIFT_STONE;
                break;
            default:
                toolMaterial = isSturdyMaterial(wood_type) ? BetterToolsMaterial.STURDY_WOOD : BetterToolsMaterial.SWIFT_WOOD;
                break;
        }
        return toolMaterial;
    }

    private static FabricItemSettings generateToolSettings( String material ) {
        return "netherite".equals( material ) ? new FabricItemSettings() : new FabricItemSettings().fireproof();
    }

    private static boolean isSturdyMaterial( String wood_type ) {
        switch (wood_type)
        {
            case "spruce":
            case "dark_oak":
            case "bamboo":
            case "jungle":
            case "mangrove":
            case "crimson":
                return true;
            case "oak":
            case "birch":
            case "acacia":
            case "cherry":
            case "warped":
            default:
                return false;
        }
    }
}
