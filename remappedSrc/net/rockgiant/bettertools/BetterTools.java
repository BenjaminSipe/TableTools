package net.rockgiant.bettertools;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import net.rockgiant.bettertools.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BetterTools implements ModInitializer {

	public static final String MOD_ID = "bettertools";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();

	}
}