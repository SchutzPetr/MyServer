package cz.Sicka_gp.MyServer.utils;

import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.Configuration.ConfigurationManager;

public class MessageList {
	private static MyServer plugin;
	
	public static String UnableVault;
	public static String VaultFound;
	public static String EconomyPluginNotFound;
	public static String UnsupportedPermissionPlugin;
	public static String PermissionPluginNotFound;
	public static String ChatPluginNotFound;
	public static String SuccessfulhookedEconomy;
	public static String SuccessfulhookedPermission;
	public static String SuccessfulhookedChat;
	
	public static String DisablingPlugin;
	public static String DisablePlugin;
	public static String Version;
	public static String Author;
	public static String Enable;
	public static String Metrics;
	public static String Unable_Metrics;
	public static String StoppedRefreshTask;
	public static String CancelAllTasks;
	public static String Reloading;
	public static String Reload;
	public static String AutomessageDisabled;
	public static String AutomessageEnabled;
	
	
	public static String CouldNotSaveConfig;
	public static String CreateConfigFile;
	public static String LoadingConfigs;
	public static String ConfigsLoaded;
	public static String SavingConfigs;
	public static String ConfigsSaved;
	public static String ConfigOutdated;
	public static String ConfiLangUpdate;
	public static String ConfiLangReload;
	public static String ConfiLangReloadDone;
	
	public static String JoinMessageNull;
	public static String QuitnMessageNull;
	public static String KickMessageNull;
	public static String JoinMessage;
	public static String QuitnMessage;
	public static String KickMessage;
	
	public static String isLongerThanAllowed;
	
	public static String CanNotPerms;
	public static String AutomessageUsage;
	public static String ScoreboardDisabled;
	public static String CountDownRun;
	public static String CountDownNotRunning;
	public static String CountDownRunning;
	public static String ReloadedDone;
	public static String CountDownCancel;
	public static String AutomessageRunning;
	public static String AutomessageStoped;
	public static String AutomessageNotRun;
	public static String ScoreboardShow;
	public static String ScoreboardHide;
	public static String AutomessageRun;
	
	public static String AuthorCommandMsg;
	public static String VersionCommandMsg;
	public static String AutomessageEnableDisableCommands;
	public static String HideShowCommand;
	public static String ReloadPluginCommand;
	public static String CountdownCommand;
	public static String CountdownCommandtimer;
	public static String CountdownCommandtimersec;
	public static String CountdownCommandreload;
	public static String CountdownCommandreloadsec;
	public static String CountdownCommandshutdown;
	public static String CountdownCommandshutdownsec;
	public static String CountdownUsage;
	
	public static String pluginName = "[MyServer] ";
	
	public MessageList(MyServer instance){
		plugin = instance;
		
		initVaultMessage();
		initPluginMessage();
		iniConfigMessage();
		iniJQKMessage();
		iniScoreboard();
		initCommandMessage();
		initcommandMessage();
	}
	
	private static String getMessage(String path, String defaultmessage) {
		return  ConfigurationManager.getLang().getConfig().getString(path, defaultmessage);
	}
	
	private static void initCommandMessage(){
		AutomessageUsage = getMessage("AutomessageUsage", "Use /cm am <start/stop>");
		CanNotPerms = getMessage("CanNotPerms", "You dont have permission for this!");
		AutomessageNotRun = getMessage("AutomessageNotRun", "Automessage not running!");
		AutomessageStoped = getMessage("AutomessageStoped", "Automessage has been stopped!");
		AutomessageRunning = getMessage("AutomessageRunning", "Automessage is already running!");
		AutomessageRun = getMessage("AutomessageRun", "Automessage has been started!");
		ScoreboardShow = getMessage("ScoreboardShow", "Sidebar displayed!");
		ScoreboardHide = getMessage("ScoreboardHide", "The Sidebar is hidden!");
		ScoreboardDisabled = getMessage("ScoreboardDisabled", "The Sidebar is disabled!");
		ReloadedDone = getMessage("ReloadedDone", "Plugin reloaded!");
		CountDownCancel = getMessage("CountDownCancel", "The countdown is canceled!");
		CountDownRunning = getMessage("CountDownRunning", "Countdown is already running!");
		CountDownRun = getMessage("CountDownRun", "Countdown has been started!");
		CountDownNotRunning = getMessage("CountDownNotRunning", "Countdown not running!");
	}
	
	
	private static void initVaultMessage(){
		UnableVault = pluginName + getMessage("UnableVault", "Plugin can´t be enabled because it was unable to locate Vault :(");
		VaultFound = pluginName + getMessage("VaultFound", "Vault found! :)");
		EconomyPluginNotFound = pluginName + "[Economy]" + getMessage("EconomyPluginNotFound", "Problem has been detected! I can't find an economy plugin. Is it enable ?");
		PermissionPluginNotFound = pluginName + "[Permission]" + getMessage("PermissionPluginNotFound", "Problem has been detected! I can't find a permission plugin. Is it enable ?");
		ChatPluginNotFound = pluginName + "[Chat]" + getMessage("ChatPluginNotFound", "Problem has been detected! I can't find a plugin for chat management. Is it enable ?");
		UnsupportedPermissionPlugin = pluginName + "[Permission]" +getMessage("UnsupportedPermissionPlugin", "Problem has been detected! I can't hook to 'SuperPerms' plugin! This permission plugin is Unsupported");
		SuccessfulhookedEconomy = pluginName + "[Economy]" + getMessage("SuccessfulhookedEconomy", "Successful hooked to") + " ";
		SuccessfulhookedPermission = pluginName + "[Permission]" + getMessage("SuccessfulhookedPermission", "Successful hooked to")+ " ";
		SuccessfulhookedChat = pluginName + "[Chat]" + getMessage("SuccessfulhookedChat", "Successful hooked to")+ " ";
	}
	
	private static void initPluginMessage(){
		DisablePlugin = pluginName + getMessage("DisablePlugin", "is disable!");
		DisablingPlugin = pluginName + getMessage("DisablingPlugin", "Disabling...");
		Version = pluginName + getMessage("Version", "Version") + " " + plugin.getDescription().getVersion();
		Author = pluginName + getMessage("Author", "Author")+ " " + plugin.getDescription().getAuthors();
		Enable = pluginName + getMessage("Enable", "The Plugin is enabled and ready to work!");
		Metrics = pluginName + getMessage("Metrics", "Starting Metrics");
		Unable_Metrics = pluginName + getMessage("Unable_Metrics", "Unable to enable metrics due to network problems.");
		StoppedRefreshTask = pluginName + getMessage("StoppedRefreshTask", "Refresh task has been stopped!");
		CancelAllTasks = pluginName + getMessage("CancelAllTasks", "All tasks has been Canceled!");
		Reloading = pluginName + getMessage("Reloading", "Reloading configuration...");
		Reload = pluginName + getMessage("Reload", "Configuration reloaded!");
		AutomessageDisabled = pluginName + getMessage("AutomessageDisabled", "Automessages is disabled!");
		AutomessageEnabled = pluginName + getMessage("AutomessageEnabled", "Automessages is enabled!");
	}
	
	private static void iniConfigMessage(){
		CouldNotSaveConfig = pluginName + getMessage("CouldNotSaveConfig", "Could not save config to ");
		CreateConfigFile = pluginName + getMessage("CreateConfigFile", "Creating config file - ");
		LoadingConfigs = pluginName + getMessage("LoadingConfigs", "Loading configs...");
		ConfigsLoaded = pluginName + getMessage("ConfigsLoaded", "Configs loaded!");
		SavingConfigs = pluginName + getMessage("SavingConfigs", "Saving configs...");
		ConfigsSaved = pluginName + getMessage("ConfigsSaved", "Configs Saved!");
		ConfigOutdated = pluginName + getMessage("ConfigOutdated", "The following configuration files are outdated! Please update it!");
		ConfiLangUpdate = getMessage("ConfiLangUpdate", "The language configuration file has been updated! The following values have been added:");
		ConfiLangReload = getMessage("ConfiLangReload", "Reloading language configuration file....");
		ConfiLangReloadDone = getMessage("ConfiLangReloadDone", "Language configuration was successfully Reloaded!");
		
	}
	
	private static void iniJQKMessage(){
		JoinMessageNull = pluginName + getMessage("JoinMessageNull", "Problem has been detected! Join message is null!");
		QuitnMessageNull = pluginName + getMessage("QuitnMessageNull", "Problem has been detected! Quit message is null!");
		KickMessageNull = pluginName + getMessage("KickMessageNull", "Problem has been detected! Kick message is null!");
		JoinMessage = " " + getMessage("JoinMessage", "has join the game.");
		QuitnMessage = " " + getMessage("QuitnMessage", "has quit the game.");
		KickMessage =  " " + getMessage("KickMessage", "has been kicked out of the game for ");
		
	}
	
	private static void iniScoreboard(){
		isLongerThanAllowed = getMessage("isLongerThanAllowed", "is longer than") + " ";
	}
	
	private static void initcommandMessage(){
		AuthorCommandMsg = getMessage("AuthorCommandMsg", "&bAuthor&7:&6 ");
		VersionCommandMsg = getMessage("VersionCommandMsg", "&bVersion&7:&6 ");
		AutomessageEnableDisableCommands = getMessage("AutomessageEnableDisableCommands", "&bAutomessage Enable/Disable&7:&6");
		HideShowCommand = getMessage("HideShowCommand", "&bHide Sidebar/View sideba&7:&6 ");
		ReloadPluginCommand = getMessage("ReloadPluginCommand", "&bReload plugin&7:&6 ");
		CountdownCommand = getMessage("CountdownCommand", "&bCountdown&7:&6 ");
		CountdownUsage = getMessage("CountdownUsage", "&bCountdown usage");
		CountdownCommandtimer = getMessage("CountdownCommandtimer", "&bTimer&7:&6 ");
		CountdownCommandtimersec = getMessage("CountdownCommandtimersec", "&bTimer for 10 sec&7:&6 ");
		CountdownCommandreload = getMessage("CountdownCommandreload", "&bReload server&7:&6 ");
		CountdownCommandreloadsec = getMessage("CountdownCommandreloadsec", "&bReload server in 10 sec&7:&6 ");
		CountdownCommandshutdown = getMessage("CountdownCommandshutdown", "&bShutdown Server&7:&6 ");
		CountdownCommandshutdownsec = getMessage("CountdownCommandshutdownsec", "&bShutdown Server in 10 sec&7:&6 ");
	}
}