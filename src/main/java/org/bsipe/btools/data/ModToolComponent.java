package org.bsipe.btools.data;

import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.StringIdentifiable;

public enum ModToolComponent implements StringIdentifiable {

    BASIC_HANDLE( "tool_handle" ),
    SHOVEL_HANDLE( "shovel_handle" ),
    SWORD_HILT( "sword_handle" ),
    AXE_HEAD( "axe_head", -3.2f, BASIC_HANDLE ),
    HOE_HEAD( "hoe_head", 0f, BASIC_HANDLE  ),
    PICKAXE_HEAD( "pickaxe_head", -2.8f, BASIC_HANDLE  ),
    SHOVEL_HEAD( "shovel_head", -3f, SHOVEL_HANDLE ),
    SWORD_BLADE( "sword_head", -2.4f, SWORD_HILT );


    String suffix;
    ModToolComponent handleReference;
    float attackSpeed;

    ModToolComponent(String suffix) {
        this( suffix, 0, null );
    }

    ModToolComponent(String suffix, float attackSpeed, ModToolComponent handleReference ) {
        this.suffix = suffix;
        this.attackSpeed = attackSpeed;
        this.handleReference = handleReference;
    }

    public ModToolComponent getHandleReference() { return handleReference; }
    public double getAttackSpeed() { return (double) attackSpeed; }
    @Override public String asString() { return name(); }

    public static final StringIdentifiable.EnumCodec<ModToolComponent> CODEC = StringIdentifiable.createCodec(() -> ModToolComponent.values());

    public static float getBaseDamage( ModToolComponent component ) {
        if ( component.equals( HOE_HEAD ) || component.equals( PICKAXE_HEAD )) return 0;
        if ( component.equals( SHOVEL_HEAD ) ) return .5f;
        if ( component.equals( SWORD_BLADE ) ) return 2;
        return -1;
    }

    public static boolean isGradient( ModToolComponent component ) {
        return component.equals( PICKAXE_HEAD ) || component.equals( SHOVEL_HEAD ) || component.equals( SWORD_BLADE );
    }

    public TagKey<Block> getEffectiveBlock() {
        if (AXE_HEAD.equals( this )) return BlockTags.AXE_MINEABLE;
        if (SHOVEL_HEAD.equals( this )) return BlockTags.SHOVEL_MINEABLE;
        if (HOE_HEAD.equals( this )) return BlockTags.HOE_MINEABLE;
        if (PICKAXE_HEAD.equals( this )) return BlockTags.PICKAXE_MINEABLE;
        return null;
    }

}
