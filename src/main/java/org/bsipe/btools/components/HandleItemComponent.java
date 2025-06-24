package org.bsipe.btools.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record HandleItemComponent(String handleId ) {
    public static final Codec<HandleItemComponent> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            Codec.STRING.fieldOf( "handleId" ).forGetter( HandleItemComponent::handleId))
                    .apply( instance, HandleItemComponent::new ));

}
