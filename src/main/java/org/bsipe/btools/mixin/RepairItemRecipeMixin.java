package org.bsipe.btools.mixin;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RepairItemRecipe;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;
import org.bsipe.btools.data.DataComponentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.xml.crypto.Data;
import java.util.function.Predicate;

@Mixin(RepairItemRecipe.class)
public abstract class RepairItemRecipeMixin {
	@Shadow public abstract ItemStack craft(CraftingRecipeInput craftingRecipeInput, RegistryWrapper.WrapperLookup wrapperLookup);

	@Inject( cancellable = true, at = @At("TAIL"), method = "craft(Lnet/minecraft/recipe/input/CraftingRecipeInput;Lnet/minecraft/registry/RegistryWrapper$WrapperLookup;)Lnet/minecraft/item/ItemStack;")
	private void craftOverride(CraftingRecipeInput craftingRecipeInput, RegistryWrapper.WrapperLookup wrapperLookup, CallbackInfoReturnable<ItemStack> cir) {

		ItemStack base = craftingRecipeInput.getStacks().stream().filter( Predicate.not(ItemStack::isEmpty) ).findFirst().orElse(null);
		ItemStack returnValue = cir.getReturnValue();
		if (returnValue == null || base==null || base.get( DataComponentTypes.CUSTOM_DATA).isEmpty()) return;
		DataComponentHelper.copyToolComponents( base, returnValue );
		cir.setReturnValue( returnValue );
	}

	@Inject( cancellable = true, at = @At("TAIL"), method="matches(Lnet/minecraft/recipe/input/CraftingRecipeInput;Lnet/minecraft/world/World;)Z")
	private void canCombineStacksOverride(CraftingRecipeInput craftingRecipeInput, World world, CallbackInfoReturnable<Boolean> cir) {
		if ( cir.getReturnValue() == false ) return;

		ItemStack stack1 = null, stack2 = null;
		for ( ItemStack stack : craftingRecipeInput.getStacks() ) {
			if ( ! stack.isEmpty() && stack1 != null && stack2 == null ) stack2 = stack;
			if ( ! stack.isEmpty() && stack1 == null ) stack1 = stack;
		}
		if ( stack1.get( DataComponentTypes.CUSTOM_DATA ).isEmpty() || stack2.get(DataComponentTypes.CUSTOM_DATA).isEmpty()) return;

		cir.setReturnValue( DataComponentHelper.testToolsMatch( stack1, stack2 ));
	}
}