package org.bsipe.btools;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.bsipe.btools.data.ModToolHandle;
import org.bsipe.btools.data.ModToolIngredient;
import org.bsipe.btools.data.ModToolMaterial;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static org.bsipe.btools.BetterToolsModInitializer.LOGGER;
import static org.bsipe.btools.BetterToolsModInitializer.MOD_ID;

public class ModResources {

    public static final RegistryKey<ItemGroup> ITEM_GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of( MOD_ID, "table_tools_creative_tab"));

    public static void addToolsToCreativeTab() {
        ItemGroupEvents.modifyEntriesEvent( ITEM_GROUP_KEY).register(content -> {
            content.addAll( ModToolHandle.getToolHandles() );
            content.addAll( ModToolIngredient.getAllTools(), ItemGroup.StackVisibility.PARENT_TAB_ONLY );
            content.addAll( ModToolIngredient.getOakTools(), ItemGroup.StackVisibility.SEARCH_TAB_ONLY );
        } );
    }

    public static void initialize() {
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(
                new SimpleSynchronousResourceReloadListener() {

                    @Override
                    public Identifier getFabricId() {
                        return Identifier.of( "btools","resource_loader");
                    }

                    @Override
                    public void reload(ResourceManager manager) {
                        addToolsToCreativeTab();
                    }
                }
        );
    }
}
