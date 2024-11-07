package org.bsipe.btools;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.bsipe.btools.network.BlockPosPayload;
import org.bsipe.btools.screenhandler.ForgeScreenHandler;

public class ModScreenHandlerTypes {

    public static final ScreenHandlerType<ForgeScreenHandler> FORGE= register( "forge", ForgeScreenHandler::new, BlockPosPayload.PACKET_CODEC);


    public static void initialize() {}

    public static <T extends ScreenHandler, D extends CustomPayload> ExtendedScreenHandlerType<T, D>
        register(String name,
                 ExtendedScreenHandlerType.ExtendedFactory<T,D> factory,
                 PacketCodec<? super RegistryByteBuf, D> codec ) {
        return Registry.register(Registries.SCREEN_HANDLER, Identifier.of( BetterToolsModInitializer.MOD_ID, name ), new ExtendedScreenHandlerType<>(factory, codec));
    }
}
