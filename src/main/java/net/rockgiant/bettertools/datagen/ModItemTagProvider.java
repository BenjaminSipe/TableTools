package net.rockgiant.bettertools.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.rockgiant.bettertools.item.ModItemTags;
import net.rockgiant.bettertools.item.ModItems;

import java.util.concurrent.CompletableFuture;

import static net.rockgiant.bettertools.item.ModItems.*;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {


    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {

        getOrCreateTagBuilder(ModItemTags.BETTER_TOOL_RODS )
                .add( ACACIA_TINTED_TOOL_ROD )
                .add( BAMBOO_TINTED_TOOL_ROD )
                .add( BIRCH_TINTED_TOOL_ROD )
                .add( CHERRY_TINTED_TOOL_ROD )
                .add( CRIMSON_TINTED_TOOL_ROD )
                .add( DARK_OAK_TINTED_TOOL_ROD )
                .add( JUNGLE_TINTED_TOOL_ROD )
                .add( MANGROVE_TINTED_TOOL_ROD )
                .add( OAK_TINTED_TOOL_ROD )
                .add( SPRUCE_TINTED_TOOL_ROD )
                .add( WARPED_TINTED_TOOL_ROD );

        getOrCreateTagBuilder(ModItemTags.BETTER_AXES )
                .add(
                        BETTER_AMETHYST_AXE,
                        BETTER_ANDESITE_AXE,
                        BETTER_BASALT_AXE,
                        BETTER_BLACKSTONE_AXE,
                        BETTER_CALCITE_AXE,
                        BETTER_COPPER_AXE,
                        BETTER_CRYING_OBSIDIAN_AXE,
                        BETTER_DEEPSLATE_AXE,
                        BETTER_DIAMOND_AXE,
                        BETTER_DIORITE_AXE,
                        BETTER_EMERALD_AXE,
                        BETTER_END_STONE_AXE,
                        BETTER_FLINT_AXE,
                        BETTER_GOLD_AXE,
                        BETTER_GRANITE_AXE,
                        BETTER_IRON_AXE,
                        BETTER_NETHERITE_AXE,
                        BETTER_NETHERRACK_AXE,
                        BETTER_OBSIDIAN_AXE,
                        BETTER_QUARTZ_AXE,
                        BETTER_RED_SANDSTONE_AXE,
                        BETTER_SANDSTONE_AXE,
                        BETTER_STONE_AXE,
                        BETTER_TUFF_AXE,
                        BETTER_WOODEN_AXE
                );

        getOrCreateTagBuilder(ModItemTags.BETTER_HOES )
                .add(
                        BETTER_AMETHYST_HOE,
                        BETTER_ANDESITE_HOE,
                        BETTER_BASALT_HOE,
                        BETTER_BLACKSTONE_HOE,
                        BETTER_CALCITE_HOE,
                        BETTER_COPPER_HOE,
                        BETTER_CRYING_OBSIDIAN_HOE,
                        BETTER_DEEPSLATE_HOE,
                        BETTER_DIAMOND_HOE,
                        BETTER_DIORITE_HOE,
                        BETTER_EMERALD_HOE,
                        BETTER_END_STONE_HOE,
                        BETTER_FLINT_HOE,
                        BETTER_GOLD_HOE,
                        BETTER_GRANITE_HOE,
                        BETTER_IRON_HOE,
                        BETTER_NETHERITE_HOE,
                        BETTER_NETHERRACK_HOE,
                        BETTER_OBSIDIAN_HOE,
                        BETTER_QUARTZ_HOE,
                        BETTER_RED_SANDSTONE_HOE,
                        BETTER_SANDSTONE_HOE,
                        BETTER_STONE_HOE,
                        BETTER_TUFF_HOE,
                        BETTER_WOODEN_HOE
                );

        getOrCreateTagBuilder(ModItemTags.BETTER_PICKAXES )
                .add(
                        BETTER_AMETHYST_PICKAXE,
                        BETTER_ANDESITE_PICKAXE,
                        BETTER_BASALT_PICKAXE,
                        BETTER_BLACKSTONE_PICKAXE,
                        BETTER_CALCITE_PICKAXE,
                        BETTER_COPPER_PICKAXE,
                        BETTER_CRYING_OBSIDIAN_PICKAXE,
                        BETTER_DEEPSLATE_PICKAXE,
                        BETTER_DIAMOND_PICKAXE,
                        BETTER_DIORITE_PICKAXE,
                        BETTER_EMERALD_PICKAXE,
                        BETTER_END_STONE_PICKAXE,
                        BETTER_FLINT_PICKAXE,
                        BETTER_GOLD_PICKAXE,
                        BETTER_GRANITE_PICKAXE,
                        BETTER_IRON_PICKAXE,
                        BETTER_NETHERITE_PICKAXE,
                        BETTER_NETHERRACK_PICKAXE,
                        BETTER_OBSIDIAN_PICKAXE,
                        BETTER_QUARTZ_PICKAXE,
                        BETTER_RED_SANDSTONE_PICKAXE,
                        BETTER_SANDSTONE_PICKAXE,
                        BETTER_STONE_PICKAXE,
                        BETTER_TUFF_PICKAXE,
                        BETTER_WOODEN_PICKAXE
                );

        getOrCreateTagBuilder(ModItemTags.BETTER_SHOVELS )
                .add(
                        BETTER_AMETHYST_SHOVEL,
                        BETTER_ANDESITE_SHOVEL,
                        BETTER_BASALT_SHOVEL,
                        BETTER_BLACKSTONE_SHOVEL,
                        BETTER_CALCITE_SHOVEL,
                        BETTER_COPPER_SHOVEL,
                        BETTER_CRYING_OBSIDIAN_SHOVEL,
                        BETTER_DEEPSLATE_SHOVEL,
                        BETTER_DIAMOND_SHOVEL,
                        BETTER_DIORITE_SHOVEL,
                        BETTER_EMERALD_SHOVEL,
                        BETTER_END_STONE_SHOVEL,
                        BETTER_FLINT_SHOVEL,
                        BETTER_GRANITE_SHOVEL,
                        BETTER_GOLD_SHOVEL,
                        BETTER_IRON_SHOVEL,
                        BETTER_NETHERITE_SHOVEL,
                        BETTER_NETHERRACK_SHOVEL,
                        BETTER_OBSIDIAN_SHOVEL,
                        BETTER_QUARTZ_SHOVEL,
                        BETTER_RED_SANDSTONE_SHOVEL,
                        BETTER_SANDSTONE_SHOVEL,
                        BETTER_STONE_SHOVEL,
                        BETTER_TUFF_SHOVEL,
                        BETTER_WOODEN_SHOVEL
                );

        getOrCreateTagBuilder(ModItemTags.BETTER_SWORDS )
                .add(
                        BETTER_AMETHYST_SWORD,
                        BETTER_ANDESITE_SWORD,
                        BETTER_BASALT_SWORD,
                        BETTER_BLACKSTONE_SWORD,
                        BETTER_CALCITE_SWORD,
                        BETTER_COPPER_SWORD,
                        BETTER_CRYING_OBSIDIAN_SWORD,
                        BETTER_DEEPSLATE_SWORD,
                        BETTER_DIAMOND_SWORD,
                        BETTER_DIORITE_SWORD,
                        BETTER_EMERALD_SWORD,
                        BETTER_END_STONE_SWORD,
                        BETTER_FLINT_SWORD,
                        BETTER_GOLD_SWORD,
                        BETTER_GRANITE_SWORD,
                        BETTER_IRON_SWORD,
                        BETTER_NETHERITE_SWORD,
                        BETTER_NETHERRACK_SWORD,
                        BETTER_OBSIDIAN_SWORD,
                        BETTER_QUARTZ_SWORD,
                        BETTER_RED_SANDSTONE_SWORD,
                        BETTER_SANDSTONE_SWORD,
                        BETTER_STONE_SWORD,
                        BETTER_TUFF_SWORD,
                        BETTER_WOODEN_SWORD
                        );

                getOrCreateTagBuilder(ModItemTags.BETTER_TOOLS)
                        .forceAddTag( ModItemTags.BETTER_PICKAXES )
                        .forceAddTag( ModItemTags.BETTER_AXES )
                        .forceAddTag( ModItemTags.BETTER_SHOVELS )
                        .forceAddTag( ModItemTags.BETTER_SWORDS )
                        .forceAddTag( ModItemTags.BETTER_HOES );

        getOrCreateTagBuilder(ModItemTags.BETTER_GEMSTONE_VARIANTS).add(Items.AMETHYST_SHARD,Items.QUARTZ, Items.EMERALD);
        getOrCreateTagBuilder(ModItemTags.BETTER_SOFT_STONE_VARIANTS).add(Items.NETHERRACK, Items.SANDSTONE,Items.RED_SANDSTONE );
        getOrCreateTagBuilder(ModItemTags.BETTER_STONE_VARIANTS).add(Items.COBBLESTONE, Items.GRANITE,Items.ANDESITE,Items.DIORITE,Items.TUFF,Items.CALCITE,Items.BLACKSTONE);
        getOrCreateTagBuilder(ModItemTags.BETTER_HARD_STONE_VARIANTS).add(Items.COBBLED_DEEPSLATE,Items.END_STONE,Items.BASALT);
        getOrCreateTagBuilder(ModItemTags.BETTER_OBSIDIAN_VARIANTS).add(Items.OBSIDIAN,Items.CRYING_OBSIDIAN);

        getOrCreateTagBuilder(ModItemTags.ENCHANTMENT_SMITHING_TEMPLATES).add(EFFICIENCY_SMITHING_TEMPLATE);

    }
}
