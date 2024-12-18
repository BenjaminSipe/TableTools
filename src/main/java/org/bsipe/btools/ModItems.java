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

    public static Item ENDER_STEEL_INGOT = register( new Item( new Item.Settings()), "ender_steel_ingot");
    public static Item BLAZE_STEEL_INGOT = register( new Item( new Item.Settings()), "blaze_steel_ingot");
    public static Item SLIME_STEEL_INGOT = register( new Item( new Item.Settings()), "slime_steel_ingot" );
    public static Item CRYSTALIZED_ENDIRIUM = register( new Item( new Item.Settings() ), "crystalized_endirium" );
    public static Item CRYING_STEEL_INGOT = register( new Item( new Item.Settings() ), "crying_steel_ingot" );
    public static Item BLOOD_DIAMOND = register( new Item( new Item.Settings()), "blood_diamond" );
    public static Item PALADUS_INGOT = register( new Item( new Item.Settings()), "paladus_ingot" );
    public static Item RAW_PALADUS = register( new Item( new Item.Settings()), "raw_paladus" );
    public static Item HEART_OF_UNDYING = register( new Item( new Item.Settings()), "heart_of_undying" );
    public static Item UNDYING_INGOT = register( new Item( new Item.Settings()), "undying_ingot" );
    public static Item CRACKED_REINFORCEMENT = register( new Item( new Item.Settings()), "cracked_reinforcement" );
    public static Item REINFORCED_NETHERITE_INGOT = register( new Item( new Item.Settings()), "reinforced_netherite_ingot" );
    public static Item RED_SOUL_SHARD = register( new Item( new Item.Settings()), "red_soul_shard" );
    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register((itemGroup) -> {
                    itemGroup.addAfter(Items.GOLD_NUGGET, ModItems.HEART_OF_UNDYING );
                    itemGroup.addAfter(Items.DIAMOND, ModItems.BLOOD_DIAMOND );
                    itemGroup.addAfter(Items.AMETHYST_SHARD, ModItems.CRYSTALIZED_ENDIRIUM );
                    itemGroup.addAfter(Items.AMETHYST_SHARD, ModItems.RED_SOUL_SHARD );
                    itemGroup.addAfter(Items.GOLD_INGOT, ModItems.UNDYING_INGOT );
                    itemGroup.addAfter(Items.GOLD_INGOT, ModItems.PALADUS_INGOT );
                    itemGroup.addAfter(Items.GOLD_INGOT, ModItems.CRYING_STEEL_INGOT );
                    itemGroup.addAfter(Items.GOLD_INGOT, ModItems.ENDER_STEEL_INGOT );
                    itemGroup.addAfter(Items.GOLD_INGOT, ModItems.BLAZE_STEEL_INGOT );
                    itemGroup.addAfter(Items.GOLD_INGOT, ModItems.SLIME_STEEL_INGOT );
                    itemGroup.addAfter(Items.NETHERITE_SCRAP, ModItems.CRACKED_REINFORCEMENT);
                    itemGroup.addAfter(Items.NETHERITE_INGOT, ModItems.REINFORCED_NETHERITE_INGOT );
                    itemGroup.addAfter(Items.RAW_GOLD, ModItems.RAW_PALADUS );
                });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL)
                .register((itemGroup ->
                {
                    itemGroup.addAfter(Blocks.ANCIENT_DEBRIS.asItem(), ModBlocks.PALADUS_ORE );
                    itemGroup.addAfter(Blocks.ANCIENT_DEBRIS.asItem(), ModBlocks.ENDSTONE_ENDIRIUM_ORE );
                    itemGroup.addAfter( Blocks.SOUL_SAND.asItem(), ModBlocks.RED_SOUL_SAND );

                } ) );

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL)
                .register((itemGroup -> itemGroup.addAfter(Blocks.BLAST_FURNACE.asItem(), ModBlocks.DEEPSLATE_FORGE_BLOCK ) ) );

    }

    public static Item register(Item item, String name )
    {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), item);
    }
}
