
package com.menghuan.cn.handher;

import com.menghuan.cn.handher.Slot.BlackpackSlot;
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

import static com.menghuan.cn.Slimefun4Mod.screenHandlersblack;

public class BackScteeHandler extends ScreenHandler {

    private final Inventory inventory;

    public BackScteeHandler(int syncId, PlayerInventory playerInventory,PacketByteBuf packetByteBuf) {
        super(screenHandlersblack, syncId);
        this.inventory = new SimpleInventory(10);
        this.addSlot(new BlackpackSlot(inventory, 0, 8, 9));
        this.addSlot(new BlackpackSlot(inventory, 1, 26, 9));
        this.addSlot(new BlackpackSlot(inventory, 2, 44, 9));
        this.addSlot(new BlackpackSlot(inventory, 3, 62, 9));
        this.addSlot(new BlackpackSlot(inventory, 4, 80, 9));
        this.addSlot(new BlackpackSlot(inventory, 5, 98, 9));
        this.addSlot(new BlackpackSlot(inventory, 7, 116, 9));
        this.addSlot(new BlackpackSlot(inventory, 8, 134, 9));
        this.addSlot(new BlackpackSlot(inventory, 9, 152, 9));
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

        NbtCompound tag = new NbtCompound();
        playerInventory.player.writeNbt(tag);
        System.out.println(tag.getKeys());
        System.out.println(tag);
        System.out.println();

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
        if (saveToPlayerData()!= null){
            NbtCompound tag = new NbtCompound();
            player.writeNbt(tag);
            tag.put("backpackdata", saveToPlayerData());
            player.readNbt(tag);


        }

    }

    private NbtCompound saveToPlayerData() {
            NbtCompound tag = new NbtCompound();
            for (int i = 0; i < inventory.size(); i++) {
                ItemStack itemStack = inventory.getStack(i);
                NbtCompound itemTag = new NbtCompound();
                if (!itemStack.isEmpty()) {
                    itemStack.writeNbt(itemTag);
                }
                tag.put("Item" + i, itemTag);
            }

            return tag;

    }




    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }


}