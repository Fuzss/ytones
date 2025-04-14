package fuzs.ytones.data;

import fuzs.puzzleslib.api.data.v2.AbstractLootProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.ytones.init.ModRegistry;
import fuzs.ytones.world.level.block.Tone;

public class ModBlockLootProvider extends AbstractLootProvider.Blocks {

    public ModBlockLootProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addLootTables() {
        this.dropSelf(ModRegistry.YTONE_BLOCK.value());
        this.dropSelf(ModRegistry.FLAT_LAMP_BLOCK.value());
        Tone.forEach((tone, toneType) -> {
            this.dropSelf(tone.block(toneType));
        });
    }
}
