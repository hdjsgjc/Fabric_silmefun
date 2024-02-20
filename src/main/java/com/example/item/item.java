package com.example.item;

import com.example.ExampleMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class item {

    public static final Item ICE = ModItem("ice", new Item(new FabricItemSettings()));
    public static final Item BACKPACK_GILDED = ModItem("backpack_gilded", new Item(new FabricItemSettings()));
    public static final Item BACKPACK_SMALL = ModItem("backpack_small", new Item(new FabricItemSettings()));
    public static final Item backpack = ModItem("backpack", new Item(new FabricItemSettings()));

    private static Item ModItem(String name, Item item, RegistryKey<ItemGroup>... itemGroups) {
        Item registerITEM = Registry.register(Registries.ITEM, new Identifier(ExampleMod.MOD_ID, name), item);
        for (RegistryKey<ItemGroup> itemGroup : itemGroups) {
            ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> entries.add(registerITEM));
        }
        return registerITEM;
    }

    public static void regModitem() {
        // 在这里注册其他物品...
    }
}
