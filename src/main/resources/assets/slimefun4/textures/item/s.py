import os
os.chdir("/storage/emulated/0/Download/java/Fabric/fabric-example-mod-1.20/src/main/resources/assets/slimefun4/textures/item/models/")

filename = os.listdir(".")

name = []
for i in filename:
    portion = os.path.splitext(i)   # 把文件名拆分为名字和后缀
    if portion[1] == ".zip":
        name.append(portion[0])
    if portion[1] == ".png":
        name_upper = portion[0].upper()
        name.append(portion[0])
        line = f'public static final Item {name_upper} = ModItem("{name[-1]}", new Item(new FabricItemSettings()));'
        print(line)