package fuzs.ytones.data;

import fuzs.puzzleslib.api.data.v1.AbstractLanguageProvider;
import fuzs.ytones.Ytones;
import fuzs.ytones.init.ModRegistry;
import fuzs.ytones.world.level.block.Tone;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraftforge.data.event.GatherDataEvent;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(GatherDataEvent evt, String modId) {
        super(evt, modId);
    }

    @Override
    protected void addTranslations() {
        this.addCreativeModeTab(Ytones.MOD_NAME);
        this.add(ModRegistry.STONE_TILE_BLOCK.value(), "Stone Tile");
        this.add(ModRegistry.FLAT_LAMP_ITEM.value(), "Flat Lamp");
        Tone.forEach((tone, toneType) -> {
            this.add(BuiltInRegistries.BLOCK.get(Ytones.id(tone.id(toneType))), tone.text(toneType));
        });
    }
}
