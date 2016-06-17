package com.RoyalNinja.ZeraniaHub.Portals;

import org.bukkit.Location;

import com.RoyalNinja.ZeraniaHub.Main;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class PortalManager
{
	public static boolean isPortal(Location l)
	{
		WorldGuardPlugin worldGuard = Main.getWorldGuard();
		
		Vector portal = new Vector(l.getX(), l.getY(), l.getZ());
		
		RegionManager regionManager = worldGuard.getRegionManager(l.getWorld());
		
		ApplicableRegionSet set = regionManager.getApplicableRegions(portal);
		
		for(ProtectedRegion region : set.getRegions())
		{
			if(region.contains(portal))
			{
				if(region.getId().toLowerCase().contains("portal".toLowerCase()))
				{
					return true;
				}
				return false;
			}
		}
		
		return false;
	}
}
