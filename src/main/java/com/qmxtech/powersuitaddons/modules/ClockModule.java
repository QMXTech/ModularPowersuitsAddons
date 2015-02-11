package com.qmxtech.powersuitaddons.modules;

import net.machinemuse.api.IModularItem;
import net.machinemuse.api.moduletrigger.IToggleableModule;
import net.machinemuse.powersuits.item.ItemComponent;
import net.machinemuse.powersuits.powermodule.PowerModuleBase;
import net.machinemuse.utils.MuseCommonStrings;
import net.machinemuse.utils.MuseItemUtils;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

import java.util.List;

/**
 * Created by User: Andrew2448
 * 11:12 PM 6/11/13
 */
public class ClockModule extends PowerModuleBase implements IToggleableModule {

    public static final String MODULE_CLOCK = "Clock";
    public static ItemStack clock = new ItemStack(Items.clock);

    public ClockModule(List<IModularItem> validItems) {
        super(validItems);
        addInstallCost(MuseItemUtils.copyAndResize(ItemComponent.controlCircuit, 1));
        addInstallCost(clock);
    }

    @Override
    public String getTextureFile() {
        return null;
    }

    @Override
    public IIcon getIcon(ItemStack item) {
        return clock.getIconIndex();
    }

    @Override
    public String getCategory() {
        return MuseCommonStrings.CATEGORY_SPECIAL;
    }

    @Override
    public String getDataName() {
        return MODULE_CLOCK;
    }

    @Override
    public String getLocalizedName() {
        return StatCollector.translateToLocal("module.clock.name");
    }

    @Override
    public String getDescription() {
        return StatCollector.translateToLocal("module.clock.desc");
    }
}
