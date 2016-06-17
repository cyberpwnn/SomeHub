package com.RoyalNinja.ZeraniaHub.Portals;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import com.RoyalNinja.ZeraniaHub.ServerSelector.ServerSelectorGUI;

public class PortalEvents implements Listener
{
	@EventHandler
	public void onPlayerPortalEnter(PlayerMoveEvent e)
	{
		Player p = e.getPlayer();
		
		Location l = e.getTo();
		
		if(PortalManager.isPortal(l))
		{
			e.setCancelled(true);
			p.teleport(e.getFrom());
			
			Location playerToThrowLocation = p.getEyeLocation();
			
			double x = playerToThrowLocation.getX() - l.getX();
			double y = playerToThrowLocation.getY() - l.getY();
			double z = playerToThrowLocation.getZ() - l.getZ();
			
			Vector throwVector = new Vector(x, y, z);
			
			throwVector.normalize();
			throwVector.multiply(5.0D);
			throwVector.setY(0.5D);
			
			p.setVelocity(throwVector);
			
			p.openInventory(ServerSelectorGUI.getServerSelectorGUI());
		}
	}
}
