package org.bsipe.btools;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static void initialize() {

    }

    public static final TagKey<Item> TOOL_HANDLES = TagKey.of( RegistryKeys.ITEM, Identifier.of( DataGenerator.MOD_ID, "tool_handles" ) );

    public static final TagKey<Item> TOOL_HEAD_INGREDIENTS = TagKey.of( RegistryKeys.ITEM, Identifier.of( DataGenerator.MOD_ID, "tool_materials" ) );

    public static final TagKey<Item> SOFT_METAL_INGREDIENTS = TagKey.of( RegistryKeys.ITEM, Identifier.of( DataGenerator.MOD_ID, "soft_metals" ) );

    public static final TagKey<Item> SOFT_GEMSTONE_INGREDIENTS = TagKey.of( RegistryKeys.ITEM, Identifier.of( DataGenerator.MOD_ID, "soft_gemstones" ) );
    public static final TagKey<Item> SOFT_STONE_INGREDIENTS = TagKey.of( RegistryKeys.ITEM, Identifier.of( DataGenerator.MOD_ID, "soft_stones" ) );
    public static final TagKey<Item> OBSIDIAN_INGREDIENTS = TagKey.of( RegistryKeys.ITEM, Identifier.of( DataGenerator.MOD_ID, "obsidian" ) );



}
