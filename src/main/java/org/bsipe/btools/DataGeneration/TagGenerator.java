package org.bsipe.btools.DataGeneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import org.bsipe.btools.ModItems;
import static org.bsipe.btools.ModItems.TOOL_HANDLES;

import java.util.concurrent.CompletableFuture;

public class TagGenerator extends FabricTagProvider.ItemTagProvider {

    public TagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        getOrCreateTagBuilder(TOOL_HANDLES)
                .add(ModItems.ACACIA_TOOL_HANDLE)
                .add(ModItems.BAMBOO_TOOL_HANDLE)
                .add(ModItems.BIRCH_TOOL_HANDLE)
                .add(ModItems.CHERRY_TOOL_HANDLE)
                .add(ModItems.CRIMSON_TOOL_HANDLE)
                .add(ModItems.DARK_OAK_TOOL_HANDLE)
                .add(ModItems.JUNGLE_TOOL_HANDLE)
                .add(ModItems.MANGROVE_TOOL_HANDLE)
                .add(ModItems.OAK_TOOL_HANDLE)
                .add(ModItems.SPRUCE_TOOL_HANDLE)
                .add(ModItems.WARPED_TOOL_HANDLE);
    }
}
