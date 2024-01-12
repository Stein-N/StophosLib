package net.xstopho.stophoslib.modifier.loot_tables;

import net.minecraft.resources.ResourceLocation;

public class ChestLootTables {

    public static final ResourceLocation ABANDONED_MINESHAFT = getLootTableId("abandoned_mineshaft");
    public static final ResourceLocation ANCIENT_CITY = getLootTableId("ancient_city");
    public static final ResourceLocation ANCIENT_CITY_ICE_BOX = getLootTableId("ancient_city_ice_box");
    public static final ResourceLocation BASTION_BRIDGE = getLootTableId("bastion_bridge");
    public static final ResourceLocation BASTION_HOGLIN_STABLE = getLootTableId("bastion_hoglin_stable");
    public static final ResourceLocation BASTION_OTHER = getLootTableId("bastion_other");
    public static final ResourceLocation BASTION_TREASURE = getLootTableId("bastion_treasure");
    public static final ResourceLocation BURIED_TREASURE = getLootTableId("buried_treasure");
    public static final ResourceLocation DESERT_PYRAMID = getLootTableId("desert_pyramid");
    public static final ResourceLocation END_CITY_TREASURE = getLootTableId("end_city_treasure");
    public static final ResourceLocation IGLOO_CHEST = getLootTableId("igloo_chest");
    public static final ResourceLocation JUNGLE_TEMPLE = getLootTableId("jungle_temple");
    public static final ResourceLocation JUNGLE_TEMPLE_DISPENSER = getLootTableId("jungle_temple_dispenser");
    public static final ResourceLocation NETHER_BRIDGE = getLootTableId("nether_bridge");
    public static final ResourceLocation PILLAGER_OUTPOST = getLootTableId("pillager_outpost");
    public static final ResourceLocation RUINED_PORTAL = getLootTableId("ruined_portal");
    public static final ResourceLocation SHIPWRECK_MAP = getLootTableId("shipwreck_map");
    public static final ResourceLocation SHIPWRECK_SUPPLY = getLootTableId("shipwreck_supply");
    public static final ResourceLocation SHIPWRECK_TREASURE = getLootTableId("shipwreck_treasure");
    public static final ResourceLocation SIMPLE_DUNGEON = getLootTableId("simple_dungeon");
    public static final ResourceLocation SPAWN_BONUS_CHEST = getLootTableId("spawn_bonus_chest");
    public static final ResourceLocation STRONGHOLD_CORRIDOR = getLootTableId("stronghold_corridor");
    public static final ResourceLocation STRONGHOLD_CROSSING = getLootTableId("stringhold_crossing");
    public static final ResourceLocation STRONGHOLD_LIBRARY = getLootTableId("stronghold_library");
    public static final ResourceLocation UNDERWATER_RUIN_BIG = getLootTableId("underwater_ruin_big");
    public static final ResourceLocation UNDERWATER_RUIN_SMALL = getLootTableId("underwater_ruin_small");
    public static final ResourceLocation VILLAGE_ARMORER = getLootTableId("village/village_armorer");
    public static final ResourceLocation VILLAGE_BUTCHER = getLootTableId("village/village_butcher");
    public static final ResourceLocation VILLAGE_CARTOGRAPHER = getLootTableId("village/village_cartographer");
    public static final ResourceLocation VILLAGE_DESERT_HOUSE = getLootTableId("village/village_desert_house");
    public static final ResourceLocation VILLAGE_FISHER = getLootTableId("village/village_fisher");
    public static final ResourceLocation VILLAGE_FLETCHER = getLootTableId("village/village_fletcher");
    public static final ResourceLocation VILLAGE_MASON = getLootTableId("village/village_mason");
    public static final ResourceLocation VILLAGE_PLAINS_HOUSE = getLootTableId("village/village_plains_house");
    public static final ResourceLocation VILLAGE_SAVANNA_HOUSE = getLootTableId("village/village_savanna_house");
    public static final ResourceLocation VILLAGE_SHEPARD = getLootTableId("village/village_shepard");
    public static final ResourceLocation VILLAGE_SNOWY_HOUSE = getLootTableId("village/village_snowy_house");
    public static final ResourceLocation VILLAGE_TAIGA_HOUSE = getLootTableId("village/village_taiga_house");
    public static final ResourceLocation VILLAGE_TANNERY = getLootTableId("village/village_tannery");
    public static final ResourceLocation VILLAGE_TEMPLE = getLootTableId("village/village_temple");
    public static final ResourceLocation VILLAGE_TOOLSMITH = getLootTableId("village/village_toolsmith");
    public static final ResourceLocation VILLAGE_WEAPONSMITH = getLootTableId("village/village_weaponsmith");
    public static final ResourceLocation WOODLAND_MANSION = getLootTableId("woodland_mansion");


    private static ResourceLocation getLootTableId(String id) {
        return new ResourceLocation("chests/" + id);
    }
}
