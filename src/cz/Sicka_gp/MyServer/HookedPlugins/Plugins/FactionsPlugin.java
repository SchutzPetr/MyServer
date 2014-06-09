package cz.Sicka_gp.MyServer.HookedPlugins.Plugins;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.UPlayer;

import cz.Sicka_gp.MyServer.MyServer;

public class FactionsPlugin {
	private static boolean factions;
	private static MyServer plugin;
	private UPlayer uplayer;
	private Faction faction;

	public FactionsPlugin(MyServer instance){
		plugin = instance;
		CheckuFactions();
	}
	
	private static void CheckuFactions(){
    	final PluginManager pm = Bukkit.getServer().getPluginManager();
    	Plugin Factionspl = pm.getPlugin("Factions");
    	
    	if(Factionspl != null && Factionspl.isEnabled()){
    		factions = pm.getPlugin("Factions") != null;
			plugin.getPluginsManager().getPluginList().add(Factionspl);
    	}
    }

	public boolean isFactions() {
		return factions;
	}

	public UPlayer getUplayer(Player p) {
		uplayer = UPlayer.get(p);
		return uplayer;
	}

	public Faction getFaction(Player p) {
		faction = getUplayer(p).getFaction();
		return faction;
	}

}
