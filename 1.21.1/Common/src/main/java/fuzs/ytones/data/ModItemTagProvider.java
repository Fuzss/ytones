package fuzs.ytones.data;

import fuzs.puzzleslib.api.data.v2.AbstractTagProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.ytones.world.level.block.Tone;
import net.minecraft.core.HolderLookup;

public class ModItemTagProvider extends AbstractTagProvider.Items {

    public ModItemTagProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        Tone.forEach((tone, toneType) -> {
            this.tag(tone.tagKey()).add(tone.block(toneType).asItem());
        });
    }
}
