package com.RoyalNinja.ZeraniaHub.InfoBook;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import com.RoyalNinja.ZeraniaHub.Main;

public class BookHandler
{
	public static ItemStack getInfoBook()
	{
		FileConfiguration config = Main.plugin.getConfig();
		
		String author = config.getString("InfoBook.Author");
		String title = config.getString("InfoBook.Title");
		
		Integer totalPages = Integer.valueOf(config.getInt("InfoBook.Pages.Total"));
		
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta bm = (BookMeta) book.getItemMeta();
		bm.setAuthor(ChatColor.translateAlternateColorCodes('&', author));
		bm.setTitle(ChatColor.translateAlternateColorCodes('&', title));
		ArrayList<String> pages = new ArrayList();
		
		for(int i = 0; i < totalPages.intValue(); i++)
		{
			String page = config.getString("InfoBook.Pages." + i);
			
			pages.add(ChatColor.translateAlternateColorCodes('&', page));
		}
		
		bm.setPages(pages);
		
		book.setItemMeta(bm);
		
		return book;
	}
}
