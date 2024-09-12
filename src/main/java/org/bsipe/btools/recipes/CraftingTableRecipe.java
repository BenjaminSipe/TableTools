package org.bsipe.btools.recipes;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.bsipe.btools.ModItems;
import org.bsipe.btools.data.*;

public class CraftingTableRecipe implements CraftingRecipe {
    private final ModToolComponent modToolComponent;
    private final ItemStack result;

    private ModToolIngredient headIngredient;
    private ModToolHandle handleIngredient;

    public CraftingTableRecipe(ModToolComponent modToolComponent, ItemStack result) {
        this.modToolComponent = modToolComponent;
        this.result = result;
    }

    @Override public boolean isIgnoredInRecipeBook() {
        return true;
    }
    @Override public boolean showNotification() { return false; }

    @Override
    public boolean matches(CraftingRecipeInput inventory, World world) {
        if ( world.isClient() ) return false;

        return switch(modToolComponent) {
            case AXE_HEAD ->  matchesSize(3, 2, inventory ) && ( matchesLists( ZOT, F, RI, inventory ) || matchesLists( ZOR, I, TF, inventory ) );
            case HOE_HEAD -> matchesSize(3,2, inventory ) && ( matchesLists( ZO, TF, RI, inventory ) || matchesLists( ZO, RI, TF, inventory ) );
            case PICKAXE_HEAD -> matchesSize( 3,3,inventory ) && matchesLists( ZOT, RISG, FE, inventory );
            case SHOVEL_HEAD ->  matchesSize(3,1, inventory ) && matchesLists( Z, N, OT, inventory );
            default /*SWORD_HEAD*/ -> matchesSize(3,1, inventory ) && matchesLists( ZO, N, T, inventory );
        };
    }


    private static final int[]    N = new int[] {};
    private static final int[]    Z = new int[] {0};
    private static final int[]    T = new int[] {2};
    private static final int[]    F = new int[] {4};
    private static final int[]    I = new int[] {5};
    private static final int[]   ZO = new int[] {0,1};
    private static final int[]   OT = new int[] {1,2};
    private static final int[]   TF = new int[] {2,4};
    private static final int[]   RI = new int[] {3,5};
    private static final int[]   FE = new int[] {4,7};
    private static final int[]  ZOT = new int[] {0,1,2};
    private static final int[]  ZOR = new int[] {0,1,3};
    private static final int[] RISG = new int[] {3,5,6,8};

    public boolean matchesSize( int height, int width, CraftingRecipeInput input ) {
        return input.getHeight() == height && input.getWidth() == width;
    }
    public boolean matchesLists(int[] ingredients, int[] air, int[] sticks, CraftingRecipeInput inventory ) {
        headIngredient = ModToolIngredient.get( inventory.getStackInSlot(ingredients[0]), ModToolIngredient.ToolSource.CRAFTING );
        handleIngredient = ModToolHandle.getModToolHandle( inventory.getStackInSlot(sticks[0]));

        if ( headIngredient == null ) return false;
        if ( handleIngredient == null ) return false;

        for ( int stick : sticks ) {
            if ( ! handleIngredient.getIngredient().test( inventory.getStackInSlot(stick) ) ) return false;
        }

        for ( int index : air ) {
            if ( ! Ingredient.empty().test( inventory.getStackInSlot( index  ) ) ) return false;
        }

        for ( int index : ingredients ) {
            if ( ! headIngredient.getIngredient().test( inventory.getStackInSlot( index ) ) ) return false;
        }

        return true;
    }

    @Override
    public ItemStack craft(CraftingRecipeInput inventory, RegistryWrapper.WrapperLookup lookup) {
        return getResult(lookup).copy();
    }

    @Override
    public boolean fits(int width, int height ) {
        if ( height != 3 ) return false;
        return switch( modToolComponent ) {
            case HOE_HEAD, AXE_HEAD -> width == 2;
            case SHOVEL_HEAD, SWORD_BLADE -> width == 1;
            default -> width==3;
        };
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup lookup) {
        if ( headIngredient == null || handleIngredient == null ) return ItemStack.EMPTY;
        DataComponentHelper.addToolComponents( result, headIngredient, handleIngredient, modToolComponent );

        return result;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.ofSize(9, Ingredient.EMPTY);
        Ingredient headIngredient = ModToolIngredient.getAllIngredients(ModToolIngredient.ToolSource.CRAFTING);
        list.set(1, headIngredient );

        list.set(7, Ingredient.ofItems( ModItems.TOOL_HANDLE ) );

        int i = 2; // used to keep the switch-case clean.
        switch (modToolComponent) {
            case SWORD_BLADE:
//                list.set(4, headIngredient.getIngredient() );
                break;
            case AXE_HEAD:
                i+=1;
            case PICKAXE_HEAD:
                list.set( i, headIngredient );
            case HOE_HEAD:
                list.set(0, headIngredient );
            case SHOVEL_HEAD:
                list.set(4, Ingredient.ofItems( ModItems.TOOL_HANDLE ) );
        }
        return list;
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeType.CRAFTING;
    }

    @Override
    public CraftingRecipeCategory getCategory() {
        return CraftingRecipeCategory.EQUIPMENT;
    }

    @Override
    public boolean isEmpty() {
        DefaultedList<Ingredient> defaultedList = this.getIngredients();
        return defaultedList.isEmpty() || defaultedList.stream().filter(ingredient -> !ingredient.isEmpty()).anyMatch(ingredient -> ingredient.getMatchingStacks().length == 0);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }


    public static class Serializer implements RecipeSerializer<CraftingTableRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "btools_crafting";

        public static final MapCodec<CraftingTableRecipe> CODEC = RecordCodecBuilder.mapCodec(
                in -> in.group(
                        ModToolComponent.CODEC.fieldOf( "component" ).forGetter(r->r.modToolComponent),
                        ItemStack.VALIDATED_CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
                ).apply(in, CraftingTableRecipe::new));


        @Override
        public MapCodec<CraftingTableRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, CraftingTableRecipe> packetCodec() {
            return PACKET_CODEC;
        }

        public static final PacketCodec<RegistryByteBuf, CraftingTableRecipe> PACKET_CODEC = PacketCodec.ofStatic(
                CraftingTableRecipe.Serializer::write, CraftingTableRecipe.Serializer::read
        );

        private static CraftingTableRecipe read(RegistryByteBuf buf) {
            ModToolComponent component = ModToolComponent.valueOf( PacketCodecs.STRING.decode( buf ) );
            ItemStack result = ItemStack.PACKET_CODEC.decode( buf );
            return new CraftingTableRecipe( component, result );
        }

        private static void write(RegistryByteBuf buf, CraftingTableRecipe recipe) {
            PacketCodecs.STRING.encode( buf, recipe.modToolComponent.name() );
            ItemStack.PACKET_CODEC.encode( buf, recipe.result );
        }
    }
}
