package fuzs.ytones.world.level.block;

import com.google.common.collect.Maps;
import fuzs.ytones.Ytones;
import fuzs.ytones.tags.TagFactory;
import fuzs.ytones.world.item.crafting.CommonIngredients;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public enum Tone implements StringRepresentable {
    AGON(Items.WHITE_CONCRETE, Items.ORANGE_CONCRETE, Items.MAGENTA_CONCRETE, Items.LIGHT_BLUE_CONCRETE, Items.YELLOW_CONCRETE, Items.LIME_CONCRETE, Items.PINK_CONCRETE, Items.GRAY_CONCRETE, Items.LIGHT_GRAY_CONCRETE, Items.CYAN_CONCRETE, Items.PURPLE_CONCRETE, Items.BLUE_CONCRETE, Items.BROWN_CONCRETE, Items.GREEN_CONCRETE, Items.RED_CONCRETE, Items.BLACK_CONCRETE), AZUR(CommonIngredients.BLUE_DYES), BITT(Items.COAL), CRAY(ItemTags.TERRACOTTA), FORT(Blocks.TUFF), GLAXX(Ingredient.of(Items.GLASS), (tone, type) -> new ToneGlassBlock(tone, type, BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS))), ISZM(Blocks.DEEPSLATE), JELT(CommonIngredients.ORANGE_DYES), KORP(Blocks.GRAVEL), KRYP(Blocks.DIRT), LAIR(Blocks.GRANITE), LAVE(CommonIngredients.LIGHT_BLUE_DYES), MINT(CommonIngredients.LIME_DYES), MYST(Blocks.SNOW), REDS(CommonIngredients.RED_DYES), REED(Blocks.SUGAR_CANE), ROEN(Blocks.SAND), SOLS(CommonIngredients.YELLOW_DYES), SYNC(CommonIngredients.GREEN_DYES), TANK(CommonIngredients.GRAY_DYES), VECT(CommonIngredients.BLACK_DYES), VENA(Blocks.ANDESITE), ZANE(Blocks.CALCITE), ZECH(Items.CHARCOAL), ZEST(Blocks.MUD), ZETA(Blocks.BASALT), ZION(Blocks.SMOOTH_BASALT), ZKUL(Items.BONE), ZOEA(Blocks.DIORITE), ZOME(Blocks.COBBLESTONE), ZONE(Blocks.COBBLED_DEEPSLATE), ZORG(Blocks.CLAY), ZTYL(CommonIngredients.LIGHT_GRAY_DYES), ZYTH(Blocks.BLACKSTONE);

    public static final StringRepresentable.EnumCodec<Tone> CODEC = StringRepresentable.fromEnum(Tone::values);

    public final Ingredient ingredient;
    public final Function<ToneType, Block> factory;
    private final Map<ToneType, Block> blocks = Maps.newEnumMap(ToneType.class);

    Tone(ItemLike... item) {
        this(Ingredient.of(item));
    }

    Tone(TagKey<Item> tagKey) {
        this(Ingredient.of(tagKey));
    }

    Tone(Ingredient ingredient) {
        this(ingredient, (tone, type) -> new ToneBlock(tone, type, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    }

    Tone(Ingredient ingredient, BiFunction<Tone, ToneType, Block> factory) {
        this.ingredient = ingredient;
        this.factory = type -> factory.apply(this, type);
    }

    @Override
    public String getSerializedName() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    public static void forEach(BiConsumer<Tone, ToneType> consumer) {
        for (Tone tone : Tone.values()) {
            for (ToneType type : ToneType.values()) {
                consumer.accept(tone, type);
            }
        }
    }

    public String id() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    public String id(ToneType type) {
        return this.id() + "_" + type.id();
    }

    public String text() {
        return StringUtils.capitalize(this.name().toLowerCase(Locale.ROOT));
    }

    public String text(ToneType type) {
        return this.text() + " " + type.text();
    }

    public Block block(ToneType type) {
        return this.blocks.computeIfAbsent(type, $ -> BuiltInRegistries.BLOCK.get(Ytones.id(this.id(type))));
    }

    public TagKey<Item> tagKey() {
        return TagFactory.ITEM.make(Ytones.MOD_ID, this.id());
    }
}
