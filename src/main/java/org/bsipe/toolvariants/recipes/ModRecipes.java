package org.bsipe.toolvariants.recipes;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.bsipe.toolvariants.BensToolVariants;

public class ModRecipes {
    public static void initialize() {
        Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of( BensToolVariants.MOD_ID, ToolVariantCraftingRecipe.Serializer.ID ),
                 ToolVariantCraftingRecipe.Serializer.INSTANCE );

        Registry.register( Registries.RECIPE_SERIALIZER, Identifier.of( BensToolVariants.MOD_ID, SmithingTransformRecipe.Serializer.ID ),
                SmithingTransformRecipe.Serializer.INSTANCE );
    }
}
