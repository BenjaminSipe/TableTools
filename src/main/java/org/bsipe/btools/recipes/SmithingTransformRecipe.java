package org.bsipe.btools.recipes;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.stream.Stream;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.recipe.input.SmithingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

import org.bsipe.btools.ModItems;
import org.bsipe.btools.data.DataComponentHelper;
import org.bsipe.btools.data.ModToolComponent;
import org.bsipe.btools.data.ModToolIngredient;
import org.bsipe.btools.data.ModToolMaterial;

import static net.minecraft.component.DataComponentTypes.CUSTOM_DATA;

public class SmithingTransformRecipe implements SmithingRecipe {
    final Ingredient template = Ingredient.ofItems( Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE );
    final ModToolComponent component;
    final Ingredient base;

    private ModToolIngredient ingredient;

    private ItemStack result = ModItems.AXE.getDefaultStack();
    private String layer0;

    public SmithingTransformRecipe(ModToolComponent component, Ingredient base) {
        this.component = component;
        this.base = base;
    }

    public boolean matches(SmithingRecipeInput smithingRecipeInput, World world) {

        if ( ( world.isClient() && layer0 != null ) // probably way less efficient. . . but oh well.
                || smithingRecipeInput.getSize() != 3
                || ! this.template.test( smithingRecipeInput.template())
                || ! this.base.test( smithingRecipeInput.base()) ) return false;

        ingredient = ModToolIngredient.get( smithingRecipeInput.addition(), ModToolIngredient.ToolSource.SMITHING );

        if ( ingredient == null ) return false;

        ModToolMaterial material = DataComponentHelper.getMaterial( smithingRecipeInput.base() );

        if ( ! ingredient.getBaseMaterial().equals( material.getId() )) return false;
        layer0 = smithingRecipeInput.base().get( CUSTOM_DATA ).copyNbt().getString( "layer0" );

        result = smithingRecipeInput.base().copy();
        DataComponentHelper.addToolComponents( result, ModToolIngredient.get( smithingRecipeInput.addition() ), layer0, component);

        return true;
    }

    public ItemStack craft(SmithingRecipeInput smithingRecipeInput, RegistryWrapper.WrapperLookup wrapperLookup) {
        result = smithingRecipeInput.base().copy();
        DataComponentHelper.addToolComponents( result, ModToolIngredient.get( smithingRecipeInput.addition() ), layer0, component);

        return this.result;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        // the problem is that this is working from the client side. . .
        result.set(CUSTOM_DATA, DataComponentHelper.getCustomData(ingredient, layer0, component));
        return this.result;
    }

    @Override
    public boolean testTemplate(ItemStack stack) {
        return this.template.test(stack);
    }

    @Override
    public boolean testBase(ItemStack stack) {
        return this.base.test(stack);
    }

    @Override
    public boolean testAddition(ItemStack stack) {
        return ModToolIngredient.get( stack, ModToolIngredient.ToolSource.SMITHING ) != null;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public boolean isEmpty() {
        return Stream.of(this.template, this.base).anyMatch(Ingredient::isEmpty);
    }

    public static class Serializer implements RecipeSerializer<SmithingTransformRecipe> {

        public static final String ID = "btools_smithing";

        public static final SmithingTransformRecipe.Serializer INSTANCE = new Serializer();

        private static final MapCodec<SmithingTransformRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                                ModToolComponent.CODEC.fieldOf( "component" ).forGetter( recipe -> recipe.component ),
                                Ingredient.ALLOW_EMPTY_CODEC.fieldOf("base").forGetter(recipe -> recipe.base)
                        )
                        .apply(instance, SmithingTransformRecipe::new)
        );
        public static final PacketCodec<RegistryByteBuf, SmithingTransformRecipe> PACKET_CODEC = PacketCodec.ofStatic(
                SmithingTransformRecipe.Serializer::write, SmithingTransformRecipe.Serializer::read
        );

        @Override
        public MapCodec<SmithingTransformRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, SmithingTransformRecipe> packetCodec() {
            return PACKET_CODEC;
        }

        private static SmithingTransformRecipe read(RegistryByteBuf buf) {
            ModToolComponent component = ModToolComponent.valueOf( PacketCodecs.STRING.decode( buf ) );
            Ingredient ingredient = Ingredient.PACKET_CODEC.decode(buf);
            return new SmithingTransformRecipe(component, ingredient);
        }

        private static void write(RegistryByteBuf buf, SmithingTransformRecipe recipe) {
            PacketCodecs.STRING.encode( buf, recipe.component.name() );
            Ingredient.PACKET_CODEC.encode( buf, recipe.base );
        }
    }
}
