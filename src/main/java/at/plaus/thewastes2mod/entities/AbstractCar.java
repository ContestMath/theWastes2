package at.plaus.thewastes2mod.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;

public abstract class AbstractCar extends LivingEntity {
    public float speed;
    public float rotSpeed;
    public int maxHealth;


    public AbstractCar(EntityType<? extends LivingEntity> entity, Level level) {
        super(entity, level);
    }

    private final NonNullList<ItemStack> armorItems = NonNullList.withSize(4, ItemStack.EMPTY);

    public Iterable<ItemStack> getArmorSlots() {
        return this.armorItems;
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot slot) {
        return this.armorItems.get(slot.getIndex());
    }

    @Override
    public void setItemSlot(EquipmentSlot p_21036_, ItemStack p_21037_) {

    }

    @Override
    public HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }

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
        if (damage < this.getHealth()) {
            return false;
        }
        return super.hurt(source, damage);
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
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
    private int getThisMaxHealth() {
        return maxHealth;
    }

    @Override
    public void travel(Vec3 vector) {
        Vec3 deltaMovementVector = new Vec3(0, 0, 0);
        Vec3 sight = this.getLookAngle();

        if(this.hasExactlyOnePlayerPassenger()) {
            if (Minecraft.getInstance().options.keyUp.isDown()) {deltaMovementVector = sight;}
            if (Minecraft.getInstance().options.keyLeft.isDown()) {
                this.setYRot(this.getYRot()-rotSpeed);
                deltaMovementVector.scale(Math.cos(rotSpeed/360f*Math.PI*2));
            } else if (Minecraft.getInstance().options.keyRight.isDown()) {
                this.setYRot(this.getYRot()+rotSpeed);
                deltaMovementVector.scale(Math.cos(rotSpeed/360f*Math.PI*2));
            }
        }

        setDeltaMovement(this.getDeltaMovement().add(deltaMovementVector.scale(speed)));
        super.travel(vector);
    }

}