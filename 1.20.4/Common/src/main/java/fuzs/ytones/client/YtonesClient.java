package fuzs.ytones.client;

import fuzs.hotbarslotcycling.api.v1.client.SlotCyclingProvider;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.core.v1.context.RenderTypesContext;
import fuzs.ytones.world.level.block.Tone;
import fuzs.ytones.world.level.block.ToneType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

public class YtonesClient implements ClientModConstructor {

    @Override
    public void onClientSetup() {
        Tone.forEach((tone, toneType) -> {
            SlotCyclingProvider.registerProvider(tone.block(toneType).asItem(), ToneCyclingProvider::new);
        });
    }

    @Override
    public void onRegisterBlockRenderTypes(RenderTypesContext<Block> context) {
        for (ToneType type : ToneType.values()) {
            context.registerRenderType(RenderType.cutout(), Tone.GLAXX.block(type));
        }
    }
}
