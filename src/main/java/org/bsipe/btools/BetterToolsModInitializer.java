package org.bsipe.btools;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import org.bsipe.btools.block.entity.ForgeBlockEntity;
import org.bsipe.btools.data.worldgen.BiomeModificationInit;
import org.bsipe.btools.recipes.ModRecipeTypes;
import org.bsipe.btools.recipes.ModRecipes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BetterToolsModInitializer implements ModInitializer {


	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "btools";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() {
		ModRecipeTypes.initialize();
		ModRecipes.initialize();
		ModItems.initialize();
		ModBlocks.initialize();
		ModResources.initialize();
		ModBlockEntityTypes.initialize();
		ModScreenHandlerTypes.initialize();
		BiomeModificationInit.load();
		ModItemGroups.initialize();

		BiomeModifications.addFeature(
				BiomeSelectors.foundInOverworld(),
				GenerationStep.Feature.UNDERGROUND_STRUCTURES,
				RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of( "btools", "fossil_paladus" )));
	}
}