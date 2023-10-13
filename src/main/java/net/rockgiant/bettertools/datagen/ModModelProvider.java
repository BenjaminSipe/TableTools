package net.rockgiant.bettertools.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.rockgiant.bettertools.BetterTools;
import net.rockgiant.bettertools.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    public static final String TOOL_ROD_PREFIX = "_tool_rod";
    public static final String TOOL_HEAD_PREFIX = "_head";

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        generateBasicItemModels(itemModelGenerator);

    }

    public void addTwoLayerModel(ItemModelGenerator img, Item item, Item layer0Item, Item layer1Item )
    {
        addTwoLayerModel(img, ModelIds.getItemModelId(item),TextureMap.getId(layer0Item), TextureMap.getId(layer1Item));
    }

    public void addTwoLayerModel(ItemModelGenerator img, Identifier id0, Identifier id1, Identifier id2 )
    {
        Models.GENERATED_TWO_LAYERS.upload( id0, TextureMap.layered(id1, id2), img.writer );
    }

    public void generateBasicItemModels(ItemModelGenerator img) {
        for ( String tool_type : ModItems.TOOL_TYPES ) {
            for (String wood_type : ModItems.WOOD_TYPES ) {
                Identifier id1;
                switch( tool_type )
                {
                    case "shovel":
                        id1 = new Identifier(BetterTools.MOD_ID, "item/tool_rod/shovel_handle/" + wood_type + "_shovel_handle");
                        break;
                    case "sword":
                        id1 = new Identifier(BetterTools.MOD_ID, "item/tool_rod/sword_handle/" + wood_type + "_sword_handle");
                        break;
                    default:
                        id1 = new Identifier(BetterTools.MOD_ID, "item/tool_rod/" + wood_type + "_tool_rod");
                        break;
                }
                for ( String material_type : ModItems.TOOL_MATERIALS ) {
                    Identifier id0 = new Identifier(BetterTools.MOD_ID , "item/" + wood_type + "_" + material_type + "_" + tool_type );
                    Identifier id2 = new Identifier(BetterTools.MOD_ID ,"item/" + material_type + "_tool_head/" + material_type + "_" + tool_type + "_head" );
                    addTwoLayerModel( img, id0, id1, id2 );
                }
            }
        }
    }
}
