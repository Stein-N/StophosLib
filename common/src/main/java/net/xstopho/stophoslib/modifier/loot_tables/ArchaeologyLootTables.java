package net.xstopho.stophoslib.modifier.loot_tables;

import net.minecraft.resources.ResourceLocation;

import javax.xml.stream.events.EntityReference;

public class ArchaeologyLootTables {

    public static final ResourceLocation DESERT_PYRAMID = getLootTableId("desert_pyramid");
    public static final ResourceLocation DESERT_WELL = getLootTableId("desert_well");
    public static final ResourceLocation OCEAN_RUIN_COLD = getLootTableId("ocean_ruin_cold");
    public static final ResourceLocation OCEAN_RUIN_WARM = getLootTableId("ocean_ruin_warm");
    public static final ResourceLocation TRAIL_RUINS_COMMON = getLootTableId("trail_ruins_common");
    public static final ResourceLocation TRAIL_RUINS_RARE = getLootTableId("trail_ruins_rare");

    private static ResourceLocation getLootTableId(String id) {
        return new ResourceLocation("archaeology/" + id);
    }
}
