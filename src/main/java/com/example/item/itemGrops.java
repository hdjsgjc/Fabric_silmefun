package com.example.item;

import com.example.ExampleMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class itemGrops {
    public static final ItemGroup SLIMEFUN_TOOL = Registry.register(Registries.ITEM_GROUP,
            new Identifier(ExampleMod.MOD_ID,"KFC"),
            FabricItemGroup.builder().displayName(Text.translatable("item.slimefun4.tool"))
                    .icon(()-> new ItemStack(item.backpack)).entries((displayContext, entries) -> {
                        entries.add(item.backpack);
                        entries.add(item.BACKPACK_ENDER);
                    }).build());
    public static void regItemGrops() {

    }
}
