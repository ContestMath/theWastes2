package at.plaus.thewastes2mod.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;


public class Roadrunner extends AbstractCar implements IAnimatable {

    private AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public Roadrunner(EntityType<? extends LivingEntity> entity, Level level) {
        super(entity, level);
        this.noCulling = true;
        this.speed = 0.4f;
        this.rotSpeed = 2f;
        this.maxHealth = 10;
    }

    @Override
    public double[] getRiderOffset() {
        return new double[]{
                0.775,
                -0.6,
                0.5
        };
    }


    public static AttributeSupplier.Builder getAttribues() {
        return createStandartVehicleAttributes()
                .add(Attributes.MAX_HEALTH, 8)
                ;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {

        AnimationBuilder drive = new AnimationBuilder().addAnimation("animation.roadrunner.drive", ILoopType.EDefaultLoopTypes.LOOP);
        AnimationBuilder wipe = new AnimationBuilder().addAnimation("animation.roadrunner.wipers", ILoopType.EDefaultLoopTypes.LOOP);


        if (event.isMoving()) {
            event.getController().setAnimation(drive);
        } else {
            drive.clearAnimations();
            event.getController().setAnimation(drive);
        }

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<Roadrunner>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
