package cz.Sicka_gp.MyServer.Motd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.API.ScoreBoardAPI;
import cz.Sicka_gp.MyServer.Configuration.ConfigSettings;
import cz.Sicka_gp.MyServer.Scoreboard.ScoreboardItemID;
import cz.Sicka_gp.MyServer.utils.AnsiColor;
import cz.Sicka_gp.MyServer.utils.ColouredConsoleSender;
import cz.Sicka_gp.MyServer.utils.ScoreboardLength;

public class ScoreBoardMOTD {
	private static MyServer plugin;
	private static int ScoreBoardMotd_Interval;
	public final static Map<Player, Integer> task = new HashMap<Player, Integer>();
	public final static Map<Integer, String> list = new HashMap<Integer, String>();
	public final static Map<String, Byte> score = new HashMap<String, Byte>(15);
	
	public ScoreBoardMOTD(MyServer instance){
		plugin = instance;
		init();
	}
	
	public static void init(){
		List<String> key = plugin.getConfigManager().getMOTDConfig().getConfig().getStringList("ScoreBoardMotd.Messages");
		ScoreBoardMotd_Interval = ConfigSettings.ScoreboardMotd_Interval;
		int i = key.size();
		for(String k : key){
			k = ScoreboardLength.checkLength(k, 32);
			list.put(i, k);
			i--;
		}
		for(String sc : plugin.getConfigManager().getMOTDConfig().getConfig().getStringList("ScoreBoardMotd.ScoreBoardItems")){
		    String[] scores = sc.split(";", 2);
		    String scorename = scores[0];
		    String scorevaule = scores[1];
		    if(!(scorename.length() > 16)){
		        try{
		        	score.put(scorename, ScoreboardItemID.ReplaceScoreboardItemToID(scorevaule));
		        }catch(Exception e){
		        	e.printStackTrace();
		        }
		    }
		}
	}
	
	public static final void CancelTask(Player p){
		if(task.containsKey(p)){
			plugin.getServer().getScheduler().cancelTask(task.get(p));
			task.remove(p);
		}
	}
	
	private static void UpdateMotd(Player p, int i, int number, String title){
		if(number == i)ScoreBoardAPI.setSidebar(p, "motd", title, DisplaySlot.SIDEBAR);
		for(String key : score.keySet()){
			ScoreBoardAPI.UpdateScoreByID(p, DisplaySlot.SIDEBAR, key, score.get(key));
		}
		ScoreBoardAPI.UpdateSidebarTitle(p, DisplaySlot.SIDEBAR, title);
	}

	public static void SidebarMotdTask(final Player p, final int i){
		int taskid = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
			int number = i;
			@Override
			public void run() {
				if(number != -1) {
					  if(number !=0) {
						  UpdateMotd(p, i, number, list.get(number));
						  p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0F, p.getLocation().getPitch());
						  number--;
						  plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.RED, "aaa"));
					  }else{
						  ScoreBoardAPI.RemoveSidebarFromSlot(p, DisplaySlot.SIDEBAR);
						  p.playSound(p.getLocation(), Sound.LEVEL_UP, 4.0F, p.getLocation().getPitch());
						  number--;
						  CancelTask(p);
						  plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.RED, "oooo"));
					  }
				  }
			  }
			
		}, 0, ScoreBoardMotd_Interval *20);
		task.put(p, taskid);
	}
	
	public static void CreateMotdSidebar(final Player p){
		SidebarMotdTask(p, list.size());
	}

}
