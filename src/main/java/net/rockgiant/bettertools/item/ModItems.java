package net.rockgiant.bettertools.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.rockgiant.bettertools.BetterTools;
import net.rockgiant.bettertools.item.tools.*;

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
                SWORDS.add((BetterSwordItem) registerItem(
                        wood_type + "_" + tool_material + "_sword",
                        new BetterSwordItem(tm, tool_material, wood_type, (int)
                                getToolAttackDamage("sword", tool_material ),
                                getToolAttackSpeed( "sword" ),
                                new FabricItemSettings())));
                AXES.add((BetterAxeItem) registerItem(
                        wood_type + "_" + tool_material + "_axe",
                        new BetterAxeItem(tm, tool_material, wood_type,
                                (int) getToolAttackDamage("axe", tool_material ),
                                getToolAttackSpeed( "axe" ),
                                new FabricItemSettings())));
                PICKAXES.add((BetterPickaxeItem) registerItem(
                        wood_type + "_" + tool_material + "_pickaxe",
                        new BetterPickaxeItem(tm, tool_material, wood_type,
                                (int) getToolAttackDamage("pickaxe", tool_material ),
                                getToolAttackSpeed( "pickaxe" ),
                                new FabricItemSettings())));
                SHOVELS.add( (BetterShovelItem) registerItem(
                        wood_type + "_" + tool_material + "_shovel",
                        new BetterShovelItem(tm, tool_material, wood_type,
                                getToolAttackDamage("shovel", tool_material ),
                                getToolAttackSpeed( "shovel" ),
                                new FabricItemSettings())));
                HOES.add( (BetterHoeItem) registerItem(
                        wood_type + "_" + tool_material + "_hoe",
                        new BetterHoeItem(tm, tool_material, wood_type,
                                (int) getToolAttackDamage("hoe", tool_material ),
                                getToolAttackSpeed( "hoe" ),
                                new FabricItemSettings())));
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
}
