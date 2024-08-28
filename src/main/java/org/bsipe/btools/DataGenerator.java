package org.bsipe.btools;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.bsipe.btools.DataGeneration.ModelGenerator;
import org.bsipe.btools.DataGeneration.RecipeGenerator;
import org.bsipe.btools.DataGeneration.TagGenerator;
import org.bsipe.btools.DataGeneration.TranslationGenerator;

public class DataGenerator implements DataGeneratorEntrypoint {

	public static final String MOD_ID = "btools";

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModelGenerator::new);
		pack.addProvider(TagGenerator::new);
		pack.addProvider(RecipeGenerator::new );
		pack.addProvider(TranslationGenerator::new);
	}
}
