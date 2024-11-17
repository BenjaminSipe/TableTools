package org.bsipe.btools.data.worldgen;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import org.bsipe.btools.BetterToolsModInitializer;
import org.bsipe.btools.ModBlocks;

import java.util.List;

public class ConfiguredFeatureInit {
    public static final RegistryKey<ConfiguredFeature<?, ?>> END_ORE_KEY = registerKey( "end_ore" );

    public static void bootstrap( Registerable<ConfiguredFeature<?,?>> context ) {
        RuleTest endOreReplaceables = new BlockMatchRuleTest(Blocks.END_STONE);

//        RuleTest stoneOreReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
//        RuleTest deepslateOreReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
//        RuleTest netherOreReplaceables = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);


        List<OreFeatureConfig.Target> endExampleTargets = List.of(
                OreFeatureConfig.createTarget(endOreReplaceables, ModBlocks.ENDSTONE_ENDIRIUM_ORE.getDefaultState()));

        register(context, END_ORE_KEY, Feature.ORE, new OreFeatureConfig(endExampleTargets, 9));

    }

    private static RegistryKey<ConfiguredFeature<?,?>> registerKey( String name ) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(BetterToolsModInitializer.MOD_ID, name ));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register( Registerable<ConfiguredFeature<?,?>> context,
                                                                                    RegistryKey<ConfiguredFeature<?,?>> key,
                                                                                    F feature,
                                                                                    FC featureConfig) {
        context.register( key, new ConfiguredFeature<>(feature, featureConfig));
    }
}


