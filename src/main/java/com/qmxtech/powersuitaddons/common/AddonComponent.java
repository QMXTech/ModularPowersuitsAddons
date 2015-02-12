package com.qmxtech.powersuitaddons.common;

import net.machinemuse.powersuits.common.MPSItems;
import net.machinemuse.utils.MuseCommonStrings;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class AddonComponent {
    public static ItemStack magnet;
    public static ItemStack solarPanel;
    public static ItemStack computerChip;
    public static ItemStack liquidNitrogen;
    public static ItemStack rubberHose;

    public static void populate() {
        if (MPSItems.components() != null) {
            solarPanel = MPSItems.components().addComponent("componentSolarPanel", StatCollector.translateToLocal("item.powerArmorComponent.componentSolarPanel.desc"), "solarpanel");
            magnet = MPSItems.components().addComponent("componentMagnet", StatCollector.translateToLocal("item.powerArmorComponent.componentMagnet.desc"), "magnetb");
            computerChip = MPSItems.components().addComponent("componentComputerChip", StatCollector.translateToLocal("item.powerArmorComponent.componentComputerChip.desc"), "computerchip");
            liquidNitrogen = MPSItems.components().addComponent("componentLiquidNitrogen", StatCollector.translateToLocal("item.powerArmorComponent.componentLiquidNitrogen.desc"), "liquidnitrogen");
            rubberHose = MPSItems.components().addComponent("componentRubberHose", StatCollector.translateToLocal("item.powerArmorComponent.componentRubberHose.desc"), "rubberhose");
            
            // Initialize common module strings (these can't really go anywhere else...)
            MuseCommonStrings.addPropertyLocalString("Daytime Energy Generation", StatCollector.translateToLocal("module.common.energy.day"));
            MuseCommonStrings.addPropertyLocalString("Nighttime Energy Generation", StatCollector.translateToLocal("module.common.energy.night"));
            MuseCommonStrings.addPropertyLocalString("Daytime Heat Generation", StatCollector.translateToLocal("module.common.heat.day"));
            MuseCommonStrings.addPropertyLocalString("Nighttime Heat Generation", StatCollector.translateToLocal("module.common.heat.night"));
            MuseCommonStrings.addPropertyLocalString("Activation Percent", StatCollector.translateToLocal("module.common.activation.percent"));
            MuseCommonStrings.addPropertyLocalString("Heat Activation Percent", StatCollector.translateToLocal("module.common.heat.activationPercent"));
            MuseCommonStrings.addPropertyLocalString("Power Usage", StatCollector.translateToLocal("module.common.power.usage"));
            MuseCommonStrings.addPropertyLocalString("Energy Generated", StatCollector.translateToLocal("module.common.energy.generated"));
            MuseCommonStrings.addPropertyLocalString("Energy Generation", StatCollector.translateToLocal("module.common.energy.generation"));
            MuseCommonStrings.addPropertyLocalString("Energy Consumption", StatCollector.translateToLocal("module.common.energy.consumption"));
            MuseCommonStrings.addPropertyLocalString("Energy Per Block", StatCollector.translateToLocal("module.common.energy.perBlock"));
            MuseCommonStrings.addPropertyLocalString("Energy Per 5 Blocks", StatCollector.translateToLocal("module.common.energy.perFive"));
            MuseCommonStrings.addPropertyLocalString("Heat Generation", StatCollector.translateToLocal("module.common.heat.generation"));
            MuseCommonStrings.addPropertyLocalString("Heat Emission", StatCollector.translateToLocal("module.common.heat.emission"));
            MuseCommonStrings.addPropertyLocalString("Efficiency", StatCollector.translateToLocal("module.common.efficiency"));
            MuseCommonStrings.addPropertyLocalString("X Radius", StatCollector.translateToLocal("module.common.radius.x"));
            MuseCommonStrings.addPropertyLocalString("Y Radius", StatCollector.translateToLocal("module.common.radius.y"));
            MuseCommonStrings.addPropertyLocalString("Z Radius", StatCollector.translateToLocal("module.common.radius.z"));
            
        } else {
            AddonLogger.logError("MPS components were not initialized, MPSA componenets will not be activated.");
        }
    }

}
