package com.qmxtech.powersuitaddons.common;

import cpw.mods.fml.common.event.FMLInterModComms;
import net.machinemuse.numina.general.MuseLogger;
import net.machinemuse.numina.recipe.JSONRecipeList;
import net.machinemuse.powersuits.common.ModCompatability;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.commons.io.FileUtils;
import java.io.*;


public class AddonRecipeManager {
    static private boolean isLoaded;
    
    static {
    	isLoaded = false;
    }
    
    public static void loadOrPutRecipesFromJar(String path) {
    	if (!isLoaded) {
    		try {
    		    String recipeJarPath = "/assets/powersuitaddons/recipes";
    		    
                if (AddonConfig.vanillaRecipesEnabled()) {
                    File vanilla = new File(path, "vanilla.recipes");
                    if (!vanilla.isFile()) {
                        FileUtils.copyURLToFile(AddonRecipeManager.class.getResource( recipeJarPath + "/vanilla.recipes" ), vanilla);
                    }
                    JSONRecipeList.loadRecipesFromFile(vanilla);
                }
                // Universal Electricity/Resonant Induction/Electrodynamics reintroduction postponed until Calclavia gets his act together....
                // if ModCompatability.isBasicComponentsLoaded() {
                //     if ModCompatability.UERecipesEnabled()
                //         File ue = new File(path, "UniversalElectricity.recipes");
                //         if (!ue.isFile()) {
                //             FileUtils.copyURLToFile(getClass().getResource( recipeJarPath + "/UniversalElectricity.recipes" ), ue);
                //         }
                //         JSONRecipeList.loadRecipesFromFile(ue);
                //     }
                // }
                // EnderIO support planned...
                // if ModCompatability.isEnderIOLoaded() {
                //     if ModCompatability.EnderIORecipesEnabled() {
                //         File eio = new File(path, "EnderIO.recipes");
                //         if (!eio.isFile()) {
                //             FileUtils.copyURLToFile(AddonRecipeManager.class.getResource( recipeJarPath + "/EnderIO.recipes"), eio);
                //         }
                //         JSONRecipeList.loadRecipesFromFile(eio);
                //     }
                // }
                // Mekanism support planned...
                // if ModCompatability.isMekanismLoaded() {
                //     if ModCompatability.MekanismRecipesEnabled() {
                //         File mk = new File(path, "Mekanism.recipes");
                //         if (!mk.isFile()) {
                //             FileUtils.copyURLToFile(AddonRecipeManager.class.getResource( recipeJarPath + "/Mekanism.recipes"), mk);
                //         }
                //         JSONRecipeList.loadRecipesFromFile(mk);
                //     }
                // }
                if (ModCompatability.isIndustrialCraftLoaded()) {
    				if (AddonConfig.IC2RecipesEnabled()) {
	        		    File ic2 = new File(path, "IndustrialCraft2.recipes");
	            		if (!ic2.isFile()) {
	                		FileUtils.copyURLToFile(AddonRecipeManager.class.getResource( recipeJarPath + "/IndustrialCraft2.recipes" ), ic2);
	            		}
	            		JSONRecipeList.loadRecipesFromFile(ic2);
    				}
	        		if (ModCompatability.isGregTechLoaded()) {
	        		    if (AddonConfig.GregTechRecipesEnabled()) {
		                    File gt = new File(path, "GregTech.recipes");
		            		if (!gt.isFile()) {
		                		FileUtils.copyURLToFile(AddonRecipeManager.class.getResource( recipeJarPath + "/GregTech.recipes" ), gt);
		            		}
		            		JSONRecipeList.loadRecipesFromFile(gt);
	        		    }
	        		}
        		}
                if (ModCompatability.isThermalExpansionLoaded()) {
                    if (AddonConfig.ThermalExpansionRecipesEnabled()) {
                        File te = new File(path, "ThermalExpansion.recipes");
                        if (!te.isFile()) {
                            FileUtils.copyURLToFile(AddonRecipeManager.class.getResource( recipeJarPath + "/ThermalExpansion.recipes" ), te);
                        }
                        JSONRecipeList.loadRecipesFromFile(te);
                    }
                }
                AddonConfig.getConfig().save();
                isLoaded = true;
            } catch (IOException e) {
            	MuseLogger.logError("Unable to access and/or generate recipes!");
            	MuseLogger.logError("Please check your permissions for the following directory: " + path);
            	e.printStackTrace();
            }
    	}
    }
    
    
    // Also seems like a good basis to base a furnace recipe handler on... Although as such it shouldn't be strictly TE, perhaps do something like this in MPS?
    public static void cheatyLeather() {
        if (AddonConfig.useCheatyLeatherRecipe && ModCompatability.isThermalExpansionLoaded()) {

            NBTTagCompound toSend = new NBTTagCompound();
            toSend.setInteger("energy", 1350);
            toSend.setTag("input", new NBTTagCompound());
            toSend.setTag("output", new NBTTagCompound());

            new ItemStack(Items.rotten_flesh).writeToNBT(toSend.getCompoundTag("input"));
            new ItemStack(Items.leather).writeToNBT(toSend.getCompoundTag("output"));
            FMLInterModComms.sendMessage("ThermalExpansion", "FurnaceRecipe", toSend);
        }
    }
/*
    // THIS IS ONLY LEFT IN AS A REFERENCE FOR OLD UE RECIPES FOR WHEN ELECTRODYNAMICS IS FINISHED
    // DO NOT UNCOMMENT! USE NEW RECIPE FILES INSTEAD!
    public static void addRecipes() {
        ItemStack glass = new ItemStack(Blocks.glass);
        ItemStack lapisBlock = new ItemStack(Blocks.lapis_block);
        ItemStack ironIngot = new ItemStack(Items.iron_ingot);
        ItemStack redstoneBlock = new ItemStack(Blocks.redstone_block);
        ItemStack diamond = new ItemStack(Items.diamond);
        ItemStack goldIngot = new ItemStack(Items.gold_ingot);

        if (ModCompatability.UERecipesEnabled() && ModCompatability.isBasicComponentsLoaded()) {
            String basicCircuit = "basicCircuit";
            String advancedCircuit = "advancedCircuit";
            String eliteCircuit = "eliteCircuit";
            //===========================================================================================================================
            GameRegistry.addRecipe(new ShapedOreRecipe(AddonComponent.solarPanel, true, "GGG", "CLC", "SSS", 'G', glass, 'C', basicCircuit, 'L', lapisBlock, 'S', "plateSteel"));
            GameRegistry.addRecipe(new ShapedOreRecipe(AddonComponent.magnet, "ICI", "SSS", "ICI", 'I', ironIngot, 'C', advancedCircuit, 'S', ItemComponent.solenoid));
            GameRegistry.addRecipe(new ShapedOreRecipe(AddonComponent.computerChip, "CRC", "GDG", 'C', eliteCircuit, 'R', redstoneBlock, 'G', goldIngot, 'D', diamond));
            //===========================================================================================================================
        }
    }*/

}
