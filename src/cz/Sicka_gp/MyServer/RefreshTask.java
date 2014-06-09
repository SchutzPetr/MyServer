package cz.Sicka_gp.MyServer;

import java.util.logging.Level;

import org.bukkit.Bukkit;

import cz.Sicka_gp.MyServer.Configuration.ConfigSettings;
import cz.Sicka_gp.MyServer.Scoreboard.SBManager;
import cz.Sicka_gp.MyServer.Scoreboard.ScoreboardPlayerData;
import cz.Sicka_gp.MyServer.utils.AnsiColor;
import cz.Sicka_gp.MyServer.utils.ColouredConsoleSender;
import cz.Sicka_gp.MyServer.utils.NewMessageList;

public class RefreshTask {
	private static long RefreshInterval;
	private static MyServer plugin;
	private static int refreshtaskIDScoreboard;
	private static int Scoreboard_DynamicTitleUpdateInterval;


	public RefreshTask(MyServer instance){
		plugin = instance;
		RefreshInterval = ConfigSettings.Scoreboard_Update;
		Scoreboard_DynamicTitleUpdateInterval = ConfigSettings.Scoreboard_DynamicTitleUpdateInterval;
		MyServerScoreboardRefreshTask();
	}
	
	
	public void MyServerScoreboardRefreshTask(){
		refreshtaskIDScoreboard = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
    		
    		private int number = 0;

			@Override
			public void run(){
				if(!ScoreboardPlayerData.getPlayerData().isEmpty()){
					SBManager.UpdateScoreForAll();
					if(number == 5){
						ScoreboardPlayerData.ReloadPlayersDataForAllII();
						number++;
					}
					else if(number == Scoreboard_DynamicTitleUpdateInterval){
	    				number = 0;
	    				SBManager.UpdateScoreBoardTitleForALL();
	    			}else{
	    				number++;
	    			}
				}
			}
		}, 0L, RefreshInterval*20L);
		
	}
	
	public static void StopScoreboardRefreshTask(){
		plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.GREEN, NewMessageList.StoppedRefreshTask));
    	plugin.getServer().getScheduler().cancelTask(refreshtaskIDScoreboard);
    }
}
