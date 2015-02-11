package com.qmxtech.powersuitaddons.modules;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.Optional;
import net.machinemuse.api.IModularItem;
import net.machinemuse.api.moduletrigger.IRightClickModule;
import net.machinemuse.api.moduletrigger.IPlayerTickModule;
import net.machinemuse.powersuits.item.ItemComponent;
import net.machinemuse.powersuits.powermodule.PowerModuleBase;
import net.machinemuse.powersuits.common.ModCompatability;
import net.machinemuse.utils.MuseCommonStrings;
import net.machinemuse.utils.MuseItemUtils;
import com.qmxtech.powersuitaddons.common.AddonUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraftforge.client.MinecraftForgeClient;
//import mrtjp.projectred.transmission.bundledwires.TWireCommons;

import java.util.List;

/**
 * Created by User: Korynkai
 * 6:30 PM 2014-11-17
 *
 * TODO: Fix ProjectRed (may require PR to ProjectRed)
 */

public class OmniProbeModule extends PowerModuleBase implements IRightClickModule, IPlayerTickModule {
public static final String MODULE_OMNIPROBE = "Prototype OmniProbe";
private ItemStack conduitProbe;
private ItemStack rednetMeter;
private ItemStack cpmPSD;
private ItemStack rcMeter;
private ItemStack prDebugger;
//    private ItemStack teMultimeter;

public OmniProbeModule(List<IModularItem> validItems) {
        super(validItems);
        addInstallCost(MuseItemUtils.copyAndResize(ItemComponent.controlCircuit, 4));
        ItemStack tHighest = GameRegistry.findItemStack("minecraft", "comparator", 1);

        /* Project Red seems to do something odd with its debugger. Will have to look into this. */
        // if (Loader.isModLoaded("ProjRed|Core")) {
        //         prDebugger = GameRegistry.findItemStack("ProjRed|Core", "projectred.core.wiredebugger", 1);
        //         tHighest = prDebugger;
        // }

        if (Loader.isModLoaded("MineFactoryReloaded")) {
                rednetMeter = GameRegistry.findItemStack("MineFactoryReloaded", "rednet.meter", 1);
                tHighest = rednetMeter;
        }

        if (Loader.isModLoaded("Railcraft")) {
                rcMeter = GameRegistry.findItemStack("Railcraft", "tool.electric.meter", 1);
                tHighest = rcMeter;
        }

        /* Will be added when ThermalExpansion's new conduit mod is released */
        // if (Loader.isModLoaded("ThermalExpansion")) {
        //     teMultimeter = GameRegistry.findItemStack("ThermalExpansion", "multimeter", 1);
        //     tHighest = teMultimeter
        // }

        if (Loader.isModLoaded("EnderIO")) {
                conduitProbe = GameRegistry.findItemStack("EnderIO", "itemConduitProbe", 1);
                tHighest = conduitProbe;
        }

        addInstallCost(tHighest);
}

@Override
public String getTextureFile() {
        return "omniprobe";
}

@Override
public String getCategory() {
        return MuseCommonStrings.CATEGORY_TOOL;
}

@Override
public String getDataName() {
        return MODULE_OMNIPROBE;
}

@Override
public String getLocalizedName() {
        return StatCollector.translateToLocal("module.omniProbe.name");
}

@Override
public String getDescription() {
        return StatCollector.translateToLocal("module.omniProbe.desc");
}

@Override
public void onRightClick(EntityPlayer player, World world, ItemStack item) {
}

@Override
public void onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
}

@Override
public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        int block = Block.getIdFromBlock(world.getBlock(x, y, z));

        /* Project Red seems to do something odd with its debugger. Will have to look into this. */
        // if (Loader.isModLoaded("ProjRed|Transmission")) {
	      //    if ( world.getBlock(x, y, z) instanceof TWireCommons) {
        //
	      //    }
        // }

        if (Loader.isModLoaded("MineFactoryReloaded")) {
                if (block == Block.getIdFromBlock(GameRegistry.findBlock("MineFactoryReloaded", "cable.redstone")))
                        return rednetMeter.getItem().onItemUseFirst(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
        }

        if (Loader.isModLoaded("Railcraft")) {
                if ((block == Block.getIdFromBlock(GameRegistry.findBlock("Railcraft", "tile.railcraft.machine.alpha"))) ||
                    (block == Block.getIdFromBlock(GameRegistry.findBlock("Railcraft", "tile.railcraft.track"))) ||
                    (block == Block.getIdFromBlock(GameRegistry.findBlock("Railcraft", "tile.railcraft.machine.epsilon"))) ||
                    (block == Block.getIdFromBlock(GameRegistry.findBlock("Railcraft", "tile.railcraft.machine.delta")))) {
                        return rcMeter.getItem().onItemUseFirst(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
                }
        }

        //  if (ModCompatability.isThermalExpansionLoaded()) {
        //     if (block == Block.getIdFromBlock(GameRegistry.findBlock("ThermalExpansion", .... ))) {
        //    return multiMeter.getItem().onItemUseFirst(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
        //     }
        //  }

        if (Loader.isModLoaded("EnderIO")) {
                if (block == Block.getIdFromBlock(GameRegistry.findBlock("EnderIO", "blockConduitBundle"))) {
                        return conduitProbe.getItem().onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
                }
        }

        return false;
}
@Override
public void onPlayerTickActive(EntityPlayer player, ItemStack item) {
        if (!AddonUtils.getEIOFacadeTransparency(item)) {
                AddonUtils.setEIONoCompete(item, MODULE_OMNIPROBE);
                AddonUtils.setEIOFacadeTransparency(item, true);
        }
}

@Override
public void onPlayerTickInactive(EntityPlayer player, ItemStack item) {

        if ((AddonUtils.getEIONoCompete(item) != null) && (!AddonUtils.getEIONoCompete(item).isEmpty())) {
                if (AddonUtils.getEIONoCompete(item).equals(MODULE_OMNIPROBE)) {
                        AddonUtils.setEIONoCompete(item, "");
                        if (AddonUtils.getEIOFacadeTransparency(item)) {
                                AddonUtils.setEIOFacadeTransparency(item, false);

                        }
                }
        } else {
                if (AddonUtils.getEIOFacadeTransparency(item)) {
                        AddonUtils.setEIOFacadeTransparency(item, false);

                }
        }
}

public float minF(float a, float b) {
        return a < b ? a : b;
}

@Override
public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int par4) {
}
}
