package at.plaus.thewastes2mod.gui.screen;

import at.plaus.thewastes2mod.TheWastes2Mod;
import at.plaus.thewastes2mod.gui.menu.CarMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class CarScreen extends AbstractContainerScreen<CarMenu> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(TheWastes2Mod.MODID, "textures/gui/test.png");
    public CarScreen(CarMenu container, Inventory pPlayerInventory, Component pTitle) {
        super(container, pPlayerInventory, pTitle);
        this.imageWidth = 176;
        this.imageHeight = 166;

    }



    @Override
    public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(ms);
        super.render(ms, mouseX, mouseY, partialTicks);
        this.renderTooltip(ms, mouseX, mouseY);
    }

    @Override
    protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderTexture(0, TEXTURE);
        blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
        RenderSystem.disableBlend();
    }

    @Override
    public void init() {
        super.init();
        this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
        this.addRenderableWidget(new Button(this.leftPos + 8, this.topPos + 21, 46, 15, Component.literal("Fuel"), e -> {
        }));
        this.addRenderableWidget(new Button(this.leftPos + 8, this.topPos + 36, 56, 15, Component.literal("Repair"), e -> {
        }));
        this.addRenderableWidget(new Button(this.leftPos + 8, this.topPos + 51, 61, 15, Component.literal("Pick-Up"), e -> {
        }));
    }




}
