package com.menghuan.cn;

import com.menghuan.cn.effect.Effect;
import com.menghuan.cn.item.item;
import com.menghuan.cn.item.itemGrops;
import com.menghuan.cn.item.ToolItems.backpack;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class slimefun4Mod implements ModInitializer {


	public static final String MOD_ID = "slimefun4";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final Effect ANTIMICROBIAL_RESISTANCE = new Effect();

	@Override
	public void onInitialize(){
		LOGGER.info("Hello Fabric world!");
		item.regModitem();
		itemGrops.regItemGrops();
		Registry.register(Registries.STATUS_EFFECT,new Identifier(MOD_ID,"antimicrobial_resistance"),ANTIMICROBIAL_RESISTANCE);
	}
}