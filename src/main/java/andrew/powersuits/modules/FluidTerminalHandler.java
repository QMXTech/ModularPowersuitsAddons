package andrew.powersuits.modules;

import java.util.HashMap;
import java.util.Set;

import extracells.api.ECApi;
import extracells.api.IWirelessFluidTermHandler;

import andrew.powersuits.common.AddonConfig;
import appeng.api.AEApi;
import appeng.api.config.AccessRestriction;
import appeng.api.config.Settings;
import appeng.api.config.SortDir;
import appeng.api.config.SortOrder;
import appeng.api.config.ViewItems;
import appeng.api.config.StorageFilter;
import appeng.api.config.LevelEmitterMode;
import appeng.api.util.IConfigManager;
import net.machinemuse.api.electricity.MuseElectricItem;
import net.machinemuse.utils.ElectricItemUtils;
import cpw.mods.fml.common.Loader;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 *  * Created by User: Korynkai
 *   * 10:03pm - 2014-11-15
 */



public class FluidTerminalHandler implements IWirelessFluidTermHandler {

@Override
public boolean canHandle(ItemStack is) {
        if(is == null)
                return false;
        if(is.getUnlocalizedName() == null)
                return false;
        if(is.getUnlocalizedName().equals("item.powerFist"))
                return true;
        return false;
}

@Override
public boolean usePower(EntityPlayer entityPlayer, double v, ItemStack itemStack) {
        boolean ret = false;
        if (( v * AddonConfig.appengMultiplier ) < ( ElectricItemUtils.getPlayerEnergy( entityPlayer ) * AddonConfig.appengMultiplier ))
        {
                ElectricItemUtils.drainPlayerEnergy(entityPlayer, ( v * AddonConfig.appengMultiplier ) );
                ret = true;
        }

        return ret;
}

@Override
public boolean hasPower(EntityPlayer entityPlayer, double v, ItemStack itemStack) {
        return (( v * AddonConfig.appengMultiplier ) < ( ElectricItemUtils.getPlayerEnergy(entityPlayer) * AddonConfig.appengMultiplier ));
}

@Override
public boolean isItemNormalWirelessTermToo(ItemStack stack) {
  return false;
}

@Override
public String getEncryptionKey(ItemStack item)
{
        if (item == null)
                return null;

        NBTTagCompound tag = openNbtData(item);
        if (tag != null)
                return tag.getString("encKey");

        return null;
}

@Override
public void setEncryptionKey(ItemStack item, String encKey, String name)
{
        if (item == null)
                return;

        NBTTagCompound tag = openNbtData(item);
        if (tag != null)
        {
                tag.setString("encKey", encKey);
        }

}
public static void registerHandler() {
        if (Loader.isModLoaded("extracells")) {
                ECApi.instance().registryWirelessFluidTermHandler(new FluidTerminalHandler());
                System.out.println("MPSA: Registering AE Fluid Terminal Handler :MPSA");
        }

}

public static NBTTagCompound openNbtData(ItemStack item) {
        NBTTagCompound compound = item.getTagCompound();
        if (compound == null) {
                item.setTagCompound(compound = new NBTTagCompound());
        }
        return compound;
}
}
