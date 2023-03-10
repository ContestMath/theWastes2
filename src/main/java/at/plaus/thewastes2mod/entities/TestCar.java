package at.plaus.thewastes2mod.entities;

import at.plaus.thewastes2mod.entities.AbstractCar;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;


public class TestCar extends AbstractCar {

    public TestCar(EntityType<? extends Mob> entity, Level level) {
        super(entity, level);
        this.acceleration = 0.25f;
        this.rotSpeed = 4f;
    }

    @Override
    public double[] getRiderOffset() {
        return new double[]{0, 0, 0};
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player p_39956_) {
        return null;
    }




    public static AttributeSupplier.Builder getAttribues() {
        return createStandartVehicleAttributes()
                .add(Attributes.MAX_HEALTH, 8)
                ;
    }

}
