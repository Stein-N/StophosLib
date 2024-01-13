package net.xstopho.stophoslib.modifier.loot_tables;

import net.minecraft.resources.ResourceLocation;

public class GameplayLootTables {

    public static final ResourceLocation FISHING_FISH = getLootTableId("fishing/fish");
    public static final ResourceLocation FISHING_JUNK = getLootTableId("fishing/junk");
    public static final ResourceLocation FISHING_TREASURE = getLootTableId("fishing/treasure");

    public static final ResourceLocation ARMORER_GIFT = getLootTableId("hero_of_the_village/armorer_gift");
    public static final ResourceLocation BUTCHER_GIFT = getLootTableId("hero_of_the_village/butcher_gift");
    public static final ResourceLocation CARTOGRAPHER_GIFT = getLootTableId("hero_of_the_village/cartographer_gift");
    public static final ResourceLocation CLERIC_GIFT = getLootTableId("hero_of_the_village/cleric_gift");
    public static final ResourceLocation FARMER_GIFT = getLootTableId("hero_of_the_village/farmer_gift");
    public static final ResourceLocation FISHERMAN_GIFT = getLootTableId("hero_of_the_village/fisherman_gift");
    public static final ResourceLocation FLETCHER_GIFT = getLootTableId("hero_of_the_village/fletcher_gift");
    public static final ResourceLocation LEATHERWORKER_GIFT = getLootTableId("hero_of_the_village/leatherworker_gift");
    public static final ResourceLocation LIBRARIAN_GIFT = getLootTableId("hero_of_the_village/librarian_gift");
    public static final ResourceLocation MASON_GIFT = getLootTableId("hero_of_the_village/mason_gift");
    public static final ResourceLocation SHEPARD_GIFT = getLootTableId("hero_of_the_village/shepard_gift");
    public static final ResourceLocation TOOLSMITH_GIFT = getLootTableId("hero_of_the_village/toolsmith_gift");
    public static final ResourceLocation WEAPONSMITH_GIFT = getLootTableId("hero_of_the_village/weaponsmith_gift");

    public static final ResourceLocation CAT_MORNING_GIFT = getLootTableId("cat_morning_gift");
    public static final ResourceLocation PIGLIN_BARTERING = getLootTableId("piglin_bartering");
    public static final ResourceLocation SNIFFER_DIGING = getLootTableId("sniffer_digging");

    private static ResourceLocation getLootTableId(String id) {
        return new ResourceLocation("gameplay/" + id);
    }
}
