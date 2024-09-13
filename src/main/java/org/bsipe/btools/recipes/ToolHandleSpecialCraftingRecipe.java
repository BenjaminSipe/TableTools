package org.bsipe.btools.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;
import org.bsipe.btools.ModItems;
import org.bsipe.btools.data.DataComponentHelper;
import org.bsipe.btools.data.ModToolHandle;

import static org.bsipe.btools.data.ModToolHandle.TOOL_HANDLE_CRAFTING_INGREDIENT_LIST;

public class ToolHandleSpecialCraftingRecipe extends SpecialCraftingRecipe {
    public ToolHandleSpecialCraftingRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {

        if ( input.isEmpty() || input.getStackCount() != 2 || ! fits( input.getWidth(), input.getHeight() ) ) return false;

        ItemStack i1 = input.getStackInSlot(0),i2 = input.getStackInSlot(3) , air1 = input.getStackInSlot(1),air2 = input.getStackInSlot(2), temp;

        if ( i1.isEmpty() ) {
            // breaks every formatting rule, but I think actually makes it more obvious.
            temp = i1;i1 = air1;air1 = i2;i2 = air2;air2 = temp;
        }

        if ( ! air1.isEmpty() || ! air2.isEmpty() || i1.isEmpty() || i2.isEmpty() || ! i1.isOf( i2.getItem() ) ) return false;

        return TOOL_HANDLE_CRAFTING_INGREDIENT_LIST.get( Registries.ITEM.getId( i1.getItem() ) ) != null;
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        // So I know it matches, hence, it is just the form.
        ItemStack ingredient = input.getStackInSlot(0).isEmpty() ? input.getStackInSlot(1) : input.getStackInSlot(0);
        ModToolHandle handleMaterial = TOOL_HANDLE_CRAFTING_INGREDIENT_LIST.get( Registries.ITEM.getId( ingredient.getItem() ) );
        ItemStack toolHandle = ModItems.TOOL_HANDLE.getDefaultStack();
        toolHandle.setCount( 2 );
        DataComponentHelper.addHandleComponents( toolHandle, handleMaterial );
        return toolHandle;
    }

    @Override
    public boolean fits(int width, int height) {
        return width == 2 && height == 2;
    }

    public static RecipeSerializer<ToolHandleSpecialCraftingRecipe> INSTANCE = new SpecialRecipeSerializer<>(ToolHandleSpecialCraftingRecipe::new);

    public static String ID = "tool_handle_crafting_recipe";

    @Override
    public RecipeSerializer<?> getSerializer() {
        return INSTANCE;
    }
}
