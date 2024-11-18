package org.bsipe.btools;

import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import org.bsipe.btools.block.ForgeBlock;
import org.bsipe.btools.block.RedSoulFireBlock;

import static org.bsipe.btools.BetterToolsModInitializer.MOD_ID;

public class ModBlocks {

    public static void initialize() {

    }

    public static final ForgeBlock DEEPSLATE_FORGE_BLOCK = registerWithItem( "deepslate_forge", new ForgeBlock( AbstractBlock.Settings.create().strength( 1.5f, 6.0f).requiresTool().luminance(Blocks.createLightLevelFromLitBlockState(10))), new Item.Settings());
    public static final Block ENDSTONE_ENDIRIUM_ORE = registerWithItem("endstone_endirium_ore", new ExperienceDroppingBlock(UniformIntProvider.create(3, 7), AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 9.0F)), new Item.Settings() );

    public static final Block RED_SOUL_SAND = registerWithItem(
            "red_soul_sand",
            new SoulSandBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.DARK_RED)
                            .instrument(NoteBlockInstrument.COW_BELL)
                            .strength(0.5F)
                            .velocityMultiplier(0.4F)
                            .sounds(BlockSoundGroup.SOUL_SAND)
                            .allowsSpawning(Blocks::always)
                            .solidBlock(Blocks::always)
                            .blockVision(Blocks::always)
                            .suffocates(Blocks::always)
            ), new Item.Settings()
    );

    public static final Block RED_SOUL_FIRE = register(
            "red_soul_fire",
            new RedSoulFireBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.BRIGHT_RED)
                            .replaceable()
                            .noCollision()
                            .breakInstantly()
                            .nonOpaque()
                            .blockVision(Blocks::never)
                            .luminance(state -> 10)
                            .sounds(BlockSoundGroup.WOOL)
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );

    public static <T extends Block> T register(String name, T block) {
        return Registry.register(Registries.BLOCK, Identifier.of(MOD_ID, name), block);
    }

    public static <T extends Block> T registerWithItem(String name, T block, Item.Settings settings) {
        T registered = register(name, block);
        ModItems.register(new BlockItem(registered, settings), name);
        return registered;
    }
}
