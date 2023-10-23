package net.rockgiant.bettertools.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
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
import net.rockgiant.bettertools.item.TintedToolRodItem;
import net.rockgiant.bettertools.item.tools.*;

import java.util.List;

import static net.rockgiant.bettertools.util.ToolGenerationUtils.addTint;

class BetterToolsRecipe implements CraftingRecipe {

    private final ItemStack output;
    private final Ingredient recipeItem;

    private int tint = 0;

    public BetterToolsRecipe( Ingredient ingredient, ItemStack itemStack) {
        this.output = itemStack;
        this.recipeItem = ingredient;
    }
    @Override
    public boolean matches(RecipeInputInventory inventory, World world) {
        if ( world.isClient() ) {
            return false;
        }

        if ( output.getItem() instanceof BetterPickaxeItem )
            return matchesLists( new int[] {0,1,2}, new int[] {3,5,6,8}, new int[] {4,7}, inventory );
        if ( output.getItem() instanceof BetterSwordItem )
            return matchesLists( new int[] {0,3}, new int[] {1,2,4,5,7,8}, new int[] {6}, inventory )
                || matchesLists( new int[] {1,4}, new int[] {0,2,3,5,6,8}, new int[] {7}, inventory )
                || matchesLists( new int[] {2,5}, new int[] {0,1,3,4,6,7}, new int[] {8}, inventory );
        if ( output.getItem() instanceof BetterAxeItem )
            return matchesLists( new int[] {0,1,4}, new int[] {2,5,7,8}, new int[] {3,6}, inventory )
                || matchesLists( new int[] {0,1,3}, new int[] {2,5,6,8}, new int[] {4,7}, inventory )
                || matchesLists( new int[] {1,2,5}, new int[] {0,3,6,8}, new int[] {4,7}, inventory )
                || matchesLists( new int[] {1,2,6}, new int[] {0,3,4,7}, new int[] {5,8}, inventory );
        if ( output.getItem() instanceof BetterShovelItem )
            return matchesLists( new int[] {0}, new int[] {1,2,4,5,7,8}, new int[] {3,6}, inventory )
                || matchesLists( new int[] {1}, new int[] {0,2,3,5,6,8}, new int[] {4,7}, inventory )
                || matchesLists( new int[] {2}, new int[] {0,1,3,4,6,7}, new int[] {5,8}, inventory );
        if ( output.getItem() instanceof BetterHoeItem )
            return matchesLists( new int[] {0,1}, new int[] {2,4,5,7,8}, new int[] {3,6}, inventory )
                || matchesLists( new int[] {0,1}, new int[] {2,3,5,6,8}, new int[] {4,7}, inventory )
                || matchesLists( new int[] {1,2}, new int[] {0,3,5,6,8}, new int[] {4,7}, inventory )
                || matchesLists( new int[] {1,2}, new int[] {0,3,4,6,7}, new int[] {5,8}, inventory );
        return false; // UNREACHABLE
    }


    @Override
    public ItemStack craft(RecipeInputInventory inventory, DynamicRegistryManager registryManager) {
        return getResult(registryManager).copy();
    }

    @Override
    public boolean fits(int width, int height) {
        if ( output.getItem() instanceof BetterAxeItem ) return width >= 2 && height >= 3;
        if ( output.getItem() instanceof BetterSwordItem ) return height >= 3;
        if ( output.getItem() instanceof BetterShovelItem ) return height >= 3;
        if ( output.getItem() instanceof BetterPickaxeItem ) return width >= 3 && height >= 3;
        if ( output.getItem() instanceof BetterHoeItem ) return width >= 2 && height >= 3;
        return false;
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        if ( this.tint != 0 ) {
            addTint( output, this.tint );
        }
        return output;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.ofSize(1);
        list.add( recipeItem );
        return list;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeType.CRAFTING;
    }

    @Override
    public CraftingRecipeCategory getCategory() {
        return CraftingRecipeCategory.EQUIPMENT;
    }

    public boolean matchesLists(int[] ingredients, int[] air, int[] sticks, Inventory inventory ) {
        for ( int stick : sticks ) {
            if ( ! (inventory.getStack( stick ).getItem() instanceof TintedToolRodItem) ) return false;
        }

        for ( int index : ingredients ) {
            if ( ! recipeItem.test( inventory.getStack( index  ) ) ) return false;
        }

        for ( int index : air ) {
            if ( ! Ingredient.empty().test( inventory.getStack( index  ) ) ) return false;
        }

        this.tint = ((TintedToolRodItem) inventory.getStack( sticks[0] ).getItem()).getTint();

        return true;
    }

    public static class Serializer implements RecipeSerializer<BetterToolsRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "bettertools_crafting";

        public static final Codec<BetterToolsRecipe> CODEC = RecordCodecBuilder.create(

                in -> in.group(
                        Ingredient.DISALLOW_EMPTY_CODEC
                                .fieldOf("ingredient")
                                .forGetter(r->r.recipeItem),
                RecipeCodecs.CRAFTING_RESULT
                        .fieldOf("output")
                        .forGetter( r-> r.output )
                ).apply(in, BetterToolsRecipe::new));

//        private static Codec<List<Ingredient>> validateAmount(Codec<Ingredient> delegate, int max) {
//            return Codecs.validate(
//                    Codecs.validate(
//                            delegate.listOf(),
//                            list -> list.size() > max
//                                    ? DataResult.error(() -> "Recipe has too many ingredients!")
//                                    : DataResult.success(list)),
//                    list -> list.isEmpty()
//                            ? DataResult.error(() -> "Recipe has no ingredients!")
//                            : DataResult.success(list));
//        }

        @Override
        public Codec<BetterToolsRecipe> codec() {
            return CODEC;
        }

        @Override
        public BetterToolsRecipe read(PacketByteBuf buf) {
//            DefaultedList<Ingredient> inputs = DefaultedList.ofSize( buf.readInt(), Ingredient.EMPTY );
//            for ( int i = 0; i < inputs.size(); i++) {
                Ingredient i = Ingredient.fromPacket(buf);
//            }


            ItemStack output = buf.readItemStack();
            return new BetterToolsRecipe(i, output);
        }

        @Override
        public void write(PacketByteBuf buf, BetterToolsRecipe recipe) {
//            buf.writeInt(recipe.getIngredients().size());
//            for ( Ingredient ingredient : recipe.getIngredients()) {
//                ingredient.write(buf);
//            }
            recipe.recipeItem.write( buf );
            buf.writeItemStack(recipe.getResult(null));
        }
    }
}