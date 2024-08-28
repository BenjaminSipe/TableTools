package org.bsipe.btools.data;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import java.util.Arrays;
import java.util.Optional;

import static org.bsipe.btools.ModItems.*;


public enum Material {

    ACACIA(ItemTags.PLANKS, Items.ACACIA_PLANKS, "acacia"),
    BAMBOO(ItemTags.PLANKS, Items.BAMBOO_PLANKS, "bamboo"),
    BIRCH(ItemTags.PLANKS, Items.BIRCH_PLANKS, "birch"),
    CHERRY(ItemTags.PLANKS, Items.CHERRY_PLANKS, "cherry"),
    CRIMSON(ItemTags.PLANKS, Items.CRIMSON_PLANKS, "crimson"),
    DARK_OAK(ItemTags.PLANKS, Items.DARK_OAK_PLANKS, "dark_oak"),
    JUNGLE(ItemTags.PLANKS, Items.JUNGLE_PLANKS, "jungle"),
    MANGROVE(ItemTags.PLANKS, Items.MANGROVE_PLANKS, "mangrove"),
    OAK(ItemTags.PLANKS, Items.OAK_PLANKS, "oak", true ),
    SPRUCE(ItemTags.PLANKS, Items.SPRUCE_PLANKS, "spruce"),
    WARPED(ItemTags.PLANKS, Items.WARPED_PLANKS, "warped"),
    STONE(ItemTags.STONE_TOOL_MATERIALS, Items.COBBLESTONE, "stone", true),
    DEEPSLATE(ItemTags.STONE_TOOL_MATERIALS, Items.COBBLED_DEEPSLATE, "deepslate"),
    BLACKSTONE(ItemTags.STONE_TOOL_MATERIALS, Items.BLACKSTONE, "blackstone"),
    GOLD(Items.GOLD_INGOT, "gold"),
    IRON(Items.IRON_INGOT, "iron"),
    DIAMOND(Items.DIAMOND, "diamond"),
    NETHERITE(Items.NETHERITE_INGOT, "netherite"),

    ACACIA_HANDLE(true, TOOL_HANDLES, ACACIA_TOOL_HANDLE, "acacia", false),
    BAMBOO_HANDLE(true, TOOL_HANDLES, BAMBOO_TOOL_HANDLE, "bamboo", false),
    BIRCH_HANDLE(true,TOOL_HANDLES, BIRCH_TOOL_HANDLE, "birch", false),
    CHERRY_HANDLE(true,TOOL_HANDLES, CHERRY_TOOL_HANDLE, "cherry", false),
    CRIMSON_HANDLE(true,TOOL_HANDLES, CRIMSON_TOOL_HANDLE, "crimson", false),
    DARK_OAK_HANDLE(true,TOOL_HANDLES, DARK_OAK_TOOL_HANDLE, "dark_oak", false),
    JUNGLE_HANDLE(true,TOOL_HANDLES, JUNGLE_TOOL_HANDLE, "jungle", false),
    MANGROVE_HANDLE(true,TOOL_HANDLES, MANGROVE_TOOL_HANDLE, "mangrove", false),
    OAK_HANDLE(true,TOOL_HANDLES, OAK_TOOL_HANDLE, "oak", true ),
    SPRUCE_HANDLE(true,TOOL_HANDLES, SPRUCE_TOOL_HANDLE, "spruce", false),
    WARPED_HANDLE(true,TOOL_HANDLES, WARPED_TOOL_HANDLE, "warped", false),

    ;



    Ingredient materialGroup, material;

    String name;
    boolean isDefault, isHandle;

    Material(boolean isHandle, TagKey<Item> tag, Item material, String name, boolean isDefault ) {
        this.materialGroup = Ingredient.fromTag( tag );
        this.material = Ingredient.ofItems( material );
        this.name = name;
        this.isDefault = isDefault;
        this.isHandle = isHandle;
    }

    Material(Ingredient materialGroup, Item material, String name, boolean isDefault ) {
        this.isHandle = false;
        this.materialGroup = materialGroup;
        this.material = Ingredient.ofItems( material );
        this.name = name;
        this.isDefault = isDefault;
    }

    Material(TagKey<Item> tag, Item item, String name) {
        this( Ingredient.fromTag( tag ), item, name, false);
    }

    Material(TagKey<Item> tag, Item item, String name, boolean isDefault) {
        this( Ingredient.fromTag( tag ), item, name, isDefault);
    }

    Material(Item item, String name) {
        this( Ingredient.ofItems( item ), item, name, true);
    }

    public static String getSpriteText( ItemStack i, ToolComponent toolComponent) {
        Optional<Material> optional = Arrays.stream( Material.values() ).filter(v -> v.material.test( i ) ).findFirst();
        if (optional.isEmpty())
            optional = Arrays.stream( Material.values() ).filter(v -> v.isDefault).filter(v -> v.materialGroup.test( i ) ).findFirst();
        if ( optional.isEmpty() ) return "";
        return optional.get().getSpriteText(toolComponent);
    }

    public String getSpriteText( ToolComponent toolComponent) {
        if ( isHandle )
            return "btools:item/" + name + "_" + toolComponent.suffix;
        return "btools:item/tool_heads/" + name + "/" + name + "_" + toolComponent.suffix;
    }






}
