package com.menghuan.cn.item;

import com.menghuan.cn.util.Slimefun4Mod;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FoodItem extends Item {
    public static final FoodComponent vitamins = new FoodComponent.Builder().hunger(0).saturationModifier(0).alwaysEdible().
             statusEffect(new StatusEffectInstance(Slimefun4Mod.ANTIMICROBIAL_RESISTANCE,1200),1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH,2),1f).snack().build();

    public static final FoodComponent medicine = new FoodComponent.Builder().hunger(0).saturationModifier(0).alwaysEdible().
            statusEffect(new StatusEffectInstance(Slimefun4Mod.ANTIMICROBIAL_RESISTANCE,1200),1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH,2),1f).snack().build();
    public static final FoodComponent rag = new FoodComponent.Builder().hunger(0).saturationModifier(0).alwaysEdible().build();

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user){
        if (user instanceof PlayerEntity && Items.getSpecualValue(stack.getItem())){
            user.removeStatusEffect(StatusEffects.POISON);
            user.removeStatusEffect(StatusEffects.WITHER);
            user.extinguish();
        }
        return super.finishUsing(stack,world,user);
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.slimefun4.VITAMINS.tip").formatted(Formatting.BOLD, Formatting.GREEN));
        super.appendTooltip(stack, world, tooltip, context);
    }
    public FoodItem(Settings settings) {
        super(settings);
    }
}
