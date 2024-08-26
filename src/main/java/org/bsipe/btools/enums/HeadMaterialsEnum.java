package org.bsipe.btools.enums;


import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;

public enum HeadMaterialsEnum {
    /*** BEGIN HEADS */
    AcaciaAxeHead( Items.ACACIA_PLANKS, ComponentEnum.AXE_HEAD, "btools:item/tool_heads/acacia/acacia_axe_head" ),
    AcaciaPickaxeHead( Items.ACACIA_PLANKS, ComponentEnum.PICKAXE_HEAD, "btools:item/tool_heads/acacia/acacia_pickaxe_head" ),
    AcaciaHoeHead( Items.ACACIA_PLANKS, ComponentEnum.HOE_HEAD, "btools:item/tool_heads/acacia/acacia_hoe_head" ),
    AcaciaSwordHead( Items.ACACIA_PLANKS, ComponentEnum.SWORD_BLADE, "btools:item/tool_heads/acacia/acacia_sword_head" ),
    AcaciaShovelHead( Items.ACACIA_PLANKS, ComponentEnum.SHOVEL_HEAD, "btools:item/tool_heads/acacia/acacia_shovel_head" ),

    BambooAxeHead( Items.BAMBOO_PLANKS, ComponentEnum.AXE_HEAD, "btools:item/tool_heads/bamboo/bamboo_axe_head" ),
    BambooPickaxeHead( Items.BAMBOO_PLANKS, ComponentEnum.PICKAXE_HEAD, "btools:item/tool_heads/bamboo/bamboo_pickaxe_head" ),
    BambooHoeHead( Items.BAMBOO_PLANKS, ComponentEnum.HOE_HEAD, "btools:item/tool_heads/bamboo/bamboo_hoe_head" ),
    BambooSwordHead( Items.BAMBOO_PLANKS, ComponentEnum.SWORD_BLADE, "btools:item/tool_heads/bamboo/bamboo_sword_head" ),
    BambooShovelHead( Items.BAMBOO_PLANKS, ComponentEnum.SHOVEL_HEAD, "btools:item/tool_heads/bamboo/bamboo_shovel_head" ),

    BirchAxeHead( Items.BIRCH_PLANKS, ComponentEnum.AXE_HEAD, "btools:item/tool_heads/birch/birch_axe_head" ),
    BirchPickaxeHead( Items.BIRCH_PLANKS, ComponentEnum.PICKAXE_HEAD, "btools:item/tool_heads/birch/birch_pickaxe_head" ),
    BirchHoeHead( Items.BIRCH_PLANKS, ComponentEnum.HOE_HEAD, "btools:item/tool_heads/birch/birch_hoe_head" ),
    BirchSwordHead( Items.BIRCH_PLANKS, ComponentEnum.SWORD_BLADE, "btools:item/tool_heads/birch/birch_sword_head" ),
    BirchShovelHead( Items.BIRCH_PLANKS, ComponentEnum.SHOVEL_HEAD, "btools:item/tool_heads/birch/birch_shovel_head" ),

    CherryAxeHead( Items.CHERRY_PLANKS, ComponentEnum.AXE_HEAD, "btools:item/tool_heads/cherry/cherry_axe_head" ),
    CherryPickaxeHead( Items.CHERRY_PLANKS, ComponentEnum.PICKAXE_HEAD, "btools:item/tool_heads/cherry/cherry_pickaxe_head" ),
    CherryHoeHead( Items.CHERRY_PLANKS, ComponentEnum.HOE_HEAD, "btools:item/tool_heads/cherry/cherry_hoe_head" ),
    CherrySwordHead( Items.CHERRY_PLANKS, ComponentEnum.SWORD_BLADE, "btools:item/tool_heads/cherry/cherry_sword_head" ),
    CherryShovelHead( Items.CHERRY_PLANKS, ComponentEnum.SHOVEL_HEAD, "btools:item/tool_heads/cherry/cherry_shovel_head" ),

    CrimsonAxeHead( Items.CRIMSON_PLANKS, ComponentEnum.AXE_HEAD, "btools:item/tool_heads/crimson/crimson_axe_head" ),
    CrimsonPickaxeHead( Items.CRIMSON_PLANKS, ComponentEnum.PICKAXE_HEAD, "btools:item/tool_heads/crimson/crimson_pickaxe_head" ),
    CrimsonHoeHead( Items.CRIMSON_PLANKS, ComponentEnum.HOE_HEAD, "btools:item/tool_heads/crimson/crimson_hoe_head" ),
    CrimsonSwordHead( Items.CRIMSON_PLANKS, ComponentEnum.SWORD_BLADE, "btools:item/tool_heads/crimson/crimson_sword_head" ),
    CrimsonShovelHead( Items.CRIMSON_PLANKS, ComponentEnum.SHOVEL_HEAD, "btools:item/tool_heads/crimson/crimson_shovel_head" ),

    DarkOakAxeHead( Items.DARK_OAK_PLANKS, ComponentEnum.AXE_HEAD, "btools:item/tool_heads/dark_oak/dark_oak_axe_head" ),
    DarkOakPickaxeHead( Items.DARK_OAK_PLANKS, ComponentEnum.PICKAXE_HEAD, "btools:item/tool_heads/dark_oak/dark_oak_pickaxe_head" ),
    DarkOakHoeHead( Items.DARK_OAK_PLANKS, ComponentEnum.HOE_HEAD, "btools:item/tool_heads/dark_oak/dark_oak_hoe_head" ),
    DarkOakSwordHead( Items.DARK_OAK_PLANKS, ComponentEnum.SWORD_BLADE, "btools:item/tool_heads/dark_oak/dark_oak_sword_head" ),
    DarkOakShovelHead( Items.DARK_OAK_PLANKS, ComponentEnum.SHOVEL_HEAD, "btools:item/tool_heads/dark_oak/dark_oak_shovel_head" ),

    JungleAxeHead( Items.JUNGLE_PLANKS, ComponentEnum.AXE_HEAD, "btools:item/tool_heads/jungle/jungle_axe_head" ),
    JunglePickaxeHead( Items.JUNGLE_PLANKS, ComponentEnum.PICKAXE_HEAD, "btools:item/tool_heads/jungle/jungle_pickaxe_head" ),
    JungleHoeHead( Items.JUNGLE_PLANKS, ComponentEnum.HOE_HEAD, "btools:item/tool_heads/jungle/jungle_hoe_head" ),
    JungleSwordHead( Items.JUNGLE_PLANKS, ComponentEnum.SWORD_BLADE, "btools:item/tool_heads/jungle/jungle_sword_head" ),
    JungleShovelHead( Items.JUNGLE_PLANKS, ComponentEnum.SHOVEL_HEAD, "btools:item/tool_heads/jungle/jungle_shovel_head" ),

    MangroveAxeHead( Items.MANGROVE_PLANKS, ComponentEnum.AXE_HEAD, "btools:item/tool_heads/mangrove/mangrove_axe_head" ),
    MangrovePickaxeHead( Items.MANGROVE_PLANKS, ComponentEnum.PICKAXE_HEAD, "btools:item/tool_heads/mangrove/mangrove_pickaxe_head" ),
    MangroveHoeHead( Items.MANGROVE_PLANKS, ComponentEnum.HOE_HEAD, "btools:item/tool_heads/mangrove/mangrove_hoe_head" ),
    MangroveSwordHead( Items.MANGROVE_PLANKS, ComponentEnum.SWORD_BLADE, "btools:item/tool_heads/mangrove/mangrove_sword_head" ),
    MangroveShovelHead( Items.MANGROVE_PLANKS, ComponentEnum.SHOVEL_HEAD, "btools:item/tool_heads/mangrove/mangrove_shovel_head" ),

    OakAxeHead( Items.OAK_PLANKS, ComponentEnum.AXE_HEAD, "btools:item/tool_heads/oak/oak_axe_head" ),
    OakPickaxeHead( Items.OAK_PLANKS, ComponentEnum.PICKAXE_HEAD, "btools:item/tool_heads/oak/oak_pickaxe_head" ),
    OakHoeHead( Items.OAK_PLANKS, ComponentEnum.HOE_HEAD, "btools:item/tool_heads/oak/oak_hoe_head" ),
    OakSwordHead( Items.OAK_PLANKS, ComponentEnum.SWORD_BLADE, "btools:item/tool_heads/oak/oak_sword_head" ),
    OakShovelHead( Items.OAK_PLANKS, ComponentEnum.SHOVEL_HEAD, "btools:item/tool_heads/oak/oak_shovel_head" ),

    SpruceAxeHead( Items.SPRUCE_PLANKS, ComponentEnum.AXE_HEAD, "btools:item/tool_heads/spruce/spruce_axe_head" ),
    SprucePickaxeHead( Items.SPRUCE_PLANKS, ComponentEnum.PICKAXE_HEAD, "btools:item/tool_heads/spruce/spruce_pickaxe_head" ),
    SpruceHoeHead( Items.SPRUCE_PLANKS, ComponentEnum.HOE_HEAD, "btools:item/tool_heads/spruce/spruce_hoe_head" ),
    SpruceSwordHead( Items.SPRUCE_PLANKS, ComponentEnum.SWORD_BLADE, "btools:item/tool_heads/spruce/spruce_sword_head" ),
    SpruceShovelHead( Items.SPRUCE_PLANKS, ComponentEnum.SHOVEL_HEAD, "btools:item/tool_heads/spruce/spruce_shovel_head" ),

    WarpedAxeHead( Items.WARPED_PLANKS, ComponentEnum.AXE_HEAD, "btools:item/tool_heads/warped/warped_axe_head" ),
    WarpedPickaxeHead( Items.WARPED_PLANKS, ComponentEnum.PICKAXE_HEAD, "btools:item/tool_heads/warped/warped_pickaxe_head" ),
    WarpedHoeHead( Items.WARPED_PLANKS, ComponentEnum.HOE_HEAD, "btools:item/tool_heads/warped/warped_hoe_head" ),
    WarpedSwordHead( Items.WARPED_PLANKS, ComponentEnum.SWORD_BLADE, "btools:item/tool_heads/warped/warped_sword_head" ),
    WarpedShovelHead( Items.WARPED_PLANKS, ComponentEnum.SHOVEL_HEAD, "btools:item/tool_heads/warped/warped_shovel_head" ),

    StoneAxeHead( Items.COBBLESTONE, ComponentEnum.AXE_HEAD, "btools:item/tool_heads/stone/stone_axe_head" ),
    StonePickaxeHead( Items.COBBLESTONE, ComponentEnum.PICKAXE_HEAD, "btools:item/tool_heads/stone/stone_pickaxe_head" ),
    StoneHoeHead( Items.COBBLESTONE, ComponentEnum.HOE_HEAD, "btools:item/tool_heads/stone/stone_hoe_head" ),
    StoneSwordHead( Items.COBBLESTONE, ComponentEnum.SWORD_BLADE, "btools:item/tool_heads/stone/stone_sword_head" ),
    StoneShovelHead( Items.COBBLESTONE, ComponentEnum.SHOVEL_HEAD, "btools:item/tool_heads/stone/stone_shovel_head" ),

    BlackStoneAxeHead( Items.BLACKSTONE, ComponentEnum.AXE_HEAD, "btools:item/tool_heads/blackstone/blackstone_axe_head" ),
    BlackstonePickaxeHead( Items.BLACKSTONE, ComponentEnum.PICKAXE_HEAD, "btools:item/tool_heads/blackstone/blackstone_pickaxe_head" ),
    BlackstoneHoeHead( Items.BLACKSTONE, ComponentEnum.HOE_HEAD, "btools:item/tool_heads/blackstone/blackstone_hoe_head" ),
    BlackstoneSwordHead( Items.BLACKSTONE, ComponentEnum.SWORD_BLADE, "btools:item/tool_heads/blackstone/blackstone_sword_head" ),
    BlackstoneShovelHead( Items.BLACKSTONE, ComponentEnum.SHOVEL_HEAD, "btools:item/tool_heads/blackstone/blackstone_shovel_head" ),

    DeepslateAxeHead( Items.COBBLED_DEEPSLATE, ComponentEnum.AXE_HEAD, "btools:item/tool_heads/deepslate/deepslate_axe_head" ),
    DeepslatePickaxeHead( Items.COBBLED_DEEPSLATE, ComponentEnum.PICKAXE_HEAD, "btools:item/tool_heads/deepslate/deepslate_pickaxe_head" ),
    DeepslateHoeHead( Items.COBBLED_DEEPSLATE, ComponentEnum.HOE_HEAD, "btools:item/tool_heads/deepslate/deepslate_hoe_head" ),
    DeepslateSwordHead( Items.COBBLED_DEEPSLATE, ComponentEnum.SWORD_BLADE, "btools:item/tool_heads/deepslate/deepslate_sword_head" ),
    DeepslateShovelHead( Items.COBBLED_DEEPSLATE, ComponentEnum.SHOVEL_HEAD, "btools:item/tool_heads/deepslate/deepslate_shovel_head" ),

    GoldAxeHead( Items.GOLD_INGOT, ComponentEnum.AXE_HEAD, "btools:item/tool_heads/gold/gold_axe_head" ),
    GoldPickaxeHead( Items.GOLD_INGOT, ComponentEnum.PICKAXE_HEAD, "btools:item/tool_heads/gold/gold_pickaxe_head" ),
    GoldHoeHead( Items.GOLD_INGOT, ComponentEnum.HOE_HEAD, "btools:item/tool_heads/gold/gold_hoe_head" ),
    GoldSwordHead( Items.GOLD_INGOT, ComponentEnum.SWORD_BLADE, "btools:item/tool_heads/gold/gold_sword_head" ),
    GoldShovelHead( Items.GOLD_INGOT, ComponentEnum.SHOVEL_HEAD, "btools:item/tool_heads/gold/gold_shovel_head" ),

    IronAxeHead( Items.IRON_INGOT, ComponentEnum.AXE_HEAD, "btools:item/tool_heads/iron/iron_axe_head" ),
    IronPickaxeHead( Items.IRON_INGOT, ComponentEnum.PICKAXE_HEAD, "btools:item/tool_heads/iron/iron_pickaxe_head" ),
    IronHoeHead( Items.IRON_INGOT, ComponentEnum.HOE_HEAD, "btools:item/tool_heads/iron/iron_hoe_head" ),
    IronSwordHead( Items.IRON_INGOT, ComponentEnum.SWORD_BLADE, "btools:item/tool_heads/iron/iron_sword_head" ),
    IronShovelHead( Items.IRON_INGOT, ComponentEnum.SHOVEL_HEAD, "btools:item/tool_heads/iron/iron_shovel_head" ),

    DiamondAxeHead( Items.DIAMOND, ComponentEnum.AXE_HEAD, "btools:item/tool_heads/diamond/diamond_axe_head" ),
    DiamondPickaxeHead( Items.DIAMOND, ComponentEnum.PICKAXE_HEAD, "btools:item/tool_heads/diamond/diamond_pickaxe_head" ),
    DiamondHoeHead( Items.DIAMOND, ComponentEnum.HOE_HEAD, "btools:item/tool_heads/diamond/diamond_hoe_head" ),
    DiamondSwordHead( Items.DIAMOND, ComponentEnum.SWORD_BLADE, "btools:item/tool_heads/diamond/diamond_sword_head" ),
    DiamondShovelHead( Items.DIAMOND, ComponentEnum.SHOVEL_HEAD, "btools:item/tool_heads/diamond/diamond_shovel_head" ),

    NetheriteAxeHead( Items.NETHERITE_INGOT, ComponentEnum.AXE_HEAD, "btools:item/tool_heads/netherite/netherite_axe_head" ),
    NetheritePickaxeHead( Items.NETHERITE_INGOT, ComponentEnum.PICKAXE_HEAD, "btools:item/tool_heads/netherite/netherite_pickaxe_head" ),
    NetheriteHoeHead( Items.NETHERITE_INGOT, ComponentEnum.HOE_HEAD, "btools:item/tool_heads/netherite/netherite_hoe_head" ),
    NetheriteSwordHead( Items.NETHERITE_INGOT, ComponentEnum.SWORD_BLADE, "btools:item/tool_heads/netherite/netherite_sword_head" ),
    NetheriteShovelHead( Items.NETHERITE_INGOT, ComponentEnum.SHOVEL_HEAD, "btools:item/tool_heads/netherite/netherite_shovel_head" ),

    ;



    HeadMaterialsEnum(Item ingredient, ComponentEnum component, String spriteIdentifier ) {
        this.ingredient = ingredient;
        this.component = component;
        this.sprite = spriteIdentifier;
    }

    Item ingredient;
    ComponentEnum component;
    String sprite;

    public String getSprite() {
        return sprite;
    }

    static public HeadMaterialsEnum getMaterial(Item ingredient ) {
        for (HeadMaterialsEnum e :  HeadMaterialsEnum.values() )
        {
            if ( e.ingredient.equals( ingredient ) ) return e;
        }
        return null;
    }

    static public String getSprite( Item ingredient ) {
        return getMaterial( ingredient ).getSprite();
    }

    static public HeadMaterialsEnum getMaterialByComponent(Item i, ComponentEnum c) {
        for (HeadMaterialsEnum e :  HeadMaterialsEnum.values() )
        {
            if ( e.ingredient.equals( i ) && e.component == c ) return e;
        }
        return null;
    }

    static public String getSpriteByIngredient( Ingredient i, ComponentEnum component ) {
        for ( HeadMaterialsEnum e : HeadMaterialsEnum.values() ) {
            if ( i.test( new ItemStack( e.ingredient ) ) && e.component.equals( component ) ) return e.sprite;
        }
        return "";
    }
    static public String getSpriteByItem( Item i, ComponentEnum component ) {
        if ( i == null || component == null ) return "";
        for ( HeadMaterialsEnum e : HeadMaterialsEnum.values() ) {
            if ( Ingredient.ofItems( i ).test( new ItemStack( e.ingredient ) ) && e.component.equals( component ) ) return e.sprite;
        }
        return "";
    }

}
