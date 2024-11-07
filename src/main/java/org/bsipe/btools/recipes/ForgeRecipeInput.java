package org.bsipe.btools.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record ForgeRecipeInput(ItemStack primary, ItemStack secondary) implements RecipeInput {
    public ItemStack getStackInSlot( int slot ) {
        return switch (slot) {
            case 0 -> this.primary;
            case 2 -> this.secondary;
            default -> throw new IllegalArgumentException("Recipe does not contain slot " + slot );
        };
    }

    @Override public int getSize() { return 2; }

    @Override
    public boolean isEmpty() {
        return this.primary.isEmpty() && this.secondary.isEmpty();
    }

}
