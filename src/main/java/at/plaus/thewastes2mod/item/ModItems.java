package at.plaus.thewastes2mod.item;

import at.plaus.thewastes2mod.TheWastes2Mod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TheWastes2Mod.MODID);

    public static final RegistryObject<Item> SCRAP = ITEMS.register("scrap",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.THEWASTES2_Tab)));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
