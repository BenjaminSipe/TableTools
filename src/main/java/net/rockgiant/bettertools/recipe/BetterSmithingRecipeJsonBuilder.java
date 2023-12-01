package net.rockgiant.bettertools.recipe;

        import com.google.gson.JsonObject;
        import net.minecraft.advancement.*;
        import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
        import net.minecraft.data.server.recipe.RecipeExporter;
        import net.minecraft.data.server.recipe.RecipeJsonProvider;
        import net.minecraft.enchantment.Enchantment;
        import net.minecraft.enchantment.EnchantmentHelper;
        import net.minecraft.recipe.Ingredient;
        import net.minecraft.recipe.RecipeSerializer;
        import net.minecraft.recipe.book.RecipeCategory;
        import net.minecraft.util.Identifier;
        import net.rockgiant.bettertools.BetterTools;
        import org.jetbrains.annotations.Nullable;

public class BetterSmithingRecipeJsonBuilder {
    private final Ingredient template;
    private final Ingredient base;
    private final Ingredient addition;
    private final Enchantment enchantment;
    private final RecipeCategory category = RecipeCategory.TOOLS;
    private final AdvancementCriterion<?> criterion;

    private final RecipeSerializer<?> serializer;

    public BetterSmithingRecipeJsonBuilder(RecipeSerializer<?> serializer, Ingredient template, Ingredient base, Ingredient addition, Enchantment enchantment, AdvancementCriterion criterion ) {
        this.template = template;
        this.base = base;
        this.addition = addition;
        this.enchantment = enchantment;
        this.serializer = serializer;
        this.criterion = criterion;
    }

    public static BetterSmithingRecipeJsonBuilder create( Ingredient template, Ingredient base, Ingredient addition, Enchantment enchantment, AdvancementCriterion criterion )
    {
        return new BetterSmithingRecipeJsonBuilder(BetterSmithingRecipe.Serializer.INSTANCE, template, base, addition, enchantment, criterion);
    }

    public void offerTo(RecipeExporter exporter, String recipeId)
    {
        this.offerTo(exporter, new Identifier(BetterTools.MOD_ID, recipeId));
    }

    public void offerTo(RecipeExporter exporter, Identifier recipeId) {
        Advancement.Builder builder = exporter.getAdvancementBuilder().criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId)).rewards(AdvancementRewards.Builder.recipe(recipeId)).criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
        builder.criterion("has_ingredient", criterion);
        exporter.accept(new BetterSmithingRecipeJsonBuilder.BetterSmithingRecipeJsonProvider(recipeId, this.serializer,this.template, this.base, this.addition, this.enchantment, builder.build(recipeId.withPrefixedPath("recipes/" + this.category.getName() + "/")) ));
    }

    public record BetterSmithingRecipeJsonProvider(Identifier id, RecipeSerializer<?> serializer, Ingredient template, Ingredient base, Ingredient addition, Enchantment enchantment, AdvancementEntry advancement) implements RecipeJsonProvider
    {
        @Override
        public void serialize(JsonObject json) {
            json.add("template", this.template.toJson(false));
            json.add("base", this.base.toJson(false));
            json.add("addition", this.addition.toJson(false));
            json.addProperty( "enchantment", EnchantmentHelper.getEnchantmentId(enchantment).toString() );
        }

        @Nullable
        @Override
        public AdvancementEntry advancement() {
            return this.advancement;
        }
    }
}


