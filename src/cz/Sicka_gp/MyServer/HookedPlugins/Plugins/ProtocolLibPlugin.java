package cz.Sicka_gp.MyServer.HookedPlugins.Plugins;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.HookedPlugins.PluginsManager;
import cz.Sicka_gp.MyServer.HookedPlugins.Plugins.Managers.ProtocolLibManager;

public class ProtocolLibPlugin {
	private static MyServer plugin;
	private static boolean isProtocolLib;
	private ProtocolLibManager plibmanager;


	public ProtocolLibPlugin(MyServer instance) {
		plugin = instance;
		CheckProtocolLib();
	}
	
	private void CheckProtocolLib(){
		final PluginManager pm = Bukkit.getServer().getPluginManager();
    	Plugin ProtocolLibpl = pm.getPlugin("ProtocolLib");
    	
    	if(ProtocolLibpl != null && ProtocolLibpl.isEnabled()){
    		isProtocolLib = pm.getPlugin("ProtocolLib") != null;
    		plibmanager = new ProtocolLibManager(plugin);
    		PluginsManager.PluginList.add(ProtocolLibpl);
    	}
	}
	
	public boolean isProtocolLib() {
		return isProtocolLib;
	}
	
	public ProtocolLibManager getProtocolLibManager(){
		return plibmanager;
	}
	

}
