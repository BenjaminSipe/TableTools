package org.bsipe.btools;

import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.bsipe.btools.block.entity.ForgeBlockEntity;

public class ModBlockEntityTypes {

    public static final BlockEntityType<ForgeBlockEntity> FORGE_BLOCK_ENTITY = register( "forge_block_entity",
            BlockEntityType.Builder.create( ForgeBlockEntity::new, ModBlocks.DEEPSLATE_FORGE_BLOCK).build());

    public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> type ) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of( BetterToolsModInitializer.MOD_ID, name ), type );
    }




    public static void initialize() {
        ItemStorage.SIDED.registerForBlockEntity(ForgeBlockEntity::getInventoryProvider, FORGE_BLOCK_ENTITY );
    }
}


