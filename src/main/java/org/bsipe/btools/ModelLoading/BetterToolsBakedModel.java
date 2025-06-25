package org.bsipe.btools.ModelLoading;

import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.model.*;
import net.minecraft.client.render.model.json.*;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import org.bsipe.btools.ModComponents;
import org.bsipe.btools.components.RenderComponent;
import org.jetbrains.annotations.Nullable;

import java.io.StringReader;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class BetterToolsBakedModel implements BakedModel, FabricBakedModel, UnbakedModel {
    private static Function<SpriteIdentifier, Sprite> TEXTURE_GETTER;
    private ModelBakeSettings MODEL_BAKE_SETTINGS;

    static final ItemModelGenerator ITEM_MODEL_GENERATOR = new ItemModelGenerator();
    private static Baker BAKER;
    private JsonUnbakedModel ITEM_HANDHELD;

    private Map<String, BakedModel> BAKED_MODEL_CACHE = new HashMap<>();
    private UnbakedModel original;
    private BakedModel bakedModel;

    public BetterToolsBakedModel(UnbakedModel original)  {
        this.original = original;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction face, Random random) {
        return List.of();
    }

    @Override
    public boolean useAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean hasDepth() {
        return false;
    }

    @Override
    public boolean isSideLit() {
        return false;
    }

    @Override
    public boolean isBuiltin() {
        return false;
    }

    @Override
    public Sprite getParticleSprite() {
        return TEXTURE_GETTER.apply( new SpriteIdentifier( SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, Identifier.of( "btools","item/oak_tool_handle")));
    }

    @Override
    public ModelTransformation getTransformation() {
        return ITEM_HANDHELD.getTransformations();
    }

    @Override
    public ModelOverrideList getOverrides() {
        return ModelOverrideList.EMPTY;
    }

    @Override
    public boolean isVanillaAdapter() {
        return false;
    }

    @Override
    public void emitItemQuads(ItemStack stack, Supplier<Random> randomSupplier, RenderContext context) {
        RenderComponent data = stack.get( ModComponents.RENDER_COMPONENT );
        if ( data == null ) {
            bakedModel.emitItemQuads( stack, randomSupplier, context);
            return;
        }

        if ( ! BAKED_MODEL_CACHE.containsKey( data.layers().toString() ) )
        {
            JsonUnbakedModel model = deserialize( ( new ToolModelClass( data.layers() )).toString() );
            BAKED_MODEL_CACHE.put( data.layers().toString(), ITEM_MODEL_GENERATOR.create( TEXTURE_GETTER, model).bake(BAKER, model, TEXTURE_GETTER, this.MODEL_BAKE_SETTINGS, false) );
        }
        BAKED_MODEL_CACHE.get( data.layers().toString() ).emitItemQuads( stack, randomSupplier, context );
    }

    private class ToolModelClass
    {
        List<String> identifiers;
        final String PREFIX = "{\"parent\":\"minecraft:item/handheld\",\"textures\": {";
        final String SUFFIX = "}}";

        public ToolModelClass(List<String> identifiers) { this.identifiers = identifiers; }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder( PREFIX );
            for ( int i = 0; i < identifiers.size(); i ++ )
            {
                sb.append("\"layer").append(i).append("\":\"").append(identifiers.get(i)).append("\"").append(i == identifiers.size() - 1 ? "" : ",");
            }
            sb.append( SUFFIX );
            return sb.toString();
        }
    }

    public static JsonUnbakedModel deserialize(String json) {
        return JsonUnbakedModel.deserialize(new StringReader(json));
    }

    @Override
    public Collection<Identifier> getModelDependencies() {
        return List.of();
    }

    @Override
    public void setParents(Function<Identifier, UnbakedModel> modelLoader) {
        ITEM_HANDHELD = (JsonUnbakedModel) modelLoader.apply( Identifier.of("minecraft", "item/handheld"));
    }

    @Nullable
    @Override
    public BakedModel bake(Baker baker, Function<SpriteIdentifier, Sprite> textureGetter, ModelBakeSettings rotationContainer) {
        TEXTURE_GETTER = textureGetter;
        MODEL_BAKE_SETTINGS = rotationContainer;
        BAKER = baker;
        this.bakedModel = ITEM_MODEL_GENERATOR.create( TEXTURE_GETTER, (JsonUnbakedModel) original).bake(baker,(JsonUnbakedModel) this.original, textureGetter, rotationContainer, false);

        return this;
    }
}
