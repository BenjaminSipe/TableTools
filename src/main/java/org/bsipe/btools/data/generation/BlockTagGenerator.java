package org.bsipe.btools.data.generation;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.bsipe.btools.BetterToolsModInitializer;
import org.bsipe.btools.ModBlocks;

import java.util.concurrent.CompletableFuture;

import static org.bsipe.btools.ModBlockTags.*;
import static org.bsipe.btools.ModBlocks.*;

public class BlockTagGenerator extends FabricTagProvider.BlockTagProvider {

    public BlockTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }


    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE )
                .add( ENDSTONE_ENDIRIUM_ORE )
                .add( Blocks.REINFORCED_DEEPSLATE )
                .add( DEEPSLATE_FORGE_BLOCK );

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE )
                .add( RED_SOUL_SAND );

        getOrCreateTagBuilder( NEEDS_NETHERITE_TOOL )
                .add( ENDSTONE_ENDIRIUM_ORE );

        getOrCreateTagBuilder( NEEDS_ENDIRIUM_TOOL )
                .add( Blocks.REINFORCED_DEEPSLATE )
                .add( PALADUS_ORE );

        getOrCreateTagBuilder( NEEDS_PALADUS_TOOL );




        getOrCreateTagBuilder( BlockTags.INCORRECT_FOR_DIAMOND_TOOL )
                .addTag( NEEDS_NETHERITE_TOOL );
        getOrCreateTagBuilder( INCORRECT_FOR_NETHERITE_TOOL )
                .addTag( NEEDS_PALADUS_TOOL );
        getOrCreateTagBuilder( INCORRECT_FOR_ENDIRIUM_TOOL )
                .addTag( NEEDS_PALADUS_TOOL );


    }
}
