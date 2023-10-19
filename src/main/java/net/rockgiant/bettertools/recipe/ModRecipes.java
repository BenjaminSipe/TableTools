package net.rockgiant.bettertools.recipe;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.rockgiant.bettertools.BetterTools;

public class ModRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier( BetterTools.MOD_ID, BetterToolsRecipe.Serializer.ID ),
                BetterToolsRecipe.Serializer.INSTANCE );
//        Registry.register( Registries.RECIPE_TYPE, new Identifier( BetterTools.MOD_ID, BetterToolsRecipe.Type.ID ),
//                BetterToolsRecipe.Type.INSTANCE );
    }
}
