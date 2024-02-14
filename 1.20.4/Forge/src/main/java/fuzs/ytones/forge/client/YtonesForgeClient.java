package fuzs.ytones.forge.client;

import fuzs.ytones.Ytones;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.ytones.client.YtonesClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

@Mod.EventBusSubscriber(modid = Ytones.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class YtonesForgeClient {

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        ClientModConstructor.construct(Ytones.MOD_ID, YtonesClient::new);
    }
}
