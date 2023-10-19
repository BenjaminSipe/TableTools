package net.rockgiant.bettertools;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;

import static net.rockgiant.bettertools.item.ModItems.TINTED_TOOL_ROD;

public class BetterToolsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0x445566, TINTED_TOOL_ROD );
	};
}
