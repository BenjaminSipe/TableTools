package net.rockgiant.bettertools.util.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.rockgiant.bettertools.item.ModItemTags;
import net.rockgiant.bettertools.item.TintedToolRodItem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static net.rockgiant.bettertools.util.ToolGenerationUtils.*;

public class BetterToolsRepairRecipe extends SpecialCraftingRecipe {


//    private final ItemStack item;
//    private final Ingredient recipeItem;

    private ItemStack output;

    public BetterToolsRepairRecipe(CraftingRecipeCategory category ) {
        super(category);
    }

    // I think I may not need a real crafting recipe here.

//    private int tint = 0;
//    private int headTint = 0;
//    private String handle_tooltip = "";
/*
if (itemStack2.isDamageable() && itemStack2.getItem().canRepair(itemStack, itemStack3)) {
                int m;
                int l = Math.min(itemStack2.getDamage(), itemStack2.getMaxDamage() / 4);
                if (l <= 0) {
                    this.output.setStack(0, ItemStack.EMPTY);
                    this.levelCost.set(0);
                    return;
                }
                for (m = 0; l > 0 && m < itemStack3.getCount(); ++m) {
                    int n = itemStack2.getDamage() - l;
                    itemStack2.setDamage(n);
                    ++i;
                    l = Math.min(itemStack2.getDamage(), itemStack2.getMaxDamage() / 4);
                }
                this.repairItemUsage = m;
            }



                    if (!itemStack2.isEmpty()) {
            int t = itemStack2.getRepairCost();
            if (!itemStack3.isEmpty() && t < itemStack3.getRepairCost()) {
                t = itemStack3.getRepairCost();
            }
            if (k != i || k == 0) {
                t = AnvilScreenHandler.getNextCost(t);
            }
            itemStack2.setRepairCost(t);
        }

 */

    @Override
    public boolean matches(RecipeInputInventory inventory, World world) {
        if ( world.isClient() ) {
            return false;
        }
        ItemStack tool = null;
        List<ItemStack> repairIngredients = new ArrayList<>();
        // basically I just need to check if
        for (int i = 0; i < inventory.getWidth(); ++i) {
            for (int j = 0; j < inventory.getHeight(); ++j) {
                ItemStack itemStack = inventory.getStack(i + j * inventory.getWidth());

                if ( itemStack.isDamageable()
                        // ONLY works on my tools for now.
                        && Ingredient.fromTag(ModItemTags.BETTER_TOOLS).test(itemStack)
                ) {
                    if ( tool != null ) {
                        return false;
                    } else {
                        tool = itemStack;
                    }
                }
                else
                {
                    if ( ! itemStack.isEmpty() )
                    {
                        repairIngredients.add( itemStack );
                    }
                }
            }
        }
        if ( tool == null || repairIngredients.isEmpty() || repairIngredients.size() > 3 ) return false;
        for( ItemStack i : repairIngredients ) if ( ! tool.getItem().canRepair(tool, i ) ) return false;

        // I need to set the output, and then we're good.
        this.output = tool.copy();
        output.removeSubNbt("Enchantments");


        int maxRepairAmount = tool.getDamage();
        int singleItemRepairValue = getSingleItemRepairValue(tool);
        int totalAmountRepaired = Math.min( singleItemRepairValue * repairIngredients.size(), maxRepairAmount );
        int toolDurability = Math.max( 0, maxRepairAmount - totalAmountRepaired );

        output.setDamage(toolDurability);


        return true;


    }

    private int getSingleItemRepairValue(ItemStack tool) {
        return tool.getMaxDamage() / getMaxRepairCount(tool);
    }


    @Override
    public ItemStack craft(RecipeInputInventory inventory, DynamicRegistryManager registryManager) {
        return getResult(registryManager).copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return TOOLS_REPAIR_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeType.CRAFTING;
    }

    @Override
    public CraftingRecipeCategory getCategory() {
        return CraftingRecipeCategory.EQUIPMENT;
    }

    public static final String ID = "bettertools_repair";

    public static final RecipeSerializer<BetterToolsRepairRecipe> TOOLS_REPAIR_SERIALIZER = new SpecialRecipeSerializer<BetterToolsRepairRecipe>(BetterToolsRepairRecipe::new);

}