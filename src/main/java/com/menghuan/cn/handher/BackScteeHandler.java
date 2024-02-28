package com.menghuan.cn.handher;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;

import static com.menghuan.cn.Slimefun4Mod.screenHandlersblack;

public class BackScteeHandler extends ScreenHandler {

    private Inventory inventory;

    public BackScteeHandler(int syncId, PlayerInventory playerInventory,PacketByteBuf packetByteBuf) {
        super(screenHandlersblack, syncId);
        this.inventory = new SimpleInventory(9);  // 这里可以根据需要进行初始化
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }


}