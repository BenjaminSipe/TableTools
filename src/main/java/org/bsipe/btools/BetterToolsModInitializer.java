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
		ModComponents.initialize();
		ModRegistries.initialize();
	}
}