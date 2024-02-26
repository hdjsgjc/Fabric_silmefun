package com.menghuan.cn.effect;

import com.menghuan.cn.item.item;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.TypedActionResult;


public class Effect extends StatusEffect {
    private LivingEntity entit;

    public Effect() {
        super(StatusEffectCategory.HARMFUL,0xCD661D);
    }
    @Override
    public boolean canApplyUpdateEffect(int duration,int amplifier){
        return true;

    }
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier){
        if (entity instanceof PlayerEntity){
            this.entit = entity;
        UseItemCallback.EVENT.register((player, world, hand) -> {
            ItemStack itemStack = entity.getStackInHand(hand);
            if (itemStack.getItem().isFood()) {
                Item items = itemStack.getItem();
                if (items != null && item.getSpecualValue(items)) {
                    if (!entity.hasStatusEffect(this)) {
                        return TypedActionResult.pass(itemStack);
                    } else {
                        return TypedActionResult.fail(itemStack);
                    }
                }
                return TypedActionResult.pass(itemStack);
            }
            return TypedActionResult.pass(itemStack);
        });
      }
    }

}