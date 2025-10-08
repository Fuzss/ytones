package fuzs.ytones.init;

import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import fuzs.puzzleslib.api.init.v3.tags.TagFactory;
import fuzs.ytones.Ytones;
import fuzs.ytones.world.level.block.FlatLampBlock;
import fuzs.ytones.world.level.block.Tone;
import fuzs.ytones.world.level.block.ToneType;
import net.minecraft.core.Holder;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModRegistry {
    static final RegistryManager REGISTRIES = RegistryManager.from(Ytones.MOD_ID);
    public static final Holder.Reference<Block> YTONE_BLOCK = REGISTRIES.registerBlock("ytone",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final Holder.Reference<Block> FLAT_LAMP_BLOCK = REGISTRIES.registerBlock("flat_lamp",
            FlatLampBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_LAMP).isValidSpawn(Blocks::never));
    public static final Holder.Reference<Item> STONE_TILE_ITEM = REGISTRIES.registerBlockItem(YTONE_BLOCK);
    public static final Holder.Reference<Item> FLAT_LAMP_ITEM = REGISTRIES.registerBlockItem(FLAT_LAMP_BLOCK);
    public static final Holder.Reference<CreativeModeTab> CREATIVE_MODE_TAB = REGISTRIES.registerCreativeModeTab("main",
            () -> new ItemStack(FLAT_LAMP_ITEM),
            (CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) -> {
                output.accept(ModRegistry.FLAT_LAMP_ITEM.value());
                output.accept(ModRegistry.STONE_TILE_ITEM.value());
                Tone.forEach((Tone tone, ToneType toneType) -> {
                    output.accept(tone.block(toneType));
                });
            },
            true);

    public static final TagFactory TAGS = TagFactory.make(Ytones.MOD_ID);

    public static void bootstrap() {
        Tone.forEach((Tone tone, ToneType type) -> {
            Holder.Reference<Block> block = REGISTRIES.registerBlock(tone.id(type),
                    (BlockBehaviour.Properties properties) -> tone.createBlock(type, properties),
                    tone::createBlockProperties);
            REGISTRIES.registerBlockItem(block);
        });
    }
}
