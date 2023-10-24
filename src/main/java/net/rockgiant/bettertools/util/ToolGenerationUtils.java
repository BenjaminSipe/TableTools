package net.rockgiant.bettertools.util;

import com.mojang.datafixers.types.templates.Tag;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.rockgiant.bettertools.BetterTools;

import java.util.Optional;

public class ToolGenerationUtils {

    public static final String TINT_KEY = "tint";

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
                    return -1;
                case "diamond":
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

    public static Optional getMaterial(String material ) {

        switch ( material ) {
            case "wood":
                return Optional.of(ItemTags.PLANKS);
            case "stone":
                return Optional.of( ItemTags.STONE_TOOL_MATERIALS );
            case "iron":
                return Optional.of( Items.IRON_INGOT );
            case "gold":
                return Optional.of( Items.GOLD_INGOT );
            case "diamond":
                return Optional.of( Items.DIAMOND );
            case "netherite":
            default:
                return Optional.of( Items.NETHERITE_INGOT );
        }
    }

    public static int getTint( ItemStack tool ) {
        return tool.getOrCreateNbt().getInt( TINT_KEY );
    }
    public static int getHeadTint( ItemStack tool ) {
        return tool.getOrCreateNbt().getInt( "head"+ TINT_KEY );
    }


    public static void addTint(ItemStack tool, int tint ) {
        BetterTools.LOGGER.error( "Added Tint to ItemStack " + tint );
        tool.getOrCreateNbt().putInt( TINT_KEY, tint);
    }
    public static void addHeadTint(ItemStack tool, int tint ) {
        BetterTools.LOGGER.error( "Added Tint to ItemStack " + tint );
        tool.getOrCreateNbt().putInt( "head" + TINT_KEY, tint);
    }

}
