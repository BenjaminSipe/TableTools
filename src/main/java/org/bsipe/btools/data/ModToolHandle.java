package org.bsipe.btools.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;
import org.bsipe.btools.ModComponents;
import org.bsipe.btools.ModItems;
import org.bsipe.btools.ModRegistries;

import java.util.*;

import static org.bsipe.btools.BetterToolsModInitializer.LOGGER;

public class ModToolHandle {

    public static Registry<ModToolHandle> getRegistry() {
        try {
            return MinecraftClient.getInstance().world.getRegistryManager().get(ModRegistries.HANDLE_REGISTRY );
        } catch( Exception e ) {
            return null;
        }
    }

    // note to self, optional codecs don't accept a "null" default value.
    // Not sure how to allow that, so for now, just avoid.
    public static Codec<ModToolHandle> CODEC = RecordCodecBuilder.create( instance -> instance.group(
            Identifier.CODEC.fieldOf( "id" ).forGetter( ModToolHandle::getId ),
            Codec.STRING.fieldOf( "prefix" ).forGetter( ModToolHandle::getPrefix ),
            Codec.STRING.optionalFieldOf( "sprite", "" ).forGetter( ModToolHandle::getSprite ),
            Identifier.CODEC.fieldOf( "item" ).forGetter( ModToolHandle::getItem ),
            TransformType.CODEC.fieldOf( "transform" ).forGetter( ModToolHandle::getTransform ),
            Modifier.CODEC.listOf().optionalFieldOf( "modifiers", List.of() ).forGetter( ModToolHandle::getModifiers )
            ).apply( instance, ModToolHandle::new )
    );

    public ModToolHandle(
            Identifier id,
            String prefix,
            String sprite,
            Identifier item,
            TransformType transform,
            List<Modifier> modifiers ) {
        this.id = id.toString();
        this.prefix = prefix;
        this.sprite = sprite;
        this.item = item.toString();
        this.transform = transform;
        this.modifiers = null;
    }

    public TransformType getTransform() {
        return transform;
    }

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
//    public static Map<Identifier, ModToolHandle> TOOL_HANDLE_LIST = new HashMap<>();
//    public static Map<Identifier, ModToolHandle> TOOL_HANDLE_LIST_BY_ID = new HashMap<>();
//
//    // USED TO TRACK INGREDIENTS CRAFTED INTO A TOOL HANDLE.
//    public static Map<Identifier, ModToolHandle> TOOL_HANDLE_CRAFTING_INGREDIENT_LIST = new HashMap<>();

    // RAW PROPERTIES, GSON MAPS to THESE
    private String id; // Identifier
    private String prefix; //
    private String item;
    private String sprite;
    private TransformType transform;
    private Modifier[] modifiers;

    // COMPUTED PROPERTIES, DON'T TRY TO MAP TO THESE
    private transient Ingredient ingredient;


    /*
    So when I add them to the list,
    I have 3 lists.
    1. list by ID ( includes all of them ).
    2. List of ingredients used AS tool handles, not to make them.
    3. List of ingredients used to make tool handles.
     */
//    public static void addEntry( ModToolHandle handle ) {
//        TOOL_HANDLE_LIST_BY_ID.put( Identifier.of( handle.id ), handle );
//        if ( handle.transform.equals( TransformType.IDENTITY) ) {
//            TOOL_HANDLE_LIST.put( Identifier.of( handle.item ), handle );
//        } else if ( handle.transform.equals( TransformType.CRAFTED) ) {
//            TOOL_HANDLE_LIST.put( Identifier.of( handle.id), handle );
//            TOOL_HANDLE_CRAFTING_INGREDIENT_LIST.put( Identifier.of( handle.item ), handle );
//        }
//    }
    public record Modifier( float factor, ModifierProperty property, Operation operation ) {

        public static Codec<Modifier> CODEC = RecordCodecBuilder.create( instance -> instance
                .group(
                        Codec.FLOAT.fieldOf( "factor").forGetter( Modifier::factor ),
                        ModifierProperty.CODEC.fieldOf( "property" ).forGetter( Modifier::property ),
                        Operation.CODEC.fieldOf( "operation" ).forGetter( Modifier::operation )

                ).apply( instance, Modifier::new ));


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

        private enum Operation implements StringIdentifiable {
            MULTIPLY, ADD;
            public static final Codec<ModToolHandle.Modifier.Operation> CODEC = StringIdentifiable.createCodec( () -> values() );

            @Override
            public String asString() {
                return this.name();
            }
        }

        public float addModifier(float previous) {
            return switch( operation ) {
                case ADD -> previous + factor;
                case MULTIPLY -> previous * factor;
            };
        }

    }

    private enum ModifierProperty implements StringIdentifiable {
        DURABILITY, DAMAGE, MINING_SPEED;

        public static final Codec<ModToolHandle.ModifierProperty> CODEC = StringIdentifiable.createCodec( () -> values() );

        @Override
        public String asString() {
            return this.name();
        }

    }

    private enum TransformType implements StringIdentifiable {
        IDENTITY, CRAFTED;

        public static final Codec<ModToolHandle.TransformType> CODEC = StringIdentifiable.createCodec( () -> values() );

        @Override
        public String asString() {
            return this.name();
        }
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

    public List<Modifier> getModifiers() {
        return List.of( modifiers );
    }

    public String getSprite() {
        return ( sprite != null && ! "".equals( sprite ) ) ? sprite : prefix + ModToolComponent.BASIC_HANDLE.getSuffix();
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
        if ( getRegistry() == null ) return null;
        if ( ingredient.isOf( ModItems.TOOL_HANDLE ) ) {
            return getRegistry().get( Identifier.of( ingredient.get(ModComponents.HANDLE_RENDER_COMPONENT).handleId() ) );
        }
        Identifier ingredientId = Registries.ITEM.getId( ingredient.getItem() );

        return ModToolHandle.getRegistry().stream().filter( handle -> handle.getItem().equals( ingredientId )).findAny().orElse( null );
//        if ( ingredient.isOf( ModItems.TOOL_HANDLE ) ) {
//            return ModToolHandle.TOOL_HANDLE_LIST.get( Identifier.of( ingredient.get(ModComponents.HANDLE_RENDER_COMPONENT).handleId() ) );
//        }
//        return ModToolHandle.TOOL_HANDLE_LIST.get( Registries.ITEM.getId( ingredient.getItem() ) );
    }

    public static ModToolHandle getByCraftingIngredient( ItemStack ingredient ) {
        if ( getRegistry() == null ) return null;
        return ModToolHandle.getRegistry().stream().filter( handle -> handle.getTransform().equals( TransformType.CRAFTED ) && handle.getItem().equals( Registries.ITEM.getId( ingredient.getItem()))).findAny().orElse( null );
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
            if (modifier.property() == property ) {
                return modifier.addModifier( previous );
            }
        }
        return previous;
    }

    public static Collection<ItemStack> getToolHandles() {
        return getRegistry().stream().map(handle ->
                handle.transform == TransformType.IDENTITY ?
                        Registries.ITEM.get( handle.getItem() ).getDefaultStack() :
                        DataComponentHelper.addHandleComponents(
                                ModItems.TOOL_HANDLE.getDefaultStack(), handle )).toList();
    }
}
