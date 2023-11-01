package net.rockgiant.bettertools.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.server.recipe.ComplexRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.rockgiant.bettertools.BetterTools;
import net.rockgiant.bettertools.util.recipe.BetterToolsCraftingRecipeJsonBuilder;
import net.rockgiant.bettertools.util.recipe.BetterToolsRepairRecipe;
import net.rockgiant.bettertools.util.recipe.ModRecipes;

import static net.rockgiant.bettertools.item.ModItems.*;


public class ModRecipeProvider extends FabricRecipeProvider {


    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generate(RecipeExporter exporter) {
        generateToolRodRecipes(exporter);

        generateAxeRecipes(exporter);
        generateHoeRecipes(exporter);
        generatePickaxeRecipes(exporter);
        generateShovelRecipes(exporter);
        generateSwordRecipes(exporter);

        generateBetterToolsNetheriteSmithingRecipes(exporter);

        // ADD special repair recipe...
        ComplexRecipeJsonBuilder.create(BetterToolsRepairRecipe.TOOLS_REPAIR_SERIALIZER).offerTo(exporter, new Identifier(BetterTools.MOD_ID, BetterToolsRepairRecipe.ID ));


    }

    public void generateToolRodRecipes(RecipeExporter exporter ) {
        generateToolRodRecipe(exporter, ACACIA_TINTED_TOOL_ROD, Items.ACACIA_PLANKS );
        generateToolRodRecipe(exporter, BAMBOO_TINTED_TOOL_ROD, Items.BAMBOO_PLANKS );
        generateToolRodRecipe(exporter, BIRCH_TINTED_TOOL_ROD, Items.BIRCH_PLANKS );
        generateToolRodRecipe(exporter, CHERRY_TINTED_TOOL_ROD, Items.CHERRY_PLANKS );
        generateToolRodRecipe(exporter, CRIMSON_TINTED_TOOL_ROD, Items.CRIMSON_PLANKS );
        generateToolRodRecipe(exporter, DARK_OAK_TINTED_TOOL_ROD, Items.DARK_OAK_PLANKS );
        generateToolRodRecipe(exporter, JUNGLE_TINTED_TOOL_ROD, Items.JUNGLE_PLANKS );
        generateToolRodRecipe(exporter, MANGROVE_TINTED_TOOL_ROD, Items.MANGROVE_PLANKS );
        generateToolRodRecipe(exporter, OAK_TINTED_TOOL_ROD, Items.OAK_PLANKS );
        generateToolRodRecipe(exporter, SPRUCE_TINTED_TOOL_ROD, Items.SPRUCE_PLANKS );
        generateToolRodRecipe(exporter, WARPED_TINTED_TOOL_ROD, Items.WARPED_PLANKS );
    }

    public void generateToolRodRecipe(RecipeExporter exporter, Item item, Item ingredient )
    {
        ShapedRecipeJsonBuilder.create( RecipeCategory.TOOLS, item, 2 ).pattern("# ").pattern(" #").input('#', Ingredient.ofItems(ingredient)).criterion(FabricRecipeProvider.hasItem(ingredient), FabricRecipeProvider.conditionsFromItem(ingredient)).offerTo(exporter);
    }

    // may be used for smithing upgrades, as I think those naturally keep nbt data. So I can still use them.
    public void generateSmithingTableUpgradeRecipe(RecipeExporter exporter, Item result, Item ingredient, String recipeId ) {
        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems( Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE ),
                Ingredient.ofItems( ingredient ),
                Ingredient.ofItems( Items.NETHERITE_INGOT ),
                RecipeCategory.TOOLS,
                result)
                .criterion(FabricRecipeProvider.hasItem( Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE ),
                        FabricRecipeProvider.conditionsFromItem( Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .criterion(FabricRecipeProvider.hasItem( Items.NETHERITE_INGOT ),
                        FabricRecipeProvider.conditionsFromItem( Items.NETHERITE_INGOT ) )
                .offerTo(exporter, recipeId);
    }

    public void generateBetterToolsCraftingRecipe(RecipeExporter exporter, Item result, Ingredient ingredient, String recipeId ) {
        BetterToolsCraftingRecipeJsonBuilder.create(ingredient,result).offerTo(exporter,recipeId);
    }

    public void generateBetterToolsNetheriteSmithingRecipes(RecipeExporter exporter) {
        generateSmithingTableUpgradeRecipe(exporter, BETTER_NETHERITE_AXE, BETTER_DIAMOND_AXE, "better_netherite_axe_smithing_recipe");
        generateSmithingTableUpgradeRecipe(exporter, BETTER_NETHERITE_PICKAXE, BETTER_DIAMOND_PICKAXE, "better_netherite_pickaxe_smithing_recipe");
        generateSmithingTableUpgradeRecipe(exporter, BETTER_NETHERITE_SHOVEL, BETTER_DIAMOND_SHOVEL, "better_netherite_shovel_smithing_recipe");
        generateSmithingTableUpgradeRecipe(exporter, BETTER_NETHERITE_SWORD, BETTER_DIAMOND_SWORD, "better_netherite_sword_smithing_recipe");
        generateSmithingTableUpgradeRecipe(exporter, BETTER_NETHERITE_HOE, BETTER_DIAMOND_HOE, "better_netherite_hoe_smithing_recipe");
    }

    private void generateAxeRecipes(RecipeExporter exporter) {
        generateBetterToolsCraftingRecipe(exporter, BETTER_AMETHYST_AXE, Ingredient.ofItems( Items.AMETHYST_SHARD ), "better_tools_amethyst_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_ANDESITE_AXE, Ingredient.ofItems( Items.ANDESITE ), "better_tools_andesite_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_BASALT_AXE, Ingredient.ofItems( Items.BASALT ), "better_tools_basalt_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_BLACKSTONE_AXE, Ingredient.ofItems( Items.BLACKSTONE ), "better_tools_blackstone_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_CALCITE_AXE, Ingredient.ofItems( Items.CALCITE ), "better_tools_calcite_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_COPPER_AXE, Ingredient.ofItems( Items.COPPER_INGOT ), "better_tools_copper_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_CRYING_OBSIDIAN_AXE, Ingredient.ofItems( Items.CRYING_OBSIDIAN ), "better_tools_crying_obsidian_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DEEPSLATE_AXE, Ingredient.ofItems( Items.DEEPSLATE ), "better_tools_deepslate_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DIAMOND_AXE, Ingredient.ofItems( Items.DIAMOND ), "better_tools_diamond_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DIORITE_AXE, Ingredient.ofItems( Items.DIORITE ), "better_tools_diorite_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_EMERALD_AXE, Ingredient.ofItems( Items.EMERALD ), "better_tools_emerald_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_END_STONE_AXE, Ingredient.ofItems( Items.END_STONE ), "better_tools_endstone_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_FLINT_AXE, Ingredient.ofItems( Items.FLINT ), "better_tools_flint_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_GOLD_AXE, Ingredient.ofItems( Items.GOLD_INGOT ), "better_tools_gold_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_GRANITE_AXE, Ingredient.ofItems( Items.GRANITE ), "better_tools_granite_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_IRON_AXE, Ingredient.ofItems( Items.IRON_INGOT ), "better_tools_iron_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_NETHERRACK_AXE, Ingredient.ofItems( Items.NETHERRACK ), "better_tools_netherrack_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_OBSIDIAN_AXE, Ingredient.ofItems( Items.OBSIDIAN ), "better_tools_obsidian_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_QUARTZ_AXE, Ingredient.ofItems( Items.QUARTZ ), "better_tools_quartz_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_RED_SANDSTONE_AXE, Ingredient.ofItems( Items.RED_SANDSTONE ), "better_tools_red_sandstone_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_SANDSTONE_AXE, Ingredient.ofItems( Items.SANDSTONE ), "better_tools_sandstone_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_STONE_AXE, Ingredient.ofItems( Items.COBBLESTONE ), "better_tools_stone_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_TUFF_AXE, Ingredient.ofItems( Items.TUFF ), "better_tools_tuff_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_WOODEN_AXE, Ingredient.fromTag( ItemTags.PLANKS ), "better_tools_wooden_axe_recipe" );
    }

    private void generateHoeRecipes(RecipeExporter exporter) {
        generateBetterToolsCraftingRecipe(exporter, BETTER_AMETHYST_HOE, Ingredient.ofItems( Items.AMETHYST_SHARD ), "better_tools_amethyst_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_ANDESITE_HOE, Ingredient.ofItems( Items.ANDESITE ), "better_tools_andesite_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_BASALT_HOE, Ingredient.ofItems( Items.BASALT ), "better_tools_basalt_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_BLACKSTONE_HOE, Ingredient.ofItems( Items.BLACKSTONE ), "better_tools_blackstone_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_CALCITE_HOE, Ingredient.ofItems( Items.CALCITE ), "better_tools_calcite_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_COPPER_HOE, Ingredient.ofItems( Items.COPPER_INGOT ), "better_tools_copper_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_CRYING_OBSIDIAN_HOE, Ingredient.ofItems( Items.CRYING_OBSIDIAN ), "better_tools_crying_obsidian_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DEEPSLATE_HOE, Ingredient.ofItems( Items.DEEPSLATE ), "better_tools_deepslate_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DIAMOND_HOE, Ingredient.ofItems( Items.DIAMOND ), "better_tools_diamond_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DIORITE_HOE, Ingredient.ofItems( Items.DIORITE ), "better_tools_diorite_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_EMERALD_HOE, Ingredient.ofItems( Items.EMERALD ), "better_tools_emerald_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_END_STONE_HOE, Ingredient.ofItems( Items.END_STONE ), "better_tools_endstone_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_FLINT_HOE, Ingredient.ofItems( Items.FLINT ), "better_tools_flint_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_GOLD_HOE, Ingredient.ofItems( Items.GOLD_INGOT ), "better_tools_gold_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_GRANITE_HOE, Ingredient.ofItems( Items.GRANITE ), "better_tools_granite_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_IRON_HOE, Ingredient.ofItems( Items.IRON_INGOT ), "better_tools_iron_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_NETHERRACK_HOE, Ingredient.ofItems( Items.NETHERRACK ), "better_tools_netherrack_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_OBSIDIAN_HOE, Ingredient.ofItems( Items.OBSIDIAN ), "better_tools_obsidian_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_QUARTZ_HOE, Ingredient.ofItems( Items.QUARTZ ), "better_tools_quartz_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_RED_SANDSTONE_HOE, Ingredient.ofItems( Items.RED_SANDSTONE ), "better_tools_red_sandstone_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_SANDSTONE_HOE, Ingredient.ofItems( Items.SANDSTONE ), "better_tools_sandstone_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_STONE_HOE, Ingredient.ofItems( Items.COBBLESTONE ), "better_tools_stone_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_TUFF_HOE, Ingredient.ofItems( Items.TUFF ), "better_tools_tuff_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_WOODEN_HOE, Ingredient.fromTag( ItemTags.PLANKS ), "better_tools_wooden_hoe_recipe" );
    }

    private void generatePickaxeRecipes(RecipeExporter exporter) {
        generateBetterToolsCraftingRecipe(exporter, BETTER_AMETHYST_PICKAXE, Ingredient.ofItems( Items.AMETHYST_SHARD ), "better_tools_amethyst_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_ANDESITE_PICKAXE, Ingredient.ofItems( Items.ANDESITE ), "better_tools_andesite_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_BASALT_PICKAXE, Ingredient.ofItems( Items.BASALT ), "better_tools_basalt_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_BLACKSTONE_PICKAXE, Ingredient.ofItems( Items.BLACKSTONE ), "better_tools_blackstone_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_CALCITE_PICKAXE, Ingredient.ofItems( Items.CALCITE ), "better_tools_calcite_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_COPPER_PICKAXE, Ingredient.ofItems( Items.COPPER_INGOT ), "better_tools_copper_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_CRYING_OBSIDIAN_PICKAXE, Ingredient.ofItems( Items.CRYING_OBSIDIAN ), "better_tools_crying_obsidian_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DEEPSLATE_PICKAXE, Ingredient.ofItems( Items.DEEPSLATE ), "better_tools_deepslate_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DIAMOND_PICKAXE, Ingredient.ofItems( Items.DIAMOND ), "better_tools_diamond_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DIORITE_PICKAXE, Ingredient.ofItems( Items.DIORITE ), "better_tools_diorite_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_EMERALD_PICKAXE, Ingredient.ofItems( Items.EMERALD ), "better_tools_emerald_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_END_STONE_PICKAXE, Ingredient.ofItems( Items.END_STONE ), "better_tools_endstone_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_FLINT_PICKAXE, Ingredient.ofItems( Items.FLINT ), "better_tools_flint_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_GOLD_PICKAXE, Ingredient.ofItems( Items.GOLD_INGOT ), "better_tools_gold_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_GRANITE_PICKAXE, Ingredient.ofItems( Items.GRANITE ), "better_tools_granite_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_IRON_PICKAXE, Ingredient.ofItems( Items.IRON_INGOT ), "better_tools_iron_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_NETHERRACK_PICKAXE, Ingredient.ofItems( Items.NETHERRACK ), "better_tools_netherrack_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_OBSIDIAN_PICKAXE, Ingredient.ofItems( Items.OBSIDIAN ), "better_tools_obsidian_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_QUARTZ_PICKAXE, Ingredient.ofItems( Items.QUARTZ ), "better_tools_quartz_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_RED_SANDSTONE_PICKAXE, Ingredient.ofItems( Items.RED_SANDSTONE ), "better_tools_red_sandstone_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_SANDSTONE_PICKAXE, Ingredient.ofItems( Items.SANDSTONE ), "better_tools_sandstone_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_STONE_PICKAXE, Ingredient.ofItems( Items.COBBLESTONE ), "better_tools_stone_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_TUFF_PICKAXE, Ingredient.ofItems( Items.TUFF ), "better_tools_tuff_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_WOODEN_PICKAXE, Ingredient.fromTag( ItemTags.PLANKS ), "better_tools_wooden_pickaxe_recipe" );
    }

    private void generateShovelRecipes(RecipeExporter exporter) {
        generateBetterToolsCraftingRecipe(exporter, BETTER_AMETHYST_SHOVEL, Ingredient.ofItems( Items.AMETHYST_SHARD ), "better_tools_amethyst_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_ANDESITE_SHOVEL, Ingredient.ofItems( Items.ANDESITE ), "better_tools_andesite_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_BASALT_SHOVEL, Ingredient.ofItems( Items.BASALT ), "better_tools_basalt_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_BLACKSTONE_SHOVEL, Ingredient.ofItems( Items.BLACKSTONE ), "better_tools_blackstone_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_CALCITE_SHOVEL, Ingredient.ofItems( Items.CALCITE ), "better_tools_calcite_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_COPPER_SHOVEL, Ingredient.ofItems( Items.COPPER_INGOT ), "better_tools_copper_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_CRYING_OBSIDIAN_SHOVEL, Ingredient.ofItems( Items.CRYING_OBSIDIAN ), "better_tools_crying_obsidian_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DEEPSLATE_SHOVEL, Ingredient.ofItems( Items.DEEPSLATE ), "better_tools_deepslate_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DIAMOND_SHOVEL, Ingredient.ofItems( Items.DIAMOND ), "better_tools_diamond_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DIORITE_SHOVEL, Ingredient.ofItems( Items.DIORITE ), "better_tools_diorite_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_EMERALD_SHOVEL, Ingredient.ofItems( Items.EMERALD ), "better_tools_emerald_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_END_STONE_SHOVEL, Ingredient.ofItems( Items.END_STONE ), "better_tools_endstone_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_FLINT_SHOVEL, Ingredient.ofItems( Items.FLINT ), "better_tools_flint_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_GOLD_SHOVEL, Ingredient.ofItems( Items.GOLD_INGOT ), "better_tools_gold_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_GRANITE_SHOVEL, Ingredient.ofItems( Items.GRANITE ), "better_tools_granite_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_IRON_SHOVEL, Ingredient.ofItems( Items.IRON_INGOT ), "better_tools_iron_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_NETHERRACK_SHOVEL, Ingredient.ofItems( Items.NETHERRACK ), "better_tools_netherrack_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_OBSIDIAN_SHOVEL, Ingredient.ofItems( Items.OBSIDIAN ), "better_tools_obsidian_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_QUARTZ_SHOVEL, Ingredient.ofItems( Items.QUARTZ ), "better_tools_quartz_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_RED_SANDSTONE_SHOVEL, Ingredient.ofItems( Items.RED_SANDSTONE ), "better_tools_red_sandstone_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_SANDSTONE_SHOVEL, Ingredient.ofItems( Items.SANDSTONE ), "better_tools_sandstone_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_STONE_SHOVEL, Ingredient.ofItems( Items.COBBLESTONE ), "better_tools_stone_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_TUFF_SHOVEL, Ingredient.ofItems( Items.TUFF ), "better_tools_tuff_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_WOODEN_SHOVEL, Ingredient.fromTag( ItemTags.PLANKS ), "better_tools_wooden_shovel_recipe" );
    }

    private void generateSwordRecipes(RecipeExporter exporter) {
        generateBetterToolsCraftingRecipe(exporter, BETTER_AMETHYST_SWORD, Ingredient.ofItems( Items.AMETHYST_SHARD ), "better_tools_amethyst_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_ANDESITE_SWORD, Ingredient.ofItems( Items.ANDESITE ), "better_tools_andesite_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_BASALT_SWORD, Ingredient.ofItems( Items.BASALT ), "better_tools_basalt_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_BLACKSTONE_SWORD, Ingredient.ofItems( Items.BLACKSTONE ), "better_tools_blackstone_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_CALCITE_SWORD, Ingredient.ofItems( Items.CALCITE ), "better_tools_calcite_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_COPPER_SWORD, Ingredient.ofItems( Items.COPPER_INGOT ), "better_tools_copper_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_CRYING_OBSIDIAN_SWORD, Ingredient.ofItems( Items.CRYING_OBSIDIAN ), "better_tools_crying_obsidian_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DEEPSLATE_SWORD, Ingredient.ofItems( Items.DEEPSLATE ), "better_tools_deepslate_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DIAMOND_SWORD, Ingredient.ofItems( Items.DIAMOND ), "better_tools_diamond_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DIORITE_SWORD, Ingredient.ofItems( Items.DIORITE ), "better_tools_diorite_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_EMERALD_SWORD, Ingredient.ofItems( Items.EMERALD ), "better_tools_emerald_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_END_STONE_SWORD, Ingredient.ofItems( Items.END_STONE ), "better_tools_endstone_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_FLINT_SWORD, Ingredient.ofItems( Items.FLINT ), "better_tools_flint_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_GOLD_SWORD, Ingredient.ofItems( Items.GOLD_INGOT ), "better_tools_gold_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_GRANITE_SWORD, Ingredient.ofItems( Items.GRANITE ), "better_tools_granite_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_IRON_SWORD, Ingredient.ofItems( Items.IRON_INGOT ), "better_tools_iron_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_NETHERRACK_SWORD, Ingredient.ofItems( Items.NETHERRACK ), "better_tools_netherrack_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_OBSIDIAN_SWORD, Ingredient.ofItems( Items.OBSIDIAN ), "better_tools_obsidian_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_QUARTZ_SWORD, Ingredient.ofItems( Items.QUARTZ ), "better_tools_quartz_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_RED_SANDSTONE_SWORD, Ingredient.ofItems( Items.RED_SANDSTONE ), "better_tools_red_sandstone_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_SANDSTONE_SWORD, Ingredient.ofItems( Items.SANDSTONE ), "better_tools_sandstone_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_STONE_SWORD, Ingredient.ofItems( Items.COBBLESTONE ), "better_tools_stone_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_TUFF_SWORD, Ingredient.ofItems( Items.TUFF ), "better_tools_tuff_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_WOODEN_SWORD, Ingredient.fromTag( ItemTags.PLANKS ), "better_tools_wooden_sword_recipe" );
    }
}
