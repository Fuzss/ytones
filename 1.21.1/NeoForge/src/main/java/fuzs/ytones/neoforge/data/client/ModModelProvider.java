package fuzs.ytones.neoforge.data.client;

import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import fuzs.puzzleslib.neoforge.api.data.v2.client.AbstractModelProvider;
import fuzs.puzzleslib.neoforge.api.data.v2.core.NeoForgeDataProviderContext;
import fuzs.ytones.init.ModRegistry;
import fuzs.ytones.world.level.block.FlatLampBlock;
import fuzs.ytones.world.level.block.Tone;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import org.apache.commons.lang3.ArrayUtils;

public class ModModelProvider extends AbstractModelProvider {

    public ModModelProvider(NeoForgeDataProviderContext context) {
        super(context);
    }

    @Override
    protected void registerStatesAndModels() {
        this.simpleBlockWithItem(ModRegistry.YTONE_BLOCK.value(), this.cubeAll(ModRegistry.YTONE_BLOCK.value()));
        Tone.forEach((tone, toneType) -> {
            Block block = tone.block(toneType);
            ResourceLocation texture = this.blockTexture(block);
            texture = ResourceLocationHelper.fromNamespaceAndPath(texture.getNamespace(), texture.getPath()
                    .replaceAll(toneType.id() + "$", String.valueOf(toneType.ordinal())));
            BlockModelBuilder model = this.models().cubeAll(this.name(block), texture);
            this.simpleBlockWithItem(block, model);
        });
        this.getVariantBuilder(ModRegistry.FLAT_LAMP_BLOCK.value()).forAllStatesExcept(state -> {
            Direction facing = state.getValue(FlatLampBlock.FACING);
            boolean lit = state.getValue(FlatLampBlock.LIT);
            ResourceLocation path = this.blockTexture(ModRegistry.FLAT_LAMP_BLOCK.value());
            ModelFile modelFile = this.models()
                    .getExistingFile(ResourceLocationHelper.fromNamespaceAndPath(path.getNamespace(),
                            path.getPath() + (lit ? "_on" : "_off")
                    ));
            return ConfiguredModel.builder().modelFile(modelFile).rotationX(
                    facing == Direction.DOWN ? 180 : facing.getAxis().isHorizontal() ? 90 : 0).rotationY(
                    facing.getAxis().isVertical() ? 0 : (((int) facing.toYRot()) + 180) % 360).build();
        }, FlatLampBlock.WATERLOGGED);
        ResourceLocation resourceLocation = this.key(ModRegistry.FLAT_LAMP_BLOCK.value());
        resourceLocation = ResourceLocationHelper.fromNamespaceAndPath(resourceLocation.getNamespace(),
                resourceLocation.getPath() + "_on"
        );
        this.itemModels()
                .withExistingParent(this.name(ModRegistry.FLAT_LAMP_BLOCK.value()), this.extendKey(resourceLocation, ModelProvider.BLOCK_FOLDER));
    }

    public ResourceLocation extendKey(ResourceLocation resourceLocation, String... extensions) {
        extensions = ArrayUtils.add(extensions, resourceLocation.getPath());
        return ResourceLocationHelper.fromNamespaceAndPath(resourceLocation.getNamespace(), String.join("/", extensions));
    }
}
