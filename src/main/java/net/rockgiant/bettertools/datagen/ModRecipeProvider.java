package net.rockgiant.bettertools.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.ComplexRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.rockgiant.bettertools.BetterTools;
import net.rockgiant.bettertools.item.ModItemTags;
import net.rockgiant.bettertools.item.ModItems;
import net.rockgiant.bettertools.util.recipe.BetterSmithingRecipeJsonBuilder;
import net.rockgiant.bettertools.util.recipe.BetterToolsCraftingRecipeJsonBuilder;
import net.rockgiant.bettertools.util.recipe.BetterToolsRepairRecipe;

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

        generateEnchantingSmithingTemplateRecipes(exporter);


        // ADD special repair recipe...
        ComplexRecipeJsonBuilder.create(BetterToolsRepairRecipe.TOOLS_REPAIR_SERIALIZER).offerTo(exporter, new Identifier(BetterTools.MOD_ID, BetterToolsRepairRecipe.ID ));


    }

    private void generateEnchantingSmithingTemplateRecipes(RecipeExporter exporter) {
        // efficiency
        generateEnchantingSmithingTemplateRecipe(exporter, EFFICIENCY_SMITHING_TEMPLATE, Ingredient.fromTag( ModItemTags.BETTER_AXES ), Ingredient.ofItems(Items.DIAMOND), Enchantments.EFFICIENCY, "better_tools_efficiency_axe_smithing_recipe");
        generateEnchantingSmithingTemplateRecipe(exporter, EFFICIENCY_SMITHING_TEMPLATE, Ingredient.fromTag( ModItemTags.BETTER_HOES ), Ingredient.ofItems(Items.DIAMOND), Enchantments.EFFICIENCY, "better_tools_efficiency_hoe_smithing_recipe");
        generateEnchantingSmithingTemplateRecipe(exporter, EFFICIENCY_SMITHING_TEMPLATE, Ingredient.fromTag( ModItemTags.BETTER_SHOVELS ), Ingredient.ofItems(Items.DIAMOND), Enchantments.EFFICIENCY, "better_tools_efficiency_shovel_smithing_recipe");
        generateEnchantingSmithingTemplateRecipe(exporter, EFFICIENCY_SMITHING_TEMPLATE, Ingredient.fromTag( ModItemTags.BETTER_PICKAXES ), Ingredient.ofItems(Items.DIAMOND), Enchantments.EFFICIENCY, "better_tools_efficiency_pickaxe_smithing_recipe");

        // unbreaking
        generateEnchantingSmithingTemplateRecipe(exporter, UNBREAKING_SMITHING_TEMPLATE, Ingredient.fromTag( ModItemTags.BETTER_TOOLS ), Ingredient.ofItems(Items.DIAMOND), Enchantments.UNBREAKING, "better_tools_unbreaking_smithing_recipe");

        // fortune
        generateEnchantingSmithingTemplateRecipe(exporter, FORTUNE_SMITHING_TEMPLATE, Ingredient.fromTag( ModItemTags.BETTER_AXES ), Ingredient.ofItems(Items.DIAMOND), Enchantments.FORTUNE, "better_tools_fortune_axe_smithing_recipe");
        generateEnchantingSmithingTemplateRecipe(exporter, FORTUNE_SMITHING_TEMPLATE, Ingredient.fromTag( ModItemTags.BETTER_HOES ), Ingredient.ofItems(Items.DIAMOND), Enchantments.FORTUNE, "better_tools_fortune_hoe_smithing_recipe");
        generateEnchantingSmithingTemplateRecipe(exporter, FORTUNE_SMITHING_TEMPLATE, Ingredient.fromTag( ModItemTags.BETTER_SHOVELS ), Ingredient.ofItems(Items.DIAMOND), Enchantments.FORTUNE, "better_tools_fortune_shovel_smithing_recipe");
        generateEnchantingSmithingTemplateRecipe(exporter, FORTUNE_SMITHING_TEMPLATE, Ingredient.fromTag( ModItemTags.BETTER_PICKAXES ), Ingredient.ofItems(Items.DIAMOND), Enchantments.FORTUNE, "better_tools_fortune_pickaxe_smithing_recipe");

        //silktouch
        generateEnchantingSmithingTemplateRecipe(exporter, SILKTOUCH_SMITHING_TEMPLATE, Ingredient.fromTag( ModItemTags.BETTER_AXES ), Ingredient.ofItems(Items.DIAMOND), Enchantments.SILK_TOUCH, "better_tools_silktouch_axe_smithing_recipe");
        generateEnchantingSmithingTemplateRecipe(exporter, SILKTOUCH_SMITHING_TEMPLATE, Ingredient.fromTag( ModItemTags.BETTER_HOES ), Ingredient.ofItems(Items.DIAMOND), Enchantments.SILK_TOUCH, "better_tools_silktouch_hoe_smithing_recipe");
        generateEnchantingSmithingTemplateRecipe(exporter, SILKTOUCH_SMITHING_TEMPLATE, Ingredient.fromTag( ModItemTags.BETTER_SHOVELS ), Ingredient.ofItems(Items.DIAMOND), Enchantments.SILK_TOUCH, "better_tools_silktouch_shovel_smithing_recipe");
        generateEnchantingSmithingTemplateRecipe(exporter, SILKTOUCH_SMITHING_TEMPLATE, Ingredient.fromTag( ModItemTags.BETTER_PICKAXES ), Ingredient.ofItems(Items.DIAMOND), Enchantments.SILK_TOUCH, "better_tools_silktouch_pick_smithing_recipe");

        // mending
        generateEnchantingSmithingTemplateRecipe(exporter, MENDING_SMITHING_TEMPLATE, Ingredient.fromTag( ModItemTags.BETTER_TOOLS ), Ingredient.ofItems(Items.DIAMOND), Enchantments.MENDING, "better_tools_mending_smithing_recipe");

        // duplication recipes:
        generateSmithingTemplateDuplicationRecipe(exporter, EFFICIENCY_SMITHING_TEMPLATE);
        generateSmithingTemplateDuplicationRecipe(exporter, UNBREAKING_SMITHING_TEMPLATE);
        generateSmithingTemplateDuplicationRecipe(exporter, FORTUNE_SMITHING_TEMPLATE);
        generateSmithingTemplateDuplicationRecipe(exporter, SILKTOUCH_SMITHING_TEMPLATE);
        generateSmithingTemplateDuplicationRecipe(exporter, MENDING_SMITHING_TEMPLATE);

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
        ShapedRecipeJsonBuilder.create( RecipeCategory.TOOLS, item, 2 )
                .group("tool_rods").pattern("# ").pattern(" #").input('#', Ingredient.ofItems(ingredient))
                .criterion(FabricRecipeProvider.hasItem(ingredient), FabricRecipeProvider.conditionsFromItem(ingredient)).offerTo(exporter);
    }

    public void generateSmithingTemplateDuplicationRecipe(RecipeExporter exporter, Item item )
    {
        ShapedRecipeJsonBuilder.create( RecipeCategory.TOOLS, item, 2 )
                .group("smithing_recipes").pattern("LLL").pattern("L#L").pattern("LBL")
                .input( 'L', Ingredient.ofItems(Items.LAPIS_LAZULI))
                .input('#', Ingredient.ofItems( item ))
                .input( 'B', Ingredient.ofItems(BURNING_OBSIDIAN))
                .criterion(FabricRecipeProvider.hasItem(item), FabricRecipeProvider.conditionsFromItem(item)).offerTo(exporter);
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
                .offerTo(exporter, new Identifier( BetterTools.MOD_ID, recipeId));
    }

    public void generateBetterToolsCraftingRecipe(RecipeExporter exporter, Item result, Item ingredient, String recipeId ) {
        BetterToolsCraftingRecipeJsonBuilder.create(Ingredient.ofItems( ingredient ),result, FabricRecipeProvider.conditionsFromItem(ingredient)).offerTo(exporter,recipeId);
    }

    public void generateEnchantingSmithingTemplateRecipe(RecipeExporter exporter, Item template, Ingredient base, Ingredient addition, Enchantment enchantment, String recipeId)
    {
        BetterSmithingRecipeJsonBuilder.create(Ingredient.ofItems( template ),base, addition, enchantment, FabricRecipeProvider.conditionsFromItem(template)).offerTo(exporter,recipeId);

    }

    public void generateBetterToolsCraftingRecipe(RecipeExporter exporter, Item result, TagKey<Item> ingredient, String recipeId ) {
        BetterToolsCraftingRecipeJsonBuilder.create(Ingredient.fromTag( ingredient ),result, FabricRecipeProvider.conditionsFromTag(ingredient)).offerTo(exporter,recipeId);
    }

    public void generateBetterToolsNetheriteSmithingRecipes(RecipeExporter exporter) {
        generateSmithingTableUpgradeRecipe(exporter, BETTER_NETHERITE_AXE, BETTER_DIAMOND_AXE, "better_netherite_axe_smithing_recipe");
        generateSmithingTableUpgradeRecipe(exporter, BETTER_NETHERITE_PICKAXE, BETTER_DIAMOND_PICKAXE, "better_netherite_pickaxe_smithing_recipe");
        generateSmithingTableUpgradeRecipe(exporter, BETTER_NETHERITE_SHOVEL, BETTER_DIAMOND_SHOVEL, "better_netherite_shovel_smithing_recipe");
        generateSmithingTableUpgradeRecipe(exporter, BETTER_NETHERITE_SWORD, BETTER_DIAMOND_SWORD, "better_netherite_sword_smithing_recipe");
        generateSmithingTableUpgradeRecipe(exporter, BETTER_NETHERITE_HOE, BETTER_DIAMOND_HOE, "better_netherite_hoe_smithing_recipe");
    }

    private void generateAxeRecipes(RecipeExporter exporter) {
        generateBetterToolsCraftingRecipe(exporter, BETTER_AMETHYST_AXE, Items.AMETHYST_SHARD, "better_tools_amethyst_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_ANDESITE_AXE, Items.ANDESITE, "better_tools_andesite_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_BASALT_AXE, Items.BASALT, "better_tools_basalt_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_BLACKSTONE_AXE, Items.BLACKSTONE, "better_tools_blackstone_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_CALCITE_AXE, Items.CALCITE, "better_tools_calcite_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_COPPER_AXE, Items.COPPER_INGOT, "better_tools_copper_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_CRYING_OBSIDIAN_AXE, Items.CRYING_OBSIDIAN, "better_tools_crying_obsidian_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DEEPSLATE_AXE, Items.DEEPSLATE, "better_tools_deepslate_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DIAMOND_AXE, Items.DIAMOND, "better_tools_diamond_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DIORITE_AXE, Items.DIORITE, "better_tools_diorite_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_EMERALD_AXE, Items.EMERALD, "better_tools_emerald_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_END_STONE_AXE, Items.END_STONE, "better_tools_endstone_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_FLINT_AXE, Items.FLINT, "better_tools_flint_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_GOLD_AXE, Items.GOLD_INGOT, "better_tools_gold_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_GRANITE_AXE, Items.GRANITE, "better_tools_granite_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_IRON_AXE, Items.IRON_INGOT, "better_tools_iron_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_NETHERRACK_AXE, Items.NETHERRACK, "better_tools_netherrack_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_OBSIDIAN_AXE, Items.OBSIDIAN, "better_tools_obsidian_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_QUARTZ_AXE, Items.QUARTZ, "better_tools_quartz_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_RED_SANDSTONE_AXE, Items.RED_SANDSTONE, "better_tools_red_sandstone_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_SANDSTONE_AXE, Items.SANDSTONE, "better_tools_sandstone_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_STONE_AXE, Items.COBBLESTONE, "better_tools_stone_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_TUFF_AXE, Items.TUFF, "better_tools_tuff_axe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_WOODEN_AXE, ItemTags.PLANKS, "better_tools_wooden_axe_recipe" );
    }

    private void generateHoeRecipes(RecipeExporter exporter) {
        generateBetterToolsCraftingRecipe(exporter, BETTER_AMETHYST_HOE, Items.AMETHYST_SHARD, "better_tools_amethyst_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_ANDESITE_HOE, Items.ANDESITE, "better_tools_andesite_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_BASALT_HOE, Items.BASALT, "better_tools_basalt_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_BLACKSTONE_HOE, Items.BLACKSTONE, "better_tools_blackstone_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_CALCITE_HOE, Items.CALCITE, "better_tools_calcite_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_COPPER_HOE, Items.COPPER_INGOT, "better_tools_copper_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_CRYING_OBSIDIAN_HOE, Items.CRYING_OBSIDIAN, "better_tools_crying_obsidian_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DEEPSLATE_HOE, Items.DEEPSLATE, "better_tools_deepslate_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DIAMOND_HOE, Items.DIAMOND, "better_tools_diamond_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DIORITE_HOE, Items.DIORITE, "better_tools_diorite_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_EMERALD_HOE, Items.EMERALD, "better_tools_emerald_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_END_STONE_HOE, Items.END_STONE, "better_tools_endstone_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_FLINT_HOE, Items.FLINT, "better_tools_flint_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_GOLD_HOE, Items.GOLD_INGOT, "better_tools_gold_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_GRANITE_HOE, Items.GRANITE, "better_tools_granite_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_IRON_HOE, Items.IRON_INGOT, "better_tools_iron_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_NETHERRACK_HOE, Items.NETHERRACK, "better_tools_netherrack_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_OBSIDIAN_HOE, Items.OBSIDIAN, "better_tools_obsidian_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_QUARTZ_HOE, Items.QUARTZ, "better_tools_quartz_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_RED_SANDSTONE_HOE, Items.RED_SANDSTONE, "better_tools_red_sandstone_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_SANDSTONE_HOE, Items.SANDSTONE, "better_tools_sandstone_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_STONE_HOE, Items.COBBLESTONE, "better_tools_stone_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_TUFF_HOE, Items.TUFF, "better_tools_tuff_hoe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_WOODEN_HOE, ItemTags.PLANKS, "better_tools_wooden_hoe_recipe" );
    }

    private void generatePickaxeRecipes(RecipeExporter exporter) {
        generateBetterToolsCraftingRecipe(exporter, BETTER_AMETHYST_PICKAXE, Items.AMETHYST_SHARD, "better_tools_amethyst_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_ANDESITE_PICKAXE, Items.ANDESITE, "better_tools_andesite_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_BASALT_PICKAXE, Items.BASALT, "better_tools_basalt_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_BLACKSTONE_PICKAXE, Items.BLACKSTONE, "better_tools_blackstone_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_CALCITE_PICKAXE, Items.CALCITE, "better_tools_calcite_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_COPPER_PICKAXE, Items.COPPER_INGOT, "better_tools_copper_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_CRYING_OBSIDIAN_PICKAXE, Items.CRYING_OBSIDIAN, "better_tools_crying_obsidian_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DEEPSLATE_PICKAXE, Items.DEEPSLATE, "better_tools_deepslate_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DIAMOND_PICKAXE, Items.DIAMOND, "better_tools_diamond_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DIORITE_PICKAXE, Items.DIORITE, "better_tools_diorite_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_EMERALD_PICKAXE, Items.EMERALD, "better_tools_emerald_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_END_STONE_PICKAXE, Items.END_STONE, "better_tools_endstone_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_FLINT_PICKAXE, Items.FLINT, "better_tools_flint_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_GOLD_PICKAXE, Items.GOLD_INGOT, "better_tools_gold_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_GRANITE_PICKAXE, Items.GRANITE, "better_tools_granite_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_IRON_PICKAXE, Items.IRON_INGOT, "better_tools_iron_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_NETHERRACK_PICKAXE, Items.NETHERRACK, "better_tools_netherrack_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_OBSIDIAN_PICKAXE, Items.OBSIDIAN, "better_tools_obsidian_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_QUARTZ_PICKAXE, Items.QUARTZ, "better_tools_quartz_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_RED_SANDSTONE_PICKAXE, Items.RED_SANDSTONE, "better_tools_red_sandstone_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_SANDSTONE_PICKAXE, Items.SANDSTONE, "better_tools_sandstone_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_STONE_PICKAXE, Items.COBBLESTONE, "better_tools_stone_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_TUFF_PICKAXE, Items.TUFF, "better_tools_tuff_pickaxe_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_WOODEN_PICKAXE, ItemTags.PLANKS, "better_tools_wooden_pickaxe_recipe" );
    }

    private void generateShovelRecipes(RecipeExporter exporter) {
        generateBetterToolsCraftingRecipe(exporter, BETTER_AMETHYST_SHOVEL, Items.AMETHYST_SHARD, "better_tools_amethyst_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_ANDESITE_SHOVEL, Items.ANDESITE, "better_tools_andesite_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_BASALT_SHOVEL, Items.BASALT, "better_tools_basalt_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_BLACKSTONE_SHOVEL, Items.BLACKSTONE, "better_tools_blackstone_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_CALCITE_SHOVEL, Items.CALCITE, "better_tools_calcite_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_COPPER_SHOVEL, Items.COPPER_INGOT, "better_tools_copper_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_CRYING_OBSIDIAN_SHOVEL, Items.CRYING_OBSIDIAN, "better_tools_crying_obsidian_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DEEPSLATE_SHOVEL, Items.DEEPSLATE, "better_tools_deepslate_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DIAMOND_SHOVEL, Items.DIAMOND, "better_tools_diamond_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DIORITE_SHOVEL, Items.DIORITE, "better_tools_diorite_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_EMERALD_SHOVEL, Items.EMERALD, "better_tools_emerald_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_END_STONE_SHOVEL, Items.END_STONE, "better_tools_endstone_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_FLINT_SHOVEL, Items.FLINT, "better_tools_flint_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_GOLD_SHOVEL, Items.GOLD_INGOT, "better_tools_gold_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_GRANITE_SHOVEL, Items.GRANITE, "better_tools_granite_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_IRON_SHOVEL, Items.IRON_INGOT, "better_tools_iron_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_NETHERRACK_SHOVEL, Items.NETHERRACK, "better_tools_netherrack_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_OBSIDIAN_SHOVEL, Items.OBSIDIAN, "better_tools_obsidian_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_QUARTZ_SHOVEL, Items.QUARTZ, "better_tools_quartz_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_RED_SANDSTONE_SHOVEL, Items.RED_SANDSTONE, "better_tools_red_sandstone_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_SANDSTONE_SHOVEL, Items.SANDSTONE, "better_tools_sandstone_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_STONE_SHOVEL, Items.COBBLESTONE, "better_tools_stone_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_TUFF_SHOVEL, Items.TUFF, "better_tools_tuff_shovel_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_WOODEN_SHOVEL, ItemTags.PLANKS, "better_tools_wooden_shovel_recipe" );
    }

    private void generateSwordRecipes(RecipeExporter exporter) {
        generateBetterToolsCraftingRecipe(exporter, BETTER_AMETHYST_SWORD, Items.AMETHYST_SHARD, "better_tools_amethyst_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_ANDESITE_SWORD, Items.ANDESITE, "better_tools_andesite_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_BASALT_SWORD, Items.BASALT, "better_tools_basalt_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_BLACKSTONE_SWORD, Items.BLACKSTONE, "better_tools_blackstone_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_CALCITE_SWORD, Items.CALCITE, "better_tools_calcite_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_COPPER_SWORD, Items.COPPER_INGOT, "better_tools_copper_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_CRYING_OBSIDIAN_SWORD, Items.CRYING_OBSIDIAN, "better_tools_crying_obsidian_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DEEPSLATE_SWORD, Items.DEEPSLATE, "better_tools_deepslate_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DIAMOND_SWORD, Items.DIAMOND, "better_tools_diamond_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_DIORITE_SWORD, Items.DIORITE, "better_tools_diorite_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_EMERALD_SWORD, Items.EMERALD, "better_tools_emerald_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_END_STONE_SWORD, Items.END_STONE, "better_tools_endstone_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_FLINT_SWORD, Items.FLINT, "better_tools_flint_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_GOLD_SWORD, Items.GOLD_INGOT, "better_tools_gold_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_GRANITE_SWORD, Items.GRANITE, "better_tools_granite_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_IRON_SWORD, Items.IRON_INGOT, "better_tools_iron_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_NETHERRACK_SWORD, Items.NETHERRACK, "better_tools_netherrack_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_OBSIDIAN_SWORD, Items.OBSIDIAN, "better_tools_obsidian_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_QUARTZ_SWORD, Items.QUARTZ, "better_tools_quartz_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_RED_SANDSTONE_SWORD, Items.RED_SANDSTONE, "better_tools_red_sandstone_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_SANDSTONE_SWORD, Items.SANDSTONE, "better_tools_sandstone_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_STONE_SWORD, Items.COBBLESTONE, "better_tools_stone_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_TUFF_SWORD, Items.TUFF, "better_tools_tuff_sword_recipe" );
        generateBetterToolsCraftingRecipe(exporter, BETTER_WOODEN_SWORD, ItemTags.PLANKS, "better_tools_wooden_sword_recipe" );
    }
}
