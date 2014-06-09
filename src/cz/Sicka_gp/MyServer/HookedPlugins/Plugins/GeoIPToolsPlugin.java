package cz.Sicka_gp.MyServer.HookedPlugins.Plugins;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import uk.org.whoami.geoip.GeoIPLookup;
import uk.org.whoami.geoip.GeoIPTools;

import cz.Sicka_gp.MyServer.MyServer;

public class GeoIPToolsPlugin {
	private static MyServer plugin;
	private static boolean geoip;
	private static GeoIPLookup geo;
	
	public GeoIPToolsPlugin(MyServer instance){
		plugin = instance;
		CheckGeoIPTools();
	}
	
	private static void CheckGeoIPTools(){
    	final PluginManager pm = Bukkit.getServer().getPluginManager();
    	Plugin geopl = pm.getPlugin("GeoIPTools");
    	
    	if(geopl != null && geopl.isEnabled()){
        	setGeoIPLookup();
        	plugin.getPluginsManager().getPluginList().add(geopl);
    	}
    }
	
	public static void setGeoIPLookup() {
    	try{
          Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("GeoIPTools");

          if (plugin != null){
        	  geo = ((GeoIPTools)plugin).getGeoIPLookup();
          }
        }
        catch (NullPointerException e) {
        	//TODO: Logs
        	//ConfigurableMessages.log.warning(MessagesList.GeoIPTools_Load_ex + e.getMessage());
        	//ConfigurableMessages.log.warning(MessagesList.ContactDevelopers);
        }
    }

	public boolean isGeoIPTools() {
		return geoip;
	}
	
	public GeoIPLookup getGeoIPLookup(){
		return geo;
	}

}
