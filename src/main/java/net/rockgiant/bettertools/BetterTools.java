package net.rockgiant.bettertools;

import net.fabricmc.api.ModInitializer;

import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.rockgiant.bettertools.item.ModItems;

import net.rockgiant.bettertools.recipe.ModRecipes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BetterTools implements ModInitializer {

	public static final String MOD_ID = "bettertools";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() {
		ModItems.registerModItems();

		ModRecipes.registerRecipes();

//		Registry.register(Registries.RECIPE_SERIALIZER, BetterToolsToolRecipe.ID, BetterToolsToolRecipe.BETTER_TOOLS);
	}
}