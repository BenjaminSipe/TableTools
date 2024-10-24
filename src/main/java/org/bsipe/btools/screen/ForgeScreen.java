package org.bsipe.btools.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.bsipe.btools.BetterToolsModInitializer;
import org.bsipe.btools.screenhandler.ForgeScreenHandler;

public class ForgeScreen extends HandledScreen<ForgeScreenHandler> {
    private static final Identifier TEXTURE = Identifier.of( BetterToolsModInitializer.MOD_ID, "textures/gui/container/forge_gui.png" );

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
        context.drawTexture( TEXTURE, this.x, this.y, 0, 0, this.backgroundWidth, this.backgroundHeight );

        context.fill( this.x + 144, this.y + 10 + 66 - this.handler.getCounter(), this.x + 144 + 20, this.y + 10 + 66, 0xFFD4AF37 );
    }

    @Override
    public void render( DrawContext context, int mouseX, int mouseY, float delta ) {
        super.render( context, mouseX, mouseY, delta );
        drawMouseoverTooltip( context, mouseX, mouseY );
        if ( isPointWithinBounds( 144,10 + 66 - this.handler.getCounter(), 20, this.handler.getCounter(), mouseX, mouseY ) ) {
            context.drawTooltip( this.textRenderer, Text.literal( "" + this.handler.getCounter() ), mouseX, mouseY );
        }
    }

}
