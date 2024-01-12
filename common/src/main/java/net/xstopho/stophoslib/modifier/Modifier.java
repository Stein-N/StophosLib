package net.xstopho.stophoslib.modifier;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

public class Modifier {
    final ItemLike item;
    final float amount;
    final float chance;
    final ResourceLocation lootTable;

    public Modifier(ItemLike item, float amount, float chance, ResourceLocation lootTable) {
        this.item = item;
        this.amount = amount;
        this.chance = chance;
        this.lootTable = lootTable;
    }

    public float getAmount() {
        return amount;
    }

    public float getChance() {
        return chance;
    }

    public ItemLike getItem() {
        return item;
    }

    public ResourceLocation getLootTable() {
        return lootTable;
    }
}
