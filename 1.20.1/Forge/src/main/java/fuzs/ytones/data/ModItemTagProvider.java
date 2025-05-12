package fuzs.ytones.data;

import fuzs.puzzleslib.api.data.v1.AbstractTagProvider;
import fuzs.ytones.world.level.block.Tone;
import net.minecraft.core.HolderLookup;
import net.minecraftforge.data.event.GatherDataEvent;

public class ModItemTagProvider extends AbstractTagProvider.Items {

    public ModItemTagProvider(GatherDataEvent evt, String modId) {
        super(evt, modId);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        Tone.forEach((tone, toneType) -> {
            this.tag(tone.getItemTagKey()).add(tone.block(toneType).asItem());
        });
    }
}
