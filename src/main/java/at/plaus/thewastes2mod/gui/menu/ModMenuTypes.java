package at.plaus.thewastes2mod.gui.menu;

import at.plaus.thewastes2mod.TheWastes2Mod;
import at.plaus.thewastes2mod.gui.screen.CarScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, TheWastes2Mod.MODID);


    public static final RegistryObject<MenuType<CarMenu>> CAR_MENU =
            registerMenuType(CarMenu::new, "car_menu");


    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                 String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }

    public static void menuSetup(final FMLCommonSetupEvent event)
    {
        MenuScreens.register(ModMenuTypes.CAR_MENU.get(), CarScreen::new);

    }


}
