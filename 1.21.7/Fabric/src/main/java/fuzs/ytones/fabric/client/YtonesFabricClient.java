package fuzs.ytones.fabric.client;

import fuzs.ytones.Ytones;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.ytones.client.YtonesClient;
import net.fabricmc.api.ClientModInitializer;

public class YtonesFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(Ytones.MOD_ID, YtonesClient::new);
    }
}
