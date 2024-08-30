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

        translationBuilder.add( ModItems.AXE, "Axe" );
        translationBuilder.add( ModItems.HOE, "Hoe" );
        translationBuilder.add( ModItems.PICKAXE, "Pickaxe" );
        translationBuilder.add( ModItems.SWORD, "Sword" );
        translationBuilder.add( ModItems.SHOVEL, "Shovel" );


//        translationBuilder.add( ModItems.WOODEN_AXE, "Wooden Axe" );
//        translationBuilder.add( ModItems.WOODEN_HOE, "Wooden Hoe" );
//        translationBuilder.add( ModItems.WOODEN_PICKAXE, "Wooden Pickaxe" );
//        translationBuilder.add( ModItems.WOODEN_SWORD, "Wooden Sword" );
//        translationBuilder.add( ModItems.WOODEN_SHOVEL, "Wooden Shovel" );
//
//        translationBuilder.add( ModItems.STONE_AXE, "Stone Axe" );
//        translationBuilder.add( ModItems.STONE_HOE, "Stone Hoe" );
//        translationBuilder.add( ModItems.STONE_PICKAXE, "Stone Pickaxe" );
//        translationBuilder.add( ModItems.STONE_SWORD, "Stone Sword" );
//        translationBuilder.add( ModItems.STONE_SHOVEL, "Stone Shovel" );
//
//        translationBuilder.add( ModItems.GOLDEN_AXE, "Golden Axe" );
//        translationBuilder.add( ModItems.GOLDEN_HOE, "Golden Hoe" );
//        translationBuilder.add( ModItems.GOLDEN_PICKAXE, "Golden Pickaxe" );
//        translationBuilder.add( ModItems.GOLDEN_SWORD, "Golden Sword" );
//        translationBuilder.add( ModItems.GOLDEN_SHOVEL, "Golden Shovel" );
//
//        translationBuilder.add( ModItems.IRON_AXE, "Iron Axe" );
//        translationBuilder.add( ModItems.IRON_HOE, "Iron Hoe" );
//        translationBuilder.add( ModItems.IRON_PICKAXE, "Iron Pickaxe" );
//        translationBuilder.add( ModItems.IRON_SWORD, "Iron Sword" );
//        translationBuilder.add( ModItems.IRON_SHOVEL, "Iron Shovel" );
//
//        translationBuilder.add( ModItems.DIAMOND_AXE, "Diamond Axe" );
//        translationBuilder.add( ModItems.DIAMOND_HOE, "Diamond Hoe" );
//        translationBuilder.add( ModItems.DIAMOND_PICKAXE, "Diamond Pickaxe" );
//        translationBuilder.add( ModItems.DIAMOND_SWORD, "Diamond Sword" );
//        translationBuilder.add( ModItems.DIAMOND_SHOVEL, "Diamond Shovel" );
//
//        translationBuilder.add( ModItems.NETHERITE_AXE, "Netherite Axe" );
//        translationBuilder.add( ModItems.NETHERITE_HOE, "Netherite Hoe" );
//        translationBuilder.add( ModItems.NETHERITE_PICKAXE, "Netherite Pickaxe" );
//        translationBuilder.add( ModItems.NETHERITE_SWORD, "Netherite Sword" );
//        translationBuilder.add( ModItems.NETHERITE_SHOVEL, "Netherite Shovel" );

        translationBuilder.add(ModTags.TOOL_HANDLES, "Tool Handles" );
        translationBuilder.add(ModTags.TOOL_HEAD_INGREDIENTS, "Tool Head Ingredients" );


    }
}
