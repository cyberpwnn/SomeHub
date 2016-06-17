package com.RoyalNinja.ZeraniaHub.Cosmetics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.RoyalNinja.ZeraniaHub.Main;

public class CosmeticsGUI
{
	static HashMap<String, String> trails = CosmeticsHandler.trailsActive;
	
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
	
	public static Inventory getCosmetics()
	{
		Inventory inv = Bukkit.createInventory(null, Main.plugin.getConfig().getInt("Cosmetics.Size"), net.md_5.bungee.api.ChatColor.AQUA + "Cosmetics");
		
		for(int i = 0; i < Main.plugin.getConfig().getInt("Cosmetics.Size"); i++)
		{
			int x = i + 1;
			
			String configName = Main.plugin.getConfig().getString("Cosmetics.Arrangement." + x);
			
			ItemStack item = getItemStack(Main.plugin.getConfig().getString("Cosmetics.Items." + configName + ".ItemID"));
			ItemMeta meta = item.getItemMeta();
			
			meta.setDisplayName(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Cosmetics.Items." + configName + ".Name")));
			
			ArrayList<String> lore = new ArrayList();
			
			for(String s : Main.plugin.getConfig().getStringList("Cosmetics.Items." + configName + ".Lore"))
			{
				lore.add(org.bukkit.ChatColor.translateAlternateColorCodes('&', s));
			}
			
			meta.setLore(lore);
			item.setItemMeta(meta);
			
			inv.setItem(i, item);
		}
		
		return inv;
	}
	
	public static Inventory getTrails(Player p)
	{
		Inventory i = Bukkit.createInventory(null, 9, net.md_5.bungee.api.ChatColor.AQUA + "Trails");
		
		if((p.hasPermission("trail.explode")) || (p.hasPermission("trail.*")))
		{
			if(((String) trails.get(p.getName())).contains("Explode"))
			{
				
				ItemStack item = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.InuseItem.ID"));
				ItemMeta itemMeta = item.getItemMeta();
				List<String> itemLore = new ArrayList();
				
				itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.DARK_GRAY + "Explode Trail");
				
				for(String s : Main.plugin.getConfig().getStringList("Trails.GUI.InuseItem.Lore"))
				{
					itemLore.add(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
				}
				
				itemMeta.setLore(itemLore);
				item.setItemMeta(itemMeta);
				
				i.addItem(new ItemStack[] { item });
			} else
			{
				ItemStack item = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.UnlockedItem.ID"));
				ItemMeta itemMeta = item.getItemMeta();
				List<String> itemLore = new ArrayList();
				
				itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.DARK_GRAY + "Explode Trail");
				
				for(String s : Main.plugin.getConfig().getStringList("Trails.GUI.UnlockedItem.Lore"))
				{
					itemLore.add(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
				}
				
				itemMeta.setLore(itemLore);
				item.setItemMeta(itemMeta);
				
				i.addItem(new ItemStack[] { item });
			}
			
		} else
		{
			ItemStack item = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.LockedItem.ID"));
			ItemMeta itemMeta = item.getItemMeta();
			List<String> itemLore = new ArrayList();
			
			itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.DARK_GRAY + "Explode Trail");
			
			for(String s : Main.plugin.getConfig().getStringList("Trails.GUI.LockedItem.Lore"))
			{
				itemLore.add(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
			}
			
			itemMeta.setLore(itemLore);
			item.setItemMeta(itemMeta);
			
			i.addItem(new ItemStack[] { item });
		}
		
		if((p.hasPermission("trail.firework")) || (p.hasPermission("trail.*")))
		{
			if(((String) trails.get(p.getName())).contains("Firework"))
			{
				ItemStack item = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.InuseItem.ID"));
				ItemMeta itemMeta = item.getItemMeta();
				List<String> itemLore = new ArrayList();
				
				itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.GRAY + "Firework Trail");
				
				for(String s : Main.plugin.getConfig().getStringList("Trails.GUI.InuseItem.Lore"))
				{
					itemLore.add(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
				}
				
				itemMeta.setLore(itemLore);
				item.setItemMeta(itemMeta);
				
				i.addItem(new ItemStack[] { item });
			} else
			{
				ItemStack item = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.UnlockedItem.ID"));
				ItemMeta itemMeta = item.getItemMeta();
				List<String> itemLore = new ArrayList();
				
				itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.GRAY + "Firework Trail");
				
				for(String s : Main.plugin.getConfig().getStringList("Trails.GUI.UnlockedItem.Lore"))
				{
					itemLore.add(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
				}
				
				itemMeta.setLore(itemLore);
				item.setItemMeta(itemMeta);
				
				i.addItem(new ItemStack[] { item });
			}
			
		} else
		{
			ItemStack item = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.LockedItem.ID"));
			ItemMeta itemMeta = item.getItemMeta();
			List<String> itemLore = new ArrayList();
			
			itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.GRAY + "Firework Trail");
			
			for(String s : Main.plugin.getConfig().getStringList("Trails.GUI.LockedItem.Lore"))
			{
				itemLore.add(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
			}
			
			itemMeta.setLore(itemLore);
			item.setItemMeta(itemMeta);
			
			i.addItem(new ItemStack[] { item });
		}
		
		if((p.hasPermission("trail.crit")) || (p.hasPermission("trail.*")))
		{
			if(((String) trails.get(p.getName())).contains("Crit"))
			{
				ItemStack item = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.InuseItem.ID"));
				ItemMeta itemMeta = item.getItemMeta();
				List<String> itemLore = new ArrayList();
				
				itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.LIGHT_PURPLE + "Crit Trail");
				
				for(String s : Main.plugin.getConfig().getStringList("Trails.GUI.InuseItem.Lore"))
				{
					itemLore.add(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
				}
				
				itemMeta.setLore(itemLore);
				item.setItemMeta(itemMeta);
				
				i.addItem(new ItemStack[] { item });
			} else
			{
				ItemStack item = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.UnlockedItem.ID"));
				ItemMeta itemMeta = item.getItemMeta();
				List<String> itemLore = new ArrayList();
				
				itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.LIGHT_PURPLE + "Crit Trail");
				
				for(String s : Main.plugin.getConfig().getStringList("Trails.GUI.UnlockedItem.Lore"))
				{
					itemLore.add(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
				}
				
				itemMeta.setLore(itemLore);
				item.setItemMeta(itemMeta);
				
				i.addItem(new ItemStack[] { item });
			}
		} else
		{
			ItemStack item = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.LockedItem.ID"));
			ItemMeta itemMeta = item.getItemMeta();
			List<String> itemLore = new ArrayList();
			
			itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.LIGHT_PURPLE + "Crit Trail");
			
			for(String s : Main.plugin.getConfig().getStringList("Trails.GUI.LockedItem.Lore"))
			{
				itemLore.add(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
			}
			
			itemMeta.setLore(itemLore);
			item.setItemMeta(itemMeta);
			
			i.addItem(new ItemStack[] { item });
		}
		
		if((p.hasPermission("trail.smoke")) || (p.hasPermission("trail.*")))
		{
			if(((String) trails.get(p.getName())).contains("Smoke"))
			{
				ItemStack item = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.InuseItem.ID"));
				ItemMeta itemMeta = item.getItemMeta();
				List<String> itemLore = new ArrayList();
				
				itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.BLACK + "Smoke Trail");
				
				for(String s : Main.plugin.getConfig().getStringList("Trails.GUI.InuseItem.Lore"))
				{
					itemLore.add(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
				}
				
				itemMeta.setLore(itemLore);
				item.setItemMeta(itemMeta);
				
				i.addItem(new ItemStack[] { item });
			} else
			{
				ItemStack item = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.UnlockedItem.ID"));
				ItemMeta itemMeta = item.getItemMeta();
				List<String> itemLore = new ArrayList();
				
				itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.BLACK + "Smoke Trail");
				
				for(String s : Main.plugin.getConfig().getStringList("Trails.GUI.UnlockedItem.Lore"))
				{
					itemLore.add(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
				}
				
				itemMeta.setLore(itemLore);
				item.setItemMeta(itemMeta);
				
				i.addItem(new ItemStack[] { item });
			}
		} else
		{
			ItemStack item = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.LockedItem.ID"));
			ItemMeta itemMeta = item.getItemMeta();
			List<String> itemLore = new ArrayList();
			
			itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.BLACK + "Smoke Trail");
			
			for(String s : Main.plugin.getConfig().getStringList("Trails.GUI.LockedItem.Lore"))
			{
				itemLore.add(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
			}
			
			itemMeta.setLore(itemLore);
			item.setItemMeta(itemMeta);
			
			i.addItem(new ItemStack[] { item });
		}
		
		if((p.hasPermission("trail.portal")) || (p.hasPermission("trail.*")))
		{
			if(((String) trails.get(p.getName())).contains("Portal"))
			{
				ItemStack item = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.InuseItem.ID"));
				ItemMeta itemMeta = item.getItemMeta();
				List<String> itemLore = new ArrayList();
				
				itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.DARK_PURPLE + "Portal Trail");
				
				for(String s : Main.plugin.getConfig().getStringList("Trails.GUI.InuseItem.Lore"))
				{
					itemLore.add(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
				}
				
				itemMeta.setLore(itemLore);
				item.setItemMeta(itemMeta);
				
				i.addItem(new ItemStack[] { item });
			} else
			{
				ItemStack item = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.UnlockedItem.ID"));
				ItemMeta itemMeta = item.getItemMeta();
				List<String> itemLore = new ArrayList();
				
				itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.DARK_PURPLE + "Portal Trail");
				
				for(String s : Main.plugin.getConfig().getStringList("Trails.GUI.UnlockedItem.Lore"))
				{
					itemLore.add(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
				}
				
				itemMeta.setLore(itemLore);
				item.setItemMeta(itemMeta);
				
				i.addItem(new ItemStack[] { item });
			}
		} else
		{
			ItemStack item = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.LockedItem.ID"));
			ItemMeta itemMeta = item.getItemMeta();
			List<String> itemLore = new ArrayList();
			
			itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.DARK_PURPLE + "Portal Trail");
			
			for(String s : Main.plugin.getConfig().getStringList("Trails.GUI.LockedItem.Lore"))
			{
				itemLore.add(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
			}
			
			itemMeta.setLore(itemLore);
			item.setItemMeta(itemMeta);
			
			i.addItem(new ItemStack[] { item });
		}
		
		if((p.hasPermission("trail.flame")) || (p.hasPermission("trail.*")))
		{
			if(((String) trails.get(p.getName())).contains("Flame"))
			{
				ItemStack item = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.InuseItem.ID"));
				ItemMeta itemMeta = item.getItemMeta();
				List<String> itemLore = new ArrayList();
				
				itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.RED + "Flame Trail");
				
				for(String s : Main.plugin.getConfig().getStringList("Trails.GUI.InuseItem.Lore"))
				{
					itemLore.add(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
				}
				
				itemMeta.setLore(itemLore);
				item.setItemMeta(itemMeta);
				
				i.addItem(new ItemStack[] { item });
			} else
			{
				ItemStack item = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.UnlockedItem.ID"));
				ItemMeta itemMeta = item.getItemMeta();
				List<String> itemLore = new ArrayList();
				
				itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.RED + "Flame Trail");
				
				for(String s : Main.plugin.getConfig().getStringList("Trails.GUI.UnlockedItem.Lore"))
				{
					itemLore.add(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
				}
				
				itemMeta.setLore(itemLore);
				item.setItemMeta(itemMeta);
				
				i.addItem(new ItemStack[] { item });
			}
		} else
		{
			ItemStack item = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.LockedItem.ID"));
			ItemMeta itemMeta = item.getItemMeta();
			List<String> itemLore = new ArrayList();
			
			itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.RED + "Flame Trail");
			
			for(String s : Main.plugin.getConfig().getStringList("Trails.GUI.LockedItem.Lore"))
			{
				itemLore.add(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
			}
			
			itemMeta.setLore(itemLore);
			item.setItemMeta(itemMeta);
			
			i.addItem(new ItemStack[] { item });
		}
		
		if((p.hasPermission("trail.cloud")) || (p.hasPermission("trail.*")))
		{
			if(((String) trails.get(p.getName())).contains("Cloud"))
			{
				ItemStack item = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.InuseItem.ID"));
				ItemMeta itemMeta = item.getItemMeta();
				List<String> itemLore = new ArrayList();
				
				itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.GRAY + "Cloud Trail");
				
				for(String s : Main.plugin.getConfig().getStringList("Trails.GUI.InuseItem.Lore"))
				{
					itemLore.add(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
				}
				
				itemMeta.setLore(itemLore);
				item.setItemMeta(itemMeta);
				
				i.addItem(new ItemStack[] { item });
			} else
			{
				ItemStack item = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.UnlockedItem.ID"));
				ItemMeta itemMeta = item.getItemMeta();
				List<String> itemLore = new ArrayList();
				
				itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.GRAY + "Cloud Trail");
				
				for(String s : Main.plugin.getConfig().getStringList("Trails.GUI.UnlockedItem.Lore"))
				{
					itemLore.add(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
				}
				
				itemMeta.setLore(itemLore);
				item.setItemMeta(itemMeta);
				
				i.addItem(new ItemStack[] { item });
			}
		} else
		{
			ItemStack item = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.LockedItem.ID"));
			ItemMeta itemMeta = item.getItemMeta();
			List<String> itemLore = new ArrayList();
			
			itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.GRAY + "Cloud Trail");
			
			for(String s : Main.plugin.getConfig().getStringList("Trails.GUI.LockedItem.Lore"))
			{
				itemLore.add(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
			}
			
			itemMeta.setLore(itemLore);
			item.setItemMeta(itemMeta);
			
			i.addItem(new ItemStack[] { item });
		}
		
		if((p.hasPermission("trail.slime")) || (p.hasPermission("trail.*")))
		{
			if(((String) trails.get(p.getName())).contains("Slime"))
			{
				ItemStack item = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.InuseItem.ID"));
				ItemMeta itemMeta = item.getItemMeta();
				List<String> itemLore = new ArrayList();
				
				itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.GREEN + "Slime Trail");
				
				for(String s : Main.plugin.getConfig().getStringList("Trails.GUI.InuseItem.Lore"))
				{
					itemLore.add(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
				}
				
				itemMeta.setLore(itemLore);
				item.setItemMeta(itemMeta);
				
				i.addItem(new ItemStack[] { item });
			} else
			{
				ItemStack item = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.UnlockedItem.ID"));
				ItemMeta itemMeta = item.getItemMeta();
				List<String> itemLore = new ArrayList();
				
				itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.GREEN + "Slime Trail");
				
				for(String s : Main.plugin.getConfig().getStringList("Trails.GUI.UnlockedItem.Lore"))
				{
					itemLore.add(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
				}
				
				itemMeta.setLore(itemLore);
				item.setItemMeta(itemMeta);
				
				i.addItem(new ItemStack[] { item });
			}
		} else
		{
			ItemStack item = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.LockedItem.ID"));
			ItemMeta itemMeta = item.getItemMeta();
			List<String> itemLore = new ArrayList();
			
			itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.GREEN + "Slime Trail");
			
			for(String s : Main.plugin.getConfig().getStringList("Trails.GUI.LockedItem.Lore"))
			{
				itemLore.add(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
			}
			
			itemMeta.setLore(itemLore);
			item.setItemMeta(itemMeta);
			
			i.addItem(new ItemStack[] { item });
		}
		
		if((p.hasPermission("trail.heart")) || (p.hasPermission("trail.*")))
		{
			if(((String) trails.get(p.getName())).contains("Heart"))
			{
				ItemStack item = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.InuseItem.ID"));
				ItemMeta itemMeta = item.getItemMeta();
				List<String> itemLore = new ArrayList();
				
				itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.RED + "Heart Trail");
				
				for(String s : Main.plugin.getConfig().getStringList("Trails.GUI.InuseItem.Lore"))
				{
					itemLore.add(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
				}
				
				itemMeta.setLore(itemLore);
				item.setItemMeta(itemMeta);
				
				i.addItem(new ItemStack[] { item });
			} else
			{
				ItemStack item = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.UnlockedItem.ID"));
				ItemMeta itemMeta = item.getItemMeta();
				List<String> itemLore = new ArrayList();
				
				itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.RED + "Heart Trail");
				
				for(String s : Main.plugin.getConfig().getStringList("Trails.GUI.UnlockedItem.Lore"))
				{
					itemLore.add(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
				}
				
				itemMeta.setLore(itemLore);
				item.setItemMeta(itemMeta);
				
				i.addItem(new ItemStack[] { item });
			}
		} else
		{
			ItemStack item = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.LockedItem.ID"));
			ItemMeta itemMeta = item.getItemMeta();
			List<String> itemLore = new ArrayList();
			
			itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.RED + "Heart Trail");
			
			for(String s : Main.plugin.getConfig().getStringList("Trails.GUI.LockedItem.Lore"))
			{
				itemLore.add(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
			}
			
			itemMeta.setLore(itemLore);
			item.setItemMeta(itemMeta);
			
			i.addItem(new ItemStack[] { item });
		}
		
		return i;
	}
}
