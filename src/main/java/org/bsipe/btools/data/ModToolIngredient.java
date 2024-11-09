package org.bsipe.btools.data;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

    public ModToolIngredient(String id, String path, String material_group, String material, String source, String baseMaterial ) {
        this.id = Identifier.of(id);
        this.material = Identifier.of( material );
        this.modToolMaterial = MATERIAL_LIST.get( Identifier.of( material_group ) );
        this.path = path;
        this.source = ToolSource.valueOf( source );
        if ( baseMaterial != null )
            this.baseMaterial = Identifier.of( baseMaterial );

    }

    public ModToolIngredient( ToolIngredient toolIngredient ) {
        this( toolIngredient.id, toolIngredient.path, toolIngredient.material_group, toolIngredient.material, toolIngredient.source, toolIngredient.base_material );
    }

    Identifier id, material, baseMaterial;
    public ModToolMaterial modToolMaterial;
    String path;
    ToolSource source;

    public String getId() {
        return id.toString();
    }

    public static void clearList() {
        INGREDIENT_LIST = new HashMap<>();
    }

    public static void addEntry( ModToolIngredient modToolIngredient) {
        if ( modToolIngredient.modToolMaterial != null ) {
            INGREDIENT_LIST.put( modToolIngredient.material, modToolIngredient);
        } else {
            LOGGER.error( "Tool Ingredient has no corresponding material, aborting adding entry to registry.");
        }
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
        return Ingredient.ofItems( Registries.ITEM.get( material ) );
    }

    public Item getMaterialItem() {
        return Registries.ITEM.get( material );
    }

    public int getDurability() {
        return modToolMaterial.durability;
    }

    public TagKey<Block> getInverseTag() {
        return modToolMaterial.inverseTag;
    }

    public float getMiningSpeedMultiplier() {
        return modToolMaterial.miningSpeed;
    }

    public boolean isFireResistent() {
        return modToolMaterial.fireResistent;
    }

    public float getDamage() {
        return modToolMaterial.getDamage();
    }

    public static Ingredient getAllIngredients( ToolSource source ) {
        return Ingredient.ofStacks( INGREDIENT_LIST.values().stream().filter( ingredient -> ingredient.source.equals( source ) ).map( id -> Registries.ITEM.get( id.material ).getDefaultStack() ) );
    }

    public static Ingredient getIngredientsForBaseMaterial( ModToolMaterial materialGroup ) {
        return Ingredient.ofStacks( INGREDIENT_LIST.values().stream().filter( ingredient -> ingredient.modToolMaterial.equals( materialGroup) ).map( id -> Registries.ITEM.get( id.material ).getDefaultStack() ) );
    }

    // SMITHING RECIPES
    public Identifier getBaseMaterial() { return baseMaterial; }

    public record ToolIngredient(String id, String path, String material_group, String material, String source, String base_material ) {}

    public enum ToolSource {
        CRAFTING,
        SMITHING,
        ALLOYING;
    }

    record Crafting() {};
    record Smithing() {};
    record Alloying() {};
}
