package cz.Sicka_gp.MyServer.HookedPlugins.Plugins;

import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import net.sacredlabyrinth.phaed.simpleclans.managers.ClanManager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import cz.Sicka_gp.MyServer.MyServer;

public class SimpleClansPlugin {
	private static MyServer plugin;
	private static ClanManager clanManager;
	private static boolean isclan;

	public SimpleClansPlugin(MyServer instance){
		plugin = instance;
		CheckSimpleclans();
	}
	
	private static void CheckSimpleclans(){
    	final PluginManager pm = Bukkit.getServer().getPluginManager();
    	Plugin simpleclanspl = pm.getPlugin("SimpleClans");
    	
    	if(simpleclanspl != null && simpleclanspl.isEnabled()){
    		clanManager = SimpleClans.getInstance().getClanManager();
    		plugin.getPluginsManager().getPluginList().add(simpleclanspl);
    		isclan = pm.getPlugin("SimpleClans") != null;
    	}
    }

	public ClanManager getClanManager() {
		return clanManager;
	}

	public boolean isIsclan() {
		return isclan;
	}

}
