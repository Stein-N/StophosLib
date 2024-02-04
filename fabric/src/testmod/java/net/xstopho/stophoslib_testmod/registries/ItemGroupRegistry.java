package net.xstopho.stophoslib_testmod.registries;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ItemGroupRegistry {

    public static final CreativeModeTab TESTMOD = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation("testmod:item_group"),
            CreativeModeTab.builder(CreativeModeTab.Row.TOP, -1).title(Component.literal("Testmod"))
                    .icon(() -> new ItemStack(Items.DIAMOND)).displayItems((itemDisplayParameters, output) -> {

                        output.accept(ItemRegistry.test_axe);
                        output.accept(ItemRegistry.test_pickaxe);
                        output.accept(ItemRegistry.test_shovel);

                    }).build());

    public static void init() {}
}