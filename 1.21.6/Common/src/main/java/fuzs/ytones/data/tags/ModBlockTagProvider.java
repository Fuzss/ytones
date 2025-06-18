package fuzs.ytones.data.tags;

import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagProvider;
import fuzs.ytones.init.ModRegistry;
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
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModRegistry.YTONE_BLOCK.value());
        Tone.forEach((Tone tone, ToneType toneType) -> {
            this.tag(tone.getBlockTagKey()).add(tone.block(toneType));
        });
        for (Tone tone : Tone.values()) {
            if (tone.getToolTagKey() != null) {
                this.tag(tone.getToolTagKey()).addTag(tone.getBlockTagKey());
            }
        }
    }
}
