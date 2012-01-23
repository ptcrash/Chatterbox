package me.JnH.ChatterBox;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigHandler{
	private YamlConfiguration config;
	private HashMap<String, Object> configDefaults = new HashMap<String, Object>();
	public ConfigHandler(File ConfigFile){
		this.config = new YamlConfiguration();
		
		this.configDefaults.put("webserver.port", 80);
		this.configDefaults.put("webserver.apiport", 25573);
		this.configDefaults.put("webserver.debug_mode", false);
		this.configDefaults.put("General.stats", true);
		
		if(ConfigFile.exists()== false){
			for(String key: this.configDefaults.keySet()){
			  this.config.set(key, this.configDefaults.get(key));
			}
			try {
				this.config.save(ConfigFile);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			try {
				this.config.load(ConfigFile);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getInt(String key){
		return this.config.getInt(key, (Integer)     this.configDefaults.get(key));
	}
	public boolean getBool(String key){
		return this.config.getBoolean(key, (Boolean) this.configDefaults.get(key));
	}
}
