package org.bsipe.btools;

import com.google.gson.Gson;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.bsipe.btools.data.ModToolIngredient;
import org.bsipe.btools.data.ModToolMaterial;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.bsipe.btools.BetterToolsModInitializer.LOGGER;

public class ModResources {


    public static void reloadMaterials( ResourceManager manager ) {
        ModToolMaterial.clearList();
        for(Identifier id : manager.findResources("btools/material", path -> path.getPath().endsWith("json")).keySet()) {
            try(InputStream stream = manager.getResource(id).get().getInputStream()) {
                ModToolMaterial.addEntry( new ModToolMaterial( new Gson().fromJson( new InputStreamReader( stream, "UTF-8" ), ModToolMaterial.Material.class ) ) );
            } catch(Exception e) {
                LOGGER.error( "Error has occurred loading " + id + ",", e );
            }
        }
        LOGGER.info( "Loaded {} materials", ModToolMaterial.getCount() );
    }

    public static void reloadIngredients( ResourceManager manager ) {
        ModToolIngredient.clearList();
        for(Identifier id : manager.findResources("btools/ingredient", path -> path.getPath().endsWith("json")).keySet()) {
            try(InputStream stream = manager.getResource(id).get().getInputStream()) {
                ModToolIngredient.addEntry( new ModToolIngredient( new Gson().fromJson( new InputStreamReader( stream, "UTF-8" ), ModToolIngredient.ToolIngredient.class ) ) );
            } catch(Exception e) {
                LOGGER.error( "Error has occurred,", e );
            }
        }
        LOGGER.info( "Loaded {} ingredients", ModToolIngredient.getCount() );
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
                        reloadMaterials( manager );
                        reloadIngredients( manager );
                    }
                }
        );
    }
}
