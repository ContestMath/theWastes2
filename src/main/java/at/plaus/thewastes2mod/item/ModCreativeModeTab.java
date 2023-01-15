package at.plaus.thewastes2mod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab THEWASTES2_Tab = new CreativeModeTab("thewastes2tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SCRAP.get());
        }
    };
}
