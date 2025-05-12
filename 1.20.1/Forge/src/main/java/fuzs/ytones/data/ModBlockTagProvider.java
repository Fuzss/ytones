package fuzs.ytones.data;

import fuzs.puzzleslib.api.data.v1.AbstractTagProvider;
import fuzs.ytones.world.level.block.Tone;
import fuzs.ytones.world.level.block.ToneType;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.data.event.GatherDataEvent;

public class ModBlockTagProvider extends AbstractTagProvider.Blocks {

    public ModBlockTagProvider(GatherDataEvent evt, String modId) {
        super(evt, modId);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        Tone.forEach((Tone tone, ToneType toneType) -> {
            this.tag(tone.getBlockTagKey()).add(tone.block(toneType));
        });
        for (Tone tone : Tone.values()) {
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(tone.getBlockTagKey());
        }
    }
}
