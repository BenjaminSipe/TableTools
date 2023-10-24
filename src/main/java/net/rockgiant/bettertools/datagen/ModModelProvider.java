package net.rockgiant.bettertools.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.rockgiant.bettertools.BetterTools;
import net.rockgiant.bettertools.item.ModItems;

import static net.rockgiant.bettertools.item.ModItems.*;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        for ( Item tool_rod : TINTED_TOOL_RODS ) {
            Models.GENERATED.upload(ModelIds.getItemModelId(tool_rod), TextureMap.layer0(new Identifier(BetterTools.MOD_ID, "item/tinted_tool_rod")), itemModelGenerator.writer);
        }
    }

}
