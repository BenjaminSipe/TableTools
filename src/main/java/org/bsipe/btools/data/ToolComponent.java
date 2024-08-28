package org.bsipe.btools.data;

import net.minecraft.util.StringIdentifiable;

public enum ToolComponent implements StringIdentifiable {

    BASIC_HANDLE( "tool_handle" ),
    SHOVEL_HANDLE( "shovel_handle" ),
    SWORD_HILT( "sword_handle" ),
    SHOVEL_HEAD( "shovel_head", SHOVEL_HANDLE ),
    AXE_HEAD( "axe_head", BASIC_HANDLE ),
    HOE_HEAD( "hoe_head", BASIC_HANDLE  ),
    PICKAXE_HEAD( "pickaxe_head", BASIC_HANDLE  ),
    SWORD_BLADE( "sword_head", SWORD_HILT );


    String suffix;
    ToolComponent handleReference;

    ToolComponent(String suffix) {
        this( suffix, null );
    }

    ToolComponent(String suffix, ToolComponent handleReference ) {
        this.suffix = suffix;
        this.handleReference = handleReference;
    }

    public ToolComponent getHandleReference() {
        return handleReference;
    }
    @Override
    public String asString() {
        return name();
    }

    public static final StringIdentifiable.EnumCodec<ToolComponent> CODEC = StringIdentifiable.createCodec(() -> ToolComponent.values());

}
