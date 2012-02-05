package com.jobud9.Chatterbox;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigHandler{
	private YamlConfiguration config;
	private HashMap<String, Object> configDefaults = new HashMap<String, Object>();
	public ConfigHandler(File ConfigFile){
		this.config = new YamlConfiguration();
		
		this.configDefaults.put("general.stats", true);
		
		this.configDefaults.put("webserver.port", 80);
		this.configDefaults.put("webserver.apiport", 25573);
		this.configDefaults.put("webserver.debug_mode", false);
		
		this.configDefaults.put("database.type", "mysql");
		this.configDefaults.put("database.user", "database_username");
		this.configDefaults.put("database.pass", "database_password");
		this.configDefaults.put("database.db_uri", "jdbc:mysql:DOMAIN.COM/DATABASE");

		
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
