package net.rockgiant.bettertools.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import static net.rockgiant.bettertools.item.ModItems.*;

public class ModLangProvider extends FabricLanguageProvider {
    public ModLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        generateShovelTranslations(translationBuilder);
        generateAxeTranslations(translationBuilder);
        generateHoeTranslations(translationBuilder);
        generatePickaxeTranslations(translationBuilder);
        generateSwordTranslations(translationBuilder);
        generateToolRodTranslations(translationBuilder);
    }

    private void generateToolRodTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add( ACACIA_TINTED_TOOL_ROD, "Acacia Tool Rod");
        translationBuilder.add( BAMBOO_TINTED_TOOL_ROD, "Bamboo Tool Rod");
        translationBuilder.add( BIRCH_TINTED_TOOL_ROD, "Birch Tool Rod");
        translationBuilder.add( CHERRY_TINTED_TOOL_ROD, "Cherry Tool Rod");
        translationBuilder.add( CRIMSON_TINTED_TOOL_ROD, "Crimson Tool Rod");
        translationBuilder.add( DARK_OAK_TINTED_TOOL_ROD, "Dark Oak Tool Rod");
        translationBuilder.add( JUNGLE_TINTED_TOOL_ROD, "Jungle Tool Rod");
        translationBuilder.add( MANGROVE_TINTED_TOOL_ROD, "Mangrove Tool Rod");
        translationBuilder.add( OAK_TINTED_TOOL_ROD, "OAk Tool Rod");
        translationBuilder.add( SPRUCE_TINTED_TOOL_ROD, "Spruce Tool Rod");
        translationBuilder.add( WARPED_TINTED_TOOL_ROD, "Warped Tool Rod");
    }

    private void generateShovelTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add( BETTER_WOODEN_SHOVEL, "Wooden Shovel");
        translationBuilder.add( BETTER_STONE_SHOVEL, "Stone Shovel");
        translationBuilder.add( BETTER_IRON_SHOVEL, "Iron Shovel");
        translationBuilder.add( BETTER_GOLD_SHOVEL, "Gold Shovel");
        translationBuilder.add( BETTER_DIAMOND_SHOVEL, "Diamond Shovel");
        translationBuilder.add( BETTER_NETHERITE_SHOVEL, "Netherite Shovel");
        translationBuilder.add( BETTER_FLINT_SHOVEL, "Flint Shovel");
        translationBuilder.add( BETTER_OBSIDIAN_SHOVEL, "Obsidian Shovel");
        translationBuilder.add( BETTER_CRYING_OBSIDIAN_SHOVEL, "Crying Obsidian Shovel");
        translationBuilder.add( BETTER_GRANITE_SHOVEL, "Granite Shovel");
        translationBuilder.add( BETTER_ANDESITE_SHOVEL, "Andesite Shovel");
        translationBuilder.add( BETTER_DIORITE_SHOVEL, "Diorite Shovel");
        translationBuilder.add( BETTER_TUFF_SHOVEL, "Tuff Shovel");
        translationBuilder.add( BETTER_CALCITE_SHOVEL, "Calcite Shovel");
        translationBuilder.add( BETTER_BLACKSTONE_SHOVEL, "Blackstone Shovel");
        translationBuilder.add( BETTER_SANDSTONE_SHOVEL, "Sandstone Shovel");
        translationBuilder.add( BETTER_RED_SANDSTONE_SHOVEL, "Red Sandstone Shovel");
        translationBuilder.add( BETTER_EMERALD_SHOVEL, "Emerald Shovel");
        translationBuilder.add( BETTER_AMETHYST_SHOVEL, "Amethyst Shovel");
        translationBuilder.add( BETTER_END_STONE_SHOVEL, "End Stone Shovel");
        translationBuilder.add( BETTER_DEEPSLATE_SHOVEL, "Deepslate Shovel");
        translationBuilder.add( BETTER_BASALT_SHOVEL, "Basalt Shovel");
        translationBuilder.add( BETTER_QUARTZ_SHOVEL, "Quartz Shovel");
        translationBuilder.add( BETTER_NETHERRACK_SHOVEL, "Netherrack Shovel");
        translationBuilder.add( BETTER_COPPER_SHOVEL, "Copper Shovel");
    }

    private void generateAxeTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add( BETTER_WOODEN_AXE, "Wooden Axe");
        translationBuilder.add( BETTER_STONE_AXE, "Stone Axe");
        translationBuilder.add( BETTER_IRON_AXE, "Iron Axe");
        translationBuilder.add( BETTER_GOLD_AXE, "Gold Axe");
        translationBuilder.add( BETTER_DIAMOND_AXE, "Diamond Axe");
        translationBuilder.add( BETTER_NETHERITE_AXE, "Netherite Axe");
        translationBuilder.add( BETTER_FLINT_AXE, "Flint Axe");
        translationBuilder.add( BETTER_OBSIDIAN_AXE, "Obsidian Axe");
        translationBuilder.add( BETTER_CRYING_OBSIDIAN_AXE, "Crying Obsidian Axe");
        translationBuilder.add( BETTER_GRANITE_AXE, "Granite Axe");
        translationBuilder.add( BETTER_ANDESITE_AXE, "Andesite Axe");
        translationBuilder.add( BETTER_DIORITE_AXE, "Diorite Axe");
        translationBuilder.add( BETTER_TUFF_AXE, "Tuff Axe");
        translationBuilder.add( BETTER_CALCITE_AXE, "Calcite Axe");
        translationBuilder.add( BETTER_BLACKSTONE_AXE, "Blackstone Axe");
        translationBuilder.add( BETTER_SANDSTONE_AXE, "Sandstone Axe");
        translationBuilder.add( BETTER_RED_SANDSTONE_AXE, "Red Sandstone Axe");
        translationBuilder.add( BETTER_EMERALD_AXE, "Emerald Axe");
        translationBuilder.add( BETTER_AMETHYST_AXE, "Amethyst Axe");
        translationBuilder.add( BETTER_END_STONE_AXE, "End Stone Axe");
        translationBuilder.add( BETTER_DEEPSLATE_AXE, "Deepslate Axe");
        translationBuilder.add( BETTER_BASALT_AXE, "Basalt Axe");
        translationBuilder.add( BETTER_QUARTZ_AXE, "Quartz Axe");
        translationBuilder.add( BETTER_NETHERRACK_AXE, "Netherrack Axe");
        translationBuilder.add( BETTER_COPPER_AXE, "Copper Axe");
    }

    private void generateSwordTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add( BETTER_WOODEN_SWORD, "Wooden Sword");
        translationBuilder.add( BETTER_STONE_SWORD, "Stone Sword");
        translationBuilder.add( BETTER_IRON_SWORD, "Iron Sword");
        translationBuilder.add( BETTER_GOLD_SWORD, "Gold Sword");
        translationBuilder.add( BETTER_DIAMOND_SWORD, "Diamond Sword");
        translationBuilder.add( BETTER_NETHERITE_SWORD, "Netherite Sword");
        translationBuilder.add( BETTER_FLINT_SWORD, "Flint Sword");
        translationBuilder.add( BETTER_OBSIDIAN_SWORD, "Obsidian Sword");
        translationBuilder.add( BETTER_CRYING_OBSIDIAN_SWORD, "Crying Obsidian Sword");
        translationBuilder.add( BETTER_GRANITE_SWORD, "Granite Sword");
        translationBuilder.add( BETTER_ANDESITE_SWORD, "Andesite Sword");
        translationBuilder.add( BETTER_DIORITE_SWORD, "Diorite Sword");
        translationBuilder.add( BETTER_TUFF_SWORD, "Tuff Sword");
        translationBuilder.add( BETTER_CALCITE_SWORD, "Calcite Sword");
        translationBuilder.add( BETTER_BLACKSTONE_SWORD, "Blackstone Sword");
        translationBuilder.add( BETTER_SANDSTONE_SWORD, "Sandstone Sword");
        translationBuilder.add( BETTER_RED_SANDSTONE_SWORD, "Red Sandstone Sword");
        translationBuilder.add( BETTER_EMERALD_SWORD, "Emerald Sword");
        translationBuilder.add( BETTER_AMETHYST_SWORD, "Amethyst Sword");
        translationBuilder.add( BETTER_END_STONE_SWORD, "End Stone Sword");
        translationBuilder.add( BETTER_DEEPSLATE_SWORD, "Deepslate Sword");
        translationBuilder.add( BETTER_BASALT_SWORD, "Basalt Sword");
        translationBuilder.add( BETTER_QUARTZ_SWORD, "Quartz Sword");
        translationBuilder.add( BETTER_NETHERRACK_SWORD, "Netherrack Sword");
        translationBuilder.add( BETTER_COPPER_SWORD, "Copper Sword");
    }

    private void generatePickaxeTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add( BETTER_WOODEN_PICKAXE, "Wooden Pickaxe");
        translationBuilder.add( BETTER_STONE_PICKAXE, "Stone Pickaxe");
        translationBuilder.add( BETTER_IRON_PICKAXE, "Iron Pickaxe");
        translationBuilder.add( BETTER_GOLD_PICKAXE, "Gold Pickaxe");
        translationBuilder.add( BETTER_DIAMOND_PICKAXE, "Diamond Pickaxe");
        translationBuilder.add( BETTER_NETHERITE_PICKAXE, "Netherite Pickaxe");
        translationBuilder.add( BETTER_FLINT_PICKAXE, "Flint Pickaxe");
        translationBuilder.add( BETTER_OBSIDIAN_PICKAXE, "Obsidian Pickaxe");
        translationBuilder.add( BETTER_CRYING_OBSIDIAN_PICKAXE, "Crying Obsidian Pickaxe");
        translationBuilder.add( BETTER_GRANITE_PICKAXE, "Granite Pickaxe");
        translationBuilder.add( BETTER_ANDESITE_PICKAXE, "Andesite Pickaxe");
        translationBuilder.add( BETTER_DIORITE_PICKAXE, "Diorite Pickaxe");
        translationBuilder.add( BETTER_TUFF_PICKAXE, "Tuff Pickaxe");
        translationBuilder.add( BETTER_CALCITE_PICKAXE, "Calcite Pickaxe");
        translationBuilder.add( BETTER_BLACKSTONE_PICKAXE, "Blackstone Pickaxe");
        translationBuilder.add( BETTER_SANDSTONE_PICKAXE, "Sandstone Pickaxe");
        translationBuilder.add( BETTER_RED_SANDSTONE_PICKAXE, "Red Sandstone Pickaxe");
        translationBuilder.add( BETTER_EMERALD_PICKAXE, "Emerald Pickaxe");
        translationBuilder.add( BETTER_AMETHYST_PICKAXE, "Amethyst Pickaxe");
        translationBuilder.add( BETTER_END_STONE_PICKAXE, "End Stone Pickaxe");
        translationBuilder.add( BETTER_DEEPSLATE_PICKAXE, "Deepslate Pickaxe");
        translationBuilder.add( BETTER_BASALT_PICKAXE, "Basalt Pickaxe");
        translationBuilder.add( BETTER_QUARTZ_PICKAXE, "Quartz Pickaxe");
        translationBuilder.add( BETTER_NETHERRACK_PICKAXE, "Netherrack Pickaxe");
        translationBuilder.add( BETTER_COPPER_PICKAXE, "Copper Pickaxe");
    }

    private void generateHoeTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add( BETTER_WOODEN_HOE, "Wooden Hoe");
        translationBuilder.add( BETTER_STONE_HOE, "Stone Hoe");
        translationBuilder.add( BETTER_IRON_HOE, "Iron Hoe");
        translationBuilder.add( BETTER_GOLD_HOE, "Gold Hoe");
        translationBuilder.add( BETTER_DIAMOND_HOE, "Diamond Hoe");
        translationBuilder.add( BETTER_NETHERITE_HOE, "Netherite Hoe");
        translationBuilder.add( BETTER_FLINT_HOE, "Flint Hoe");
        translationBuilder.add( BETTER_OBSIDIAN_HOE, "Obsidian Hoe");
        translationBuilder.add( BETTER_CRYING_OBSIDIAN_HOE, "Crying Obsidian Hoe");
        translationBuilder.add( BETTER_GRANITE_HOE, "Granite Hoe");
        translationBuilder.add( BETTER_ANDESITE_HOE, "Andesite Hoe");
        translationBuilder.add( BETTER_DIORITE_HOE, "Diorite Hoe");
        translationBuilder.add( BETTER_TUFF_HOE, "Tuff Hoe");
        translationBuilder.add( BETTER_CALCITE_HOE, "Calcite Hoe");
        translationBuilder.add( BETTER_BLACKSTONE_HOE, "Blackstone Hoe");
        translationBuilder.add( BETTER_SANDSTONE_HOE, "Sandstone Hoe");
        translationBuilder.add( BETTER_RED_SANDSTONE_HOE, "Red Sandstone Hoe");
        translationBuilder.add( BETTER_EMERALD_HOE, "Emerald Hoe");
        translationBuilder.add( BETTER_AMETHYST_HOE, "Amethyst Hoe");
        translationBuilder.add( BETTER_END_STONE_HOE, "End Stone Hoe");
        translationBuilder.add( BETTER_DEEPSLATE_HOE, "Deepslate Hoe");
        translationBuilder.add( BETTER_BASALT_HOE, "Basalt Hoe");
        translationBuilder.add( BETTER_QUARTZ_HOE, "Quartz Hoe");
        translationBuilder.add( BETTER_NETHERRACK_HOE, "Netherrack Hoe");
        translationBuilder.add( BETTER_COPPER_HOE, "Copper Hoe");
    }
}
