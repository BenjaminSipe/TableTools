package org.bsipe.btools;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.impl.client.indigo.renderer.render.ItemRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import org.bsipe.btools.ModelLoading.ModelLoadingPlugin;
import org.bsipe.btools.screen.ForgeScreen;

import java.awt.image.renderable.RenderContext;
import java.util.List;

public class ModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin.register( new ModelLoadingPlugin() );

//        BuiltinItemRendererRegistry.INSTANCE.register( ModItems.RAW_PALADUS, new ModDynamicEntityRenderer() );



        // Bind Screen to handlre
        HandledScreens.register( ModScreenHandlerTypes.FORGE, ForgeScreen::new );
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RED_SOUL_FIRE, RenderLayer.getCutout());
    }

//    private class ModDynamicEntityRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer
//    {
//        ItemColors colors = new ItemColors();
//        @Override
//        public void render(ItemStack stack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
//            matrices.push();
//            BakedModel model = MinecraftClient.getInstance().getBakedModelManager().getModel(new ModelIdentifier(Identifier.of( "minecraft:gold_ingot"), "inventory" ));
//            renderBakedItemModel( model, stack, light, overlay, matrices, vertexConsumers.getBuffer(RenderLayers.getItemLayer(stack, true)));
//            matrices.pop();
//
//        }
//
//        private void renderBakedItemModel(BakedModel model, ItemStack stack, int light, int overlay, MatrixStack matrices, VertexConsumer vertices) {
//            Random random = Random.create();
//            long l = 42L;
//
//            for (Direction direction : Direction.values()) {
//                random.setSeed(42L);
//                this.renderBakedItemQuads(matrices, vertices, model.getQuads(null, direction, random), stack, light, overlay);
//            }
//
//            random.setSeed(42L);
//            this.renderBakedItemQuads(matrices, vertices, model.getQuads(null, null, random), stack, light, overlay);
//        }
//
//        private void renderBakedItemQuads(MatrixStack matrices, VertexConsumer vertices, List<BakedQuad> quads, ItemStack stack, int light, int overlay) {
//            boolean bl = !stack.isEmpty();
//            MatrixStack.Entry entry = matrices.peek();
//
//            for (BakedQuad bakedQuad : quads) {
//                int i = Colors.WHITE;
//                if (bl && bakedQuad.hasColor()) {
//                    i = this.colors.getColor(stack, bakedQuad.getColorIndex());
//                }
//
//                float f = (float) ColorHelper.Argb.getAlpha(i) / 255.0F;
//                float g = (float)ColorHelper.Argb.getRed(i) / 255.0F;
//                float h = (float)ColorHelper.Argb.getGreen(i) / 255.0F;
//                float j = (float)ColorHelper.Argb.getBlue(i) / 255.0F;
//                vertices.quad(entry, bakedQuad, g, h, j, f, light, overlay);
//            }
//        }
//    }
}
