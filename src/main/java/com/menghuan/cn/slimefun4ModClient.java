package com.menghuan.cn;

import com.menghuan.cn.handher.BackPack.*;
import com.menghuan.cn.handher.PotableScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class slimefun4ModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(Slimefun4Mod.screenHandlersblack, BackSmallScreen::new);
        HandledScreens.register(Slimefun4Mod.potableScreenHandlerScreenHandlerType, PotableScreen::new);
        HandledScreens.register(Slimefun4Mod.BackPackScreenHandlerScreenHandlerType, BackPackScreen::new);
        HandledScreens.register(Slimefun4Mod.BackPackLargeScreeHandlerTye, BackPackLargeScreen::new);

    }


}