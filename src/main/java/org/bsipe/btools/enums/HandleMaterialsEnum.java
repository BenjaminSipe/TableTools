package org.bsipe.btools.enums;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import org.bsipe.btools.ModItems;

public enum HandleMaterialsEnum {
    /*** BEGIN HANDLES */
    OakToolHandle(ModItems.OAK_TOOL_HANDLE, HandleEnum.BASIC_HANDLE, "btools:item/oak_tool_handle"  ),
    OakShovelHandle( ModItems.OAK_TOOL_HANDLE, HandleEnum.SHOVEL_HANDLE, "btools:item/oak_shovel_handle"  ),
    OakSwordHandle( ModItems.OAK_TOOL_HANDLE, HandleEnum.SWORD_HILT, "btools:item/oak_sword_handle"  ),

    BirchToolHandle( ModItems.BIRCH_TOOL_HANDLE, HandleEnum.BASIC_HANDLE, "btools:item/birch_tool_handle"  ),
    BirchShovelHandle( ModItems.BIRCH_TOOL_HANDLE, HandleEnum.SHOVEL_HANDLE, "btools:item/birch_shovel_handle"  ),
    BirchSwordHandle( ModItems.BIRCH_TOOL_HANDLE, HandleEnum.SWORD_HILT, "btools:item/birch_sword_handle"  ),

    AcaciaToolHandle( ModItems.ACACIA_TOOL_HANDLE, HandleEnum.BASIC_HANDLE, "btools:item/acacia_tool_handle.json"  ),
    AcaciaShovelHandle( ModItems.ACACIA_TOOL_HANDLE, HandleEnum.SHOVEL_HANDLE, "btools:item/acacia_shovel_handle"  ),
    AcaciaSwordHandle( ModItems.ACACIA_TOOL_HANDLE, HandleEnum.SWORD_HILT, "btools:item/acacia_sword_handle"  ),

    BambooToolHandle( ModItems.BAMBOO_TOOL_HANDLE, HandleEnum.BASIC_HANDLE, "btools:item/bamboo_tool_handle"  ),
    BambooShovelHandle( ModItems.BAMBOO_TOOL_HANDLE, HandleEnum.SHOVEL_HANDLE, "btools:item/bamboo_shovel_handle"  ),
    BambooSwordHandle( ModItems.BAMBOO_TOOL_HANDLE, HandleEnum.SWORD_HILT, "btools:item/bamboo_sword_handle"  ),

    CherryToolHandle( ModItems.CHERRY_TOOL_HANDLE, HandleEnum.BASIC_HANDLE, "btools:item/cherry_tool_handle"  ),
    CherryShovelHandle( ModItems.CHERRY_TOOL_HANDLE, HandleEnum.SHOVEL_HANDLE, "btools:item/cherry_shovel_handle"  ),
    CherrySwordHandle( ModItems.CHERRY_TOOL_HANDLE, HandleEnum.SWORD_HILT, "btools:item/cherry_sword_handle"  ),

    CrimsonToolHandle( ModItems.CRIMSON_TOOL_HANDLE, HandleEnum.BASIC_HANDLE, "btools:item/crimson_tool_handle"  ),
    CrimsonShovelHandle( ModItems.CRIMSON_TOOL_HANDLE, HandleEnum.SHOVEL_HANDLE, "btools:item/crimson_shovel_handle"  ),
    CrimsonSwordHandle( ModItems.CRIMSON_TOOL_HANDLE, HandleEnum.SWORD_HILT, "btools:item/crimson_sword_handle"  ),

    DarkOakToolHandle( ModItems.DARK_OAK_TOOL_HANDLE, HandleEnum.BASIC_HANDLE, "btools:item/dark_oak_tool_handle"  ),
    DarkOakShovelHandle( ModItems.DARK_OAK_TOOL_HANDLE, HandleEnum.SHOVEL_HANDLE, "btools:item/dark_oak_shovel_handle"  ),
    DarkOakSwordHandle( ModItems.DARK_OAK_TOOL_HANDLE, HandleEnum.SWORD_HILT, "btools:item/dark_oak_sword_handle"  ),

    JungleToolHandle( ModItems.JUNGLE_TOOL_HANDLE, HandleEnum.BASIC_HANDLE, "btools:item/jungle_tool_handle"  ),
    JungleShovelHandle( ModItems.JUNGLE_TOOL_HANDLE, HandleEnum.SHOVEL_HANDLE, "btools:item/jungle_shovel_handle"  ),
    JungleSwordHandle( ModItems.JUNGLE_TOOL_HANDLE, HandleEnum.SWORD_HILT, "btools:item/jungle_sword_handle"  ),

    MangroveToolHandle( ModItems.MANGROVE_TOOL_HANDLE, HandleEnum.BASIC_HANDLE, "btools:item/mangrove_tool_handle"  ),
    MangroveShovelHandle( ModItems.MANGROVE_TOOL_HANDLE, HandleEnum.SHOVEL_HANDLE, "btools:item/mangrove_shovel_handle"  ),
    MangroveSwordHandle( ModItems.MANGROVE_TOOL_HANDLE, HandleEnum.SWORD_HILT, "btools:item/mangrove_sword_handle"  ),

    SpruceToolHandle( ModItems.SPRUCE_TOOL_HANDLE, HandleEnum.BASIC_HANDLE, "btools:item/spruce_tool_handle"  ),
    SpruceShovelHandle( ModItems.SPRUCE_TOOL_HANDLE, HandleEnum.SHOVEL_HANDLE, "btools:item/spruce_shovel_handle"  ),
    SpruceSwordHandle( ModItems.SPRUCE_TOOL_HANDLE, HandleEnum.SWORD_HILT, "btools:item/spruce_sword_handle"  ),

    WarpedToolHandle( ModItems.WARPED_TOOL_HANDLE, HandleEnum.BASIC_HANDLE, "btools:item/warped_tool_handle"  ),
    WarpedShovelHandle( ModItems.WARPED_TOOL_HANDLE, HandleEnum.SHOVEL_HANDLE, "btools:item/warped_shovel_handle"  ),
    WarpedSwordHandle( ModItems.WARPED_TOOL_HANDLE, HandleEnum.SWORD_HILT, "btools:item/warped_sword_handle"  );
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
