import os
import json
Lj = "/storage/emulated/0/Download/java/Fabric/fabric-example-mod-1.20/src/main/resources/assets/slimefun4/textures/item/models/slimefun/androids/android"
filename = os.listdir(Lj)
current_path = os.getcwd()
name = []
for i in filename:
  print(Lj + "/" + i)
  with open(Lj + "/" + i, 'r') as f:
    content = f.read()
  print(content)
  data = json.loads(content)
# 修改 "textures" 中的 "layer0"
  data['textures']['layer0'] = data['textures']['layer0'].replace('slimefun', 'myMod')

# 将修改后的数据转换回 JSON 格式
  modified_json = json.dumps(data, indent=2)
  with open(Lj + "/" + i, 'w') as file:
        # 将数据写入文件
        file.write(modified_json)
        #print(modified_json)