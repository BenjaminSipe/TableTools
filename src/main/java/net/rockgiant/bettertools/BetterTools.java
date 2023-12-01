package net.rockgiant.bettertools;

import net.fabricmc.api.ModInitializer;

import net.rockgiant.bettertools.item.ModItemTags;
import net.rockgiant.bettertools.item.ModItems;

import net.rockgiant.bettertools.recipe.ModRecipes;
import net.rockgiant.bettertools.world.gen.ModWorldGeneration;

public class BetterTools implements ModInitializer {

	public static final String MOD_ID = "bettertools";

	@Override
	public void onInitialize() {
		ModItems.registerModItems();

		ModRecipes.registerRecipes();

		ModItemTags.registerTags();

		ModWorldGeneration.generateModWorldGen();

	}
}