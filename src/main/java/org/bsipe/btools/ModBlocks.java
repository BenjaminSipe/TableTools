package org.bsipe.btools;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    private static Block registerBlock(Block block, String name ) {
        registerBlockItem(block, name);
        return Registry.register(Registries.BLOCK, Identifier.of( BetterToolsModInitializer.MOD_ID, name ), block );
    }

    private static void registerBlockItem(Block block, String name) {
        Registry.register( Registries.ITEM, Identifier.of( BetterToolsModInitializer.MOD_ID, name ),
                new BlockItem( block, new Item.Settings()));
    }

    public static void initialize()
    {

    }
}
