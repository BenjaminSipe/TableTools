package net.rockgiant.bettertools.recipe;

import com.google.gson.JsonObject;
import net.minecraft.advancement.*;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.rockgiant.bettertools.BetterTools;
import org.jetbrains.annotations.Nullable;

public class BetterToolsCraftingRecipeJsonBuilder {
    private final Ingredient ingredient;
    private final Item result;
    private final RecipeCategory category = RecipeCategory.TOOLS;
    private final AdvancementCriterion<?> criterion;

    private final RecipeSerializer<?> serializer;

    public BetterToolsCraftingRecipeJsonBuilder(RecipeSerializer<?> serializer, Ingredient ingredient, Item result, AdvancementCriterion criterion ) {
        this.ingredient = ingredient;
        this.result = result;
        this.serializer = serializer;
        this.criterion = criterion;
    }

    public static BetterToolsCraftingRecipeJsonBuilder create( Ingredient ingredient, Item result, AdvancementCriterion criterion )
    {
        return new BetterToolsCraftingRecipeJsonBuilder(
                BetterToolsCraftingRecipe.Serializer.INSTANCE,
                ingredient,result, criterion);
    }

    public void offerTo(RecipeExporter exporter, String recipeId)
    {
        this.offerTo(exporter, new Identifier(BetterTools.MOD_ID, recipeId));
    }

    public void offerTo(RecipeExporter exporter, Identifier recipeId) {
            Advancement.Builder builder = exporter.getAdvancementBuilder().criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId)).rewards(AdvancementRewards.Builder.recipe(recipeId)).criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
        builder.criterion("has_ingredient", criterion);
        exporter.accept(new BetterToolsCraftingRecipeJsonBuilder.BetterToolsCraftingRecipeJsonProvider(recipeId, this.serializer,this.ingredient, this.result, builder.build(recipeId.withPrefixedPath("recipes/" + this.category.getName() + "/")) ));
    }

    public record BetterToolsCraftingRecipeJsonProvider(Identifier id, RecipeSerializer<?> serializer, Ingredient ingredient, Item result, AdvancementEntry advancement) implements RecipeJsonProvider
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
            return this.advancement;
        }
    }
}


