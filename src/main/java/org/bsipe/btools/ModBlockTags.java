package org.bsipe.btools;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import static org.bsipe.btools.BetterToolsModInitializer.MOD_ID;

public class ModBlockTags {
    public static void initialize() {}

    public static final TagKey<Block> NEEDS_NETHERITE_TOOL = TagKey.of( RegistryKeys.BLOCK, Identifier.of( MOD_ID, "needs_netherite_tool" ) );
    public static final TagKey<Block> NEEDS_ENDIRIUM_TOOL = TagKey.of( RegistryKeys.BLOCK, Identifier.of( MOD_ID, "needs_endirium_tool" ) );
    public static final TagKey<Block> NEEDS_PALADUS_TOOL = TagKey.of( RegistryKeys.BLOCK, Identifier.of( MOD_ID, "needs_paladus_tool" ) );


    public static final TagKey<Block> INCORRECT_FOR_NETHERITE_TOOL = TagKey.of( RegistryKeys.BLOCK, Identifier.of( MOD_ID, "incorrect_for_netherite_tool" ) );
    public static final TagKey<Block> INCORRECT_FOR_ENDIRIUM_TOOL = TagKey.of( RegistryKeys.BLOCK, Identifier.of( MOD_ID, "incorrect_for_endirium_tool" ) );
    public static final TagKey<Block> INCORRECT_FOR_PALADUS_TOOL = TagKey.of( RegistryKeys.BLOCK, Identifier.of( MOD_ID, "incorrect_for_paladus_tool" ) );

}
