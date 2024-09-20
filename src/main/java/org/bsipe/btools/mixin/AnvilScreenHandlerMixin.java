package org.bsipe.btools.mixin;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.AnvilScreenHandler;

import org.bsipe.btools.BetterToolsModInitializer;
import org.bsipe.btools.data.DataComponentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin( AnvilScreenHandler.class )
public abstract class AnvilScreenHandlerMixin {

    private ItemStack itemStack1;

    @ModifyVariable(method = "updateResult", at = @At("STORE"), ordinal = 0)
    private ItemStack updateInput1(ItemStack itemStack1 ) {
        this.itemStack1 = itemStack1;

        return itemStack1;
    }

    @ModifyVariable(method = "updateResult", at = @At("STORE"), ordinal = 2)
    private ItemStack updateInput2(ItemStack itemStack2 ) {
        if ( itemStack1.isEmpty() || itemStack2.isEmpty()) return itemStack2;

        if ( ! DataComponentHelper.testToolsMatch( itemStack1, itemStack2 ) && DataComponentHelper.isBetterTool( itemStack2 ) ) return ItemStack.EMPTY;

        return itemStack2;
    }
}
