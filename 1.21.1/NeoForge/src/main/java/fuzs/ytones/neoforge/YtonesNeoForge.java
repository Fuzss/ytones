package fuzs.ytones.neoforge;

import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import fuzs.ytones.Ytones;
import fuzs.ytones.data.ModBlockLootProvider;
import fuzs.ytones.data.ModBlockTagProvider;
import fuzs.ytones.data.ModItemTagProvider;
import fuzs.ytones.data.ModRecipeProvider;
import net.neoforged.fml.common.Mod;

@Mod(Ytones.MOD_ID)
public class YtonesNeoForge {

    public YtonesNeoForge() {
        ModConstructor.construct(Ytones.MOD_ID, Ytones::new);
        DataProviderHelper.registerDataProviders(Ytones.MOD_ID,
                ModBlockLootProvider::new,
                ModBlockTagProvider::new,
                ModItemTagProvider::new,
                ModRecipeProvider::new);
    }
}
