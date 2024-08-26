package org.bsipe.btools.enums;

import net.minecraft.util.StringIdentifiable;

public enum ComponentEnum implements StringIdentifiable {
    SHOVEL_HEAD( "shovel_head", HandleEnum.SHOVEL_HANDLE ),
    AXE_HEAD( "axe_head", HandleEnum.BASIC_HANDLE ),
    HOE_HEAD( "hoe_head", HandleEnum.BASIC_HANDLE  ),
    PICKAXE_HEAD( "pickaxe_head", HandleEnum.BASIC_HANDLE  ),
    SWORD_BLADE( "sword_head", HandleEnum.SWORD_HILT );

    ComponentEnum( String suffix, HandleEnum handleType ) {
        this.suffix = suffix;
        this.handleType = handleType;
    }

    String suffix;
    HandleEnum handleType;


    public HandleEnum getHandleType() {
        return handleType;
    }
    @Override
    public String asString() {
        return name();
    }

    public static final StringIdentifiable.EnumCodec<ComponentEnum> CODEC = StringIdentifiable.createCodec(() -> ComponentEnum.values());

}
