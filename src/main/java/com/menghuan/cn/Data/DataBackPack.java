package com.menghuan.cn.Data;

import net.minecraft.nbt.NbtCompound;

import com.google.gson.Gson;

import java.io.*;
import java.util.UUID;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.NbtTagSizeTracker;

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

  public static NbtCompound OnnbtData(UUID uuid) throws IOException {
      File newFile = new File(System.getProperty("user.dir") + File.separator + "BlockPackData" +File.separator + uuid.toString() + File.separator +
              uuid.toString() + ".nbt");
      if (newFile.exists()){
          FileInputStream inputStream = new FileInputStream(newFile);
          NbtTagSizeTracker sizeTracker = new NbtTagSizeTracker(5120L,5000);
          NbtCompound nbtCompound = NbtIo.readCompressed(inputStream,sizeTracker);
          return nbtCompound;
      }
      return null;
  }

}
