package cz.Sicka_gp.MyServer.Scoreboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.Sicka_gp.MyServer.MyServer;

public class ScoreboardSettings {
	protected MyServer plugin;
	private static List<String> dtitle;
	private static List<String> dworld;
	private static Map<String, Byte> items;
	private static Map<String, List<String>> disableworlds = new HashMap<String, List<String>>();
	private static Map<String, List<String>> DynamicTitle = new HashMap<String, List<String>>();
	private static Map<String, Map<String, Byte>> PermItems = new HashMap<String, Map<String, Byte>>();
	
	public ScoreboardSettings(MyServer instance){
		plugin = instance;
	}

	public static void addToPermItems(String key1, String key2, Byte value) {
		items = PermItems.get(key1);
        if (items == null) {
        	items = new HashMap<String, Byte>(14);
        	PermItems.put(key1, items);
        }
        items.put(key2, value);
    }	
	
	public static Map<String, Byte> getItems(String key){
		items = PermItems.get(key);
		if(items == null){
			return null;
		}
		return items;
		
	}
	
	public static void addToDynamicTitle(String key, String title){
		dtitle = DynamicTitle.get(key);
		if(dtitle == null){
			dtitle =  new ArrayList<String>();
			DynamicTitle.put(key, dtitle);
		}
		dtitle.add(title);
	}
	
	public static List<String> getDynamicTitle(String key){
		dtitle = DynamicTitle.get(key);
		if(dtitle == null){
			return null;
		}
		return dtitle;
	}
	
	public static void addToDisableWorld(String key, String world){
		dworld = disableworlds.get(key);
		if(dworld == null){
			dworld =  new ArrayList<String>();
			disableworlds.put(key, dworld);
		}
		dworld.add(world);
	}
	
	public static List<String> getDisableWorld(String key){
		dworld = disableworlds.get(key);
		if(dworld == null){
			return null;
		}
		return dworld;
	}

	public static void clearALL() {
		disableworlds.clear();
		DynamicTitle.clear();
		PermItems.clear();
	}

	public static Map<String, List<String>> getDisableworlds() {
		return disableworlds;
	}

	public static Map<String, List<String>> getDynamicTitle() {
		return DynamicTitle;
	}

	public static Map<String, Map<String, Byte>> getPermItems() {
		return PermItems;
	}
}
