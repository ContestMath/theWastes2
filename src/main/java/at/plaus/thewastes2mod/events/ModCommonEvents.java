package at.plaus.thewastes2mod.events;

import at.plaus.thewastes2mod.TheWastes2Mod;
import at.plaus.thewastes2mod.entities.EntityInit;
import at.plaus.thewastes2mod.entities.Roadrunner;
import at.plaus.thewastes2mod.entities.TestCar;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = TheWastes2Mod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)

public class ModCommonEvents {
    @SubscribeEvent
    public static void entityAttributs(EntityAttributeCreationEvent event) {
        event.put(EntityInit.Test.get(), TestCar.getAttribues().build());
        event.put(EntityInit.ROADRUNNER.get(), Roadrunner.getAttribues().build());
        event.put(EntityInit.WAR_RIG.get(), Roadrunner.getAttribues().build());
    }
}
