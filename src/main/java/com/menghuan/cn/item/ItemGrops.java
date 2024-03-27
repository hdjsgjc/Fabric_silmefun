package com.menghuan.cn.item;

import com.menghuan.cn.Block.ItemBlocklocks;
import com.menghuan.cn.Slimefun4Mod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ItemGrops {
    public static final ItemGroup SLIMEFUN_TOOL = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Slimefun4Mod.MOD_ID,"kfc"),
            FabricItemGroup.builder().displayName(Text.translatable("item.slimefun4.tool"))
                    .icon(()-> new ItemStack(Items.BACKPACK)).entries((displayContext, entries) -> {
                        entries.add(Items.BACKPACK);
                        entries.add(Items.BACKPACK_GILDED);
                        entries.add(Items.BACKPACK_LARGE);
                        entries.add(Items.BACKPACK_RADIANT);
                        entries.add(Items.BACKPACK_SMALL);
                        entries.add(Items.BACKPACK_WOVEN);
                        entries.add(Items.BANDAGE);
                        entries.add(Items.COOLER);
                        entries.add(Items.MEDICINE);
                        entries.add(Items.PORTABLE_CRAFTINGTABBLE);
                        entries.add(Items.PORTABLE_DUSTBIN);
                        entries.add(Items.RAG);
                        entries.add(Items.RESTORED_BACKPACK);
                        entries.add(Items.SPLINT);
                        entries.add(Items.TAPE_MEASURE);
                        entries.add(Items.VITAMINS);
                        entries.add(Items.CLOTH);
                        entries.add(ItemBlocklocks.EnhancedWorkbench);


                    }).build());
    public static void regItemGrops() {

    }
}
