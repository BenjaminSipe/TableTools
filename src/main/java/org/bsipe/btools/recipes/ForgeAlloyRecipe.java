package org.bsipe.btools.recipes;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.World;

public class ForgeAlloyRecipe extends AbstractForgeRecipe {

    private final Ingredient primary, secondary;
    private final ItemStack result;
    private final int count, cookingTime;
    private final float experience;

    public ForgeAlloyRecipe(
             Ingredient primary,
             Ingredient secondary,
             int count,
             ItemStack result,
             float experience,
             int cookingTime
    ) {
        this.primary = primary;
        this.secondary = secondary;
        this.count = count;
        this.result = result;
        this.cookingTime = cookingTime;
        this.experience = experience;
    }

    @Override
    public boolean matches(ForgeRecipeInput input, World world) {

        // may be an off-by-one issues with the count.
        return ! world.isClient() && primary.test( input.primary() )
                && secondary.test( input.secondary() );
    }

    @Override
    public ItemStack craft(ForgeRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return this.result.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return this.result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.ofSize(2);
        list.add( primary );
        list.add( secondary );
        return list;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeTypes.FORGE;
    }

    @Override
    public float getExperience(ForgeRecipeInput input) {
        return this.experience;
    }

    @Override
    public Ingredient getPrimary(ForgeRecipeInput input) {
        return this.primary;
    }

    @Override
    public Ingredient getSecondary(ForgeRecipeInput input) {
        return this.secondary;
    }

    @Override
    public int getCookingTime(ForgeRecipeInput input) {
        return this.cookingTime;
    }

    @Override
    public int getCount(ForgeRecipeInput input) {
        return this.count;
    }

    public static class Serializer implements RecipeSerializer<ForgeAlloyRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        public static final String ID = "forge_alloying";

        private static final MapCodec<ForgeAlloyRecipe> CODEC = RecordCodecBuilder.mapCodec(in -> in.group(
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf( "primary" ).forGetter( r-> r.primary),
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf( "secondary" ).forGetter( r-> r.secondary ),
                Codecs.POSITIVE_INT.fieldOf( "count").forGetter(r -> r.count ),
                ItemStack.VALIDATED_CODEC.fieldOf( "result" ).forGetter( r -> r.result ),
                Codecs.POSITIVE_FLOAT.fieldOf( "experience" ).forGetter( r -> r.experience ),
                Codecs.POSITIVE_INT.fieldOf( "cookingTime" ).forGetter( r -> r.cookingTime )
        ).apply(in, ForgeAlloyRecipe::new));

        @Override
        public MapCodec<ForgeAlloyRecipe> codec() { return CODEC; }

        @Override
        public PacketCodec<RegistryByteBuf, ForgeAlloyRecipe> packetCodec() {
            return PACKET_CODEC;
        }

        private static final PacketCodec<RegistryByteBuf, ForgeAlloyRecipe> PACKET_CODEC = PacketCodec.ofStatic(
            Serializer::write, Serializer::read );

        private static void write( RegistryByteBuf buf, ForgeAlloyRecipe recipe) {
            Ingredient.PACKET_CODEC.encode( buf, recipe.primary );
            Ingredient.PACKET_CODEC.encode( buf, recipe.secondary );
            PacketCodecs.INTEGER.encode( buf, recipe.count );
            ItemStack.PACKET_CODEC.encode( buf, recipe.result );
            PacketCodecs.FLOAT.encode( buf, recipe.experience );
            PacketCodecs.INTEGER.encode( buf, recipe.cookingTime );
        }

        private static ForgeAlloyRecipe read( RegistryByteBuf buf) {
            Ingredient primary = Ingredient.PACKET_CODEC.decode( buf );
            Ingredient secondary = Ingredient.PACKET_CODEC.decode( buf );
            Integer count = PacketCodecs.INTEGER.decode( buf );
            ItemStack result = ItemStack.PACKET_CODEC.decode( buf );
            Float experience = PacketCodecs.FLOAT.decode( buf );
            Integer cookingTime = PacketCodecs.INTEGER.decode( buf );

            return new ForgeAlloyRecipe( primary, secondary, count, result, experience, cookingTime );
        }
    }
}
