package fuzs.ytones.tags;

import fuzs.puzzleslib.api.init.v3.tags.TagFactory;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class DyeItemTags {
    static final TagFactory TAGS = TagFactory.make("c");
    public static final TagKey<Item> DYES = TAGS.registerItemTag("dyes");
    public static final TagKey<Item> WHITE_DYES = TAGS.registerItemTag("dyes/white");
    public static final TagKey<Item> ORANGE_DYES = TAGS.registerItemTag("dyes/orange");
    public static final TagKey<Item> MAGENTA_DYES = TAGS.registerItemTag("dyes/magenta");
    public static final TagKey<Item> LIGHT_BLUE_DYES = TAGS.registerItemTag("dyes/light_blue");
    public static final TagKey<Item> YELLOW_DYES = TAGS.registerItemTag("dyes/yellow");
    public static final TagKey<Item> LIME_DYES = TAGS.registerItemTag("dyes/lime");
    public static final TagKey<Item> PINK_DYES = TAGS.registerItemTag("dyes/pink");
    public static final TagKey<Item> GRAY_DYES = TAGS.registerItemTag("dyes/gray");
    public static final TagKey<Item> LIGHT_GRAY_DYES = TAGS.registerItemTag("dyes/light_gray");
    public static final TagKey<Item> CYAN_DYES = TAGS.registerItemTag("dyes/cyan");
    public static final TagKey<Item> PURPLE_DYES = TAGS.registerItemTag("dyes/purple");
    public static final TagKey<Item> BLUE_DYES = TAGS.registerItemTag("dyes/blue");
    public static final TagKey<Item> BROWN_DYES = TAGS.registerItemTag("dyes/brown");
    public static final TagKey<Item> GREEN_DYES = TAGS.registerItemTag("dyes/green");
    public static final TagKey<Item> RED_DYES = TAGS.registerItemTag("dyes/red");
    public static final TagKey<Item> BLACK_DYES = TAGS.registerItemTag("dyes/black");

    private DyeItemTags() {
        // NO-OP
    }
}
