package com.RoyalNinja.ZeraniaHub;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.RoyalNinja.ZeraniaHub.Cosmetics.CosmeticsEvents;
import com.RoyalNinja.ZeraniaHub.Cosmetics.TrailsThread;
import com.RoyalNinja.ZeraniaHub.HotBar.HotBarEvents;
import com.RoyalNinja.ZeraniaHub.HubFeatures.FeatureEvents;
import com.RoyalNinja.ZeraniaHub.HubFeatures.FeatureThread;
import com.RoyalNinja.ZeraniaHub.HubFeatures.GrapplingHook;
import com.RoyalNinja.ZeraniaHub.NPC.NPCEvents;
import com.RoyalNinja.ZeraniaHub.NPC.NPCHandler;
import com.RoyalNinja.ZeraniaHub.PlayerStacker.PlayerStackerEvents;
import com.RoyalNinja.ZeraniaHub.Portals.PortalEvents;
import com.RoyalNinja.ZeraniaHub.ServerSelector.ServerSelectorEvents;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements PluginMessageListener
{
	public static Plugin plugin;
	public static int FeatureTimer;
	public static int TrailsTimer;
	
	public void onEnable()
	{
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
		
		FeatureTimer = getServer().getScheduler().scheduleSyncRepeatingTask(this, new FeatureThread(), 20L, 20L);
		TrailsTimer = getServer().getScheduler().scheduleSyncRepeatingTask(this, new TrailsThread(), 1L, 1L);
		
		plugin = this;
		
		//Config moved to method.
		bsConfig();
		
		NPCHandler.createAllNPCS();
		Bukkit.getServer().getPluginManager().registerEvents(new FeatureEvents(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ServerSelectorEvents(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PortalEvents(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerStackerEvents(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new CosmeticsEvents(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new NPCEvents(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new GrapplingHook(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new HotBarEvents(), this);
	}
	
	public void onPluginMessageReceived(String channel, Player player, byte[] message)
	{
		if(!channel.equals("BungeeCord"))
		{
			return;
		}
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		String subchannel = in.readUTF();
		subchannel.equals("PlayerCount");
	}
	
	public static WorldGuardPlugin getWorldGuard()
	{
		Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
		
		if((plugin == null) || (!(plugin instanceof WorldGuardPlugin)))
		{
			return null;
		}
		
		return (WorldGuardPlugin) plugin;
	}
	
	public static WorldEditPlugin getWorldEdit()
	{
		Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
		
		if((plugin == null) || (!(plugin instanceof WorldEditPlugin)))
		{
			return null;
		}
		
		return (WorldEditPlugin) plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		return false;
	}
	
	public void bsConfig()
	{
		getConfig().addDefault("SpeedBoostEnabled", Boolean.valueOf(true));
		getConfig().addDefault("DoubleJump.Upward", Integer.valueOf(5));
		getConfig().addDefault("DoubleJump.Forward", Integer.valueOf(2));
		getConfig().addDefault("PlayerVisibility.ItemID", Integer.valueOf(2));
		getConfig().addDefault("PlayerVisibility.UseMessageOn", "&bPlayers are now visible again!");
		getConfig().addDefault("PlayerVisibility.UseMessageOff", "&cPlayers are no longer visible!");
		ArrayList<String> serverItems = new ArrayList<String>();
		ArrayList<String> GUILore = new ArrayList<String>();
		ArrayList<String> lockedLore = new ArrayList<String>();
		ArrayList<String> unlockedLore = new ArrayList<String>();
		ArrayList<String> inuseLore = new ArrayList<String>();
		ArrayList<String> NPCList = new ArrayList<String>();
		ArrayList<String> hubs = new ArrayList<String>();
		GUILore.add("&bClick to join the Example Server");
		serverItems.add("ExampleServer");
		getConfig().addDefault("PlayerStackerThrowStrength", Double.valueOf(3.0D));
		getConfig().addDefault("ServerSelector.ItemID", "345");
		getConfig().addDefault("ServerSelector.Items.ExampleServer.BungeeName", "lobby");
		getConfig().addDefault("ServerSelector.Items.ExampleServer.ItemID", "24");
		getConfig().addDefault("ServerSelector.Items.ExampleServer.Name", "&cExample Server");
		getConfig().addDefault("ServerSelector.Items.ExampleServer.Lore", GUILore);
		getConfig().addDefault("ServerSelector.Items.Filler.ItemID", "2");
		getConfig().addDefault("ServerSelector.Items.Filler.Name", "Filler");
		getConfig().addDefault("ServerSelector.Inventory.Size", Integer.valueOf(9));
		getConfig().addDefault("ServerSelector.Inventory.Arrangement.1", "Filler");
		getConfig().addDefault("ServerSelector.Inventory.Arrangement.2", "ExampleServer");
		getConfig().addDefault("ServerSelector.Inventory.Arrangement.3", "Filler");
		getConfig().addDefault("ServerSelector.Inventory.Arrangement.4", "ExampleServer");
		getConfig().addDefault("ServerSelector.Inventory.Arrangement.5", "Filler");
		getConfig().addDefault("ServerSelector.Inventory.Arrangement.6", "ExampleServer");
		getConfig().addDefault("ServerSelector.Inventory.Arrangement.7", "Filler");
		getConfig().addDefault("ServerSelector.Inventory.Arrangement.8", "ExampleServer");
		getConfig().addDefault("ServerSelector.Inventory.Arrangement.9", "Filler");
		getConfig().addDefault("Trails.GUI.LockedItem.ID", "351");
		getConfig().addDefault("Trails.GUI.UnlockedItem.ID", "352");
		getConfig().addDefault("Trails.GUI.InuseItem.ID", "353");
		getConfig().addDefault("Trails.GUI.LockedItem.Lore", lockedLore);
		getConfig().addDefault("Trails.GUI.UnlockedItem.Lore", unlockedLore);
		getConfig().addDefault("Trails.GUI.InuseItem.Lore", inuseLore);
		NPCList.add("ExampleNPC");
		getConfig().addDefault("NPC.HubWorld", "world");
		getConfig().addDefault("NPC.NPCList", NPCList);
		getConfig().addDefault("NPC.ExampleNPC.Name", "Example Server");
		getConfig().addDefault("NPC.ExampleNPC.UUID", UUID.randomUUID().toString());
		getConfig().addDefault("NPC.ExampleNPC.Command", "server lobby");
		getConfig().addDefault("NPC.ExampleNPC.Location.World", "world");
		getConfig().addDefault("NPC.ExampleNPC.Location.X", Double.valueOf(145.0D));
		getConfig().addDefault("NPC.ExampleNPC.Location.Y", Double.valueOf(63.0D));
		getConfig().addDefault("NPC.ExampleNPC.Location.Z", Double.valueOf(310.0D));
		hubs.add("Lobby");
		getConfig().addDefault("HubBalancer.Hubs", hubs);
		getConfig().addDefault("HubBalancer.lobby.BungeeName", "lobby");
		getConfig().addDefault("HubBalancer.lobby.Ip", "localhost");
		getConfig().addDefault("HubBalancer.lobby.Port", Integer.valueOf(25565));
		getConfig().addDefault("InfoBook.Author", "Server");
		getConfig().addDefault("InfoBook.Title", "&cInfo &bBook");
		getConfig().addDefault("InfoBook.Pages.Total", Integer.valueOf(5));
		getConfig().addDefault("InfoBook.Pages.0", "&eThis is the first page");
		getConfig().addDefault("InfoBook.Pages.1", "&cThis is the second page");
		getConfig().addDefault("InfoBook.Pages.2", "&bThis is the third page");
		getConfig().addDefault("InfoBook.Pages.3", "&7This is the fourth page");
		getConfig().addDefault("InfoBook.Pages.4", "&dThis is the fifth page");
		ArrayList<String> visiLore = new ArrayList<String>();
		ArrayList<String> serverLore = new ArrayList<String>();
		ArrayList<String> cosmeticsLore = new ArrayList<String>();
		ArrayList<String> hookLore = new ArrayList<String>();
		visiLore.add("&cUse to toggle player visibility");
		serverLore.add("&cUse to select a server to connect to");
		cosmeticsLore.add("&cUse to open the Cosmetics Selection GUI");
		hookLore.add("&cGrappling hook, hook yourself to a location then pull to reel");
		getConfig().addDefault("HotBar.Items.PlayerVisibility.ItemID", "347");
		getConfig().addDefault("HotBar.Items.PlayerVisibility.Name", "&bPlayer Visibility");
		getConfig().addDefault("HotBar.Items.PlayerVisibility.Lore", visiLore);
		getConfig().addDefault("HotBar.Items.ServerSelector.Name", "&bServer Selector");
		getConfig().addDefault("HotBar.Items.ServerSelector.Lore", serverLore);
		getConfig().addDefault("HotBar.Items.Cosmetics.ItemID", "377");
		getConfig().addDefault("HotBar.Items.Cosmetics.Name", "&bCosmetics");
		getConfig().addDefault("HotBar.Items.Cosmetics.Lore", cosmeticsLore);
		getConfig().addDefault("HotBar.Items.GrapplingHook.Name", "&bGrappling Hook");
		getConfig().addDefault("HotBar.Items.GrapplingHook.Lore", hookLore);
		getConfig().addDefault("HotBar.Items.Filler.ItemID", "102");
		getConfig().addDefault("HotBar.Items.Filler.Name", "");
		getConfig().addDefault("HotBar.Arrangement.1", "Filler");
		getConfig().addDefault("HotBar.Arrangement.2", "ServerSelector");
		getConfig().addDefault("HotBar.Arrangement.3", "Filler");
		getConfig().addDefault("HotBar.Arrangement.4", "PlayerVisibility");
		getConfig().addDefault("HotBar.Arrangement.5", "Filler");
		getConfig().addDefault("HotBar.Arrangement.6", "Cosmetics");
		getConfig().addDefault("HotBar.Arrangement.7", "Filler");
		getConfig().addDefault("HotBar.Arrangement.8", "GrapplingHook");
		getConfig().addDefault("HotBar.Arrangement.9", "Filler");
		ArrayList<String> trailLore = new ArrayList<String>();
		ArrayList<String> customLore = new ArrayList<String>();
		trailLore.add(ChatColor.RED + "Click to open the Trail Selection GUI");
		getConfig().addDefault("Cosmetics.Items.Trails.ItemID", "377");
		getConfig().addDefault("Cosmetics.Items.Trails.Name", "&cTrails");
		getConfig().addDefault("Cosmetics.Items.Trails.Lore", trailLore);
		getConfig().addDefault("Cosmetics.Items.Filler.ItemID", "102");
		getConfig().addDefault("Cosmetics.Items.Filler.Name", "&cFiller");
		ArrayList<String> customItems = new ArrayList<String>();
		customItems.add("CustomItem");
		getConfig().addDefault("Cosmetics.Items.CustomItems", customItems);
		getConfig().addDefault("Cosmetics.Items.CustomItem.ItemID", "4");
		getConfig().addDefault("Cosmetics.Items.CustomItem.Name", "&cExample Custom Item");
		getConfig().addDefault("Cosmetics.Items.CustomItem.Lore", customLore);
		getConfig().addDefault("Cosmetics.Items.CustomItem.Command", "say hi %player%");
		getConfig().addDefault("Cosmetics.Size", Integer.valueOf(18));
		getConfig().addDefault("Cosmetics.Arrangement.1", "Filler");
		getConfig().addDefault("Cosmetics.Arrangement.2", "Trails");
		getConfig().addDefault("Cosmetics.Arrangement.3", "Filler");
		getConfig().addDefault("Cosmetics.Arrangement.4", "CustomItem");
		getConfig().addDefault("Cosmetics.Arrangement.5", "Filler");
		getConfig().addDefault("Cosmetics.Arrangement.6", "Filler");
		getConfig().addDefault("Cosmetics.Arrangement.7", "Filler");
		getConfig().addDefault("Cosmetics.Arrangement.8", "Filler");
		getConfig().addDefault("Cosmetics.Arrangement.9", "Filler");
		getConfig().addDefault("Cosmetics.Arrangement.10", "Filler");
		getConfig().addDefault("Cosmetics.Arrangement.11", "Filler");
		getConfig().addDefault("Cosmetics.Arrangement.12", "Filler");
		getConfig().addDefault("Cosmetics.Arrangement.13", "Filler");
		getConfig().addDefault("Cosmetics.Arrangement.14", "Filler");
		getConfig().addDefault("Cosmetics.Arrangement.15", "Filler");
		getConfig().addDefault("Cosmetics.Arrangement.16", "Filler");
		getConfig().addDefault("Cosmetics.Arrangement.17", "Filler");
		getConfig().addDefault("Cosmetics.Arrangement.18", "Filler");
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
}
