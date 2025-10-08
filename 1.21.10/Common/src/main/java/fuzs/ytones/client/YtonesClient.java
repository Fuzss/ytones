package fuzs.ytones.client;

import fuzs.hotbarslotcycling.api.v1.client.SlotCyclingProvider;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.core.v1.context.RenderTypesContext;
import fuzs.puzzleslib.api.config.v3.ConfigHolder;
import fuzs.ytones.Ytones;
import fuzs.ytones.world.level.block.Tone;
import fuzs.ytones.world.level.block.ToneType;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.world.level.block.Block;

public class YtonesClient implements ClientModConstructor {

    @Override
    public void onConstructMod() {
        ConfigHolder.registerConfigurationScreen(Ytones.MOD_ID, "hotbarslotcycling");
    }

    @Override
    public void onClientSetup() {
        Tone.forEach((Tone tone, ToneType toneType) -> {
            SlotCyclingProvider.registerProvider(tone.block(toneType).asItem(), ToneCyclingProvider::new);
        });
    }

    @Override
    public void onRegisterBlockRenderTypes(RenderTypesContext<Block> context) {
        for (ToneType toneType : ToneType.values()) {
            context.registerChunkRenderType(Tone.GLAXX.block(toneType), ChunkSectionLayer.TRANSLUCENT);
        }
    }
}
