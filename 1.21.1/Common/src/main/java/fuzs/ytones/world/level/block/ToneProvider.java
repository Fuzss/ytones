package fuzs.ytones.world.level.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public interface ToneProvider {

    Tone tone();

    ToneType type();

    static ItemStack cycle(ItemStack itemStack, int value) {
        ToneProvider provider = (ToneProvider) ((BlockItem) itemStack.getItem()).getBlock();
        Block block = provider.tone().block(provider.type().cycle(value));
        return itemStack.transmuteCopy(block);
    }
}
