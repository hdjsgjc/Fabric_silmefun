package com.menghuan.cn.Block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import com.menghuan.cn.Slimefun4Mod;

public class ItemBlocklocks {

    public static final Block EnhancedWorkbench = registryBlock("enhancedworkbench",new EnhancedWorkbench(FabricBlockSettings.create().strength(4.0f)));

   public static Block registryBlock(String name, Block block){
       registryBlockItem(name,block);
       return Registry.register(Registries.BLOCK,new Identifier(Slimefun4Mod.MOD_ID,name),block);
   }

    public static Item registryBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM,new Identifier(Slimefun4Mod.MOD_ID,name),new BlockItem(block,new FabricItemSettings()));
    }

   public static void registr(){

   }
}
