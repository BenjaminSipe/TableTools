package org.bsipe.btools.data;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

import static org.bsipe.btools.BetterToolsModInitializer.LOGGER;
import static org.bsipe.btools.data.ModToolMaterial.MATERIAL_LIST;

public class ModToolIngredient {

    public static Map<Identifier, ModToolIngredient> INGREDIENT_LIST = new HashMap<>();

//    public ModToolIngredient(String id, String path, String material_group, String material, String source, String baseMaterial ) {
//        this.id = id;
//        this.material = material;
//        this.modToolMaterial = MATERIAL_LIST.get( Identifier.of( material_group ) );
//        this.path = path;
//        this.source = ToolSource.valueOf( source );
//        if ( baseMaterial != null )
//            this.baseMaterial = Identifier.of( baseMaterial );
//
//    }

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

    public static void clearList() {
        INGREDIENT_LIST = new HashMap<>();
    }

    public static void addEntry( ModToolIngredient modToolIngredient) {
        INGREDIENT_LIST.put( Identifier.of(modToolIngredient.material), modToolIngredient);
    }

    public static int getCount() {
        return INGREDIENT_LIST.size();
    }

    public static String getSprite( ItemStack item ) {
        ModToolIngredient i = INGREDIENT_LIST.get( Registries.ITEM.getId( item.getItem() ) );
        if ( i == null ) return "";
        return i.path;
    }

    public static ModToolIngredient get( ItemStack item ) {
        return INGREDIENT_LIST.get( Registries.ITEM.getId( item.getItem() ) );
    }

    public static ModToolIngredient get( ItemStack item, ToolSource source ) {
        ModToolIngredient i = get( item );
        return i == null || ! i.source.equals( source ) ? null : i;
    }

    public Ingredient getIngredient() {
        return Ingredient.ofItems( Registries.ITEM.get(Identifier.of(material)) );
    }

    public Item getMaterialItem() {
        return Registries.ITEM.get(Identifier.of(material));
    }

    public int getDurability() {
        return MATERIAL_LIST.get( Identifier.of( material_group ) ).durability;
    }

    public TagKey<Block> getInverseTag() {
        return MATERIAL_LIST.get( Identifier.of( material_group ) ).inverseTag;
    }

    public float getMiningSpeedMultiplier() {
        return MATERIAL_LIST.get( Identifier.of( material_group ) ).miningSpeed;
    }

    public boolean isFireResistent() {
        return MATERIAL_LIST.get( Identifier.of( material_group ) ).fireResistent;
    }

    public float getDamage() {
        return MATERIAL_LIST.get( Identifier.of( material_group ) ).getDamage();
    }

    public static Ingredient getAllIngredients( ToolSource source ) {
        return Ingredient.ofStacks( INGREDIENT_LIST.values().stream().filter( ingredient -> ingredient.source.equals( source ) ).map( id -> Registries.ITEM.get(Identifier.of(id.material)).getDefaultStack() ) );
    }

    public static Ingredient getIngredientsForBaseMaterial( ModToolMaterial materialGroup ) {
        return Ingredient.ofStacks( INGREDIENT_LIST.values().stream().filter( ingredient -> MATERIAL_LIST.get( Identifier.of( ingredient.getMaterialGroup())).equals( materialGroup) ).map( id -> Registries.ITEM.get(Identifier.of(id.material)).getDefaultStack() ) );
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

    public enum ToolSource {
        CRAFTING,
        SMITHING,
        ALLOYING;
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
        public boolean validate() {
            return baseMaterial != null;
        }
    };
    record Alloying(float experience, int cookingTime, int count, String baseMaterial) {
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
}
