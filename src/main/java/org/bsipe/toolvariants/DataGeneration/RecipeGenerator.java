package org.bsipe.toolvariants.DataGeneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RawShapedRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import org.bsipe.toolvariants.ToolVariantItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.bsipe.toolvariants.BensToolVariantsDataGenerator.MOD_ID;

public class RecipeGenerator extends FabricRecipeProvider {
    public RecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {


        generateToolHandleRecipes(exporter);
    }

    public void generateToolHandleRecipes(RecipeExporter exporter) {

        generateToolHandleRecipe( "acacia_tool_handle", Items.ACACIA_PLANKS, ToolVariantItems.ACACIA_TOOL_HANDLE, exporter );
        generateToolHandleRecipe( "bamboo_tool_handle", Items.BAMBOO_PLANKS, ToolVariantItems.BAMBOO_TOOL_HANDLE, exporter );
        generateToolHandleRecipe( "birch_tool_handle", Items.BIRCH_PLANKS, ToolVariantItems.BIRCH_TOOL_HANDLE, exporter );
        generateToolHandleRecipe( "cherry_tool_handle", Items.CHERRY_PLANKS, ToolVariantItems.CHERRY_TOOL_HANDLE, exporter );
        generateToolHandleRecipe( "crimson_tool_handle", Items.CRIMSON_PLANKS, ToolVariantItems.CRIMSON_TOOL_HANDLE, exporter );
        generateToolHandleRecipe( "dark_oak_tool_handle", Items.DARK_OAK_PLANKS, ToolVariantItems.DARK_OAK_TOOL_HANDLE, exporter );
        generateToolHandleRecipe( "jungle_tool_handle", Items.JUNGLE_PLANKS, ToolVariantItems.JUNGLE_TOOL_HANDLE, exporter );
        generateToolHandleRecipe( "mangrove_tool_handle", Items.MANGROVE_PLANKS, ToolVariantItems.MANGROVE_TOOL_HANDLE, exporter );
        generateToolHandleRecipe( "oak_tool_handle", Items.OAK_PLANKS, ToolVariantItems.OAK_TOOL_HANDLE, exporter );
        generateToolHandleRecipe( "spruce_tool_handle", Items.SPRUCE_PLANKS, ToolVariantItems.SPRUCE_TOOL_HANDLE, exporter );
        generateToolHandleRecipe( "warped_tool_handle", Items.WARPED_PLANKS, ToolVariantItems.WARPED_TOOL_HANDLE, exporter );

    }

    private void generateToolHandleRecipe(String name, Item ingredient, Item result, RecipeExporter exporter) {
        ItemStack output = result.getDefaultStack();
        output.setCount( 2 );
        ShapedRecipe recipe = new ShapedRecipe( "tool_handles",
                CraftingRecipeCategory.MISC,
                RawShapedRecipe.create(
                        Map.of('X',Ingredient.ofItems( ingredient )),
                        List.of(" X", "X ")),
                output );

        exporter.accept( Identifier.of( MOD_ID, name ), recipe, null );

    }
}
