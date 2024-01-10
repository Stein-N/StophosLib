package net.xstopho.stophoslib.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public interface RecipeRemainder {

    /**
     * Copied from Fabric API to enable the usage in the common project in
     * Multi-Loader Projects
     * <br><br>
     * Returns a leftover item stack after {@code stack} is consumed in a recipe.
     * (This is also known as "recipe remainder".)
     * For example, using a lava bucket in a furnace as fuel will leave an empty bucket.
     *
     * <p>Here is an example for a recipe remainder that increments the item's damage.
     *
     * <pre>{@code
     *  if (stack.getDamage() < stack.getMaxDamage() - 1) {
     *  	ItemStack moreDamaged = stack.copy();
     *  	moreDamaged.setDamage(stack.getDamage() + 1);
     *  	return moreDamaged;
     *  }
     *
     *  return ItemStack.EMPTY;
     * }</pre>
     *
     *
     * <p>This is a stack-aware version of {@link Item#getCraftingRemainingItem()}.
     *
     * <p>Note that simple item remainders can also be set via {@link Item.Properties#craftRemainder(Item)}.
     *
     * @param stack the consumed {@link ItemStack}
     * @return the leftover item stack
     */
    default ItemStack getRecipeRemainder(ItemStack stack) {
        return ((Item) this).hasCraftingRemainingItem() ? ((Item) this).getCraftingRemainingItem().getDefaultInstance() : ItemStack.EMPTY;
    }
}
