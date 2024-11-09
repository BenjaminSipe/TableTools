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
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.World;
import org.bsipe.btools.data.*;

import static net.minecraft.component.DataComponentTypes.CUSTOM_DATA;

public class ForgeInfusionRecipe extends AbstractForgeRecipe {
    public final Ingredient secondary;
    public final ItemStack result;
    public final ModToolComponent primary;

    public ForgeInfusionRecipe(
            ModToolComponent primary,
            Ingredient secondary,
            ItemStack result
    ) {
        this.primary = primary;
        this.secondary = secondary;
        this.result = result;
    }

    @Override
    public boolean matches(ForgeRecipeInput input, World world) {
        if ( world.isClient()) return false;
        if ( input.primary().isEmpty() || input.secondary().isEmpty()) return false;
        if ( ! result.isOf( input.primary().getItem())) return false;

        ModToolIngredient ingredient = ModToolIngredient.get( input.secondary(), ModToolIngredient.ToolSource.ALLOYING );

        if ( ingredient == null ) return false;

        ModToolMaterial material = DataComponentHelper.getMaterial( input.primary() );

        if ( ! ingredient.getBaseMaterial().equals( material.getId() )) return false;

        return true;
    }

    @Override
    public ItemStack craft(ForgeRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        ItemStack itemStack = getResult( lookup );
        ModToolIngredient ingredient = ModToolIngredient.get( input.secondary(), ModToolIngredient.ToolSource.ALLOYING );
        ModToolHandle toolHandle = ModToolHandle.TOOL_HANDLE_LIST_BY_ID.get( Identifier.of( input.primary().get( CUSTOM_DATA ).copyNbt().getString( "handle-id" ) ) );

        DataComponentHelper.addToolComponents( itemStack, ingredient, toolHandle, primary);

        return itemStack;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return this.result.copy();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeTypes.FORGE;
    }

    @Override
    public float getExperience(ForgeRecipeInput input) {
        ModToolIngredient ingredient = ModToolIngredient.get( input.secondary(), ModToolIngredient.ToolSource.ALLOYING );
//        return ingredient == null ? 0 : ingredient.getExperience();
        return 1f;
    }

    @Override
    public Ingredient getPrimary() {
        return Ingredient.ofStacks( this.result );
    }

    @Override
    public Ingredient getSecondary() {
        return this.secondary;
    }

    @Override
    public int getCookingTime(ForgeRecipeInput input) {
        ModToolIngredient ingredient = ModToolIngredient.get( input.secondary(), ModToolIngredient.ToolSource.ALLOYING );
//        return ingredient == null ? 0 : ingredient.getCookingTime();
        return 200;
    }

    @Override
    public int getCount(ForgeRecipeInput input) {
        ModToolIngredient ingredient = ModToolIngredient.get( input.secondary(), ModToolIngredient.ToolSource.ALLOYING );
//        return ingredient == null ? 0 : ingredient.getAlloyCount();
        return 4;
    }

    public static class Serializer implements RecipeSerializer<ForgeInfusionRecipe> {
        public static final ForgeInfusionRecipe.Serializer INSTANCE = new ForgeInfusionRecipe.Serializer();

        public static final String ID = "forge_infusion";

        private static final MapCodec<ForgeInfusionRecipe> CODEC = RecordCodecBuilder.mapCodec(in -> in.group(
                ModToolComponent.CODEC.fieldOf( "primary" ).forGetter(r->r.primary),
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf( "secondary" ).forGetter( r-> r.secondary ),
                ItemStack.VALIDATED_CODEC.fieldOf( "result" ).forGetter( r -> r.result )
        ).apply(in, ForgeInfusionRecipe::new));

        @Override
        public MapCodec<ForgeInfusionRecipe> codec() { return CODEC; }

        @Override
        public PacketCodec<RegistryByteBuf, ForgeInfusionRecipe> packetCodec() {
            return PACKET_CODEC;
        }

        private static final PacketCodec<RegistryByteBuf, ForgeInfusionRecipe> PACKET_CODEC = PacketCodec.ofStatic(
                ForgeInfusionRecipe.Serializer::write, ForgeInfusionRecipe.Serializer::read );

        private static void write( RegistryByteBuf buf, ForgeInfusionRecipe recipe) {
            ModToolComponent.PACKET_CODEC.encode( buf, recipe.primary );
            Ingredient.PACKET_CODEC.encode( buf, recipe.secondary );
            ItemStack.PACKET_CODEC.encode( buf, recipe.result );
        }

        private static ForgeInfusionRecipe read( RegistryByteBuf buf) {
            ModToolComponent primary = ModToolComponent.PACKET_CODEC.decode( buf );
            Ingredient secondary = Ingredient.PACKET_CODEC.decode( buf );
            ItemStack result = ItemStack.PACKET_CODEC.decode( buf );

            return new ForgeInfusionRecipe( primary, secondary, result );
        }
    }
}
