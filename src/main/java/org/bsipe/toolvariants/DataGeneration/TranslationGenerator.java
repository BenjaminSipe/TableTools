package org.bsipe.toolvariants.DataGeneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import org.bsipe.toolvariants.ToolVariantItems;

import java.util.concurrent.CompletableFuture;

public class TranslationGenerator extends FabricLanguageProvider {

    public TranslationGenerator(FabricDataOutput dataGenerator, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataGenerator, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add( ToolVariantItems.ACACIA_TOOL_HANDLE, "Acacia Tool Handle");
        translationBuilder.add( ToolVariantItems.BAMBOO_TOOL_HANDLE, "Bamboo Tool Handle");
        translationBuilder.add( ToolVariantItems.BIRCH_TOOL_HANDLE, "Birch Tool Handle");
        translationBuilder.add( ToolVariantItems.CHERRY_TOOL_HANDLE, "Cherry Tool Handle");
        translationBuilder.add( ToolVariantItems.CRIMSON_TOOL_HANDLE, "Crimson Tool Handle");
        translationBuilder.add( ToolVariantItems.DARK_OAK_TOOL_HANDLE, "Dark Oak Tool Handle");
        translationBuilder.add( ToolVariantItems.JUNGLE_TOOL_HANDLE, "Jungle Tool Handle");
        translationBuilder.add( ToolVariantItems.MANGROVE_TOOL_HANDLE, "Mangrove Tool Handle");
        translationBuilder.add( ToolVariantItems.OAK_TOOL_HANDLE, "Oak Tool Handle");
        translationBuilder.add( ToolVariantItems.SPRUCE_TOOL_HANDLE, "Spruce Tool Handle");
        translationBuilder.add( ToolVariantItems.WARPED_TOOL_HANDLE, "Warped Tool Handle");

        translationBuilder.add( ToolVariantItems.WOODEN_AXE, "Wooden Axe" );
        translationBuilder.add( ToolVariantItems.WOODEN_HOE, "Wooden Hoe" );
        translationBuilder.add( ToolVariantItems.WOODEN_PICKAXE, "Wooden Pickaxe" );
        translationBuilder.add( ToolVariantItems.WOODEN_SWORD, "Wooden Sword" );
        translationBuilder.add( ToolVariantItems.WOODEN_SHOVEL, "Wooden Shovel" );

        translationBuilder.add( ToolVariantItems.STONE_AXE, "Stone Axe" );
        translationBuilder.add( ToolVariantItems.STONE_HOE, "Stone Hoe" );
        translationBuilder.add( ToolVariantItems.STONE_PICKAXE, "Stone Pickaxe" );
        translationBuilder.add( ToolVariantItems.STONE_SWORD, "Stone Sword" );
        translationBuilder.add( ToolVariantItems.STONE_SHOVEL, "Stone Shovel" );

        translationBuilder.add( ToolVariantItems.GOLDEN_AXE, "Golden Axe" );
        translationBuilder.add( ToolVariantItems.GOLDEN_HOE, "Golden Hoe" );
        translationBuilder.add( ToolVariantItems.GOLDEN_PICKAXE, "Golden Pickaxe" );
        translationBuilder.add( ToolVariantItems.GOLDEN_SWORD, "Golden Sword" );
        translationBuilder.add( ToolVariantItems.GOLDEN_SHOVEL, "Golden Shovel" );

        translationBuilder.add( ToolVariantItems.IRON_AXE, "Iron Axe" );
        translationBuilder.add( ToolVariantItems.IRON_HOE, "Iron Hoe" );
        translationBuilder.add( ToolVariantItems.IRON_PICKAXE, "Iron Pickaxe" );
        translationBuilder.add( ToolVariantItems.IRON_SWORD, "Iron Sword" );
        translationBuilder.add( ToolVariantItems.IRON_SHOVEL, "Iron Shovel" );

        translationBuilder.add( ToolVariantItems.DIAMOND_AXE, "Diamond Axe" );
        translationBuilder.add( ToolVariantItems.DIAMOND_HOE, "Diamond Hoe" );
        translationBuilder.add( ToolVariantItems.DIAMOND_PICKAXE, "Diamond Pickaxe" );
        translationBuilder.add( ToolVariantItems.DIAMOND_SWORD, "Diamond Sword" );
        translationBuilder.add( ToolVariantItems.DIAMOND_SHOVEL, "Diamond Shovel" );

        translationBuilder.add( ToolVariantItems.NETHERITE_AXE, "Netherite Axe" );
        translationBuilder.add( ToolVariantItems.NETHERITE_HOE, "Netherite Hoe" );
        translationBuilder.add( ToolVariantItems.NETHERITE_PICKAXE, "Netherite Pickaxe" );
        translationBuilder.add( ToolVariantItems.NETHERITE_SWORD, "Netherite Sword" );
        translationBuilder.add( ToolVariantItems.NETHERITE_SHOVEL, "Netherite Shovel" );

        translationBuilder.add( ToolVariantItems.TOOL_HANDLES, "Tool Handles" );


    }
}
