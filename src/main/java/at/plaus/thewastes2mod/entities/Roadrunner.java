package at.plaus.thewastes2mod.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
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

    public Roadrunner(EntityType<? extends Mob> entity, Level level) {
        super(entity, level);
        this.noCulling = true;
        this.speed = 0.4f;
        this.rotSpeed = 2f;
        this.maxHealth = 10;
    }

    @Override
    public double[] getRiderOffset() {
        return new double[]{
                0.89 /2*1.6,
                -1.2/2*1.6,
                0.49 /2*1.6
        };
    }


    public static AttributeSupplier.Builder getAttribues() {
        return createStandartVehicleAttributes()
                .add(Attributes.MAX_HEALTH, 8)
                ;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(
                new AnimationController<Roadrunner>(this, "controller", 0, this::drivePredicate));
        data.addAnimationController(
                new AnimationController<Roadrunner>(this, "controller1", 0, this::wipePredicate));
    }


    private <E extends IAnimatable> PlayState drivePredicate(AnimationEvent<E> event) {
        if (event.isMoving() && this.getFirstPassenger() != null) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.roadrunner.drive", ILoopType.EDefaultLoopTypes.LOOP));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.roadrunner.idle", ILoopType.EDefaultLoopTypes.LOOP));
        }
        return PlayState.CONTINUE;
    }

    private <E extends IAnimatable> PlayState wipePredicate(AnimationEvent<E> event) {
        assert Minecraft.getInstance().level != null;
        if (Minecraft.getInstance().level.isRaining()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.roadrunner.wipers", ILoopType.EDefaultLoopTypes.LOOP));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.roadrunner.idle", ILoopType.EDefaultLoopTypes.LOOP));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
