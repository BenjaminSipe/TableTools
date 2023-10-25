package net.rockgiant.bettertools.util.recipe;

import com.google.gson.JsonObject;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class BetterToolsCraftingRecipeJsonBuilder {
    private final Ingredient ingredient;
    private final Item result;
    private final RecipeCategory category = RecipeCategory.TOOLS;

    private final RecipeSerializer<?> serializer;

    public BetterToolsCraftingRecipeJsonBuilder(RecipeSerializer<?> serializer, Ingredient ingredient, Item result ) {
        this.ingredient = ingredient;
        this.result = result;
        this.serializer = serializer;
    }

    public static BetterToolsCraftingRecipeJsonBuilder create( Ingredient ingredient, Item result )
    {
        return new BetterToolsCraftingRecipeJsonBuilder(
                BetterToolsCraftingRecipe.Serializer.INSTANCE,
                ingredient,result);
    }

    public void offerTo(RecipeExporter exporter, String recipeId)
    {
        this.offerTo(exporter, new Identifier(recipeId));
    }

    public void offerTo(RecipeExporter exporter, Identifier recipeId) {
        exporter.accept(new BetterToolsCraftingRecipeJsonBuilder.BetterToolsCraftingRecipeJsonProvider(recipeId, this.serializer,this.ingredient, this.result ));
    }

    public record BetterToolsCraftingRecipeJsonProvider(Identifier id, RecipeSerializer<?> serializer, Ingredient ingredient, Item result) implements RecipeJsonProvider
    {
        @Override
        public void serialize(JsonObject json) {
            json.add("ingredient", this.ingredient.toJson(true));
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("item", Registries.ITEM.getId(this.result).toString());
            json.add("output", jsonObject);
        }

        @Nullable
        @Override
        public AdvancementEntry advancement() {
            return null;
        }
    }
}


