package fuzs.ytones.forge;

import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.ytones.Ytones;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

@Mod(Ytones.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class YtonesForge {

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        ModConstructor.construct(Ytones.MOD_ID, Ytones::new);
    }
}
