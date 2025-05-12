package fuzs.ytones.world.level.block;

import fuzs.puzzleslib.api.init.v3.tags.TagFactory;
import fuzs.ytones.Ytones;
import fuzs.ytones.init.ModRegistry;
import fuzs.ytones.tags.DyeItemTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
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
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public enum Tone implements StringRepresentable {
    AGON(TagFactory.COMMON.registerItemTag("concretes")),
    AZUR(DyeItemTags.BLUE_DYES),
    BITT(Items.COAL),
    CRAY(ItemTags.TERRACOTTA),
    FORT(Blocks.TUFF),
    GLAXX((HolderGetter<Item> itemLookup) -> Ingredient.of(Items.GLASS), null) {
        @Override
        public Block createBlock(ToneType type, BlockBehaviour.Properties properties) {
            return new ToneGlassBlock(this, type, properties);
        }

        @Override
        public BlockBehaviour.Properties createBlockProperties() {
            return BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS);
        }
    },
    ISZM(Blocks.DEEPSLATE),
    JELT(DyeItemTags.ORANGE_DYES),
    KORP(Blocks.GRAVEL),
    KRYP(Blocks.DIRT),
    LAIR(Blocks.GRANITE),
    LAVE(DyeItemTags.LIGHT_BLUE_DYES),
    MINT(DyeItemTags.LIME_DYES),
    MYST(Blocks.SNOW),
    REDS(DyeItemTags.RED_DYES),
    REED(Blocks.SUGAR_CANE),
    ROEN(Blocks.SAND),
    SOLS(DyeItemTags.YELLOW_DYES),
    SYNC(DyeItemTags.GREEN_DYES),
    TANK(DyeItemTags.GRAY_DYES),
    VECT(DyeItemTags.BLACK_DYES),
    VENA(Blocks.ANDESITE),
    ZANE(Blocks.CALCITE),
    ZECH(Items.CHARCOAL),
    ZEST(Blocks.MUD),
    ZETA(Blocks.BASALT),
    ZION(Blocks.SMOOTH_BASALT),
    ZKUL(Items.BONE),
    ZOEA(Blocks.DIORITE),
    ZOME(Blocks.COBBLESTONE),
    ZONE(Blocks.COBBLED_DEEPSLATE),
    ZORG(Blocks.CLAY),
    ZTYL(DyeItemTags.LIGHT_GRAY_DYES),
    ZYTH(Blocks.BLACKSTONE);

    public static final StringRepresentable.StringRepresentableCodec<Tone> CODEC = StringRepresentable.fromEnum(Tone::values);

    private final Function<HolderGetter<Item>, Ingredient> ingredientFactory;
    @Nullable
    private final TagKey<Block> toolTagKey;
    private final TagKey<Block> blockTagKey;
    private final TagKey<Item> itemTagKey;
    private final Map<ToneType, Block> blocks = new EnumMap<>(ToneType.class);

    Tone(ItemLike... item) {
        this((HolderGetter<Item> itemLookup) -> Ingredient.of(item), BlockTags.MINEABLE_WITH_PICKAXE);
    }

    Tone(TagKey<Item> tagKey) {
        this((HolderGetter<Item> itemLookup) -> Ingredient.of(itemLookup.getOrThrow(tagKey)),
                BlockTags.MINEABLE_WITH_PICKAXE);
    }

    Tone(Function<HolderGetter<Item>, Ingredient> ingredientFactory, @Nullable TagKey<Block> toolTagKey) {
        this.ingredientFactory = ingredientFactory;
        this.toolTagKey = toolTagKey;
        this.blockTagKey = ModRegistry.TAGS.registerBlockTag(this.id());
        this.itemTagKey = ModRegistry.TAGS.registerItemTag(this.id());
    }

    public static void forEach(BiConsumer<Tone, ToneType> consumer) {
        for (Tone tone : Tone.values()) {
            for (ToneType type : ToneType.values()) {
                consumer.accept(tone, type);
            }
        }
    }

    @Override
    public String getSerializedName() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    public Ingredient getIngredient(HolderGetter<Item> items) {
        return this.ingredientFactory.apply(items);
    }

    public Block createBlock(ToneType type, BlockBehaviour.Properties properties) {
        return new ToneBlock(this, type, properties);
    }

    public BlockBehaviour.Properties createBlockProperties() {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.STONE);
    }

    public String id() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    public String id(ToneType toneType) {
        return this.id() + "_" + toneType.id();
    }

    public String getName() {
        return StringUtils.capitalize(this.name().toLowerCase(Locale.ROOT));
    }

    public String getName(ToneType toneType) {
        return this.getName() + " " + toneType.getName();
    }

    public Block block(ToneType toneType) {
        return this.blocks.computeIfAbsent(toneType,
                (ToneType toneTypeX) -> BuiltInRegistries.BLOCK.getValue(Ytones.id(this.id(toneTypeX))));
    }

    public @Nullable TagKey<Block> getToolTagKey() {
        return this.toolTagKey;
    }

    public TagKey<Block> getBlockTagKey() {
        return this.blockTagKey;
    }

    public TagKey<Item> getItemTagKey() {
        return this.itemTagKey;
    }
}
