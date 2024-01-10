package net.xstopho.stophoslib.registration;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModList;
import net.neoforged.fml.javafmlmod.FMLModContainer;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

public class NeoForgeRegistryFactory implements RegistryProvider.Factory {
    @Override
    public <T> RegistryProvider<T> create(Registry<T> registry, String modID) {
        final var modContainer = ModList.get().getModContainerById(modID);
        if (modContainer.isEmpty()) throw new NullPointerException("Cannot find Mod Container for " + modID);

        final var container = modContainer.get();
        if (container instanceof FMLModContainer fmlModContainer) {
            final var register = DeferredRegister.create(registry.key(), modID);
            register.register(Objects.requireNonNull(fmlModContainer.getEventBus()));
            return new Provider<>(register, modID);
        } else {
            throw new ClassCastException("The Container of the Mod " + modID + " is not a Forge one!");
        }
    }

    private static class Provider<T> implements RegistryProvider<T> {
        private final String modId;
        private final DeferredRegister<T> registry;

        private final Set<RegistryObject<T>> entries = new LinkedHashSet<>();
        private final Set<RegistryObject<T>> entriesView = Collections.unmodifiableSet(entries);

        private Provider(DeferredRegister<T> registry, String modId) {
            this.modId = modId;
            this.registry = registry;
        }

        @Override
        public String getModId() {
            return modId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <I extends T> RegistryObject<I> register(String name, Supplier<? extends I> supplier) {
            final var object = registry.<I>register(name, supplier);
            final var registryObject = new RegistryObject<I>() {

                @Override
                public ResourceKey<I> getResourceKey() {
                    return (ResourceKey<I>) object.getKey();
                }

                @Override
                public ResourceLocation getId() {
                    return object.getId();
                }

                @Override
                public I get() {
                    return object.get();
                }

                @Override
                public Holder<I> asHolder() {
                    return (Holder<I>) object;
                }
            };
            entries.add((RegistryObject<T>) registryObject);
            return registryObject;
        }

        @Override
        public Set<RegistryObject<T>> getEntries() {
            return entriesView;
        }
    }
}