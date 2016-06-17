package com.RoyalNinja.ZeraniaHub.HubBalancer;

import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import com.RoyalNinja.ZeraniaHub.Main;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.md_5.bungee.api.ChatColor;

public class BalancerEvents implements Listener
{
	HashMap<String, String> connectedPort = new HashMap();
	
	@EventHandler
	public void onPlayerLoginBalance(PlayerLoginEvent e)
	{
		this.connectedPort.put(e.getPlayer().getName(), e.getHostname().split(":")[0]);
	}
	
	@EventHandler
	public void onPlayerJoinBalance1(PlayerJoinEvent e)
	{
		final Player p = e.getPlayer();
		
		p.sendMessage(ChatColor.GREEN + "Sending you to least populated hub...");
		
		Main.plugin.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
		{
			public void run()
			{
				FileConfiguration config = Main.plugin.getConfig();
				
				Integer port = Integer.valueOf(config.getInt("HubBalancer." + BalancerHandler.getBalancedHub() + ".Port"));
				
				if(port.toString().equals(BalancerEvents.this.connectedPort.get(p.getName())))
				{
					return;
				}
				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				
				out.writeUTF("Connect");
				out.writeUTF(BalancerHandler.getBalancedHub());
				
				p.sendPluginMessage(Main.plugin, "BungeeCord", out.toByteArray());
			}
		}, 60L);
	}
}
