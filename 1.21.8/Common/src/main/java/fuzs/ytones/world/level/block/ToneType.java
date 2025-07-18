package fuzs.ytones.world.level.block;

import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;

import java.util.Locale;
import java.util.function.IntFunction;

public enum ToneType implements StringRepresentable {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    ELEVEN,
    TWELVE,
    THIRTEEN,
    FOURTEEN,
    FIFTEEN,
    SIXTEEN;

    public static final StringRepresentable.EnumCodec<ToneType> CODEC = StringRepresentable.fromEnum(ToneType::values);

    private static final char BASE_TEXT = '\u2460';
    private static final IntFunction<ToneType> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.WRAP);

    public String id() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    public String getName() {
        return String.valueOf((char) (BASE_TEXT + this.ordinal()));
    }

    public ToneType cycle(int value) {
        return BY_ID.apply(this.ordinal() + value);
    }

    @Override
    public String getSerializedName() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}
