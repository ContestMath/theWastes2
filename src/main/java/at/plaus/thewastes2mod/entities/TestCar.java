package at.plaus.thewastes2mod.entities;

import at.plaus.thewastes2mod.entities.AbstractCar;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;


public class TestCar extends AbstractCar {

    public TestCar(EntityType<? extends Mob> entity, Level level) {
        super(entity, level);
        this.speed = 0.25f;
        this.rotSpeed = 4f;
        this.maxHealth = 8;
    }

    @Override
    public double[] getRiderOffset() {
        return new double[]{0, 0, 0};
    }


    public static AttributeSupplier.Builder getAttribues() {
        return createStandartVehicleAttributes()
                .add(Attributes.MAX_HEALTH, 8)
                ;
    }

}