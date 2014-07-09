package cz.Sicka_gp.MyServer.Configuration.Configs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.utils.AnsiColor;
import cz.Sicka_gp.MyServer.utils.ColouredConsoleSender;
import cz.Sicka_gp.MyServer.utils.MessageList;



public class HolographicDisplaysConfig {
	private FileConfiguration config = null;
	public File configfile = null;
	private MyServer plugin;
	
	public HolographicDisplaysConfig(MyServer instance){
		plugin = instance;
	}
	
	
	public void reloadConfig() {
	    if (configfile == null) {
	    configfile = new File(plugin.getDataFolder(), "holographicdisplays.yml");
	    }
	    config = YamlConfiguration.loadConfiguration(configfile);
	 
	    // Look for defaults in the jar
	    InputStream defConfigStream = plugin.getResource("holographicdisplays.yml");
	    if (defConfigStream != null) {
	    	YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream));
	        config.setDefaults(defConfig);
	    }
	}
	
	
	public FileConfiguration getConfig() {
	    if (config == null) {
	        reloadConfig();
	    }
	    return config;
	}
	
	public void saveConfig() {
	    if (config == null || configfile == null) {
	        return;
	    }
	    try {
	        getConfig().save(configfile);
	    } catch (IOException ex) {
	    	plugin.getLog().log(Level.SEVERE, ColouredConsoleSender.sendConsoleMessage(AnsiColor.RED, MessageList.CouldNotSaveConfig  + configfile), ex);
	    }
	}
	
	public void saveDefaultConfig() {
	    if (configfile == null) {
	        configfile = new File(plugin.getDataFolder(), "holographicdisplays.yml");
	    }
	    if (!configfile.exists()) {            
	         plugin.saveResource("holographicdisplays.yml", false);
	         plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.GREEN, MessageList.CreateConfigFile  + AnsiColor.GOLD + configfile.getName()));
	     }
	}
}
