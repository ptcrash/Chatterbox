package me.jobud9andhammale.ChatterBox;

import java.io.File;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
	   public String PluginDirpath;
	   public File configfile;
	   public ChatterConfig config;
	   public main(ChatterConfig instance){
		 this.config = instance;
	   }
	   webserver_starter webserver_starter = new webserver_starter();
	   final ChatterPlayerListener playerListener = new ChatterPlayerListener(this);
	   		public void onEnable() {
	   			
		    this.PluginDirpath = this.getDataFolder().getAbsolutePath();
		    this.configfile = new File(this.PluginDirpath + File.separator + "config" + File.separator + "config.yml");
		    this.config = new ChatterConfig(this.configfile);
		    
	        System.out.println("ChatterBox Enabled!");
	        PluginManager pm = this.getServer().getPluginManager();
	        pm.registerEvent(Event.Type.PLAYER_CHAT, playerListener, Event.Priority.Normal, this);
	    }
	   	   
	    public void onDisable() {
	        System.out.println("ChatterBox Disabled!");
	    }

}	
