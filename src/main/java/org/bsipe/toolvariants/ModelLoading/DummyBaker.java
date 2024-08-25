package org.bsipe.toolvariants.ModelLoading;

import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.Baker;
import net.minecraft.client.render.model.ModelBakeSettings;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class DummyBaker implements Baker {
    @Override
    public UnbakedModel getOrLoadModel(Identifier id) {
        return null;
    }

    @Nullable
    @Override
    public BakedModel bake(Identifier id, ModelBakeSettings settings) {
        return null;
    }
}
