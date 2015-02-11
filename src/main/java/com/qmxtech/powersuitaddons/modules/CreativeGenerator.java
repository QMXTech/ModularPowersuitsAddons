package com.qmxtech.powersuitaddons.modules;


import net.machinemuse.api.IModularItem;
import net.machinemuse.api.ModuleManager;
import net.machinemuse.api.moduletrigger.IPlayerTickModule;
import net.machinemuse.powersuits.common.Config;
import net.machinemuse.powersuits.common.ModCompatability;
import net.machinemuse.powersuits.item.ItemComponent;
import net.machinemuse.powersuits.powermodule.PowerModuleBase;
import net.machinemuse.utils.ElectricItemUtils;
import net.machinemuse.utils.MuseCommonStrings;
import net.machinemuse.utils.MuseHeatUtils;
import net.machinemuse.utils.MuseItemUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

/**
 * Created by User: Korynkai
 * 7:38 PM 2015-01-04
 */
public class CreativeGenerator extends PowerModuleBase implements IPlayerTickModule {
        public static final String MODULE_CREATIVE_GENERATOR = "Creative Generator";
        public static final String CREATIVE_ENERGY_GENERATION = "Energy Generation";

        // REIMP
        public CreativeGenerator(List<IModularItem> validItems) {
                super(validItems);
                addInstallCost(MuseItemUtils.copyAndResize(ItemComponent.controlCircuit, 2));
                addInstallCost(nether_star);
        }

        @Override
        public String getTextureFile() {
                return "bluestar";
        }

        @Override
        public String getCategory() {
                return MuseCommonStrings.CATEGORY_ENERGY;
        }

        @Override
        public String getDataName() {
                return MODULE_CREATIVE_GENERATOR;
        }

        @Override
        public String getLocalizedName() {
                return StatCollector.translateToLocal("module.creativeGenerator.name");
        }

        @Override
        public String getDescription() {
                return StatCollector.translateToLocal("module.creativeGenerator.desc");
        }

        @Override
        public void onPlayerTickActive(EntityPlayer player, ItemStack item) {
                if (player.capabilities.isCreativeMode) {
                        if (player.worldObj.getTotalWorldTime() % 20 == 0) {
                                if (ElectricItemUtils.getPlayerEnergy(player) < ElectricItemUtils.getMaxEnergy(player)) {
                                        ElectricItemUtils.givePlayerEnergy(player, ElectricItemUtils.getMaxEnergy(player) - ElectricItemUtils.getPlayerEnergy(player));
                                }
                        }
                }
        }

        @Override
        public void onPlayerTickInactive(EntityPlayer player, ItemStack item) {
        }

        @Override
        public boolean isCreativeOnly() {
                return true;
        }
}
