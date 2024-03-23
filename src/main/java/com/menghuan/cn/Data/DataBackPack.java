package com.menghuan.cn.Data;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.NbtTagSizeTracker;
import net.minecraft.util.collection.DefaultedList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class DataBackPack{
  public static void IOnbtData(NbtCompound nbtCompound, UUID uuid) throws IOException {
      File file = new File(System.getProperty("user.dir") + File.separator + "BlockPackData" +File.separator + uuid.toString());
      if (!file.exists()){
          file.mkdirs();

      }
      File newFile = new File(System.getProperty("user.dir") + File.separator + "BlockPackData" +File.separator + uuid.toString() + File.separator +
              uuid.toString() + ".nbt");
      newFile.createNewFile();
      FileOutputStream outputStream = new FileOutputStream(newFile);
      NbtIo.writeCompressed(nbtCompound, outputStream);
      outputStream.close();

  }

  public static NbtCompound OnnbtData(UUID uuid,Long b) throws IOException {
      File newFile = new File(System.getProperty("user.dir") + File.separator + "BlockPackData" +File.separator + uuid.toString() + File.separator +
              uuid.toString() + ".nbt");
      if (newFile.exists()){
          FileInputStream inputStream = new FileInputStream(newFile);
          NbtTagSizeTracker sizeTracker = new NbtTagSizeTracker(b,5000);
          return NbtIo.readCompressed(inputStream,sizeTracker);
      }
      return null;
  }

    public static List<ItemStack> toItemStack(NbtCompound nbtCompound,int Slots){
        if (nbtCompound != null){
            DefaultedList<ItemStack> inputItems = DefaultedList.ofSize(Slots, ItemStack.EMPTY);
            for (String i : nbtCompound.getKeys()){
                NbtCompound itemNbtCompound =  nbtCompound.getCompound(i);
                int Slot = itemNbtCompound.getInt("Solt");
                itemNbtCompound.remove("Solt");
                inputItems.set(Slot,ItemStack.fromNbt(itemNbtCompound));
            }
            return inputItems;
        }
        return null;
    }

    public static NbtCompound toNbtCompound(Inventory inventory) {
        NbtCompound tag = new NbtCompound();
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack itemStack = inventory.getStack(i);
            NbtCompound itemTag = new NbtCompound();
            if (!itemStack.isEmpty()) {
                if (itemStack != null){
                    itemStack.writeNbt(itemTag);
                }

            }
            itemTag.putInt("Solt",i);
            tag.put("Item"+ i, itemTag);
        }

        return tag;
    }


}
