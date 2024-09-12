package fuzs.ytones.neoforge;

import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import fuzs.ytones.Ytones;
import fuzs.ytones.data.ModBlockLootProvider;
import fuzs.ytones.data.ModItemTagProvider;
import fuzs.ytones.data.ModRecipeProvider;
import fuzs.ytones.data.client.ModLanguageProvider;
import fuzs.ytones.neoforge.data.client.ModModelProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;

@Mod(Ytones.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class YtonesNeoForge {

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        ModConstructor.construct(Ytones.MOD_ID, Ytones::new);
        DataProviderHelper.registerDataProviders(Ytones.MOD_ID,
                ModBlockLootProvider::new,
                ModItemTagProvider::new,
                ModLanguageProvider::new,
                ModModelProvider::new,
                ModRecipeProvider::new
        );
    }
}
