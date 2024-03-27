package com.menghuan.cn.handher.BackPack;

import com.menghuan.cn.Data.DataBackPack;
import com.menghuan.cn.Slimefun4Mod;
import com.menghuan.cn.handher.Slot.BlackpackSlot;
import com.menghuan.cn.item.ToolItems.BackPackGilde;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;

import java.io.IOException;
import java.util.UUID;

public class BackPackGildedScreeHandler extends ScreenHandler {
    private final Inventory inventory;
    public BackPackGildedScreeHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf packetByteBuf) {
        super(Slimefun4Mod.BackPackGildedScreeHandlerTye, syncId);
        this.inventory = new SimpleInventory(45);
        this.addSlot(new BlackpackSlot(inventory, 0, 8, 9 -36 ));
        this.addSlot(new BlackpackSlot(inventory, 1, 26, 9 -36));
        this.addSlot(new BlackpackSlot(inventory, 2, 44, 9 -36));
        this.addSlot(new BlackpackSlot(inventory, 3, 62, 9 -36));
        this.addSlot(new BlackpackSlot(inventory, 4, 80, 9 -36));
        this.addSlot(new BlackpackSlot(inventory, 5, 98, 9 -36));
        this.addSlot(new BlackpackSlot(inventory, 6, 116, 9-36));
        this.addSlot(new BlackpackSlot(inventory, 7, 134, 9-36));
        this.addSlot(new BlackpackSlot(inventory, 8, 152, 9-36));
        this.addSlot(new BlackpackSlot(inventory, 9, 8, 27 - 36));
        this.addSlot(new BlackpackSlot(inventory, 10, 26, 27- 36));
        this.addSlot(new BlackpackSlot(inventory, 11, 44, 27- 36));
        this.addSlot(new BlackpackSlot(inventory, 12, 62, 27- 36));
        this.addSlot(new BlackpackSlot(inventory, 13, 80, 27- 36));
        this.addSlot(new BlackpackSlot(inventory, 14, 98, 27- 36));
        this.addSlot(new BlackpackSlot(inventory, 15, 116, 27- 36));
        this.addSlot(new BlackpackSlot(inventory, 16, 134, 27- 36));
        this.addSlot(new BlackpackSlot(inventory, 17, 152, 27- 36));
        this.addSlot(new BlackpackSlot(inventory, 18, 8, 45- 36));
        this.addSlot(new BlackpackSlot(inventory, 19, 26, 45- 36));
        this.addSlot(new BlackpackSlot(inventory, 20, 44, 45- 36));
        this.addSlot(new BlackpackSlot(inventory, 21, 62, 45- 36));
        this.addSlot(new BlackpackSlot(inventory, 22, 80, 45- 36));
        this.addSlot(new BlackpackSlot(inventory, 23, 98, 45 - 36));
        this.addSlot(new BlackpackSlot(inventory, 24, 116, 45- 36));
        this.addSlot(new BlackpackSlot(inventory, 25, 134, 45- 36));
        this.addSlot(new BlackpackSlot(inventory, 26, 152, 45- 36));
        this.addSlot(new BlackpackSlot(inventory, 27, 8, 63- 36));
        this.addSlot(new BlackpackSlot(inventory, 28, 26, 63- 36));
        this.addSlot(new BlackpackSlot(inventory, 29, 44, 63- 36));
        this.addSlot(new BlackpackSlot(inventory, 30, 62, 63- 36));
        this.addSlot(new BlackpackSlot(inventory, 31, 80, 63- 36));
        this.addSlot(new BlackpackSlot(inventory, 32, 98, 63- 36));
        this.addSlot(new BlackpackSlot(inventory, 33, 116, 63- 36));
        this.addSlot(new BlackpackSlot(inventory, 34, 134, 63- 36));
        this.addSlot(new BlackpackSlot(inventory, 35, 152, 63- 36));
        this.addSlot(new BlackpackSlot(inventory, 36, 8, 81- 36));
        this.addSlot(new BlackpackSlot(inventory, 37, 26, 81- 36));
        this.addSlot(new BlackpackSlot(inventory, 38, 44, 81- 36));
        this.addSlot(new BlackpackSlot(inventory, 39, 62, 81- 36));
        this.addSlot(new BlackpackSlot(inventory, 40, 80, 81- 36));
        this.addSlot(new BlackpackSlot(inventory, 41, 98, 81- 36));
        this.addSlot(new BlackpackSlot(inventory, 42, 116, 81- 36));
        this.addSlot(new BlackpackSlot(inventory, 43, 134, 81- 36));
        this.addSlot(new BlackpackSlot(inventory, 44, 152, 81- 36));
        int playerInvY = 115 - 36;
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
                    NbtCompound nbtCompound1 = DataBackPack.OnnbtData(UUID.fromString(backpackUUID));
                    if (nbtCompound1 != null){
                        int u = 0;
                        for (ItemStack i : DataBackPack.toItemStack(nbtCompound1,45)){
                            if (u <= 44){
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
        if (player.getStackInHand(Hand.MAIN_HAND).getItem() instanceof BackPackGilde){
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
    public void onSlotClick(int slotIndex, int button, SlotActionType actionType, PlayerEntity player) {
        super.onSlotClick(slotIndex, button, actionType, player);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
