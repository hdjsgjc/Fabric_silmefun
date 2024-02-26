package com.menghuan.cn.item;

import com.menghuan.cn.slimefun4Mod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class itemGrops {
    public static final ItemGroup SLIMEFUN_TOOL = Registry.register(Registries.ITEM_GROUP,
            new Identifier(slimefun4Mod.MOD_ID,"kfc"),
            FabricItemGroup.builder().displayName(Text.translatable("item.slimefun4.tool"))
                    .icon(()-> new ItemStack(item.BACKPACK)).entries((displayContext, entries) -> {
                        entries.add(item.BACKPACK);
                        entries.add(item.BACKPACK_GILDED);
                        entries.add(item.BACKPACK_LARGE);
                        entries.add(item.BACKPACK_RADIANT);
                        entries.add(item.BACKPACK_SMALL);
                        entries.add(item.BACKPACK_WOVEN);
                        entries.add(item.BANDAGE);
                        entries.add(item.COOLER);
                        entries.add(item.MEDICINE);
                        entries.add(item.PORTABLE_CRAFTINGTABBLE);
                        entries.add(item.PORTABLE_DUSTBIN);
                        entries.add(item.RAG);
                        entries.add(item.RESTORED_BACKPACK);
                        entries.add(item.SPLINT);
                        entries.add(item.TAPE_MEASURE);
                        entries.add(item.VITAMINS);
                        entries.add(item.CLOTH);


                    }).build());
    public static void regItemGrops() {

    }
}
