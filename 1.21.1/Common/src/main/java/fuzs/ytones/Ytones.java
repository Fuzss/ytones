package fuzs.ytones;

import com.google.common.collect.Lists;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.api.core.v1.context.CreativeModeTabContext;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import fuzs.puzzleslib.api.item.v2.CreativeModeTabConfigurator;
import fuzs.puzzleslib.api.network.v3.NetworkHandler;
import fuzs.ytones.init.ModRegistry;
import fuzs.ytones.network.client.ServerboundCycleToneMessage;
import fuzs.ytones.world.level.block.Tone;
import fuzs.ytones.world.level.block.ToneType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Ytones implements ModConstructor {
    public static final String MOD_ID = "ytones";
    public static final String MOD_NAME = "Ytones";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final NetworkHandler NETWORK = NetworkHandler.builder(MOD_ID)
            .registerServerbound(ServerboundCycleToneMessage.class);

    @Override
    public void onConstructMod() {
        ModRegistry.touch();
    }

    @Override
    public void onRegisterCreativeModeTabs(CreativeModeTabContext context) {
        context.registerCreativeModeTab(CreativeModeTabConfigurator.from(MOD_ID).icons(() -> {
            List<ItemStack> items = Lists.newArrayList();
            ToneType type = ToneType.values()[0];
            for (Tone tone : Tone.values()) {
                items.add(new ItemStack(tone.block(type)));
            }
            return items.toArray(ItemStack[]::new);
        }).withSearchBar().displayItems((itemDisplayParameters, output) -> {
            output.accept(ModRegistry.FLAT_LAMP_ITEM.value());
            output.accept(ModRegistry.STONE_TILE_ITEM.value());
            Tone.forEach((tone, toneType) -> {
                output.accept(tone.block(toneType));
            });
        }));
    }

    public static ResourceLocation id(String path) {
        return ResourceLocationHelper.fromNamespaceAndPath(MOD_ID, path);
    }
}
