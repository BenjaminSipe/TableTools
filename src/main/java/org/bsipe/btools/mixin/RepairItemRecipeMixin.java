package org.bsipe.btools.mixin;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RepairItemRecipe;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RepairItemRecipe.class)
public abstract class RepairItemRecipeMixin {
	@Shadow public abstract ItemStack craft(CraftingRecipeInput craftingRecipeInput, RegistryWrapper.WrapperLookup wrapperLookup);

	@Inject( cancellable = true, at = @At("TAIL"), method = "craft(Lnet/minecraft/recipe/input/CraftingRecipeInput;Lnet/minecraft/registry/RegistryWrapper$WrapperLookup;)Lnet/minecraft/item/ItemStack;")
	private void craftOverride(CraftingRecipeInput craftingRecipeInput, RegistryWrapper.WrapperLookup wrapperLookup, CallbackInfoReturnable<ItemStack> cir) {
		NbtComponent customData = null;
		ItemStack returnValue = cir.getReturnValue();
		for ( int i = 0 ; i < craftingRecipeInput.getStackCount() && customData == null; i ++ ) {
			ItemStack stack = craftingRecipeInput.getStackInSlot(i);
			if (stack.isEmpty()) continue;
			customData = stack.get( DataComponentTypes.CUSTOM_DATA );
		}

		if ( returnValue != null && customData != null && ! customData.isEmpty() ) {
			returnValue.set( DataComponentTypes.CUSTOM_DATA, customData );
			cir.setReturnValue( returnValue );
		}

	}
}