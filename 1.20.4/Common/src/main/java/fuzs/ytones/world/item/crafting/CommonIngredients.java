package fuzs.ytones.world.item.crafting;

import fuzs.ytones.tags.FabricItemTags;
import fuzs.ytones.tags.ForgeItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.stream.Stream;

@SuppressWarnings("unchecked")
public final class CommonIngredients {
    public static final Ingredient DYES = tags(FabricItemTags.DYES, ForgeItemTags.DYES);
    public static final Ingredient WHITE_DYES = tags(FabricItemTags.WHITE_DYES, ForgeItemTags.WHITE_DYES);
    public static final Ingredient ORANGE_DYES = tags(FabricItemTags.ORANGE_DYES, ForgeItemTags.ORANGE_DYES);
    public static final Ingredient MAGENTA_DYES = tags(FabricItemTags.MAGENTA_DYES, ForgeItemTags.MAGENTA_DYES);
    public static final Ingredient LIGHT_BLUE_DYES = tags(FabricItemTags.LIGHT_BLUE_DYES, ForgeItemTags.LIGHT_BLUE_DYES);
    public static final Ingredient YELLOW_DYES = tags(FabricItemTags.YELLOW_DYES, ForgeItemTags.YELLOW_DYES);
    public static final Ingredient LIME_DYES = tags(FabricItemTags.LIME_DYES, ForgeItemTags.LIME_DYES);
    public static final Ingredient PINK_DYES = tags(FabricItemTags.PINK_DYES, ForgeItemTags.PINK_DYES);
    public static final Ingredient GRAY_DYES = tags(FabricItemTags.GRAY_DYES, ForgeItemTags.GRAY_DYES);
    public static final Ingredient LIGHT_GRAY_DYES = tags(FabricItemTags.LIGHT_GRAY_DYES, ForgeItemTags.LIGHT_GRAY_DYES);
    public static final Ingredient CYAN_DYES = tags(FabricItemTags.CYAN_DYES, ForgeItemTags.CYAN_DYES);
    public static final Ingredient PURPLE_DYES = tags(FabricItemTags.PURPLE_DYES, ForgeItemTags.PURPLE_DYES);
    public static final Ingredient BLUE_DYES = tags(FabricItemTags.BLUE_DYES, ForgeItemTags.BLUE_DYES);
    public static final Ingredient BROWN_DYES = tags(FabricItemTags.BROWN_DYES, ForgeItemTags.BROWN_DYES);
    public static final Ingredient GREEN_DYES = tags(FabricItemTags.GREEN_DYES, ForgeItemTags.GREEN_DYES);
    public static final Ingredient RED_DYES = tags(FabricItemTags.RED_DYES, ForgeItemTags.RED_DYES);
    public static final Ingredient BLACK_DYES = tags(FabricItemTags.BLACK_DYES, ForgeItemTags.BLACK_DYES);

    private CommonIngredients() {

    }

    private static Ingredient tags(TagKey<Item>... tagKeys) {
        return CombinedIngredients.INSTANCE.any(Stream.of(tagKeys).map(Ingredient::of).toArray(Ingredient[]::new));
    }
}
