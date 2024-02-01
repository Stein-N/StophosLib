package net.xstopho.stophoslib.util;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;


public class TreeTrimmingUtil {

    private static final List<BlockPos> visited = new ArrayList<>();
    private static Level constantLevel;

    public static BlockPos getLastPossibleBlock(Level level, BlockPos pos) {
        constantLevel = level;
        BlockPos lastPos = scanTree(pos);
        visited.clear();
        return lastPos;
    }

    static BlockPos scanTree(BlockPos pos) {

        if (hasBlockAbove(pos)) pos = scanTree(pos.above());

        if (hasDiagonalNeighbour(pos)) pos = scanTree(getDiagonalNeighbour(pos));

        if (hasNeighbour(pos)) pos = scanTree(getNeighbour(pos));

        return pos;
    }

    static boolean hasBlockAbove(BlockPos pos) {
        return isBlockAllowed(pos.above());
    }

    static boolean hasDiagonalNeighbour(BlockPos pos) {
        return pos != iterateBlocks(pos, 1);
    }

    static boolean hasNeighbour(BlockPos pos) {
        return pos != iterateBlocks(pos, 0);
    }

    static BlockPos getDiagonalNeighbour(BlockPos pos) {
        visited.add(pos);
        return iterateBlocks(pos, 1);
    }

    static BlockPos getNeighbour(BlockPos pos) {
        visited.add(pos);
        return iterateBlocks(pos, 0);
    }

    static BlockPos iterateBlocks(BlockPos originPos, int yOffset) {
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                BlockPos checkBlock = new BlockPos(originPos.getX() + x, originPos.getY() + yOffset, originPos.getZ() + z);
                if (isBlockAllowed(checkBlock) && !visited.contains(checkBlock)) {
                    return checkBlock;
                }
            }
        }
        return originPos;
    }

    public static boolean isBlockAllowed(BlockPos pos) {
        return constantLevel.getBlockState(pos).is(BlockTags.LOGS);
    }
}
