package org.bsipe.btools.ModelLoading;

import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;

import java.util.Set;

import static org.bsipe.btools.BetterToolsModInitializer.MOD_ID;

public class ModelLoadingPlugin implements net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin {
    public static final Set<ModelIdentifier> MODELS = Set.of(
            modelId( "pickaxe", true ),
            modelId( "pickaxe", false ),
            modelId( "axe", true ),
            modelId( "axe", false ),
            modelId( "shovel", true ),
            modelId( "shovel", false ),
            modelId( "hoe", true ),
            modelId( "hoe", false ),
            modelId( "sword", true ),
            modelId( "sword", false ),
            modelId( "tool_handle", true ),
            modelId( "tool_handle", false )
    );


    @Override
    public void onInitializeModelLoader(Context pluginContext) {
        pluginContext.modifyModelOnLoad().register((original, context) -> {
            if ( context.topLevelId() == null ) return original;
            if ( MODELS.contains( context.topLevelId() ) )
                return new BetterToolsBakedModel( original );
            return original;
        });
    }

    public static ModelIdentifier modelId( String path, boolean inventory ) {
        return new ModelIdentifier( Identifier.of( MOD_ID, path ), inventory ? "inventory" : "" );
    }
}
