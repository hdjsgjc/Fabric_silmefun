package com.menghuan.cn.handher;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

import static com.menghuan.cn.Slimefun4Mod.potableScreenHandlerScreenHandlerType;

public class PotableScreenHandler extends ScreenHandler {

    private Inventory inventory;

    public PotableScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf packetByteBuf) {
        super(potableScreenHandlerScreenHandlerType, syncId);
        this.inventory = new SimpleInventory(1);
        this.addSlot(new Slot(inventory, 0, 79, 32));
        int playerInvY = 79;
        int playerInvX = 8;
        for (int m = 0; m < 3; ++m) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, playerInvX + l * 18, playerInvY + m * 18));
            }
        }

        // 设置玩家快捷栏槽位位置
        for (int n = 0; n < 9; ++n) {
            this.addSlot(new Slot(playerInventory, n, playerInvX + n * 18, playerInvY + 58));
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        return ItemStack.EMPTY;
    }



    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
