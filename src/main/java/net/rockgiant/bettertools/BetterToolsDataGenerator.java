package net.rockgiant.bettertools;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.rockgiant.bettertools.datagen.ModModelProvider;
import net.rockgiant.bettertools.datagen.ModRecipeProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.rockgiant.bettertools.BetterTools.MOD_ID;


public class BetterToolsDataGenerator implements DataGeneratorEntrypoint {

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModModelProvider::new);
        pack.addProvider( ModRecipeProvider::new );
    }

}
