package fuzs.ytones.neoforge.client;

import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import fuzs.ytones.Ytones;
import fuzs.ytones.client.YtonesClient;
import fuzs.ytones.data.client.ModLanguageProvider;
import fuzs.ytones.data.client.ModModelProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = Ytones.MOD_ID, dist = Dist.CLIENT)
public class YtonesNeoForgeClient {

    public YtonesNeoForgeClient() {
        ClientModConstructor.construct(Ytones.MOD_ID, YtonesClient::new);
        DataProviderHelper.registerDataProviders(Ytones.MOD_ID, ModLanguageProvider::new, ModModelProvider::new);
    }
}
