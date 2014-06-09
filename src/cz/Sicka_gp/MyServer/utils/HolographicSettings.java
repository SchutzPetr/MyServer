package cz.Sicka_gp.MyServer.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.Sicka_gp.MyServer.MyServer;

public class HolographicSettings {
	private static List<String> Messages = new ArrayList<String>();
	private static Map<String, List<String>> HolographicMessages = new HashMap<String, List<String>>();
	
	private static Map<String, String> title = new HashMap<String, String>();
	
	private static Map<String, Integer> Location = new HashMap<String, Integer>();
	private static Map<String, Map<String, Integer>> Holographicloc = new HashMap<String, Map<String, Integer>>();
	
	private static MyServer plugin;
	
	private static int DisplayLength;
	
	public HolographicSettings(MyServer instance){
		plugin = instance;
		init();
	}
	
	public static void init(){
		HolographicMessages.clear();
		Messages.clear();
		Location.clear();
		Holographicloc.clear();
		HolographicMessages.clear();
		
		for (String key : plugin.getConfigManager().getMOTDConfig().getConfig().getConfigurationSection("HolographicMOTD.Holograms").getKeys(false)){
			addToHolographicTitle(key, plugin.getConfigManager().getMOTDConfig().getConfig().getString("HolographicMOTD.Holograms." + key + ".Title"));
			addToHolographicLocation(key, "x", plugin.getConfigManager().getMOTDConfig().getConfig().getInt("HolographicMOTD.Holograms." + key + ".Location.x", 0));
			addToHolographicLocation(key, "y", plugin.getConfigManager().getMOTDConfig().getConfig().getInt("HolographicMOTD.Holograms." + key + ".Location.y", 0));
			addToHolographicLocation(key, "z", plugin.getConfigManager().getMOTDConfig().getConfig().getInt("HolographicMOTD.Holograms." + key + ".Location.z", 0));
			for(String msg : plugin.getConfigManager().getMOTDConfig().getConfig().getStringList("HolographicMOTD.Holograms." + key + ".Messages")){
				addToHolographicMessages(key, msg);
			}
		}
	}
	
	public static void addToHolographicLocation(String key, String lockey, int loc){
		Location = Holographicloc.get(key);
		if(Location == null){
			Location =  new HashMap<String, Integer>();
			Holographicloc.put(key, Location);
		}
		Location.put(lockey, loc);
	}
	
	public static Map<String, Integer> getHolographicLocation(String key){
		Location = Holographicloc.get(key);
		if(Location == null){
			return null;
		}
		return Location;
	}
	
	public static void addToHolographicMessages(String key, String msg){
		Messages = HolographicMessages.get(key);
		if(Messages == null){
			Messages =  new ArrayList<String>();
			HolographicMessages.put(key, Messages);
		}
		Messages.add(msg);
	}
	
	public static List<String> getHolographicMessages(String key){
		Messages = HolographicMessages.get(key);
		if(Messages == null){
			return null;
		}
		return Messages;
	}
	
	public static void addToHolographicTitle(String key, String tit){
		if(title == null){
			title =  new HashMap<String, String>();
		}
		title.put(key, tit);
	}
	
	public static String getHolographicTitle(String key){
		if(title == null){
			return null;
		}
		return title.get(key);
	}

	public static Map<String, Map<String, Integer>> getHolographicLocationMAP() {
		return Holographicloc;
	}
	
	public static Map<String, List<String>> getHolographicMessagesMAP() {
		return HolographicMessages;
	}

	public static int getDisplayLength() {
		return DisplayLength;
	}
}
