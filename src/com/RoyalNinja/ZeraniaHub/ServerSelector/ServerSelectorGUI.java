package com.RoyalNinja.ZeraniaHub.ServerSelector;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.RoyalNinja.ZeraniaHub.Main;

import net.md_5.bungee.api.ChatColor;

public class ServerSelectorGUI
{
	public static ItemStack getItemStack(String itemId)
	{
		String[] parts = itemId.split(":");
		int matId = Integer.parseInt(parts[0]);
		if(parts.length == 2)
		{
			short data = Short.parseShort(parts[1]);
			return new ItemStack(Material.getMaterial(matId), 1, data);
		}
		return new ItemStack(Material.getMaterial(matId));
	}
	
	public static Inventory getServerSelectorGUI()
	{
		FileConfiguration config = Main.plugin.getConfig();
		
		Inventory inv = Bukkit.createInventory(null, config.getInt("ServerSelector.Inventory.Size"), ChatColor.AQUA + "Server Selector");
		
		for(int i = 0; i < config.getInt("ServerSelector.Inventory.Size"); i++)
		{
			int x = i + 1;
			
			String configItem = config.getString("ServerSelector.Inventory.Arrangement." + x);
			
			ItemStack item = getItemStack(config.getString("ServerSelector.Items." + configItem + ".ItemID"));
			ItemMeta meta = item.getItemMeta();
			
			meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString("ServerSelector.Items." + configItem + ".Name")));
			
			ArrayList<String> lore = new ArrayList();
			
			for(String s : config.getStringList("ServerSelector.Items." + configItem + ".Lore"))
			{
				lore.add(ChatColor.translateAlternateColorCodes('&', s));
			}
			
			meta.setLore(lore);
			item.setItemMeta(meta);
			
			inv.setItem(i, item);
		}
		
		return inv;
	}
}
