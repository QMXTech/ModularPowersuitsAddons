package com.qmxtech.powersuitaddons.tick;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.machinemuse.utils.ElectricItemUtils;
import net.minecraft.client.Minecraft;

/**
 * Created by Shawn on 10/7/2014.
 */
public class MPSACommonTickHandler {

    public MPSACommonTickHandler() {
    }

    @SubscribeEvent
    public void onPreServerTick(TickEvent.ServerTickEvent event) {


    }
    @SubscribeEvent
    public void onPrePlayerTick(TickEvent.PlayerTickEvent event) {
      if (event.player != null && (event.player.worldObj.getTotalWorldTime() % 20 == 0) && ElectricItemUtils.getPlayerEnergy(event.player) > ElectricItemUtils.getMaxEnergy(event.player)) {
        if (ElectricItemUtils.getPlayerEnergy(event.player) > ElectricItemUtils.getMaxEnergy(event.player)) {
          ElectricItemUtils.drainPlayerEnergy(event.player, ElectricItemUtils.getPlayerEnergy(event.player) - (ElectricItemUtils.getPlayerEnergy(event.player) - ElectricItemUtils.getMaxEnergy(event.player)));
        }
      }
    }
}
