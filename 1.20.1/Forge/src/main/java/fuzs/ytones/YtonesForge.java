package fuzs.ytones;

import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.ytones.data.*;
import net.minecraftforge.data.event.GatherDataEvent;
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

    @SubscribeEvent
    public static void onGatherData(final GatherDataEvent evt) {
        evt.getGenerator().addProvider(true, new ModBlockLootProvider(evt, Ytones.MOD_ID));
        evt.getGenerator().addProvider(true, new ModBlockTagProvider(evt, Ytones.MOD_ID));
        evt.getGenerator().addProvider(true, new ModItemTagProvider(evt, Ytones.MOD_ID));
        evt.getGenerator().addProvider(true, new ModLanguageProvider(evt, Ytones.MOD_ID));
        evt.getGenerator().addProvider(true, new ModModelProvider(evt, Ytones.MOD_ID));
        evt.getGenerator().addProvider(true, new ModRecipeProvider(evt, Ytones.MOD_ID));
    }
}
