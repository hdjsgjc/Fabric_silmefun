package com.menghuan.cn.handher;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class BackInventory implements Inventory {
    private final DefaultedList<ItemStack> inventory;

    public BackInventory(int size) {
        this.inventory = DefaultedList.ofSize(size, ItemStack.EMPTY);
    }

    @Override
    public int size() {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : inventory) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getStack(int slot) {
        return inventory.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack stack = Inventories.splitStack(inventory, slot, amount);
        if (!stack.isEmpty()) {
            markDirty();
        }
        return stack;
    }

    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(inventory, slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        inventory.set(slot, stack);
        if (stack.getCount() > getMaxCountPerStack()) {
            stack.setCount(getMaxCountPerStack());
        }
        markDirty();
    }

    @Override
    public void markDirty() {
        // 在这里实现在背包内容发生改变时的逻辑
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        // 在这里实现判断玩家是否可以使用背包的逻辑
        return true;
    }

    @Override
    public void clear() {
        inventory.clear();
    }
}
