package me.jobud9andhammale.ChatterBox;

import java.io.File;
import java.util.logging.Logger;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
    public String PluginDirPath;
    public File ConfigFile;
    public configHandler config;
    public PluginDescriptionFile pdfile;
    final ChatHandler playerListener = new ChatHandler(this);

    public void toConsole(String msg, int type) {
        Logger log = Logger.getLogger("Minecraft");
        switch (type) {
        case 1:
            log.info("["+pdfile.getName()+"] " + msg);
            break;
        case 2:
            log.warning("["+pdfile.getName()+"] " + msg);
            break;
        case 3:
            log.severe("["+pdfile.getName()+"] " + msg);
            break;
        }
    }

    public void onEnable() {
    	this.pdfile = this.getDescription();
        this.PluginDirPath = this.getDataFolder().getAbsolutePath()+File.separator;
        this.ConfigFile = new File(this.PluginDirPath + File.separator + "config.yml");
        this.config = new configHandler(this.ConfigFile);
        
        Thread t1 = new Thread(new server(this));
        t1.start();
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvent(Event.Type.PLAYER_CHAT, playerListener, Event.Priority.Normal, this);
        
        if(this.config.getBool("webserver.debug_mode")==true){
        	this.toConsole("Enabled, but in debug mode! this may cause unwanted spaming of console", 2);
        }
        else{
        	this.toConsole("Enabled without error!", 1);
        }
    }
    public void onDisable() {
    	this.toConsole("Disabled", 1);
    }
    
    
}