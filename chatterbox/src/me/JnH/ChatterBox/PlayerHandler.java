package me.JnH.ChatterBox;

import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerHandler extends PlayerListener {
	public final Main plugin;
	public PlayerHandler(Main plugin) {this.plugin = plugin;}

	public void onPlayerChat(PlayerChatEvent event){
		String player = event.getPlayer().getDisplayName();
		plugin.toConsole(player+" just chatted!", 1);
	}
	public void onPlayerJoin(PlayerJoinEvent event){
		String player = event.getPlayer().getDisplayName();
		plugin.toConsole(player+" just joined!", 1);
	}
	public void onPlayerQuit(PlayerQuitEvent event){
		String player = event.getPlayer().getDisplayName();
		plugin.toConsole(player+" just quite!", 1);
	}
}
