package org.bsipe.btools.data;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.bsipe.btools.ModItems;

import java.util.HashMap;
import java.util.Map;

import static org.bsipe.btools.BetterToolsModInitializer.LOGGER;

public class ModToolHandle {


    // breakdown of how this works. . . there are essentially two different models.
    /*
    For context, I will assume we have 3 tool handles,
    Oak, Spruce, and Blaze Rod.
    Oak and Spruce are crafted, blaze rod is used directly.

    When loading,

   -- BLAZE ROD
   minecraft:blaze_rod is the key on entered into the TOOL_HANDLE_LIST,
   no entry into "CRAFTED" is added for blaze rod.
   -- OAK_PLANKS

   The handle's MATERIAL_ID 'btools:oak' is the key entered into the TOOL_HANDLE_LIST it will contain the correct sprite.
   minecraft_oak_planks is the key entered into the TOOL_HANDLE_CRAFTING_INGREDIENT_LIST.

   When crafting a tool handle, the TOOL_HANDLE_CRAFTING_INGREDIENT_LIST is referenced for materials.
   When crafting a tool, the TOOL_HANDLE_LIST is referenced, which will have a hard coded reference to "btools:tool-handle.
   If the dynamic tool handle is caught, then the crafted id is pulled off the handle and used to reference TOOL_HANDLE_LIST...
   This the returns the proper information.


     */

    // USED TO TRACK ACTUAL TOOL HANDLES.
    // KEYED ON Ingredient ID. . . any thing other than a btools: tool handle will reference this.
    // btools-tool handle sprites will reference
    public static Map<Identifier, ModToolHandle> TOOL_HANDLE_LIST = new HashMap<>();
    public static Map<Identifier, ModToolHandle> TOOL_HANDLE_LIST_BY_ID = new HashMap<>();

    // USED TO TRACK INGREDIENTS CRAFTED INTO A TOOL HANDLE.
    public static Map<Identifier, ModToolHandle> TOOL_HANDLE_CRAFTING_INGREDIENT_LIST = new HashMap<>();

    // RAW PROPERTIES, GSON MAPS to THESE
    private String id;
    private String prefix;
    private String item;
    private String sprite;
    private TransformType transform;
    private Modifier[] modifiers;

    // COMPUTED PROPERTIES, DON'T TRY TO MAP TO THESE
    private transient Ingredient ingredient;

    public static void addEntry( ModToolHandle handle ) {
        TOOL_HANDLE_LIST_BY_ID.put( Identifier.of( handle.id ), handle );
        if ( handle.transform.equals( TransformType.IDENTITY) ) {
            TOOL_HANDLE_LIST.put( Identifier.of( handle.item ), handle );
        } else if ( handle.transform.equals( TransformType.CRAFTED) ) {
            TOOL_HANDLE_LIST.put( Identifier.of( handle.id), handle );
            TOOL_HANDLE_CRAFTING_INGREDIENT_LIST.put( Identifier.of( handle.item ), handle );
        }
    }

    public static int count() {
        return TOOL_HANDLE_LIST_BY_ID.size();
    }

    public class Modifier {
        private float factor;
        private ModifierProperty property;
        private Operation operation;

        public boolean validate() {
            boolean isValid = true;
            if ( factor == 0f ) {
                LOGGER.error( "Handle json modifier missing \"factor\" entry." );
                isValid = false;
            }
            if ( property == null ) {
                LOGGER.error( "Handle json modifier missing \"property\" entry.");
                isValid = false;
            }
            if ( operation == null ) {
                LOGGER.error( "Handle json modifier missing \"operation\" entry.");
                isValid = false;
            }
            return isValid;
        }

        private enum Operation {
            MULTIPLY, ADD;
        }

        public float addModifier(float previous) {
            return switch( operation ) {
                case ADD -> previous + factor;
                case MULTIPLY -> previous * factor;
            };
        }

        public ModifierProperty getProperty() {
            return property;
        }
    }

    private enum ModifierProperty {
        DURABILITY, DAMAGE, MINING_SPEED;
    }

    private enum TransformType {
        IDENTITY, CRAFTED;
    }


    public boolean validate() {
        boolean isValid = true;
        if ( id == null ) {
            LOGGER.error( "Handle json missing \"id\" entry." );
            isValid = false;
        } else if ( ! Items.AIR.equals( Registries.ITEM.get( Identifier.of( id ) ) ) ) {
            LOGGER.error( "Handle json \"id\" entry cannot be the same as an existing item ID." );
            isValid = false;
        }
        if ( prefix == null ) {
            LOGGER.error( "Handle json missing \"prefix\" entry." );
            isValid = false;
        }
        if ( transform == null ) {
            LOGGER.error( "Handle json missing \"transform\" entry.");
            isValid = false;
        }
        if ( item == null ) {
            LOGGER.error( "Handle json missing \"item\" entry." );
            isValid = false;
        } else if ( Items.AIR.equals( Registries.ITEM.get( Identifier.of( item ) ) == null ) ) {
            LOGGER.error( "Handle json \"item\" entry is invalid ( doesn't reference an existing item )." );
            isValid = false;
        }

        if ( modifiers != null ) {
            for ( Modifier modifier : modifiers ) {
                isValid &= modifier.validate();
            }
        }
        return isValid;
    }

    public Identifier getId() {
        return Identifier.of( id );
    }

    public Identifier getItem() {
        return Identifier.of( item );
    }

    public String getPrefix() {
        return prefix;
    }

    public Modifier[] getModifiers() {
        return modifiers;
    }

    public String getSprite() {
        return ( sprite != null ) ? sprite : prefix + ModToolComponent.BASIC_HANDLE.getSuffix();
    }

    public String getHandleSprite(ModToolComponent c) {
        return prefix + c.getSuffix();
    }

    public Ingredient getIngredient() {
        if ( ingredient != null ) return ingredient;
        ingredient = switch( transform ) {
            case IDENTITY -> Ingredient.ofItems( Registries.ITEM.get( Identifier.of( item ) ) );
            case CRAFTED -> Ingredient.ofItems( ModItems.TOOL_HANDLE );
        };
        return ingredient;
    }

    public static ModToolHandle getModToolHandle( ItemStack ingredient ) {
        if ( ingredient.isOf( ModItems.TOOL_HANDLE ) ) {
            return ModToolHandle.TOOL_HANDLE_LIST.get( Identifier.of( ingredient.get(DataComponentTypes.CUSTOM_DATA).copyNbt().getString( "handle-id") ) );
        }
        return ModToolHandle.TOOL_HANDLE_LIST.get( Registries.ITEM.getId( ingredient.getItem() ) );
    }

    public int modifyDurability(int previous) {
        return (int) applyModifier( previous, ModifierProperty.DURABILITY );
    }

    public float modifyMiningSpeed(float previous) {
        return applyModifier(previous, ModifierProperty.MINING_SPEED);
    }

    public float modifyDamage(float previous) {
        // damage does this weird thing where it has a base of 1. . .
        // so to make the math work out I take that into account.
        return applyModifier(previous + 1f, ModifierProperty.DAMAGE) - 1f;
    }

    public float applyModifier(float previous, ModifierProperty property ) {
        if ( modifiers == null || modifiers.length == 0 ) return previous;
        for ( Modifier modifier : modifiers ) {
            if (modifier.getProperty() == property ) {
                return modifier.addModifier( previous );
            }
        }
        return previous;
    }
}
