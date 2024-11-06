package org.bsipe.btools;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
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
    public static Item BLOOD_DIAMOND = register( new Item( new Item.Settings().rarity( Rarity.RARE )), "blood_diamond" );

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register((itemGroup) -> {
                    itemGroup.add( ModItems.BLOOD_DIAMOND );
                    itemGroup.add( ModItems.ENDER_STEEL_INGOT );
                    itemGroup.add( ModItems.SLIME_STEEL_INGOT );
                    itemGroup.add( ModItems.BLAZE_STEEL_INGOT );
                    itemGroup.add( ModItems.CRYSTALIZED_ENDIRIUM );
                });

    }

    public static Item register(Item item, String name )
    {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), item);
    }
}
