package com.RoyalNinja.ZeraniaHub.HubFeatures;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

import com.RoyalNinja.ZeraniaHub.Main;

import net.md_5.bungee.api.ChatColor;

public class FeatureEvents implements org.bukkit.event.Listener
{
	public static HashMap<String, Boolean> playersHidden = new HashMap<String, Boolean>();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		
		if(p.getItemInHand().getType() == Material.getMaterial(Main.plugin.getConfig().getInt("PlayerVisibility.ItemID")))
		{
			if(((Boolean) playersHidden.get(p.getName())).booleanValue())
			{
				for(Player all : Bukkit.getServer().getOnlinePlayers())
				{
					p.showPlayer(all);
				}
				playersHidden.put(p.getName(), Boolean.valueOf(false));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("PlayerVisibility.UseMessageOn")));
			} else
			{
				for(Player all : Bukkit.getServer().getOnlinePlayers())
				{
					p.hidePlayer(all);
				}
				playersHidden.put(p.getName(), Boolean.valueOf(true));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("PlayerVisibility.UseMessageOff")));
			}
		}
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e)
	{
		e.setCancelled(true);
		e.setDamage(0.0D);
	}
	
	@EventHandler
	public void onInventoryMove(InventoryClickEvent e)
	{
		Player p = (Player) e.getWhoClicked();
		
		if(e.getInventory().getName().equals(p.getInventory().getName()))
		{
			e.setCancelled(true);
			p.updateInventory();
			
			return;
		}
	}
	
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e)
	{
		Player p = e.getPlayer();
		
		e.setCancelled(true);
		p.updateInventory();
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e)
	{
		e.setDroppedExp(0);
		e.getDrops().clear();
	}
	
	@EventHandler
	public void onPlayerToggleFlight(PlayerToggleFlightEvent e)
	{
		Player p = e.getPlayer();
		
		if(p.getGameMode() == GameMode.CREATIVE)
		{
			return;
		}
		e.setCancelled(true);
		p.setAllowFlight(false);
		p.setFlying(false);
		
		p.setVelocity(p.getEyeLocation().getDirection().multiply(Main.plugin.getConfig().getDouble("DoubleJump.Forward")).add(new Vector(0.0D, Main.plugin.getConfig().getDouble("DoubleJump.Upward"), 0.0D)));
		
		FeatureThread.doubleJumpCooldown.put(p.getName(), Integer.valueOf(5));
	}
	
	@EventHandler
	public void onPlayerJump(PlayerMoveEvent e)
	{
		Player p = e.getPlayer();
		
		if((p.getGameMode() != GameMode.CREATIVE) && (p.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().getType() != Material.AIR) && (!p.isFlying()))
		{
			if(((Integer) FeatureThread.doubleJumpCooldown.get(p.getName())).intValue() > 0)
			{
				return;
			}
			p.setAllowFlight(true);
		}
	}
}
