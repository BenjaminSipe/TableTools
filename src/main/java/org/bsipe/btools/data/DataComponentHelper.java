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

    public static NbtComponent getCustomData(ItemStack head, ItemStack handle, ModToolComponent component, ToolMaterial material ) {
        String layer1 = ModToolHeadMaterial.getSpriteText( head, component);
        String layer0 = ModToolHandleMaterial.getSpriteText( handle, component.getHandleReference() );

        NbtCompound compound = new NbtCompound();

        compound.put( "layer0", NbtString.of( layer0 ) );
        compound.put( "layer1", NbtString.of( layer1 ) );
        compound.put( "material", NbtString.of( ((ModToolHeadMaterial) material ).getGroupId() ) );
        return NbtComponent.of( compound );
    }

    public static int getMaxDamage(ToolMaterial material ) {
        return material.getDurability();
    }

    /**
     * Damage and attack speed are slightly simplified from vanilla tools for simplicity.
     * For damage, all tools have a base attack damage of 1.
     * For a Hoe, damage stays at one.
     * For Pickaxes, Shovels, and Swords. . . a base value is added to a gradient based on the mining tier of the tool
     * For Axes, there is a 3 tier system. . . low (wood + gold ), mid( stone, iron, diamond), and high(netherite)
     * EXAMPLE: Stone axe: damageVar = 0. . . axe_damage_map( stone_tool ) returns 8.
     * Which is added to the base damage of 1 by the AttributeModifiersComponent.Builder to give a total damage of 9. . .
     *
     * For attack speed, some simplification was used. . .
     * In vanilla, Pickaxes, Shovels, and Swords all have constant attack speed.
     * The attack speed of a Hoe uses a gradient going from wood ( attack speed matches other wood tools )
     * to netherite ( attack speed matches a non weapon item ). . .
     * This could be useful, except hoes only ever do 1 damage, so scaling attack speed becomes rather meaningless.
     * For axes, the attack speed hovers around 3 with iron and stone having slightly longer attack speeds (3.1/3.2)
     * to compensate for doing as much damage as a diamond axe. . .
     * Again, unless you are very precise with your timings, this .1 second won't matter. . .
     *
     * Hence, to simplify, attack speed for tools is based on tool type only.
     *
     * @param component used to know what tool is being used, and what damage and attack speed are used.
     * @param material used to calculate damage based on tool tier
     * @return AttributeModifiersComponent in a normal tool, this controls the damage and attack speed when attacking in main hand. . .
     */
    public static AttributeModifiersComponent getAttributeModifiers( ModToolComponent component, ToolMaterial material ) {


        float damage = 0;
        if ( AXE_HEAD.equals( component ) ) {
            damage = AXE_DAMAGE_MAP.get( material.getInverseTag() );
        } else if ( ModToolComponent.isGradient( component ) ) {
            damage = ModToolComponent.getBaseDamage( component ) + GRADIENT_DAMAGE_MAP.get( material.getInverseTag() );
        }

        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                Item.BASE_ATTACK_DAMAGE_MODIFIER_ID, damage, EntityAttributeModifier.Operation.ADD_VALUE
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

    public static ToolComponent getTool(ModToolComponent component, ToolMaterial material ) {
        return SWORD_BLADE.equals( component ) ? SWORD_TOOL_COMPONENT : material.createComponent( component.getEffectiveBlock() );
    }

    public static void addToolComponents(ItemStack result, ItemStack headItem, ItemStack handleItem, ModToolComponent component ) {
        ToolMaterial material = ModToolHeadMaterial.getMaterial( headItem );
        result.set( DataComponentTypes.CUSTOM_DATA, getCustomData( headItem, handleItem, component, material ) );
        result.set( DataComponentTypes.MAX_DAMAGE, getMaxDamage(material) );
        if ( material.equals( ModToolHeadMaterial.NETHERITE ) ) result.set( DataComponentTypes.FIRE_RESISTANT, Unit.INSTANCE );
        result.set( DataComponentTypes.ATTRIBUTE_MODIFIERS, getAttributeModifiers( component, material ));
        result.set( DataComponentTypes.TOOL, getTool(component, material) );
    }

    public static void copyToolComponents( ItemStack base, ItemStack result ) {
        result.set( DataComponentTypes.CUSTOM_DATA, base.get( DataComponentTypes.CUSTOM_DATA) );
        result.set( DataComponentTypes.MAX_DAMAGE, base.get( DataComponentTypes.MAX_DAMAGE) );
        if ( base.get( DataComponentTypes.FIRE_RESISTANT ) != null ) result.set( DataComponentTypes.FIRE_RESISTANT, Unit.INSTANCE );
        result.set( DataComponentTypes.ATTRIBUTE_MODIFIERS, base.get( DataComponentTypes.ATTRIBUTE_MODIFIERS) );
        result.set( DataComponentTypes.TOOL, base.get( DataComponentTypes.TOOL) );
    }

    public static boolean testToolsMatch( ItemStack one, ItemStack two ) {
        if ( one.get( DataComponentTypes.CUSTOM_DATA).isEmpty() || two.get( DataComponentTypes.CUSTOM_DATA).isEmpty()) return false;
        return one.get(DataComponentTypes.CUSTOM_DATA).getNbt().getString("material").equals(
                one.get(DataComponentTypes.CUSTOM_DATA).getNbt().getString("material")
        );
    }

    public static boolean testToolsMatch( ItemStack one, ModToolHeadMaterial two ) {
        return ( ! one.get( DataComponentTypes.CUSTOM_DATA).isEmpty() )
                && one.get( DataComponentTypes.CUSTOM_DATA).getNbt().getString( "material" ).equals( two.groupId );
    }

    public static final ToolComponent SWORD_TOOL_COMPONENT = new ToolComponent(
            List.of(ToolComponent.Rule.ofAlwaysDropping(List.of(Blocks.COBWEB), 15.0F), ToolComponent.Rule.of(BlockTags.SWORD_EFFICIENT, 1.5F)), 1.0F, 2
            );

}
