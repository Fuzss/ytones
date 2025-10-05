package fuzs.ytones.data;

import fuzs.puzzleslib.api.data.v2.AbstractRecipeProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.ytones.Ytones;
import fuzs.ytones.init.ModRegistry;
import fuzs.ytones.world.level.block.Tone;
import fuzs.ytones.world.level.block.ToneType;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(this.items(), RecipeCategory.REDSTONE, ModRegistry.FLAT_LAMP_BLOCK.value())
                .define('#', Blocks.SMOOTH_STONE_SLAB)
                .define('X', Items.IRON_INGOT)
                .define('@', Ingredient.of(Items.GLOWSTONE, Items.GLOW_INK_SAC))
                .pattern("X@X")
                .pattern("###")
                .unlockedBy(getHasName(Items.GLOWSTONE), this.has(Items.GLOWSTONE))
                .unlockedBy(getHasName(Items.GLOW_INK_SAC), this.has(Items.GLOW_INK_SAC))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(this.items(), RecipeCategory.BUILDING_BLOCKS, ModRegistry.YTONE_BLOCK.value(), 8)
                .define('#', Blocks.STONE)
                .define('@', Blocks.SMOOTH_STONE_SLAB)
                .pattern("###")
                .pattern("#@#")
                .pattern("###")
                .unlockedBy(getHasName(Blocks.SMOOTH_STONE_SLAB), this.has(Blocks.SMOOTH_STONE_SLAB))
                .save(recipeOutput);
        ToneType toneTypeX = ToneType.values()[0];
        for (Tone tone : Tone.values()) {
            ShapedRecipeBuilder.shaped(this.items(), RecipeCategory.BUILDING_BLOCKS, tone.block(toneTypeX), 8)
                    .define('#', ModRegistry.YTONE_BLOCK.value())
                    .define('@', tone.getIngredient(this.items()))
                    .pattern("###")
                    .pattern("#@#")
                    .pattern("###")
                    .unlockedBy(getHasName(ModRegistry.YTONE_BLOCK.value()), this.has(ModRegistry.YTONE_BLOCK.value()))
                    .save(recipeOutput);
        }
        Tone.forEach((Tone tone, ToneType toneType) -> {
            this.stonecutterResultFromBase(recipeOutput, tone, toneType);
        });
    }

    public void stonecutterResultFromBase(RecipeOutput recipeOutput, Tone tone, ToneType toneType) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(this.items().getOrThrow(tone.getItemTagKey())),
                        RecipeCategory.BUILDING_BLOCKS,
                        tone.block(toneType))
                .unlockedBy("has_" + tone.id(), this.has(tone.getItemTagKey()))
                .save(recipeOutput,
                        ResourceKey.create(Registries.RECIPE, Ytones.id(tone.id(toneType) + "_stonecutting")));
    }
}
