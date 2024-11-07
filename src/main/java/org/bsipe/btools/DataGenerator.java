package org.bsipe.btools;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.bsipe.btools.data.generation.BlockTagGenerator;
import org.bsipe.btools.data.generation.ItemTagGenerator;
import org.bsipe.btools.data.generation.ModelGenerator;
import org.bsipe.btools.data.generation.TranslationGenerator;

public class DataGenerator implements DataGeneratorEntrypoint {

	public static final String MOD_ID = "btools";

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ItemTagGenerator::new);
		pack.addProvider(TranslationGenerator::new);
		pack.addProvider(BlockTagGenerator::new);
		pack.addProvider(ModelGenerator::new);
	}
}
