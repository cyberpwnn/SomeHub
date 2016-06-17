package com.RoyalNinja.ZeraniaHub.NPC;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.RoyalNinja.ZeraniaHub.Main;
import com.mojang.authlib.GameProfile;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.PlayerInteractManager;
import net.minecraft.server.v1_8_R3.WorldServer;

public class NPCHandler
{
	static ArrayList<EntityPlayer> npcList = new ArrayList<EntityPlayer>();
	
	public static EntityPlayer getNPC(String name, String uuid)
	{
		MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
		WorldServer world = ((CraftWorld) Bukkit.getServer().getWorlds().get(0)).getHandle();
		
		return new EntityPlayer(server, world, new GameProfile(UUID.fromString(uuid), name), new PlayerInteractManager(world));
	}
	
	public static void createAllNPCS()
	{
		FileConfiguration config = Main.plugin.getConfig();
		
		List<String> allNpcs = config.getStringList("NPC.NPCList");
		
		for(String npcs : allNpcs)
		{
			Location npcLocation = new Location(Bukkit.getWorld(config.getString("NPC." + npcs + ".Location.World")), config.getInt("NPC." + npcs + ".Location.X"), config.getInt("NPC." + npcs + ".Location.Y"), config.getInt("NPC." + npcs + ".Location.Z"));
			
			EntityPlayer npc = getNPC(config.getString("NPC." + npcs + ".Name"), config.getString("NPC." + npcs + ".UUID"));
			
			config.set("NPC." + npcs + ".ID", Integer.valueOf(npc.getId()));
			Main.plugin.saveConfig();
			
			npc.setLocation(npcLocation.getX(), npcLocation.getY(), npcLocation.getZ(), 0.0F, 0.0F);
			
			npcList.add(npc);
		}
	}
	
	public static void updateAllNPCPackets(Player p)
	{
		if(npcList.size() <= 0)
		{
			return;
		}
		
		for(EntityPlayer npcs : npcList)
		{
			PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, new EntityPlayer[] { npcs }));
			connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npcs));
		}
	}
}
