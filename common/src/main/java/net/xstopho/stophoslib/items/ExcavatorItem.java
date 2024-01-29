package net.xstopho.stophoslib.items;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.xstopho.stophoslib.util.AreaUtil;

public class ExcavatorItem {
    public abstract static class Pickaxe extends PickaxeItem {

        public Pickaxe(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
            super(tier, attackDamage, attackSpeed, properties);
        }

        public abstract int getRadius();

        @Override
        public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
            ItemStack stack = player.getMainHandItem();
            int materialMiningLevel = this.getTier().getLevel();

            if (stack.isCorrectToolForDrops(state) && state.requiresCorrectToolForDrops()) {
                AreaUtil.breakNeighborBlocks(level, player, getRadius(), materialMiningLevel);
            }
            return true;
        }
    }

    public abstract static class Shovel extends ShovelItem {

        public Shovel(Tier tier, float attackDamage, float attackSpeed, Properties properties) {
            super(tier, attackDamage, attackSpeed, properties);
        }

        public abstract int getRadius();

        @Override
        public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
            ItemStack stack = player.getMainHandItem();
            int materialMiningLevel = this.getTier().getLevel();

            if (stack.isCorrectToolForDrops(state)) {
                AreaUtil.breakNeighborBlocks(level, player, getRadius(), materialMiningLevel);
            }
            return true;
        }
    }
}
