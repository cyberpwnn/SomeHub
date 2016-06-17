package com.RoyalNinja.ZeraniaHub.NPC;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.RoyalNinja.ZeraniaHub.Main;

public class NPCEvents implements Listener
{
	@SuppressWarnings("deprecation")
	public static boolean isFacingNPC(Player p, Location npcLoc, int distance)
	{
		List<Block> lineOfSight = new ArrayList<Block>();
		lineOfSight.add(p.getTargetBlock((HashSet<Byte>)null, distance));
		
		for(Block b : lineOfSight)
		{
			if((b.getX() == npcLoc.getBlockX()) && (b.getZ() == npcLoc.getBlockZ()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	@EventHandler
	public void onRegisterNPCPacket(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		NPCHandler.updateAllNPCPackets(p);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void useNPCCommand(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		Block clicked = p.getTargetBlock((HashSet<Byte>)null, 2);
		FileConfiguration config = Main.plugin.getConfig();
		List<String> allNpcs = config.getStringList("NPC.NPCList");
		
		for(String npcs : allNpcs)
		{
			Location npcLocation = new Location(Bukkit.getWorld(config.getString("NPC." + npcs + ".Location.World")), config.getInt("NPC." + npcs + ".Location.X"), config.getInt("NPC." + npcs + ".Location.Y"), config.getInt("NPC." + npcs + ".Location.Z"));
			
			if((Math.abs(clicked.getX() - npcLocation.getX()) <= 2.0D) && (Math.abs(clicked.getY() - npcLocation.getY()) <= 2.0D) && (Math.abs(clicked.getZ() - npcLocation.getZ()) <= 2.0D))
			{
				if(isFacingNPC(p, npcLocation, 2))
				{
					String command = config.getString("NPC." + npcs + ".Command").replaceAll("%player%", p.getName());
					Bukkit.getServer().broadcastMessage(command);
					Bukkit.getServer().dispatchCommand(p, command);
				}
			}
		}
	}
}
