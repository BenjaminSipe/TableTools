package org.bsipe.toolvariants.DataGeneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import org.bsipe.toolvariants.ToolVariantItems;

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
        itemModelGenerator.register( ToolVariantItems.ACACIA_TOOL_HANDLE, Models.HANDHELD );
        itemModelGenerator.register( ToolVariantItems.BAMBOO_TOOL_HANDLE, Models.HANDHELD );
        itemModelGenerator.register( ToolVariantItems.BIRCH_TOOL_HANDLE, Models.HANDHELD );
        itemModelGenerator.register( ToolVariantItems.CHERRY_TOOL_HANDLE, Models.HANDHELD );
        itemModelGenerator.register( ToolVariantItems.CRIMSON_TOOL_HANDLE, Models.HANDHELD );
        itemModelGenerator.register( ToolVariantItems.DARK_OAK_TOOL_HANDLE, Models.HANDHELD );
        itemModelGenerator.register( ToolVariantItems.JUNGLE_TOOL_HANDLE, Models.HANDHELD );
        itemModelGenerator.register( ToolVariantItems.MANGROVE_TOOL_HANDLE, Models.HANDHELD );
        itemModelGenerator.register( ToolVariantItems.OAK_TOOL_HANDLE, Models.HANDHELD );
        itemModelGenerator.register( ToolVariantItems.SPRUCE_TOOL_HANDLE, Models.HANDHELD );
        itemModelGenerator.register( ToolVariantItems.WARPED_TOOL_HANDLE, Models.HANDHELD );
    }


}


