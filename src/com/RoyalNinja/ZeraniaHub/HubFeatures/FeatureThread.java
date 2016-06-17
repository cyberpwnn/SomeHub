package com.RoyalNinja.ZeraniaHub.HubFeatures;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.RoyalNinja.ZeraniaHub.Main;

public class FeatureThread implements Runnable
{
	public static HashMap<String, Integer> doubleJumpCooldown = new HashMap<String, Integer>();
	
	public void run()
	{
		for(Player p : Bukkit.getServer().getOnlinePlayers())
		{
			p.setFoodLevel(20);
			p.setHealth(20.0D);
			p.setMaxHealth(20.0D);
			p.setHealthScale(20.0D);
			p.setHealthScaled(true);
			
			if(Main.plugin.getConfig().getBoolean("SpeedBoostEnabled"))
			{
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999999, 1));
			}
			
			if(!doubleJumpCooldown.containsKey(p.getName()))
			{
				doubleJumpCooldown.put(p.getName(), Integer.valueOf(0));
			}
			if(!FeatureEvents.playersHidden.containsKey(p.getName()))
			{
				FeatureEvents.playersHidden.put(p.getName(), Boolean.valueOf(true));
				
				for(Player all : Bukkit.getServer().getOnlinePlayers())
				{
					p.hidePlayer(all);
				}
			}
			
			if(((Integer) doubleJumpCooldown.get(p.getName())).intValue() > 0)
			{
				doubleJumpCooldown.put(p.getName(), Integer.valueOf(((Integer) doubleJumpCooldown.get(p.getName())).intValue() - 1));
			}
		}
	}
}
