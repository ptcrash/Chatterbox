package me.JnH.ChatterBox;

import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

public class ChatHandler extends PlayerListener {
	
	public final Main plugin;
	
	public ChatHandler(Main plugin) {
		this.plugin = plugin;
	}
	public void onPlayerChat(PlayerChatEvent e){
		plugin.toConsole(e.getPlayer().getName()+" has chatted", 1);
	}
	public void onPlayerJoin(PlayerJoinEvent e){
		plugin.toConsole(e.getPlayer().getName()+" has joined", 1);
	}
	public void onPlayerQuit(PlayerQuitEvent e){
		plugin.toConsole(e.getPlayer().getName()+" has left", 1);
	}
}
