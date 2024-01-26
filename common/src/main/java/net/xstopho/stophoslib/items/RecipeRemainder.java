package net.xstopho.stophoslib.items;

import net.minecraft.world.item.ItemStack;

public class RecipeRemainder {
    public static abstract class Item extends net.minecraft.world.item.Item implements LoaderMethods {

        public Item(Properties properties) {
            super(properties);
        }

        @Override
        public boolean hasCraftingRemainingItem() {
            return true;
        }

        public abstract ItemStack getRemainder(ItemStack stack);

        @Override
        public final ItemStack getRecipeRemainder(ItemStack stack) {
            return getRemainder(stack);
        }

        @Override
        public final ItemStack getCraftingRemainingItem(ItemStack stack) {
            return getRemainder(stack);
        }

    }

    private interface LoaderMethods {
        // Fabric implementation
        ItemStack getRecipeRemainder(ItemStack stack);

        // Neo-/Forge implementation
        ItemStack getCraftingRemainingItem(ItemStack stack);
    }
}
