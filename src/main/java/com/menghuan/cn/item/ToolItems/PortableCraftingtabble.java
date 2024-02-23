package com.menghuan.cn.item.ToolItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PortableCraftingtabble extends Item {


    public PortableCraftingtabble(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context){
        System.out.print(context);
        World world = context.getWorld();
        if (!world.isClient()){
            BlockPos blockPos = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            openCraftingtabble(player,blockPos);
            boolean foundBlock = false;
            for (int i = -64; i <= blockPos.getY() + 64; i++){
                BlockState state = context.getWorld().getBlockState(blockPos.down(i));
                if(isRightBLOCK(state)){
                    outputMessge(blockPos.down(i),player,state.getBlock());
                    foundBlock = true;
                    break;
                }
            }
            if(!foundBlock){
                player.sendMessage(Text.literal("NO Ore Furind"));
            }
        }
        context.getStack().damage(1,context.getPlayer(),
                playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
        return ActionResult.SUCCESS;
    }

    private void outputMessge(BlockPos down, PlayerEntity player, Block block) {
        player.sendMessage(Text.literal("found" + block.getName().getString() + "at" +
                '(' + down.getX() + "," + down.getY() + ',' + down.getZ() + ")" ));
    }

    private boolean isRightBLOCK(BlockState state) {
        return state.isOf(Blocks.IRON_ORE);
    }

    public static void openCraftingtabble(PlayerEntity player,BlockPos pos) {
       player.openHandledScreen((NamedScreenHandlerFactory) Blocks.CRAFTING_TABLE);

    }
}