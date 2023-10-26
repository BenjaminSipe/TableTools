package net.rockgiant.bettertools.item;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.rockgiant.bettertools.BetterTools;

public class ModItemTags {

    public static final TagKey<Item> BETTER_TOOL_RODS = TagKey.of( RegistryKeys.ITEM, new Identifier(BetterTools.MOD_ID, "better_tool_rods"));
    public static final TagKey<Item> BETTER_SHOVELS = TagKey.of( RegistryKeys.ITEM, new Identifier(BetterTools.MOD_ID, "better_shovels"));
    public static final TagKey<Item> BETTER_HOES = TagKey.of( RegistryKeys.ITEM, new Identifier(BetterTools.MOD_ID, "better_hoes"));
    public static final TagKey<Item> BETTER_AXES = TagKey.of( RegistryKeys.ITEM, new Identifier(BetterTools.MOD_ID, "better_axes"));
    public static final TagKey<Item> BETTER_PICKAXES = TagKey.of( RegistryKeys.ITEM, new Identifier(BetterTools.MOD_ID, "better_pickaxes"));
    public static final TagKey<Item> BETTER_SWORDS = TagKey.of( RegistryKeys.ITEM, new Identifier(BetterTools.MOD_ID, "better_swords"));

    public static final TagKey<Item> BETTER_TOOLS = TagKey.of( RegistryKeys.ITEM, new Identifier(BetterTools.MOD_ID, "better_tools"));

    // repair materials
    public static final TagKey<Item> BETTER_STONE_VARIANTS = TagKey.of( RegistryKeys.ITEM, new Identifier(BetterTools.MOD_ID, "better_stone_variants"));
    public static final TagKey<Item> BETTER_SOFT_STONE_VARIANTS = TagKey.of( RegistryKeys.ITEM, new Identifier(BetterTools.MOD_ID, "better_soft_stone_variants"));
    public static final TagKey<Item> BETTER_HARD_STONE_VARIANTS = TagKey.of( RegistryKeys.ITEM, new Identifier(BetterTools.MOD_ID, "better_hard_stone_variants"));
    public static final TagKey<Item> BETTER_GEMSTONE_VARIANTS = TagKey.of( RegistryKeys.ITEM, new Identifier(BetterTools.MOD_ID, "better_gemstone_variants"));
    public static final TagKey<Item> BETTER_OBSIDIAN_VARIANTS = TagKey.of( RegistryKeys.ITEM, new Identifier(BetterTools.MOD_ID, "better_obsidian_variants"));

    public static void registerTags() {

    }
}
