package org.bsipe.toolvariants.enums;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.*;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.EncoderException;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.StringIdentifiable;
import org.bsipe.toolvariants.recipes.ToolVariantCraftingRecipe;

import java.util.stream.Stream;

public enum ComponentEnum implements StringIdentifiable {
    SHOVEL_HEAD( "shovel_head", HandleEnum.SHOVEL_HANDLE ),
    AXE_HEAD( "axe_head", HandleEnum.BASIC_HANDLE ),
    HOE_HEAD( "hoe_head", HandleEnum.BASIC_HANDLE  ),
    PICKAXE_HEAD( "pickaxe_head", HandleEnum.BASIC_HANDLE  ),
    SWORD_BLADE( "sword_head", HandleEnum.SWORD_HILT );

    ComponentEnum( String suffix, HandleEnum handleType ) {
        this.suffix = suffix;
        this.handleType = handleType;
    }

    String suffix;
    HandleEnum handleType;


    public HandleEnum getHandleType() {
        return handleType;
    }
    @Override
    public String asString() {
        return name();
    }

    public static final StringIdentifiable.EnumCodec<ComponentEnum> CODEC = StringIdentifiable.createCodec(() -> ComponentEnum.values());

}
