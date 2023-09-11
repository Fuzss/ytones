package fuzs.ytones;

import fuzs.puzzleslib.api.core.v1.ModConstructor;
import net.fabricmc.api.ModInitializer;

public class YtonesFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModConstructor.construct(Ytones.MOD_ID, Ytones::new);
    }
}
