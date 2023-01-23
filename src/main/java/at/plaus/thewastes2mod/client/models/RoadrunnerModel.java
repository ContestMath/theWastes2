package at.plaus.thewastes2mod.client.models;

import at.plaus.thewastes2mod.TheWastes2Mod;
import at.plaus.thewastes2mod.entities.Roadrunner;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RoadrunnerModel extends AnimatedGeoModel<Roadrunner> {


    private static final ResourceLocation modelResource = new ResourceLocation(TheWastes2Mod.MODID, "geo/roadrunner.geo.json");
    private static final ResourceLocation textureResource = new ResourceLocation(TheWastes2Mod.MODID, "textures/entity/roadrunner.png");
    private static final ResourceLocation animationResource = new ResourceLocation(TheWastes2Mod.MODID, "animations/roadrunner.animation.json");


    @Override
    public ResourceLocation getModelResource(Roadrunner object) {
        return modelResource;
    }

    @Override
    public ResourceLocation getTextureResource(Roadrunner object) {
        return textureResource;
    }

    @Override
    public ResourceLocation getAnimationResource(Roadrunner animatable) {
        return animationResource;
    }
}
