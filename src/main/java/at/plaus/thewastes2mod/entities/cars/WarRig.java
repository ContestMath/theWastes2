package at.plaus.thewastes2mod.entities.cars;

import at.plaus.thewastes2mod.entities.AbstractCar;
import at.plaus.thewastes2mod.entities.Roadrunner;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class WarRig extends AbstractCar implements IAnimatable {

    private AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public WarRig(EntityType<? extends LivingEntity> entity, Level level) {
        super(entity, level);
        this.noCulling = true;
        this.speed = 0.2f;
        this.rotSpeed = 1f;
        this.maxHealth = 40;
    }

    @Override
    public double[] getRiderOffset() {
        return new double[]{
                0.675,
                0.3,
                1.1
        };
    }

    public static AttributeSupplier.Builder createStandartVehicleAttributes() {
        return  AbstractHorse.createMobAttributes()
                .add(ForgeMod.STEP_HEIGHT_ADDITION.get(), 1)
                .add(Attributes.MAX_HEALTH, 20)
                ;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(
                new AnimationController<WarRig>(this, "controller", 0, this::drivePredicate));
        data.addAnimationController(
                new AnimationController<WarRig>(this, "controller1", 0, this::wipePredicate));
    }


    private <E extends IAnimatable> PlayState drivePredicate(AnimationEvent<E> event) {
        if (event.isMoving() && this.getFirstPassenger() != null) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.war_rig.drive", ILoopType.EDefaultLoopTypes.LOOP));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.war_rig.idle", ILoopType.EDefaultLoopTypes.LOOP));
        }
        return PlayState.CONTINUE;
    }

    private <E extends IAnimatable> PlayState wipePredicate(AnimationEvent<E> event) {
        assert Minecraft.getInstance().level != null;
        if (Minecraft.getInstance().level.isRaining()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.war_rig.wipers", ILoopType.EDefaultLoopTypes.LOOP));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.war_rig.idle", ILoopType.EDefaultLoopTypes.LOOP));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
