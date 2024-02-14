package fuzs.ytones.world.level.block;

import net.minecraft.util.ByIdMap;
import net.minecraft.world.entity.monster.SpellcasterIllager;

import java.util.Locale;
import java.util.function.IntFunction;

public enum ToneType {
    ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, ELEVEN, TWELVE, THIRTEEN, FOURTEEN, FIFTEEN, SIXTEEN;

    private static final char BASE_TEXT = '\u2460';
    private static final IntFunction<ToneType> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.WRAP);

    public String id() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    public String text() {
        return String.valueOf((char) (BASE_TEXT + this.ordinal()));
    }

    public ToneType cycle(int value) {
        return BY_ID.apply(this.ordinal() + value);
    }
}
