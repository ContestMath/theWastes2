package at.plaus.thewastes2mod.client.renderer;

import at.plaus.thewastes2mod.client.models.RoadrunnerModel;
import at.plaus.thewastes2mod.entities.Roadrunner;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RoadrunnerRenderer extends GeoEntityRenderer<Roadrunner> {
    public RoadrunnerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RoadrunnerModel());
    }

    @Override
    public RenderType getRenderType(Roadrunner animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        float scaler = 1.6f;
        poseStack.scale(scaler, scaler, scaler);
        return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}
