package cz.Sicka_gp.MyServer.HookedPlugins.Plugins;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.HookedPlugins.PluginsManager;
import cz.Sicka_gp.MyServer.utils.HolographicDisplaysManager;

public class HolographicDisplaysPlugin {
	private static MyServer plugin;
	private static boolean isholdisplays;
	private static HolographicDisplaysManager cmhd;

	public HolographicDisplaysPlugin(MyServer instance){
		plugin = instance;
		CheckHolographicDisplays();
	}
	
	private static void CheckHolographicDisplays(){
    	final PluginManager pm = Bukkit.getServer().getPluginManager();
    	Plugin hpl = pm.getPlugin("HolographicDisplays");
    	
    	if(hpl != null && hpl.isEnabled()){
    		isholdisplays = pm.getPlugin("HolographicDisplays") != null;
    		cmhd = new HolographicDisplaysManager(plugin);
    		//plugin.getPluginsManager().getPluginList().add(hpl);
    		PluginsManager.PluginList.add(hpl);
    	}
	}

	public boolean isHolographicDisplays() {
		return isholdisplays;
	}

	public static HolographicDisplaysManager getHolographicDisplaysManager() {
		return cmhd;
	}

}
