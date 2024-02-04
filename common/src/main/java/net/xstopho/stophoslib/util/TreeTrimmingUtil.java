package net.xstopho.stophoslib.util;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;


public class TreeTrimmingUtil {

    private static final List<BlockPos> visited = new ArrayList<>();

    public static BlockPos getLastBlock(Level level, BlockPos pos) {
        visited.clear();
        return scanTree(level, pos);
    }

    static BlockPos scanTree(Level level, BlockPos pos) {
        if (!notVisited(level, pos)) return pos;

        visited.add(pos);

        if (hasBlockAbove(level, pos)) pos = scanTree(level, getBlockAbove(level, pos));

        if (hasDiagonalNeighbour(level, pos)) pos = scanTree(level, getDiagonalNeighbour(level, pos));

        if (hasNeighbour(level, pos)) pos = scanTree(level, getNeighbour(level, pos));

        return pos;
    }

    static BlockPos getBlockAbove(Level level, BlockPos pos) {
        if (notVisited(level, pos.above())) return pos.above();
        return pos;
    }

    static BlockPos getDiagonalNeighbour(Level level, BlockPos pos) {
        return iterateBlocks(level, pos, 1);
    }

    static BlockPos getNeighbour(Level level, BlockPos pos) {
        return iterateBlocks(level, pos, 0);
    }

    static boolean hasBlockAbove(Level level, BlockPos pos) {
        return notVisited(level, pos.above());
    }

    static boolean hasDiagonalNeighbour(Level level, BlockPos pos) {
        return !pos.equals(iterateBlocks(level, pos, 1));
    }

    static boolean hasNeighbour(Level level, BlockPos pos) {
        return !pos.equals(iterateBlocks(level, pos, 0));
    }

    static BlockPos iterateBlocks(Level level, BlockPos pos, int yOffset) {
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                BlockPos check = new BlockPos(pos.getX() + x, pos.getY() + yOffset, pos.getZ() + z);
                if (notVisited(level, check)) return check;
            }
        }
        return pos;
    }

    static boolean notVisited(Level level, BlockPos pos) {
        return isAllowedBlock(level, pos) && !visited.contains(pos);
    }

    static boolean isAllowedBlock(Level level, BlockPos pos) {
        return level.getBlockState(pos).is(BlockTags.LOGS);
    }
}