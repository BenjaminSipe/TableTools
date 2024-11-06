package org.bsipe.btools.block.entity;

import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeInputProvider;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.bsipe.btools.ModBlockEntityTypes;
import org.bsipe.btools.block.ForgeBlock;
import org.bsipe.btools.network.BlockPosPayload;
import org.bsipe.btools.recipes.ForgeAlloyRecipe;
import org.bsipe.btools.recipes.ForgeRecipeInput;
import org.bsipe.btools.screenhandler.ForgeScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

import static org.bsipe.btools.BetterToolsModInitializer.MOD_ID;

public class ForgeInventory extends SimpleInventory {

    public ForgeInventory( int size ) {
        super( size );
    }

    public ForgeInventory(ItemStack... items) {
        super( items );
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        super.setStack( slot, stack );
        if ( slot == 2 ) {
            // do something.
        }
    }
}
