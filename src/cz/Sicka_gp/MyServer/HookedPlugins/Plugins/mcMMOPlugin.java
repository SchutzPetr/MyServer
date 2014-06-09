package cz.Sicka_gp.MyServer.HookedPlugins.Plugins;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import com.gmail.nossr50.api.ExperienceAPI;

import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.HookedPlugins.PluginsManager;

public class mcMMOPlugin {
	protected static MyServer plugin;
	private static boolean mcmmo;
	private ExperienceAPI exapi;
	
	public mcMMOPlugin(MyServer instance){
		plugin = instance;
		CheckMcmmo();
	}
	
	private static void CheckMcmmo(){
    	final PluginManager pm = Bukkit.getServer().getPluginManager();
    	Plugin mcmmopl = pm.getPlugin("mcMMO");
    	
    	if(mcmmopl != null && mcmmopl.isEnabled()){
    		mcmmo = pm.getPlugin("mcMMO") != null;
    		PluginsManager.PluginList.add(mcmmopl);
    	}
	}

	public boolean isMcmmo() {
		return mcmmo;
	}
	
	public ExperienceAPI getExperienceAPI(){
		return exapi;
	}

}
