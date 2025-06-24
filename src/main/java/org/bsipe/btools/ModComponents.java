package org.bsipe.btools;

import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.bsipe.btools.components.HandleItemComponent;
import org.bsipe.btools.components.RenderComponent;
import org.bsipe.btools.components.ToolItemComponent;

public class ModComponents {
    public static final ComponentType<ToolItemComponent> TOOL_RENDER_COMPONENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BetterToolsModInitializer.MOD_ID, "tool_render_component"),
            ComponentType.<ToolItemComponent>builder().codec(ToolItemComponent.CODEC).build()
    );

    public static final ComponentType<HandleItemComponent> HANDLE_RENDER_COMPONENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of( BetterToolsModInitializer.MOD_ID, "handle_render_component"),
            ComponentType.<HandleItemComponent>builder().codec( HandleItemComponent.CODEC ).build()
    );

    public static final ComponentType<RenderComponent> RENDER_COMPONENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of( BetterToolsModInitializer.MOD_ID, "render_component"),
            ComponentType.<RenderComponent>builder().codec( RenderComponent.CODEC ).build()
    );
    protected static void initialize() {

    }
}
