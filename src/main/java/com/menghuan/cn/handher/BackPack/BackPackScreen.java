package com.menghuan.cn.handher.BackPack;

import com.menghuan.cn.Slimefun4Mod;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BackPackScreen extends HandledScreen<BackPackScreeHandler> {

    public static final Identifier TEXTER = new Identifier(Slimefun4Mod.MOD_ID,"textures/gui/backpackgui.png");
    public BackPackScreen(BackPackScreeHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);

    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f,1f,1f,1f);
        RenderSystem.setShaderTexture(0,TEXTER);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundWidth) / 2;
        context.drawTexture(TEXTER,x,y,0,0,backgroundWidth,backgroundHeight,176,166);


    }

    @Override
    protected void init() {
        this.titleX = 8;
        this.titleY = 2;
        playerInventoryTitleX = 10;
        playerInventoryTitleY = 69;
        super.init();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context,mouseX,mouseY);
    }
}
