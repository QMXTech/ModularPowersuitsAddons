package com.qmxtech.powersuitaddons.common;

import com.qmxtech.powersuitaddons.network.AndrewPacketHandler;
import com.qmxtech.powersuitaddons.network.MPSAPacketHandler;
import com.qmxtech.powersuitaddons.tick.MPSACommonTickHandler;
import net.minecraftforge.common.MinecraftForge;


public class CommonProxy {

    //private static CommonTickHandler commonTickHandler;
    public static MPSAPacketHandler packetHandler;


    public void registerHandlers() {
        //commonTickHandler = new CommonTickHandler();
        //TickRegistry.registerTickHandler(commonTickHandler, Side.SERVER);
        //CommonTickHandler.load();
        MinecraftForge.EVENT_BUS.register(new MPSACommonTickHandler());
        packetHandler = new MPSAPacketHandler();
        //packetHandler.register();
    }

    public void registerRenderers() {
    }

    public static String translate(String str) {
        return str;
    }


}
