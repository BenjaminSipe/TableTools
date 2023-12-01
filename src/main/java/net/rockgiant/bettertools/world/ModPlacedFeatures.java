package net.rockgiant.bettertools.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.rockgiant.bettertools.BetterTools;

import java.util.List;

public class ModPlacedFeatures {


    public static final RegistryKey<PlacedFeature> BURNING_OBSIDIAN_PLACED_KEY = registerKey("burning_obsidian_placed");

    public static void boostrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register( context, BURNING_OBSIDIAN_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.BURNING_OBSIDIAN_KEY),
                ModOrePlacement.modifiersWithCount( 24, // Veins per chunk
        HeightRangePlacementModifier.uniform(YOffset.fixed(-65), YOffset.fixed(-59))));
    }


    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier( BetterTools.MOD_ID, name ) );
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?,?>> configuration,
                                                                                    List<PlacementModifier> modifiers ) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
