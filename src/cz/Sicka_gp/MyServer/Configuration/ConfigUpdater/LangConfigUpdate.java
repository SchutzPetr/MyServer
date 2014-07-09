package cz.Sicka_gp.MyServer.Configuration.ConfigUpdater;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.Configuration.ConfigurationManager;
import cz.Sicka_gp.MyServer.utils.AnsiColor;
import cz.Sicka_gp.MyServer.utils.ColouredConsoleSender;
import cz.Sicka_gp.MyServer.utils.MessageList;

public class LangConfigUpdate {
	protected static MyServer plugin;
	private static List<String> news = new ArrayList<String>();
	
	public LangConfigUpdate(MyServer instance){
		plugin = instance;
		
		initVaultMessage();
		initPluginMessage();
		iniConfigMessage();
		iniJQKMessage();
		iniScoreboard();
		initCommandMessage();
		initcommandMessage();
		if(news != null && !news.isEmpty()){
			plugin.getLogger().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.YELLOW, MessageList.ConfiLangUpdate));
			for(String s : news){
				plugin.getLogger().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.YELLOW, s));
			}
			plugin.getLogger().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.BLUE, MessageList.ConfiLangReload));
			ConfigurationManager.getLang().saveConfig();
			ConfigurationManager.initLanguage();
			plugin.getLogger().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.GREEN, MessageList.ConfiLangReloadDone));
		}
	}
	
	private static boolean isSet(String path){
		return ConfigurationManager.getLang().getConfig().isSet(path);
	}
	
	
	
	private static void SetMessage(String path, String defaultmessage){
		if(!isSet(path)){
			ConfigurationManager.getLang().getConfig().set(path, defaultmessage);
			news.add("'" + path + "'" + AnsiColor.GREEN + " : " + AnsiColor.WHITE + defaultmessage);
		}
	}
	
	private static void initCommandMessage(){
		SetMessage("AutomessageUsage", "Use /cm am <start/stop>");
		SetMessage("CanNotPerms", "You dont have permission for this!");
		SetMessage("AutomessageNotRun", "Automessage not running!");
		SetMessage("AutomessageStoped", "Automessage has been stopped!");
		SetMessage("AutomessageRunning", "Automessage is already running!");
		SetMessage("AutomessageRun", "Automessage has been started!");
		SetMessage("ScoreboardShow", "Sidebar displayed!");
		SetMessage("ScoreboardHide", "The Sidebar is hidden!");
		SetMessage("ScoreboardDisabled", "The Sidebar is disabled!");
		SetMessage("ReloadedDone", "Plugin reloaded!");
		SetMessage("CountDownCancel", "The countdown is canceled!");
		SetMessage("CountDownRunning", "Countdown is already running!");
		SetMessage("CountDownRun", "Countdown has been started!");
		SetMessage("CountDownNotRunning", "Countdown not running!");
	}
	
	
	private static void initVaultMessage(){
		SetMessage("UnableVault", "Plugin can´t be enabled because it was unable to locate Vault :(");
		SetMessage("VaultFound", "Vault found! :)");
		SetMessage("EconomyPluginNotFound", "Problem has been detected! I can't find an economy plugin. Is it enable ?");
		SetMessage("PermissionPluginNotFound", "Problem has been detected! I can't find a permission plugin. Is it enable ?");
		SetMessage("ChatPluginNotFound", "Problem has been detected! I can't find a plugin for chat management. Is it enable ?");
		SetMessage("UnsupportedPermissionPlugin", "Problem has been detected! I can't hook to 'SuperPerms' plugin! This permission plugin is Unsupported");
		SetMessage("SuccessfulhookedEconomy", "Successful hooked to");
		SetMessage("SuccessfulhookedPermission", "Successful hooked to");
		SetMessage("SuccessfulhookedChat", "Successful hooked to");
	}
	
	private static void initPluginMessage(){
		SetMessage("DisablePlugin", "is disable!");
		SetMessage("DisablingPlugin", "Disabling...");
		SetMessage("Version", "Version");
		SetMessage("Author", "Author");
		SetMessage("Enable", "The Plugin is enabled and ready to work!");
		SetMessage("Metrics", "Starting Metrics");
		SetMessage("Unable_Metrics", "Unable to enable metrics due to network problems.");
		SetMessage("StoppedRefreshTask", "Refresh task has been stopped!");
		SetMessage("CancelAllTasks", "All tasks has been Canceled!");
		SetMessage("Reloading", "Reloading configuration...");
		SetMessage("Reload", "Configuration reloaded!");
		SetMessage("AutomessageDisabled", "Automessages is disabled!");
		SetMessage("AutomessageEnabled", "Automessages is enabled!");
	}
	
	private static void iniConfigMessage(){
		SetMessage("CouldNotSaveConfig", "Could not save config to ");
		SetMessage("CreateConfigFile", "Creating config file - ");
		SetMessage("LoadingConfigs", "Loading configs...");
		SetMessage("ConfigsLoaded", "Configs loaded!");
		SetMessage("SavingConfigs", "Saving configs...");
		SetMessage("ConfigsSaved", "Configs Saved!");
		SetMessage("ConfigOutdated", "The following configuration files are outdated! Please update it!");
		SetMessage("ConfiLangReload", "Reloading language configuration file....");
		SetMessage("ConfiLangReloadDone", "Language configuration was successfully Reloaded!");
		
	}
	
	private static void iniJQKMessage(){
		SetMessage("JoinMessageNull", "Problem has been detected! Join message is null!");
		SetMessage("QuitnMessageNull", "Problem has been detected! Quit message is null!");
		SetMessage("KickMessageNull", "Problem has been detected! Kick message is null!");
		SetMessage("JoinMessage", "has join the game.");
		SetMessage("QuitnMessage", "has quit the game.");
		SetMessage("KickMessage", "has been kicked out of the game for ");
		
	}
	
	private static void iniScoreboard(){
		SetMessage("isLongerThanAllowed", "is longer than");
	}
	
	private static void initcommandMessage(){
		SetMessage("AuthorCommandMsg", "&bAuthor&7:&6 ");
		SetMessage("VersionCommandMsg", "&bVersion&7:&6 ");
		SetMessage("AutomessageEnableDisableCommands", "&bAutomessage Enable/Disable&7:&6");
		SetMessage("HideShowCommand", "&bHide Sidebar/View sideba&7:&6 ");
		SetMessage("ReloadPluginCommand", "&bReload plugin&7:&6 ");
		SetMessage("CountdownCommand", "&bCountdown&7:&6 ");
		SetMessage("CountdownUsage", "&bCountdown usage");
		SetMessage("CountdownCommandtimer", "&bTimer&7:&6 ");
		SetMessage("CountdownCommandtimersec", "&bTimer for 10 sec&7:&6 ");
		SetMessage("CountdownCommandreload", "&bReload server&7:&6 ");
		SetMessage("CountdownCommandreloadsec", "&bReload server in 10 sec&7:&6 ");
		SetMessage("CountdownCommandshutdown", "&bShutdown Server&7:&6 ");
		SetMessage("CountdownCommandshutdownsec", "&bShutdown Server in 10 sec&7:&6 ");
	}
}
