package org.bsipe.btools.DataGeneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import org.bsipe.btools.ModItems;

import java.util.concurrent.CompletableFuture;

import static org.bsipe.btools.ModItems.*;

public class TagGenerator extends FabricTagProvider.ItemTagProvider {

    public TagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add( WOODEN_SWORD, STONE_SWORD, IRON_SWORD, DIAMOND_SWORD, NETHERITE_SWORD, GOLDEN_SWORD );
        getOrCreateTagBuilder(ItemTags.AXES)
                .add( WOODEN_AXE, STONE_AXE, IRON_AXE, DIAMOND_AXE, NETHERITE_AXE, GOLDEN_AXE );
        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add( WOODEN_PICKAXE, STONE_PICKAXE, IRON_PICKAXE, DIAMOND_PICKAXE, NETHERITE_PICKAXE, GOLDEN_PICKAXE );
        getOrCreateTagBuilder(ItemTags.SHOVELS)
                .add( WOODEN_SHOVEL, STONE_SHOVEL, IRON_SHOVEL, DIAMOND_SHOVEL, NETHERITE_SHOVEL, GOLDEN_SHOVEL );
        getOrCreateTagBuilder(ItemTags.HOES)
                .add( WOODEN_HOE, STONE_HOE, IRON_HOE, DIAMOND_HOE, NETHERITE_HOE, GOLDEN_HOE );

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
