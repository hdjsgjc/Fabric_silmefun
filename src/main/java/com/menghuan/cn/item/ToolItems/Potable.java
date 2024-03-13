package com.menghuan.cn.item.ToolItems;

import com.menghuan.cn.handher.PotableScreenHandler;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
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
import org.jetbrains.annotations.NotNull;


public class Potable extends Item {
    public Potable(Item.Settings settings) {
        super(settings);
    }

    @NotNull
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient()) {
            player.openHandledScreen(new ExtendedScreenHandlerFactory() {
                @Override
                public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {

                }

                @Override
                public Text getDisplayName() {
                    return Text.translatable("item.potable.gui.tiet");
                }
                @NotNull
                @Override
                public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
                    return new PotableScreenHandler(syncId, playerInventory, new PacketByteBuf(Unpooled.buffer())){

                    };
                }
            });
        }
        return TypedActionResult.success(player.getStackInHand(hand));
    }

}

