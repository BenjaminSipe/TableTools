package org.bsipe.btools.codecs;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Decoder;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.type.AttributeModifiersComponent;

import javax.tools.Tool;

public record ToolRenderComponent(String layer0, String layer1, String material, String handleId ) {

public static final Codec<ToolRenderComponent> CODEC = RecordCodecBuilder.create(
        instance -> instance.group(
                Codec.STRING.fieldOf( "layer0" ).forGetter( ToolRenderComponent::layer0),
                Codec.STRING.fieldOf( "layer1" ).forGetter( ToolRenderComponent::layer1),
                Codec.STRING.fieldOf( "material" ).forGetter( ToolRenderComponent::material),
                Codec.STRING.fieldOf( "handleId" ).forGetter( ToolRenderComponent::handleId))
                .apply( instance, ToolRenderComponent::new ));
}
