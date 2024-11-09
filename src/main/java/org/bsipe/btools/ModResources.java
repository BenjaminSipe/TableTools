package org.bsipe.btools;

import com.google.gson.Gson;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.bsipe.btools.data.ModToolHandle;
import org.bsipe.btools.data.ModToolIngredient;
import org.bsipe.btools.data.ModToolMaterial;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
                ModToolIngredient modToolIngredient = new Gson().fromJson( new InputStreamReader( stream, "UTF-8" ), ModToolIngredient.class );
                if ( modToolIngredient.validate() ) {
                    ModToolIngredient.addEntry( modToolIngredient );
                } else {
                    LOGGER.error( "Validation error following parsing tool ingredient {}.", id.toString());
                }
            } catch(Exception e) {
                LOGGER.error( "An error has occurred,", e );
            }
        }
        LOGGER.info( "Loaded {} ingredients", ModToolIngredient.getCount() );
    }

    public static void reloadHandles( ResourceManager manager ) {
        ArrayList<ModToolHandle> handles = new ArrayList<>();
        for(Identifier id : manager.findResources("btools/handle", path -> path.getPath().endsWith("json")).keySet()) {
            try(InputStream stream = manager.getResource(id).get().getInputStream()) {
                ModToolHandle handle =  new Gson().fromJson( new InputStreamReader( stream, "UTF-8" ), ModToolHandle.class );
                if ( handle.validate() ) {
                    ModToolHandle.addEntry( handle );
                } else {
                    LOGGER.error( "Validation error following parsing handle {}.", id.toString());
                }
            } catch(Exception e) {
                LOGGER.error( "An exception has occurred,", e );
            }
        }
        LOGGER.info( "Loaded {} handles", handles.size() );
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
                        reloadHandles( manager );
                    }
                }
        );
    }
}
