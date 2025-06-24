package org.bsipe.btools.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record ToolItemComponent(String material, String handleId ) {

public static final Codec<ToolItemComponent> CODEC = RecordCodecBuilder.create(
        instance -> instance.group(
                Codec.STRING.fieldOf( "material" ).forGetter( ToolItemComponent::material),
                Codec.STRING.fieldOf( "handleId" ).forGetter( ToolItemComponent::handleId))
                .apply( instance, ToolItemComponent::new ));
}
