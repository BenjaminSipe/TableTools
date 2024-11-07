package org.bsipe.btools;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.bsipe.btools.items.*;

import static org.bsipe.btools.BetterToolsModInitializer.MOD_ID;

public class ModItems {
    public static Item AXE = register( new ModAxeItem( new Item.Settings()), "axe" );
    public static Item HOE = register( new ModHoeItem( new Item.Settings()), "hoe" );
    public static Item PICKAXE = register( new ModPickaxeItem( new Item.Settings()), "pickaxe" );
    public static Item SHOVEL = register( new ModShovelItem( new Item.Settings()), "shovel" );
    public static Item SWORD = register( new ModSwordItem( new Item.Settings()), "sword" );

    public static Item TOOL_HANDLE = register( new Item( new Item.Settings() ), "tool_handle" );

    public static Item ENDER_STEEL_INGOT = register( new Item( new Item.Settings().rarity( Rarity.UNCOMMON )), "ender_steel_ingot");
    public static Item BLAZE_STEEL_INGOT = register( new Item( new Item.Settings().rarity( Rarity.UNCOMMON )), "blaze_steel_ingot");
    public static Item SLIME_STEEL_INGOT = register( new Item( new Item.Settings().rarity( Rarity.UNCOMMON ) ), "slime_steel_ingot" );
    public static Item CRYSTALIZED_ENDIRIUM = register( new Item( new Item.Settings().rarity( Rarity.UNCOMMON ) ), "crystalized_endirium" );
    public static Item CRYING_STEEL_INGOT = register( new Item( new Item.Settings().rarity( Rarity.UNCOMMON ) ), "crying_steel_ingot" );
    public static Item BLOOD_DIAMOND = register( new Item( new Item.Settings().rarity( Rarity.RARE )), "blood_diamond" );

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register((itemGroup) -> {
                    itemGroup.addAfter(Items.DIAMOND, ModItems.BLOOD_DIAMOND );
                    itemGroup.addAfter(Items.AMETHYST_SHARD, ModItems.CRYSTALIZED_ENDIRIUM );
                    itemGroup.addAfter(Items.GOLD_INGOT, ModItems.CRYING_STEEL_INGOT );
                    itemGroup.addAfter(Items.GOLD_INGOT, ModItems.ENDER_STEEL_INGOT );
                    itemGroup.addAfter(Items.GOLD_INGOT, ModItems.BLAZE_STEEL_INGOT );
                    itemGroup.addAfter(Items.GOLD_INGOT, ModItems.SLIME_STEEL_INGOT );
                });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL)
                .register((itemGroup -> itemGroup.addAfter(Blocks.ANCIENT_DEBRIS.asItem(), ModBlocks.ENDSTONE_ENDIRIUM_ORE ) ) );

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL)
                .register((itemGroup -> itemGroup.addAfter(Blocks.BLAST_FURNACE.asItem(), ModBlocks.DEEPSLATE_FORGE_BLOCK ) ) );

    }

    public static Item register(Item item, String name )
    {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), item);
    }
}
