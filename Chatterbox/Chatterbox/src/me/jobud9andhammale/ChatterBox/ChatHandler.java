package me.jobud9andhammale.ChatterBox;

import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;

public class ChatHandler extends PlayerListener {
	public final main plugin;
	public ChatHandler(main plugin) {
	  this.plugin = plugin;
	}
	public void onPlayerChat(PlayerChatEvent e){
		plugin.toConsole("you chatted", 1);
	}
}
