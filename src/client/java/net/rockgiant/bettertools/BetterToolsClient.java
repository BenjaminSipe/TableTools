package net.rockgiant.bettertools;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.item.Item;
import net.rockgiant.bettertools.item.ModItemTags;
import net.rockgiant.bettertools.item.TintedToolRodItem;

import static net.rockgiant.bettertools.item.ModItems.*;
import static net.rockgiant.bettertools.util.ToolGenerationUtils.getHeadTint;
import static net.rockgiant.bettertools.util.ToolGenerationUtils.getTint;

public class BetterToolsClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ColorProviderRegistry.ITEM.register(
				(stack, tintIndex) ->
						((TintedToolRodItem) stack.getItem() ).getTint(),
				ACACIA_TINTED_TOOL_ROD,
				BAMBOO_TINTED_TOOL_ROD,
				BIRCH_TINTED_TOOL_ROD,
				CHERRY_TINTED_TOOL_ROD,
				CRIMSON_TINTED_TOOL_ROD,
				DARK_OAK_TINTED_TOOL_ROD,
				JUNGLE_TINTED_TOOL_ROD,
				MANGROVE_TINTED_TOOL_ROD,
				OAK_TINTED_TOOL_ROD,
				SPRUCE_TINTED_TOOL_ROD,
				WARPED_TINTED_TOOL_ROD
		);

		ColorProviderRegistry.ITEM.register(
				(stack, tintIndex) -> tintIndex != 0 ? -1 : getTint( stack ),
				BETTER_IRON_PICKAXE,
				BETTER_IRON_AXE,
				BETTER_IRON_HOE,
				BETTER_IRON_SWORD,
				BETTER_IRON_SHOVEL,

				BETTER_STONE_PICKAXE,
				BETTER_STONE_AXE,
				BETTER_STONE_HOE,
				BETTER_STONE_SWORD,
				BETTER_STONE_SHOVEL,

				BETTER_GOLD_PICKAXE,
				BETTER_GOLD_AXE,
				BETTER_GOLD_HOE,
				BETTER_GOLD_SWORD,
				BETTER_GOLD_SHOVEL,

				BETTER_DIAMOND_PICKAXE,
				BETTER_DIAMOND_AXE,
				BETTER_DIAMOND_HOE,
				BETTER_DIAMOND_SWORD,
				BETTER_DIAMOND_SHOVEL,

				BETTER_NETHERITE_PICKAXE,
				BETTER_NETHERITE_AXE,
				BETTER_NETHERITE_HOE,
				BETTER_NETHERITE_SWORD,
				BETTER_NETHERITE_SHOVEL,

				BETTER_AMETHYST_SWORD,
				BETTER_AMETHYST_AXE,
				BETTER_AMETHYST_HOE,
				BETTER_AMETHYST_PICKAXE,
				BETTER_AMETHYST_SHOVEL
				);

		ColorProviderRegistry.ITEM.register(
				(stack, tintIndex) -> tintIndex != 0 ? getHeadTint(stack) : getTint( stack ),
				new Item[] {
						BETTER_WOODEN_PICKAXE, BETTER_WOODEN_AXE, BETTER_WOODEN_HOE, BETTER_WOODEN_SWORD, BETTER_WOODEN_SHOVEL
				} );
	};

}
