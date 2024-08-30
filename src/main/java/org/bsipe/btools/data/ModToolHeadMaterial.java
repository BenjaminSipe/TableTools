package org.bsipe.btools.data;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import org.bsipe.btools.ModTags;

import java.util.Arrays;
import java.util.Optional;


public enum ModToolHeadMaterial implements ToolMaterial {

    // vanilla tools
    ACACIA(Material.WOOD, ItemTags.PLANKS, Items.ACACIA_PLANKS, "acacia"),
    BAMBOO(Material.WOOD, ItemTags.PLANKS, Items.BAMBOO_PLANKS, "bamboo"),
    BIRCH( Material.WOOD, ItemTags.PLANKS, Items.BIRCH_PLANKS, "birch"),
    CHERRY( Material.WOOD, ItemTags.PLANKS, Items.CHERRY_PLANKS, "cherry"),
    CRIMSON( Material.WOOD, ItemTags.PLANKS, Items.CRIMSON_PLANKS, "crimson"),
    DARK_OAK( Material.WOOD, ItemTags.PLANKS, Items.DARK_OAK_PLANKS, "dark_oak"),
    JUNGLE( Material.WOOD, ItemTags.PLANKS, Items.JUNGLE_PLANKS, "jungle"),
    MANGROVE( Material.WOOD, ItemTags.PLANKS, Items.MANGROVE_PLANKS, "mangrove"),
    OAK( Material.WOOD, ItemTags.PLANKS, Items.OAK_PLANKS, "oak", true ),
    SPRUCE( Material.WOOD, ItemTags.PLANKS, Items.SPRUCE_PLANKS, "spruce"),
    WARPED( Material.WOOD, ItemTags.PLANKS, Items.WARPED_PLANKS, "warped"),
    IRON( Material.IRON, Items.IRON_INGOT, "iron"),
    DIAMOND( Material.DIAMOND, Items.DIAMOND, "diamond"),
    NETHERITE( Material.NETHERITE, Items.NETHERITE_INGOT, "netherite"),

    // vanilla but changed,
    // add copper variant to new family of "soft metals"
    GOLD( Material.SOFT_METAL, ModTags.SOFT_METAL_INGREDIENTS, Items.GOLD_INGOT, "gold", true),

    // added new materials to "stone tool family"
    STONE( Material.STONE, ItemTags.STONE_TOOL_MATERIALS, Items.COBBLESTONE, "stone", true),
    DEEPSLATE( Material.STONE, ItemTags.STONE_TOOL_MATERIALS, Items.COBBLED_DEEPSLATE, "deepslate"),
    BLACKSTONE( Material.STONE, ItemTags.STONE_TOOL_MATERIALS, Items.BLACKSTONE, "blackstone"),

    // modded,
    COPPER( Material.SOFT_METAL, ModTags.SOFT_METAL_INGREDIENTS, Items.COPPER_INGOT, "copper"),

    GRANITE( Material.STONE, ItemTags.STONE_TOOL_MATERIALS, Items.GRANITE, "granite"),
    DIORITE( Material.STONE, ItemTags.STONE_TOOL_MATERIALS, Items.DIORITE, "diorite"),
    ANDESITE( Material.STONE, ItemTags.STONE_TOOL_MATERIALS, Items.ANDESITE, "andesite"),
    TUFF( Material.STONE, ItemTags.STONE_TOOL_MATERIALS, Items.TUFF, "tuff"),
    BASALT( Material.STONE, ItemTags.STONE_TOOL_MATERIALS, Items.BASALT, "basalt"),
    CALCITE( Material.STONE, ItemTags.STONE_TOOL_MATERIALS, Items.CALCITE, "calcite"),

    AMETHYST( Material.SOFT_GEMSTONES, ModTags.SOFT_GEMSTONE_INGREDIENTS, Items.AMETHYST_SHARD, "amethyst"),
    EMERALD( Material.SOFT_GEMSTONES, ModTags.SOFT_GEMSTONE_INGREDIENTS, Items.EMERALD, "emerald"),
    QUARTZ( Material.SOFT_GEMSTONES, ModTags.SOFT_GEMSTONE_INGREDIENTS, Items.QUARTZ, "quartz"),

    FLINT( Material.SOFT_STONES, ModTags.SOFT_STONE_INGREDIENTS, Items.FLINT, "flint"),
    NETHERRACK( Material.SOFT_STONES, ModTags.SOFT_STONE_INGREDIENTS, Items.NETHERRACK, "netherrack"),
    SANDSTONE( Material.SOFT_STONES, ModTags.SOFT_STONE_INGREDIENTS, Items.SANDSTONE, "sandstone"),
    RED_SANDSTONE( Material.SOFT_STONES, ModTags.SOFT_STONE_INGREDIENTS, Items.RED_SANDSTONE, "red_sandstone"),

    CRYING_OBSIDIAN( Material.OBSIDIAN, ModTags.OBSIDIAN_INGREDIENTS, Items.CRYING_OBSIDIAN, "crying_obsidian"),
    OBSIDIAN( Material.OBSIDIAN, ModTags.OBSIDIAN_INGREDIENTS, Items.OBSIDIAN, "obsidian"),




            ;

    public static Item[] getCraftingIngredients() {
        return Arrays.stream(values()).filter( e -> ! e.equals( NETHERITE ) ).map( e -> e.material.getMatchingStacks()[0].getItem()).toArray(Item[]::new);
    }

    Ingredient materialGroup, material;

    String name;
    boolean isDefault;
    int enchantability, durrability;
    float miningSpeed;
    TagKey<Block> inverseTag;
    String groupId;

    ModToolHeadMaterial(int enchantability, int durrability, float miningSpeed, TagKey<Block> inverseTag, Ingredient materialGroup, Item material, String name, String groupId, boolean isDefault ) {
        this.enchantability = enchantability;
        this.durrability = durrability;
        this.miningSpeed = miningSpeed;
        this.inverseTag = inverseTag;
        this.materialGroup = materialGroup;
        this.material = Ingredient.ofItems( material );
        this.name = name;
        this.isDefault = isDefault;
        this.groupId = groupId;
    }

    ModToolHeadMaterial(Material material, Ingredient group, Item item, String name, String groupId, boolean isDefault ) {
       this( material.enchantability, material.itemDurability, material.miningSpeed, material.inverseTag, group, item, name, groupId, isDefault );
    }

    ModToolHeadMaterial(Material material, TagKey<Item> tag, Item item, String name) {
        this(material, Ingredient.fromTag( tag ), item, name, tag.id().toString(), false);
    }

    ModToolHeadMaterial(Material material, TagKey<Item> tag, Item item, String name, boolean isDefault) {
        this( material, Ingredient.fromTag( tag ), item, name, tag.id().toString(), isDefault);
    }

    ModToolHeadMaterial(Material material, Item item, String name) {
        this(material, Ingredient.ofItems( item ), item, name, item.toString(), true);
    }

    public static String getSpriteText( ItemStack i, ModToolComponent modToolComponent) {
        return getMaterial(i).getSpriteText(modToolComponent);
    }

    public String getSpriteText( ModToolComponent modToolComponent) {
        return "btools:item/tool_heads/" + name + "/" + name + "_" + modToolComponent.suffix;
    }

    public static ModToolHeadMaterial getMaterial(ItemStack i ) {
        Optional<ModToolHeadMaterial> optional = Arrays.stream( ModToolHeadMaterial.values() ).filter(v -> v.material.test( i ) ).findFirst();
        if (optional.isEmpty())
            optional = Arrays.stream( ModToolHeadMaterial.values() ).filter(v -> v.isDefault).filter(v -> v.materialGroup.test( i ) ).findFirst();
        return optional.orElse( OAK );
    }


    @Override
    public int getDurability() {
        return durrability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return miningSpeed;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return inverseTag;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return materialGroup;
    }

    public String getGroupId() {
        return groupId;
    }

    // NOT NECESSARY.
    @Override
    public float getAttackDamage() {
        return 0;
    }

    private enum Material {
        WOOD(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 59, 2.0F, 15),
        STONE(BlockTags.INCORRECT_FOR_STONE_TOOL, 131, 4.0F, 5),
        IRON(BlockTags.INCORRECT_FOR_IRON_TOOL, 250, 6.0F,  14),
        DIAMOND(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1561, 8.0F, 10),
        NETHERITE(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2031, 9.0F, 15),

        // renamed GOLD...
        SOFT_METAL(BlockTags.INCORRECT_FOR_GOLD_TOOL, 32, 12.0F, 22),

        // MODDED MATERIALS:
        SOFT_GEMSTONES(BlockTags.INCORRECT_FOR_STONE_TOOL, 59, 9.0F, 15),
        SOFT_STONES(BlockTags.INCORRECT_FOR_STONE_TOOL, 78, 4.0F, 5),
        OBSIDIAN(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2031, 4.0F, 5),


        ;

        final TagKey<Block> inverseTag;
        final int itemDurability;
        final float miningSpeed;
        final int enchantability;

        Material(
                final TagKey<Block> inverseTag,
                final int itemDurability,
                final float miningSpeed,
                final int enchantability
        ) {
            this.inverseTag = inverseTag;
            this.itemDurability = itemDurability;
            this.miningSpeed = miningSpeed;
            this.enchantability = enchantability;
        }
    }
}
