package net.rockgiant.bettertools.util.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.rockgiant.bettertools.item.ModItemTags;
import net.rockgiant.bettertools.item.TintedToolRodItem;
import net.rockgiant.bettertools.item.tools.*;

import static net.rockgiant.bettertools.util.ToolGenerationUtils.*;

class BetterToolsCraftingRecipe implements CraftingRecipe {

    private final ItemStack output;
    private final Ingredient recipeItem;

    private int tint = 0;
    private int headTint = 0;

    public BetterToolsCraftingRecipe(Ingredient ingredient, ItemStack itemStack) {
        this.output = itemStack;
        this.recipeItem = ingredient;
    }

    @Override
    public boolean matches(RecipeInputInventory inventory, World world) {
        if ( world.isClient() ) {
            return false;
        }

//        if ( output.getItem() instanceof BetterPickaxeItem )
        if ( Ingredient.fromTag( ModItemTags.BETTER_PICKAXES ).test( output ) )
            return matchesLists( new int[] {0,1,2}, new int[] {3,5,6,8}, new int[] {4,7}, inventory );
        if ( Ingredient.fromTag( ModItemTags.BETTER_SWORDS ).test( output ) )
            return matchesLists( new int[] {0,3}, new int[] {1,2,4,5,7,8}, new int[] {6}, inventory )
                || matchesLists( new int[] {1,4}, new int[] {0,2,3,5,6,8}, new int[] {7}, inventory )
                || matchesLists( new int[] {2,5}, new int[] {0,1,3,4,6,7}, new int[] {8}, inventory );
        if ( Ingredient.fromTag( ModItemTags.BETTER_AXES ).test( output ) )
            return matchesLists( new int[] {0,1,4}, new int[] {2,5,7,8}, new int[] {3,6}, inventory )
                || matchesLists( new int[] {0,1,3}, new int[] {2,5,6,8}, new int[] {4,7}, inventory )
                || matchesLists( new int[] {1,2,5}, new int[] {0,3,6,8}, new int[] {4,7}, inventory )
                || matchesLists( new int[] {1,2,6}, new int[] {0,3,4,7}, new int[] {5,8}, inventory );
        if ( Ingredient.fromTag( ModItemTags.BETTER_SHOVELS ).test( output ) )
            return matchesLists( new int[] {0}, new int[] {1,2,4,5,7,8}, new int[] {3,6}, inventory )
                || matchesLists( new int[] {1}, new int[] {0,2,3,5,6,8}, new int[] {4,7}, inventory )
                || matchesLists( new int[] {2}, new int[] {0,1,3,4,6,7}, new int[] {5,8}, inventory );
        if ( Ingredient.fromTag( ModItemTags.BETTER_HOES ).test( output ) )
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
        if ( Ingredient.fromTag( ModItemTags.BETTER_AXES ).test( output ) ) return width >= 2 && height >= 3;
        if ( Ingredient.fromTag( ModItemTags.BETTER_SWORDS ).test( output ) ) return height >= 3;
        if ( Ingredient.fromTag( ModItemTags.BETTER_SHOVELS ).test( output ) ) return height >= 3;
        if ( Ingredient.fromTag( ModItemTags.BETTER_PICKAXES ).test( output ) ) return width >= 3 && height >= 3;
        if ( Ingredient.fromTag( ModItemTags.BETTER_HOES ).test( output ) ) return width >= 2 && height >= 3;
        return false;
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        if ( this.tint != 0 ) {
            addTint( output, this.tint );
        }
        if ( this.headTint != 0 ) {
            addHeadTint( output, this.headTint );
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
            if ( ! Ingredient.fromTag( ModItemTags.BETTER_TOOL_RODS ).test( inventory.getStack(stick) ) ) return false;
        }

        for ( int index : ingredients ) {
            if ( ! recipeItem.test( inventory.getStack( index  ) ) ) return false;
        }

        for ( int index : air ) {
            if ( ! Ingredient.empty().test( inventory.getStack( index  ) ) ) return false;
        }

        this.tint = ((TintedToolRodItem) inventory.getStack( sticks[0] ).getItem()).getTint();

        if ( Ingredient.fromTag(ItemTags.PLANKS ).equals( recipeItem ) ) {
            this.headTint = getWoodTint(inventory.getStack(ingredients[0]).getItem());
        }
        return true;
    }

    public static class Serializer implements RecipeSerializer<BetterToolsCraftingRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "bettertools_crafting";

        public static final Codec<BetterToolsCraftingRecipe> CODEC = RecordCodecBuilder.create(

                in -> in.group(
                        Ingredient.DISALLOW_EMPTY_CODEC
                                .fieldOf("ingredient")
                                .forGetter(r->r.recipeItem),
                RecipeCodecs.CRAFTING_RESULT
                        .fieldOf("output")
                        .forGetter( r-> r.output )
                ).apply(in, BetterToolsCraftingRecipe::new));


        @Override
        public Codec<BetterToolsCraftingRecipe> codec() {
            return CODEC;
        }

        @Override
        public BetterToolsCraftingRecipe read(PacketByteBuf buf) {
            Ingredient i = Ingredient.fromPacket(buf);

            ItemStack output = buf.readItemStack();
            return new BetterToolsCraftingRecipe(i, output);
        }

        @Override
        public void write(PacketByteBuf buf, BetterToolsCraftingRecipe recipe) {
            recipe.recipeItem.write( buf );
            buf.writeItemStack(recipe.getResult(null));
        }
    }
}