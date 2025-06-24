package org.bsipe.btools.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;
import org.bsipe.btools.ModBlockTags;
import org.bsipe.btools.ModRegistries;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ModToolMaterial {

    public static Registry<ModToolMaterial> getRegistry() {
        return MinecraftClient.getInstance().world.getRegistryManager().get(ModRegistries.MATERIAL_REGISTRY);
    }

    public static ModToolMaterial get(Identifier id ) {
        return getRegistry().get( id );
    }


    public static Codec<ModToolMaterial> CODEC = RecordCodecBuilder.create( instance -> instance.group(
            Identifier.CODEC.fieldOf( "id" ).forGetter( ModToolMaterial::getId ),
            Codec.INT.fieldOf( "durability" ).forGetter( ModToolMaterial::getDurability ),
            Codec.FLOAT.fieldOf( "miningSpeed" ).forGetter( ModToolMaterial::getMiningSpeed ),
            MiningLevel.CODEC.fieldOf( "miningLevel" ).forGetter( ModToolMaterial::getMiningLevel ),
            Codec.FLOAT.fieldOf( "damage" ).forGetter( ModToolMaterial::getDamage ),
            Codec.BOOL.optionalFieldOf( "fireResistent", false ).forGetter( ModToolMaterial::isFireResistent )


            ).apply( instance, ModToolMaterial::new ));

    public ModToolMaterial(Identifier id, int durability, float mining_speed, MiningLevel miningLevel,  float damage, boolean fireResistent ) {
        this.id = id;
        this.durability = durability;
        this.miningSpeed = mining_speed;
        this.inverseTag = miningLevel.inverseTag;
        this.fireResistent = fireResistent;
        this.damage = damage;
    }

//    public ModToolMaterial( ModToolMaterial.Material material ) {
//        this(material.id, material.durability, material.mining_speed, material.mining_level, material.fire_resistant, material.damage);
//    }

    Identifier id;
    int durability;
    float miningSpeed;
    TagKey<Block> inverseTag;
    boolean fireResistent;
    float damage;
    MiningLevel miningLevel;


//    public static Map<Identifier, ModToolMaterial> MATERIAL_LIST = new HashMap<>();

//    public static void clearList() {
//        MATERIAL_LIST = new HashMap<>();
//    }
//
//    public static void addEntry( ModToolMaterial modToolMaterial) {
//        MATERIAL_LIST.put( modToolMaterial.id, modToolMaterial);
//    }

    private enum MiningLevel implements StringIdentifiable {
        WOOD(BlockTags.INCORRECT_FOR_WOODEN_TOOL),
        STONE(BlockTags.INCORRECT_FOR_STONE_TOOL),
        IRON(BlockTags.INCORRECT_FOR_IRON_TOOL),
        DIAMOND(BlockTags.INCORRECT_FOR_DIAMOND_TOOL),
        NETHERITE(BlockTags.INCORRECT_FOR_NETHERITE_TOOL),
        ENDIRIUM(ModBlockTags.INCORRECT_FOR_ENDIRIUM_TOOL),
        PALADUS(ModBlockTags.INCORRECT_FOR_PALADUS_TOOL)

        ;

        public static final Codec<MiningLevel> CODEC = StringIdentifiable.createCodec( () -> values() );

        TagKey<Block> inverseTag;

        MiningLevel(TagKey<Block> inverseTag) {
            this.inverseTag = inverseTag;
        }

        @Override
        public String asString() {
            return this.name();
        }

    }

    public static int getCount() {
        return getRegistry().size();
    }

    public Identifier getId() {
        return id;
    }

    public float getDamage() { return damage; }
    public float getMiningSpeed() { return miningSpeed; }
    public int getDurability() { return durability; }
    public MiningLevel getMiningLevel() { return miningLevel; }
    public boolean isFireResistent() { return fireResistent; }

    // public record Material(String id, int durability, float mining_speed, String mining_level, boolean fire_resistant, float damage ) {}
}
