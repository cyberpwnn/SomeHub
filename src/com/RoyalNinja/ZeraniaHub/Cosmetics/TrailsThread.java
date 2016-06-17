package com.RoyalNinja.ZeraniaHub.Cosmetics;

import org.bukkit.entity.Player;

import com.RoyalNinja.ZeraniaHub.ParticleLib.ParticleEffect;

public class TrailsThread implements Runnable
{
	public void run()
	{
		for(Player p : org.bukkit.Bukkit.getServer().getOnlinePlayers())
		{
			if(!CosmeticsHandler.trailsActive.containsKey(p.getName()))
			{
				CosmeticsHandler.trailsActive.put(p.getName(), "None");
			}
			
			String equipped = (String) CosmeticsHandler.trailsActive.get(p.getName());
			
			if(equipped.contains("Explode"))
			{
				ParticleEffect.EXPLOSION_LARGE.display(0.0F, 0.0F, 0.0F, 0.1F, 20, p.getLocation(), 1.0D);
			}
			
			if(equipped.contains("Firework"))
			{
				ParticleEffect.FIREWORKS_SPARK.display(0.0F, 0.0F, 0.0F, 0.1F, 20, p.getLocation(), 1.0D);
			}
			
			if(equipped.contains("Crit"))
			{
				ParticleEffect.CRIT_MAGIC.display(0.0F, 0.0F, 0.0F, 0.1F, 20, p.getLocation(), 1.0D);
			}
			
			if(equipped.contains("Smoke"))
			{
				ParticleEffect.SMOKE_NORMAL.display(0.0F, 0.0F, 0.0F, 0.1F, 20, p.getLocation(), 1.0D);
			}
			
			if(equipped.contains("Portal"))
			{
				ParticleEffect.PORTAL.display(0.0F, 0.0F, 0.0F, 0.1F, 20, p.getLocation(), 1.0D);
			}
			
			if(equipped.contains("Flame"))
			{
				ParticleEffect.FLAME.display(0.0F, 0.0F, 0.0F, 0.1F, 20, p.getLocation(), 1.0D);
			}
			
			if(equipped.contains("Cloud"))
			{
				ParticleEffect.CLOUD.display(0.0F, 0.0F, 0.0F, 0.1F, 20, p.getLocation(), 1.0D);
			}
			
			if(equipped.contains("Slime"))
			{
				ParticleEffect.SLIME.display(0.0F, 0.0F, 0.0F, 0.1F, 20, p.getLocation(), 1.0D);
			}
			
			if(equipped.contains("Heart"))
			{
				ParticleEffect.HEART.display(0.0F, 0.0F, 0.0F, 0.1F, 20, p.getLocation(), 1.0D);
			}
		}
	}
}
