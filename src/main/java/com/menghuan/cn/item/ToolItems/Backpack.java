package com.menghuan.cn.item.ToolItems;

import com.menghuan.cn.handher.BackScteeHandler;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
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


public class Backpack extends Item {
    private UUID UUIDs = UUID.randomUUID();
    private BackScteeHandler backScteeHandler;

    public Backpack(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user instanceof PlayerEntity && !world.isClient){
            user.openHandledScreen(new ExtendedScreenHandlerFactory() {
                @Override
                public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {

                }

                @Override
                public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
                    backScteeHandler = new BackScteeHandler(syncId,playerInventory, new PacketByteBuf(Unpooled.buffer()));
                    return backScteeHandler;
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
        super.inventoryTick(stack, world, entity, slot, selected);
        if (!world.isClient){
            NbtCompound nbtCompound = stack.getOrCreateNbt();
            if (nbtCompound != null && !nbtCompound.contains("BackPackUUID")) {
                nbtCompound.putString("BackPackUUID", UUID.randomUUID().toString());
                stack.setNbt(nbtCompound);
            }
        }
    }


    public void setItemUUID(UUID uuid){
        this.UUIDs = uuid;
    }

    public UUID getItemUUID(){
        return UUIDs;
    }

}
