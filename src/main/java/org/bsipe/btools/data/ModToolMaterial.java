package org.bsipe.btools.data;

import net.minecraft.block.Block;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.bsipe.btools.ModBlockTags;

import java.util.HashMap;
import java.util.Map;

public class ModToolMaterial {

    public ModToolMaterial(String id, int durability, float mining_speed, String mining_level, boolean fireResistent, float damage ) {
        this.id = Identifier.of ( id );
        this.durability = durability;
        this.miningSpeed = mining_speed;
        this.inverseTag = MiningLevel.valueOf( mining_level ).inverseTag;
        this.fireResistent = fireResistent;
        this.damage = damage;
    }

    public ModToolMaterial( ModToolMaterial.Material material ) {
        this(material.id, material.durability, material.mining_speed, material.mining_level, material.fire_resistant, material.damage);
    }

    Identifier id;
    int durability;
    float miningSpeed;
    TagKey<Block> inverseTag;
    boolean fireResistent;
    float damage;

    public static Map<Identifier, ModToolMaterial> MATERIAL_LIST = new HashMap<>();

    public static void clearList() {
        MATERIAL_LIST = new HashMap<>();
    }

    public static void addEntry( ModToolMaterial modToolMaterial) {
        MATERIAL_LIST.put( modToolMaterial.id, modToolMaterial);
    }

    private enum MiningLevel {
        WOOD(BlockTags.INCORRECT_FOR_WOODEN_TOOL),
        STONE(BlockTags.INCORRECT_FOR_STONE_TOOL),
        IRON(BlockTags.INCORRECT_FOR_IRON_TOOL),
        DIAMOND(BlockTags.INCORRECT_FOR_DIAMOND_TOOL),
        NETHERITE(BlockTags.INCORRECT_FOR_NETHERITE_TOOL),
        ENDIRIUM(ModBlockTags.INCORRECT_FOR_ENDIRIUM_TOOL),
        PALADUS(ModBlockTags.INCORRECT_FOR_PALADUS_TOOL)

        ;

        TagKey<Block> inverseTag;

        MiningLevel(TagKey<Block> inverseTag) {
            this.inverseTag = inverseTag;
        }
    }

    public static int getCount() {
        return MATERIAL_LIST.size();
    }

    public Identifier getId() {
        return id;
    }

    public float getDamage() { return damage; }
    public float getMiningSpeed() { return miningSpeed; }

    public record Material(String id, int durability, float mining_speed, String mining_level, boolean fire_resistant, float damage ) {}
}
