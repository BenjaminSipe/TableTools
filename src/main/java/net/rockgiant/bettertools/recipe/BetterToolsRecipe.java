package net.rockgiant.bettertools.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.World;

import java.util.List;

class BetterToolsRecipe implements CraftingRecipe {

    private final ItemStack output;
    private final List<Ingredient> recipeItems;

    public BetterToolsRecipe( List<Ingredient> ingredients, ItemStack itemStack ) {
        this.output = itemStack;
        this.recipeItems = ingredients;
    }

    @Override
    public boolean matches(RecipeInputInventory inventory, World world) {
        if ( world.isClient() ) {
            return false;
        }

        // does json item == inventory slot 0 ( top right );
        return recipeItems.get(0).test( inventory.getStack( 0  ) );
    }

    @Override
    public ItemStack craft(RecipeInputInventory inventory, DynamicRegistryManager registryManager) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.ofSize(this.recipeItems.size());
        list.addAll( recipeItems );
        return list;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeType.CRAFTING;
    }

    @Override
    public CraftingRecipeCategory getCategory() {
        return CraftingRecipeCategory.EQUIPMENT;
    }

//    public static class Type implements RecipeType<BetterToolsRecipe> {
//        public static final Type INSTANCE = new Type();
//        public static final String ID = "bettettools_crafting";
//
//    }

    public static class Serializer implements RecipeSerializer<BetterToolsRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "bettertools_crafting";

        public static final Codec<BetterToolsRecipe> CODEC = RecordCodecBuilder.create(
                in -> in.group(
                        validateAmount( Ingredient.DISALLOW_EMPTY_CODEC, 9)
                                .fieldOf("ingredients")
                                .forGetter(BetterToolsRecipe::getIngredients),
                RecipeCodecs.CRAFTING_RESULT
                        .fieldOf("output")
                        .forGetter( r-> r.output )
        ).apply(in, BetterToolsRecipe::new));

        private static Codec<List<Ingredient>> validateAmount(Codec<Ingredient> delegate, int max) {
            return Codecs.validate(
                    Codecs.validate(
                            delegate.listOf(),
                            list -> list.size() > max
                                    ? DataResult.error(() -> "Recipe has too many ingredients!")
                                    : DataResult.success(list)),
                    list -> list.isEmpty()
                            ? DataResult.error(() -> "Recipe has no ingredients!")
                            : DataResult.success(list));
        }

        @Override
        public Codec<BetterToolsRecipe> codec() {
            return CODEC;
        }

        @Override
        public BetterToolsRecipe read(PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize( buf.readInt(), Ingredient.EMPTY );
            for ( int i = 0; i < inputs.size(); i++) {
                inputs.set( i, Ingredient.fromPacket(buf));
            }
            ItemStack output = buf.readItemStack();
            return new BetterToolsRecipe(inputs, output);

        }

        @Override
        public void write(PacketByteBuf buf, BetterToolsRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for ( Ingredient ingredient : recipe.getIngredients()) {
                ingredient.write(buf);
            }
            buf.writeItemStack(recipe.getResult(null));
        }
    }
}