package fuzs.ytones.data;

import fuzs.puzzleslib.api.data.v1.AbstractModelProvider;
import fuzs.ytones.init.ModRegistry;
import fuzs.ytones.world.level.block.FlatLampBlock;
import fuzs.ytones.world.level.block.Tone;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import org.apache.commons.lang3.ArrayUtils;

public class ModModelProvider extends AbstractModelProvider {

    public ModModelProvider(GatherDataEvent evt, String modId) {
        super(evt, modId);
    }

    @Override
    protected void registerStatesAndModels() {
        this.simpleBlockWithItem(ModRegistry.STONE_TILE_BLOCK.value(), this.cubeAll(ModRegistry.STONE_TILE_BLOCK.value()));
        Tone.forEach((tone, toneType) -> {
            Block block = tone.block(toneType);
            ResourceLocation texture = this.blockTexture(block);
            texture = new ResourceLocation(texture.getNamespace(), texture.getPath().replaceAll(toneType.id() + "$", String.valueOf(toneType.ordinal())));
            BlockModelBuilder model = this.models().cubeAll(this.name(block), texture);
            this.simpleBlockWithItem(block, model);
        });
        this.getVariantBuilder(ModRegistry.FLAT_LAMP_BLOCK.get()).forAllStatesExcept(state -> {
            Direction facing = state.getValue(FlatLampBlock.FACING);
            boolean lit = state.getValue(FlatLampBlock.LIT);
            ResourceLocation path = this.blockTexture(ModRegistry.FLAT_LAMP_BLOCK.get());
            ModelFile modelFile = this.models().getExistingFile(new ResourceLocation(path.getNamespace(), path.getPath() + (lit ? "_on" : "_off")));
            return ConfiguredModel.builder()
                    .modelFile(modelFile)
                    .rotationX(facing == Direction.DOWN ? 180 : facing.getAxis().isHorizontal() ? 90 : 0)
                    .rotationY(facing.getAxis().isVertical() ? 0 : (((int) facing.toYRot()) + 180) % 360)
                    .build();
        }, FlatLampBlock.WATERLOGGED);
        ResourceLocation resourceLocation = this.key(ModRegistry.FLAT_LAMP_BLOCK.get());
        resourceLocation = new ResourceLocation(resourceLocation.getNamespace(), resourceLocation.getPath() + "_on");
        this.itemModels().withExistingParent(this.name(ModRegistry.FLAT_LAMP_BLOCK.get()), this.extendKey(resourceLocation, ModelProvider.BLOCK_FOLDER));
    }

    public ResourceLocation extendKey(ResourceLocation resourceLocation, String... extensions) {
        extensions = ArrayUtils.add(extensions, resourceLocation.getPath());
        return new ResourceLocation(resourceLocation.getNamespace(), String.join("/", extensions));
    }
}
