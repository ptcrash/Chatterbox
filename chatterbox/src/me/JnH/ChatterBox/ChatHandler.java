package me.JnH.ChatterBox;

import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;

public class ChatHandler extends PlayerListener {
	
	public final Main plugin;
	
	public ChatHandler(Main plugin) {
		this.plugin = plugin;
	}
	public void onPlayerChat(PlayerChatEvent e){
		plugin.toConsole(e.getPlayer().getName()+" has chatted", 1);
	}
}
