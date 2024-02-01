package net.xstopho.stophoslib.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AmethystBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public final class AreaUtil {

    public static void breakNeighborBlocks(Level level, Player player, int radius, int miningLevel) {
        if (miningLevel <= 0 || miningLevel >= 4) throw new IllegalArgumentException("Unexpected Mining Level (" + miningLevel + ")");
        if (!level.isClientSide) {
            ItemStack itemStack = player.getMainHandItem();
            boolean silkTouch = itemStack.getEnchantmentTags().toString().contains("silktouch");

            List<BlockPos> blocks = calcRay(level, player, radius);
            for (BlockPos blockPos : blocks) {
                BlockState blockState = level.getBlockState(blockPos);
                Block block = blockState.getBlock();

                if (miningLevel >= MiningLevelUtil.getMiningLevel(blockState) && isBlockMineable(itemStack, blockState) && !hasBlockEntity(block, blockState)) {
                    level.destroyBlock(blockPos, false);
                    if (silkTouch && !(block instanceof AmethystBlock)) spawnItem(level, blockPos, block);
                    else Block.dropResources(blockState, level, blockPos, null, player, itemStack);
                }
            }
        }
    }

    private static List<BlockPos> calcRay(Level level, Player player, int radius) {
        ArrayList<BlockPos> blockResultList = new ArrayList<>();
        Vec3 cameraPos = player.getEyePosition(1.0F);
        Vec3 rotation = player.getViewVector(1.0F);
        Vec3 cameraPosWithRotation = cameraPos.add(rotation.x * 5.0, rotation.y * 5.0, rotation.z * 5.0);
        BlockHitResult blockHitResult = level.clip(new ClipContext(cameraPos, cameraPosWithRotation, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player));

        if (blockHitResult.getType() == HitResult.Type.BLOCK) {
            Direction.Axis axis = blockHitResult.getDirection().getAxis();
            BlockPos origin = blockHitResult.getBlockPos();

            for (int x = -radius; x <= radius; x++) {
                for (int y = -radius; y <= radius; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        BlockPos pos = new BlockPos(x, y, z);
                        if ((axis == Direction.Axis.X && pos.getX() == 0) ||
                            (axis == Direction.Axis.Y && pos.getY() == 0) ||
                            (axis == Direction.Axis.Z && pos.getZ() == 0)) {
                            blockResultList.add(origin.offset(pos));
                        }
                    }
                }
            }
        }
        return blockResultList;
    }

    private static void spawnItem(Level level, BlockPos pos, Block block) {
        level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(block.asItem(), 1)));
    }

    private static boolean isBlockMineable(ItemStack stack, BlockState state) {
        return stack.getItem().isCorrectToolForDrops(state);
    }

    private static boolean hasBlockEntity(Block block, BlockState state) {
        return (state.hasBlockEntity() || block instanceof EntityBlock);
    }
}
