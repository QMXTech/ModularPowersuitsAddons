package com.qmxtech.powersuitaddons.modules;

import extracells.api.ECApi;
import extracells.item.ItemWirelessTerminalFluid;
import appeng.api.AEApi;
import appeng.api.networking.IGrid;
import appeng.api.networking.IGridHost;
import appeng.api.networking.IGridNode;
import appeng.api.networking.IMachineSet;
import appeng.api.util.DimensionalCoord;
import cpw.mods.fml.common.registry.GameRegistry;
import net.machinemuse.api.IModularItem;
import net.machinemuse.api.moduletrigger.IPlayerTickModule;
import net.machinemuse.api.moduletrigger.IRightClickModule;
import net.machinemuse.powersuits.item.ItemComponent;
import net.machinemuse.powersuits.powermodule.PowerModuleBase;
import net.machinemuse.utils.ElectricItemUtils;
import net.machinemuse.utils.MuseCommonStrings;
import net.machinemuse.utils.MuseItemUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

/**
 * Created by User: Korynkai
 * 10:03pm - 2014-11-15
 */
 
public class AppEngWirelessFluidModule extends PowerModuleBase implements IRightClickModule {
    public static final String MODULE_APPENG_EC_WIRELESS_FLUID = "AppEng EC Wireless Fluid Terminal";
    private ItemStack wirelessFluidTerminal;


    public AppEngWirelessFluidModule(List<IModularItem> validItems) {
        super(validItems);
        addInstallCost(MuseItemUtils.copyAndResize(ItemComponent.controlCircuit, 1));
        wirelessFluidTerminal = ECApi.instance().items().wirelessFluidTerminal().stack(1);
        addInstallCost(wirelessFluidTerminal);

    }

    @Override
    public String getTextureFile() {
        return "ItemWirelessTerminalFluid"; //?
    }

    @Override
    public String getCategory() {
        return MuseCommonStrings.CATEGORY_TOOL;
    }

    @Override
    public String getDataName() {
        return MODULE_APPENG_EC_WIRELESS_FLUID;
    }

    @Override
    public String getLocalizedName() {
        return StatCollector.translateToLocal("module.appengECWirelessFluid.name");
    }

    @Override
    public String getDescription() {
        return StatCollector.translateToLocal("module.appengECWirelessFluid.desc");
    }

    @Override
    public void onRightClick(EntityPlayer player, World world, ItemStack item) {
        ECApi.instance().openWirelessTerminal(player, player.getHeldItem(), world);
    }

    @Override
    public void onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    }

    @Override
    public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        return false;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int par4) {
    }
}
