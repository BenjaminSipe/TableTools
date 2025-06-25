package org.bsipe.btools.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;
import org.bsipe.btools.ModItems;
import org.bsipe.btools.ModRegistries;

import java.util.*;

public class ModToolIngredient {

    public static Registry<ModToolIngredient> getRegistry() {
        try {
            return MinecraftClient.getInstance().world.getRegistryManager().get( ModRegistries.INGREDIENT_REGISTRY );
        } catch ( Exception e ) {
            return null;
        }
    }

    public static Codec<ModToolIngredient> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Identifier.CODEC.fieldOf("id").forGetter( ModToolIngredient::getIdentifier ),
                    Codec.STRING.fieldOf( "path" ).forGetter( ModToolIngredient::getPath ),
                    Identifier.CODEC.fieldOf( "material_group" ).forGetter( ModToolIngredient::getMaterialGroup ),
                    Identifier.CODEC.fieldOf( "material" ).forGetter( ModToolIngredient::getMaterial ),
                    ToolSource.CODEC.fieldOf( "source" ).forGetter( ModToolIngredient::getSource ),
                    Smithing.CODEC.optionalFieldOf( "smithingDetails", new Smithing( Identifier.of( "" ) ) ).forGetter( ModToolIngredient::getSmithingDetails ),
                    Alloying.CODEC.optionalFieldOf( "alloyingDetails", new Alloying( 0, 0, 0, Identifier.of("") ) ).forGetter( ModToolIngredient::getAlloyingDetails )
            ).apply( instance, ModToolIngredient::new ));

    public ModToolIngredient(Identifier id, String path, Identifier material_group, Identifier material, ToolSource source, Smithing smithingDetails, Alloying alloyingDetails ) {
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
    Identifier material_group;
    Identifier material;
    ToolSource source;
    Crafting craftingDetails;
    Smithing smithingDetails;
    Alloying alloyingDetails;

    public Identifier getMaterialGroup() {
        return material_group;
    }

    public String getId() {
        return id.toString();
    }

    public Identifier getIdentifier() { return Identifier.of( id ); }
    public String getPath() { return path; }
    public Identifier getMaterial() { return material; }
    public ToolSource getSource() { return source; }
    public Crafting getCraftingDetails() { return craftingDetails; } // There are no crafting details at the moment, eventually I may have advancement data here.
    public Smithing getSmithingDetails() { return smithingDetails; }
    public Alloying getAlloyingDetails() { return alloyingDetails; }

    public static Optional<ModToolIngredient> get(ItemStack item ) {
        Registry<ModToolIngredient> registry = getRegistry();
        return registry == null ? Optional.empty() : registry
                .stream()
                .filter( ingredient -> ingredient.getMaterial().equals( Registries.ITEM.getId( item.getItem() ) ) )
                .findAny();
    }

    public static String getSprite( ItemStack item, DynamicRegistryManager manager ) {
        Optional<ModToolIngredient> result = get( item );
        return result.isEmpty() ? "" : result.get().getPath();
    }

    public static ModToolIngredient get( ItemStack item, ToolSource source) {

        Optional<ModToolIngredient> ingredient = get( item );
        return ingredient.isEmpty() || ! ingredient.get().getSource().equals( source ) ? null : ingredient.get();
    }

    public Ingredient getIngredient() {
        return Ingredient.ofItems( Registries.ITEM.get(material) );
    }

    public Item getMaterialItem() {
        return Registries.ITEM.get(material);
    }

    public int getDurability() {
        return ModToolMaterial.get( material_group ).getDurability();
    }

    public TagKey<Block> getInverseTag() {
        return ModToolMaterial.get( material_group ).inverseTag;
    }

    public float getMiningSpeedMultiplier() {
        return ModToolMaterial.get( material_group ).miningSpeed;
    }

    public boolean isFireResistent() {
        return ModToolMaterial.get( material_group ).fireResistent;
    }

    public float getDamage() {
        return ModToolMaterial.get( material_group ).getDamage();
    }

    public static Ingredient getAllIngredients( ToolSource source ) {
        Registry<ModToolIngredient> registry = getRegistry();
        return registry == null ? Ingredient.EMPTY : Ingredient.ofStacks(
                registry
                        .stream()
                        .filter( ingredient -> ingredient.getSource().equals( source ) )
                        .map( ingredient -> Registries.ITEM.get( ingredient.getMaterial()).getDefaultStack()));
    }

    public static Ingredient getIngredientsForBaseMaterial( ModToolMaterial materialGroup ) {
        if ( materialGroup == null ) return Ingredient.EMPTY;
        Registry<ModToolIngredient> registry = getRegistry();

        List<ModToolIngredient> ingredients = registry == null ? List.of() : registry.stream().filter( ingredient -> ingredient.getMaterialGroup().equals( materialGroup.getId().toString() ) ).toList();
        if ( ingredients.size() == 0 ) return Ingredient.EMPTY;
        return Ingredient.ofStacks( ingredients.stream().map( ingredient -> Registries.ITEM.get(ingredient.material).getDefaultStack() ));
    }

    public Identifier getBaseMaterial() {
        if ( source == ToolSource.SMITHING ) {
            return smithingDetails.baseMaterial;
        }
        return alloyingDetails.baseMaterial;
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

    record Crafting() {};
    record Smithing(Identifier baseMaterial) {

        public static Codec<Smithing> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        Identifier.CODEC.fieldOf( "baseMaterial" ).forGetter( Smithing::baseMaterial )
                ).apply( instance, Smithing::new )
        );
    };
    record Alloying(float experience, int cookingTime, int count, Identifier baseMaterial) {

        public static Codec<Alloying> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        Codec.FLOAT.fieldOf( "experience" ).forGetter( Alloying::experience ),
                        Codec.INT.fieldOf( "cookingTime" ).forGetter( Alloying::cookingTime ),
                        Codec.INT.fieldOf( "count" ).forGetter( Alloying::count ),
                        Identifier.CODEC.fieldOf( "baseMaterial" ).forGetter( Alloying::baseMaterial )
                ).apply( instance, Alloying::new )
        );
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
