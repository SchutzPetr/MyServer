package cz.Sicka_gp.MyServer.Configuration;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.Scoreboard.ScoreboardItemID;
import cz.Sicka_gp.MyServer.Scoreboard.ScoreboardSettings;
import cz.Sicka_gp.MyServer.utils.ScoreboardLength;

public class ConfigSettings {
	private static MyServer plugin;
	public static String Messages_FullServer;
	public static boolean Messages_DisableAllJoinQuitKickMSG;
	public static boolean Messages_UseDefaultMessage;
	public static boolean Messages_EnableTabList;
	public static boolean Messages_UseDefaultTabList;
	public static boolean Messages_UseDefaultTabListColor;
	public static String Messages_WhitelistMessage;
	public static boolean Messages_EnableJoinQuitKickMSG;
	public static boolean ChatMotd_Enable;
	public static List<String> ChatMotd_List;
	public static boolean PlayerList_Enable;
	public static boolean PersonalMOTD_Enable;
	public static boolean PersonalMOTD_Faces;
	public static String PersonalMOTD_Unknown;
	public static boolean ScoreboardMotd_Enable;
	public static int ScoreboardMotd_Interval;
	public static int HolographicMOTD_DisplayLength;
	public static int Scoreboard_DynamicTitleUpdateInterval;
	public static int Scoreboard_Update;
	public static boolean Scoreboard_Enable;
	public static int Scoreboard_DynamicUpdateInterval;
	public static boolean AutoMessage_Enable;
	public static long AutoMessage_Interval;
	public static String AutoMessage_Prefix;
	public static boolean FirstMessageEnable;
	public static boolean Kits;
	public static boolean Scoreboard_UseDefault;
	public static String Countdown_ShutdownTitle;
	public static String Countdown_ReloadTitle;
	public static String Countdown_CancelTitle;
	public static String Countdown_ShutdownBroadcastMSG;
	public static String Countdown_CountdownBroadcastMSG;
	public static String Countdown_ReloadBroadcastMSG;
	public static String Countdown_CancelBroadcastMSG;
	public static String Countdown_CountdownTitle;
	public static String Countdown_CountdownEndTitle;
	public static String Countdown_ShutdownEndBroadcastMSG;
	public static String Countdown_ReloadingBroadcastMSG;
	public static boolean CountDown_EnableSounds;
	public static String Countdown_CountdownEndBroadcastMSG;
	
	public static boolean Badwords_Enable;
	
	public ConfigSettings(MyServer instance){
		plugin = instance;
		
		initAll();
	}
	
	public static void initAll(){
		initMessages();
		initMotd();
		initNewbies();
		initAutoMessage();
		initScoreboard();
		initScoreboardCountDown();
		initBadwords();
	}
	
	public static void initMessages(){
		Messages_FullServer = getCustomMessageConfig().getString("Messages.FullServer", "&dServer is full!");
		Messages_WhitelistMessage = getCustomMessageConfig().getString("Messages.WhitelistMessage", "&dYou are not whitelisted on this server.");
		Messages_DisableAllJoinQuitKickMSG = getCustomMessageConfig().getBoolean("Messages.DisableAllJoinQuitKickMSG", false);
		Messages_EnableJoinQuitKickMSG = getCustomMessageConfig().getBoolean("Messages.EnableJoinQuitKickMSG", true);
		Messages_UseDefaultMessage = getCustomMessageConfig().getBoolean("Messages.UseDefaultMessage", false);
		Messages_EnableTabList = getCustomMessageConfig().getBoolean("Messages.EnableTabList", true);
		Messages_UseDefaultTabList = getCustomMessageConfig().getBoolean("Messages.UseDefaultTabList", false);
		Messages_UseDefaultTabListColor = getCustomMessageConfig().getBoolean("Messages.UseDefaultTabListColor", false);
		
	}
	
	public static void initNewbies(){
		FirstMessageEnable = getCustomMessageConfig().getBoolean("Newbies.FirstMessageEnable", true);
		Kits = getCustomMessageConfig().getBoolean("Newbies.Kits", false);
	}
	
	public static void initAutoMessage(){
		AutoMessage_Interval = getCustomMessageConfig().getLong("Automessage.Interval", 60);
		AutoMessage_Enable = getCustomMessageConfig().getBoolean("Automessage.Enable", true);
		AutoMessage_Prefix = getCustomMessageConfig().getString("Automessage.Prefix", "&f[&6ConfigurableMessage&f]");
	}
	
	public static void initMotd(){
		ChatMotd_Enable = getMOTDConfig().getBoolean("ChatMotd.Enable", false);
		ChatMotd_List = getMOTDConfig().getStringList("ChatMotd.Messages");
		
		PlayerList_Enable = getMOTDConfig().getBoolean("ServerListMotd.PlayerList.Enable", false);
		
		PersonalMOTD_Enable = getMOTDConfig().getBoolean("ServerListMotd.PersonalMOTD.Enable", false);
		PersonalMOTD_Faces = getMOTDConfig().getBoolean("ServerListMotd.PersonalMOTD.Faces", false);
		PersonalMOTD_Unknown = getMOTDConfig().getString("ServerListMotd.PersonalMOTD.Unknown", "&6Welcome Guest!{NEW_LINE} &9{SERVER_IP}");
		
		ScoreboardMotd_Enable = getMOTDConfig().getBoolean("ScoreboardMotd.Enable", true);
		ScoreboardMotd_Interval = getMOTDConfig().getInt("ScoreboardMotd.Interval", 4);
		HolographicMOTD_DisplayLength = getMOTDConfig().getInt("HolographicMOTD.DisplayLength", 15);
	}
	
	public static void initScoreboard(){
		Scoreboard_Enable = getScoreboardConfig().getBoolean("Scoreboard.Enable", true);
		Scoreboard_Update = getScoreboardConfig().getInt("Scoreboard.Update", 2);
		Scoreboard_DynamicTitleUpdateInterval = getConfig().getInt("Scoreboard.DynamicTitleUpdateInterval", 2);
		Scoreboard_UseDefault = getScoreboardConfig().getBoolean("Scoreboard.UseDefault", true);
		ScoreboardSettings.clearALL();
		for (String key : getScoreboardConfig().getConfigurationSection("Scoreboard.Scoreboards").getKeys(false)) {
			for(String score : getScoreboardConfig().getStringList("Scoreboard.Scoreboards." + key + ".Items")){
			    String[] scores = score.split(";", 2);
			    String scorename = scores[0];
			    String scorevaule = scores[1];
			    if(!(scorename.length() > 16)){
			        try{
			        	ScoreboardSettings.addToPermItems(key, scorename, ScoreboardItemID.ReplaceScoreboardItemToID(scorevaule));
			        }catch(Exception e){
			        	e.printStackTrace();
			        }
			    }
			}
			for(String title : getScoreboardConfig().getStringList("Scoreboard.Scoreboards." + key + ".DynamicTitle")){
				ScoreboardSettings.addToDynamicTitle(key, checkLength(title, 32));
			}
			for(String world : getScoreboardConfig().getStringList("Scoreboard.Scoreboards." + key + ".DisabledWorlds")){
				ScoreboardSettings.addToDisableWorld(key, world);
			}
		}
	}
	
	public static void initScoreboardCountDown(){
		CountDown_EnableSounds = getConfig().getBoolean("Countdown.EnableSounds", true);
		
		Countdown_ShutdownTitle = checkLength(getScoreboardConfig().getString("ScoreboardCountdown.ShutdownTitle", "Shutdown in {CD} minutes."), 31);
		Countdown_CountdownTitle = checkLength(getScoreboardConfig().getString("ScoreboardCountdown.CountdownTitle", "Timer: {CD} minutes."), 31);
		Countdown_ReloadTitle = checkLength(getScoreboardConfig().getString("ScoreboardCountdown.ReloadTitle", "Reload in {CD} minutes."), 31);
		Countdown_CancelTitle = checkLength(getScoreboardConfig().getString("ScoreboardCountdown.CancelTitle", "The countdown was cancelled."), 31);
		
		Countdown_ShutdownBroadcastMSG = getScoreboardConfig().getString("ScoreboardCountdown.ShutdownBroadcastMSG", "The Server will be shut down in {CD} minutes.");
		Countdown_CountdownBroadcastMSG = getScoreboardConfig().getString("ScoreboardCountdown.CountdownBroadcastMSG", "The countdown ends for {CD} minutes.");
		Countdown_ReloadBroadcastMSG = getScoreboardConfig().getString("ScoreboardCountdown.ReloadBroadcastMSG", "The Server will be reload in {CD} minutes.");
		Countdown_CancelBroadcastMSG = getScoreboardConfig().getString("ScoreboardCountdown.CancelBroadcastMSG", "The countdown was cancelled.");
		
		Countdown_ShutdownEndBroadcastMSG = getScoreboardConfig().getString("ScoreboardCountdown.ShutdownEndBroadcastMSG", "[MyServer] The Server is shut down!");
		Countdown_CountdownEndBroadcastMSG = getScoreboardConfig().getString("ScoreboardCountdown.CountdownEndBroadcastMSG", "[MyServer] Countdown complete!");
		Countdown_ReloadingBroadcastMSG = getScoreboardConfig().getString("ScoreboardCountdown.ReloadingBroadcastMSG", "[MyServer] Reloading....");
		Countdown_CountdownEndTitle = getScoreboardConfig().getString("ScoreboardCountdown.CountdownEndTitle", "Countdown complete {CD}");
	}
	
	public static void initBadwords(){
		Badwords_Enable = getBadwords().getBoolean("Enable", false);
	}
	
	
	private static FileConfiguration getBadwords() {
		return plugin.getConfigManager().getBadwordsConfig().getConfig();
	}

	private static String checkLength(String string, int i) {
		return ScoreboardLength.checkLength(string, i);
	}
	
	private static FileConfiguration getConfig() {
		return plugin.getConfigManager().getConfig().getConfig();
	}
	
	private static FileConfiguration getCustomMessageConfig() {
		return plugin.getConfigManager().getCustomMessageConfig().getConfig();
	}
	
	private static FileConfiguration getScoreboardConfig() {
		return plugin.getConfigManager().getScoreboardConfig().getConfig();
	}
	
	private static FileConfiguration getMOTDConfig() {
		return plugin.getConfigManager().getMOTDConfig().getConfig();
	}
}
