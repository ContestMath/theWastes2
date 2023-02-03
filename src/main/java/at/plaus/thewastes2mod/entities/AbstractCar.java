package at.plaus.thewastes2mod.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractCar extends Mob {
    public float acceleration;
    public float rotSpeed;
    public float rollFrictionCoefficient = 0.0995f;
    public float glideFrictionCoefficient = rollFrictionCoefficient/1.03f;
    //public float driftFrictionCoefficient = rollFrictionCoefficient/1.02f;
    public float weight;
    public Vec3 movementVector = new Vec3(0,0,0);


    public AbstractCar(EntityType<? extends Mob> entity, Level level) {
        super(entity, level);
    }

    private final NonNullList<ItemStack> armorItems = NonNullList.withSize(4, ItemStack.EMPTY);

    public void doPlayerRide(Player player) {
        if (!this.level.isClientSide) {
            player.startRiding(this);
        }
    }

    public void positionRider(Entity entity, Entity.MoveFunction moveFunction, double xOffset, double yOffset, double zOffset) {
        if (this.hasPassenger(entity)) {
            double d0 = this.getY() + this.getPassengersRidingOffset() + entity.getMyRidingOffset();
            Vec3 offset = new Vec3(xOffset, yOffset, zOffset);
            Vec3 pos = new Vec3(this.getX() , d0 , this.getZ() );
            Vec3 result = pos.add(offset.yRot((float) -this.getYRot()/360*2*((float)Math.PI)));
            moveFunction.accept(entity, result.x, result.y, result.z);
        }
    }

    public abstract double[] getRiderOffset();

    @Override
    public void positionRider(Entity entity) {
        positionRider(entity, Entity::setPos, getRiderOffset()[0], getRiderOffset()[1], getRiderOffset()[2]);
    }

    @Override
    public boolean hurt(DamageSource source, float damage) {
        return super.hurt(source, damage);
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        doPlayerRide(player);
        return InteractionResult.sidedSuccess(this.level.isClientSide);
    }

    public static AttributeSupplier.Builder createStandartVehicleAttributes() {
        return  AbstractHorse.createMobAttributes()
                .add(ForgeMod.STEP_HEIGHT_ADDITION.get(), 1)
                .add(Attributes.JUMP_STRENGTH)
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.MOVEMENT_SPEED, (double)0.225F)
                ;
    }

    @Override
    public void setCustomNameVisible(boolean p_20341_) {
        super.setCustomNameVisible(false);
    }

    @Override
    public void travel(Vec3 vector) {
        Vec3 deltaMovementVector = new Vec3(0, 0, 0);
        Vec3 sight = this.getForward().normalize();
        Vec3 movementDirection = movementVector.normalize();
        float accForce = acceleration/weight;
        boolean isRollingFrictionType = false;

        if (movementDirection == deltaMovementVector) {
            movementDirection = sight;
        }

        if (this.hasExactlyOnePlayerPassenger()) {
            if (Minecraft.getInstance().options.keyUp.isDown()) {
                deltaMovementVector = deltaMovementVector.add(sight.scale(accForce));
                isRollingFrictionType = true;
            } else if (Minecraft.getInstance().options.keyDown.isDown() && movementDirection.dot(sight) > 0) {
                //deltaMovementVector = deltaMovementVector.add(sight.scale(-accForce * 2));
            }  else if (Minecraft.getInstance().options.keyDown.isDown() && movementDirection.dot(sight) <= 0) {
                deltaMovementVector = deltaMovementVector.add(sight.scale(-accForce * 0.25));
                isRollingFrictionType = true;
            }

            if (!Minecraft.getInstance().options.keyJump.isDown()) {
                if (Minecraft.getInstance().options.keyLeft.isDown()) {
                    this.setYRot(this.getYRot() - rotSpeed);
                } else if (Minecraft.getInstance().options.keyRight.isDown()) {
                    this.setYRot(this.getYRot() + rotSpeed);
                }
            } else {
                if (Minecraft.getInstance().options.keyLeft.isDown()) {
                    this.setYRot(this.getYRot() - 3 * rotSpeed);
                } else if (Minecraft.getInstance().options.keyRight.isDown()) {
                    this.setYRot(this.getYRot() + 3 * rotSpeed);
                }
            }

        }


        if (Math.abs(Math.acos(cosAngle(sight, movementDirection))) > Math.PI/2/5) {
            isRollingFrictionType = false;
        }

        movementVector = movementVector.add(deltaMovementVector);
        Double frictionSumm;

        if (isRollingFrictionType) {
            frictionSumm = Double.valueOf(rollFrictionCoefficient*10);
        } else {
            frictionSumm = Double.valueOf(glideFrictionCoefficient*10);
        }

        if (Minecraft.getInstance().options.keyDown.isDown() && movementDirection.dot(sight) > 0) {
            frictionSumm /= 1.1;
        }

        if (movementVector.length() < acceleration*1000 && deltaMovementVector.equals(new Vec3(0, 0, 0))) {
            movementVector = new Vec3(0,0,0);
        }

        if (!(movementVector.equals(new Vec3(0,0,0)))) {
            movementVector = movementVector.scale(frictionSumm);
        };
        setDeltaMovement(this.getDeltaMovement().scale(frictionSumm));
        setDeltaMovement(this.getDeltaMovement().add(movementVector));

        super.travel(vector);
    }

    public double cosAngle(Vec3 v1, Vec3 v2) {
        return v1.normalize().dot(v2.normalize());
    }


    @Nullable
    public abstract AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player p_39956_);
}