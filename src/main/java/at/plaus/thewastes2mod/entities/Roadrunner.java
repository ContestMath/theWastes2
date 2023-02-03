package at.plaus.thewastes2mod.entities;

import at.plaus.thewastes2mod.gui.menu.CarMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;


public class Roadrunner extends AbstractCar implements IAnimatable, MenuProvider {

    private AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public Roadrunner(EntityType<? extends Mob> entity, Level level) {
        super(entity, level);
        this.noCulling = true;
        this.acceleration = 0.015f;
        this.rotSpeed = 1.2f;
        this.weight = 1f;
    }

    @Override
    public double[] getRiderOffset() {
        return new double[]{
                0.89 /2*1.6,
                -2.0,
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
    @Override
    public boolean isPushable() {
        return false;
    }


    @Override
    public void push(double pX, double pY, double pZ) {

    }

    @Override
    public boolean ignoreExplosion() {
        return true;
    }


    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if(player.isShiftKeyDown()&&!player.level.isClientSide)
        {
            System.out.println("Test Gui");
            NetworkHooks.openScreen(((ServerPlayer) player), this);
        }
        return super.mobInteract(player, hand);
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

    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
        }
    };

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player p_39956_) {
        return new CarMenu(pContainerId, pPlayerInventory,this);
    }

    public IItemHandler getItemStackHandler() {
        return this.itemHandler;
    }
}
