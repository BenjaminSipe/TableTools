package net.rockgiant.bettertools.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.rockgiant.bettertools.item.ModItems;
import net.rockgiant.bettertools.item.tools.*;

import java.util.Optional;

import static net.rockgiant.bettertools.item.ModItems.*;
import static net.rockgiant.bettertools.util.ToolGenerationUtils.getMaterial;
import static net.rockgiant.bettertools.util.ToolGenerationUtils.getPlanks;

public class ModRecipeProvider extends FabricRecipeProvider {


    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generate(RecipeExporter exporter) {
        generateToolRodRecipes(exporter);
    }

    public void generateToolRodRecipes(RecipeExporter exporter ) {
        generateToolRodRecipe(exporter, ACACIA_TINTED_TOOL_ROD, Items.ACACIA_PLANKS );
        generateToolRodRecipe(exporter, SPRUCE_TINTED_TOOL_ROD, Items.SPRUCE_PLANKS );
        generateToolRodRecipe(exporter, OAK_TINTED_TOOL_ROD, Items.OAK_PLANKS );
        generateToolRodRecipe(exporter, BIRCH_TINTED_TOOL_ROD, Items.BIRCH_PLANKS );
        generateToolRodRecipe(exporter, JUNGLE_TINTED_TOOL_ROD, Items.JUNGLE_PLANKS );
        generateToolRodRecipe(exporter, MANGROVE_TINTED_TOOL_ROD, Items.MANGROVE_PLANKS );
        generateToolRodRecipe(exporter, CHERRY_TINTED_TOOL_ROD, Items.CHERRY_PLANKS );
        generateToolRodRecipe(exporter, DARK_OAK_TINTED_TOOL_ROD, Items.DARK_OAK_PLANKS );
        generateToolRodRecipe(exporter, BAMBOO_TINTED_TOOL_ROD, Items.BAMBOO_PLANKS );
        generateToolRodRecipe(exporter, CRIMSON_TINTED_TOOL_ROD, Items.CRIMSON_PLANKS );
        generateToolRodRecipe(exporter, WARPED_TINTED_TOOL_ROD, Items.WARPED_PLANKS );

        generateSmithingTableUpgradeRecipe(exporter, BETTER_NETHERITE_AXE, BETTER_DIAMOND_AXE, "better_netherite_axe_smithing_recipe");
        generateSmithingTableUpgradeRecipe(exporter, BETTER_NETHERITE_PICKAXE, BETTER_DIAMOND_PICKAXE, "better_netherite_pickaxe_smithing_recipe");
        generateSmithingTableUpgradeRecipe(exporter, BETTER_NETHERITE_SHOVEL, BETTER_DIAMOND_SHOVEL, "better_netherite_shovel_smithing_recipe");
        generateSmithingTableUpgradeRecipe(exporter, BETTER_NETHERITE_SWORD, BETTER_DIAMOND_SWORD, "better_netherite_sword_smithing_recipe");
        generateSmithingTableUpgradeRecipe(exporter, BETTER_NETHERITE_HOE, BETTER_DIAMOND_HOE, "better_netherite_hoe_smithing_recipe");
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
}
