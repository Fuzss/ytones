package fuzs.ytones.tags;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;

public final class TagFactory<T> {
    public static final TagFactory<Block> BLOCK = make(Registries.BLOCK);
    public static final TagFactory<Item> ITEM = make(Registries.ITEM);
    public static final TagFactory<Fluid> FLUID = make(Registries.FLUID);
    public static final TagFactory<EntityType<?>> ENTITY_TYPE = make(Registries.ENTITY_TYPE);
    public static final TagFactory<Enchantment> ENCHANTMENT = make(Registries.ENCHANTMENT);
    public static final TagFactory<Biome> BIOME = make(Registries.BIOME);
    public static final TagFactory<GameEvent> GAME_EVENT = make(Registries.GAME_EVENT);
    public static final TagFactory<DamageType> DAMAGE_TYPE = make(Registries.DAMAGE_TYPE);

    private final ResourceKey<Registry<T>> registryKey;

    private TagFactory(ResourceKey<Registry<T>> registryKey) {
        this.registryKey = registryKey;
    }

    public static <T> TagFactory<T> make(ResourceKey<Registry<T>> registryKey) {
        return new TagFactory<>(registryKey);
    }

    public TagKey<T> make(String namespace, String path) {
        return TagKey.create(this.registryKey, new ResourceLocation(namespace, path));
    }

    public TagKey<T> minecraft(String path) {
        return this.make("minecraft", path);
    }

    public TagKey<T> common(String path) {
        return this.make("c", path);
    }

    public TagKey<T> fabric(String path) {
        return this.make("fabric", path);
    }

    public TagKey<T> forge(String path) {
        return this.make("forge", path);
    }

    public TagKey<T> curios(String path) {
        return this.make("curios", path);
    }

    public TagKey<T> trinkets(String path) {
        return this.make("trinkets", path);
    }
}
