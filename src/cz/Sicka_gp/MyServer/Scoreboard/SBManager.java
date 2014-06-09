package cz.Sicka_gp.MyServer.Scoreboard;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.Permissions;
import cz.Sicka_gp.MyServer.Configuration.ConfigSettings;
import cz.Sicka_gp.MyServer.utils.PlayerGroup;
import cz.Sicka_gp.MyServer.utils.Replacer;


public class SBManager {
	protected static MyServer plugin;
	private static boolean Scoreboard_Enable;
	private static String DisplayName = "&eLoading...";
	
	public SBManager(MyServer instance){
		plugin = instance;
		Scoreboard_Enable = ConfigSettings.Scoreboard_Enable;
	}
	
	public static void HideScoreboard(Player p){
    	p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
    	ScoreboardPlayerData.AddPlayerToHideList(p);
    }
	
	public static void ShowScoreboard(Player p){
    	p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
    	ScoreboardPlayerData.AddPlayerToHideList(p);
    }
    
    public static void RemoveScoreboard(Player p){
    	p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
    }
    
    public static void RemoveScoreboardAndPlayerData(Player p){
    	p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
    	ScoreboardPlayerData.RemovePlayerFromPlayerData(p);
    }
    
    public static boolean isSidebar() {
		return Scoreboard_Enable;
	}
	
	public static void UpdateScore(Player p){
    	String key = PlayerGroup.getPlayerGroup(p);
    	Map<String, Byte> s = ScoreboardSettings.getItems(key);
    	if(s.isEmpty() || !ScoreboardSettings.getPermItems().containsKey(key))return;
		for(String str : s.keySet()){
			SendUpdateScore(p, "sicka_gp", str, s.get(str));
		}
    }
    
    public static void UpdateScoreForAll(){
    	for(String key : ScoreboardSettings.getPermItems().keySet()){
    		Map<String, Byte> map = ScoreboardSettings.getItems(key);
    		if(map.isEmpty() || !ScoreboardPlayerData.getPlayerData().containsKey(key))continue;
    		for(String str : map.keySet()){
    			SendUpdateScore(key, str, map.get(str));
    		}
    		
    	}
    }
    
    public static void SendUpdateScore(Player p, String sbobj, String sname, byte id){
    	sname = ChatColor.translateAlternateColorCodes("&".charAt(0), sname);
    	Objective objective = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);
		if(objective == null){
			Sidebar(p);
			UpdateScore(p);
			return;
		}else if(objective.getName().endsWith(sbobj)){
			Score score = objective.getScore(sname);
    		score.setScore(ScoreboardItemsIDReplacer.getReplacedInt(id, p));
		}
    }
    
    public static void SendUpdateScore(String key, String sname, byte id){
    	sname = ChatColor.translateAlternateColorCodes("&".charAt(0), sname);
    	for(Player p : ScoreboardPlayerData.getPlayerData().get(key)){
    		if(!p.isOnline() || p == null){
    			ScoreboardPlayerData.getPlayerData().get(key).remove(p);
    			continue;
    		}
    		else if(ScoreboardPlayerData.getHidePlayers().contains(p)){
				continue;
			}
    		Objective objective = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);
    		if(objective == null){
    			Sidebar(p);
    			UpdateScore(p);
    			continue;
    		}else if(objective.getName().endsWith("sicka_gp")){
    			Score score = objective.getScore(sname);
        		score.setScore(ScoreboardItemsIDReplacer.getReplacedInt(id, p));
    		}
    	}
    }
    
    public static void UpdateScoreBoardTitle(Player p){
    	Objective objective = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);
    	objective.setDisplayName(Replacer.replaceSidebarName(DisplayName, p));
    }
    
    public static void UpdateScoreBoardTitleForALL(){
    	for(String key : ScoreboardSettings.getDynamicTitle().keySet()){
    		if(!ScoreboardPlayerData.getPlayerData().containsKey(key))continue;
    		int i = (int) (Math.random() * ScoreboardSettings.getDynamicTitle(key).size());
    		String random = ScoreboardSettings.getDynamicTitle(key).get(i);
    		for(Player p : ScoreboardPlayerData.getPlayerData().get(key)){
    			if(!p.isOnline() || p == null){
    				ScoreboardPlayerData.getPlayerData().get(key).remove(p);
        			continue;
        		}
    			else if(ScoreboardPlayerData.getHidePlayers().contains(p)){
    				continue;
    			}
    			Objective objective = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);
    			if(objective == null){
    				Sidebar(p);
        			continue;
    			}else if(objective.getName().endsWith("sicka_gp")){
    				objective.setDisplayName(Replacer.replaceSidebarName(random, p));
    			}
    		}
    	}
    }

    public static void Sidebar(Player p){
    	if((Scoreboard_Enable)&& p.hasPermission(Permissions.sidebar)){
    		ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard board = manager.getNewScoreboard();
            Objective objective = board.registerNewObjective("sicka_gp", "sicka_vaule");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.setDisplayName(Replacer.replaceSidebarName(DisplayName, p));
            ScoreboardPlayerData.AddPlayerToPlayerData(p);
            p.setScoreboard(board);
    	}
    }

}
