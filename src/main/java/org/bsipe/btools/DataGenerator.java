package org.bsipe.btools;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.bsipe.btools.data.generation.ModelGenerator;
import org.bsipe.btools.data.generation.RecipeGenerator;
import org.bsipe.btools.data.generation.TagGenerator;
import org.bsipe.btools.data.generation.TranslationGenerator;

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
