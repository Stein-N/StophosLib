package net.xstopho.stophoslib.items;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.xstopho.stophoslib.util.AreaUtil;
import net.xstopho.stophoslib.util.TreeTrimmingUtil;

public class ExcavatorItem {

    /**
     * Provides a "prebuilt" Pickaxe Item where you only need to change the Radius with the getRadius Method. <br>
     * This can also be a Config Value.<br>
     * Config has to be initialized before registering the Item. So this is not Compatible with the "Vanilla"
     * registration of the Neo-/Forge Loader. But every other Config API works fine as long the config get registered
     * before registering the Items.
     */
    public abstract static class Pickaxe extends PickaxeItem {

        public Pickaxe(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
            super(tier, attackDamage, attackSpeed, properties);
        }

        public abstract int getRadius();

        @Override
        public final boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
            ItemStack stack = player.getMainHandItem();
            int materialMiningLevel = this.getTier().getLevel();

            if (stack.isCorrectToolForDrops(state) && state.requiresCorrectToolForDrops()) {
                AreaUtil.breakNeighborBlocks(level, player, getRadius(), materialMiningLevel);
            }
            return true;
        }
    }

    /**
     * Provides a "prebuilt" Shovel Item where you only need to change the Radius with the getRadius Method. <br>
     * This can also be a Config Value.<br>
     * Config has to be initialized before registering the Item. So this is not Compatible with the "Vanilla"
     * registration of the Neo-/Forge Loader. But every other Config API works fine as long the config get registered
     * before registering the Items.
     */
    public abstract static class Shovel extends ShovelItem {

        public Shovel(Tier tier, float attackDamage, float attackSpeed, Properties properties) {
            super(tier, attackDamage, attackSpeed, properties);
        }

        public abstract int getRadius();

        @Override
        public final boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
            ItemStack stack = player.getMainHandItem();
            int materialMiningLevel = this.getTier().getLevel();

            if (stack.isCorrectToolForDrops(state)) {
                AreaUtil.breakNeighborBlocks(level, player, getRadius(), materialMiningLevel);
            }
            return true;
        }
    }

    /**
     * Provides a "prebuilt" Axe Item, to trim a Tree from the Top to bottom. <br>
     */
    public abstract static class Axe extends AxeItem {
        public Axe(Tier tier, float attackDamage, float attackSpeed, Properties properties) {
            super(tier, attackDamage, attackSpeed, properties);
        }

        @Override
        public final boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {

            if (level.getBlockState(pos).is(BlockTags.LOGS)) {
                BlockPos lastPos = TreeTrimmingUtil.getLastPossibleBlock(level, pos);
                if (lastPos != null) level.destroyBlock(lastPos, true);

                ItemStack stack = player.getMainHandItem();
                stack.setDamageValue(stack.getDamageValue() + 1);

                return false;
            }

            return true;
        }
    }
}
