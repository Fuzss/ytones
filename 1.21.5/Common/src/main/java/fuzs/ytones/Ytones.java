package fuzs.ytones;

import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import fuzs.puzzleslib.api.network.v3.NetworkHandler;
import fuzs.ytones.init.ModRegistry;
import fuzs.ytones.network.client.ServerboundCycleToneMessage;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ytones implements ModConstructor {
    public static final String MOD_ID = "ytones";
    public static final String MOD_NAME = "Ytones";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final NetworkHandler NETWORK = NetworkHandler.builder(MOD_ID)
            .registerServerbound(ServerboundCycleToneMessage.class);

    @Override
    public void onConstructMod() {
        ModRegistry.bootstrap();
    }

    public static ResourceLocation id(String path) {
        return ResourceLocationHelper.fromNamespaceAndPath(MOD_ID, path);
    }
}
