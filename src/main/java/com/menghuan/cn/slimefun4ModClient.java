package com.menghuan.cn;

import com.menghuan.cn.handher.BackScreen;
import com.menghuan.cn.handher.PotableScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class slimefun4ModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(Slimefun4Mod.screenHandlersblack,BackScreen::new);
        HandledScreens.register(Slimefun4Mod.potableScreenHandlerScreenHandlerType, PotableScreen::new);


    }


}