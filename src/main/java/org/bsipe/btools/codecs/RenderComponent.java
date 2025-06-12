package org.bsipe.btools.codecs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

public record RenderComponent(List<String> layers) {
    public static final Codec<RenderComponent> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.list(Codec.STRING, 1,2)
                         .fieldOf( "layers" )
                         .forGetter(RenderComponent::layers ))
                    .apply( instance, RenderComponent::new ));
}
