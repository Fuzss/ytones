package fuzs.ytones.data;

import fuzs.puzzleslib.api.data.v1.AbstractLootProvider;
import fuzs.ytones.init.ModRegistry;
import fuzs.ytones.world.level.block.Tone;
import net.minecraftforge.data.event.GatherDataEvent;

public class ModBlockLootProvider extends AbstractLootProvider.Blocks {

    public ModBlockLootProvider(GatherDataEvent evt, String modId) {
        super(evt, modId);
    }

    @Override
    public void generate() {
        this.dropSelf(ModRegistry.STONE_TILE_BLOCK.value());
        this.dropSelf(ModRegistry.FLAT_LAMP_BLOCK.value());
        Tone.forEach((tone, toneType) -> {
            this.dropSelf(tone.block(toneType));
        });
    }
}
