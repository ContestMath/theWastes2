package at.plaus.thewastes2mod.client.renderer;

import at.plaus.thewastes2mod.client.models.WarRigModel;
import at.plaus.thewastes2mod.entities.WarRig;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class WarRigRenderer extends GeoEntityRenderer<WarRig> {
    public WarRigRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WarRigModel());
    }

    @Override
    public RenderType getRenderType(WarRig animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        float scaler = 1.8f;
        poseStack.scale(scaler, scaler, scaler);
        return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}
