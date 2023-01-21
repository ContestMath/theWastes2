package at.plaus.thewastes2mod.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Rotations;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;


public class TestCar extends LivingEntity {
    private float standAnimO;
    private float standAnim;

    public TestCar(EntityType<? extends LivingEntity> entity, Level level) {
        super(entity, level);
        this.setCustomNameVisible(false);
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

    protected void doPlayerRide(Player p_30634_) {
        if (!this.level.isClientSide) {
            p_30634_.setYRot(this.getYRot());
            p_30634_.setXRot(this.getXRot());
            p_30634_.startRiding(this);
        }
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {

        ItemStack itemstack = player.getItemInHand(hand);
        doPlayerRide(player);
        return InteractionResult.sidedSuccess(this.level.isClientSide);
    }



    public static AttributeSupplier.Builder getAttribues() {
        return  AbstractHorse.createBaseHorseAttributes()
                .add(ForgeMod.STEP_HEIGHT_ADDITION.get(), 3)
                ;
    }

    @Override
    public void travel(Vec3 vector) {
        Vec3 deltaMovementVector = new Vec3(0, 0, 0);
        Vec3 sight = this.getLookAngle();
        sight.dot(new Vec3(1, 0, 1));
        sight.normalize();

        if(this.hasExactlyOnePlayerPassenger() && Minecraft.getInstance().options.keyUp.isDown()) {
            deltaMovementVector = sight;
            if (Minecraft.getInstance().options.keyLeft.isDown()) {
                this.setYRot(this.getYRot()-10f);
            }
            if (Minecraft.getInstance().options.keyRight.isDown()) {
                this.setYRot(this.getYRot()+10f);
            }
        }

        setDeltaMovement(this.getDeltaMovement().add(deltaMovementVector.scale(0.1)));
        super.travel(vector);
    }

}
