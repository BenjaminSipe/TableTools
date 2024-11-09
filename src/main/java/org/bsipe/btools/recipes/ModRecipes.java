package org.bsipe.btools.recipes;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.bsipe.btools.BetterToolsModInitializer;

public class ModRecipes {

    public static void initialize() {
        Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of( BetterToolsModInitializer.MOD_ID, CraftingTableRecipe.Serializer.ID ),
                 CraftingTableRecipe.Serializer.INSTANCE );

        Registry.register( Registries.RECIPE_SERIALIZER, Identifier.of( BetterToolsModInitializer.MOD_ID, ToolHandleSpecialCraftingRecipe.ID ),
                ToolHandleSpecialCraftingRecipe.INSTANCE );

        Registry.register( Registries.RECIPE_SERIALIZER, Identifier.of( BetterToolsModInitializer.MOD_ID, ModSmithingTransformRecipe.Serializer.ID ),
                ModSmithingTransformRecipe.Serializer.INSTANCE );

        Registry.register( Registries.RECIPE_SERIALIZER, Identifier.of( BetterToolsModInitializer.MOD_ID, ForgeAlloyRecipe.Serializer.ID ),
                ForgeAlloyRecipe.Serializer.INSTANCE );

        Registry.register( Registries.RECIPE_SERIALIZER, Identifier.of( BetterToolsModInitializer.MOD_ID, ForgeInfusionRecipe.Serializer.ID ),
                ForgeInfusionRecipe.Serializer.INSTANCE );
    }
}
