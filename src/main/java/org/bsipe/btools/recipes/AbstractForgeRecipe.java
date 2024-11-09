package org.bsipe.btools.recipes;

import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;

public abstract class AbstractForgeRecipe implements Recipe<ForgeRecipeInput> {


    public abstract float getExperience( ForgeRecipeInput input );
    public abstract Ingredient getPrimary();
    public abstract Ingredient getSecondary();
    public abstract int getCookingTime(ForgeRecipeInput input);
    public abstract int getCount(ForgeRecipeInput input);

}
