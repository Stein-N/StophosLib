package net.xstopho.stophoslib.modifier;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.LootTableLoadEvent;
import net.xstopho.stophoslib.StophoLibConstants;

import java.util.LinkedList;
import java.util.List;

@Mod.EventBusSubscriber(modid = StophoLibConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class NeoForgeLootTableModifier implements LootTableModifier {

    private static final List<Modifier> lootModifier = new LinkedList<>();
    private static final List<RangedModifier> rangedLootModifier = new LinkedList<>();
    private record Modifier(ItemLike item, float amount, float chance, ResourceLocation lootTable) {}

    private record RangedModifier(ItemLike item, float minAmount, float maxAmount, float chance, ResourceLocation lootTable) {}

    @Override
    public void addToPool(ItemLike item, float amount, float chance, ResourceLocation... lootTables) {
        for (ResourceLocation lootTable : lootTables) {
            lootModifier.add(new Modifier(item, amount, chance, lootTable));
        }
    }

    @Override
    public void addToPool(ItemLike item, float minAmount, float maxAmount, float chance, ResourceLocation... lootTables) {
        for (ResourceLocation lootTable : lootTables) {
            rangedLootModifier.add(new RangedModifier(item, minAmount, maxAmount, chance, lootTable));
        }
    }

    @SubscribeEvent
    public static void init(LootTableLoadEvent event) {
        for (Modifier modifier : lootModifier) {
            if (event.getName().equals(modifier.lootTable())) {
                event.getTable().addPool(LootTableModifier.createLootPool(modifier.item(), modifier.chance(), modifier.amount()).build());
            }
        }
        for (RangedModifier modifier : rangedLootModifier) {
            if (event.getName().equals(modifier.lootTable())) {
                event.getTable().addPool(LootTableModifier.createLootPool(modifier.item(), modifier.chance(), modifier.minAmount(), modifier.maxAmount()).build());
            }
        }
    }
}
