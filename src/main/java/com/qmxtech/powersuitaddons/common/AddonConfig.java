package com.qmxtech.powersuitaddons.common;

import com.qmxtech.powersuitaddons.modules.*;
import cpw.mods.fml.common.Loader;
import net.machinemuse.api.IModularItem;
import net.machinemuse.api.IPowerModule;
import net.machinemuse.api.ModuleManager;
import net.machinemuse.powersuits.common.MPSItems;
import net.machinemuse.powersuits.common.ModCompatability;
import net.minecraft.block.Block;


import net.minecraft.init.Blocks;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class AddonConfig{


    public static String category = "Modular Powersuits Addons";

    public static Block torch;

    public static File configFolder;
    private static Configuration config;

    public static boolean useAdvancedOreScannerMessage;
    public static boolean useOldAutoFeeder;
    public static boolean useCheatyLeatherRecipe;
    public static boolean useHUDStuff;
    public static boolean useDebugMode;
    public static boolean use24hClock;
    public static double appengMultiplier;


    public static void init(Configuration config) {
        AddonConfig.config = config;
        config.load();


        config.save();
    }

    public static void addModule(IPowerModule module) {
        ModuleManager.addModule(module);
    }


    public static void loadPowerModules() {
        List<IModularItem> ARMORONLY = Arrays.asList((IModularItem) MPSItems.powerArmorHead(), MPSItems.powerArmorTorso(), MPSItems.powerArmorLegs(), MPSItems.powerArmorFeet());
        List<IModularItem> ALLITEMS = Arrays.asList((IModularItem) MPSItems.powerArmorHead(), MPSItems.powerArmorTorso(), MPSItems.powerArmorLegs(), MPSItems.powerArmorFeet(), MPSItems.powerTool());
        List<IModularItem> HEADONLY = Arrays.asList((IModularItem) MPSItems.powerArmorHead());
        List<IModularItem> TORSOONLY = Arrays.asList((IModularItem) MPSItems.powerArmorTorso());
        List<IModularItem> LEGSONLY = Arrays.asList((IModularItem) MPSItems.powerArmorLegs());
        List<IModularItem> FEETONLY = Arrays.asList((IModularItem) MPSItems.powerArmorFeet());
        List<IModularItem> TOOLONLY = Arrays.asList((IModularItem) MPSItems.powerTool());

        addModule(new InPlaceAssemblerModule(TOOLONLY));
        addModule(new KineticGeneratorModule(LEGSONLY));
        addModule(new SolarGeneratorModule(HEADONLY));
        addModule(new AutoFeederModule(HEADONLY));
        addModule(new MagnetModule(TORSOONLY));
        addModule(new OreScannerModule(TOOLONLY));
        addModule(new LeafBlowerModule(TOOLONLY));
        addModule(new ThermalGeneratorModule(TORSOONLY));
        addModule(new MobRepulsorModule(TORSOONLY));
        addModule(new FlintAndSteelModule(TOOLONLY));
        addModule(new ClockModule(HEADONLY));
        addModule(new CompassModule(HEADONLY));
        addModule(new LightningModule(TOOLONLY));
        addModule(new WaterTankModule(TORSOONLY));
        addModule(new DimensionalRiftModule(TOOLONLY));
        addModule(new AdvancedSolarGenerator(HEADONLY));
        addModule(new NitrogenCoolingSystem(TORSOONLY));
        addModule(new MechanicalAssistance(TORSOONLY));
        addModule(new CreativeGenerator(TORSOONLY));
        //addModule(new CoalGenerator(TORSOONLY));


        if (Loader.isModLoaded("appliedenergistics2")) {
            addModule(new AppEngWirelessModule(TOOLONLY));
            if (Loader.isModLoaded("extracells")) {
              addModule(new AppEngWirelessFluidModule(TOOLONLY));
            }
            System.out.println("MPSA: Loading AE Modules :MPSA");
        }
	      if (Loader.isModLoaded("EnderIO") || Loader.isModLoaded("MineFactoryReloaded") || Loader.isModLoaded("Railcraft")) {
	          addModule(new OmniProbeModule(TOOLONLY));
	      }

	      if (Loader.isModLoaded("CompactMachines")) {
	          addModule(new PersonalShrinkingModule(TOOLONLY));
	      }

        //if (Loader.isModLoaded("OpenComputers")) {
	      //    addModule(new OCTerminalModule(TOOLONLY));
	      //}

    }

    public static Configuration getConfig() {
        return config;
    }

    public static void setConfigFolderBase(File folder) {
        configFolder = folder;
    }

    public static void initItems() {
    }
    
    public static boolean vanillaRecipesEnabled() {
    //    boolean defaultval = ((!isBasicComponentsLoaded()) && (!isIndustrialCraftLoaded()) && (!isThermalExpansionLoaded()));
        boolean defaultval = ((! ModCompatability.isThermalExpansionLoaded()) && (! ModCompatability.isIndustrialCraftLoaded()));
        return getConfig().get("Recipes", "Vanilla Recipes", defaultval).getBoolean(defaultval);
    }
    
    public static boolean IC2RecipesEnabled() {
        boolean defaultval = (ModCompatability.isIndustrialCraftLoaded() && (! ModCompatability.isGregTechLoaded()) && (! ModCompatability.isThermalExpansionLoaded()));
        return getConfig().get("Recipes", "IndustrialCraft Recipes", defaultval).getBoolean(defaultval);
    }
    
    public static boolean GregTechRecipesEnabled() {
            boolean defaultval = ModCompatability.isGregTechLoaded();
            return getConfig().get("Recipes", "Gregtech Recipes", defaultval).getBoolean(defaultval);
    }
    
    public static boolean ThermalExpansionRecipesEnabled() {
            boolean defaultval = (ModCompatability.isThermalExpansionLoaded() && (! ModCompatability.isGregTechLoaded()));
            return getConfig().get("Recipes", "Thermal Expansion Recipes", defaultval).getBoolean(defaultval);
    }
    
    // public static boolean EnderIORecipesEnabled() {
    //         boolean defaultval = ModCompatibility.isEnderIOLoaded();
    //         return config.getConfig().get("Recipes", "EnderIO Recipes", defaultval).getBoolean(defaultval);
    // }
    
    // public static boolean MekanismRecipesEnabled() {
    //         boolean defaultval = ModCompatibility.isMekanismLoaded();
    //         return config.getConfig().get("Recipes", "Mekanism Recipes", defaultval).getBoolean(defaultval);
    // }

    public static void loadOptions() {
        useAdvancedOreScannerMessage = getConfig().get(Configuration.CATEGORY_GENERAL, "Use Detailed Ore Scanner Message", true).getBoolean(true);
        useOldAutoFeeder = getConfig().get(Configuration.CATEGORY_GENERAL, "Use Old Auto Feeder Method", false).getBoolean(false);
        useCheatyLeatherRecipe = getConfig().get("Recipes", "Use Cheaty Leather Recipe (Requires Thermal Expansion)", true).getBoolean(true);
        useHUDStuff = getConfig().get(Configuration.CATEGORY_GENERAL, "Use HUD for certain modules (Auto Feeder, Compass, Clock, etc.)", true).getBoolean(true);
        useDebugMode = getConfig().get("Debug", "Use Debug mode. WARNING: WILL PROBABLY SPAM YOUR CONSOLE", false).getBoolean(false);
        use24hClock = getConfig().get(Configuration.CATEGORY_GENERAL, "Use a 24h clock instead of 12h", false).getBoolean(false);
        if (Loader.isModLoaded("appliedenergistics2"))
            appengMultiplier = getConfig().get("Energy", "Energy per AE", 5.0).getDouble(5.0);
    }


}
