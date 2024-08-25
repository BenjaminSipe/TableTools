package org.bsipe.toolvariants.enums;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import org.bsipe.toolvariants.ToolVariantItems;

public enum HandleMaterialsEnum {
    /*** BEGIN HANDLES */
    OakToolHandle(ToolVariantItems.OAK_TOOL_HANDLE, HandleEnum.BASIC_HANDLE, "tool-variants:item/oak_tool_handle"  ),
    OakShovelHandle( ToolVariantItems.OAK_TOOL_HANDLE, HandleEnum.SHOVEL_HANDLE, "tool-variants:item/oak_shovel_handle"  ),
    OakSwordHandle( ToolVariantItems.OAK_TOOL_HANDLE, HandleEnum.SWORD_HILT, "tool-variants:item/oak_sword_handle"  ),

    BirchToolHandle( ToolVariantItems.BIRCH_TOOL_HANDLE, HandleEnum.BASIC_HANDLE, "tool-variants:item/birch_tool_handle"  ),
    BirchShovelHandle( ToolVariantItems.BIRCH_TOOL_HANDLE, HandleEnum.SHOVEL_HANDLE, "tool-variants:item/birch_shovel_handle"  ),
    BirchSwordHandle( ToolVariantItems.BIRCH_TOOL_HANDLE, HandleEnum.SWORD_HILT, "tool-variants:item/birch_sword_handle"  ),

    AcaciaToolHandle( ToolVariantItems.ACACIA_TOOL_HANDLE, HandleEnum.BASIC_HANDLE, "tool-variants:item/acacia_tool_handle.json"  ),
    AcaciaShovelHandle( ToolVariantItems.ACACIA_TOOL_HANDLE, HandleEnum.SHOVEL_HANDLE, "tool-variants:item/acacia_shovel_handle"  ),
    AcaciaSwordHandle( ToolVariantItems.ACACIA_TOOL_HANDLE, HandleEnum.SWORD_HILT, "tool-variants:item/acacia_sword_handle"  ),

    BambooToolHandle( ToolVariantItems.BAMBOO_TOOL_HANDLE, HandleEnum.BASIC_HANDLE, "tool-variants:item/bamboo_tool_handle"  ),
    BambooShovelHandle( ToolVariantItems.BAMBOO_TOOL_HANDLE, HandleEnum.SHOVEL_HANDLE, "tool-variants:item/bamboo_shovel_handle"  ),
    BambooSwordHandle( ToolVariantItems.BAMBOO_TOOL_HANDLE, HandleEnum.SWORD_HILT, "tool-variants:item/bamboo_sword_handle"  ),

    CherryToolHandle( ToolVariantItems.CHERRY_TOOL_HANDLE, HandleEnum.BASIC_HANDLE, "tool-variants:item/cherry_tool_handle"  ),
    CherryShovelHandle( ToolVariantItems.CHERRY_TOOL_HANDLE, HandleEnum.SHOVEL_HANDLE, "tool-variants:item/cherry_shovel_handle"  ),
    CherrySwordHandle( ToolVariantItems.CHERRY_TOOL_HANDLE, HandleEnum.SWORD_HILT, "tool-variants:item/cherry_sword_handle"  ),

    CrimsonToolHandle( ToolVariantItems.CRIMSON_TOOL_HANDLE, HandleEnum.BASIC_HANDLE, "tool-variants:item/crimson_tool_handle"  ),
    CrimsonShovelHandle( ToolVariantItems.CRIMSON_TOOL_HANDLE, HandleEnum.SHOVEL_HANDLE, "tool-variants:item/crimson_shovel_handle"  ),
    CrimsonSwordHandle( ToolVariantItems.CRIMSON_TOOL_HANDLE, HandleEnum.SWORD_HILT, "tool-variants:item/crimson_sword_handle"  ),

    DarkOakToolHandle( ToolVariantItems.DARK_OAK_TOOL_HANDLE, HandleEnum.BASIC_HANDLE, "tool-variants:item/dark_oak_tool_handle"  ),
    DarkOakShovelHandle( ToolVariantItems.DARK_OAK_TOOL_HANDLE, HandleEnum.SHOVEL_HANDLE, "tool-variants:item/dark_oak_shovel_handle"  ),
    DarkOakSwordHandle( ToolVariantItems.DARK_OAK_TOOL_HANDLE, HandleEnum.SWORD_HILT, "tool-variants:item/dark_oak_sword_handle"  ),

    JungleToolHandle( ToolVariantItems.JUNGLE_TOOL_HANDLE, HandleEnum.BASIC_HANDLE, "tool-variants:item/jungle_tool_handle"  ),
    JungleShovelHandle( ToolVariantItems.JUNGLE_TOOL_HANDLE, HandleEnum.SHOVEL_HANDLE, "tool-variants:item/jungle_shovel_handle"  ),
    JungleSwordHandle( ToolVariantItems.JUNGLE_TOOL_HANDLE, HandleEnum.SWORD_HILT, "tool-variants:item/jungle_sword_handle"  ),

    MangroveToolHandle( ToolVariantItems.MANGROVE_TOOL_HANDLE, HandleEnum.BASIC_HANDLE, "tool-variants:item/mangrove_tool_handle"  ),
    MangroveShovelHandle( ToolVariantItems.MANGROVE_TOOL_HANDLE, HandleEnum.SHOVEL_HANDLE, "tool-variants:item/mangrove_shovel_handle"  ),
    MangroveSwordHandle( ToolVariantItems.MANGROVE_TOOL_HANDLE, HandleEnum.SWORD_HILT, "tool-variants:item/mangrove_sword_handle"  ),

    SpruceToolHandle( ToolVariantItems.SPRUCE_TOOL_HANDLE, HandleEnum.BASIC_HANDLE, "tool-variants:item/spruce_tool_handle"  ),
    SpruceShovelHandle( ToolVariantItems.SPRUCE_TOOL_HANDLE, HandleEnum.SHOVEL_HANDLE, "tool-variants:item/spruce_shovel_handle"  ),
    SpruceSwordHandle( ToolVariantItems.SPRUCE_TOOL_HANDLE, HandleEnum.SWORD_HILT, "tool-variants:item/spruce_sword_handle"  ),

    WarpedToolHandle( ToolVariantItems.WARPED_TOOL_HANDLE, HandleEnum.BASIC_HANDLE, "tool-variants:item/warped_tool_handle"  ),
    WarpedShovelHandle( ToolVariantItems.WARPED_TOOL_HANDLE, HandleEnum.SHOVEL_HANDLE, "tool-variants:item/warped_shovel_handle"  ),
    WarpedSwordHandle( ToolVariantItems.WARPED_TOOL_HANDLE, HandleEnum.SWORD_HILT, "tool-variants:item/warped_sword_handle"  );
    /*** END HANDLES */

    Item ingredient;
    HandleEnum component;
    String sprite;

    HandleMaterialsEnum(Item ingredient, HandleEnum component, String spriteIdentifier ) {
        this.ingredient = ingredient;
        this.component = component;
        this.sprite = spriteIdentifier;
    }

    static public String getSpriteByIngredient(Ingredient i, HandleEnum component ) {
        for ( HandleMaterialsEnum e : HandleMaterialsEnum.values() ) {
            if ( i.test( new ItemStack( e.ingredient ) ) && e.component.equals( component ) ) return e.sprite;
        }
        return "";
    }

    static public String getSpriteByItem(Item i, HandleEnum component ) {
        if ( i == null || component == null ) return "";
        for ( HandleMaterialsEnum e : HandleMaterialsEnum.values() ) {
            if ( Ingredient.ofItems( i ).test( new ItemStack( e.ingredient ) ) && e.component.equals( component ) ) return e.sprite;
        }
        return "";
    }

}
