package net.xstopho.stophoslib.modifier;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.xstopho.stophoslib.platform.Services;

public interface LootTableModifier {

    static LootTableModifier get() {
        return Services.load(LootTableModifier.class);
    }

    void addToPool(ItemLike item, float amount, float chance, ResourceLocation... lootTables);
    void addToPool(ItemLike item, float amount, float chance, ResourceLocation lootTable);

    static LootPool.Builder createLootPool(ItemLike item, float chance, float amount) {
        return LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .when(LootItemRandomChanceCondition.randomChance(value(chance)))
                .add(LootItem.lootTableItem(item))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, amount)));
    }

    static float value(float chance) {
        return Math.min(1.0f, Math.max(0.0f, chance));
    }
}
