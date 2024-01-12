package net.xstopho.stophoslib.modifier;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

public class FabricLootTableModifier implements LootTableModifier {
    @Override
    public void addToPool(ItemLike item, float amount, float chance, ResourceLocation... lootTable) {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            for (ResourceLocation loc : lootTable) {
                if (id.equals(loc)) tableBuilder.withPool(LootTableModifier.createLootPool(item, chance, amount));
            }
        });
    }

    @Override
    public void addToPool(ItemLike item, float amount, float chance, ResourceLocation lootTable) {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (id.equals(lootTable)) tableBuilder.withPool(LootTableModifier.createLootPool(item, chance, amount));
        });
    }
}