package com.RoyalNinja.ZeraniaHub.HubBalancer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import com.RoyalNinja.ZeraniaHub.Main;

public class BalancerHandler
{
	public static int getIndexOfMin(List<Integer> data)
	{
		int min = ((Integer) data.get(0)).intValue();
		
		for(Integer i : data)
		{
			if(i.intValue() < min)
			{
				min = i.intValue();
			}
		}
		return min;
	}
	
	public static String getBalancedHub()
	{
		List<String> hubs = Main.plugin.getConfig().getStringList("HubBalancer.Hubs");
		
		HashMap<Integer, String> lowestHub = new HashMap();
		
		ArrayList<Integer> hubPops = new ArrayList();
		
		for(String s : hubs)
		{
			FileConfiguration config = Main.plugin.getConfig();
			
			String ip = config.getString("HubBalancer." + s + ".Ip");
			String bungee = config.getString("HubBalancer." + s + ".BungeeName");
			
			Integer port = Integer.valueOf(config.getInt("HubBalancer." + s + ".Port"));
			
			hubPops.add(getServerPopulation(ip, port));
			lowestHub.put(getServerPopulation(ip, port), bungee);
		}
		
		int minIndex = getIndexOfMin(hubPops);
		
		return (String) lowestHub.get(Integer.valueOf(minIndex));
	}
	
	public static Integer getServerPopulation(String ip, Integer port)
	{
		try
		{
			Socket socket = new Socket();
			socket.connect(new InetSocketAddress(ip, port.intValue()), 1000);
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			
			out.write(254);
			
			StringBuilder str = new StringBuilder();
			
			int b;
			while((b = in.read()) != -1)
			{
				if((b != 0) && (b > 16) && (b != 255) && (b != 23) && (b != 24))
				{
					str.append((char) b);
				}
			}
			
			String[] data = str.toString().split("§");
			
			String motd = data[0];
			int onlinePlayers = Integer.valueOf(data[1]).intValue();
			int maxPlayers = Integer.valueOf(data[2]).intValue();
			
			return Integer.valueOf(onlinePlayers);
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
