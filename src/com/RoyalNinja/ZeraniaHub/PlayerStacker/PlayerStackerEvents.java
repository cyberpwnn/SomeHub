package com.RoyalNinja.ZeraniaHub.PlayerStacker;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import com.RoyalNinja.ZeraniaHub.Main;

public class PlayerStackerEvents implements Listener
{
	@EventHandler
	public void onPlayerStack(PlayerInteractEntityEvent e)
	{
		Player p = e.getPlayer();
		
		if((e.getRightClicked() instanceof Player))
		{
			p.setPassenger(e.getRightClicked());
		}
	}
	
	@EventHandler
	public void onPlayerThrowStack(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		
		if(e.getAction() == Action.LEFT_CLICK_AIR)
		{
			if(p.getPassenger() != null)
			{
				if((p.getPassenger() instanceof Player))
				{
					Entity stacked = p.getPassenger();
					
					p.eject();
					
					Double strength = Double.valueOf(Main.plugin.getConfig().getDouble("PlayerStackerThrowStrength"));
					
					stacked.setVelocity(p.getEyeLocation().getDirection().multiply(strength.doubleValue()).add(new Vector(0.0D, 1.0D, 0.0D)));
				}
			}
		}
	}
}
