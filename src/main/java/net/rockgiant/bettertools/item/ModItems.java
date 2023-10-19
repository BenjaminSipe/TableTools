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
            for ( Item item : TOOL_RODS.values() )
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
