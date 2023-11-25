package net.rockgiant.bettertools.util;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.rockgiant.bettertools.BetterTools;
import net.rockgiant.bettertools.item.ModItemTags;
import net.rockgiant.bettertools.item.ModItems;

public class ToolGenerationUtils {

    public static final String TINT_KEY = "tint";
    public static final String HEAD_TINT_KEY = "headtint";
    public static final String HANDLE_TOOL_TIP = "handle_tooltip";
    public static final String MATERIAL_COUNT = "material_count";

    public static final Formatting TITLE_FORMATTING = Formatting.GRAY;
    public static final Formatting DESCRIPTION_FORMATTING = Formatting.BLUE;
    public static final Text ENCHANTMENT_TEMPLATE_APPLIES_TO_TEXT = Text.translatable(Util.createTranslationKey("item", new Identifier(BetterTools.MOD_ID,"smithing_template.enchantment_upgrade.applies_to"))).formatted(DESCRIPTION_FORMATTING);
    public static final Text ENCHANTMENT_TEMPLATE_INGREDIENTS_TEXT = Text.translatable(Util.createTranslationKey("item", new Identifier(BetterTools.MOD_ID, "smithing_template.enchantment_upgrade.ingredients"))).formatted(DESCRIPTION_FORMATTING);
    public static final Text ENCHANTMENT_TEMPLATE_BASE_SLOT_DESCRIPTION_TEXT_TOOL = Text.translatable(Util.createTranslationKey("item", new Identifier(BetterTools.MOD_ID,"smithing_template.enchantment_upgrade.base_slot_description_tool")));
    public static final Text ENCHANTMENT_TEMPLATE_BASE_SLOT_DESCRIPTION_TEXT_ALL = Text.translatable(Util.createTranslationKey("item", new Identifier(BetterTools.MOD_ID,"smithing_template.enchantment_upgrade.base_slot_description_all")));
    public static final Text ENCHANTMENT_TEMPLATE_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", new Identifier(BetterTools.MOD_ID, "smithing_template.enchantment_upgrade.additions_slot_description")));
    public static float getToolAttackSpeed(String tool_type ) {
        switch (tool_type) {
            case "sword":
                return -2.4f;
            case "axe":
                return -3.2f;
            case "pickaxe":
                return -2.8f;
            default:
                return -3.0f;
        }
    }

    public static int getWoodTint(Item item) {
        if ( item.equals( Items.ACACIA_PLANKS ) ) return 0xC26D3F;
        if ( item.equals( Items.BAMBOO_PLANKS ) ) return 0xEFD97E;
        if ( item.equals( Items.BIRCH_PLANKS ) ) return 0xD7CB8D;
        if ( item.equals( Items.CHERRY_PLANKS ) ) return 0xDD9D97;
        if ( item.equals( Items.CRIMSON_PLANKS ) ) return 0xAA4072;
        if ( item.equals( Items.DARK_OAK_PLANKS ) ) return 0x53381A;
        if ( item.equals( Items.JUNGLE_PLANKS ) ) return 0xBF8E6B;
        if ( item.equals( Items.MANGROVE_PLANKS ) ) return 0x8C3539;
        if ( item.equals( Items.OAK_PLANKS ) ) return 0x8F7448;
        if ( item.equals( Items.SPRUCE_PLANKS ) ) return 0x644A2A;
        if ( item.equals( Items.WARPED_PLANKS ) ) return 0x3A8E8C;
        return 0;
    }

    public static float getToolAttackDamage( String tool_type, String material_type ) {
        // these numbers are very strange and don't seem to correlate to anything.
        // but they are pulled directly from vanilla so they should match.
        if ( "sword".equals(tool_type) ) return 3;
        if ( "shovel".equals( tool_type ) ) return 1.5f;
        if ( "pickaxe".equals( tool_type ) ) return 1f;
        if ( "hoe".equals( tool_type ) ) {
            switch ( material_type ) {
                case "wood":
                    return 0;
                case "stone":
                case "copper":
                case "flint":
                    return -1;
                case "diamond":
                case "obsidian":
                    return -3;
                case "netherite":
                    return -4;
                default:
                    return -2;
            }
        }
//        if ( "axe".equals( tool_type ) ) {
        switch ( material_type ) {
            case "stone":
                return 7;
            case "diamond":
            case "netherite":
            case "obsidian":
                return 5;
            default:
                return 6;
        }
//        }
    }

    public static ItemConvertible getPlanks(String wood_type ) {

        switch ( wood_type ) {
            case "acacia":
                return Blocks.ACACIA_PLANKS;
            case "spruce":
                return Blocks.SPRUCE_PLANKS;
            case "dark_oak":
                return Blocks.DARK_OAK_PLANKS;
            case "birch":
                return Blocks.BIRCH_PLANKS;
            case "jungle":
                return Blocks.JUNGLE_PLANKS;
            case "mangrove":
                return Blocks.MANGROVE_PLANKS;
            case "bamboo":
                return Blocks.BAMBOO_PLANKS;
            case "cherry":
                return Blocks.CHERRY_PLANKS;
            case "warped":
                return Blocks.WARPED_PLANKS;
            case "crimson":
                return Blocks.CRIMSON_PLANKS;
            case "oak":
            default:
                return Blocks.OAK_PLANKS;
        }
    }

    public static String getHandleToolTip( Item item ) {
        if ( item.equals(ModItems.ACACIA_TINTED_TOOL_ROD ) ) return "Acacia Handle";
        if ( item.equals( ModItems.BAMBOO_TINTED_TOOL_ROD ) ) return "Bamboo Handle";
        if ( item.equals( ModItems.BIRCH_TINTED_TOOL_ROD ) ) return "Birch Handle";
        if ( item.equals( ModItems.CHERRY_TINTED_TOOL_ROD ) ) return "Cherry Handle";
        if ( item.equals( ModItems.CRIMSON_TINTED_TOOL_ROD ) ) return "Crimson Handle";
        if ( item.equals( ModItems.DARK_OAK_TINTED_TOOL_ROD ) ) return "Dark Oak Handle";
        if ( item.equals( ModItems.JUNGLE_TINTED_TOOL_ROD ) ) return "Jungle Handle";
        if ( item.equals( ModItems.MANGROVE_TINTED_TOOL_ROD ) ) return "Mangrove Handle";
        if ( item.equals( ModItems.OAK_TINTED_TOOL_ROD ) ) return "Oak Handle";
        if ( item.equals( ModItems.SPRUCE_TINTED_TOOL_ROD ) ) return "Spruce Handle";
        if ( item.equals( ModItems.WARPED_TINTED_TOOL_ROD ) ) return "Warped Handle";
        return "";
    }

    public static int getTint( ItemStack tool ) {
        int tint = tool.getOrCreateNbt().getInt( TINT_KEY );

        return tint != 0 ? tint : 0x8F7448;
    }
    public static int getHeadTint( ItemStack tool ) {
        int tint = tool.getOrCreateNbt().getInt( HEAD_TINT_KEY );

        return tint != 0 ? tint : 0x8F7448;
    }


    public static void addTint(ItemStack tool, int tint ) {
        tool.getOrCreateNbt().putInt( TINT_KEY, tint);
    }
    public static void addHeadTint(ItemStack tool, int tint ) {
        tool.getOrCreateNbt().putInt( HEAD_TINT_KEY, tint);
    }

    public static int getMaxRepairCount(ItemStack stack) {
        return stack.getOrCreateNbt().getInt(MATERIAL_COUNT);
    }

    public static void addMaxRepairCount(ItemStack stack ) {
        if (
            Ingredient.fromTag( ModItemTags.BETTER_AXES ).test( stack ) ||
            Ingredient.fromTag( ModItemTags.BETTER_PICKAXES ).test( stack ))
                    stack.getOrCreateNbt().putInt(MATERIAL_COUNT, 3);
        if (
            Ingredient.fromTag( ModItemTags.BETTER_SWORDS ).test( stack ) ||
            Ingredient.fromTag( ModItemTags.BETTER_HOES ).test( stack ))
                    stack.getOrCreateNbt().putInt(MATERIAL_COUNT, 2);
        if (
            Ingredient.fromTag( ModItemTags.BETTER_SHOVELS ).test( stack ) )
                    stack.getOrCreateNbt().putInt(MATERIAL_COUNT, 1);
    }

    public static void addHandleTooltip(ItemStack tool, String tooltip ) {
        tool.getOrCreateNbt().putString(HANDLE_TOOL_TIP, tooltip);
    }

    public static String getHandleToolTip(ItemStack tool ) {
        return tool.getOrCreateNbt().getString(HANDLE_TOOL_TIP);
    }

}
