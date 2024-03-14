package com.menghuan.cn.Data;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.PersistentState;

public class DataBackPack extends PersistentState {
    public DataBackPack() {
        this.data = new NbtCompound();
    }
    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        return null;
    }

    private static final String TAG_NAME = "backpack__data";
    private final NbtCompound data;




}
