package com.jobud9.Chatterbox;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public File ConfigFile  = new File(this.getDataFolder() + File.separator + File.separator + "config.yml");
	public ConfigHandler config = new ConfigHandler(this.ConfigFile);
	public void onEnable() {
		PluginDescriptionFile pdfile = this.getDescription();
		CommandHandler cmdHandler = new CommandHandler(this);
		Thread t1 = new Thread(new FileServer(this));
		Thread t2 = new Thread(new CommandServer(this));
		
		t1.start();
		t2.start();
		
		getCommand("cb").setExecutor(cmdHandler);
	}
	public void onDisable() {}
	public void toConsole(String msg, int type) {
		Level level;
		if(type == 1){level = Level.INFO;}
		else if(type == 2){level = Level.WARNING;}
		else if(type == 3){level = Level.SEVERE;}
		else{level = Level.INFO;}
		Logger.getLogger("Minecraft").log(level,msg);
	}
	public void onPlayerChat(PlayerChatEvent event){
		String player = event.getPlayer().getDisplayName();
		this.toConsole(player+" just chatted!", 1);
	}
	public void onPlayerJoin(PlayerJoinEvent event){
		String player = event.getPlayer().getDisplayName();
		this.toConsole(player+" just joined!", 1);
	}
	public void onPlayerQuit(PlayerQuitEvent event){
		String player = event.getPlayer().getDisplayName();
		this.toConsole(player+" just quit!", 1);
	}
}