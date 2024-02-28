package com.menghuan.cn.item.ToolItems;

import com.menghuan.cn.Slimefun4Mod;
import com.menghuan.cn.handher.BackScreen;
import com.menghuan.cn.handher.BackScteeHandler;
import com.menghuan.cn.handher.PotableScreenHandler;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Potable extends Item {
    public Potable(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user instanceof  PlayerEntity && !world.isClient){
            user.openHandledScreen(new ExtendedScreenHandlerFactory() {
                @Override
                public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {

                }

                @Override
                public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
                    return new PotableScreenHandler(syncId, playerInventory,new PacketByteBuf(Unpooled.buffer()));
                }

                @Override
                public Text getDisplayName() {
                    return Text.of("Custom Screen");
                }
            });

        }
        return super.use(world, user, hand);
    }
}
