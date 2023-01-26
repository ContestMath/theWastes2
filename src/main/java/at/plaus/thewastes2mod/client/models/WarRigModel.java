package at.plaus.thewastes2mod.client.models;

import at.plaus.thewastes2mod.TheWastes2Mod;
import at.plaus.thewastes2mod.entities.WarRig;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WarRigModel extends AnimatedGeoModel<WarRig> {


    private static final ResourceLocation modelResource = new ResourceLocation(TheWastes2Mod.MODID, "geo/war_rig.geo.json");
    private static final ResourceLocation textureResource = new ResourceLocation(TheWastes2Mod.MODID, "textures/entity/war_rig.png");
    private static final ResourceLocation animationResource = new ResourceLocation(TheWastes2Mod.MODID, "animations/war_rig.animation.json");

    @Override
    public ResourceLocation getModelResource(WarRig object) {
        return modelResource;
    }

    @Override
    public ResourceLocation getTextureResource(WarRig object) {
        return textureResource;
    }

    @Override
    public ResourceLocation getAnimationResource(WarRig animatable) {
        return animationResource;
    }
}
