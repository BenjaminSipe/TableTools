package org.bsipe.btools.data.generation;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import org.bsipe.btools.ModItems;
import org.bsipe.btools.ModTags;

import java.util.concurrent.CompletableFuture;

public class TranslationGenerator extends FabricLanguageProvider {

    public TranslationGenerator(FabricDataOutput dataGenerator, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataGenerator, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add( ModItems.ACACIA_TOOL_HANDLE, "Acacia Tool Handle");
        translationBuilder.add( ModItems.BAMBOO_TOOL_HANDLE, "Bamboo Tool Handle");
        translationBuilder.add( ModItems.BIRCH_TOOL_HANDLE, "Birch Tool Handle");
        translationBuilder.add( ModItems.CHERRY_TOOL_HANDLE, "Cherry Tool Handle");
        translationBuilder.add( ModItems.CRIMSON_TOOL_HANDLE, "Crimson Tool Handle");
        translationBuilder.add( ModItems.DARK_OAK_TOOL_HANDLE, "Dark Oak Tool Handle");
        translationBuilder.add( ModItems.JUNGLE_TOOL_HANDLE, "Jungle Tool Handle");
        translationBuilder.add( ModItems.MANGROVE_TOOL_HANDLE, "Mangrove Tool Handle");
        translationBuilder.add( ModItems.OAK_TOOL_HANDLE, "Oak Tool Handle");
        translationBuilder.add( ModItems.SPRUCE_TOOL_HANDLE, "Spruce Tool Handle");
        translationBuilder.add( ModItems.WARPED_TOOL_HANDLE, "Warped Tool Handle");

        translationBuilder.add( ModItems.AXE, "%s Axe" );
        translationBuilder.add( ModItems.HOE, "%s Hoe" );
        translationBuilder.add( ModItems.PICKAXE, "%s Pickaxe" );
        translationBuilder.add( ModItems.SWORD, "%s Sword" );
        translationBuilder.add( ModItems.SHOVEL, "%s Shovel" );

        translationBuilder.add(ModTags.TOOL_HANDLES, "Tool Handles" );

        // hard coded langs that I might as well put in data gen for simplicity...

        translationBuilder.add("btools:ingredient/acacia", "Acacia");
        translationBuilder.add("btools:ingredient/bamboo", "Bamboo");
        translationBuilder.add("btools:ingredient/birch", "Birch");
        translationBuilder.add("btools:ingredient/cherry", "Cherry");
        translationBuilder.add("btools:ingredient/crimson", "Crimson");
        translationBuilder.add("btools:ingredient/dark_oak", "Dark Oak");
        translationBuilder.add("btools:ingredient/jungle", "Jungle");
        translationBuilder.add("btools:ingredient/mangrove", "Mangrove");
        translationBuilder.add("btools:ingredient/oak", "Oak");
        translationBuilder.add("btools:ingredient/spruce", "Spruce");
        translationBuilder.add("btools:ingredient/warped", "Warped");
        translationBuilder.add("btools:ingredient/cobblestone", "Cobblestone");
        translationBuilder.add("btools:ingredient/cobbled_deepslate", "Deepslate");
        translationBuilder.add("btools:ingredient/blackstone", "Blackstone");
        translationBuilder.add("btools:ingredient/iron", "Iron");
        translationBuilder.add("btools:ingredient/diamond", "Diamond");
        translationBuilder.add("btools:ingredient/gold", "Gold");
        translationBuilder.add("btools:ingredient/copper", "Copper");
        translationBuilder.add("btools:ingredient/netherite", "Netherite");

        translationBuilder.add("btools:material/diamond", "Diamond");
        translationBuilder.add("btools:material/soft_metal", "Soft Metal");
        translationBuilder.add("btools:material/stone", "Stone");
        translationBuilder.add("btools:material/netherite", "Netherite");
        translationBuilder.add("btools:material/iron", "Iron");
        translationBuilder.add("btools:material/wood", "Wood");

    }
}
