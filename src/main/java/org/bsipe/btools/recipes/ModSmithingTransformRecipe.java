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
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import org.bsipe.btools.ModComponents;
import org.bsipe.btools.data.*;

public class ModSmithingTransformRecipe implements SmithingRecipe {
    final Ingredient template = Ingredient.ofItems( Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE );
    final Ingredient base;
    // no "addition" because that is driven by the material system.
    final ModToolComponent component;
    private ItemStack result;

    public ModSmithingTransformRecipe(ModToolComponent component, ItemStack result) {
        this.component = component;
        this.base = Ingredient.ofStacks( result );
        this.result = result;
    }

    @Override public boolean isIgnoredInRecipeBook() {
        return true;
    }
    @Override public boolean showNotification() { return false; }


    public boolean matches(SmithingRecipeInput smithingRecipeInput, World world) {

        if ( world.isClient() // probably way less efficient. . . but oh well.
            || smithingRecipeInput.getSize() != 3
            || ! this.template.test( smithingRecipeInput.template())
            || ! this.base.test( smithingRecipeInput.base()) ) return false;

        ModToolIngredient ingredient = ModToolIngredient.get( smithingRecipeInput.addition(), ModToolIngredient.ToolSource.SMITHING );

        if ( ingredient == null ) return false;

        ModToolMaterial material = DataComponentHelper.getMaterial( smithingRecipeInput.base() );

        if ( ! ingredient.getBaseMaterial().equals( material.getId() )) return false;
//        ModToolHandle toolHandle = ModToolHandle.TOOL_HANDLE_LIST_BY_ID.get( smithingRecipeInput.base().get( CUSTOM_DATA ).copyNbt().get( "handle-id" ) );
//
//        result = smithingRecipeInput.base().copy();
//        DataComponentHelper.addToolComponents( result, ingredient, toolHandle, component);

        return true;
    }

    public ItemStack craft(SmithingRecipeInput smithingRecipeInput, RegistryWrapper.WrapperLookup wrapperLookup) {
        ItemStack itemStack = getResult( wrapperLookup );
        ModToolIngredient ingredient = ModToolIngredient.get( smithingRecipeInput.addition(), ModToolIngredient.ToolSource.SMITHING );
        ModToolHandle toolHandle = ModToolHandle.getRegistry().get( Identifier.of( smithingRecipeInput.base().get(ModComponents.TOOL_RENDER_COMPONENT ).handleId() ) );

        DataComponentHelper.addToolComponents( itemStack, ingredient, toolHandle, component);

        return itemStack;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return this.result.copy();
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

    public static class Serializer implements RecipeSerializer<ModSmithingTransformRecipe> {

        public static final ModSmithingTransformRecipe.Serializer INSTANCE = new ModSmithingTransformRecipe.Serializer();
        public static final String ID = "btools_smithing";

        public static final MapCodec<ModSmithingTransformRecipe> CODEC = RecordCodecBuilder.mapCodec(
                in -> in.group(
                        ModToolComponent.CODEC.fieldOf( "component" ).forGetter(r->r.component),
                        ItemStack.VALIDATED_CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
                ).apply(in, ModSmithingTransformRecipe::new));


        @Override
        public MapCodec<ModSmithingTransformRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, ModSmithingTransformRecipe> packetCodec() {
            return PACKET_CODEC;
        }

        public static final PacketCodec<RegistryByteBuf, ModSmithingTransformRecipe> PACKET_CODEC = PacketCodec.ofStatic(
                ModSmithingTransformRecipe.Serializer::write, ModSmithingTransformRecipe.Serializer::read
        );

        private static ModSmithingTransformRecipe read(RegistryByteBuf buf) {
            ModToolComponent component = ModToolComponent.valueOf( PacketCodecs.STRING.decode( buf ) );
            ItemStack result = ItemStack.PACKET_CODEC.decode( buf );
            return new ModSmithingTransformRecipe( component, result );
        }

        private static void write(RegistryByteBuf buf, ModSmithingTransformRecipe recipe) {
            PacketCodecs.STRING.encode( buf, recipe.component.name() );
            ItemStack.PACKET_CODEC.encode( buf, recipe.result );
        }
    }
}
