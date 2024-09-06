package org.bsipe.btools.data.generation;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

import static org.bsipe.btools.ModItems.*;
import static org.bsipe.btools.ModTags.*;

public class TagGenerator extends FabricTagProvider.ItemTagProvider {

    public TagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add( SWORD );
        getOrCreateTagBuilder(ItemTags.AXES)
                .add( AXE );
        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add( PICKAXE );
        getOrCreateTagBuilder(ItemTags.SHOVELS)
                .add( SHOVEL );
        getOrCreateTagBuilder(ItemTags.HOES)
                .add( HOE );

        getOrCreateTagBuilder(TOOL_HANDLES)
                .add( ACACIA_TOOL_HANDLE,
                        BAMBOO_TOOL_HANDLE,
                        BIRCH_TOOL_HANDLE,
                        CHERRY_TOOL_HANDLE,
                        CRIMSON_TOOL_HANDLE,
                        DARK_OAK_TOOL_HANDLE,
                        JUNGLE_TOOL_HANDLE,
                        MANGROVE_TOOL_HANDLE,
                        OAK_TOOL_HANDLE,
                        SPRUCE_TOOL_HANDLE,
                        WARPED_TOOL_HANDLE);

    }
}
