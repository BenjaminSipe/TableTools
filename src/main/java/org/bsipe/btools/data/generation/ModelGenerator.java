package org.bsipe.btools.data.generation;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.Models;
import org.bsipe.btools.ModBlocks;
import org.bsipe.btools.ModItems;

public class ModelGenerator extends FabricModelProvider {
    public ModelGenerator(FabricDataOutput generator) {
        super(generator);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    }

    @Override
    public void generateItemModels(net.minecraft.data.client.ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.TOOL_HANDLE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PICKAXE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.SLIME_STEEL_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDER_STEEL_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLAZE_STEEL_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRYSTALIZED_ENDIRIUM, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLOOD_DIAMOND, Models.GENERATED);
    }
}
