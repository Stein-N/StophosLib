package net.xstopho.stophoslib.modifier;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.xstopho.stophoslib.registration.RegistryObject;

public class FabricLootTableModifier implements LootTableModifier {
    @Override
    public void addToPool(RegistryObject<Item> item, float amount, float chance, ResourceLocation... lootTables) {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            for (ResourceLocation loc : lootTables) {
                if (id.equals(loc)) tableBuilder.withPool(LootTableModifier.createLootPool(item.get(), chance, amount));
            }
        });
    }

    @Override
    public void addToPool(RegistryObject<Item> item, float minAmount, float maxAmount, float chance, ResourceLocation... lootTables) {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            for (ResourceLocation loc : lootTables) {
                if (id.equals(loc)) tableBuilder.withPool(LootTableModifier.createLootPool(item.get(), chance, minAmount, maxAmount));
            }
        });
    }
}
