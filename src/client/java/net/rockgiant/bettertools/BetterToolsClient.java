package net.rockgiant.bettertools;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.item.Item;
import net.rockgiant.bettertools.item.TintedToolRodItem;

import static net.rockgiant.bettertools.item.ModItems.*;
import static net.rockgiant.bettertools.util.ToolGenerationUtils.getTint;

public class BetterToolsClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ColorProviderRegistry.ITEM.register(
				(stack, tintIndex) ->
						((TintedToolRodItem) stack.getItem() ).getTint(), TINTED_TOOL_RODS );

		ColorProviderRegistry.ITEM.register(
				(stack, tintIndex) -> tintIndex != 0 ? -1 : getTint( stack ),
				new Item[] {BETTER_IRON_PICKAXE, BETTER_IRON_AXE} );

	};

}
