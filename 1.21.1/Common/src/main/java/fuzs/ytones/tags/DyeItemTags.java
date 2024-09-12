package fuzs.ytones.tags;

import fuzs.puzzleslib.api.init.v3.tags.BoundTagFactory;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class DyeItemTags {
    public static final TagKey<Item> DYES = BoundTagFactory.COMMON.registerItemTag("dyes");
    public static final TagKey<Item> WHITE_DYES = BoundTagFactory.COMMON.registerItemTag("dyes/white");
    public static final TagKey<Item> ORANGE_DYES = BoundTagFactory.COMMON.registerItemTag("dyes/orange");
    public static final TagKey<Item> MAGENTA_DYES = BoundTagFactory.COMMON.registerItemTag("dyes/magenta");
    public static final TagKey<Item> LIGHT_BLUE_DYES = BoundTagFactory.COMMON.registerItemTag("dyes/light_blue");
    public static final TagKey<Item> YELLOW_DYES = BoundTagFactory.COMMON.registerItemTag("dyes/yellow");
    public static final TagKey<Item> LIME_DYES = BoundTagFactory.COMMON.registerItemTag("dyes/lime");
    public static final TagKey<Item> PINK_DYES = BoundTagFactory.COMMON.registerItemTag("dyes/pink");
    public static final TagKey<Item> GRAY_DYES = BoundTagFactory.COMMON.registerItemTag("dyes/gray");
    public static final TagKey<Item> LIGHT_GRAY_DYES = BoundTagFactory.COMMON.registerItemTag("dyes/light_gray");
    public static final TagKey<Item> CYAN_DYES = BoundTagFactory.COMMON.registerItemTag("dyes/cyan");
    public static final TagKey<Item> PURPLE_DYES = BoundTagFactory.COMMON.registerItemTag("dyes/purple");
    public static final TagKey<Item> BLUE_DYES = BoundTagFactory.COMMON.registerItemTag("dyes/blue");
    public static final TagKey<Item> BROWN_DYES = BoundTagFactory.COMMON.registerItemTag("dyes/brown");
    public static final TagKey<Item> GREEN_DYES = BoundTagFactory.COMMON.registerItemTag("dyes/green");
    public static final TagKey<Item> RED_DYES = BoundTagFactory.COMMON.registerItemTag("dyes/red");
    public static final TagKey<Item> BLACK_DYES = BoundTagFactory.COMMON.registerItemTag("dyes/black");

    private DyeItemTags() {
        // NO-OP
    }
}
