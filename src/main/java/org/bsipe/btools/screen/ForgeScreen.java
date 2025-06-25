package org.bsipe.btools.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.bsipe.btools.BetterToolsModInitializer;
import org.bsipe.btools.screenhandler.ForgeScreenHandler;

@Environment( EnvType.CLIENT )
public class ForgeScreen extends HandledScreen<ForgeScreenHandler> {
    private static final Identifier BACKGROUND_TEXTURE = Identifier.of( BetterToolsModInitializer.MOD_ID, "textures/gui/container/forge_gui.png" );
    private static final Identifier LIT_PROGRESS_TEXTURE = Identifier.of( BetterToolsModInitializer.MOD_ID, "container/forge/lit_progress");
    private static final Identifier BURN_PROGRESS_TEXTURE = Identifier.of( BetterToolsModInitializer.MOD_ID, "container/forge/burn_progress");
    private static final Identifier FORGE_PROGRESS_TEXTURE = Identifier.of( BetterToolsModInitializer.MOD_ID, "container/forge/forge_progress");

    public ForgeScreen(ForgeScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    // adding wiggits and other crap like buttons would be done here.
//    @Override
//    protected void init() {
//        super.init();
//    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int i = this.x;
        int j = this.y;
        context.drawTexture(BACKGROUND_TEXTURE, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight );
        if (this.handler.isBurning()) {
            int k = 14;
            int l = MathHelper.ceil(this.handler.getFuelProgress() * 13.0F) + 1;
            context.drawGuiTexture(LIT_PROGRESS_TEXTURE, k, k, 0, k - l, i + 42, j + 35 + k - l, k, l);

        }

        int xTop = 63, yTop = 21;
        int progress = MathHelper.ceil(this.handler.getCookProgress() * 32.0F);
        int xShrink = progress < 22 ? progress + 1 : 28;
        int yShrink = Math.min( Math.max( progress - 17 , 4 ), 14 );
        context.drawGuiTexture(FORGE_PROGRESS_TEXTURE, 28, 14, 0, 0, i + xTop, j + yTop, xShrink, yShrink );

//        int k = 24;
        int l = MathHelper.ceil(this.handler.getAlloyProgress() * 24.0F);
        context.drawGuiTexture(BURN_PROGRESS_TEXTURE, 24, 16, 0, 0, i + 97, j + 39, l, 16);

//        context.drawText(this.textRenderer, Text.literal( String.valueOf( this.handler.getFuelProgress() )), 10, 25, 4210752, false);
//        context.drawText(this.textRenderer, Text.literal( String.valueOf( this.handler.getCookProgress() )), 10, 35, 4210752, false);
//        context.drawText(this.textRenderer, Text.literal( String.valueOf( this.handler.getAlloyProgress() )), 10, 45, 4210752, false);
//
//
//        context.drawText(this.textRenderer, Text.literal( "B " + this.handler.getPropertyDelegate().get( 0 )), 40, 35, 4210752, false);
//        context.drawText(this.textRenderer, Text.literal( "F " + this.handler.getPropertyDelegate().get( 1 )), 40, 45, 4210752, false);
//        context.drawText(this.textRenderer, Text.literal( "C " + this.handler.getPropertyDelegate().get( 2 )), 40, 55, 4210752, false);
//        context.drawText(this.textRenderer, Text.literal( "CT" + this.handler.getPropertyDelegate().get( 3 )), 40, 65, 4210752, false);
//        context.drawText(this.textRenderer, Text.literal( "A " + this.handler.getPropertyDelegate().get( 4 )), 40, 75, 4210752, false);
//        context.drawText(this.textRenderer, Text.literal( "AT" + this.handler.getPropertyDelegate().get( 5 )), 40, 85, 4210752, false);


    }

    @Override
    public void render( DrawContext context, int mouseX, int mouseY, float delta ) {
        super.render( context, mouseX, mouseY, delta );
        drawMouseoverTooltip( context, mouseX, mouseY );
//        if ( isPointWithinBounds( 144,10 + 66 - this.handler.getCounter(), 20, this.handler.getCounter(), mouseX, mouseY ) ) {
//            context.drawTooltip( this.textRenderer, Text.literal( "" + this.handler.getCounter() ), mouseX, mouseY );
//        }
    }

}
