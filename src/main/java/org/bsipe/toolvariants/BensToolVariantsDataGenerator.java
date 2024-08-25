package org.bsipe.toolvariants;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.bsipe.toolvariants.DataGeneration.ModelGenerator;
import org.bsipe.toolvariants.DataGeneration.RecipeGenerator;
import org.bsipe.toolvariants.DataGeneration.TagGenerator;
import org.bsipe.toolvariants.DataGeneration.TranslationGenerator;

public class BensToolVariantsDataGenerator implements DataGeneratorEntrypoint {

	public static final String MOD_ID = "tool-variants";

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModelGenerator::new);
		pack.addProvider(TagGenerator::new);
		pack.addProvider(RecipeGenerator::new );
		pack.addProvider(TranslationGenerator::new);
	}
}
