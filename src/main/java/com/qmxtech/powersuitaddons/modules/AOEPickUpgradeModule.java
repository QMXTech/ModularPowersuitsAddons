package com.qmxtech.powersuitaddons.modules;

import com.qmxtech.powersuitaddons.common.AddonLogger;
import net.machinemuse.api.IModularItem;
import net.machinemuse.api.ModuleManager;
import net.machinemuse.api.moduletrigger.IBlockBreakingModule;
import net.machinemuse.api.moduletrigger.IToggleableModule;
import net.machinemuse.powersuits.item.ItemComponent;
import net.machinemuse.powersuits.powermodule.PowerModuleBase;
import net.machinemuse.powersuits.powermodule.tool.PickaxeModule;
import net.machinemuse.utils.ElectricItemUtils;
import net.machinemuse.utils.MuseCommonStrings;
import net.machinemuse.utils.MuseItemUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eximius88 on 1/29/14.
 */
// Would be nice to integrate, but this isn't even pulled in right now, so... comments...
// public class AOEPickUpgradeModule extends PowerModuleBase implements IBlockBreakingModule, IToggleableModule {
//     public static final String MODULE_AOE_PICK_UPGRADE = "Diamond Drill Upgrade";
//     //public static final ItemStack ironPickaxe = new ItemStack(Item.pickaxeIron);
//     public static final String ENERGY_CONSUMPTION = "Energy Consumption";


//     public AOEPickUpgradeModule(List<IModularItem> validItems) {
//         super(validItems);
//         addInstallCost(MuseItemUtils.copyAndResize(ItemComponent.solenoid, 1));
//         //addInstallCost(new ItemStack(Item.diamond, 3));
//         addBaseProperty(ENERGY_CONSUMPTION, 5, "J");

//     }

//     @Override
//     public String getTextureFile() {
//         return "diamondupgrade1";
//     }

//     @Override
//     public String getCategory() {
//         return MuseCommonStrings.CATEGORY_SPECIAL;
//     }

//     @Override
//     public String getDataName() {
//         return MODULE_AOE_PICK_UPGRADE;
//     }

//     @Override
//     public String getLocalizedName() {
//         return StatCollector.translateToLocal("module.aoePickUpgrade.name");
//     }

//     @Override
//     public String getDescription() {
//         return "An updrade that will allow the pickaxe module to mine a 3x3 area of blocks";
//     }


//     @Override
//     public boolean canHarvestBlock(ItemStack itemStack, Block block, int i, EntityPlayer entityPlayer) {
//         return false;
//     }

//     @Override
//     public boolean onBlockDestroyed(ItemStack itemStack, World world, Block block, int i, int i2, int i3, EntityPlayer entityPlayer) {
//         return false;
//     }

//     @Override
//     public void handleBreakSpeed(PlayerEvent.BreakSpeed breakSpeed) {

//     }
// }