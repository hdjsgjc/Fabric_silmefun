package com.menghuan.cn.handher;

import com.menghuan.cn.Slimefun4Mod;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BackScreen extends HandledScreen<BackScteeHandler> {

    public static final Identifier TEXTER = new Identifier(Slimefun4Mod.MOD_ID,"textures/gui/porable_bustbin.png");
    public BackScreen(BackScteeHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {

    }
}
