package net.rockgiant.bettertools;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.rockgiant.bettertools.datagen.ModItemTagProvider;
import net.rockgiant.bettertools.datagen.ModLangProvider;
import net.rockgiant.bettertools.datagen.ModModelProvider;
import net.rockgiant.bettertools.datagen.ModRecipeProvider;

public class BetterToolsDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModModelProvider::new);
        pack.addProvider( ModRecipeProvider::new );
        pack.addProvider(ModItemTagProvider::new);
        pack.addProvider(ModLangProvider::new);
    }

}
