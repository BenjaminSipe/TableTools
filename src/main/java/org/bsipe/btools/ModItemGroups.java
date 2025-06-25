package org.bsipe.btools;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static org.bsipe.btools.BetterToolsModInitializer.MOD_ID;

public class ModItemGroups {
    public static void initialize() {}

    public static final ItemGroup TABLE_TOOLS =
            Registry.register(Registries.ITEM_GROUP, Identifier.of( MOD_ID, "table_tools_creative_tab" ),  FabricItemGroup.builder()
            .icon(() -> ModItems.RAW_PALADUS.getDefaultStack())
            .displayName( Text.translatable( "itemGroup.btools.tools" ))
            .entries(( context, entries ) -> {
                entries.add( ModItems.RAW_PALADUS );
            })
            .build());
}
