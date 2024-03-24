package com.menghuan.cn.handher.BackPack;

import com.menghuan.cn.Data.DataBackPack;
import com.menghuan.cn.Slimefun4Mod;
import com.menghuan.cn.handher.Slot.BlackpackSlot;
import com.menghuan.cn.item.ToolItems.BackPack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Hand;

import java.io.IOException;
import java.util.UUID;

public class BackPackScreeHandler extends ScreenHandler {

    private final Inventory inventory;
    public BackPackScreeHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf packetByteBuf) {
        super(Slimefun4Mod.BackPackScreenHandlerScreenHandlerType, syncId);
        this.inventory = new SimpleInventory(18);
        this.addSlot(new BlackpackSlot(inventory, 0, 8, 9));
        this.addSlot(new BlackpackSlot(inventory, 1, 26, 9));
        this.addSlot(new BlackpackSlot(inventory, 2, 44, 9));
        this.addSlot(new BlackpackSlot(inventory, 3, 62, 9));
        this.addSlot(new BlackpackSlot(inventory, 4, 80, 9));
        this.addSlot(new BlackpackSlot(inventory, 5, 98, 9));
        this.addSlot(new BlackpackSlot(inventory, 6, 116, 9));
        this.addSlot(new BlackpackSlot(inventory, 7, 134, 9));
        this.addSlot(new BlackpackSlot(inventory, 8, 152, 9));
        this.addSlot(new BlackpackSlot(inventory, 9, 8, 27));
        this.addSlot(new BlackpackSlot(inventory, 10, 26, 27));
        this.addSlot(new BlackpackSlot(inventory, 11, 44, 27));
        this.addSlot(new BlackpackSlot(inventory, 12, 62, 27));
        this.addSlot(new BlackpackSlot(inventory, 13, 80, 27));
        this.addSlot(new BlackpackSlot(inventory, 14, 98, 27));
        this.addSlot(new BlackpackSlot(inventory, 15, 116, 27));
        this.addSlot(new BlackpackSlot(inventory, 16, 134, 27));
        this.addSlot(new BlackpackSlot(inventory, 17, 152, 27));
        int playerInvY = 79;
        int playerInvX = 8;
        for (int m = 0; m < 3; ++m) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, playerInvX + l * 18, playerInvY + m * 18));
            }
        }

        for (int n = 0; n < 9; ++n) {
            this.addSlot(new Slot(playerInventory, n, playerInvX + n * 18, playerInvY + 58));
        }

        NbtCompound nbtCompound = playerInventory.player.getStackInHand(Hand.MAIN_HAND).getOrCreateNbt();
        if (nbtCompound != null) {
            if (nbtCompound.contains("BackPackUUID")) {
                String backpackUUID = nbtCompound.getString("BackPackUUID");
                try {
                    NbtCompound nbtCompound1 = DataBackPack.OnnbtData(UUID.fromString(backpackUUID),50000L);
                    if (nbtCompound1 != null){
                        int u = 0;
                        for (ItemStack i : DataBackPack.toItemStack(nbtCompound1,18)){
                            if (u <= 17){
                                inventory.setStack(u,i);
                            }else {
                                break;
                            }
                            u++;
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        inventory.markDirty();
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }
            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        if (player.getStackInHand(Hand.MAIN_HAND).getItem() instanceof BackPack){
            try {
                NbtCompound nbtCompound = player.getStackInHand(Hand.MAIN_HAND).getOrCreateNbt();
                if (nbtCompound != null){
                    if (nbtCompound.contains("BackPackUUID")){
                        String backpackUUID = nbtCompound.getString("BackPackUUID");
                        DataBackPack.IOnbtData(DataBackPack.toNbtCompound(inventory), UUID.fromString(backpackUUID));
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
