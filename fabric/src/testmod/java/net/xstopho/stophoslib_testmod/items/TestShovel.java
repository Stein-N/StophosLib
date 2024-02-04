package net.xstopho.stophoslib_testmod.items;

import net.minecraft.world.item.Tier;
import net.xstopho.stophoslib.items.ExcavatorItem;

public class TestShovel extends ExcavatorItem.Shovel {
    public TestShovel(Tier tier, float attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    @Override
    public int getRadius() {
        return 1;
    }
}
