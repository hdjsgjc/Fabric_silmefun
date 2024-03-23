package com.menghuan.cn.item;

import com.menghuan.cn.item.ToolItems.Backpack;
import com.menghuan.cn.Slimefun4Mod;
import com.menghuan.cn.item.ToolItems.PortableCraftingtabble;
import com.menghuan.cn.item.ToolItems.Potable;
import com.menghuan.cn.item.ToolItems.rag;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.util.HashSet;
import java.util.Set;

public class Items {
    private static Set<Item> medicalItems = new HashSet<>();
    public static final Item BACKPACK = ModItem("backpack", new Backpack(new FabricItemSettings().maxCount(1)));
    public static final Item BACKPACK_GILDED = ModItem("backpack_gilded", new Item(new FabricItemSettings()));
    public static final Item BACKPACK_LARGE = ModItem("backpack_large", new Item(new FabricItemSettings()));
    public static final Item BACKPACK_RADIANT = ModItem("backpack_radiant", new Item(new FabricItemSettings()));
    public static final Item BACKPACK_SMALL = ModItem("backpack_small", new Item(new FabricItemSettings()));
    public static final Item BACKPACK_WOVEN = ModItem("backpack_woven", new Item(new FabricItemSettings()));
    public static final Item BANDAGE = ModItem("bandage", new Item(new FabricItemSettings()));
    public static final Item COOLER = ModItem("cooler", new Item(new FabricItemSettings()));
    public static final Item MEDICINE = ModItem("medicine", new FoodItem(new FabricItemSettings().food(FoodItem.medicine)));
    public static final Item PORTABLE_CRAFTINGTABBLE = ModItem("portable_craftingtabble", new PortableCraftingtabble(new FabricItemSettings().maxCount(1)));
    public static final Item PORTABLE_DUSTBIN = ModItem("portable_dustbin", new Potable(new FabricItemSettings().maxCount(1)));
    public static final Item RAG = ModItem("rag", new rag(new FabricItemSettings().food(FoodItem.rag)));
    public static final Item RESTORED_BACKPACK = ModItem("restored_backpack", new Item(new FabricItemSettings()));
    public static final Item SPLINT = ModItem("splint", new Item(new FabricItemSettings()));
    public static final Item TAPE_MEASURE = ModItem("tape_measure", new Item(new FabricItemSettings()));
    public static final Item VITAMINS = ModItem("vitamins", new FoodItem(new FabricItemSettings().food(FoodItem.vitamins)));
    public static final Item CLOTH = ModItem("cloth",new Item(new FabricItemSettings()));
    
    private static Item ModItem(String name, Item item, RegistryKey<ItemGroup>... itemGroups) {
        Item registerITEM = Registry.register(Registries.ITEM, new Identifier(Slimefun4Mod.MOD_ID, name), item);
        for (RegistryKey<ItemGroup> itemGroup : itemGroups) {
            ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> entries.add(registerITEM));
        }
        return registerITEM;
    }

    public static boolean getSpecualValue(Item item){
        medicalItems.add(VITAMINS);
        medicalItems.add(MEDICINE);
        medicalItems.add(BANDAGE);
        if (medicalItems.contains(item)){
            return true;
        }else {
            return false;
        }
    }

    public static void regModitem() {

    }
}
