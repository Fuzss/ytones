package fuzs.ytones.data.client;

import fuzs.puzzleslib.api.client.data.v2.AbstractLanguageProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.ytones.Ytones;
import fuzs.ytones.init.ModRegistry;
import fuzs.ytones.world.level.block.Tone;
import net.minecraft.core.registries.BuiltInRegistries;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations(TranslationBuilder builder) {
        builder.addCreativeModeTab(Ytones.MOD_ID, Ytones.MOD_NAME);
        builder.add(ModRegistry.YTONE_BLOCK.value(), "Ytone");
        builder.add(ModRegistry.FLAT_LAMP_ITEM.value(), "Flat Lamp");
        Tone.forEach((tone, toneType) -> {
            builder.add(BuiltInRegistries.BLOCK.getValue(Ytones.id(tone.id(toneType))), tone.text(toneType));
        });
    }
}
