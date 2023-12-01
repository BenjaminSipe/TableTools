package net.rockgiant.bettertools.recipe;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.rockgiant.bettertools.BetterTools;

public class ModRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier( BetterTools.MOD_ID, BetterToolsCraftingRecipe.Serializer.ID ),
                BetterToolsCraftingRecipe.Serializer.INSTANCE );
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier( BetterTools.MOD_ID, BetterToolsRepairRecipe.ID ),
                BetterToolsRepairRecipe.TOOLS_REPAIR_SERIALIZER );
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier( BetterTools.MOD_ID, BetterSmithingRecipe.ID ),
                BetterSmithingRecipe.Serializer.INSTANCE);
    }
}
