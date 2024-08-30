package org.bsipe.btools.data;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.TagKey;

import java.util.Arrays;
import java.util.Optional;

import static org.bsipe.btools.ModItems.*;
import static org.bsipe.btools.ModTags.TOOL_HANDLES;

public enum ModToolHandleMaterial {
    ACACIA_HANDLE( TOOL_HANDLES, ACACIA_TOOL_HANDLE, "acacia", false),
    BAMBOO_HANDLE( TOOL_HANDLES, BAMBOO_TOOL_HANDLE, "bamboo", false),
    BIRCH_HANDLE(TOOL_HANDLES, BIRCH_TOOL_HANDLE, "birch", false),
    CHERRY_HANDLE(TOOL_HANDLES, CHERRY_TOOL_HANDLE, "cherry", false),
    CRIMSON_HANDLE(TOOL_HANDLES, CRIMSON_TOOL_HANDLE, "crimson", false),
    DARK_OAK_HANDLE(TOOL_HANDLES, DARK_OAK_TOOL_HANDLE, "dark_oak", false),
    JUNGLE_HANDLE(TOOL_HANDLES, JUNGLE_TOOL_HANDLE, "jungle", false),
    MANGROVE_HANDLE(TOOL_HANDLES, MANGROVE_TOOL_HANDLE, "mangrove", false),
    OAK_HANDLE(TOOL_HANDLES, OAK_TOOL_HANDLE, "oak", true ),
    SPRUCE_HANDLE(TOOL_HANDLES, SPRUCE_TOOL_HANDLE, "spruce", false),
    WARPED_HANDLE(TOOL_HANDLES, WARPED_TOOL_HANDLE, "warped", false),
    ;

    Ingredient materialGroup, material;

    String name;
    boolean isDefault;

    ModToolHandleMaterial(TagKey<Item> tag, Item material, String name, boolean isDefault ) {
        this.materialGroup = Ingredient.fromTag( tag );
        this.material = Ingredient.ofItems( material );
        this.name = name;
        this.isDefault = isDefault;
    }

    public static String getSpriteText(ItemStack i, ModToolComponent modToolComponent) {
        return getMaterial(i).getSpriteText(modToolComponent);
    }

    public String getSpriteText( ModToolComponent modToolComponent) {
        return "btools:item/" + name + "_" + modToolComponent.suffix;
    }

    public static ModToolHandleMaterial getMaterial(ItemStack i ) {
        Optional<ModToolHandleMaterial> optional = Arrays.stream( ModToolHandleMaterial.values() ).filter(v -> v.material.test( i ) ).findFirst();
        if (optional.isEmpty())
            optional = Arrays.stream( ModToolHandleMaterial.values() ).filter(v -> v.isDefault).filter(v -> v.materialGroup.test( i ) ).findFirst();
        return optional.orElse( OAK_HANDLE );
    }
}
