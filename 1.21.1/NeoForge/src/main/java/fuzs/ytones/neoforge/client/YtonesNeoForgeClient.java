package fuzs.ytones.neoforge.client;

import fuzs.ytones.Ytones;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.ytones.client.YtonesClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;

@Mod.EventBusSubscriber(modid = Ytones.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class YtonesNeoForgeClient {

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        ClientModConstructor.construct(Ytones.MOD_ID, YtonesClient::new);
    }
}
