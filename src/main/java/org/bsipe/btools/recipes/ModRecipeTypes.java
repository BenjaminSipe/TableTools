package org.bsipe.btools.recipes;

import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.bsipe.btools.BetterToolsModInitializer;

public class ModRecipeTypes {

    public static RecipeType<AbstractForgeRecipe> FORGE = Registry.register( Registries.RECIPE_TYPE, Identifier.of( BetterToolsModInitializer.MOD_ID, Type.ID ), Type.INSTANCE );

    public static void initialize() {}

    public static class Type implements RecipeType<AbstractForgeRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "forge";
    }
}
