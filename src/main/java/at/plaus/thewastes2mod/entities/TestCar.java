package at.plaus.thewastes2mod.entities;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;


public class TestCar extends AbstractCar {

    public TestCar(EntityType<? extends LivingEntity> entity, Level level) {
        super(entity, level);
        this.speed = 0.25f;
        this.rotSpeed = 4f;
        this.maxHealth = 8;
    }

    @Override
    public void positionThisRider(Entity entity, MoveFunction moveFunction) {
        if (this.hasPassenger(entity)) {
            double d0 = this.getY() + this.getPassengersRidingOffset() + entity.getMyRidingOffset();
            moveFunction.accept(entity, this.getX(), d0, this.getZ());
        }
    }

    public static AttributeSupplier.Builder getAttribues() {
        return createStandartVehicleAttributes()
                .add(Attributes.MAX_HEALTH, 8)
                ;
    }
}
