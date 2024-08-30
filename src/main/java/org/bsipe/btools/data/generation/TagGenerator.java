package org.bsipe.btools.data.generation;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import org.bsipe.btools.data.ModToolHeadMaterial;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.registry.tag.ItemTags.STONE_TOOL_MATERIALS;
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

        getOrCreateTagBuilder(TOOL_HEAD_INGREDIENTS)
                .add(ModToolHeadMaterial.getCraftingIngredients());

        getOrCreateTagBuilder(STONE_TOOL_MATERIALS).add(
                Items.GRANITE,
                Items.DIORITE,
                Items.ANDESITE,
                Items.TUFF,
                Items.BASALT,
                Items.CALCITE,
                Items.END_STONE
        );

        getOrCreateTagBuilder(SOFT_METAL_INGREDIENTS).add( Items.GOLD_INGOT, Items.COPPER_INGOT );

        getOrCreateTagBuilder(SOFT_GEMSTONE_INGREDIENTS).add(
                Items.AMETHYST_SHARD,
                Items.EMERALD,
                Items.QUARTZ );
        getOrCreateTagBuilder(SOFT_STONE_INGREDIENTS).add(
                Items.NETHERRACK,
                Items.SANDSTONE,
                Items.RED_SANDSTONE,
                Items.FLINT
        );
        getOrCreateTagBuilder(OBSIDIAN_INGREDIENTS).add( Items.OBSIDIAN, Items.CRYING_OBSIDIAN);
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
