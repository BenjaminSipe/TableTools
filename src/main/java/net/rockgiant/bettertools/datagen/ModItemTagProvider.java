package net.rockgiant.bettertools.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.rockgiant.bettertools.item.ModItemTags;

import java.util.concurrent.CompletableFuture;

import static net.rockgiant.bettertools.item.ModItems.*;

public class ModItemTagProvider extends FabricTagProvider {
    /**
     * Constructs a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output           the {@link FabricDataOutput} instance
     * @param registryKey
     * @param registriesFuture the backing registry for the tag type
     */
    public ModItemTagProvider(FabricDataOutput output, RegistryKey registryKey, CompletableFuture registriesFuture) {
        super(output, registryKey, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {

        getOrCreateTagBuilder(ModItemTags.BETTER_TOOL_RODS )
                .add( ACACIA_TINTED_TOOL_ROD )
                .add( BIRCH_TINTED_TOOL_ROD )
                .add( BAMBOO_TINTED_TOOL_ROD )
                .add( DARK_OAK_TINTED_TOOL_ROD )
                .add( SPRUCE_TINTED_TOOL_ROD )
                .add( CHERRY_TINTED_TOOL_ROD )
                .add( MANGROVE_TINTED_TOOL_ROD )
                .add( JUNGLE_TINTED_TOOL_ROD )
                .add( OAK_TINTED_TOOL_ROD )
                .add( WARPED_TINTED_TOOL_ROD )
                .add( CRIMSON_TINTED_TOOL_ROD );

        getOrCreateTagBuilder(ModItemTags.BETTER_SHOVELS )
                .add( BETTER_WOODEN_SHOVEL )
                .add( BETTER_STONE_SHOVEL )
                .add( BETTER_GOLD_SHOVEL )
                .add( BETTER_IRON_SHOVEL )
                .add( BETTER_DIAMOND_SHOVEL )
                .add( BETTER_NETHERITE_SHOVEL );

        getOrCreateTagBuilder(ModItemTags.BETTER_AXES )
                .add( BETTER_WOODEN_AXE )
                .add( BETTER_STONE_AXE )
                .add( BETTER_GOLD_AXE )
                .add( BETTER_IRON_AXE )
                .add( BETTER_DIAMOND_AXE )
                .add( BETTER_NETHERITE_AXE );

        getOrCreateTagBuilder(ModItemTags.BETTER_PICKAXES )
                .add( BETTER_WOODEN_PICKAXE )
                .add( BETTER_STONE_PICKAXE )
                .add( BETTER_GOLD_PICKAXE )
                .add( BETTER_IRON_PICKAXE )
                .add( BETTER_DIAMOND_PICKAXE )
                .add( BETTER_NETHERITE_PICKAXE );

        getOrCreateTagBuilder(ModItemTags.BETTER_SWORDS )
                .add( BETTER_WOODEN_SWORD )
                .add( BETTER_STONE_SWORD )
                .add( BETTER_GOLD_SWORD )
                .add( BETTER_IRON_SWORD )
                .add( BETTER_DIAMOND_SWORD )
                .add( BETTER_NETHERITE_SWORD );

        getOrCreateTagBuilder(ModItemTags.BETTER_HOES )
                .add( BETTER_WOODEN_HOE )
                .add( BETTER_STONE_HOE )
                .add( BETTER_GOLD_HOE )
                .add( BETTER_IRON_HOE )
                .add( BETTER_DIAMOND_HOE )
                .add( BETTER_NETHERITE_HOE );

        getOrCreateTagBuilder(ModItemTags.BETTER_TOOLS)
                .forceAddTag( ModItemTags.BETTER_PICKAXES )
                .forceAddTag( ModItemTags.BETTER_AXES )
                .forceAddTag( ModItemTags.BETTER_SHOVELS )
                .forceAddTag( ModItemTags.BETTER_SWORDS )
                .forceAddTag( ModItemTags.BETTER_HOES );
    }
}
