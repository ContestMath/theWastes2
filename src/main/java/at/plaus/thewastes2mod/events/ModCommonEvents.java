package at.plaus.thewastes2mod.events;

import at.plaus.thewastes2mod.TheWastes2Mod;
import at.plaus.thewastes2mod.entities.EntityInit;
import at.plaus.thewastes2mod.entities.TestCar;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.swing.plaf.PanelUI;

@Mod.EventBusSubscriber(modid = TheWastes2Mod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)

public class ModCommonEvents {
    @SubscribeEvent
    public static void entityAttributs(EntityAttributeCreationEvent event) {
        event.put(EntityInit.Test.get(), TestCar.getAttribues().build());
    }
}
