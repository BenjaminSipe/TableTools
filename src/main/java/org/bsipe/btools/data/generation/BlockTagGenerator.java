package org.bsipe.btools.data.generation;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

import static org.bsipe.btools.ModBlocks.DEEPSLATE_FORGE_BLOCK;
import static org.bsipe.btools.ModBlocks.ENDSTONE_ENDIRIUM_ORE;

public class BlockTagGenerator extends FabricTagProvider.BlockTagProvider {

    // letting this one be a minecraft one because this is really just a vanilla thing. . . .
    public static final TagKey<Block> NEEDS_NETHERITE_TOOL = TagKey.of( RegistryKeys.BLOCK, Identifier.of( "needs_netherite_tool" ) );

    public BlockTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }


    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE )
                .add( ENDSTONE_ENDIRIUM_ORE )
                .add( DEEPSLATE_FORGE_BLOCK );


        getOrCreateTagBuilder( NEEDS_NETHERITE_TOOL )
                .add( ENDSTONE_ENDIRIUM_ORE );

        getOrCreateTagBuilder( BlockTags.INCORRECT_FOR_DIAMOND_TOOL )
                .addTag( NEEDS_NETHERITE_TOOL );
    }
}
