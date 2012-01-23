package me.JnH.ChatterBox;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.randomappdev.pluginstats.Ping;

public class Main extends JavaPlugin {
    public String PluginDirPath;
    public File ConfigFile;
    public ConfigHandler config;
    public PluginDescriptionFile pdfile;
    final ChatHandler playerListener = new ChatHandler(this);
    public Thread t1 = new Thread(new FileServer(this));
    public Thread t2 = new Thread(new CommandServer(this));

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
        this.config = new ConfigHandler(this.ConfigFile);

        t1.start();
        t2.start();
        
        Ping ping = new Ping();
        ping.init(this);
        
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvent(Event.Type.PLAYER_CHAT, playerListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_QUIT, playerListener, Event.Priority.Normal, this);
        this.toConsole("Enabled!", 1);
    }
    
    public void onDisable() {
    	this.toConsole("Disabled", 1);
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    if (cmd.getName().equalsIgnoreCase("cb")){
		    if(args.length != 0){
		        if(args[0].matches("help")){
		        	sender.sendMessage(ChatColor.GREEN+"[required param] {optional param}");
			        sender.sendMessage(ChatColor.GREEN+" Command                   Action");
			        sender.sendMessage(ChatColor.BLUE +" /about                    Shows plugin version");
			        sender.sendMessage(ChatColor.BLUE +" /register [user] [pass]   Registers your chaterbox account");
		        }
		        else if(args[0].matches("about")){
		        	sender.sendMessage(ChatColor.GREEN + this.pdfile.getName()+" version "+this.pdfile.getVersion());
		        }
		        else if(args[0].matches("register")){
		        	if(args.length == 1){
		        		
		        	}
		        	else if(args.length != 3){
		        		
		        	}
		        	else{
		        		
		        	}
		        }
		        return true;
		    }
	    	else{
	    		sender.sendMessage(ChatColor.GREEN + this.pdfile.getName()+" version "+this.pdfile.getVersion());
	    		return true;
	    	}

	    }
	    return false;
    }

    
    
}