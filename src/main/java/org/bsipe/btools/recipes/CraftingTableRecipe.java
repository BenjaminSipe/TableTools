package org.bsipe.btools.recipes;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtString;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.bsipe.btools.data.*;

public class CraftingTableRecipe implements CraftingRecipe {
    private final ToolComponent toolComponent;
    private final Ingredient headIngredient;
    private final Ingredient handleIngredient;
    private final ItemStack result;


    private ItemStack headItem;
    private ItemStack handleItem;

    public CraftingTableRecipe(ToolComponent toolComponent, Ingredient headIngredient, Ingredient handleIngredient, ItemStack result) {
        this.toolComponent = toolComponent;
        this.result = result;
        this.headIngredient = headIngredient;
        this.handleIngredient = handleIngredient;
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return false;
    }
    @Override
    public boolean matches(CraftingRecipeInput inventory, World world) {
        if ( world.isClient() ) return false;

        return switch( toolComponent ) {
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
        headItem = inventory.getStackInSlot(ingredients[0]);
        handleItem = inventory.getStackInSlot(sticks[0]);

        for ( int stick : sticks ) {
            if ( ! handleIngredient.test( inventory.getStackInSlot(stick) ) ) return false;
        }

        for ( int index : air ) {
            if ( ! Ingredient.empty().test( inventory.getStackInSlot( index  ) ) ) return false;
        }

        for ( int index : ingredients ) {
            if ( ! headIngredient.test( inventory.getStackInSlot( index  ) ) ) return false;
            if ( ! Ingredient.ofStacks( headItem ).test( inventory.getStackInSlot( index ) ) ) return false;
        }

        return true;
    }

    @Override
    public ItemStack craft(CraftingRecipeInput inventory, RegistryWrapper.WrapperLookup lookup) {
        return getResult(lookup).copy();
    }

    @Override
    public boolean fits(int width, int height ) {
        return height >= 3;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup lookup) {

//        String layer0 = Material.getSpriteText( handleItem, tool )

        String layer1 = Material.getSpriteText( headItem, toolComponent );
        String layer0 = Material.getSpriteText( handleItem, toolComponent.getHandleReference() );

        System.out.println( layer1 );
        System.out.println( layer0 );

        NbtCompound compound = new NbtCompound();

        compound.put( "layer0", NbtString.of( layer0 ) );
        compound.put( "layer1", NbtString.of( layer1 ) );

        result.set( DataComponentTypes.CUSTOM_DATA, NbtComponent.of( compound ) );

//        result.set( DataComponentTypes.MAX_DAMAGE, 500 );
//        ToolComponent tc = result.get( DataComponentTypes.TOOL );
//        result.set( DataComponentTypes.TOOL, createComponent());

        return result;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.ofSize(9, Ingredient.EMPTY);
        list.set(1, headIngredient );

        list.set(7, handleIngredient );

        int i = 2; // used to keep the switch-case clean.
        switch ( toolComponent ) {
            case SWORD_BLADE:
                list.set(4, headIngredient );
                break;
            case AXE_HEAD:
                i+=1;
            case PICKAXE_HEAD:
                list.set( i, headIngredient );
            case HOE_HEAD:
                list.set(0, headIngredient );
            case SHOVEL_HEAD:
                list.set(4, handleIngredient );
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
                        ToolComponent.CODEC.fieldOf( "toolComponent" ).forGetter( r->r.toolComponent),
                        Ingredient.DISALLOW_EMPTY_CODEC
                                .fieldOf("headIngredient")
                                .forGetter(r->r.headIngredient),
                        Ingredient.DISALLOW_EMPTY_CODEC
                                .fieldOf("handleIngredient")
                                .forGetter(r->r.handleIngredient),
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
            ToolComponent component = ToolComponent.valueOf( PacketCodecs.STRING.decode( buf ) );
            Ingredient headIngredient = Ingredient.PACKET_CODEC.decode( buf );
            Ingredient handleIngredient = Ingredient.PACKET_CODEC.decode( buf );
            ItemStack result = ItemStack.PACKET_CODEC.decode( buf );
            return new CraftingTableRecipe( component, headIngredient, handleIngredient, result );
        }

        private static void write(RegistryByteBuf buf, CraftingTableRecipe recipe) {
            PacketCodecs.STRING.encode( buf, recipe.toolComponent.name() );

            Ingredient.PACKET_CODEC.encode( buf, recipe.headIngredient );
            Ingredient.PACKET_CODEC.encode( buf, recipe.handleIngredient );
            ItemStack.PACKET_CODEC.encode( buf, recipe.result );
        }
    }


//    ToolComponent createComponent() {
//        return new ToolComponent(
//                List.of(ToolComponent.Rule.ofNeverDropping(BlockTags.INCORRECT_FOR_IRON_TOOL), ToolComponent.Rule.ofAlwaysDropping(BlockTags.PICKAXE_MINEABLE, 100f)), 1.0F, 1
//        );
//    }
}
