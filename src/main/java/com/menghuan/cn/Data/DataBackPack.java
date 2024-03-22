package com.menghuan.cn.Data;

import net.minecraft.nbt.NbtCompound;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.UUID;

public class DataBackPack{
  public static void IOnbtData(NbtCompound nbtCompound, UUID uuid) throws IOException {
      File file = new File(System.getProperty("user.dir") + File.separator + "BlockPackData" +File.separator + uuid.toString());
      if (!file.exists()){
          file.mkdirs();

      }
      File newFile = new File(System.getProperty("user.dir") + File.separator + "BlockPackData" +File.separator + uuid.toString() + File.separator +
              uuid.toString() + ".json");
      newFile.createNewFile();
      FileWriter fileWriter = new FileWriter(newFile);
      fileWriter.write(new Gson().toJson(nbtCompound));
      fileWriter.close();

  }

}
