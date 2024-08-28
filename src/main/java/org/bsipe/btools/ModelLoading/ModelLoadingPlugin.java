package org.bsipe.btools.ModelLoading;

import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;

import java.util.Set;

import static org.bsipe.btools.BetterToolsModInitializer.MOD_ID;

public class ModelLoadingPlugin implements net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin {
    public static final Set<ModelIdentifier> MODELS = Set.of(
            modelId( "wooden_pickaxe", true ),
            modelId( "wooden_pickaxe", false ),
            modelId( "wooden_axe", true ),
            modelId( "wooden_axe", false ),
            modelId( "wooden_shovel", true ),
            modelId( "wooden_shovel", false ),
            modelId( "wooden_hoe", true ),
            modelId( "wooden_hoe", false ),
            modelId( "wooden_sword", true ),
            modelId( "wooden_sword", false ),
            modelId( "stone_pickaxe", true ),
            modelId( "stone_pickaxe", false ),
            modelId( "stone_axe", true ),
            modelId( "stone_axe", false ),
            modelId( "stone_shovel", true ),
            modelId( "stone_shovel", false ),
            modelId( "stone_hoe", true ),
            modelId( "stone_hoe", false ),
            modelId( "stone_sword", true ),
            modelId( "stone_sword", false ),
            modelId( "golden_pickaxe", true ),
            modelId( "golden_pickaxe", false ),
            modelId( "golden_axe", true ),
            modelId( "golden_axe", false ),
            modelId( "golden_shovel", true ),
            modelId( "golden_shovel", false ),
            modelId( "golden_hoe", true ),
            modelId( "golden_hoe", false ),
            modelId( "golden_sword", true ),
            modelId( "golden_sword", false ),
            modelId( "iron_pickaxe", true ),
            modelId( "iron_pickaxe", false ),
            modelId( "iron_axe", true ),
            modelId( "iron_axe", false ),
            modelId( "iron_shovel", true ),
            modelId( "iron_shovel", false ),
            modelId( "iron_hoe", true ),
            modelId( "iron_hoe", false ),
            modelId( "iron_sword", true ),
            modelId( "iron_sword", false ),
            modelId( "diamond_pickaxe", true ),
            modelId( "diamond_pickaxe", false ),
            modelId( "diamond_axe", true ),
            modelId( "diamond_axe", false ),
            modelId( "diamond_shovel", true ),
            modelId( "diamond_shovel", false ),
            modelId( "diamond_hoe", true ),
            modelId( "diamond_hoe", false ),
            modelId( "diamond_sword", true ),
            modelId( "diamond_sword", false ),
            modelId( "netherite_pickaxe", true ),
            modelId( "netherite_pickaxe", false ),
            modelId( "netherite_axe", true ),
            modelId( "netherite_axe", false ),
            modelId( "netherite_shovel", true ),
            modelId( "netherite_shovel", false ),
            modelId( "netherite_hoe", true ),
            modelId( "netherite_hoe", false ),
            modelId( "netherite_sword", true ),
            modelId( "netherite_sword", false )
    );

    private static BetterToolsBakedModel modelBaker = new BetterToolsBakedModel();

    @Override
    public void onInitializeModelLoader(Context pluginContext) {
        pluginContext.modifyModelOnLoad().register((original, context) -> {

            if ( context.topLevelId() == null ) return original;
            if ( MODELS.contains( context.topLevelId() ) )
                return modelBaker;
            return original;
        });
    }

    public static ModelIdentifier modelId( String path, boolean inventory ) {
        return new ModelIdentifier( Identifier.of( MOD_ID, path ), inventory ? "inventory" : "" );
    }
}
