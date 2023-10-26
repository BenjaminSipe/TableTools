package net.rockgiant.bettertools.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;
import net.rockgiant.bettertools.BetterTools;

import static net.rockgiant.bettertools.item.ModItems.*;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        generateToolRodModels(itemModelGenerator);

        generateWoodenToolModels(itemModelGenerator);
        generateStoneToolModels(itemModelGenerator);
        generateGoldToolModels(itemModelGenerator);
        generateIronToolModels(itemModelGenerator);
        generateDiamondToolModels(itemModelGenerator);
        generateNetheriteToolModels(itemModelGenerator);

        generateAmethystToolModels(itemModelGenerator);
        generateQuartzToolModels(itemModelGenerator);
        generateEmeraldToolModels(itemModelGenerator);

        generateNetherrackToolModels(itemModelGenerator);
        generateSandstoneToolModels(itemModelGenerator);
        generateRedSandstoneToolModels(itemModelGenerator);

        generateGraniteToolModels(itemModelGenerator);
        generateAndesiteToolModels(itemModelGenerator);
        generateDioriteToolModels(itemModelGenerator);
        generateTuffToolModels(itemModelGenerator);
        generateCalciteToolModels(itemModelGenerator);
        generateBlackstoneToolModels(itemModelGenerator);

        generateObsidianToolModels(itemModelGenerator);
        generateFlintToolModels(itemModelGenerator);
        generateCryingObsidianToolModels(itemModelGenerator);

        generateDeepslateToolModels(itemModelGenerator);
        generateEndStoneToolModels(itemModelGenerator);
        generateBasaltToolModels(itemModelGenerator);

        generateCopperToolModels(itemModelGenerator);
    }

    private void generateCopperToolModels(ItemModelGenerator img) {
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_COPPER_AXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/copper/copper_axe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_COPPER_HOE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/copper/copper_hoe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_COPPER_PICKAXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/copper/copper_pickaxe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_COPPER_SHOVEL), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_shovel_handle"),new Identifier(BetterTools.MOD_ID, "item/copper/copper_shovel_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_COPPER_SWORD), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_sword_handle"),new Identifier(BetterTools.MOD_ID, "item/copper/copper_sword_head")), img.writer);
    }

    private void generateDeepslateToolModels(ItemModelGenerator img) {
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_DEEPSLATE_AXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/deepslate/deepslate_axe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_DEEPSLATE_HOE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/deepslate/deepslate_hoe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_DEEPSLATE_PICKAXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/deepslate/deepslate_pickaxe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_DEEPSLATE_SHOVEL), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_shovel_handle"),new Identifier(BetterTools.MOD_ID, "item/deepslate/deepslate_shovel_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_DEEPSLATE_SWORD), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_sword_handle"),new Identifier(BetterTools.MOD_ID, "item/deepslate/deepslate_sword_head")), img.writer);
    }

    private void generateEndStoneToolModels(ItemModelGenerator img) {
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_END_STONE_AXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/endstone/endstone_axe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_END_STONE_HOE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/endstone/endstone_hoe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_END_STONE_PICKAXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/endstone/endstone_pickaxe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_END_STONE_SHOVEL), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_shovel_handle"),new Identifier(BetterTools.MOD_ID, "item/endstone/endstone_shovel_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_END_STONE_SWORD), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_sword_handle"),new Identifier(BetterTools.MOD_ID, "item/endstone/endstone_sword_head")), img.writer);
    }

    private void generateBasaltToolModels(ItemModelGenerator img) {
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_BASALT_AXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/basalt/basalt_axe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_BASALT_HOE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/basalt/basalt_hoe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_BASALT_PICKAXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/basalt/basalt_pickaxe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_BASALT_SHOVEL), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_shovel_handle"),new Identifier(BetterTools.MOD_ID, "item/basalt/basalt_shovel_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_BASALT_SWORD), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_sword_handle"),new Identifier(BetterTools.MOD_ID, "item/basalt/basalt_sword_head")), img.writer);
    }

    private void generateObsidianToolModels(ItemModelGenerator img) {
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_OBSIDIAN_AXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/obsidian/obsidian_axe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_OBSIDIAN_HOE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/obsidian/obsidian_hoe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_OBSIDIAN_PICKAXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/obsidian/obsidian_pickaxe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_OBSIDIAN_SHOVEL), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_shovel_handle"),new Identifier(BetterTools.MOD_ID, "item/obsidian/obsidian_shovel_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_OBSIDIAN_SWORD), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_sword_handle"),new Identifier(BetterTools.MOD_ID, "item/obsidian/obsidian_sword_head")), img.writer);
    }

    private void generateFlintToolModels(ItemModelGenerator img) {
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_FLINT_AXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/flint/flint_axe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_FLINT_HOE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/flint/flint_hoe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_FLINT_PICKAXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/flint/flint_pickaxe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_FLINT_SHOVEL), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_shovel_handle"),new Identifier(BetterTools.MOD_ID, "item/flint/flint_shovel_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_FLINT_SWORD), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_sword_handle"),new Identifier(BetterTools.MOD_ID, "item/flint/flint_sword_head")), img.writer);
    }

    private void generateCryingObsidianToolModels(ItemModelGenerator img) {
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_CRYING_OBSIDIAN_AXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/crying_obsidian/crying_obsidian_axe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_CRYING_OBSIDIAN_HOE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/crying_obsidian/crying_obsidian_hoe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_CRYING_OBSIDIAN_PICKAXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/crying_obsidian/crying_obsidian_pickaxe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_CRYING_OBSIDIAN_SHOVEL), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_shovel_handle"),new Identifier(BetterTools.MOD_ID, "item/crying_obsidian/crying_obsidian_shovel_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_CRYING_OBSIDIAN_SWORD), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_sword_handle"),new Identifier(BetterTools.MOD_ID, "item/crying_obsidian/crying_obsidian_sword_head")), img.writer);
    }

    private void generateGraniteToolModels(ItemModelGenerator img) {
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_GRANITE_AXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/granite/granite_axe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_GRANITE_HOE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/granite/granite_hoe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_GRANITE_PICKAXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/granite/granite_pickaxe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_GRANITE_SHOVEL), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_shovel_handle"),new Identifier(BetterTools.MOD_ID, "item/granite/granite_shovel_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_GRANITE_SWORD), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_sword_handle"),new Identifier(BetterTools.MOD_ID, "item/granite/granite_sword_head")), img.writer);
    }

    private void generateAndesiteToolModels(ItemModelGenerator img) {
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_ANDESITE_AXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/andesite/andesite_axe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_ANDESITE_HOE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/andesite/andesite_hoe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_ANDESITE_PICKAXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/andesite/andesite_pickaxe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_ANDESITE_SHOVEL), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_shovel_handle"),new Identifier(BetterTools.MOD_ID, "item/andesite/andesite_shovel_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_ANDESITE_SWORD), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_sword_handle"),new Identifier(BetterTools.MOD_ID, "item/andesite/andesite_sword_head")), img.writer);
    }

    private void generateDioriteToolModels(ItemModelGenerator img) {
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_DIORITE_AXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/diorite/diorite_axe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_DIORITE_HOE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/diorite/diorite_hoe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_DIORITE_PICKAXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/diorite/diorite_pickaxe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_DIORITE_SHOVEL), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_shovel_handle"),new Identifier(BetterTools.MOD_ID, "item/diorite/diorite_shovel_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_DIORITE_SWORD), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_sword_handle"),new Identifier(BetterTools.MOD_ID, "item/diorite/diorite_sword_head")), img.writer);
    }

    private void generateCalciteToolModels(ItemModelGenerator img) {
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_CALCITE_AXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/calcite/calcite_axe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_CALCITE_HOE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/calcite/calcite_hoe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_CALCITE_PICKAXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/calcite/calcite_pickaxe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_CALCITE_SHOVEL), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_shovel_handle"),new Identifier(BetterTools.MOD_ID, "item/calcite/calcite_shovel_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_CALCITE_SWORD), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_sword_handle"),new Identifier(BetterTools.MOD_ID, "item/calcite/calcite_sword_head")), img.writer);
    }

    private void generateTuffToolModels(ItemModelGenerator img) {
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_TUFF_AXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/tuff/tuff_axe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_TUFF_HOE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/tuff/tuff_hoe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_TUFF_PICKAXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/tuff/tuff_pickaxe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_TUFF_SHOVEL), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_shovel_handle"),new Identifier(BetterTools.MOD_ID, "item/tuff/tuff_shovel_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_TUFF_SWORD), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_sword_handle"),new Identifier(BetterTools.MOD_ID, "item/tuff/tuff_sword_head")), img.writer);
    }

    private void generateBlackstoneToolModels(ItemModelGenerator img) {
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_BLACKSTONE_AXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/blackstone/blackstone_axe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_BLACKSTONE_HOE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/blackstone/blackstone_hoe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_BLACKSTONE_PICKAXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/blackstone/blackstone_pickaxe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_BLACKSTONE_SHOVEL), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_shovel_handle"),new Identifier(BetterTools.MOD_ID, "item/blackstone/blackstone_shovel_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_BLACKSTONE_SWORD), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_sword_handle"),new Identifier(BetterTools.MOD_ID, "item/blackstone/blackstone_sword_head")), img.writer);
    }

    private void generateNetherrackToolModels(ItemModelGenerator img) {
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_NETHERRACK_AXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/netherrack/netherrack_axe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_NETHERRACK_HOE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/netherrack/netherrack_hoe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_NETHERRACK_PICKAXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/netherrack/netherrack_pickaxe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_NETHERRACK_SHOVEL), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_shovel_handle"),new Identifier(BetterTools.MOD_ID, "item/netherrack/netherrack_shovel_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_NETHERRACK_SWORD), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_sword_handle"),new Identifier(BetterTools.MOD_ID, "item/netherrack/netherrack_sword_head")), img.writer);
    }

    private void generateSandstoneToolModels(ItemModelGenerator img) {
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_SANDSTONE_AXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/sandstone/sandstone_axe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_SANDSTONE_HOE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/sandstone/sandstone_hoe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_SANDSTONE_PICKAXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/sandstone/sandstone_pickaxe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_SANDSTONE_SHOVEL), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_shovel_handle"),new Identifier(BetterTools.MOD_ID, "item/sandstone/sandstone_shovel_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_SANDSTONE_SWORD), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_sword_handle"),new Identifier(BetterTools.MOD_ID, "item/sandstone/sandstone_sword_head")), img.writer);
    }

    private void generateRedSandstoneToolModels(ItemModelGenerator img) {
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_RED_SANDSTONE_AXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/red_sandstone/red_sandstone_axe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_RED_SANDSTONE_HOE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/red_sandstone/red_sandstone_hoe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_RED_SANDSTONE_PICKAXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/red_sandstone/red_sandstone_pickaxe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_RED_SANDSTONE_SHOVEL), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_shovel_handle"),new Identifier(BetterTools.MOD_ID, "item/red_sandstone/red_sandstone_shovel_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_RED_SANDSTONE_SWORD), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_sword_handle"),new Identifier(BetterTools.MOD_ID, "item/red_sandstone/red_sandstone_sword_head")), img.writer);
    }

    private void generateAmethystToolModels(ItemModelGenerator img) {
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_AMETHYST_AXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/amethyst/amethyst_axe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_AMETHYST_HOE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/amethyst/amethyst_hoe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_AMETHYST_PICKAXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/amethyst/amethyst_pickaxe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_AMETHYST_SHOVEL), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_shovel_handle"),new Identifier(BetterTools.MOD_ID, "item/amethyst/amethyst_shovel_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_AMETHYST_SWORD), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_sword_handle"),new Identifier(BetterTools.MOD_ID, "item/amethyst/amethyst_sword_head")), img.writer);
    }

    private void generateEmeraldToolModels(ItemModelGenerator img) {
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_EMERALD_AXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/emerald/emerald_axe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_EMERALD_HOE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/emerald/emerald_hoe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_EMERALD_PICKAXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/emerald/emerald_pickaxe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_EMERALD_SHOVEL), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_shovel_handle"),new Identifier(BetterTools.MOD_ID, "item/emerald/emerald_shovel_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_EMERALD_SWORD), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_sword_handle"),new Identifier(BetterTools.MOD_ID, "item/emerald/emerald_sword_head")), img.writer);
    }

    private void generateQuartzToolModels(ItemModelGenerator img) {
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_QUARTZ_AXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/quartz/quartz_axe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_QUARTZ_HOE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/quartz/quartz_hoe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_QUARTZ_PICKAXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/quartz/quartz_pickaxe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_QUARTZ_SHOVEL), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_shovel_handle"),new Identifier(BetterTools.MOD_ID, "item/quartz/quartz_shovel_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_QUARTZ_SWORD), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_sword_handle"),new Identifier(BetterTools.MOD_ID, "item/quartz/quartz_sword_head")), img.writer);
    }

    private void generateNetheriteToolModels(ItemModelGenerator img) {
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_NETHERITE_AXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/netherite/netherite_axe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_NETHERITE_HOE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/netherite/netherite_hoe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_NETHERITE_PICKAXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/netherite/netherite_pickaxe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_NETHERITE_SHOVEL), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_shovel_handle"),new Identifier(BetterTools.MOD_ID, "item/netherite/netherite_shovel_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_NETHERITE_SWORD), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_sword_handle"),new Identifier(BetterTools.MOD_ID, "item/netherite/netherite_sword_head")), img.writer);
    }

    private void generateDiamondToolModels(ItemModelGenerator img) {
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_DIAMOND_AXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/diamond/diamond_axe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_DIAMOND_HOE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/diamond/diamond_hoe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_DIAMOND_PICKAXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/diamond/diamond_pickaxe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_DIAMOND_SHOVEL), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_shovel_handle"),new Identifier(BetterTools.MOD_ID, "item/diamond/diamond_shovel_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_DIAMOND_SWORD), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_sword_handle"),new Identifier(BetterTools.MOD_ID, "item/diamond/diamond_sword_head")), img.writer);
    }

    private void generateIronToolModels(ItemModelGenerator img) {
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_IRON_AXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/iron/iron_axe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_IRON_HOE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/iron/iron_hoe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_IRON_PICKAXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/iron/iron_pickaxe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_IRON_SHOVEL), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_shovel_handle"),new Identifier(BetterTools.MOD_ID, "item/iron/iron_shovel_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_IRON_SWORD), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_sword_handle"),new Identifier(BetterTools.MOD_ID, "item/iron/iron_sword_head")), img.writer);
    }

    private void generateGoldToolModels(ItemModelGenerator img) {
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_GOLD_AXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/gold/gold_axe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_GOLD_HOE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/gold/gold_hoe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_GOLD_PICKAXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/gold/gold_pickaxe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_GOLD_SHOVEL), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_shovel_handle"),new Identifier(BetterTools.MOD_ID, "item/gold/gold_shovel_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_GOLD_SWORD), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_sword_handle"),new Identifier(BetterTools.MOD_ID, "item/gold/gold_sword_head")), img.writer);
    }

    private void generateStoneToolModels(ItemModelGenerator img) {
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_STONE_AXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/stone/stone_axe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_STONE_HOE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/stone/stone_hoe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_STONE_PICKAXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/stone/stone_pickaxe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_STONE_SHOVEL), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_shovel_handle"),new Identifier(BetterTools.MOD_ID, "item/stone/stone_shovel_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_STONE_SWORD), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_sword_handle"),new Identifier(BetterTools.MOD_ID, "item/stone/stone_sword_head")), img.writer);
    }

    private void generateWoodenToolModels(ItemModelGenerator img) {
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_WOODEN_AXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/iron/iron_axe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_WOODEN_HOE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/iron/iron_hoe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_WOODEN_PICKAXE), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod"),new Identifier(BetterTools.MOD_ID, "item/iron/iron_pickaxe_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_WOODEN_SHOVEL), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_shovel_handle"),new Identifier(BetterTools.MOD_ID, "item/iron/iron_shovel_head")), img.writer);
        Models.GENERATED_TWO_LAYERS.upload( ModelIds.getItemModelId(BETTER_WOODEN_SWORD), TextureMap.layered(new Identifier(BetterTools.MOD_ID, "item/tinted_sword_handle"),new Identifier(BetterTools.MOD_ID, "item/iron/iron_sword_head")), img.writer);
    }

    private void generateToolRodModels(ItemModelGenerator img) {
        Models.GENERATED.upload(ModelIds.getItemModelId(ACACIA_TINTED_TOOL_ROD), TextureMap.layer0(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod")), img.writer);
        Models.GENERATED.upload(ModelIds.getItemModelId(BIRCH_TINTED_TOOL_ROD), TextureMap.layer0(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod")), img.writer);
        Models.GENERATED.upload(ModelIds.getItemModelId(BAMBOO_TINTED_TOOL_ROD), TextureMap.layer0(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod")), img.writer);
        Models.GENERATED.upload(ModelIds.getItemModelId(CHERRY_TINTED_TOOL_ROD), TextureMap.layer0(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod")), img.writer);
        Models.GENERATED.upload(ModelIds.getItemModelId(CRIMSON_TINTED_TOOL_ROD), TextureMap.layer0(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod")), img.writer);
        Models.GENERATED.upload(ModelIds.getItemModelId(DARK_OAK_TINTED_TOOL_ROD), TextureMap.layer0(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod")), img.writer);
        Models.GENERATED.upload(ModelIds.getItemModelId(JUNGLE_TINTED_TOOL_ROD), TextureMap.layer0(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod")), img.writer);
        Models.GENERATED.upload(ModelIds.getItemModelId(MANGROVE_TINTED_TOOL_ROD), TextureMap.layer0(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod")), img.writer);
        Models.GENERATED.upload(ModelIds.getItemModelId(OAK_TINTED_TOOL_ROD), TextureMap.layer0(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod")), img.writer);
        Models.GENERATED.upload(ModelIds.getItemModelId(SPRUCE_TINTED_TOOL_ROD), TextureMap.layer0(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod")), img.writer);
        Models.GENERATED.upload(ModelIds.getItemModelId(WARPED_TINTED_TOOL_ROD), TextureMap.layer0(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod")), img.writer);
    }

}
