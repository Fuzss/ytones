package fuzs.ytones.data;

import fuzs.puzzleslib.api.data.v2.AbstractLootProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.ytones.init.ModRegistry;
import fuzs.ytones.world.level.block.Tone;
import fuzs.ytones.world.level.block.ToneType;

public class ModBlockLootProvider extends AbstractLootProvider.Blocks {

    public ModBlockLootProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addLootTables() {
        this.dropSelf(ModRegistry.YTONE_BLOCK.value());
        this.dropSelf(ModRegistry.FLAT_LAMP_BLOCK.value());
        Tone.forEach((Tone tone, ToneType toneType) -> {
            this.dropSelf(tone.block(toneType));
        });
    }
}
