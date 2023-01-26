package at.plaus.thewastes2mod.item;

import at.plaus.thewastes2mod.TheWastes2Mod;
import at.plaus.thewastes2mod.entities.EntityInit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TheWastes2Mod.MODID);

    public static final RegistryObject<Item> SCRAP = ITEMS.register("scrap",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.THEWASTES2_Tab)));

    public static final RegistryObject<Item> SCRAP_SPEAR = ITEMS.register("scrap_spear",
            () -> new SwordItem(
                    Tiers.IRON,
                    3,
                    -2.9f,
                    new Item.Properties().tab(ModCreativeModeTab.THEWASTES2_Tab).durability(250)));

    public static final RegistryObject<Item> TEST_CAR = ITEMS.register("test_car",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.THEWASTES2_Tab)));

    public static final RegistryObject<Item> ROADRUNNER = ITEMS.register("roadrunner",
            () -> new ForgeSpawnEggItem(
                    EntityInit.ROADRUNNER,
                    0,
                    0,
                    new Item.Properties().tab(ModCreativeModeTab.THEWASTES2_Tab).stacksTo(1)));

    public static final RegistryObject<ForgeSpawnEggItem> WAR_RIG = ITEMS.register("war_rig",
            () -> new ForgeSpawnEggItem(
                    EntityInit.WAR_RIG,
                    0,
                    0,
                    new Item.Properties().tab(ModCreativeModeTab.THEWASTES2_Tab).stacksTo(1)));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
