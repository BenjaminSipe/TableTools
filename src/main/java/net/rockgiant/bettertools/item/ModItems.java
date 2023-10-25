package net.rockgiant.bettertools.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.rockgiant.bettertools.BetterTools;
import net.rockgiant.bettertools.item.tools.*;
import net.rockgiant.bettertools.toolmaterials.BetterToolsMaterial;

import static net.rockgiant.bettertools.util.ToolGenerationUtils.getToolAttackDamage;
import static net.rockgiant.bettertools.util.ToolGenerationUtils.getToolAttackSpeed;

public class ModItems {
    public static final Item ACACIA_TINTED_TOOL_ROD = registerItem( "acacia_tinted_tool_rod", new TintedToolRodItem(0xC26D3F, new FabricItemSettings()));
    public static final Item BAMBOO_TINTED_TOOL_ROD = registerItem( "bamboo_tinted_tool_rod", new TintedToolRodItem(0xEFD97E, new FabricItemSettings()));
    public static final Item BIRCH_TINTED_TOOL_ROD = registerItem( "birch_tinted_tool_rod", new TintedToolRodItem(0xD7CB8D, new FabricItemSettings()));
    public static final Item CHERRY_TINTED_TOOL_ROD = registerItem( "cherry_tinted_tool_rod", new TintedToolRodItem(0xDD9D97, new FabricItemSettings()));
    public static final Item CRIMSON_TINTED_TOOL_ROD = registerItem( "crimson_tinted_tool_rod", new TintedToolRodItem(0xAA4072, new FabricItemSettings()));
    public static final Item DARK_OAK_TINTED_TOOL_ROD = registerItem( "dark_oak_tinted_tool_rod", new TintedToolRodItem(0x53381A, new FabricItemSettings()));
    public static final Item JUNGLE_TINTED_TOOL_ROD = registerItem( "jungle_tinted_tool_rod", new TintedToolRodItem(0xBF8E6B, new FabricItemSettings()));
    public static final Item MANGROVE_TINTED_TOOL_ROD = registerItem( "mangrove_tinted_tool_rod", new TintedToolRodItem(0x8C3539, new FabricItemSettings()));
    public static final Item OAK_TINTED_TOOL_ROD = registerItem( "oak_tinted_tool_rod", new TintedToolRodItem(0x8F7448, new FabricItemSettings()));
    public static final Item SPRUCE_TINTED_TOOL_ROD = registerItem( "spruce_tinted_tool_rod", new TintedToolRodItem(0x644A2A, new FabricItemSettings()));
    public static final Item WARPED_TINTED_TOOL_ROD = registerItem( "warped_tinted_tool_rod", new TintedToolRodItem(0x3A8E8C, new FabricItemSettings()));

    public static final Item BETTER_IRON_AXE = registerItem( "better_iron_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_IRON,getToolAttackDamage("axe", "iron"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_IRON_PICKAXE = registerItem( "better_iron_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_IRON,(int)getToolAttackDamage("pickaxe", "iron"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_IRON_HOE = registerItem( "better_iron_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_IRON,(int)getToolAttackDamage("hoe", "iron"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_IRON_SWORD = registerItem( "better_iron_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_IRON,(int)getToolAttackDamage("sword", "iron"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_IRON_SHOVEL = registerItem( "better_iron_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_IRON,getToolAttackDamage("shovel", "iron"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));

    public static final Item BETTER_WOODEN_AXE = registerItem( "better_wooden_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_WOOD,getToolAttackDamage("axe", "wood"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_WOODEN_PICKAXE = registerItem( "better_wooden_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_WOOD,(int)getToolAttackDamage("pickaxe", "wood"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_WOODEN_HOE = registerItem( "better_wooden_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_WOOD,(int)getToolAttackDamage("hoe", "wood"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_WOODEN_SWORD = registerItem( "better_wooden_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_WOOD,(int)getToolAttackDamage("sword", "wood"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_WOODEN_SHOVEL = registerItem( "better_wooden_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_WOOD,getToolAttackDamage("shovel", "wood"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));

    public static final Item BETTER_STONE_AXE = registerItem( "better_stone_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_STONE,getToolAttackDamage("axe", "stone"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_STONE_PICKAXE = registerItem( "better_stone_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_STONE,(int)getToolAttackDamage("pickaxe", "stone"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_STONE_HOE = registerItem( "better_stone_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_STONE,(int)getToolAttackDamage("hoe", "stone"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_STONE_SWORD = registerItem( "better_stone_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_STONE,(int)getToolAttackDamage("sword", "stone"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_STONE_SHOVEL = registerItem( "better_stone_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_STONE,getToolAttackDamage("shovel", "stone"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));

    public static final Item BETTER_GOLD_AXE = registerItem( "better_gold_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_GOLD,getToolAttackDamage("axe", "gold"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_GOLD_PICKAXE = registerItem( "better_gold_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_GOLD,(int)getToolAttackDamage("pickaxe", "gold"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_GOLD_HOE = registerItem( "better_gold_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_GOLD,(int)getToolAttackDamage("hoe", "gold"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_GOLD_SWORD = registerItem( "better_gold_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_GOLD,(int)getToolAttackDamage("sword", "gold"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_GOLD_SHOVEL = registerItem( "better_gold_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_GOLD,getToolAttackDamage("shovel", "gold"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));

    public static final Item BETTER_DIAMOND_AXE = registerItem( "better_diamond_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_DIAMOND,getToolAttackDamage("axe", "diamond"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_DIAMOND_PICKAXE = registerItem( "better_diamond_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_DIAMOND,(int)getToolAttackDamage("pickaxe", "diamond"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_DIAMOND_HOE = registerItem( "better_diamond_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_DIAMOND,(int)getToolAttackDamage("hoe", "diamond"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_DIAMOND_SWORD = registerItem( "better_diamond_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_DIAMOND,(int)getToolAttackDamage("sword", "diamond"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_DIAMOND_SHOVEL = registerItem( "better_diamond_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_DIAMOND,getToolAttackDamage("shovel", "diamond"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));

    public static final Item BETTER_NETHERITE_AXE = registerItem( "better_netherite_axe", new BetterAxeItem(BetterToolsMaterial.STURDY_NETHERITE,getToolAttackDamage("axe", "netherite"), getToolAttackSpeed("axe"), new FabricItemSettings() ));
    public static final Item BETTER_NETHERITE_PICKAXE = registerItem( "better_netherite_pickaxe", new BetterPickaxeItem(BetterToolsMaterial.STURDY_NETHERITE,(int)getToolAttackDamage("pickaxe", "netherite"), getToolAttackSpeed("pickaxe"), new FabricItemSettings() ));
    public static final Item BETTER_NETHERITE_HOE = registerItem( "better_netherite_hoe", new BetterHoeItem(BetterToolsMaterial.STURDY_NETHERITE,(int)getToolAttackDamage("hoe", "netherite"), getToolAttackSpeed("hoe"), new FabricItemSettings() ));
    public static final Item BETTER_NETHERITE_SWORD = registerItem( "better_netherite_sword", new BetterSwordItem(BetterToolsMaterial.STURDY_NETHERITE,(int)getToolAttackDamage("sword", "netherite"), getToolAttackSpeed("sword"), new FabricItemSettings() ));
    public static final Item BETTER_NETHERITE_SHOVEL = registerItem( "better_netherite_shovel", new BetterShovelItem(BetterToolsMaterial.STURDY_NETHERITE,getToolAttackDamage("shovel", "netherite"), getToolAttackSpeed("shovel"), new FabricItemSettings() ));

    private static Item registerItem(String name, Item item)
    {
        return Registry.register( Registries.ITEM, new Identifier(BetterTools.MOD_ID, name), item);
    }

    public static void registerModItems() {

        registerModToolRods();

    }

    private static void registerModToolRods() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
            content.addAfter( Items.STICK,
                ACACIA_TINTED_TOOL_ROD,
                BAMBOO_TINTED_TOOL_ROD,
                BIRCH_TINTED_TOOL_ROD,
                CHERRY_TINTED_TOOL_ROD,
                CRIMSON_TINTED_TOOL_ROD,
                DARK_OAK_TINTED_TOOL_ROD,
                JUNGLE_TINTED_TOOL_ROD,
                MANGROVE_TINTED_TOOL_ROD,
                OAK_TINTED_TOOL_ROD,
                SPRUCE_TINTED_TOOL_ROD,
                WARPED_TINTED_TOOL_ROD );
        });
    }


    // UNUSED but should be kept for the information.
    private static boolean isSturdyMaterial( String wood_type ) {
        switch (wood_type)
        {
            case "spruce":
            case "dark_oak":
            case "bamboo":
            case "jungle":
            case "mangrove":
            case "crimson":
                return true;
            case "oak":
            case "birch":
            case "acacia":
            case "cherry":
            case "warped":
            default:
                return false;
        }
    }
}
