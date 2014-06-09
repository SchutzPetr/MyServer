package cz.Sicka_gp.MyServer.Scoreboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

import cz.Sicka_gp.MyServer.utils.PlayerGroup;

public class ScoreboardPlayerData {
	private static Map<String, List<Player>> PlayerData = new HashMap<String,List<Player>>();
	private static List<Player> plist;
	private static List<Player> hide = new ArrayList<Player>();
	
	public static void ReloadPlayerData(String worldold, Player p){
    	RemovePlayerFromPlayerData(p, worldold);
    	AddPlayerToPlayerData(p);
    	
    }
    
    public static void ReloadPlayersDataForAllI(){
    	PlayerData.clear();
    	for(Player p : Bukkit.getOnlinePlayers()){
    		SBManager.Sidebar(p);
    	}
    	
    }
    
    public static void ReloadPlayersDataForAllII(){
    	PlayerData.clear();
    	for(Player p : Bukkit.getOnlinePlayers()){
    		AddPlayerToPlayerData(p);
    	}
    	
    }
    
    public static void CheckPlayerDisableWorlds(Player p, String worldold){
    	String key = PlayerGroup.getPlayerGroup(p);
    	List<String> worlds = ScoreboardSettings.getDisableWorld(key);
    	Objective objective = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);
    	if(worlds.contains(p.getWorld().getName()) && objective != null && objective.getName().endsWith("sicka_gp")){
    		SBManager.HideScoreboard(p);
		}else if(worlds.contains(worldold)){
			SBManager.Sidebar(p);
			hide.remove(p);
		}else{
			return;
		}
    	
    }
	
	public static void AddPlayerToPlayerData(String key, Player p){
		plist = PlayerData.get(key);
		if(plist == null){
			plist =  new ArrayList<Player>();
			PlayerData.put(key, plist);
		}
		if(plist.contains(p)){
			return;
		}
		plist.add(p);
	}
    
    public static void AddPlayerToPlayerData(Player p){
    	String key = PlayerGroup.getPlayerGroup(p);
		plist = PlayerData.get(key);
		if(plist == null){
			plist =  new ArrayList<Player>();
			PlayerData.put(key, plist);
		}
		if(plist.contains(p)){
			return;
		}
		plist.add(p);
	}
	
	public static List<Player> getPlayerData(Player p){
		plist = PlayerData.get(PlayerGroup.getPlayerGroup(p));
		if(plist == null){
			return null;
		}
		return plist;
	}
	
	public static List<Player> getPlayerData(String key){
		plist = PlayerData.get(key);
		if(plist == null){
			return null;
		}
		return plist;
	}
    
    public static void RemovePlayerFromPlayerData(Player p){
    	String key = PlayerGroup.getPlayerGroup(p);
		plist = PlayerData.get(key);
		if(plist == null)return;
		if(!plist.contains(p))return;
		plist.remove(p);
    }
    
    public static void RemovePlayerFromPlayerData(Player p, String key){
		plist = PlayerData.get(key);
		if(plist == null)return;
		if(!plist.contains(p))return;
		plist.remove(p);
    }
    
    public static boolean IfPlayerInPlayerData(Player p){
    	String key = PlayerGroup.getPlayerGroup(p);
    	plist = PlayerData.get(key);
		if(plist == null)return false;
		return plist.contains(p);
    }
    
    public static boolean IfPlayerInPlayerData(Player p, String group){
    	plist = PlayerData.get(group);
		if(plist == null)return false;
		return plist.contains(p);
    }

	public static Map<String, List<Player>> getPlayerData() {
		return PlayerData;
	}

	public static List<Player> getHidePlayers() {
		return hide;
	}
	
	public static void AddPlayerToHideList(Player p){
		hide.add(p);
	}
	
	public static void RemovePlayerFromHideList(Player p){
		hide.remove(p);
	}
	
	
}
