
package com.menghuan.cn.handher;

import com.menghuan.cn.Data.DataBackPack;
import com.menghuan.cn.handher.Slot.BlackpackSlot;
import com.menghuan.cn.item.ToolItems.Backpack;
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
import net.minecraft.util.collection.DefaultedList;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.menghuan.cn.Slimefun4Mod.screenHandlersblack;
import static net.minecraft.inventory.Inventories.writeNbt;

public class BackScteeHandler extends ScreenHandler {

    private final Inventory inventory;

    public BackScteeHandler(int syncId, PlayerInventory playerInventory,PacketByteBuf packetByteBuf){
        super(screenHandlersblack, syncId);
        this.inventory = new SimpleInventory(9);
        this.addSlot(new BlackpackSlot(inventory, 0, 8, 9));
        this.addSlot(new BlackpackSlot(inventory, 1, 26, 9));
        this.addSlot(new BlackpackSlot(inventory, 2, 44, 9));
        this.addSlot(new BlackpackSlot(inventory, 3, 62, 9));
        this.addSlot(new BlackpackSlot(inventory, 4, 80, 9));
        this.addSlot(new BlackpackSlot(inventory, 5, 98, 9));
        this.addSlot(new BlackpackSlot(inventory, 6, 116, 9));
        this.addSlot(new BlackpackSlot(inventory, 7, 134, 9));
        this.addSlot(new BlackpackSlot(inventory, 8, 152, 9));
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
                    if (!(DataBackPack.OnnbtData(UUID.fromString(backpackUUID)) == null)){
                        NbtCompound nbtCompound1 = DataBackPack.OnnbtData(UUID.fromString(backpackUUID));
                        int u = 0;
                        for (ItemStack i :  RecoverBackPack(nbtCompound1)){
                            if (u <= 8){
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
    public void onSlotClick(int slotIndex, int button, SlotActionType actionType, PlayerEntity player) {
        super.onSlotClick(slotIndex, button, actionType, player);
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        if (player.getStackInHand(Hand.MAIN_HAND).getItem() instanceof Backpack){
            try {
                NbtCompound nbtCompound = player.getStackInHand(Hand.MAIN_HAND).getOrCreateNbt();
                if (nbtCompound != null){
                    if (nbtCompound.contains("BackPackUUID")){
                        String backpackUUID = nbtCompound.getString("BackPackUUID");
                        DataBackPack.IOnbtData(saveToPlayerData(), UUID.fromString(backpackUUID));
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private NbtCompound saveToPlayerData() {
            NbtCompound tag = new NbtCompound();
            for (int i = 0; i < inventory.size(); i++) {
                ItemStack itemStack = inventory.getStack(i);
                NbtCompound itemTag = new NbtCompound();
                if (!itemStack.isEmpty()) {
                    if (itemStack != null){
                        itemStack.writeNbt(itemTag);
                    }

                }
                tag.put("Item"+ i, itemTag);
            }

            return tag;
    }
    public List<ItemStack> RecoverBackPack(NbtCompound nbtCompound){
        int u = -1;
        if (nbtCompound != null){
            DefaultedList<ItemStack> inputItems = DefaultedList.ofSize(9, ItemStack.EMPTY);
            for (String i : nbtCompound.getKeys()){
                u++;
                if (u <= 8){
                    NbtCompound itemNbtCompound =  nbtCompound.getCompound(i);
                    inputItems.set(u,ItemStack.fromNbt(itemNbtCompound));
                    System.out.println(u);
                }else {
                    break;
                }
            }
            return inputItems;
        }
        return null;
    }
    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }


}