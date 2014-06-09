package cz.Sicka_gp.MyServer.utils;

import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.Configuration.ConfigurationManager;

public class NewMessageList {
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
	public static String Authors;
	public static String Enable;
	public static String Metrics;
	public static String Unable_Metrics;
	public static String StoppedRefreshTask;
	public static String CancelAllTasks;
	
	public static String CouldNotSaveConfig;
	public static String CreateConfigFile;
	public static String LoadingConfigs;
	public static String ConfigsLoaded;
	public static String SavingConfigs;
	public static String ConfigsSaved;
	public static String aaaa;
	public static String aaaaaaaaaaa;
	
	public static String JoinMessageNull;
	public static String QuitnMessageNull;
	public static String KickMessageNull;
	public static String JoinMessage;
	public static String QuitnMessage;
	public static String KickMessage;
	
	public static String isLongerThanAllowed;
	
	public static String pluginName = "[MyServer] ";
	
	public NewMessageList(MyServer instance){
		plugin = instance;
		
		initVaultMessage();
		initPluginMessage();
		iniConfigMessage();
		iniJQKMessage();
		iniScoreboard();
	}
	
	private static String getMessage(String path, String defaultmessage) {
		return  ConfigurationManager.getLang().getConfig().getString(path, defaultmessage);
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
		Authors = pluginName + getMessage("Authors", "Authors")+ " " + plugin.getDescription().getAuthors();
		Enable = pluginName + getMessage("Enable", "The Plugin is enabled and ready to work!");
		Metrics = pluginName + getMessage("Metrics", "Starting Metrics");
		Unable_Metrics = pluginName + getMessage("Unable_Metrics", "Unable to enable metrics due to network problems.");
		StoppedRefreshTask = pluginName + getMessage("StoppedRefreshTask", "Refresh task has been stopped!");
		CancelAllTasks = pluginName + getMessage("CancelAllTasks", "All tasks has been Canceled!");
	}
	
	private static void iniConfigMessage(){
		CouldNotSaveConfig = pluginName + getMessage("CouldNotSaveConfig", "Could not save config to ");
		
		CreateConfigFile = pluginName + getMessage("CreateConfigFile", "Creating config file - ");
		LoadingConfigs = pluginName + getMessage("LoadingConfigs", "Loading configs...");
		ConfigsLoaded = pluginName + getMessage("ConfigsLoaded", "Configs loaded!");
		SavingConfigs = pluginName + getMessage("SavingConfigs", "Saving configs...");
		ConfigsSaved = pluginName + getMessage("ConfigsSaved", "Configs Saved!");
		aaaaaaaaaaa = pluginName + getMessage("aaaaaaaaaaa", "ssssssssssss");
		aaaaaaaaaaa = pluginName + getMessage("aaaaaaaaaaa", "ssssssssssss");
		aaaaaaaaaaa = pluginName + getMessage("aaaaaaaaaaa", "ssssssssssss");
		
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
		aaaaaaaaaaa = pluginName + getMessage("aaaaaaaaaaa", "ssssssssssss");
		aaaaaaaaaaa = pluginName + getMessage("aaaaaaaaaaa", "ssssssssssss");
		
	}

}