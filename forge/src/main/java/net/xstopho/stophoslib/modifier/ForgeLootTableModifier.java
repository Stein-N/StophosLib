package net.xstopho.stophoslib.modifier;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.xstopho.stophoslib.StophoLibConstants;
import net.xstopho.stophoslib.registration.RegistryObject;

import java.util.LinkedList;
import java.util.List;

@Mod.EventBusSubscriber(modid = StophoLibConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeLootTableModifier implements LootTableModifier {

    private static final List<Modifier> lootModifier = new LinkedList<>();
    private static final List<RangedModifier> rangedLootModifier = new LinkedList<>();
    private record Modifier(RegistryObject<Item> item, float amount, float chance, ResourceLocation lootTable) {}

    private record RangedModifier(RegistryObject<Item> item, float minAmount, float maxAmount, float chance, ResourceLocation lootTable) {}

    @Override
    public void addToPool(RegistryObject<Item> item, float amount, float chance, ResourceLocation... lootTables) {
        for (ResourceLocation lootTable : lootTables) {
            lootModifier.add(new Modifier(item, amount, chance, lootTable));
        }
    }

    @Override
    public void addToPool(RegistryObject<Item> item, float minAmount, float maxAmount, float chance, ResourceLocation... lootTables) {
        for (ResourceLocation lootTable : lootTables) {
            rangedLootModifier.add(new RangedModifier(item, minAmount, maxAmount, chance, lootTable));
        }
    }

    @SubscribeEvent
    public static void init(LootTableLoadEvent event) {
        for (Modifier modifier : lootModifier) {
            if (event.getName().equals(modifier.lootTable())) {
                event.getTable().addPool(LootTableModifier.createLootPool(modifier.item().get(), modifier.chance(), modifier.amount()).build());
            }
        }
        for (RangedModifier modifier : rangedLootModifier) {
            if (event.getName().equals(modifier.lootTable())) {
                event.getTable().addPool(LootTableModifier.createLootPool(modifier.item().get(), modifier.chance(), modifier.minAmount(), modifier.maxAmount()).build());
            }
        }
    }
}
