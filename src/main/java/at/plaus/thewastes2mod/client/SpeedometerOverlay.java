package at.plaus.thewastes2mod.client;

import at.plaus.thewastes2mod.TheWastes2Mod;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class SpeedometerOverlay {
    private static final ResourceLocation resourceLocation = new ResourceLocation(TheWastes2Mod.MODID, "textures/speedometer");

    public static final IGuiOverlay HUD_SPEEDOMETER = ((gui, poseStack, partialTick, width, height) -> {
        int x = width / 2;
        int y = height;
        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem._setShaderTexture(0, resourceLocation);
        GuiComponent.blit(poseStack, x - 94, y - 54, 0, 0, 40, 40, 40, 40);

    });
}
