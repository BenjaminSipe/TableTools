package org.bsipe.btools;

import net.fabricmc.api.ClientModInitializer;
import org.bsipe.btools.ModelLoading.ModelLoadingPlugin;

public class ModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin.register( new ModelLoadingPlugin() );
    }
}
