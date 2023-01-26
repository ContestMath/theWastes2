package at.plaus.thewastes2mod.item.custom.cars;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.common.ForgeSpawnEggItem;

import java.util.function.Supplier;

public class WarRigItem extends ForgeSpawnEggItem {
    public WarRigItem(Supplier<? extends EntityType<? extends Mob>> type, int backgroundColor, int highlightColor, Properties props) {
        super(type, backgroundColor, highlightColor, props);
    }

}
