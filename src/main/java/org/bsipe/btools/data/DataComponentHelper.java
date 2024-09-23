package org.bsipe.btools.data;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtString;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;
import org.bsipe.btools.ModItems;

import java.util.List;
import java.util.Map;

import static org.bsipe.btools.ModItems.*;
import static org.bsipe.btools.data.ModToolComponent.*;

public class DataComponentHelper {
    public static NbtComponent getCustomData(ModToolIngredient modToolIngredient, ModToolHandle toolHandle, ModToolComponent component ) {

        String layer1 = modToolIngredient.path + component.suffix;
        String layer0 = toolHandle.getHandleSprite( component.handleReference );

        NbtCompound compound = new NbtCompound();

        compound.put( "layer0", NbtString.of( layer0 ) );
        compound.put( "layer1", NbtString.of( layer1 ) );
        compound.put( "material", NbtString.of( modToolIngredient.modToolMaterial.getId().toString() ) );
        compound.put( "handle-id", NbtString.of( toolHandle.getId().toString() ) );
        return NbtComponent.of( compound );
    }

    public static int getMaxDamage(ModToolIngredient modToolIngredient, ModToolHandle modToolHandle) {
        return modToolHandle.modifyDurability( modToolIngredient.getDurability() );
    }

    public static AttributeModifiersComponent getAttributeModifiers(ModToolComponent component, ModToolIngredient ingredient, ModToolHandle toolHandle ) {
        return AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(
                Item.BASE_ATTACK_DAMAGE_MODIFIER_ID, getDamage( component, ingredient, toolHandle),
                EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND ).add(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(
                Item.BASE_ATTACK_SPEED_MODIFIER_ID, component.getAttackSpeed(),
                EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND).build();
    }

    public static ToolComponent getTool(ModToolComponent component, ModToolIngredient ingredient, ModToolHandle toolHandle ) {
        return SWORD_BLADE.equals( component ) ? SWORD_TOOL_COMPONENT : new ToolComponent(
                List.of(ToolComponent.Rule.ofNeverDropping(ingredient.getInverseTag()), ToolComponent.Rule.ofAlwaysDropping(component.getEffectiveBlock(), toolHandle.modifyMiningSpeed( ingredient.getMiningSpeedMultiplier()))), 1.0F, 1
        );
    }

    public static void addHandleComponents( ItemStack result, ModToolHandle handleMaterial ) {
        String layer0 = handleMaterial.getSprite();
        NbtCompound compound = new NbtCompound();
        compound.put( "layer0", NbtString.of( layer0 ) );
        compound.put( "handle-id", NbtString.of( handleMaterial.getId().toString() ) );
        result.set( DataComponentTypes.CUSTOM_DATA, NbtComponent.of( compound ) );
        result.set( DataComponentTypes.ITEM_NAME, getItemName( result , handleMaterial.getId().toString() ) );
    }

    public static void addToolComponents(ItemStack result, ModToolIngredient modToolIngredient, ModToolHandle toolHandle, ModToolComponent component ) {
        result.set( DataComponentTypes.ITEM_NAME, getItemName( result , modToolIngredient.getId() ) );
        result.set( DataComponentTypes.CUSTOM_DATA, getCustomData( modToolIngredient, toolHandle, component ) );
        result.set( DataComponentTypes.MAX_DAMAGE, getMaxDamage(modToolIngredient, toolHandle ) );
        result.set( DataComponentTypes.FIRE_RESISTANT, modToolIngredient.isFireResistent() ? Unit.INSTANCE : null );
        result.set( DataComponentTypes.ATTRIBUTE_MODIFIERS, getAttributeModifiers( component, modToolIngredient, toolHandle ));
        result.set( DataComponentTypes.TOOL, getTool(component, modToolIngredient, toolHandle) );
    }

    public static void copyToolComponents( ItemStack base, ItemStack result ) {
        result.set( DataComponentTypes.ITEM_NAME, base.get( DataComponentTypes.ITEM_NAME) );
        result.set( DataComponentTypes.CUSTOM_DATA, base.get( DataComponentTypes.CUSTOM_DATA) );
        result.set( DataComponentTypes.MAX_DAMAGE, base.get( DataComponentTypes.MAX_DAMAGE) );
        if ( base.get( DataComponentTypes.FIRE_RESISTANT ) != null ) result.set( DataComponentTypes.FIRE_RESISTANT, Unit.INSTANCE );
        result.set( DataComponentTypes.ATTRIBUTE_MODIFIERS, base.get( DataComponentTypes.ATTRIBUTE_MODIFIERS) );
        result.set( DataComponentTypes.TOOL, base.get( DataComponentTypes.TOOL) );
    }

    public static Text getItemName( ItemStack item, String id ) {
        String formattedId = id.replace(':', '.').replace( '/','.');
        return Text.translatable( item.getTranslationKey() , Text.translatable( formattedId ) );
    }

    public static boolean testToolsMatch( ItemStack one, ItemStack two ) {
        NbtComponent c1 = one.get( DataComponentTypes.CUSTOM_DATA), c2 = two.get( DataComponentTypes.CUSTOM_DATA);
        return ! ( c1 == null || c2 == null || c1.isEmpty() || c2.isEmpty() )
                && c1.copyNbt().getString( "material" ).equals( c2.copyNbt().getString("material" ) );

    }

    public static final ToolComponent SWORD_TOOL_COMPONENT = new ToolComponent(
            List.of(ToolComponent.Rule.ofAlwaysDropping(List.of(Blocks.COBWEB), 15.0F), ToolComponent.Rule.of(BlockTags.SWORD_EFFICIENT, 1.5F)), 1.0F, 2
            );

    public static ModToolMaterial getMaterial( ItemStack item ) {
        if ( item.get( DataComponentTypes.CUSTOM_DATA).isEmpty()) return null;
        return ModToolMaterial.MATERIAL_LIST.get( Identifier.of(item.get(DataComponentTypes.CUSTOM_DATA).copyNbt().getString("material")));
    }

    public static boolean isBetterTool( ItemStack itemStack ) {
        return itemStack.isOf( AXE )
                || itemStack.isOf( HOE )
                || itemStack.isOf( SHOVEL )
                || itemStack.isOf( SWORD )
                || itemStack.isOf( PICKAXE );
    }

    public static boolean canRepair( ItemStack item, ItemStack ingredient ) {
        ModToolMaterial material = getMaterial( item );
        return ModToolIngredient.getIngredientsForBaseMaterial( material ).test( ingredient );
    }

    // 7 9 9 9 10
    // 4 5 6 7 8
    // W S I D N
    /*
    Hard to explain, but basically,
    base value of an axe is 7.
     */
    private final static float[] AXE_DAMAGE_BRAKETS = { 5, 5, 8, 10 };



    public static float getDamage(ModToolComponent component, ModToolIngredient ingredient, ModToolHandle toolHandle)
    {
        float damage = ingredient.getDamage();

        damage = switch( component ) {
            case HOE_HEAD -> 1;
            case AXE_HEAD -> {
                float damageOverride = 7;
                for ( float f : AXE_DAMAGE_BRAKETS ) {
                    if ( f <= damage ) damageOverride++;
                }
                yield damageOverride;
            }
            case PICKAXE_HEAD -> damage - 2;
            case SHOVEL_HEAD -> damage - 1.5f;
            default -> damage;
        };
        damage = toolHandle.modifyDamage( damage );

        // attack damage gets added to base punch damage of one.
        return damage - 1;
    }


}
