package com.RoyalNinja.ZeraniaHub.HotBar;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.RoyalNinja.ZeraniaHub.Main;

public class HotBarEvents implements Listener
{
	@SuppressWarnings("deprecation")
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
	
	@EventHandler
	public void onPlayerJoinHotbar(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		
		FileConfiguration config = Main.plugin.getConfig();
		
		ItemStack visi = getItemStack(config.getString("HotBar.Items.PlayerVisibility.ItemID"));
		ItemMeta visiMeta = visi.getItemMeta();
		visiMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString("HotBar.Items.PlayerVisibility.Name")));
		ArrayList<String> visiLore = new ArrayList<String>();
		for(String s : config.getStringList("HotBar.Items.PlayerVisibility.Lore"))
		{
			visiLore.add(ChatColor.translateAlternateColorCodes('&', s));
		}
		visiMeta.setLore(visiLore);
		visi.setItemMeta(visiMeta);
		
		ItemStack server = getItemStack(config.getString("ServerSelector.ItemID"));
		ItemMeta serverMeta = server.getItemMeta();
		serverMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString("HotBar.Items.ServerSelector.Name")));
		ArrayList<String> serverLore = new ArrayList<String>();
		for(String s : config.getStringList("HotBar.Items.ServerSelector.Lore"))
		{
			serverLore.add(ChatColor.translateAlternateColorCodes('&', s));
		}
		serverMeta.setLore(serverLore);
		server.setItemMeta(serverMeta);
		
		ItemStack trail = getItemStack(config.getString("HotBar.Items.Cosmetics.ItemID"));
		ItemMeta trailMeta = trail.getItemMeta();
		trailMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString("HotBar.Items.Cosmetics.Name")));
		ArrayList<String> trailLore = new ArrayList<String>();
		for(String s : config.getStringList("HotBar.Items.Cosmetics.Lore"))
		{
			trailLore.add(ChatColor.translateAlternateColorCodes('&', s));
		}
		trailMeta.setLore(trailLore);
		trail.setItemMeta(trailMeta);
		
		ItemStack hook = new ItemStack(Material.FISHING_ROD);
		ItemMeta hookMeta = hook.getItemMeta();
		hookMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString("HotBar.Items.GrapplingHook.Name")));
		ArrayList<String> hookLore = new ArrayList<String>();
		for(String s : config.getStringList("HotBar.Items.GrapplingHook.Lore"))
		{
			hookLore.add(ChatColor.translateAlternateColorCodes('&', s));
		}
		hookMeta.setLore(hookLore);
		hook.setItemMeta(hookMeta);
		
		ItemStack filler = getItemStack(config.getString("HotBar.Items.Filler.ItemID"));
		ItemMeta fillerMeta = filler.getItemMeta();
		fillerMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString("HotBar.Items.Filler.Name")));
		filler.setItemMeta(fillerMeta);
		
		for(int i = 0; i < 9; i++)
		{
			int x = i + 1;
			if(config.getString("HotBar.Arrangement." + x).equals("Filler"))
			{
				p.getInventory().setItem(i, filler);
			}
			if(config.getString("HotBar.Arrangement." + x).equals("ServerSelector"))
			{
				p.getInventory().setItem(i, server);
			}
			if(config.getString("HotBar.Arrangement." + x).equals("PlayerVisibility"))
			{
				p.getInventory().setItem(i, visi);
			}
			if(config.getString("HotBar.Arrangement." + x).equals("Cosmetics"))
			{
				p.getInventory().setItem(i, trail);
			}
			if(config.getString("HotBar.Arrangement." + x).equals("GrapplingHook"))
			{
				p.getInventory().setItem(i, hook);
			}
		}
	}
}
