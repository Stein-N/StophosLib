package net.xstopho.stophoslib.registration;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Supplier;

public class FabricRegistryFactory implements RegistryProvider.Factory {
    @Override
    public <T> RegistryProvider<T> create(Registry<T> registry, String modId) {
        return new Provider<>(registry, modId);
    }

    private static class Provider<T> implements RegistryProvider<T> {
        private final String modId;
        private final Registry<T> registry;

        private final Set<RegistryObject<T>> entries = new LinkedHashSet<>();
        private final Set<RegistryObject<T>> entriesView = Collections.unmodifiableSet(entries);

        private Provider(Registry<T> registry, String modId) {
            this.registry = registry;
            this.modId = modId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <I extends T> RegistryObject<I> register(String name, Supplier<? extends I> supplier) {
            final var resourceLocation = new ResourceLocation(modId, name);
            final var object = Registry.register(registry, resourceLocation, supplier.get());
            final var registryObject = new RegistryObject<I>() {
                final ResourceKey<I> key = ResourceKey.create((ResourceKey<? extends Registry<I>>) registry.key(), resourceLocation);

                @Override
                public ResourceKey<I> getResourceKey() {
                    return key;
                }

                @Override
                public ResourceLocation getId() {
                    return resourceLocation;
                }

                @Override
                public I get() {
                    return object;
                }

                @Override
                public Holder<I> asHolder() {
                    return (Holder<I>) registry.getHolderOrThrow((ResourceKey<T>) this.key);
                }
            };
            entries.add((RegistryObject<T>) registryObject);
            return registryObject;
        }

        @Override
        public Collection<RegistryObject<T>> getEntries() {
            return entriesView;
        }

        @Override
        public String getModId() {
            return modId;
        }
    }
}
