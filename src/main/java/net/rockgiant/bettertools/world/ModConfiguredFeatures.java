package net.rockgiant.bettertools.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.rockgiant.bettertools.BetterTools;
import net.rockgiant.bettertools.item.ModItems;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?,?>> BURNING_OBSIDIAN_KEY = registerKey("burning_obsidian");

    public static void boostrap(Registerable <ConfiguredFeature<?,?>> context) {
        RuleTest deepslateReplacables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreFeatureConfig.Target> overworldBurningObsidian =
        List.of(OreFeatureConfig.createTarget( deepslateReplacables, ModItems.BURNING_OBSIDIAN.getDefaultState() ) );

        register(context, BURNING_OBSIDIAN_KEY, Feature.ORE, new OreFeatureConfig(overworldBurningObsidian, 8 ));
    }
    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier( BetterTools.MOD_ID, name ) );
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?,?>> context, RegistryKey<ConfiguredFeature<?,?>> key, F feature, FC configuration) {
        context.register( key, new ConfiguredFeature<>(feature, configuration));
    }


}
