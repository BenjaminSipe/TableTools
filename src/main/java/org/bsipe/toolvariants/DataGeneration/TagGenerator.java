package org.bsipe.toolvariants.DataGeneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import org.bsipe.toolvariants.ToolVariantItems;
import static org.bsipe.toolvariants.ToolVariantItems.TOOL_HANDLES;

import java.util.concurrent.CompletableFuture;

public class TagGenerator extends FabricTagProvider.ItemTagProvider {

    public TagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        getOrCreateTagBuilder(TOOL_HANDLES)
                .add(ToolVariantItems.ACACIA_TOOL_HANDLE)
                .add(ToolVariantItems.BAMBOO_TOOL_HANDLE)
                .add(ToolVariantItems.BIRCH_TOOL_HANDLE)
                .add(ToolVariantItems.CHERRY_TOOL_HANDLE)
                .add(ToolVariantItems.CRIMSON_TOOL_HANDLE)
                .add(ToolVariantItems.DARK_OAK_TOOL_HANDLE)
                .add(ToolVariantItems.JUNGLE_TOOL_HANDLE)
                .add(ToolVariantItems.MANGROVE_TOOL_HANDLE)
                .add(ToolVariantItems.OAK_TOOL_HANDLE)
                .add(ToolVariantItems.SPRUCE_TOOL_HANDLE)
                .add(ToolVariantItems.WARPED_TOOL_HANDLE);
    }
}
