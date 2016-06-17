package com.RoyalNinja.ZeraniaHub.ServerSelector;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.RoyalNinja.ZeraniaHub.Main;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.md_5.bungee.api.ChatColor;

public class ServerSelectorEvents implements Listener
{
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();

		if(p.getItemInHand().getType().equals(Material.getMaterial(Integer.valueOf(Main.plugin.getConfig().getString("ServerSelector.ItemID")))))
		{
			p.openInventory(ServerSelectorGUI.getServerSelectorGUI());
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerServerSelect(InventoryClickEvent e)
	{
		Player p = (Player) e.getWhoClicked();
		
		if(e.getInventory().getName().equals(ChatColor.AQUA + "Server Selector"))
		{
			e.setCancelled(true);
			
			if((e.getCurrentItem() == null) || (e.getInventory().getItem(e.getSlot()) == null))
				return;
			if((!e.getCurrentItem().hasItemMeta()) || (!e.getCurrentItem().getItemMeta().hasDisplayName()))
			{
				return;
			}
			FileConfiguration config = Main.plugin.getConfig();
			
			for(int i = 0; i < config.getInt("ServerSelector.Inventory.Size"); i++)
			{
				int x = i + 1;
				
				String configItem = config.getString("ServerSelector.Inventory.Arrangement." + x);
				
				if(config.getString("ServerSelector.Items." + configItem + ".BungeeName") == null)
				{
					return;
				}
				String bungeeServerName = config.getString("ServerSelector.Items." + configItem + ".BungeeName");
				
				if(ChatColor.translateAlternateColorCodes('&', config.getString("ServerSelector.Items." + configItem + ".Name")).equals(e.getCurrentItem().getItemMeta().getDisplayName()))
				{
					System.out.println("SENDING: " + p.getName() + " <> " + bungeeServerName);
					ByteArrayDataOutput out = ByteStreams.newDataOutput();
					out.writeUTF("Connect");
					out.writeUTF(bungeeServerName);
					p.sendPluginMessage(Main.plugin, "BungeeCord", out.toByteArray());
				}
			}
		}
	}
}
