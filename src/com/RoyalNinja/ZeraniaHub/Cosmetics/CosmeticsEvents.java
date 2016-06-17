package com.RoyalNinja.ZeraniaHub.Cosmetics;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.RoyalNinja.ZeraniaHub.Main;

public class CosmeticsEvents implements org.bukkit.event.Listener
{
	@org.bukkit.event.EventHandler
	public void onPlayerUseCustomCosmeticsItem(InventoryClickEvent e)
	{
		Player p = (Player) e.getWhoClicked();
		
		if(e.getClickedInventory() == null)
			return;
		if((e.getClickedInventory().getName().equals(CosmeticsGUI.getCosmetics().getName())) && (e.getCurrentItem() != null) && (e.getCurrentItem().getType() != Material.AIR) && (e.getCurrentItem().hasItemMeta()) && (e.getCurrentItem().getItemMeta().hasDisplayName()))
		{
			ItemStack item = e.getCurrentItem();
			
			String name = item.getItemMeta().getDisplayName();
			
			FileConfiguration config = Main.plugin.getConfig();
			
			for(String custom : config.getStringList("Cosmetics.Items.CustomItems"))
			{
				if(ChatColor.translateAlternateColorCodes('&', config.getString("Cosmetics.Items." + custom + ".Name")).equals(name))
				{
					String command = config.getString("Cosmetics.Items." + custom + ".Command").replaceAll("%player%", p.getName());
					
					Main.plugin.getServer().dispatchCommand(p, command);
				}
			}
		}
	}
	
	@org.bukkit.event.EventHandler
	public void onTrailsOpen(org.bukkit.event.player.PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		
		if((p.getItemInHand() != null) && (p.getItemInHand().getType() != Material.AIR) && (p.getItemInHand().hasItemMeta()) && (p.getItemInHand().getItemMeta().hasDisplayName()))
		{
			if(p.getItemInHand().getType().getId() == getItemStack(Main.plugin.getConfig().getString("HotBar.Items.Cosmetics.ItemID")).getTypeId())
			{
				e.setCancelled(true);
				p.closeInventory();
				p.openInventory(CosmeticsGUI.getCosmetics());
				
				return;
			}
		}
	}
	
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
	
	@org.bukkit.event.EventHandler
	public void onTrailsOpen(InventoryClickEvent e)
	{
		Player p = (Player) e.getWhoClicked();
		
		if(e.getInventory().getName().equals(CosmeticsGUI.getCosmetics().getName()))
		{
			e.setCancelled(true);
			
			if((e.getCurrentItem() != null) && (e.getCurrentItem().getType() != Material.AIR) && (e.getCurrentItem().hasItemMeta()) && (e.getCurrentItem().getItemMeta().hasDisplayName()))
			{
				String clickedName = e.getCurrentItem().getItemMeta().getDisplayName();
				String trailName = Main.plugin.getConfig().getString("Cosmetics.Items.Trails.Name");
				
				if(clickedName.equals(ChatColor.translateAlternateColorCodes('&', trailName)))
				{
					p.closeInventory();
					p.openInventory(CosmeticsGUI.getTrails(p));
				}
			}
		}
	}
	
	@org.bukkit.event.EventHandler
	public void playerSelectTrail(InventoryClickEvent e)
	{
		Player p = (Player) e.getWhoClicked();
		
		if(e.getInventory().getName().equals(ChatColor.AQUA + "Trails"))
		{
			e.setCancelled(true);
			
			if((e.getCurrentItem() != null) && (e.getCurrentItem().getType() != Material.AIR) && (e.getCurrentItem().hasItemMeta()) && (e.getCurrentItem().getItemMeta().hasDisplayName()))
			{
				String trail = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
				
				ItemStack inuseItem = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.InuseItem.ID"));
				ItemStack unlockedItem = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.UnlockedItem.ID"));
				ItemStack lockedItem = getItemStack(Main.plugin.getConfig().getString("Trails.GUI.LockedItem.ID"));
				
				Integer itemId = Integer.valueOf(e.getCurrentItem().getTypeId());
				Integer itemData = Integer.valueOf(e.getCurrentItem().getDurability());
				
				if(trail.equals("Explode Trail"))
				{
					if((itemId.intValue() == inuseItem.getType().getId()) && (itemData.intValue() == inuseItem.getDurability()))
					{
						CosmeticsHandler.trailsActive.put(p.getName(), "None");
						p.sendMessage(ChatColor.GREEN + "You have unequipped the Explode trail!");
						p.closeInventory();
						return;
					}
					if((itemId.intValue() == unlockedItem.getType().getId()) && (itemData.intValue() == unlockedItem.getDurability()))
					{
						CosmeticsHandler.trailsActive.put(p.getName(), "Explode");
						p.sendMessage(ChatColor.AQUA + "You have equipped the Explode trail!");
						p.closeInventory();
						return;
					}
					if((itemId.intValue() == lockedItem.getType().getId()) && (itemData.intValue() == lockedItem.getDurability()))
					{
						p.sendMessage(ChatColor.RED + "You do not have this trail unlocked!");
						p.closeInventory();
						return;
					}
				}
				
				if(trail.equals("Firework Trail"))
				{
					if((itemId.intValue() == inuseItem.getType().getId()) && (itemData.intValue() == inuseItem.getDurability()))
					{
						CosmeticsHandler.trailsActive.put(p.getName(), "None");
						p.sendMessage(ChatColor.GREEN + "You have unequipped the Firework trail!");
						p.closeInventory();
						return;
					}
					if((itemId.intValue() == unlockedItem.getType().getId()) && (itemData.intValue() == unlockedItem.getDurability()))
					{
						CosmeticsHandler.trailsActive.put(p.getName(), "Firework");
						p.sendMessage(ChatColor.AQUA + "You have equipped the Firework trail!");
						p.closeInventory();
						return;
					}
					if((itemId.intValue() == lockedItem.getType().getId()) && (itemData.intValue() == lockedItem.getDurability()))
					{
						p.sendMessage(ChatColor.RED + "You do not have this trail unlocked!");
						p.closeInventory();
						return;
					}
				}
				
				if(trail.equals("Crit Trail"))
				{
					if((itemId.intValue() == inuseItem.getType().getId()) && (itemData.intValue() == inuseItem.getDurability()))
					{
						CosmeticsHandler.trailsActive.put(p.getName(), "None");
						p.sendMessage(ChatColor.GREEN + "You have unequipped the Crit trail!");
						p.closeInventory();
						return;
					}
					if((itemId.intValue() == unlockedItem.getType().getId()) && (itemData.intValue() == unlockedItem.getDurability()))
					{
						CosmeticsHandler.trailsActive.put(p.getName(), "Crit");
						p.sendMessage(ChatColor.AQUA + "You have equipped the Crit trail!");
						p.closeInventory();
						return;
					}
					if((itemId.intValue() == lockedItem.getType().getId()) && (itemData.intValue() == lockedItem.getDurability()))
					{
						p.sendMessage(ChatColor.RED + "You do not have this trail unlocked!");
						p.closeInventory();
						return;
					}
				}
				
				if(trail.equals("Smoke Trail"))
				{
					if((itemId.intValue() == inuseItem.getType().getId()) && (itemData.intValue() == inuseItem.getDurability()))
					{
						CosmeticsHandler.trailsActive.put(p.getName(), "None");
						p.sendMessage(ChatColor.GREEN + "You have unequipped the Smoke trail!");
						p.closeInventory();
						return;
					}
					if((itemId.intValue() == unlockedItem.getType().getId()) && (itemData.intValue() == unlockedItem.getDurability()))
					{
						CosmeticsHandler.trailsActive.put(p.getName(), "Smoke");
						p.sendMessage(ChatColor.AQUA + "You have equipped the Smoke trail!");
						p.closeInventory();
						return;
					}
					if((itemId.intValue() == lockedItem.getType().getId()) && (itemData.intValue() == lockedItem.getDurability()))
					{
						p.sendMessage(ChatColor.RED + "You do not have this trail unlocked!");
						p.closeInventory();
						return;
					}
				}
				
				if(trail.equals("Portal Trail"))
				{
					if((itemId.intValue() == inuseItem.getType().getId()) && (itemData.intValue() == inuseItem.getDurability()))
					{
						CosmeticsHandler.trailsActive.put(p.getName(), "None");
						p.sendMessage(ChatColor.GREEN + "You have unequipped the Portal trail!");
						p.closeInventory();
						return;
					}
					if((itemId.intValue() == unlockedItem.getType().getId()) && (itemData.intValue() == unlockedItem.getDurability()))
					{
						CosmeticsHandler.trailsActive.put(p.getName(), "Portal");
						p.sendMessage(ChatColor.AQUA + "You have equipped the Portal trail!");
						p.closeInventory();
						return;
					}
					if((itemId.intValue() == lockedItem.getType().getId()) && (itemData.intValue() == lockedItem.getDurability()))
					{
						p.sendMessage(ChatColor.RED + "You do not have this trail unlocked!");
						p.closeInventory();
						return;
					}
				}
				
				if(trail.equals("Flame Trail"))
				{
					if((itemId.intValue() == inuseItem.getType().getId()) && (itemData.intValue() == inuseItem.getDurability()))
					{
						CosmeticsHandler.trailsActive.put(p.getName(), "None");
						p.sendMessage(ChatColor.GREEN + "You have unequipped the Flame trail!");
						p.closeInventory();
						return;
					}
					if((itemId.intValue() == unlockedItem.getType().getId()) && (itemData.intValue() == unlockedItem.getDurability()))
					{
						CosmeticsHandler.trailsActive.put(p.getName(), "Flame");
						p.sendMessage(ChatColor.AQUA + "You have equipped the Flame trail!");
						p.closeInventory();
						return;
					}
					if((itemId.intValue() == lockedItem.getType().getId()) && (itemData.intValue() == lockedItem.getDurability()))
					{
						p.sendMessage(ChatColor.RED + "You do not have this trail unlocked!");
						p.closeInventory();
						return;
					}
				}
				
				if(trail.equals("Cloud Trail"))
				{
					if((itemId.intValue() == inuseItem.getType().getId()) && (itemData.intValue() == inuseItem.getDurability()))
					{
						CosmeticsHandler.trailsActive.put(p.getName(), "None");
						p.sendMessage(ChatColor.GREEN + "You have unequipped the Cloud trail!");
						p.closeInventory();
						return;
					}
					if((itemId.intValue() == unlockedItem.getType().getId()) && (itemData.intValue() == unlockedItem.getDurability()))
					{
						CosmeticsHandler.trailsActive.put(p.getName(), "Cloud");
						p.sendMessage(ChatColor.AQUA + "You have equipped the Cloud trail!");
						p.closeInventory();
						return;
					}
					if((itemId.intValue() == lockedItem.getType().getId()) && (itemData.intValue() == lockedItem.getDurability()))
					{
						p.sendMessage(ChatColor.RED + "You do not have this trail unlocked!");
						p.closeInventory();
						return;
					}
				}
				
				if(trail.equals("Slime Trail"))
				{
					if((itemId.intValue() == inuseItem.getType().getId()) && (itemData.intValue() == inuseItem.getDurability()))
					{
						CosmeticsHandler.trailsActive.put(p.getName(), "None");
						p.sendMessage(ChatColor.GREEN + "You have unequipped the Slime trail!");
						p.closeInventory();
						return;
					}
					if((itemId.intValue() == unlockedItem.getType().getId()) && (itemData.intValue() == unlockedItem.getDurability()))
					{
						CosmeticsHandler.trailsActive.put(p.getName(), "Slime");
						p.sendMessage(ChatColor.AQUA + "You have equipped the Slime trail!");
						p.closeInventory();
						return;
					}
					if((itemId.intValue() == lockedItem.getType().getId()) && (itemData.intValue() == lockedItem.getDurability()))
					{
						p.sendMessage(ChatColor.RED + "You do not have this trail unlocked!");
						p.closeInventory();
						return;
					}
				}
				
				if(trail.equals("Heart Trail"))
				{
					if((itemId.intValue() == inuseItem.getType().getId()) && (itemData.intValue() == inuseItem.getDurability()))
					{
						CosmeticsHandler.trailsActive.put(p.getName(), "None");
						p.sendMessage(ChatColor.GREEN + "You have unequipped the Heart trail!");
						p.closeInventory();
						return;
					}
					if((itemId.intValue() == unlockedItem.getType().getId()) && (itemData.intValue() == unlockedItem.getDurability()))
					{
						CosmeticsHandler.trailsActive.put(p.getName(), "Heart");
						p.sendMessage(ChatColor.AQUA + "You have equipped the Heart trail!");
						p.closeInventory();
						return;
					}
					if((itemId.intValue() == lockedItem.getType().getId()) && (itemData.intValue() == lockedItem.getDurability()))
					{
						p.sendMessage(ChatColor.RED + "You do not have this trail unlocked!");
						p.closeInventory();
						return;
					}
				}
			}
		}
	}
}
