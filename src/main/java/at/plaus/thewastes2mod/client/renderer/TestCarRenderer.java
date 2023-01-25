package at.plaus.thewastes2mod.client.renderer;

import at.plaus.thewastes2mod.TheWastes2Mod;
import at.plaus.thewastes2mod.client.models.TestCarModel;
import at.plaus.thewastes2mod.entities.cars.TestCar;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;

public class TestCarRenderer extends LivingEntityRenderer<TestCar, EntityModel<TestCar>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(TheWastes2Mod.MODID, "textures/entity/testcar.png");


    public TestCarRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new TestCarModel(ctx.bakeLayer(TestCarModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(TestCar p_114482_) {
        return TEXTURE;
    }

}
