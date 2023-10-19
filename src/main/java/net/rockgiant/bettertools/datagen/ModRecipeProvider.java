package net.rockgiant.bettertools.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.rockgiant.bettertools.item.ModItems;
import net.rockgiant.bettertools.item.tools.*;

import java.util.Optional;

import static net.rockgiant.bettertools.item.ModItems.*;
import static net.rockgiant.bettertools.util.ToolGenerationUtils.getMaterial;
import static net.rockgiant.bettertools.util.ToolGenerationUtils.getPlanks;

public class ModRecipeProvider extends FabricRecipeProvider {


    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generate(RecipeExporter exporter) {

        generateToolRodRecipes(exporter);
        generateToolRecipes(exporter);

    }

    private void generateToolRecipes(RecipeExporter exporter) {
        for (BetterSwordItem sword : SWORDS ) {
            if ("netherite".equals(sword.getHead())) {
                generateSmithingTableUpgradeRecipe(exporter, sword, SMITHING_RECIPE_ITEM_MAP.get(sword.getHandle() + "_sword" ),
                        "netherite_" + sword.getHandle() + "_sword_smithing" );
            }
            else
            {
                generateSwordRecipe(sword).offerTo(exporter);
            }
        }
        for (BetterPickaxeItem pickaxe : PICKAXES ) {
            if ("netherite".equals(pickaxe.getHead())) {
                generateSmithingTableUpgradeRecipe(exporter, pickaxe, SMITHING_RECIPE_ITEM_MAP.get(pickaxe.getHandle() + "_pickaxe" ),
                        "netherite_" + pickaxe.getHandle() + "_pickaxe_smithing" );
            }
            else {
                generatePickaxeRecipe(pickaxe).offerTo(exporter);
            }
        }
        for (BetterShovelItem shovel : SHOVELS ) {
            if ("netherite".equals(shovel.getHead())) {
                generateSmithingTableUpgradeRecipe(exporter, shovel, SMITHING_RECIPE_ITEM_MAP.get(shovel.getHandle() + "_shovel" ),
                        "netherite_" + shovel.getHandle() + "_shovel_smithing" );
            }
            else {
                generateShovelRecipe(shovel).offerTo(exporter);
            }
        }
        for (BetterHoeItem hoe : HOES )
        {
            if ("netherite".equals(hoe.getHead())) {
                generateSmithingTableUpgradeRecipe(exporter, hoe, SMITHING_RECIPE_ITEM_MAP.get(hoe.getHandle() + "_hoe" ),
                        "netherite_" + hoe.getHandle() + "_hoe_smithing" );
            }
            else {
                generateHoeRecipe( hoe ).offerTo( exporter );
            }
        }
        for (BetterAxeItem axe : AXES ) {
            if ("netherite".equals(axe.getHead())) {
                generateSmithingTableUpgradeRecipe(exporter, axe, SMITHING_RECIPE_ITEM_MAP.get(axe.getHandle() + "_axe" ),
                        "netherite_" + axe.getHandle() + "_axe_smithing" );
            }
            else {
                generateAxeRecipe( axe ).offerTo( exporter );
            }
        }
    }

    public void generateToolRodRecipes(RecipeExporter exporter ) {
        for ( String wood_type : ModItems.WOOD_TYPES )
        {
            ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, TOOL_RODS.get(wood_type), 2 )
                    .pattern("# ").pattern(" #")
                    .input('#', getPlanks( wood_type) )
                    .criterion( FabricRecipeProvider.hasItem(getPlanks( wood_type ) ),
                            FabricRecipeProvider.conditionsFromItem(getPlanks( wood_type ) ) ).offerTo( exporter );
        }
    }

    public void generateSmithingTableUpgradeRecipe(RecipeExporter exporter, Item result, Item ingredient, String recipeId ) {
        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems( Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE ),
                Ingredient.ofItems( ingredient ),
                Ingredient.ofItems( Items.NETHERITE_INGOT ),
                RecipeCategory.TOOLS,
                result)
                .criterion(FabricRecipeProvider.hasItem( Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE ),
                        FabricRecipeProvider.conditionsFromItem( Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .criterion(FabricRecipeProvider.hasItem( Items.NETHERITE_INGOT ),
                        FabricRecipeProvider.conditionsFromItem( Items.NETHERITE_INGOT ) )
                .offerTo(exporter, recipeId);
    }

    public ShapedRecipeJsonBuilder generatePickaxeRecipe(BetterPickaxeItem item ) {
        return generateToolRecipe(item, item.getHead(), item.getHandle()).pattern("###").pattern(" R ").pattern(" R ");
    }
    public ShapedRecipeJsonBuilder generateAxeRecipe(BetterAxeItem item ) {
        return generateToolRecipe(item, item.getHead(), item.getHandle()).pattern("##").pattern("R#").pattern("R ");
    }
    public ShapedRecipeJsonBuilder generateShovelRecipe(BetterShovelItem item) {
        return generateToolRecipe(item, item.getHead(), item.getHandle()).pattern("#").pattern("R").pattern("R");
    }
    public ShapedRecipeJsonBuilder generateSwordRecipe(BetterSwordItem item ) {
        return generateToolRecipe(item, item.getHead(), item.getHandle()).pattern("#").pattern("#").pattern("R");
    }
    public ShapedRecipeJsonBuilder generateHoeRecipe(BetterHoeItem item) {
        return generateToolRecipe(item, item.getHead(), item.getHandle()).pattern("##").pattern("R ").pattern("R ");
    }



    public ShapedRecipeJsonBuilder generateToolRecipe(Item item, String material, String wood_type ) {
        Optional materialTagOrItem = getMaterial(material);
        TagKey<Item> materialTag = materialTagOrItem.isPresent() && materialTagOrItem.get() instanceof TagKey ? (TagKey<Item>) materialTagOrItem.get() : null;
        Item materialItem = materialTagOrItem.isPresent() && materialTagOrItem.get() instanceof Item ? (Item) materialTagOrItem.get() : null;
        Item tool_rod = TOOL_RODS.get( wood_type );

        ShapedRecipeJsonBuilder builder = ShapedRecipeJsonBuilder.create( RecipeCategory.TOOLS, item );
        if ( materialTag != null ) {
            builder.input( '#', materialTag ).criterion("has_" + material, FabricRecipeProvider.conditionsFromTag( materialTag ));
        }
        else {
            builder.input( '#', materialItem ).criterion( FabricRecipeProvider.hasItem( materialItem ), FabricRecipeProvider.conditionsFromItem( materialItem ));
        }
        return builder.input( 'R', tool_rod ).criterion( FabricRecipeProvider.hasItem(tool_rod ),
                FabricRecipeProvider.conditionsFromItem(tool_rod ) );
    }
}
