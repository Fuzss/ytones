package fuzs.ytones.world.level.block;

import net.minecraft.world.level.block.Block;

public class ToneBlock extends Block implements ToneProvider {
    private final Tone tone;
    private final ToneType type;

    public ToneBlock(Tone tone, ToneType type, Properties properties) {
        super(properties);
        this.tone = tone;
        this.type = type;
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
