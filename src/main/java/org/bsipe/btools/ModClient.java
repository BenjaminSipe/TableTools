package org.bsipe.btools;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import org.bsipe.btools.ModelLoading.ModelLoadingPlugin;
import org.bsipe.btools.screen.ForgeScreen;

public class ModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin.register( new ModelLoadingPlugin() );

        // Bind Screen to handlre
        HandledScreens.register( ModScreenHandlerTypes.FORGE, ForgeScreen::new );
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RED_SOUL_FIRE, RenderLayer.getCutout());
    }
}
