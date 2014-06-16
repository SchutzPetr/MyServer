package cz.Sicka_gp.MyServer.Configuration;

import java.util.ArrayList;
import java.util.List;
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
import cz.Sicka_gp.MyServer.utils.MessageList;

public class ConfigurationManager {
	private MyServer plugin;
	private Config config;
	private static Lang lang;
	private CustomMessageConfig cmc;
	private BadwordsConfig badw;
	private HolographicDisplaysConfig hdc; 
	private ScoreboardConfig sc;
	private MOTDConfig motdc;
	private MessageList msglist;
	private static List<String> outconf;
	
	public ConfigurationManager(MyServer instance){
		plugin = instance;
		plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.GOLD, "[MyServer] Loading language..."));
		initLang();
		plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.GOLD, "[MyServer] Initialization language..."));
		msglist = new MessageList(plugin);
		plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.YELLOW, MessageList.LoadingConfigs));
		initConfig();
		initCustomMessageConfig();
		initBadwordsConfig();
		//initHolographicDisplaysConfig();
		initScoreboardConfig();
		initMOTDConfig();
		
		plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.GREEN, MessageList.ConfigsLoaded));
	}
	
	public void SaveConfigs(){
		plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.YELLOW, MessageList.SavingConfigs));
		getConfig().saveConfig();
		getLang().saveConfig();
		getCustomMessageConfig().saveConfig();
		getBadwordsConfig().saveConfig();
		//getHolographicDisplaysConfig().saveConfig();
		getScoreboardConfig().saveConfig();
		getMOTDConfig().saveConfig();
		plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.GREEN, MessageList.ConfigsSaved));
	}
	
	public void ReloadConfigs(){
		getConfig().reloadConfig();
		getLang().reloadConfig();
		getCustomMessageConfig().reloadConfig();
		getBadwordsConfig().reloadConfig();
		//getHolographicDisplaysConfig().reloadConfig();
		getScoreboardConfig().reloadConfig();
		getMOTDConfig().reloadConfig();
	}
	
	public void CheckConfigVersion(){
		int a = getConfig().getConfig().getInt("Config_version");
		int b = getLang().getConfig().getInt("Config_version");
		int c = getCustomMessageConfig().getConfig().getInt("Config_version");
		int d = getBadwordsConfig().getConfig().getInt("Config_version");
		int e = getHolographicDisplaysConfig().getConfig().getInt("Config_version");
		int f = getScoreboardConfig().getConfig().getInt("Config_version");
		int g = getMOTDConfig().getConfig().getInt("Config_version");
		if(a != 1){
			if(outconf == null){
				outconf = new ArrayList<String>();
			}
			outconf.add(getConfig().configfile.getName());
		}
		if(b != 1){
			if(outconf == null){
				outconf = new ArrayList<String>();
			}
			outconf.add(getLang().configfile.getName());
		}
		if(c != 1){
			if(outconf == null){
				outconf = new ArrayList<String>();
			}
			outconf.add(getCustomMessageConfig().configfile.getName());
		}
		if(d != 1){
			if(outconf == null){
				outconf = new ArrayList<String>();
			}
			outconf.add(getBadwordsConfig().configfile.getName());
		}
		if(e != 1){
			if(outconf == null){
				outconf = new ArrayList<String>();
			}
			outconf.add(getHolographicDisplaysConfig().configfile.getName());
		}
		if(f != 1){
			if(outconf == null){
				outconf = new ArrayList<String>();
			}
			outconf.add(getScoreboardConfig().configfile.getName());
		}
		if(g != 1){
			if(outconf == null){
				outconf = new ArrayList<String>();
			}
			outconf.add(getMOTDConfig().configfile.getName());
		}
		if(outconf != null){
			plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.RED, MessageList.ConfigOutdated));
			plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.RED, "Configs: " + ConfigStatus()));
			plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.DARK_RED, MessageList.DisablingPlugin));
			plugin.setDisableMyServer(true);
		}
	}
	
	public static String ConfigStatus(){
		StringBuilder configs = new StringBuilder();
		for(String conf : outconf){
			if(configs.length() > 0){
				configs.append(", ");
			}
			configs.append(conf);
		}
		return configs.toString();
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
	
	@SuppressWarnings("unused")
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

	public MessageList getMessageList() {
		return msglist;
	}
	

}
