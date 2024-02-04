package net.xstopho.stophoslib_testmod;

import net.fabricmc.api.ModInitializer;
import net.xstopho.stophoslib_testmod.registries.ItemGroupRegistry;
import net.xstopho.stophoslib_testmod.registries.ItemRegistry;

public class Testmod implements ModInitializer {
    @Override
    public void onInitialize() {
        ItemRegistry.init();

        ItemGroupRegistry.init();
    }
}
