package com.menghuan.cn.item;

import com.menghuan.cn.ExampleMod;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class FoodItem  {
    public static final FoodComponent VITAMINS = new FoodComponent.Builder().hunger(0).saturationModifier(0).alwaysEdible().
             statusEffect(new StatusEffectInstance(ExampleMod.ANTIMICROBIAL_RESISTANCE,1200),1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH,1),1f).build();




}
