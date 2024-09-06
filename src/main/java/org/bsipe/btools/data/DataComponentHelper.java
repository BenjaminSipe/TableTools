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

import java.util.List;
import java.util.Map;

import static org.bsipe.btools.data.ModToolComponent.AXE_HEAD;
import static org.bsipe.btools.data.ModToolComponent.SWORD_BLADE;

public class DataComponentHelper {

    public static Map< TagKey<Block>, Integer > AXE_DAMAGE_MAP =
            Map.of(
                    BlockTags.INCORRECT_FOR_GOLD_TOOL, 6,
                    BlockTags.INCORRECT_FOR_WOODEN_TOOL, 6,
                    BlockTags.INCORRECT_FOR_STONE_TOOL, 8,
                    BlockTags.INCORRECT_FOR_IRON_TOOL, 8,
                    BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 8,
                    BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 9 );
    public static Map< TagKey<Block>, Integer > GRADIENT_DAMAGE_MAP =
            Map.of(
                    BlockTags.INCORRECT_FOR_GOLD_TOOL, 1,
                    BlockTags.INCORRECT_FOR_WOODEN_TOOL, 1,
                    BlockTags.INCORRECT_FOR_STONE_TOOL, 2,
                    BlockTags.INCORRECT_FOR_IRON_TOOL, 3,
                    BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 4,
                    BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 5 );

    public static NbtComponent getCustomData(ModToolIngredient modToolIngredient, String handleSprite, ModToolComponent component ) {

        String layer1 = modToolIngredient.path + component.suffix;
        String layer0 = handleSprite;

        NbtCompound compound = new NbtCompound();

        compound.put( "layer0", NbtString.of( layer0 ) );
        compound.put( "layer1", NbtString.of( layer1 ) );
        compound.put( "material", NbtString.of( modToolIngredient.modToolMaterial.getId().toString() ) );
        return NbtComponent.of( compound );
    }

    public static int getMaxDamage(ModToolIngredient modToolIngredient ) {
        return modToolIngredient.getDurability();
    }

    public static AttributeModifiersComponent getAttributeModifiers( ModToolComponent component, ModToolIngredient ingredient ) {


        float damage = 0;
        if ( AXE_HEAD.equals( component ) ) {
            damage = AXE_DAMAGE_MAP.get( ingredient.getInverseTag() );
        } else if ( ModToolComponent.isGradient( component ) ) {
            damage = ModToolComponent.getBaseDamage( component ) + GRADIENT_DAMAGE_MAP.get( ingredient.getInverseTag() );
        }

        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                Item.BASE_ATTACK_DAMAGE_MODIFIER_ID, damage + ingredient.getDamage(), EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(Item.BASE_ATTACK_SPEED_MODIFIER_ID, component.getAttackSpeed(), EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    public static ToolComponent getTool(ModToolComponent component, ModToolIngredient ingredient ) {
        return SWORD_BLADE.equals( component ) ? SWORD_TOOL_COMPONENT : new ToolComponent(
                List.of(ToolComponent.Rule.ofNeverDropping(ingredient.getInverseTag()), ToolComponent.Rule.ofAlwaysDropping(component.getEffectiveBlock(), ingredient.getMiningSpeedMultiplier())), 1.0F, 1
        );
    }

    public static void addToolComponents(ItemStack result, ModToolIngredient modToolIngredient, ItemStack handleItem, ModToolComponent component ) {
        addToolComponents( result, modToolIngredient, ModToolHandleMaterial.getSpriteText( handleItem, component.getHandleReference() ), component);
    }

    public static void addToolComponents(ItemStack result, ModToolIngredient modToolIngredient, String handleItemSprite, ModToolComponent component ) {


        result.set( DataComponentTypes.ITEM_NAME, Text.translatable( result.getTranslationKey() , Text.translatable( modToolIngredient.getId() ) ) );
        result.set( DataComponentTypes.CUSTOM_DATA, getCustomData( modToolIngredient, handleItemSprite, component ) );
        result.set( DataComponentTypes.MAX_DAMAGE, getMaxDamage(modToolIngredient) );
        result.set( DataComponentTypes.FIRE_RESISTANT, modToolIngredient.isFireResistent() ? Unit.INSTANCE : null );
        result.set( DataComponentTypes.ATTRIBUTE_MODIFIERS, getAttributeModifiers( component, modToolIngredient ));
        result.set( DataComponentTypes.TOOL, getTool(component, modToolIngredient) );
    }

    public static void copyToolComponents( ItemStack base, ItemStack result ) {
        result.set( DataComponentTypes.ITEM_NAME, base.get( DataComponentTypes.ITEM_NAME) );
        result.set( DataComponentTypes.CUSTOM_DATA, base.get( DataComponentTypes.CUSTOM_DATA) );
        result.set( DataComponentTypes.MAX_DAMAGE, base.get( DataComponentTypes.MAX_DAMAGE) );
        if ( base.get( DataComponentTypes.FIRE_RESISTANT ) != null ) result.set( DataComponentTypes.FIRE_RESISTANT, Unit.INSTANCE );
        result.set( DataComponentTypes.ATTRIBUTE_MODIFIERS, base.get( DataComponentTypes.ATTRIBUTE_MODIFIERS) );
        result.set( DataComponentTypes.TOOL, base.get( DataComponentTypes.TOOL) );
    }

    public static boolean testToolsMatch( ItemStack one, ItemStack two ) {
        if ( one.get( DataComponentTypes.CUSTOM_DATA).isEmpty() || two.get( DataComponentTypes.CUSTOM_DATA).isEmpty()) return false;
        return one.get(DataComponentTypes.CUSTOM_DATA).getNbt().getString("material").equals(
                two.get(DataComponentTypes.CUSTOM_DATA).getNbt().getString("material")
        );
    }
    
    public static final ToolComponent SWORD_TOOL_COMPONENT = new ToolComponent(
            List.of(ToolComponent.Rule.ofAlwaysDropping(List.of(Blocks.COBWEB), 15.0F), ToolComponent.Rule.of(BlockTags.SWORD_EFFICIENT, 1.5F)), 1.0F, 2
            );

    public static ModToolMaterial getMaterial( ItemStack item ) {
        if ( item.get( DataComponentTypes.CUSTOM_DATA).isEmpty()) return null;
        return ModToolMaterial.MATERIAL_LIST.get( Identifier.of(item.get(DataComponentTypes.CUSTOM_DATA).copyNbt().getString("material")));
    }

}
