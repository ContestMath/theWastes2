package at.plaus.thewastes2mod.events;

import at.plaus.thewastes2mod.TheWastes2Mod;
import at.plaus.thewastes2mod.client.models.TestCarModel;
import at.plaus.thewastes2mod.client.renderer.TestCarRenderer;
import at.plaus.thewastes2mod.entities.EntityInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.swing.text.JTextComponent;

@Mod.EventBusSubscriber(modid = TheWastes2Mod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {
   @SubscribeEvent
   public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
       event.registerEntityRenderer(EntityInit.Test.get(), TestCarRenderer::new);
   }

   @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
       event.registerLayerDefinition(TestCarModel.LAYER_LOCATION, TestCarModel::createBodyLayer);
   }
   /*
   @SubscribeEvent
   public static void onKeyRegister(RegisterKeyMappingsEvent event) {
       //event.register(JTextComponent.KeyBinding.);
   }
   @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {

   }

    */
}
