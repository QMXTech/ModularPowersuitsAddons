package com.qmxtech.powersuitaddons.common;


import com.qmxtech.powersuitaddons.client.ClientProxy;
import com.qmxtech.powersuitaddons.modules.TerminalHandler;
import com.qmxtech.powersuitaddons.network.MPSAPacketHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.init.Localization;
import net.machinemuse.numina.recipe.JSONRecipeList;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

@Mod(modid = "@ID@", name = "@NAME@", version = "@VERSION@", dependencies = "@DEPENDENCIES@")
public class ModularPowersuitsAddons {
    
    @SidedProxy(clientSide = "com.qmxtech.powersuitaddons.client.ClientProxy", serverSide = "com.qmxtech.powersuitaddons.common.CommonProxy")
    public static CommonProxy proxy;
    
    @Instance("@ID@")
    public static ModularPowersuitsAddons INSTANCE;
    
    public static final String modid = "@ID@";
    public static final String CHANNEL = "@CHANNEL@";
    public static FMLEventChannel packetHandler;
    
    
    public static GuiHandler guiHandler = new GuiHandler();
    
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        packetHandler = NetworkRegistry.INSTANCE.newEventDrivenChannel(CHANNEL);
        INSTANCE = this;
        File newConfig = new File(event.getModConfigurationDirectory() + "@CONFIG_PATH@/@DISPLAY_NAME@.cfg");
        AddonConfig.init(new Configuration(newConfig));
        AddonConfig.setConfigFolderBase(event.getModConfigurationDirectory());
        AddonConfig.initItems();
        proxy.registerRenderers();
    }
    
    @EventHandler
    public void load(FMLInitializationEvent event) {
        packetHandler.register(new MPSAPacketHandler());
        AddonComponent.populate();

        if (!AddonUtils.isServerSide()) {
            System.out.println("MPSA: Loading Localization");
            ClientProxy.loadCurrentLanguage();
        }
        AddonConfig.loadOptions();
        proxy.registerHandlers();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandler);
        AddonRecipeManager.cheatyLeather();

        TerminalHandler.registerHandler();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        //AddonRecipeManager.addRecipes();
        AddonConfig.loadPowerModules();
        AddonConfig.getConfig().save();
    }
    
    @EventHandler
    public void onServerStart(FMLServerStartedEvent event) {
		AddonRecipeManager.loadOrPutRecipesFromJar(event.getModConfigurationDirectory() + "@CONFIG_PATH@/recipes/@DISPLAY_NAME@");
    }

}
