package org.bsipe.btools;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import org.bsipe.btools.data.generation.*;
import org.bsipe.btools.data.worldgen.ConfiguredFeatureInit;
import org.bsipe.btools.data.worldgen.PlacedFeatureInit;

public class DataGenerator implements DataGeneratorEntrypoint {

	public static final String MOD_ID = "btools";

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ItemTagGenerator::new);
		pack.addProvider(TranslationGenerator::new);
		pack.addProvider(BlockTagGenerator::new);
		pack.addProvider(ModelGenerator::new);
		pack.addProvider(ModWorldGenerator::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ConfiguredFeatureInit::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, PlacedFeatureInit::bootstrap);
	}
}
