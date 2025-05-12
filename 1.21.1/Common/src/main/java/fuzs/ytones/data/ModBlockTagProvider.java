package fuzs.ytones.data;

import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagProvider;
import fuzs.ytones.world.level.block.Tone;
import fuzs.ytones.world.level.block.ToneType;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

public class ModBlockTagProvider extends AbstractTagProvider<Block> {

    public ModBlockTagProvider(DataProviderContext context) {
        super(Registries.BLOCK, context);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        Tone.forEach((Tone tone, ToneType toneType) -> {
            this.add(tone.getBlockTagKey()).add(tone.block(toneType));
        });
        for (Tone tone : Tone.values()) {
            this.add(BlockTags.MINEABLE_WITH_PICKAXE).addTag(tone.getBlockTagKey());
        }
    }
}
