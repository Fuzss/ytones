package fuzs.ytones.data;

import fuzs.puzzleslib.api.data.v1.AbstractRecipeProvider;
import fuzs.ytones.Ytones;
import fuzs.ytones.init.ModRegistry;
import fuzs.ytones.world.level.block.Tone;
import fuzs.ytones.world.level.block.ToneType;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.data.event.GatherDataEvent;

import java.util.function.Consumer;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(GatherDataEvent evt, String modId) {
        super(evt, modId);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> exporter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModRegistry.FLAT_LAMP_BLOCK.value())
                .define('#', Blocks.SMOOTH_STONE_SLAB)
                .define('X', Items.IRON_INGOT)
                .define('@', Ingredient.of(Blocks.GLOWSTONE, Items.GLOW_INK_SAC))
                .pattern("X@X")
                .pattern("###")
                .unlockedBy(getHasName(Blocks.GLOWSTONE, Items.GLOW_INK_SAC), has(Blocks.GLOWSTONE, Items.GLOW_INK_SAC))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModRegistry.STONE_TILE_BLOCK.value(), 8)
                .define('#', Blocks.STONE)
                .define('@', Blocks.SMOOTH_STONE_SLAB)
                .pattern("###")
                .pattern("#@#")
                .pattern("###")
                .unlockedBy(getHasName(Blocks.SMOOTH_STONE_SLAB), has(Blocks.SMOOTH_STONE_SLAB))
                .save(exporter);
        ToneType type = ToneType.values()[0];
        for (Tone tone : Tone.values()) {
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, tone.block(type), 8)
                    .define('#', ModRegistry.STONE_TILE_BLOCK.value())
                    .define('@', tone.ingredient)
                    .pattern("###")
                    .pattern("#@#")
                    .pattern("###")
                    .unlockedBy(getHasName(ModRegistry.STONE_TILE_BLOCK.value()), has(ModRegistry.STONE_TILE_BLOCK.value()))
                    .save(exporter);
        }
        Tone.forEach((tone, toneType) -> {
            Block result = tone.block(toneType);
            SingleItemRecipeBuilder.stonecutting(Ingredient.of(tone.tagKey()), RecipeCategory.BUILDING_BLOCKS, result)
                    .unlockedBy("has_" + tone.id(), has(tone.tagKey()))
                    .save(exporter, Ytones.id(tone.id(toneType) + "_stonecutting"));
        });
    }
}
