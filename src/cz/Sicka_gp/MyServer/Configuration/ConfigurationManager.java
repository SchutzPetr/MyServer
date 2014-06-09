package cz.Sicka_gp.MyServer.Configuration;

import java.util.logging.Level;

import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.Configuration.Configs.BadwordsConfig;
import cz.Sicka_gp.MyServer.Configuration.Configs.Config;
import cz.Sicka_gp.MyServer.Configuration.Configs.CustomMessageConfig;
import cz.Sicka_gp.MyServer.Configuration.Configs.HolographicDisplaysConfig;
import cz.Sicka_gp.MyServer.Configuration.Configs.Lang;
import cz.Sicka_gp.MyServer.Configuration.Configs.MOTDConfig;
import cz.Sicka_gp.MyServer.Configuration.Configs.ScoreboardConfig;
import cz.Sicka_gp.MyServer.utils.AnsiColor;
import cz.Sicka_gp.MyServer.utils.ColouredConsoleSender;
import cz.Sicka_gp.MyServer.utils.NewMessageList;

public class ConfigurationManager {
	private MyServer plugin;
	private Config config;
	private static Lang lang;
	private CustomMessageConfig cmc;
	private BadwordsConfig badw;
	private HolographicDisplaysConfig hdc; 
	private ScoreboardConfig sc;
	private MOTDConfig motdc;
	private NewMessageList msglist;
	
	public ConfigurationManager(MyServer instance){
		plugin = instance;
		plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.GOLD, "[MyServer] Loading language..."));
		initLang();
		plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.GOLD, "[MyServer] Initialization language..."));
		msglist = new NewMessageList(plugin);
		plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.YELLOW, NewMessageList.LoadingConfigs));
		initConfig();
		initCustomMessageConfig();
		initBadwordsConfig();
		initHolographicDisplaysConfig();
		initScoreboardConfig();
		initMOTDConfig();
		
		plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.GREEN, NewMessageList.ConfigsLoaded));
	}
	
	public void SaveConfigs(){
		plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.YELLOW, NewMessageList.SavingConfigs));
		getConfig().saveConfig();
		getLang().saveConfig();
		getCustomMessageConfig().saveConfig();
		getBadwordsConfig().saveConfig();
		getHolographicDisplaysConfig().saveConfig();
		getScoreboardConfig().saveConfig();
		getMOTDConfig().saveConfig();
		plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.GREEN, NewMessageList.ConfigsSaved));
	}
	
	private void initConfig(){
		config = new Config(plugin);
		
		config.saveDefaultConfig();
		config.reloadConfig();
	}
	
	
	public Config getConfig(){
		return config;
	}
	
	private void initLang(){
		lang = new Lang(plugin);
		
		lang.saveDefaultConfig();
		lang.reloadConfig();
	}
	
	
	public static Lang getLang(){
		return lang;
	}
	
	private void initCustomMessageConfig(){
		cmc = new CustomMessageConfig(plugin);
		
		cmc.saveDefaultConfig();
		cmc.reloadConfig();
	}
	
	
	public CustomMessageConfig getCustomMessageConfig(){
		return cmc;
	}
	
	private void initBadwordsConfig(){
		badw = new BadwordsConfig(plugin);
		
		badw.saveDefaultConfig();
		badw.reloadConfig();
	}
	
	
	public BadwordsConfig getBadwordsConfig(){
		return badw;
	}
	
	private void initHolographicDisplaysConfig(){
		hdc = new HolographicDisplaysConfig(plugin);
		
		hdc.saveDefaultConfig();
		hdc.reloadConfig();
	}
	
	
	public HolographicDisplaysConfig getHolographicDisplaysConfig(){
		return hdc;
	}
	
	private void initScoreboardConfig(){
		sc = new ScoreboardConfig(plugin);
		
		sc.saveDefaultConfig();
		sc.reloadConfig();
	}
	
	
	public ScoreboardConfig getScoreboardConfig(){
		return sc;
	}
	
	private void initMOTDConfig(){
		motdc = new MOTDConfig(plugin);
		
		motdc.saveDefaultConfig();
		motdc.reloadConfig();
	}
	
	
	public MOTDConfig getMOTDConfig(){
		return motdc;
	}

	public NewMessageList getMessageList() {
		return msglist;
	}
	

}
