package cz.Sicka_gp.MyServer.HookedPlugins;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.Plugin;

import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.HookedPlugins.Plugins.FactionsPlugin;
import cz.Sicka_gp.MyServer.HookedPlugins.Plugins.GeoIPToolsPlugin;
import cz.Sicka_gp.MyServer.HookedPlugins.Plugins.HeroesPlugin;
import cz.Sicka_gp.MyServer.HookedPlugins.Plugins.HolographicDisplaysPlugin;
import cz.Sicka_gp.MyServer.HookedPlugins.Plugins.ProtocolLibPlugin;
import cz.Sicka_gp.MyServer.HookedPlugins.Plugins.PvpStatsPlugin;
import cz.Sicka_gp.MyServer.HookedPlugins.Plugins.SimpleClansPlugin;
import cz.Sicka_gp.MyServer.HookedPlugins.Plugins.StatsPlugin;
import cz.Sicka_gp.MyServer.HookedPlugins.Plugins.VaultPlugin;
import cz.Sicka_gp.MyServer.HookedPlugins.Plugins.mcMMOPlugin;

public class PluginsManager {
	private MyServer plugin;
	public static List<Plugin> PluginList;
	private VaultPlugin vault;
	private mcMMOPlugin mcmmo;;
	private HeroesPlugin heroes;
	private StatsPlugin stats;
	private FactionsPlugin factions;
	private PvpStatsPlugin pvpstats;
	private SimpleClansPlugin simple;
	private ProtocolLibPlugin plib;
	private GeoIPToolsPlugin geotools;
	private HolographicDisplaysPlugin hdp;
	
	
	public PluginsManager(MyServer instance){
		plugin = instance;
		PluginList = new ArrayList<Plugin>();
		vault = new VaultPlugin(plugin);
		mcmmo = new mcMMOPlugin(plugin);
		heroes = new HeroesPlugin(plugin);
		stats = new StatsPlugin(plugin);
		factions = new FactionsPlugin(plugin);
		pvpstats = new PvpStatsPlugin(plugin);
		simple = new SimpleClansPlugin(plugin);
		plib = new ProtocolLibPlugin(plugin);
		geotools = new GeoIPToolsPlugin(plugin);
		hdp = new HolographicDisplaysPlugin(plugin);
		
	}
	
	public List<Plugin> getPluginList() {
		return PluginList;
	}
	
	public VaultPlugin getVault(){
		return vault;
	}
	
	public mcMMOPlugin getmcMMO(){
		return mcmmo;
	}
	
	public StatsPlugin getStats(){
		return stats;
	}

	public HeroesPlugin getHeroes() {
		return heroes;
	}

	public FactionsPlugin getFactions() {
		return factions;
	}

	public PvpStatsPlugin getPvPStats() {
		return pvpstats;
	}
	
	public SimpleClansPlugin getSimpleClans(){
		return simple;
	}

	public ProtocolLibPlugin getProtocolLib() {
		return plib;
	}
	
	public GeoIPToolsPlugin getGeoIPTools() {
		return geotools;
	}
	
	public HolographicDisplaysPlugin getHolographicDisplays(){
		return hdp;
	}

}
