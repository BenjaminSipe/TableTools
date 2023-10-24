package net.rockgiant.bettertools.util.recipe;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.rockgiant.bettertools.BetterTools;

public class ModRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier( BetterTools.MOD_ID, BetterToolsCraftingRecipe.Serializer.ID ),
                BetterToolsCraftingRecipe.Serializer.INSTANCE );
    }
}
