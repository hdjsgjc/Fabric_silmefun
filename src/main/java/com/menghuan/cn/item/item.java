package com.menghuan.cn.item;

import com.menghuan.cn.ExampleMod;
import com.menghuan.cn.item.ToolItems.PortableCraftingtabble;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class item {

    public static final Item BACKPACK = ModItem("backpack", new Item(new FabricItemSettings()));
    public static final Item BACKPACK_GILDED = ModItem("backpack_gilded", new Item(new FabricItemSettings()));
    public static final Item BACKPACK_LARGE = ModItem("backpack_large", new Item(new FabricItemSettings()));
    public static final Item BACKPACK_RADIANT = ModItem("backpack_radiant", new Item(new FabricItemSettings()));
    public static final Item BACKPACK_SMALL = ModItem("backpack_small", new Item(new FabricItemSettings()));
    public static final Item BACKPACK_WOVEN = ModItem("backpack_woven", new Item(new FabricItemSettings()));
    public static final Item BANDAGE = ModItem("bandage", new Item(new FabricItemSettings()));
    public static final Item COOLER = ModItem("cooler", new Item(new FabricItemSettings()));
    public static final Item MEDICINE = ModItem("medicine", new Item(new FabricItemSettings()));
    public static final Item PORTABLE_CRAFTINGTABBLE = ModItem("portable_craftingtabble", new PortableCraftingtabble(new FabricItemSettings().maxDamage(200)));
    public static final Item PORTABLE_DUSTBIN = ModItem("portable_dustbin", new Item(new FabricItemSettings()));
    public static final Item RAG = ModItem("rag", new Item(new FabricItemSettings()));
    public static final Item RESTORED_BACKPACK = ModItem("restored_backpack", new Item(new FabricItemSettings()));
    public static final Item SPLINT = ModItem("splint", new Item(new FabricItemSettings()));
    public static final Item TAPE_MEASURE = ModItem("tape_measure", new Item(new FabricItemSettings()));
    public static final Item VITAMINS = ModItem("vitamins", new Item(new FabricItemSettings().food(FoodItem.VITAMINS)));
    
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
