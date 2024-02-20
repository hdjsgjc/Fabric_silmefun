import json
import os

directory_path = "/storage/emulated/0/Download/java/Fabric/Fabric_silmefun/src/main/resources/assets/slimefun4/textures/item/models/slimefun/items"  # 指定目录路径

for file_name in os.listdir(directory_path):
    if file_name.endswith(".json"):
        file_path = os.path.join(directory_path, file_name)
        with open(file_path, "r") as f:
            data = json.load(f)

        textures = data.get("textures", {})
        layer0 = textures.get("layer0", "")
        if "myMod" in layer0:
            layer0 = layer0.replace("myMod", "slimefun")
            textures["layer0"] = layer0

        with open(file_path, "w") as f:
            json.dump(data, f, indent=2)

        print("Modified file:", file_name)
