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

    }
}
