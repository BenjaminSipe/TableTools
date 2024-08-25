package org.bsipe.toolvariants;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import org.bsipe.toolvariants.ModelLoading.BensToolVariantsModelLoadingPlugin;

public class BensToolVariantsClient  implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelLoadingPlugin.register( new BensToolVariantsModelLoadingPlugin() );
    }
}
