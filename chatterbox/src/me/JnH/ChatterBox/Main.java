package me.JnH.ChatterBox;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public String PluginDirPath = this.getDataFolder().getAbsolutePath()+File.separator;
	public File ConfigFile  = new File(this.PluginDirPath + File.separator + "config.yml");
	public ConfigHandler config = new ConfigHandler(this.ConfigFile);
	public PluginDescriptionFile pdfile = this.getDescription();
	public CommandHandler cmdHandler = new CommandHandler(this);
	public PlayerHandler playerListener = new PlayerHandler(this);
	public Thread t1 = new Thread(new FileServer(this));
	public Thread t2 = new Thread(new CommandServer(this));
	public void toConsole(String msg, int type) {
		Logger log = Logger.getLogger("Minecraft");
		switch (type) {
		case 1:
			log.info("["+this.pdfile.getName()+"] " + msg);
			break;
		case 2:
			log.warning("["+this.pdfile.getName()+"] " + msg);
			break;
		case 3:
			log.severe("["+this.pdfile.getName()+"] " + msg);
			break;
		}
	}
	public void onEnable() {
		t1.start();
		t2.start();
		
		getCommand("basic").setExecutor(cmdHandler);
		
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_CHAT, playerListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_QUIT, playerListener, Event.Priority.Normal, this);
	}

	public void onDisable() {
		this.toConsole("Disabled", 1);
	}
}