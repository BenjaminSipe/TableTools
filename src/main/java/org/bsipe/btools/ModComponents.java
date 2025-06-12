package org.bsipe.btools;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.bsipe.btools.codecs.HandleRenderComponent;
import org.bsipe.btools.codecs.ToolRenderComponent;

public class ModComponents {
    public static final ComponentType<ToolRenderComponent> TOOL_RENDER_COMPONENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(BetterToolsModInitializer.MOD_ID, "tool_render_component"),
            ComponentType.<ToolRenderComponent>builder().codec(ToolRenderComponent.CODEC).build()
    );

    public static final ComponentType<HandleRenderComponent> HANDLE_RENDER_COMPONENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of( BetterToolsModInitializer.MOD_ID, "handle_render_component"),
            ComponentType.<HandleRenderComponent>builder().codec( HandleRenderComponent.CODEC ).build()
    );

    protected static void initialize() {

    }
}
