package com.qmxtech.powersuitaddons.modules;

import net.machinemuse.api.IModularItem;
import net.machinemuse.api.ModuleManager;
import net.machinemuse.api.moduletrigger.IPlayerTickModule;
import net.machinemuse.api.moduletrigger.IToggleableModule;
import net.machinemuse.powersuits.item.ItemComponent;
import net.machinemuse.powersuits.powermodule.PowerModuleBase;
import net.machinemuse.utils.ElectricItemUtils;
import net.machinemuse.utils.MuseCommonStrings;
import net.machinemuse.utils.MuseItemUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

/**
 * Created by Eximius88 on 1/17/14.
 */
public class MechanicalAssistance extends PowerModuleBase implements IPlayerTickModule, IToggleableModule {
    public static final String MODULE_MECH_ASSISTANCE = "Mechanical Assistance";
    public static final String ASSISTANCE = "Robotic Assistance";
    public static final String POWER_USAGE = "Power Usage";


    public MechanicalAssistance(List<IModularItem> validItems) {

        super(validItems);

        addInstallCost(MuseItemUtils.copyAndResize(ItemComponent.artificialMuscle, 4));
        addTradeoffProperty(ASSISTANCE, POWER_USAGE, 500, " Joules/Tick");
        addTradeoffProperty(ASSISTANCE, MuseCommonStrings.WEIGHT, -10000);
        addPropertyLocalString(ASSISTANCE, StatCollector.translateToLocal("module.mechAssistance.assistance"));
    }

    @Override
    public String getTextureFile() {
        return "mechassistance";
    }

    @Override
    public String getCategory() {
        return MuseCommonStrings.CATEGORY_ARMOR;
    }

    @Override
    public String getDataName() {
        return MODULE_MECH_ASSISTANCE;
    }

    @Override
    public String getLocalizedName() {
        return StatCollector.translateToLocal("module.mechAssistance.name");
    }

    @Override
    public String getDescription() {
        return StatCollector.translateToLocal("module.mechAssistance.desc");
    }

    @Override
    public void onPlayerTickActive(EntityPlayer player, ItemStack item) {
        ElectricItemUtils.drainPlayerEnergy(player, ModuleManager.computeModularProperty(item, POWER_USAGE));

    }

    @Override
    public void onPlayerTickInactive(EntityPlayer player, ItemStack item) {

    }
}
