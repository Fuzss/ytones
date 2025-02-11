package fuzs.ytones.data.client;

import fuzs.puzzleslib.api.client.data.v2.AbstractModelProvider;
import fuzs.puzzleslib.api.client.data.v2.models.ModelTemplateHelper;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.ytones.Ytones;
import fuzs.ytones.init.ModRegistry;
import fuzs.ytones.world.level.block.Tone;
import fuzs.ytones.world.level.block.ToneType;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.blockstates.Variant;
import net.minecraft.client.data.models.blockstates.VariantProperties;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class ModModelProvider extends AbstractModelProvider {
    public static final ModelTemplate FLAT_LAMP_TEMPLATE = ModelTemplateHelper.createBlockModelTemplate(Ytones.id(
            "template_flat_lamp"), TextureSlot.TOP, TextureSlot.SIDE, TextureSlot.BOTTOM, TextureSlot.PARTICLE);

    public ModModelProvider(DataProviderContext context) {
        super(context);
    }

    public static TextureMapping flatLamp(Block block, String suffix) {
        return new TextureMapping().put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, suffix))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side"))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(block, "_bottom"))
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block, suffix));
    }

    @Override
    public void addBlockModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube(ModRegistry.YTONE_BLOCK.value());
        Tone.forEach((Tone tone, ToneType toneType) -> {
            blockModelGenerators.createTrivialCube(tone.block(toneType));
        });
        this.createFlatLamp(ModRegistry.FLAT_LAMP_BLOCK.value(), blockModelGenerators);
    }

    public final void createFlatLamp(Block block, BlockModelGenerators blockModelGenerators) {
        ResourceLocation onModel = FLAT_LAMP_TEMPLATE.createWithSuffix(block,
                "_on",
                flatLamp(block, "_on"),
                blockModelGenerators.modelOutput);
        ResourceLocation offModel = FLAT_LAMP_TEMPLATE.createWithSuffix(block,
                "_off",
                flatLamp(block, "_off"),
                blockModelGenerators.modelOutput);
        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block)
                .with(blockModelGenerators.createColumnWithFacing())
                .with(PropertyDispatch.property(BlockStateProperties.LIT)
                        .select(Boolean.FALSE, Variant.variant().with(VariantProperties.MODEL, offModel))
                        .select(Boolean.TRUE, Variant.variant().with(VariantProperties.MODEL, onModel))));
        blockModelGenerators.registerSimpleItemModel(block, onModel);
    }
}
