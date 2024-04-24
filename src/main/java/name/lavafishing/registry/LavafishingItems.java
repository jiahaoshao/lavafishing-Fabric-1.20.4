package name.lavafishing.registry;

import name.lavafishing.LavaFishing;
import name.lavafishing.item.ItemObsidianFishingRod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class LavafishingItems {
    public static final Item OBSIDIAN_FISHING_ROD = register("obsidian_fishing_rod", new ItemObsidianFishingRod.Builder()
            .build());

    public static <T extends Item> T register(String name, T item) {
        Registry.register(Registries.ITEM, LavaFishing.id(name), item);
        ItemGroupEvents.modifyEntriesEvent(LavaFishing.ITEM_GROUP).register(entries -> entries.add(item));
        return item;
    }
    public static void init() {
        // NO-OP
    }
}
