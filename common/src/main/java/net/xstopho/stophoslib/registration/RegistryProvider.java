package net.xstopho.stophoslib.registration;

import net.minecraft.core.Registry;
import net.xstopho.stophoslib.platform.Services;

import java.util.Collection;
import java.util.function.Supplier;

public interface RegistryProvider<T> {

    static <T> RegistryProvider<T> get(Registry<T> registry, String modId) {
        return Factory.INSTANCE.create(registry, modId);
    }

    <I extends T> RegistryObject<I> register(String name, Supplier<? extends I> supplier);

    Collection<RegistryObject<T>> getEntries();

    String getModId();

    interface Factory {

        Factory INSTANCE = Services.load(Factory.class);

        <T> RegistryProvider<T> create(Registry<T> registry, String modId);
    }
}
