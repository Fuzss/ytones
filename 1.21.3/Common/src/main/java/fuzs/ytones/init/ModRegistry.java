package fuzs.ytones.init;

import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import fuzs.ytones.Ytones;
import fuzs.ytones.world.level.block.FlatLampBlock;
import fuzs.ytones.world.level.block.Tone;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModRegistry {
    static final RegistryManager REGISTRIES = RegistryManager.from(Ytones.MOD_ID);
    public static final Holder.Reference<Block> YTONE_BLOCK = REGISTRIES.registerBlock("ytone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final Holder.Reference<Block> FLAT_LAMP_BLOCK = REGISTRIES.registerBlock("flat_lamp", () -> new FlatLampBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_LAMP)
            .isValidSpawn((state, blockGetter, pos, entity) -> false)));
    public static final Holder.Reference<Item> STONE_TILE_ITEM = REGISTRIES.registerBlockItem(YTONE_BLOCK);
    public static final Holder.Reference<Item> FLAT_LAMP_ITEM = REGISTRIES.registerBlockItem(FLAT_LAMP_BLOCK);

    public static void touch() {
        Tone.forEach((tone, toneType) -> {
            REGISTRIES.registerBlockItem(REGISTRIES.registerBlock(tone.id(toneType), () -> tone.factory.apply(toneType)));
        });
    }
}
