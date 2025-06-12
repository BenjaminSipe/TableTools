package org.bsipe.btools.codecs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record HandleRenderComponent(String layer0, String handleId ) {
    public static final Codec<HandleRenderComponent> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            Codec.STRING.fieldOf( "layer0" ).forGetter( HandleRenderComponent::layer0),
                            Codec.STRING.fieldOf( "handleId" ).forGetter( HandleRenderComponent::handleId))
                    .apply( instance, HandleRenderComponent::new ));

}
