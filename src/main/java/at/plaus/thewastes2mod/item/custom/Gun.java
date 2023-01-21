package at.plaus.thewastes2mod.item.custom;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;

import java.util.function.Predicate;

public class Gun extends ProjectileWeaponItem {
    public Gun(Properties properties) {
        super(properties);
    }




    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (p_43017_) -> {
            return p_43017_.is(TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("scrap")));
        };
    }

    @Override
    public int getDefaultProjectileRange() {
        return 0;
    }
}
