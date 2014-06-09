package cz.Sicka_gp.MyServer.API;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import cz.Sicka_gp.MyServer.Scoreboard.ScoreboardItemsIDReplacer;
import cz.Sicka_gp.MyServer.Scoreboard.ScoreboardSettings;
import cz.Sicka_gp.MyServer.utils.PlayerGroup;
import cz.Sicka_gp.MyServer.utils.Replacer;
 
public class ScoreBoardAPI{
	protected static MyServer plugin;
	private static Map<String, List<Player>> PData = new HashMap<String, List<Player>>();
	private static List<Player> playerlist;
	
    public ScoreBoardAPI(MyServer instance){
        plugin = instance;
    }
    
    public static void AddPlayerToPlayerData(Player p){
    	String key = PlayerGroup.getPlayerGroup(p);
    	playerlist = PData.get(key);
		if(playerlist == null){
			playerlist =  new ArrayList<Player>();
			PData.put(key, playerlist);
		}
		if(playerlist.contains(p)){
			return;
		}
		playerlist.add(p);
	}
	
	public static List<Player> getPlayerData(Player p){
		playerlist = PData.get(PlayerGroup.getPlayerGroup(p));
		if(playerlist == null){
			return null;
		}
		return playerlist;
	}
     
    public static void setSidebarANDPlayerData(Player p, String obj, String title, DisplaySlot slot){
    	ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective(obj, "dummy");
        objective.setDisplaySlot(slot);
        objective.setDisplayName(Replacer.replaceSidebarName(title, p));
        AddPlayerToPlayerData(p);
        p.setScoreboard(board);
    }
    
    public static void setSidebar(Player p, String obj, String title, DisplaySlot slot){
    	ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective(obj, "dummy");
        objective.setDisplaySlot(slot);
        objective.setDisplayName(Replacer.replaceSidebarName(title, p));
        AddPlayerToPlayerData(p);
        p.setScoreboard(board);
    }
    
    public static void RemoveSidebarFromSlot(Player p, DisplaySlot slot){
    	p.getScoreboard().clearSlot(slot);
    }
    
    public static void ClearPlData(){
    	PData.clear();
    	playerlist.clear();
    }
    
    public static void UpdateSidebarTitle(Player p, DisplaySlot slot, String title){
    	Objective objective = p.getScoreboard().getObjective(slot);
    	objective.setDisplayName(Replacer.replaceSidebarName(title, p));
    }
    
    public static void UpdateScore(Player p, DisplaySlot slot, String scorename, int scorevalue){
    	Objective objective = p.getScoreboard().getObjective(slot);
        scorename = Replacer.replaceColor(scorename);
        Score score = objective.getScore(scorename);
        score.setScore(scorevalue);
    }
    
    public static void UpdateScoreByID(Player p, DisplaySlot slot, String scorename, byte scorevalue){
    	Objective objective = p.getScoreboard().getObjective(slot);
        scorename = Replacer.replaceColor(scorename);
        Score score = objective.getScore(scorename);
        score.setScore(ScoreboardItemsIDReplacer.getReplacedInt(scorevalue, p));
    }
    
    public static void UpdateScoreForALL(DisplaySlot slot, String scorename, int scorevalue){
    	scorename = ChatColor.translateAlternateColorCodes("&".charAt(0), scorename);
    	for(Player p : Bukkit.getOnlinePlayers()){
    		Objective objective = p.getScoreboard().getObjective(slot);
    		Score score = objective.getScore(scorename);
            score.setScore(scorevalue);
    	}
    }
    
    public static void UpdateDefaultScoreForALL(String objname){
    	for(String key : ScoreboardSettings.getPermItems().keySet()){
    		Map<String, Byte> map = ScoreboardSettings.getItems(key);
    		if(map.isEmpty() || !PData.containsKey(key))continue;
    		for(String str : map.keySet()){
    			SendUpdateScore(objname, key, str, map.get(str));
    		}
    		
    	}
    }

	private static void SendUpdateScore(String objname, String key, String sname, Byte id) {
		sname = ChatColor.translateAlternateColorCodes("&".charAt(0), sname);
    	for(Player p : PData.get(key)){
    		Objective objective = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);
    		if(objective == null){
    			continue;
    		}else if(objective.getName().endsWith(objname)){
    			Score score = objective.getScore(sname);
        		score.setScore(ScoreboardItemsIDReplacer.getReplacedInt(id, p));
    		}
    	}
	}
    
}