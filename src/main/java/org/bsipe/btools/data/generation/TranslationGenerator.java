package org.bsipe.btools.data.generation;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import org.bsipe.btools.ModItems;

import java.util.concurrent.CompletableFuture;

public class TranslationGenerator extends FabricLanguageProvider {

    public TranslationGenerator(FabricDataOutput dataGenerator, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataGenerator, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {

        translationBuilder.add( ModItems.AXE, "%s Axe" );
        translationBuilder.add( ModItems.HOE, "%s Hoe" );
        translationBuilder.add( ModItems.PICKAXE, "%s Pickaxe" );
        translationBuilder.add( ModItems.SWORD, "%s Sword" );
        translationBuilder.add( ModItems.SHOVEL, "%s Shovel" );
        translationBuilder.add( ModItems.TOOL_HANDLE, "%s Tool Handle" );

        translationBuilder.add( ModItems.SLIME_STEEL_INGOT, "Slime Steel Ingot" );
        translationBuilder.add( ModItems.CRYING_STEEL_INGOT, "Crying Steel Ingot" );
        translationBuilder.add( ModItems.ENDER_STEEL_INGOT, "Ender Steel Ingot" );
        translationBuilder.add( ModItems.BLAZE_STEEL_INGOT, "Blaze Steel Ingot" );
        translationBuilder.add( ModItems.CRYSTALIZED_ENDIRIUM, "Crystalized Endirium" );
        translationBuilder.add( ModItems.BLOOD_DIAMOND, "Blood Diamond" );
        translationBuilder.add( ModItems.CRACKED_REINFORCEMENT, "Reinforced Chunk" );
        translationBuilder.add( ModItems.REINFORCED_NETHERITE_INGOT, "Reinforced Netherite Ingot" );
        translationBuilder.add( ModItems.RED_SOUL_SHARD, "Red Soul Shard" );
        translationBuilder.add( ModItems.PALADUS_INGOT, "Paladus Ingot" );
        translationBuilder.add( ModItems.HEART_OF_UNDYING, "Heart of Undying" );
        translationBuilder.add( ModItems.UNDYING_INGOT, "Undying Ingot" );
        translationBuilder.add("btools.ingredient.acacia", "Acacia");
        translationBuilder.add("btools.ingredient.bamboo", "Bamboo");
        translationBuilder.add("btools.ingredient.birch", "Birch");
        translationBuilder.add("btools.ingredient.cherry", "Cherry");
        translationBuilder.add("btools.ingredient.crimson", "Crimson");
        translationBuilder.add("btools.ingredient.dark_oak", "Dark Oak");
        translationBuilder.add("btools.ingredient.jungle", "Jungle");
        translationBuilder.add("btools.ingredient.mangrove", "Mangrove");
        translationBuilder.add("btools.ingredient.oak", "Oak");
        translationBuilder.add("btools.ingredient.spruce", "Spruce");
        translationBuilder.add("btools.ingredient.warped", "Warped");

        translationBuilder.add("btools.ingredient.cobblestone", "Cobblestone");
        translationBuilder.add("btools.ingredient.cobbled_deepslate", "Deepslate");
        translationBuilder.add("btools.ingredient.blackstone", "Blackstone");
        translationBuilder.add("btools.ingredient.andesite", "Andesite");
        translationBuilder.add("btools.ingredient.diorite", "Diorite");
        translationBuilder.add("btools.ingredient.granite", "Granite");
        translationBuilder.add("btools.ingredient.endstone", "Endstone");
        translationBuilder.add("btools.ingredient.tuff", "Tuff");
        translationBuilder.add("btools.ingredient.basalt", "Basalt");

        translationBuilder.add("btools.ingredient.gold", "Gold");
        translationBuilder.add("btools.ingredient.copper", "Copper");

        translationBuilder.add("btools.ingredient.netherrack", "Netherrack");
        translationBuilder.add("btools.ingredient.calcite", "Calcite");
        translationBuilder.add("btools.ingredient.sandstone", "Sandstone");
        translationBuilder.add("btools.ingredient.red_sandstone", "Red Sandstone");
        translationBuilder.add("btools.ingredient.flint", "Flint");

        translationBuilder.add("btools.ingredient.quartz", "Quartz");
        translationBuilder.add("btools.ingredient.amethyst", "Amethyst");
        translationBuilder.add("btools.ingredient.emerald", "Emerald");

        translationBuilder.add("btools.ingredient.obsidian", "Obsidian");
        translationBuilder.add("btools.ingredient.crying_obsidian", "Crying Obsidian");

        translationBuilder.add("btools.ingredient.echo_shard", "Echoing");
        translationBuilder.add("btools.ingredient.nether_star", "Nether Forged");

        translationBuilder.add("btools.ingredient.iron", "Iron");
        translationBuilder.add("btools.ingredient.diamond", "Diamond");
        translationBuilder.add("btools.ingredient.netherite", "Netherite");

        translationBuilder.add("btools.material.diamond", "Diamond");
        translationBuilder.add("btools.material.soft_metal", "Soft Metal");
        translationBuilder.add("btools.material.gemstone", "Gemstone");
        translationBuilder.add("btools.material.soft_stone", "Soft Stone");
        translationBuilder.add("btools.material.obsidian", "Obsidian");
        translationBuilder.add("btools.material.stone", "Stone");
        translationBuilder.add("btools.material.netherite", "Netherite");
        translationBuilder.add("btools.material.iron", "Iron");
        translationBuilder.add("btools.material.wood", "Wood");

        translationBuilder.add("btools.handle.acacia", "Acacia");
        translationBuilder.add("btools.handle.bamboo", "Bamboo");
        translationBuilder.add("btools.handle.birch", "Birch");
        translationBuilder.add("btools.handle.cherry", "Cherry");
        translationBuilder.add("btools.handle.crimson", "Crimson");
        translationBuilder.add("btools.handle.dark_oak", "Dark Oak");
        translationBuilder.add("btools.handle.jungle", "Jungle");
        translationBuilder.add("btools.handle.mangrove", "Mangrove");
        translationBuilder.add("btools.handle.oak", "Oak");
        translationBuilder.add("btools.handle.spruce", "Spruce");
        translationBuilder.add("btools.handle.warped", "Warped");
        // These don't really matter at the moment.
        // Will be used to display tool tips eventually.
        translationBuilder.add("btools.handle.bone", "Bone");
        translationBuilder.add( "btools.handle.green_bamboo", "Bamboo" );
        translationBuilder.add( "block.btools.endstone_endirium_ore", "Endirium Ore");
        translationBuilder.add( "block.btools.deepslate_forge", "Deepslate Forge");

        translationBuilder.add( "container.btools.forge_block", "Deepslate Forge" );

    }
}
