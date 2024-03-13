package com.menghuan.cn;

import com.menghuan.cn.effect.Effect;
import com.menghuan.cn.handher.BackScteeHandler;
import com.menghuan.cn.handher.PotableScreenHandler;
import com.menghuan.cn.item.ItemGrops;
import com.menghuan.cn.item.Items;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Slimefun4Mod implements ModInitializer {


	public static final String MOD_ID = "slimefun4";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final Effect ANTIMICROBIAL_RESISTANCE = new Effect();
    public static ScreenHandlerType<BackScteeHandler> screenHandlersblack = null;
	public static ScreenHandlerType<PotableScreenHandler> potableScreenHandlerScreenHandlerType = null;

	@Override
	public void onInitialize(){
		LOGGER.info("Hello Fabric world!");
		Items.regModitem();
		ItemGrops.regItemGrops();
		potableScreenHandlerScreenHandlerType = Registry.register(Registries.SCREEN_HANDLER, new Identifier(Slimefun4Mod.MOD_ID,
				"potable_screen_handler"), new ExtendedScreenHandlerType<>(PotableScreenHandler::new));

		screenHandlersblack = Registry.register(Registries.SCREEN_HANDLER, new Identifier(Slimefun4Mod.MOD_ID,
				"antimicrobial_resistance"), new ExtendedScreenHandlerType<>(BackScteeHandler::new));

		Registry.register(Registries.STATUS_EFFECT,new Identifier(MOD_ID,"antimicrobial_resistance"),ANTIMICROBIAL_RESISTANCE);
	}
}