package com.qmxtech.powersuitaddons.modules;

import com.qmxtech.powersuitaddons.common.AddonComponent;
import net.machinemuse.api.IModularItem;
import net.machinemuse.api.ModuleManager;
import net.machinemuse.api.moduletrigger.IPlayerTickModule;
import net.machinemuse.api.moduletrigger.IToggleableModule;
import net.machinemuse.powersuits.item.ItemComponent;
import net.machinemuse.powersuits.powermodule.PowerModuleBase;
import net.machinemuse.utils.ElectricItemUtils;
import net.machinemuse.utils.MuseCommonStrings;
import net.machinemuse.utils.MuseHeatUtils;
import net.machinemuse.utils.MuseItemUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

/**
 * Created by Eximius88 on 1/17/14.
 */
public class NitrogenCoolingSystem extends PowerModuleBase implements IPlayerTickModule, IToggleableModule {
    public static final String MODULE_NITROGEN_COOLING_SYSTEM = "Liquid Nitrogen Cooling System";
    public static final String COOLING_BONUS = "Cooling Bonus";
    public static final String ENERGY = "Energy Consumption";

    public NitrogenCoolingSystem(List<IModularItem> validItems) {
        super(validItems);
        //addInstallCost(new ItemStack(Item.netherStar, 1));
        addInstallCost(MuseItemUtils.copyAndResize(AddonComponent.liquidNitrogen, 1));
        addInstallCost(MuseItemUtils.copyAndResize(AddonComponent.rubberHose, 2));
        addInstallCost(MuseItemUtils.copyAndResize(ItemComponent.controlCircuit, 1));
        addInstallCost(MuseItemUtils.copyAndResize(AddonComponent.computerChip, 2));
        addTradeoffProperty("Power", COOLING_BONUS, 7, "%");
        addTradeoffProperty("Power", ENERGY, 16, "J/t");
    }


    @Override
    public void onPlayerTickActive(EntityPlayer player, ItemStack item) {
        double heatBefore = MuseHeatUtils.getPlayerHeat(player);
        MuseHeatUtils.coolPlayer(player, 0.210 * ModuleManager.computeModularProperty(item, COOLING_BONUS));
        double cooling = heatBefore - MuseHeatUtils.getPlayerHeat(player);
        ElectricItemUtils.drainPlayerEnergy(player, cooling * ModuleManager.computeModularProperty(item, ENERGY));
    }

    @Override
    public void onPlayerTickInactive(EntityPlayer player, ItemStack item) {

    }

    @Override
    public String getTextureFile() {
        return "coolingsystem";
    }

    @Override
    public String getCategory() {
        return MuseCommonStrings.CATEGORY_ENVIRONMENTAL;
    }

    @Override
    public String getDataName() {
        return MODULE_NITROGEN_COOLING_SYSTEM;
    }

    @Override
    public String getLocalizedName() {
        return StatCollector.translateToLocal("module.nitrogenCoolingSystem.name");
    }

    @Override
    public String getDescription() {
        return StatCollector.translateToLocal("module.nitrogenCoolingSystem.desc");
    }
}
