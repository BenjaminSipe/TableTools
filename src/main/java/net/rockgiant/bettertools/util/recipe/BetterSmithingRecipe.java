package net.rockgiant.bettertools.util.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import java.util.Map;
import java.util.stream.Stream;

public class BetterSmithingRecipe implements SmithingRecipe {

    public static final String ID = "better_smithing_recipe";

    private final Ingredient template;
    private final Ingredient base;
    private final Ingredient addition;
    private final Enchantment enchantment;


    ItemStack output = null;

    public BetterSmithingRecipe(Ingredient template, Ingredient base, Ingredient addition, String enchantment )
    {
        this.template = template;
        this.base = base;
        this.addition = addition;
        this.enchantment = Registries.ENCHANTMENT.get(new Identifier( enchantment));
    }

    @Override
    public boolean testTemplate(ItemStack stack) {
        return this.template.test( stack );
    }

    @Override
    public boolean testBase(ItemStack stack) {
        return this.base.test( stack );
    }

    @Override
    public boolean testAddition(ItemStack stack) {
        return this.addition.test( stack );
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        if ( world.isClient() ) return false;
        if ( this.template.test(inventory.getStack(0)) && this.base.test(inventory.getStack(1)) && this.addition.test(inventory.getStack(2)) ) {
            this.output = inventory.getStack(1).copy();
            int level = EnchantmentHelper.getLevel( enchantment, output) + 1;
            Map<Enchantment, Integer> enchantments = EnchantmentHelper.get( this.output);
            if (enchantment.getMaxLevel() < level ) return false;
            if (enchantments.getOrDefault(enchantment, -1 ) == -1 && ! EnchantmentHelper.isCompatible(enchantments.keySet(), enchantment )) return false;
            enchantments.put(enchantment, level );
            EnchantmentHelper.set( enchantments, output );
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager) {
        return this.output.copy();
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return this.output == null ? ItemStack.EMPTY : this.output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public boolean isEmpty() {
        return Stream.of(this.template, this.base, this.addition).anyMatch(Ingredient::isEmpty);
    }

    public static class Serializer
            implements RecipeSerializer<BetterSmithingRecipe> {

        public static final Serializer INSTANCE = new Serializer();
//        private static final Codec<BetterSmithingRecipe> CODEC = RecordCodecBuilder.create(instance -> instance.group(((MapCodec)Ingredient.ALLOW_EMPTY_CODEC.fieldOf("template")).forGetter(recipe -> recipe.template), ((MapCodec)Ingredient.ALLOW_EMPTY_CODEC.fieldOf("base")).forGetter(recipe -> recipe.base), ((MapCodec)Ingredient.ALLOW_EMPTY_CODEC.fieldOf("addition")).forGetter(recipe -> recipe.addition), ((MapCodec)RecipeCodecs.CRAFTING_RESULT.fieldOf("result")).forGetter(recipe -> recipe.result)).apply((Applicative<SmithingTransformRecipe, ?>)instance, SmithingTransformRecipe::new));
        private static final Codec<BetterSmithingRecipe> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        Ingredient.DISALLOW_EMPTY_CODEC
                                .fieldOf("template")
                                .forGetter(r->r.template),
                        Ingredient.DISALLOW_EMPTY_CODEC
                                .fieldOf("base")
                                .forGetter(r->r.base),
                        Ingredient.DISALLOW_EMPTY_CODEC
                                .fieldOf("addition")
                                .forGetter(r->r.addition),
                        Codec.STRING.fieldOf("enchantment").forGetter(r-> EnchantmentHelper.getEnchantmentId(r.getEnchantment()).toString())
                ).apply( instance, BetterSmithingRecipe::new)
            );

        @Override
        public Codec<BetterSmithingRecipe> codec() {
            return CODEC;
        }

        @Override
        public BetterSmithingRecipe read(PacketByteBuf packetByteBuf) {
            Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient2 = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient3 = Ingredient.fromPacket(packetByteBuf);
            String enchantment = packetByteBuf.readString();

            return new BetterSmithingRecipe(ingredient, ingredient2, ingredient3, enchantment);
        }

        @Override
        public void write(PacketByteBuf packetByteBuf, BetterSmithingRecipe smithingTransformRecipe) {
            smithingTransformRecipe.template.write(packetByteBuf);
            smithingTransformRecipe.base.write(packetByteBuf);
            smithingTransformRecipe.addition.write(packetByteBuf);
            packetByteBuf.writeString(EnchantmentHelper.getEnchantmentId(smithingTransformRecipe.enchantment).toString());
        }
    }


    public Ingredient getTemplate() {
        return template;
    }

    public Ingredient getBase() {
        return base;
    }

    public Ingredient getAddition() {
        return addition;
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }

    @Override
    public RecipeType getType() {
        return RecipeType.SMITHING;
    }
}

