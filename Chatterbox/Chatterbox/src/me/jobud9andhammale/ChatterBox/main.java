package me.jobud9andhammale.ChatterBox;

import java.io.File;
import java.util.logging.Logger;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
	public String PluginDirPath;
	public File ConfigFile;
	final ChatHandler playerListener = new ChatHandler(this);
	public void toConsole(String msg, int type) {
		Logger log = Logger.getLogger("Minecraft");
		switch(type) {
		case 1:log.info   ("[Wampom] "+msg); break;
		case 2:log.warning("[Wampom] "+msg); break;
		case 3:log.severe ("[Wampom] "+msg); break;
		}
	}
	  
	   	public void onEnable(){	
	   		this.PluginDirPath = this.getDataFolder().getAbsolutePath();
	   		this.ConfigFile    = new File(this.PluginDirPath + File.separator + "config.yml");
	        PluginManager pm = this.getServer().getPluginManager();
	        pm.registerEvent(Event.Type.PLAYER_CHAT, playerListener, Event.Priority.Normal, this);
	        
	        this.toConsole("Enabled without error!", 1);
	    }

		public void onDisable() {
			
		}
}	