package cz.Sicka_gp.MyServer.utils;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.gmail.filoghost.holograms.api.Hologram;
import com.gmail.filoghost.holograms.api.HolographicDisplaysAPI;

import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.Motd.HolographicDisplaysMOTD;
import cz.Sicka_gp.MyServer.utils.HolographicSettings;

public class HolographicDisplaysManager {
	protected static MyServer plugin;
	
	private static Map<Player, Map<String, Hologram>> HData = new HashMap<Player, Map<String, Hologram>>();
	private static Map<String, Hologram> HologramData = new HashMap<String, Hologram>();
	
	public HolographicDisplaysManager(MyServer instance){
		plugin = instance;
		new HolographicSettings(plugin);
		new HolographicDisplaysMOTD(plugin);
	}
	
	public static void onDisablePlugin(){
		Hologram[] hol = HolographicDisplaysAPI.getHolograms(plugin);
		for(Hologram h : hol){
			h.delete();
		}
	}
	
	public static void CreateHologram(Player p, String name, String title){
		if(getHData().containsKey(p) && getHolographicPlayerData(p).containsKey(name)){
			DeleteHologram(p, name);
		}
		Hologram h = HolographicDisplaysAPI.createHologram(plugin, p.getEyeLocation(), title);
		addToHolographicPlayerData(p, name, h);
	}
	
	public static void DeleteHologram(Player p, String name){
		Hologram h = getHologram(p, name);
		h.delete();
	}
	
	public static void DeleteHologramAndPlayerFromData(Player p, String name){
		Hologram h = getHologram(p, name);
		h.delete();
		HData.remove(p);
	}
	
	public static void DeleteAllPlayerHologram(Player p){
		if(getHolographicPlayerData(p) == null)return;
		for(String name : getHolographicPlayerData(p).keySet()){
			Hologram h = getHologram(p, name);
			h.delete();
		}
		HData.remove(p);
	}
	
	public static void HideHologram(Player p, String name){
		Hologram h = getHologram(p, name);
		h.hide();
	}
	
	public static void AddLineHologram(Player p, String name, String text){
		Hologram h = getHologram(p, name);
		h.addLine(text);
	}
	
	public static void ClearLinesHologram(Player p, String name){
		Hologram h = getHologram(p, name);
		h.clearLines();
	}
	
	public static void RemoveLineHologram(Player p, String name, int line){
		Hologram h = getHologram(p, name);
		h.removeLine(line);
	}
	
	public static void InsertLineHologram(Player p, String name, int line, String text){
		Hologram h = getHologram(p, name);
		h.insertLine(line, text);
	}
	
	public static void SetLineHologram(Player p, String name, int line, String text){
		Hologram h = getHologram(p, name);
		h.setLine(line, text);
	}
	
	public static void UpdateHologram(Player p, String name){
		Hologram h = getHologram(p, name);
		h.update();
	}
	
	public static void SetLocationHologram(Player p, String name, Location location){
		Hologram h = getHologram(p, name);
		h.setLocation(location);
	}
	
	public static void addToHolographicPlayerData(Player p, String name, Hologram hol){
		HologramData = HData.get(p);
		if(HologramData == null){
			HologramData =  new HashMap<String, Hologram>();
			HData.put(p, HologramData);
		}
		HologramData.put(name, hol);
	}
	
	public static Hologram getHologram(Player p, String name){
		return getHolographicPlayerData(p).get(name);
	}
	
	public static Map<String, Hologram> getHolographicPlayerData(Player p){
		HologramData = HData.get(p);
		if(HologramData == null){
			return null;
		}
		return HologramData;
	}

	public static Map<Player, Map<String, Hologram>> getHData() {
		return HData;
	}
}
