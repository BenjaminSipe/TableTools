package org.bsipe.btools.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.world.World;
import org.bsipe.btools.ModItems;
import org.bsipe.btools.ModRegistries;
import org.bsipe.btools.components.HandleItemComponent;

import java.util.*;

import static org.bsipe.btools.BetterToolsModInitializer.LOGGER;


// OK. . . so there's several things left to do before I'm done with the transfer process.
// Primarily, I'm not actually using these registries yet. Only the hash map.

// probably making a utility class or something is the way to go. . . maybe.
// maybe not.


public class ModToolIngredient {

    public static Registry<ModToolIngredient> getRegistry() {
        try {
            return MinecraftClient.getInstance().world.getRegistryManager().get( ModRegistries.INGREDIENT_REGISTRY );
        } catch ( Exception e ) {
            return null;
        }
    }

//    public static Map<Identifier, ModToolIngredient> INGREDIENT_LIST = new HashMap<>();

    public static Codec<ModToolIngredient> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Identifier.CODEC.fieldOf("id").forGetter( ModToolIngredient::getIdentifier ),
                    Codec.STRING.fieldOf( "path" ).forGetter( ModToolIngredient::getPath ),
                    Codec.STRING.fieldOf( "material_group" ).forGetter( ModToolIngredient::getMaterialGroup ),
                    Codec.STRING.fieldOf( "material" ).forGetter( ModToolIngredient::getMaterial ),
                    ToolSource.CODEC.fieldOf( "source" ).forGetter( ModToolIngredient::getSource ),
                    Smithing.CODEC.optionalFieldOf( "smithingDetails", new Smithing( "" ) ).forGetter( ModToolIngredient::getSmithingDetails ),
                    Alloying.CODEC.optionalFieldOf( "alloyingDetails", new Alloying( 0, 0, 0, "" ) ).forGetter( ModToolIngredient::getAlloyingDetails )
            ).apply( instance, ModToolIngredient::new ));

    public ModToolIngredient(Identifier id, String path, String material_group, String material, ToolSource source, Smithing smithingDetails, Alloying alloyingDetails ) {
        this.id = id.toString();
        this.path = path;
        this.material_group = material_group;
        this.material = material;
        this.source = source;
        this.craftingDetails = null;
        this.smithingDetails = null;
        this.alloyingDetails = null;
        if ( this.source == ToolSource.SMITHING ) this.smithingDetails  = smithingDetails;
        if ( this.source == ToolSource.ALLOYING ) this.alloyingDetails = alloyingDetails;
    }



    String id;
    String path;
    String material_group;
    String material;
    ToolSource source;
    Crafting craftingDetails;
    Smithing smithingDetails;
    Alloying alloyingDetails;

    public String getMaterialGroup() {
        return material_group;
    }

    public String getId() {
        return id.toString();
    }

    public Identifier getIdentifier() { return Identifier.of( id ); }
    public String getPath() { return path; }
    public String getMaterial() { return material; }
    public ToolSource getSource() { return source; }
    public Crafting getCraftingDetails() { return craftingDetails; } // There are no crafting details at the moment, eventually I may have advancement data here.
    public Smithing getSmithingDetails() { return smithingDetails; }
    public Alloying getAlloyingDetails() { return alloyingDetails; }

//    public static void clearList() {
//        INGREDIENT_LIST = new HashMap<>();
//    }

//    public static void addEntry( ModToolIngredient modToolIngredient) {
//        INGREDIENT_LIST.put( Identifier.of(modToolIngredient.material), modToolIngredient);
//    }


    public static Optional<ModToolIngredient> get(ItemStack item ) {
        Registry<ModToolIngredient> registry = getRegistry();
        return registry == null ? Optional.empty() : registry
                .stream()
                .filter( ingredient -> Identifier.of(  ingredient.getMaterial() ).equals( Registries.ITEM.getId( item.getItem() ) ) )
                .findAny();
    }

    public static String getSprite( ItemStack item, DynamicRegistryManager manager ) {
        Optional<ModToolIngredient> result = get( item );
        return result.isEmpty() ? "" : result.get().getPath();
//        ModToolIngredient i = INGREDIENT_LIST.get( Registries.ITEM.getId( item.getItem() ) );
//        if ( i == null ) return "";
//        return i.path;
    }

    public static ModToolIngredient get( ItemStack item, ToolSource source) {

        Optional<ModToolIngredient> ingredient = get( item );
        return ingredient.isEmpty() || ! ingredient.get().getSource().equals( source ) ? null : ingredient.get();
    }

    public Ingredient getIngredient() {
        return Ingredient.ofItems( Registries.ITEM.get(Identifier.of(material)) );
    }

    public Item getMaterialItem() {
        return Registries.ITEM.get(Identifier.of(material));
    }

    public int getDurability() {
        return ModToolMaterial.get( Identifier.of( material_group ) ).getDurability();
    }

    public TagKey<Block> getInverseTag() {
        return ModToolMaterial.get( Identifier.of( material_group ) ).inverseTag;
    }

    public float getMiningSpeedMultiplier() {
        return ModToolMaterial.get( Identifier.of( material_group ) ).miningSpeed;
    }

    public boolean isFireResistent() {
        return ModToolMaterial.get( Identifier.of( material_group ) ).fireResistent;
    }

    public float getDamage() {
        return ModToolMaterial.get( Identifier.of( material_group ) ).getDamage();
    }

    public static Ingredient getAllIngredients( ToolSource source ) {
        Registry<ModToolIngredient> registry = getRegistry();
        return registry == null ? Ingredient.EMPTY : Ingredient.ofStacks(
                registry
                        .stream()
                        .filter( ingredient -> ingredient.getSource().equals( source ) )
                        .map( ingredient -> Registries.ITEM.get( Identifier.of( ingredient.getMaterial())).getDefaultStack()));
//        return Ingredient.ofStacks( INGREDIENT_LIST.values().stream().filter( ingredient -> ingredient.source.equals( source ) ).map( id -> Registries.ITEM.get(Identifier.of(id.material)).getDefaultStack() ) );
    }

    public static Ingredient getIngredientsForBaseMaterial( ModToolMaterial materialGroup ) {
        if ( materialGroup == null ) return Ingredient.EMPTY;
        Registry<ModToolIngredient> registry = getRegistry();

        List<ModToolIngredient> ingredients = registry == null ? List.of() : registry.stream().filter( ingredient -> ingredient.getMaterialGroup().equals( materialGroup.getId().toString() ) ).toList();
        if ( ingredients.size() == 0 ) return Ingredient.EMPTY;
        return Ingredient.ofStacks( ingredients.stream().map( ingredient -> Registries.ITEM.get(Identifier.of(ingredient.material)).getDefaultStack() ));
    }

    // SMITHING RECIPES
    public Identifier getBaseMaterial() {
        if ( source == ToolSource.SMITHING ) {
            return Identifier.of(smithingDetails.baseMaterial);
        }
        return Identifier.of( alloyingDetails.baseMaterial );
    }

    public int getCookingTime() {
        if ( alloyingDetails == null ) return -1;
        return alloyingDetails.cookingTime();
    }

    public int getAlloyingCount() {
        if ( alloyingDetails == null ) return -1;
        return alloyingDetails.count();
    }

    public float getExperience() {
        if ( alloyingDetails == null ) return -1;
        return alloyingDetails.experience();
    }

    public enum ToolSource implements StringIdentifiable {
        CRAFTING,
        SMITHING,
        ALLOYING;

        public static Codec<ToolSource> CODEC = StringIdentifiable.createCodec( () -> values() );

        @Override
        public String asString() {
            return name();
        }
    }

    public boolean validate() {
        boolean isValid = true;

        if ( id == null ) {
            LOGGER.error( "Ingredient json is missing \"id\" entry.");
            isValid = false;
        }
        if ( path == null ) {
            LOGGER.error( "Handle json missing \"path\" entry." );
            isValid = false;
        }
        if ( material_group == null ) {
            LOGGER.error( "Handle json missing \"material_group\" entry." );
            isValid = false;
        }
        if ( material == null ) {
            LOGGER.error( "Handle json missing \"material\" entry." );
            isValid = false;
        } else if ( Items.AIR.equals( Registries.ITEM.get(Identifier.of(material)))) {
            LOGGER.error( "Handle json \"material\" entry is invalid ( doesn't reference an existing item )." );
            isValid = false;
        }
        if ( source == null ) {
            LOGGER.error( "Handle json missing \"source\" entry." );
            isValid = false;
        } else if ( source == ToolSource.SMITHING ) {
            if ( smithingDetails == null ) {
                LOGGER.error( "Handle json missing \"smithingDetails\" entry." );
                isValid = false;
            } else if (! smithingDetails.validate() ) {
                isValid = false;
            }
        } else if ( source == ToolSource.ALLOYING ) {
            if ( alloyingDetails == null ) {
                LOGGER.error( "Handle json missing \"alloyingDetails\" entry." );
                isValid = false;
            } else if (! alloyingDetails.validate() ) {
                isValid = false;
            }
        }

        return isValid;
    }

    record Crafting() {};
    record Smithing(String baseMaterial) {

        public static Codec<Smithing> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        Codec.STRING.fieldOf( "baseMaterial" ).forGetter( Smithing::baseMaterial )
                ).apply( instance, Smithing::new )
        );

        public boolean validate() {
            return baseMaterial != null;
        }
    };
    record Alloying(float experience, int cookingTime, int count, String baseMaterial) {

        public static Codec<Alloying> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        Codec.FLOAT.fieldOf( "experience" ).forGetter( Alloying::experience ),
                        Codec.INT.fieldOf( "cookingTime" ).forGetter( Alloying::cookingTime ),
                        Codec.INT.fieldOf( "count" ).forGetter( Alloying::count ),
                        Codec.STRING.fieldOf( "baseMaterial" ).forGetter( Alloying::baseMaterial )
                ).apply( instance, Alloying::new )
        );


        public boolean validate() {
            boolean isValid = true;
            if ( baseMaterial == null ) {
                LOGGER.error( "Handle json missing \"baseMaterial\" entry." );
                isValid = false;
            }
            if ( experience <= 0 ) {
                LOGGER.error( "Handle json missing \"experience\" entry." );
                isValid = false;
            }
            if ( cookingTime < 1) {
                LOGGER.error( "Handle json missing \"cookingTime\" entry." );
                isValid = false;
            }
            if ( count < 1 ) {
                LOGGER.error( "Handle json missing \"count\" entry." );
                isValid = false;
            }

            return isValid;
        }
    };

    public static Collection<ItemStack> getAllTools() {
        List<ItemStack> list = new ArrayList<>();
        list.addAll( getAllToolsForComponent( ModItems.AXE, ModToolComponent.AXE_HEAD ));
        list.addAll( getAllToolsForComponent( ModItems.HOE, ModToolComponent.HOE_HEAD ));
        list.addAll( getAllToolsForComponent( ModItems.SHOVEL, ModToolComponent.SHOVEL_HEAD ));
        list.addAll( getAllToolsForComponent( ModItems.SWORD, ModToolComponent.SWORD_BLADE ));
        list.addAll( getAllToolsForComponent( ModItems.PICKAXE, ModToolComponent.PICKAXE_HEAD ));
        return list;
    }

    public static Collection<ItemStack> getOakTools() {
        List<ItemStack> list = new ArrayList<>();
        list.addAll( getOakToolsForComponent( ModItems.AXE, ModToolComponent.AXE_HEAD ));
        list.addAll( getOakToolsForComponent( ModItems.HOE, ModToolComponent.HOE_HEAD ));
        list.addAll( getOakToolsForComponent( ModItems.SHOVEL, ModToolComponent.SHOVEL_HEAD ));
        list.addAll( getOakToolsForComponent( ModItems.SWORD, ModToolComponent.SWORD_BLADE ));
        list.addAll( getOakToolsForComponent( ModItems.PICKAXE, ModToolComponent.PICKAXE_HEAD ));
        return list;
    }

    public static Collection<ItemStack> getAllToolsForComponent( Item item, ModToolComponent component ) {
        List<ItemStack> list = new ArrayList<>();
        Registry<ModToolIngredient> registry = getRegistry();
        if ( registry == null ) return list;
        for ( ModToolIngredient i : getRegistry() ) {
            for ( ModToolHandle handle : ModToolHandle.getRegistry()) {
                list.add( DataComponentHelper.addToolComponents( item.getDefaultStack(), i, handle, component ));
            }
        }
        return list;
    }

    public static Collection<ItemStack> getOakToolsForComponent( Item item, ModToolComponent component ) {
        List<ItemStack> list = new ArrayList<>();
        if ( getRegistry() == null ) return list;
        for ( ModToolIngredient i : getRegistry() ) {
                list.add( DataComponentHelper.addToolComponents( item.getDefaultStack(), i, ModToolHandle.getRegistry().get( Identifier.of( "btools:wood/oak" )), component ));
        }
        return list;
    }


}
