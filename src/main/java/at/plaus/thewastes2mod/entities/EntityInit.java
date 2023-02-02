package at.plaus.thewastes2mod.entities;

import at.plaus.thewastes2mod.TheWastes2Mod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TheWastes2Mod.MODID);

    public static final RegistryObject<EntityType<TestCar>> Test = ENTITIES.register("test", () -> EntityType.Builder.of(TestCar::new, MobCategory.CREATURE).build(TheWastes2Mod.MODID + "testcar"));

    public static final RegistryObject<EntityType<Roadrunner>> ROADRUNNER = ENTITIES.register("roadrunner", () -> EntityType.Builder.of(Roadrunner::new, MobCategory.CREATURE)
            .sized(3,3)
            .build(TheWastes2Mod.MODID + "roadrunner"));

    public static final RegistryObject<EntityType<WarRig>> WAR_RIG =
            ENTITIES.register("war_rig",
                    () -> EntityType.Builder.of(WarRig::new, MobCategory.CREATURE).build(TheWastes2Mod.MODID + "war_rig")
            );
}
