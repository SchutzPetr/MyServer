package cz.Sicka_gp.MyServer.HookedPlugins.Plugins;

import nl.lolmewn.stats.api.StatsAPI;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;

import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.HookedPlugins.PluginsManager;

public class StatsPlugin {
	private static StatsAPI api;
	protected static MyServer plugin;
	private static boolean isStats;
	
	public StatsPlugin(MyServer instance){
		plugin = instance;
		CheckStats();
	}

	private static void CheckStats(){
    	final PluginManager pm = Bukkit.getServer().getPluginManager();
    	Plugin stats = pm.getPlugin("Stats");
    	
    	if(stats != null && stats.isEnabled()){
    		setupStatsAPI();
    		isStats = pm.getPlugin("Stats") != null;
    		PluginsManager.PluginList.add(stats);
    	}
	}
	
	private static boolean setupStatsAPI(){
	    RegisteredServiceProvider<StatsAPI> stats = Bukkit.getServer().getServicesManager().getRegistration(StatsAPI.class);
	    if(stats == null){
	    	return false;
	    }
	    api = stats.getProvider();
	    return api != null;
	}

	public boolean isStats() {
		return isStats;
	}
	
	public StatsAPI getStatsAPI(){
		return api;
	}
}
