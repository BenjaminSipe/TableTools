package org.bsipe.btools;

import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.bsipe.btools.data.ModToolHandle;
import org.bsipe.btools.data.ModToolIngredient;
import org.bsipe.btools.data.ModToolMaterial;

public class ModRegistries {

    public static RegistryKey<Registry<ModToolMaterial>> MATERIAL_REGISTRY = RegistryKey.ofRegistry( Identifier.of( BetterToolsModInitializer.MOD_ID, "material" ));
    public static RegistryKey<Registry<ModToolHandle>> HANDLE_REGISTRY = RegistryKey.ofRegistry( Identifier.of( BetterToolsModInitializer.MOD_ID, "handle" ));
    public static RegistryKey<Registry<ModToolIngredient>> INGREDIENT_REGISTRY = RegistryKey.ofRegistry( Identifier.of( BetterToolsModInitializer.MOD_ID, "ingredient" ));

    public static void initialize() {
        DynamicRegistries.registerSynced( MATERIAL_REGISTRY, ModToolMaterial.CODEC, DynamicRegistries.SyncOption.SKIP_WHEN_EMPTY );
        DynamicRegistries.registerSynced( HANDLE_REGISTRY, ModToolHandle.CODEC, DynamicRegistries.SyncOption.SKIP_WHEN_EMPTY );
        DynamicRegistries.registerSynced( INGREDIENT_REGISTRY, ModToolIngredient.CODEC, DynamicRegistries.SyncOption.SKIP_WHEN_EMPTY );
    }



}
