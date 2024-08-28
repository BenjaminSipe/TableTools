package org.bsipe.btools.data;

public enum HandleEnum {
    BASIC_HANDLE( "tool_handle" ),
    SHOVEL_HANDLE( "shovel_handle" ),
    SWORD_HILT( "sword_handle" );

    private String handle_suffix;

    HandleEnum( String handle_suffix ) {
        this.handle_suffix = handle_suffix;
    }
}