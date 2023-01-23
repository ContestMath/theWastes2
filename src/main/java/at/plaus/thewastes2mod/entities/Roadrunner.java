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


    public static AttributeSupplier.Builder getAttribues() {
        return createStandartVehicleAttributes()
                .add(Attributes.MAX_HEALTH, 8)
                ;
    }

    public void positionThisRider(Entity entity, Entity.MoveFunction moveFunction) {
        if (this.hasPassenger(entity)) {
            double d0 = this.getY() + this.getPassengersRidingOffset() + entity.getMyRidingOffset();
            moveFunction.accept(entity, this.getX() , d0, this.getZ());
        }
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.roadrunner.drive", ILoopType.EDefaultLoopTypes.LOOP));
        }
        assert Minecraft.getInstance().level != null;
        if (Minecraft.getInstance().level.isRaining()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.roadrunner.wipers", ILoopType.EDefaultLoopTypes.LOOP));
        }
        //event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.bat.fly", ILoopType.EDefaultLoopTypes.LOOP));
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
