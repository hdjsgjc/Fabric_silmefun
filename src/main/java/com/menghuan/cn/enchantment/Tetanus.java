package com.menghuan.cn.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

public class Tetanus extends Enchantment {
    public Tetanus() {
        super(Enchantment.Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        super.onTargetDamaged(user, target, level);
        if (user instanceof PlayerEntity && target instanceof LivingEntity){
            if (!user.getWorld().isClient()){
                ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,600,3));
                ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.POISON,300,3));
                ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER,180,3));
            }
        }
    }
}
