package at.plaus.thewastes2mod.client.renderer;

import at.plaus.thewastes2mod.client.models.RoadrunnerModel;
import at.plaus.thewastes2mod.entities.Roadrunner;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RoadrunnerRenderer extends GeoEntityRenderer<Roadrunner> {
    public RoadrunnerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RoadrunnerModel());
    }
}
