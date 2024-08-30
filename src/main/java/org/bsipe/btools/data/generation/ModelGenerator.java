package org.bsipe.btools.data.generation;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import org.bsipe.btools.ModItems;

public class ModelGenerator extends FabricModelProvider {
    public ModelGenerator( FabricDataOutput generator ) {
        super(generator);
    }
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // Blap
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        registerToolHandles( itemModelGenerator );
    }

    private void registerToolHandles( ItemModelGenerator itemModelGenerator ) {
        itemModelGenerator.register( ModItems.ACACIA_TOOL_HANDLE, Models.HANDHELD );
        itemModelGenerator.register( ModItems.BAMBOO_TOOL_HANDLE, Models.HANDHELD );
        itemModelGenerator.register( ModItems.BIRCH_TOOL_HANDLE, Models.HANDHELD );
        itemModelGenerator.register( ModItems.CHERRY_TOOL_HANDLE, Models.HANDHELD );
        itemModelGenerator.register( ModItems.CRIMSON_TOOL_HANDLE, Models.HANDHELD );
        itemModelGenerator.register( ModItems.DARK_OAK_TOOL_HANDLE, Models.HANDHELD );
        itemModelGenerator.register( ModItems.JUNGLE_TOOL_HANDLE, Models.HANDHELD );
        itemModelGenerator.register( ModItems.MANGROVE_TOOL_HANDLE, Models.HANDHELD );
        itemModelGenerator.register( ModItems.OAK_TOOL_HANDLE, Models.HANDHELD );
        itemModelGenerator.register( ModItems.SPRUCE_TOOL_HANDLE, Models.HANDHELD );
        itemModelGenerator.register( ModItems.WARPED_TOOL_HANDLE, Models.HANDHELD );
    }


}


