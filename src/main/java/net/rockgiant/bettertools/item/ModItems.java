package net.rockgiant.bettertools.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.GroupEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.rockgiant.bettertools.BetterTools;
import net.rockgiant.bettertools.item.tools.*;
import net.rockgiant.bettertools.toolmaterials.BetterToolsMaterial;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static net.rockgiant.bettertools.util.ToolGenerationUtils.*;

public class ModItems {

    public static final Block BURNING_OBSIDIAN = new Block(FabricBlockSettings.create().requiresTool().strength(50.0f, 1200.0f));

    private static Item registerItem(String name, Item item)
    {
        return Registry.register( Registries.ITEM, new Identifier(BetterTools.MOD_ID, name), item);
    }

    public static void registerModItems() {

        registerModToolRods();
        Registry.register( Registries.BLOCK, new Identifier(BetterTools.MOD_ID, "burning_obsidian"), BURNING_OBSIDIAN );
        Registry.register( Registries.ITEM, new Identifier(BetterTools.MOD_ID, "burning_obsidian" ), new BlockItem( BURNING_OBSIDIAN, new FabricItemSettings() ) );

        Set<Identifier> LOOT_TABLES = Set.of(
                LootTables.DESERT_PYRAMID_CHEST,
                LootTables.ANCIENT_CITY_CHEST,
                LootTables.NETHER_BRIDGE_CHEST,
                LootTables.END_CITY_TREASURE_CHEST,
                LootTables.ABANDONED_MINESHAFT_CHEST,
                LootTables.STRONGHOLD_LIBRARY_CHEST,
                LootTables.STRONGHOLD_CORRIDOR_CHEST,
                LootTables.STRONGHOLD_CROSSING_CHEST,
                LootTables.BASTION_TREASURE_CHEST,
                LootTables.BASTION_BRIDGE_CHEST,
                LootTables.BASTION_HOGLIN_STABLE_CHEST,
                LootTables.BASTION_OTHER_CHEST,
                LootTables.BURIED_TREASURE_CHEST
                );

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && LOOT_TABLES.contains(id)) {
                LootPool.Builder poolBuilder = LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(EmptyEntry.builder().weight(20))
                        .with(ItemEntry.builder(EFFICIENCY_SMITHING_TEMPLATE))
                        .with(ItemEntry.builder(FORTUNE_SMITHING_TEMPLATE))
                        .with(ItemEntry.builder(SILKTOUCH_SMITHING_TEMPLATE))
                        .with(ItemEntry.builder(UNBREAKING_SMITHING_TEMPLATE))
                        .with(ItemEntry.builder(MENDING_SMITHING_TEMPLATE)
                        );
                tableBuilder.pool(poolBuilder);
            }
        });
    }

    private static void registerModToolRods() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> content.addAfter( Items.CRYING_OBSIDIAN, BURNING_OBSIDIAN ) );
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
            content.addAfter( Items.STICK,
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
                WARPED_TINTED_TOOL_ROD );
            content.addAfter( Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE,
                    EFFICIENCY_SMITHING_TEMPLATE,
                    FORTUNE_SMITHING_TEMPLATE,
                    SILKTOUCH_SMITHING_TEMPLATE,
                    UNBREAKING_SMITHING_TEMPLATE,
                    MENDING_SMITHING_TEMPLATE);
        });
    }


    private static Item registerSmithingItem( String itemName, List<Identifier> outlines, String title, String type ) {
        return registerItem( itemName,
                new SmithingTemplateItem(
                        Text.translatable(Util.createTranslationKey("item", new Identifier(BetterTools.MOD_ID,"smithing_template.enchantment_upgrade.applies_to_" + type))).formatted(DESCRIPTION_FORMATTING),
                        ENCHANTMENT_TEMPLATE_INGREDIENTS_TEXT,
                        Text.translatable(title ).formatted(TITLE_FORMATTING),
                        Text.translatable( Util.createTranslationKey("item", new Identifier(BetterTools.MOD_ID,"smithing_template.enchantment_upgrade.base_slot_description_" + type ))),
                        ENCHANTMENT_TEMPLATE_ADDITIONS_SLOT_DESCRIPTION_TEXT,
                        outlines,
                        List.of(new Identifier("item/empty_slot_diamond"))));
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

    private static final List<Identifier> all = List.of(
            new Identifier("item/empty_slot_hoe"),
            new Identifier("item/empty_slot_axe"),
            new Identifier("item/empty_slot_sword"),
            new Identifier("item/empty_slot_shovel"),
            new Identifier("item/empty_slot_pickaxe"));

    private static final List<Identifier> tools = List.of(
            new Identifier("item/empty_slot_hoe"),
            new Identifier("item/empty_slot_axe"),
            new Identifier("item/empty_slot_shovel"),
            new Identifier("item/empty_slot_pickaxe"));

    private static final List<Identifier> weapons = List.of(
            new Identifier("item/empty_slot_axe"),
            new Identifier("item/empty_slot_sword"));

    public static final Item EFFICIENCY_SMITHING_TEMPLATE = registerSmithingItem( "efficiency_smithing_template", all, Util.createTranslationKey("item", new Identifier(BetterTools.MOD_ID, "smithing_template.efficiency_template.title")), "tool" );
    public static final Item UNBREAKING_SMITHING_TEMPLATE = registerSmithingItem( "unbreaking_smithing_template", all, Util.createTranslationKey("item", new Identifier(BetterTools.MOD_ID, "smithing_template.unbreaking_template.title")), "all" );
    public static final Item FORTUNE_SMITHING_TEMPLATE = registerSmithingItem( "fortune_smithing_template", tools, Util.createTranslationKey("item", new Identifier(BetterTools.MOD_ID, "smithing_template.fortune_template.title")),"tool" );
    public static final Item SILKTOUCH_SMITHING_TEMPLATE = registerSmithingItem( "silktouch_smithing_template", tools, Util.createTranslationKey("item", new Identifier(BetterTools.MOD_ID, "smithing_template.silktouch_template.title")), "tool" );
    public static final Item MENDING_SMITHING_TEMPLATE = registerSmithingItem( "mending_smithing_template", all, Util.createTranslationKey("item", new Identifier(BetterTools.MOD_ID, "smithing_template.mending_template.title")), "all" );

    public static final Item ACACIA_TINTED_TOOL_ROD = registerItem( "acacia_tinted_tool_rod", new TintedToolRodItem(0xC26D3F, new FabricItemSettings()));
    public static final Item BAMBOO_TINTED_TOOL_ROD = registerItem( "bamboo_tinted_tool_rod", new TintedToolRodItem(0xEFD97E, new FabricItemSettings()));
    public static final Item BIRCH_TINTED_TOOL_ROD = registerItem( "birch_tinted_tool_rod", new TintedToolRodItem(0xD7CB8D, new FabricItemSettings()));
    public static final Item CHERRY_TINTED_TOOL_ROD = registerItem( "cherry_tinted_tool_rod", new TintedToolRodItem(0xDD9D97, new FabricItemSettings()));
    public static final Item CRIMSON_TINTED_TOOL_ROD = registerItem( "crimson_tinted_tool_rod", new TintedToolRodItem(0xAA4072, new FabricItemSettings()));
    public static final Item DARK_OAK_TINTED_TOOL_ROD = registerItem( "dark_oak_tinted_tool_rod", new TintedToolRodItem(0x53381A, new FabricItemSettings()));
    public static final Item JUNGLE_TINTED_TOOL_ROD = registerItem( "jungle_tinted_tool_rod", new TintedToolRodItem(0xBF8E6B, new FabricItemSettings()));
    public static final Item MANGROVE_TINTED_TOOL_ROD = registerItem( "mangrove_tinted_tool_rod", new TintedToolRodItem(0x8C3539, new FabricItemSettings()));
    public static final Item OAK_TINTED_TOOL_ROD = registerItem( "oak_tinted_tool_rod", new TintedToolRodItem(0x8F7448, new FabricItemSettings()));
    public static final Item SPRUCE_TINTED_TOOL_ROD = registerItem( "spruce_tinted_tool_rod", new TintedToolRodItem(0x644A2A, new FabricItemSettings()));
    public static final Item WARPED_TINTED_TOOL_ROD = registerItem( "warped_tinted_tool_rod", new TintedToolRodItem(0x3A8E8C, new FabricItemSettings()));

    public static final Item BETTER_AMETHYST_AXE = registerItem( "better_amethyst_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_GEMSTONE,getToolAttackDamage("axe", "gemstone"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_ANDESITE_AXE = registerItem( "better_andesite_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_STONE,getToolAttackDamage("axe", "stone"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_BASALT_AXE = registerItem( "better_basalt_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_HARDSTONES,getToolAttackDamage("axe", "stone"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_BLACKSTONE_AXE = registerItem( "better_blackstone_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_STONE,getToolAttackDamage("axe", "stone"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_CALCITE_AXE = registerItem( "better_calcite_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_STONE,getToolAttackDamage("axe", "stone"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_COPPER_AXE = registerItem( "better_copper_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_SOFT_METALS,getToolAttackDamage("axe", "copper"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_CRYING_OBSIDIAN_AXE = registerItem( "better_crying_obsidian_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_OBSIDIAN,getToolAttackDamage("axe", "obsidian"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_DEEPSLATE_AXE = registerItem( "better_deepslate_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_HARDSTONES,getToolAttackDamage("axe", "stone"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_DIAMOND_AXE = registerItem( "better_diamond_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_DIAMOND,getToolAttackDamage("axe", "diamond"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_DIORITE_AXE = registerItem( "better_diorite_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_STONE,getToolAttackDamage("axe", "stone"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_EMERALD_AXE = registerItem( "better_emerald_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_GEMSTONE,getToolAttackDamage("axe", "gemstone"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_END_STONE_AXE = registerItem( "better_end_stone_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_HARDSTONES,getToolAttackDamage("axe", "stone"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_FLINT_AXE = registerItem( "better_flint_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_FLINT,getToolAttackDamage("axe", "flint"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_GOLD_AXE = registerItem( "better_gold_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_SOFT_METALS,getToolAttackDamage("axe", "gold"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_GRANITE_AXE = registerItem( "better_granite_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_STONE,getToolAttackDamage("axe", "stone"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_IRON_AXE = registerItem( "better_iron_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_IRON,getToolAttackDamage("axe", "iron"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_NETHERITE_AXE = registerItem( "better_netherite_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_NETHERITE,getToolAttackDamage("axe", "netherite"), getToolAttackSpeed("axe"), new FabricItemSettings().fireproof() ));
    public static final Item BETTER_NETHERRACK_AXE = registerItem( "better_netherrack_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_SOFTSTONES,getToolAttackDamage("axe", "stone"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_OBSIDIAN_AXE = registerItem( "better_obsidian_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_OBSIDIAN,getToolAttackDamage("axe", "obsidian"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_QUARTZ_AXE = registerItem( "better_quartz_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_GEMSTONE,getToolAttackDamage("axe", "gemstone"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_RED_SANDSTONE_AXE = registerItem( "better_red_sandstone_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_SOFTSTONES,getToolAttackDamage("axe", "stone"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_SANDSTONE_AXE = registerItem( "better_sandstone_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_SOFTSTONES,getToolAttackDamage("axe", "stone"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_STONE_AXE = registerItem( "better_stone_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_STONE,getToolAttackDamage("axe", "stone"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_TUFF_AXE = registerItem( "better_tuff_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_STONE,getToolAttackDamage("axe", "stone"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_WOODEN_AXE = registerItem( "better_wooden_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_WOOD,getToolAttackDamage("axe", "wood"), getToolAttackSpeed("axe"), new FabricItemSettings() ));

    public static final Item BETTER_AMETHYST_HOE = registerItem( "better_amethyst_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_GEMSTONE,(int)getToolAttackDamage("hoe", "gemstone"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_ANDESITE_HOE = registerItem( "better_andesite_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_STONE,(int)getToolAttackDamage("hoe", "stone"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_BASALT_HOE = registerItem( "better_basalt_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_HARDSTONES,(int)getToolAttackDamage("hoe", "stone"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_BLACKSTONE_HOE = registerItem( "better_blackstone_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_STONE,(int)getToolAttackDamage("hoe", "stone"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_CALCITE_HOE = registerItem( "better_calcite_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_STONE,(int)getToolAttackDamage("hoe", "stone"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_COPPER_HOE = registerItem( "better_copper_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_SOFT_METALS,(int)getToolAttackDamage("hoe", "copper"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_CRYING_OBSIDIAN_HOE = registerItem( "better_crying_obsidian_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_OBSIDIAN,(int)getToolAttackDamage("hoe", "obsidian"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_DEEPSLATE_HOE = registerItem( "better_deepslate_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_HARDSTONES,(int)getToolAttackDamage("hoe", "stone"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_DIAMOND_HOE = registerItem( "better_diamond_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_DIAMOND,(int)getToolAttackDamage("hoe", "diamond"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_DIORITE_HOE = registerItem( "better_diorite_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_STONE,(int)getToolAttackDamage("hoe", "stone"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_EMERALD_HOE = registerItem( "better_emerald_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_GEMSTONE,(int)getToolAttackDamage("hoe", "gemstone"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_END_STONE_HOE = registerItem( "better_end_stone_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_HARDSTONES,(int)getToolAttackDamage("hoe", "stone"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_FLINT_HOE = registerItem( "better_flint_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_FLINT,(int)getToolAttackDamage("hoe", "flint"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_GOLD_HOE = registerItem( "better_gold_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_SOFT_METALS,(int)getToolAttackDamage("hoe", "gold"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_GRANITE_HOE = registerItem( "better_granite_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_STONE,(int)getToolAttackDamage("hoe", "stone"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_IRON_HOE = registerItem( "better_iron_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_IRON,(int)getToolAttackDamage("hoe", "iron"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_NETHERITE_HOE = registerItem( "better_netherite_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_NETHERITE,(int)getToolAttackDamage("hoe", "netherite"), getToolAttackSpeed("hoe"), new FabricItemSettings().fireproof() ));
    public static final Item BETTER_NETHERRACK_HOE = registerItem( "better_netherrack_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_SOFTSTONES,(int)getToolAttackDamage("hoe", "stone"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_OBSIDIAN_HOE = registerItem( "better_obsidian_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_OBSIDIAN,(int)getToolAttackDamage("hoe", "obsidian"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_QUARTZ_HOE = registerItem( "better_quartz_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_GEMSTONE,(int)getToolAttackDamage("hoe", "gemstone"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_RED_SANDSTONE_HOE = registerItem( "better_red_sandstone_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_SOFTSTONES,(int)getToolAttackDamage("hoe", "stone"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_SANDSTONE_HOE = registerItem( "better_sandstone_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_SOFTSTONES,(int)getToolAttackDamage("hoe", "stone"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_STONE_HOE = registerItem( "better_stone_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_STONE,(int)getToolAttackDamage("hoe", "stone"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_TUFF_HOE = registerItem( "better_tuff_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_STONE,(int)getToolAttackDamage("hoe", "stone"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_WOODEN_HOE = registerItem( "better_wooden_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_WOOD,(int)getToolAttackDamage("hoe", "wood"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));

    public static final Item BETTER_AMETHYST_PICKAXE = registerItem( "better_amethyst_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_GEMSTONE,(int)getToolAttackDamage("pickaxe", "gemstone"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_ANDESITE_PICKAXE = registerItem( "better_andesite_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_STONE,(int)getToolAttackDamage("pickaxe", "stone"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_BASALT_PICKAXE = registerItem( "better_basalt_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_HARDSTONES,(int)getToolAttackDamage("pickaxe", "stone"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_BLACKSTONE_PICKAXE = registerItem( "better_blackstone_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_STONE,(int)getToolAttackDamage("pickaxe", "stone"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_CALCITE_PICKAXE = registerItem( "better_calcite_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_STONE,(int)getToolAttackDamage("pickaxe", "stone"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_COPPER_PICKAXE = registerItem( "better_copper_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_SOFT_METALS,(int)getToolAttackDamage("pickaxe", "copper"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_CRYING_OBSIDIAN_PICKAXE = registerItem( "better_crying_obsidian_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_OBSIDIAN,(int)getToolAttackDamage("pickaxe", "obsidian"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_DEEPSLATE_PICKAXE = registerItem( "better_deepslate_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_HARDSTONES,(int)getToolAttackDamage("pickaxe", "stone"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_DIAMOND_PICKAXE = registerItem( "better_diamond_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_DIAMOND,(int)getToolAttackDamage("pickaxe", "diamond"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_DIORITE_PICKAXE = registerItem( "better_diorite_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_STONE,(int)getToolAttackDamage("pickaxe", "stone"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_EMERALD_PICKAXE = registerItem( "better_emerald_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_GEMSTONE,(int)getToolAttackDamage("pickaxe", "gemstone"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_END_STONE_PICKAXE = registerItem( "better_end_stone_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_HARDSTONES,(int)getToolAttackDamage("pickaxe", "stone"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_FLINT_PICKAXE = registerItem( "better_flint_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_FLINT,(int)getToolAttackDamage("pickaxe", "flint"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_GOLD_PICKAXE = registerItem( "better_gold_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_SOFT_METALS,(int)getToolAttackDamage("pickaxe", "gold"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_GRANITE_PICKAXE = registerItem( "better_granite_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_STONE,(int)getToolAttackDamage("pickaxe", "stone"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_IRON_PICKAXE = registerItem( "better_iron_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_IRON,(int)getToolAttackDamage("pickaxe", "iron"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_NETHERITE_PICKAXE = registerItem( "better_netherite_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_NETHERITE,(int)getToolAttackDamage("pickaxe", "netherite"), getToolAttackSpeed("pickaxe"), new FabricItemSettings().fireproof() ));
    public static final Item BETTER_NETHERRACK_PICKAXE = registerItem( "better_netherrack_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_SOFTSTONES,(int)getToolAttackDamage("pickaxe", "stone"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_OBSIDIAN_PICKAXE = registerItem( "better_obsidian_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_OBSIDIAN,(int)getToolAttackDamage("pickaxe", "obsidian"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_QUARTZ_PICKAXE = registerItem( "better_quartz_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_GEMSTONE,(int)getToolAttackDamage("pickaxe", "gemstone"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_RED_SANDSTONE_PICKAXE = registerItem( "better_red_sandstone_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_SOFTSTONES,(int)getToolAttackDamage("pickaxe", "stone"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_SANDSTONE_PICKAXE = registerItem( "better_sandstone_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_SOFTSTONES,(int)getToolAttackDamage("pickaxe", "stone"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_STONE_PICKAXE = registerItem( "better_stone_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_STONE,(int)getToolAttackDamage("pickaxe", "stone"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_TUFF_PICKAXE = registerItem( "better_tuff_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_STONE,(int)getToolAttackDamage("pickaxe", "stone"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_WOODEN_PICKAXE = registerItem( "better_wooden_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_WOOD,(int)getToolAttackDamage("pickaxe", "wood"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));

    public static final Item BETTER_AMETHYST_SHOVEL = registerItem( "better_amethyst_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_GEMSTONE,getToolAttackDamage("shovel", "gemstone"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));
    public static final Item BETTER_ANDESITE_SHOVEL = registerItem( "better_andesite_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_STONE,getToolAttackDamage("shovel", "stone"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));
    public static final Item BETTER_BASALT_SHOVEL = registerItem( "better_basalt_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_HARDSTONES,getToolAttackDamage("shovel", "stone"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));
    public static final Item BETTER_BLACKSTONE_SHOVEL = registerItem( "better_blackstone_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_STONE,getToolAttackDamage("shovel", "stone"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));
    public static final Item BETTER_CALCITE_SHOVEL = registerItem( "better_calcite_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_STONE,getToolAttackDamage("shovel", "stone"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));
    public static final Item BETTER_CRYING_OBSIDIAN_SHOVEL = registerItem( "better_crying_obsidian_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_OBSIDIAN,getToolAttackDamage("shovel", "obsidian"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));
    public static final Item BETTER_COPPER_SHOVEL = registerItem( "better_copper_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_SOFT_METALS,getToolAttackDamage("shovel", "copper"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));
    public static final Item BETTER_DEEPSLATE_SHOVEL = registerItem( "better_deepslate_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_HARDSTONES,getToolAttackDamage("shovel", "stone"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));
    public static final Item BETTER_DIAMOND_SHOVEL = registerItem( "better_diamond_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_DIAMOND,getToolAttackDamage("shovel", "diamond"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));
    public static final Item BETTER_DIORITE_SHOVEL = registerItem( "better_diorite_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_STONE,getToolAttackDamage("shovel", "stone"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));
    public static final Item BETTER_EMERALD_SHOVEL = registerItem( "better_emerald_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_GEMSTONE,getToolAttackDamage("shovel", "gemstone"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));
    public static final Item BETTER_END_STONE_SHOVEL = registerItem( "better_end_stone_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_HARDSTONES,getToolAttackDamage("shovel", "stone"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));
    public static final Item BETTER_FLINT_SHOVEL = registerItem( "better_flint_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_FLINT,getToolAttackDamage("shovel", "flint"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));
    public static final Item BETTER_GOLD_SHOVEL = registerItem( "better_gold_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_SOFT_METALS,getToolAttackDamage("shovel", "gold"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));
    public static final Item BETTER_GRANITE_SHOVEL = registerItem( "better_granite_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_STONE,getToolAttackDamage("shovel", "stone"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));
    public static final Item BETTER_IRON_SHOVEL = registerItem( "better_iron_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_IRON,getToolAttackDamage("shovel", "iron"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));
    public static final Item BETTER_NETHERITE_SHOVEL = registerItem( "better_netherite_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_NETHERITE,getToolAttackDamage("shovel", "netherite"), getToolAttackSpeed("shovel"), new FabricItemSettings().fireproof() ));
    public static final Item BETTER_NETHERRACK_SHOVEL = registerItem( "better_netherrack_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_SOFTSTONES,getToolAttackDamage("shovel", "stone"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));
    public static final Item BETTER_OBSIDIAN_SHOVEL = registerItem( "better_obsidian_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_OBSIDIAN,getToolAttackDamage("shovel", "obsidian"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));
    public static final Item BETTER_QUARTZ_SHOVEL = registerItem( "better_quartz_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_GEMSTONE,getToolAttackDamage("shovel", "gemstone"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));
    public static final Item BETTER_RED_SANDSTONE_SHOVEL = registerItem( "better_red_sandstone_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_SOFTSTONES,getToolAttackDamage("shovel", "stone"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));
    public static final Item BETTER_SANDSTONE_SHOVEL = registerItem( "better_sandstone_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_SOFTSTONES,getToolAttackDamage("shovel", "stone"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));
    public static final Item BETTER_STONE_SHOVEL = registerItem( "better_stone_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_STONE,getToolAttackDamage("shovel", "stone"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));
    public static final Item BETTER_TUFF_SHOVEL = registerItem( "better_tuff_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_STONE,getToolAttackDamage("shovel", "stone"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));
    public static final Item BETTER_WOODEN_SHOVEL = registerItem( "better_wooden_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_WOOD,getToolAttackDamage("shovel", "wood"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));

    public static final Item BETTER_AMETHYST_SWORD = registerItem( "better_amethyst_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_GEMSTONE,(int)getToolAttackDamage("sword", "gemstone"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_ANDESITE_SWORD = registerItem( "better_andesite_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_STONE,(int)getToolAttackDamage("sword", "stone"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_BASALT_SWORD = registerItem( "better_basalt_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_HARDSTONES,(int)getToolAttackDamage("sword", "stone"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_BLACKSTONE_SWORD = registerItem( "better_blackstone_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_STONE,(int)getToolAttackDamage("sword", "stone"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_CALCITE_SWORD = registerItem( "better_calcite_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_STONE,(int)getToolAttackDamage("sword", "stone"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_COPPER_SWORD = registerItem( "better_copper_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_SOFT_METALS,(int)getToolAttackDamage("sword", "copper"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_CRYING_OBSIDIAN_SWORD = registerItem( "better_crying_obsidian_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_OBSIDIAN,(int)getToolAttackDamage("sword", "obsidian"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_DEEPSLATE_SWORD = registerItem( "better_deepslate_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_HARDSTONES,(int)getToolAttackDamage("sword", "stone"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_DIAMOND_SWORD = registerItem( "better_diamond_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_DIAMOND,(int)getToolAttackDamage("sword", "diamond"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_DIORITE_SWORD = registerItem( "better_diorite_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_STONE,(int)getToolAttackDamage("sword", "stone"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_EMERALD_SWORD = registerItem( "better_emerald_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_GEMSTONE,(int)getToolAttackDamage("sword", "gemstone"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_END_STONE_SWORD = registerItem( "better_end_stone_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_HARDSTONES,(int)getToolAttackDamage("sword", "stone"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_FLINT_SWORD = registerItem( "better_flint_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_FLINT,(int)getToolAttackDamage("sword", "flint"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_GOLD_SWORD = registerItem( "better_gold_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_SOFT_METALS,(int)getToolAttackDamage("sword", "gold"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_GRANITE_SWORD = registerItem( "better_granite_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_STONE,(int)getToolAttackDamage("sword", "stone"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_IRON_SWORD = registerItem( "better_iron_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_IRON,(int)getToolAttackDamage("sword", "iron"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_NETHERITE_SWORD = registerItem( "better_netherite_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_NETHERITE,(int)getToolAttackDamage("sword", "netherite"), getToolAttackSpeed("sword"), new FabricItemSettings().fireproof() ));
    public static final Item BETTER_NETHERRACK_SWORD = registerItem( "better_netherrack_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_SOFTSTONES,(int)getToolAttackDamage("sword", "stone"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_OBSIDIAN_SWORD = registerItem( "better_obsidian_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_OBSIDIAN,(int)getToolAttackDamage("sword", "obsidian"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_QUARTZ_SWORD = registerItem( "better_quartz_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_GEMSTONE,(int)getToolAttackDamage("sword", "gemstone"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_RED_SANDSTONE_SWORD = registerItem( "better_red_sandstone_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_SOFTSTONES,(int)getToolAttackDamage("sword", "stone"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_SANDSTONE_SWORD = registerItem( "better_sandstone_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_SOFTSTONES,(int)getToolAttackDamage("sword", "stone"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_STONE_SWORD = registerItem( "better_stone_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_STONE,(int)getToolAttackDamage("sword", "stone"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_TUFF_SWORD = registerItem( "better_tuff_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_STONE,(int)getToolAttackDamage("sword", "stone"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_WOODEN_SWORD = registerItem( "better_wooden_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_WOOD,(int)getToolAttackDamage("sword", "wood"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
}
