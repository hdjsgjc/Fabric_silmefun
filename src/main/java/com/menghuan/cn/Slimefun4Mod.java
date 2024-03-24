package com.menghuan.cn;

import com.menghuan.cn.effect.AntimicrobialResistance;
import com.menghuan.cn.enchantment.Tetanus;
import com.menghuan.cn.handher.BackPack.BackPackLargeScreeHandler;
import com.menghuan.cn.handher.BackPack.BackPackScreeHandler;
import com.menghuan.cn.handher.BackPack.BackPackWovenScreeHandler;
import com.menghuan.cn.handher.BackPack.BackSmallScreeHandler;
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
    public static final AntimicrobialResistance ANTIMICROBIAL_RESISTANCE = new AntimicrobialResistance();
	public static Tetanus TETANUS = new Tetanus();
    public static ScreenHandlerType<BackSmallScreeHandler> screenHandlersblack = null;
	public static ScreenHandlerType<PotableScreenHandler> potableScreenHandlerScreenHandlerType = null;
	public static ScreenHandlerType<BackPackScreeHandler> BackPackScreenHandlerScreenHandlerType = null;
	public static ScreenHandlerType<BackPackLargeScreeHandler> BackPackLargeScreeHandlerTye = null;
	public static ScreenHandlerType<BackPackWovenScreeHandler> BackPackWovenScreeHandler =null;

	@Override
	public void onInitialize(){
		LOGGER.info("Hello Fabric world!");
		Items.regModitem();
		ItemGrops.regItemGrops();
		potableScreenHandlerScreenHandlerType = Registry.register(Registries.SCREEN_HANDLER, new Identifier(MOD_ID,
				"potable_screen_handler"), new ExtendedScreenHandlerType<>(PotableScreenHandler::new));

		screenHandlersblack = Registry.register(Registries.SCREEN_HANDLER, new Identifier(MOD_ID,
				"antimicrobial_resistance"), new ExtendedScreenHandlerType<>(BackSmallScreeHandler::new));
		BackPackScreenHandlerScreenHandlerType = Registry.register(Registries.SCREEN_HANDLER,new Identifier(MOD_ID,
				"sackpackscreenhandlerscreenhandler"),new ExtendedScreenHandlerType<>(BackPackScreeHandler::new));
		BackPackLargeScreeHandlerTye = Registry.register(Registries.SCREEN_HANDLER,new Identifier(MOD_ID,
				"backpacklargescreehandler"),new ExtendedScreenHandlerType<>(BackPackLargeScreeHandler::new));
		BackPackWovenScreeHandler = Registry.register(Registries.SCREEN_HANDLER,new Identifier(MOD_ID,
				"backpackwovenscreehandler"),new ExtendedScreenHandlerType<>(BackPackWovenScreeHandler::new));
		Registry.register(Registries.ENCHANTMENT,new Identifier(MOD_ID,"teeanus"),TETANUS);
		Registry.register(Registries.STATUS_EFFECT,new Identifier(MOD_ID,"antimicrobial_resistance"),ANTIMICROBIAL_RESISTANCE);
	}
}