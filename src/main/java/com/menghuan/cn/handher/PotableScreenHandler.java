package com.menghuan.cn.handher;

import com.menghuan.cn.item.Items;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;

import static com.menghuan.cn.Slimefun4Mod.potableScreenHandlerScreenHandlerType;

public class PotableScreenHandler extends ScreenHandler {

    private Inventory inventory;
    public PotableScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf byteBuf) {
        super(potableScreenHandlerScreenHandlerType, syncId);

        this.inventory = new SimpleInventory(1);
        this.addSlot(new Slot(inventory, 0, 79, 32){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() != Items.PORTABLE_DUSTBIN;
            }
        });
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
        if (slotIndex == 0){
             if (inventory.getStack(slotIndex) != ItemStack.EMPTY){
                 inventory.setStack(0,ItemStack.EMPTY);
             }
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
