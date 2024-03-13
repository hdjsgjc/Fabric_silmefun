
package com.menghuan.cn.handher;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

import static com.menghuan.cn.Slimefun4Mod.screenHandlersblack;

public class BackScteeHandler extends ScreenHandler {

    private Inventory inventory;

    public BackScteeHandler(int syncId, PlayerInventory playerInventory,PacketByteBuf packetByteBuf) {
        super(screenHandlersblack, syncId);
        this.inventory = new BackInventory(9);
        this.addSlot(new Slot(inventory, 0, 79, 32));
        this.addSlot(new Slot(inventory, 0, 79, 32));
        this.addSlot(new Slot(inventory, 0, 79, 32));
        this.addSlot(new Slot(inventory, 0, 79, 32));
        this.addSlot(new Slot(inventory, 0, 79, 32));
        this.addSlot(new Slot(inventory, 0, 79, 32));
        this.addSlot(new Slot(inventory, 0, 79, 32));
        this.addSlot(new Slot(inventory, 0, 79, 32));
        this.addSlot(new Slot(inventory, 0, 79, 32));
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
    public boolean canUse(PlayerEntity player) {
        return true;
    }


}