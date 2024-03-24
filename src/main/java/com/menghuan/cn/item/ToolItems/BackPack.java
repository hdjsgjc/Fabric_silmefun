package com.menghuan.cn.item.ToolItems;

import com.menghuan.cn.handher.BackPack.BackPackScreeHandler;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.UUID;

public class BackPack extends Item {
    public BackPack(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient && user instanceof PlayerEntity) {
            user.openHandledScreen(new ExtendedScreenHandlerFactory() {
                @Override
                public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {

                }

                @Override
                public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
                    return new BackPackScreeHandler(syncId, playerInventory, new PacketByteBuf(Unpooled.buffer()));
                }

                @Override
                public Text getDisplayName() {
                    return Text.translatable("item.backpack.gui.teit");
                }
            });
        }
        return super.use(world, user, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient) {
            NbtCompound nbtCompound = stack.getOrCreateNbt();
            if (nbtCompound != null && !nbtCompound.contains("BackPackUUID")) {
                nbtCompound.putString("BackPackUUID", UUID.randomUUID().toString());
                stack.setNbt(nbtCompound);
            }
        }
    }

}

