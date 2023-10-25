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
