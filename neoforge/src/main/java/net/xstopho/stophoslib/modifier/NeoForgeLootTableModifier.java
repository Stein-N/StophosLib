package net.xstopho.stophoslib.modifier;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.LootTableLoadEvent;

import java.util.LinkedList;
import java.util.List;

@Mod.EventBusSubscriber(modid = "stophoslib", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class NeoForgeLootTableModifier implements LootTableModifier {

    private static final List<Modifier> lootModifier = new LinkedList<>();

    @Override
    public void addToPool(ItemLike item, float amount, float chance, ResourceLocation... lootTables) {
        for (ResourceLocation lootTable : lootTables) {
            lootModifier.add(new Modifier(item, amount, chance, lootTable));
        }
    }

    @Override
    public void addToPool(ItemLike item, float amount, float chance, ResourceLocation lootTable) {
        lootModifier.add(new Modifier(item, amount, chance, lootTable));
    }

    @SubscribeEvent
    public static void init(LootTableLoadEvent event) {
        for (Modifier modifier : lootModifier) {
            if (event.getName().equals(modifier.getLootTable())) {
                event.getTable().addPool(LootTableModifier.createLootPool(modifier.getItem(), modifier.getChance(), modifier.getAmount()).build());
            }
        }
    }
}
