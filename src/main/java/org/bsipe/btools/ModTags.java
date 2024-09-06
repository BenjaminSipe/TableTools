package org.bsipe.btools;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static void initialize() {}

    public static final TagKey<Item> TOOL_HANDLES = TagKey.of( RegistryKeys.ITEM, Identifier.of( DataGenerator.MOD_ID, "tool_handles" ) );
}
