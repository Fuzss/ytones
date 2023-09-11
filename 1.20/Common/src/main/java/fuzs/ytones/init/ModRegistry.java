package fuzs.ytones.init;

import fuzs.puzzleslib.api.init.v3.RegistryManager;
import fuzs.ytones.Ytones;
import fuzs.ytones.world.level.block.FlatLampBlock;
import fuzs.ytones.world.level.block.Tone;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModRegistry {
    static final RegistryManager REGISTRY = RegistryManager.from(Ytones.MOD_ID);
    public static final Holder.Reference<Block> STONE_TILE_BLOCK = REGISTRY.registerBlock("stone_tile", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final Holder.Reference<Block> FLAT_LAMP_BLOCK = REGISTRY.registerBlock("flat_lamp", () -> new FlatLampBlock(BlockBehaviour.Properties.copy(Blocks.REDSTONE_LAMP).isValidSpawn((state, blockGetter, pos, entity) -> false)));
    public static final Holder.Reference<Item> STONE_TILE_ITEM = REGISTRY.registerBlockItem(STONE_TILE_BLOCK);
    public static final Holder.Reference<Item> FLAT_LAMP_ITEM = REGISTRY.registerBlockItem(FLAT_LAMP_BLOCK);

    public static void touch() {
        Tone.forEach((tone, toneType) -> {
            REGISTRY.registerBlockItem(REGISTRY.registerBlock(tone.id(toneType), () -> tone.factory.apply(toneType)));
        });
    }
}
