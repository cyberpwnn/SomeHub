package com.RoyalNinja.ZeraniaHub.HubFeatures;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

public class GrapplingHook implements org.bukkit.event.Listener
{
	@EventHandler
	public void playerGrappleEvent(PlayerFishEvent e)
	{
		Player p = e.getPlayer();
		
		Material item = p.getItemInHand().getType();
		if(item == Material.FISHING_ROD)
		{
			Entity hook = null;
			java.util.List<Entity> nearby = p.getNearbyEntities(50.0D, 50.0D, 50.0D);
			for(Entity ent : nearby)
			{
				if(ent.getType() == org.bukkit.entity.EntityType.FISHING_HOOK)
				{
					hook = ent;
					break;
				}
			}
			if(hook != null)
			{
				Location hookLocation = hook.getLocation();
				
				Location playerToThrowLocation = p.getEyeLocation();
				
				double x = hookLocation.getX() - playerToThrowLocation.getX();
				double y = hookLocation.getY() - playerToThrowLocation.getY();
				double z = hookLocation.getZ() - playerToThrowLocation.getZ();
				
				Vector throwVector = new Vector(x, y, z);
				
				throwVector.normalize();
				throwVector.multiply(10.0D);
				throwVector.setY(1.5D);
				
				p.setVelocity(throwVector);
			}
		}
	}
}
