package fuzs.ytones.world.level.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.Block;

public class ToneBlock extends Block implements ToneProvider {
    public static final MapCodec<ToneBlock> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return instance.group(Tone.CODEC.fieldOf("tone").forGetter(ToneProvider::tone),
                ToneType.CODEC.fieldOf("tone_type").forGetter(ToneProvider::type),
                propertiesCodec()
        ).apply(instance, ToneBlock::new);
    });

    private final Tone tone;
    private final ToneType type;

    public ToneBlock(Tone tone, ToneType type, Properties properties) {
        super(properties);
        this.tone = tone;
        this.type = type;
    }

    @Override
    protected MapCodec<? extends Block> codec() {
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
