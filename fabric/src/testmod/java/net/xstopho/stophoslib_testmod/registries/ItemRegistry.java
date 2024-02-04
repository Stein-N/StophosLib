package net.xstopho.stophoslib_testmod.registries;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.xstopho.stophoslib_testmod.items.TestAxe;
import net.xstopho.stophoslib_testmod.items.TestPickaxe;
import net.xstopho.stophoslib_testmod.items.TestShovel;

public class ItemRegistry {

    public static final Item test_axe = register("test_axe", new TestAxe(Tiers.DIAMOND, 10f, -1.5f, new Item.Properties()));
    public static final Item test_pickaxe = register("test_pickaxe", new TestPickaxe(Tiers.DIAMOND, 10, -1.5f, new Item.Properties()));
    public static final Item test_shovel = register("test_shovel", new TestShovel(Tiers.DIAMOND, 10f, -1.5f, new Item.Properties()));

    private static Item register(String id, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation("testmod", id), item);
    }

    public static void init() {}
}
