package fuzs.ytones.world.level.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.TransparentBlock;

public class ToneGlassBlock extends TransparentBlock implements ToneProvider {
    public static final MapCodec<ToneGlassBlock> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return instance.group(Tone.CODEC.fieldOf("tone").forGetter(ToneProvider::tone),
                ToneType.CODEC.fieldOf("tone_type").forGetter(ToneProvider::type),
                propertiesCodec()
        ).apply(instance, ToneGlassBlock::new);
    });

    private final Tone tone;
    private final ToneType type;

    protected ToneGlassBlock(Tone tone, ToneType type, Properties properties) {
        super(properties);
        this.tone = tone;
        this.type = type;
    }

    @Override
    protected MapCodec<? extends TransparentBlock> codec() {
        return CODEC;
    }

    @Override
    public Tone tone() {
        return this.tone;
    }

    @Override
    public ToneType type() {
        return this.type;
    }
}
