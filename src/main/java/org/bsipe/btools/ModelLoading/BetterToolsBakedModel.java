package org.bsipe.btools.ModelLoading;

import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.*;
import net.minecraft.client.render.model.json.*;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import org.bsipe.btools.ModComponents;
import org.bsipe.btools.ModItems;
import org.bsipe.btools.codecs.HandleRenderComponent;
import org.bsipe.btools.codecs.ToolRenderComponent;
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
        ToolRenderComponent data = stack.get(ModComponents.TOOL_RENDER_COMPONENT );
        HandleRenderComponent fallback = null;
        if ( data == null ) {
            fallback = stack.get( ModComponents.HANDLE_RENDER_COMPONENT );
        }

        String layer0 = data == null ? fallback == null ? "" :fallback.layer0() : data.layer0();
        String layer1 = data == null ? "" : data.layer1();
        if ( layer0.equals( "" ) ) {
            bakedModel.emitItemQuads( stack, randomSupplier, context);
            return;
        }
        if ( ! BAKED_MODEL_CACHE.containsKey( layer0 + layer1 ) )
        {
            JsonUnbakedModel model = deserialize( ( data == null ? new ToolModelClass( fallback ) : new ToolModelClass( data )).toString() );
            BAKED_MODEL_CACHE.put( layer0 + layer1, ITEM_MODEL_GENERATOR.create( TEXTURE_GETTER, model).bake(BAKER, model, TEXTURE_GETTER, this.MODEL_BAKE_SETTINGS, false) );
        }
        BAKED_MODEL_CACHE.get( layer0+layer1 ).emitItemQuads( stack, randomSupplier, context );
    }

    private class ToolModelClass
    {
        List<String> identifiers;
        final String PREFIX = "{\"parent\":\"minecraft:item/handheld\",\"textures\": {";
        final String SUFFIX = "}}";

        public ToolModelClass(List<String> identifiers) { this.identifiers = identifiers; }

        public ToolModelClass( ToolRenderComponent data ) {
            String layer0 = data.layer0();
            String layer1 = data.layer1();
            if ( "".equals( layer1 ) ) {
                identifiers = List.of(layer0);
            } else {
                identifiers = List.of(layer0, layer1);
            }
        }

        public ToolModelClass( HandleRenderComponent data ) {
            String layer0 = data.layer0();
            identifiers = List.of(layer0);
        }

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
