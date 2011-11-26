package me.jobud9andhammale.ChatterBox;

import java.io.File;
import java.util.HashMap;
import org.bukkit.util.config.Configuration;


public class ChatterConfig {
	
	private Configuration config;
	private HashMap<String, Object> ConfigDefaults = new HashMap<String, Object>();
	
	public ChatterConfig(File ConfigFile) {
		this.config = new Configuration(ConfigFile);
		
		this.ConfigDefaults.put("port", 5000);
		this.ConfigDefaults.put("refreshrate", "10 #time in seconts");
		
		if(ConfigFile.exists() == false) {
			System.out.println("[ChatterBox] creating config file");
			for (String key : this.ConfigDefaults.keySet()) {
				this.config.setProperty(key, this.ConfigDefaults.get(key));
			}
			this.config.save();
		}
		else{
			this.config.load();
		}
		
		}
	public int getInt(String key) {
			if (this.ConfigDefaults.containsKey(key) == false) {
			return 0;
		}
		else{
			return this.config.getInt(key, Integer.parseInt(this.ConfigDefaults.get(key).toString()));
			}
	}

}
