package org.bsipe.btools;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtString;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import static org.bsipe.btools.BetterToolsModInitializer.MOD_ID;

public class ModItems {


    // dummy item. ...
    public static Item WOODEN_AXE = register( new AxeItem( ToolMaterials.WOOD, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "wooden_axe" ) ).attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.WOOD, 6.0F, -3.2F))), "wooden_axe" );
    public static Item WOODEN_PICKAXE = register( new PickaxeItem( ToolMaterials.WOOD, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "wooden_pickaxe" ) ).attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.WOOD, 1.0F, -2.8F))), "wooden_pickaxe" );
    public static Item WOODEN_HOE = register( new HoeItem( ToolMaterials.WOOD, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "wooden_hoe" ) ).attributeModifiers(HoeItem.createAttributeModifiers(ToolMaterials.WOOD, 0.0F, -3.0F))), "wooden_hoe" );
    public static Item WOODEN_SHOVEL = register( new ShovelItem(ToolMaterials.WOOD, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "wooden_shovel" ) ).attributeModifiers(ShovelItem.createAttributeModifiers(ToolMaterials.WOOD, 1.5F, -3.0F))), "wooden_shovel" );
    public static Item WOODEN_SWORD = register( new SwordItem( ToolMaterials.WOOD, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "wooden_sword" ) ).attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 3, -2.4F))), "wooden_sword" );

    public static Item STONE_AXE = register( new AxeItem( ToolMaterials.STONE, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "stone_axe" ) ).attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.STONE, 7.0F, -3.2F))), "stone_axe" );
    public static Item STONE_PICKAXE = register( new PickaxeItem( ToolMaterials.STONE, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "stone_pickaxe" ) ).attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.STONE, 1.0F, -2.8F))), "stone_pickaxe" );
    public static Item STONE_HOE = register( new HoeItem( ToolMaterials.STONE, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "stone_hoe" ) ).attributeModifiers(HoeItem.createAttributeModifiers(ToolMaterials.STONE, -1.0F, -2.0F))), "stone_hoe" );
    public static Item STONE_SHOVEL = register( new ShovelItem(ToolMaterials.STONE, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "stone_shovel" ) ).attributeModifiers(ShovelItem.createAttributeModifiers(ToolMaterials.STONE, 1.5F, -3.0F))), "stone_shovel" );
    public static Item STONE_SWORD = register( new SwordItem( ToolMaterials.STONE, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "stone_sword" ) ).attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.STONE, 3, -2.4F))), "stone_sword" );

    public static Item GOLDEN_AXE = register( new AxeItem( ToolMaterials.GOLD, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "golden_axe" ) ).attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.GOLD, 6.0F, -3.0F))), "golden_axe" );
    public static Item GOLDEN_PICKAXE = register( new PickaxeItem( ToolMaterials.GOLD, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "golden_pickaxe" ) ).attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.GOLD, 1.0F, -2.8F))), "golden_pickaxe" );
    public static Item GOLDEN_HOE = register( new HoeItem( ToolMaterials.GOLD, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "golden_hoe" ) ).attributeModifiers(HoeItem.createAttributeModifiers(ToolMaterials.GOLD, 0.0F, -3.0F))), "golden_hoe" );
    public static Item GOLDEN_SHOVEL = register( new ShovelItem(ToolMaterials.GOLD, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "golden_shovel" ) ).attributeModifiers(ShovelItem.createAttributeModifiers(ToolMaterials.GOLD, 1.5F, -3.0F))), "golden_shovel" );
    public static Item GOLDEN_SWORD = register( new SwordItem( ToolMaterials.GOLD, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "golden_sword" ) ).attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.GOLD, 3, -2.4F))), "golden_sword" );

    public static Item IRON_AXE = register( new AxeItem( ToolMaterials.IRON, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "iron_axe" ) ).attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.IRON, 6.0F, -3.1F))), "iron_axe" );
    public static Item IRON_PICKAXE = register( new PickaxeItem( ToolMaterials.IRON, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "iron_pickaxe" ) ).attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.IRON, 1.0F, -2.8F))), "iron_pickaxe" );
    public static Item IRON_HOE = register( new HoeItem( ToolMaterials.IRON, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "iron_hoe" ) ).attributeModifiers(HoeItem.createAttributeModifiers(ToolMaterials.IRON, -2.0F, -1.0F))), "iron_hoe" );
    public static Item IRON_SHOVEL = register( new ShovelItem(ToolMaterials.IRON, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "iron_shovel" ) ).attributeModifiers(ShovelItem.createAttributeModifiers(ToolMaterials.IRON, 1.5F, -3.0F))), "iron_shovel" );
    public static Item IRON_SWORD = register( new SwordItem( ToolMaterials.IRON, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "iron_sword" ) ).attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.IRON, 3, -2.4F))), "iron_sword" );

    public static Item DIAMOND_AXE = register( new AxeItem( ToolMaterials.DIAMOND, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "diamond_axe" ) ).attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.DIAMOND, 5.0F, -3.0F))), "diamond_axe" );
    public static Item DIAMOND_PICKAXE = register( new PickaxeItem( ToolMaterials.DIAMOND, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "diamond_pickaxe" ) ).attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.DIAMOND, 1.0F, -2.8F))), "diamond_pickaxe" );
    public static Item DIAMOND_HOE = register( new HoeItem( ToolMaterials.DIAMOND, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "diamond_hoe" ) ).attributeModifiers(HoeItem.createAttributeModifiers(ToolMaterials.DIAMOND, -3.0F, 0.0F))), "diamond_hoe" );
    public static Item DIAMOND_SHOVEL = register( new ShovelItem(ToolMaterials.DIAMOND, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "diamond_shovel" ) ).attributeModifiers(ShovelItem.createAttributeModifiers(ToolMaterials.DIAMOND, 1.5F, -3.0F))), "diamond_shovel" );
    public static Item DIAMOND_SWORD = register( new SwordItem( ToolMaterials.DIAMOND, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "diamond_sword" ) ).attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.DIAMOND, 3, -2.4F))), "diamond_sword" );

    public static Item NETHERITE_AXE = register( new AxeItem( ToolMaterials.NETHERITE, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "netherite_axe" ) ).fireproof().attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.NETHERITE, 5.0F, -3.0F))), "netherite_axe" );
    public static Item NETHERITE_PICKAXE = register( new PickaxeItem( ToolMaterials.NETHERITE, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "netherite_pickaxe" ) ).fireproof().attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.NETHERITE, 1.0F, -2.8F))), "netherite_pickaxe" );
    public static Item NETHERITE_HOE = register( new HoeItem( ToolMaterials.NETHERITE, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "netherite_hoe" ) ).fireproof().attributeModifiers(HoeItem.createAttributeModifiers(ToolMaterials.NETHERITE, -4.0F, 0.0F))), "netherite_hoe" );
    public static Item NETHERITE_SHOVEL = register( new ShovelItem(ToolMaterials.NETHERITE, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "netherite_shovel" ) ).fireproof().attributeModifiers(ShovelItem.createAttributeModifiers(ToolMaterials.NETHERITE, 1.5F, -3.0F))), "netherite_shovel" );
    public static Item NETHERITE_SWORD = register( new SwordItem( ToolMaterials.NETHERITE, new Item.Settings().component(DataComponentTypes.CUSTOM_DATA, getDefaultSprite( "netherite_sword" ) ).fireproof().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 3, -2.4F))), "netherite_sword" );



    public static Item ACACIA_TOOL_HANDLE = register( new Item( new Item.Settings()), "acacia_tool_handle");
    public static Item BAMBOO_TOOL_HANDLE = register( new Item( new Item.Settings()), "bamboo_tool_handle");
    public static Item BIRCH_TOOL_HANDLE = register( new Item( new Item.Settings()), "birch_tool_handle");
    public static Item CHERRY_TOOL_HANDLE = register( new Item( new Item.Settings()), "cherry_tool_handle");
    public static Item CRIMSON_TOOL_HANDLE = register( new Item( new Item.Settings()), "crimson_tool_handle");
    public static Item DARK_OAK_TOOL_HANDLE = register( new Item( new Item.Settings()), "dark_oak_tool_handle");
    public static Item JUNGLE_TOOL_HANDLE = register( new Item( new Item.Settings()), "jungle_tool_handle");
    public static Item MANGROVE_TOOL_HANDLE = register( new Item( new Item.Settings()), "mangrove_tool_handle");
    public static Item OAK_TOOL_HANDLE = register( new Item( new Item.Settings()), "oak_tool_handle");
    public static Item SPRUCE_TOOL_HANDLE = register( new Item( new Item.Settings()), "spruce_tool_handle");
    public static Item WARPED_TOOL_HANDLE = register( new Item( new Item.Settings()), "warped_tool_handle");

    public static final TagKey<Item> TOOL_HANDLES = TagKey.of( RegistryKeys.ITEM, Identifier.of( DataGenerator.MOD_ID, "tool_handles" ) );


    public static void initialize() {
        registerItemGroups();
        FuelRegistry.INSTANCE.add(TOOL_HANDLES, 15 * 20 );
        FuelRegistry.INSTANCE.add(WOODEN_AXE, 5 * 20 );
        FuelRegistry.INSTANCE.add(WOODEN_HOE, 5 * 20 );
        FuelRegistry.INSTANCE.add(WOODEN_PICKAXE, 5 * 20 );
        FuelRegistry.INSTANCE.add(WOODEN_SHOVEL, 5 * 20 );
        FuelRegistry.INSTANCE.add(WOODEN_SWORD, 5 * 20 );
    }

    public static Item register(Item item, String name )
    {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), item);
    }


    public static void registerItemGroups() {
        ItemGroupEvents.modifyEntriesEvent( ItemGroups.INGREDIENTS ).register( content -> {
            content.add( ACACIA_TOOL_HANDLE );
            content.add( BAMBOO_TOOL_HANDLE );
            content.add( BIRCH_TOOL_HANDLE );
            content.add( CHERRY_TOOL_HANDLE );
            content.add( CRIMSON_TOOL_HANDLE );
            content.add( DARK_OAK_TOOL_HANDLE );
            content.add( JUNGLE_TOOL_HANDLE );
            content.add( MANGROVE_TOOL_HANDLE );
            content.add( OAK_TOOL_HANDLE );
            content.add( SPRUCE_TOOL_HANDLE );
            content.add( WARPED_TOOL_HANDLE );
        });
    }

    private static NbtComponent getDefaultSprite( String name ) {
        NbtCompound compound = new NbtCompound();
        compound.put( "layer0", NbtString.of( "minecraft:item/" + name ) );

        return NbtComponent.of( compound );
    }
}
