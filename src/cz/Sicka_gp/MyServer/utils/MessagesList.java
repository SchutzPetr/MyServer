package cz.Sicka_gp.MyServer.utils;

import org.bukkit.configuration.file.FileConfiguration;

import cz.Sicka_gp.MyServer.MyServer;

public class MessagesList {
	public static final String plug = "[MyServer]";
	public static final String warning = "[WARNING]";
	
	//Console
	@Deprecated
	public static String SimpleClans_Unsupported_Version;
	@Deprecated
	public static String Factions_Unsupported_Version;
	@Deprecated
	public static String ContactDevelopers;
	@Deprecated
	public static String GeoIPTools_Load_ex;
	@Deprecated
	public static String DisablingPlugin;
	@Deprecated
	public static String UnableVault;
	@Deprecated
	public static String DisablePlugin;
	@Deprecated
	public static String CreateConfig;
	@Deprecated
	public static String Version;
	@Deprecated
	public static String JoinMessageNull;
	@Deprecated
	public static String Authors;
	@Deprecated
	public static String Enable;
	@Deprecated
	public static String OutdateConfig;
	@Deprecated
	public static String CreateLang;
	@Deprecated
	public static String CreateBadwords;
	@Deprecated
	public static String CreateMessages;
	@Deprecated
	public static String SidebarName;
	@Deprecated
	public static String VaultFound;
	@Deprecated
	public static String QuitnMessageNull;
	@Deprecated
	public static String KickMessageNull;
	@Deprecated
	public static String Successfulhooked;
	@Deprecated
	public static String EconomyPluginNotFound;
	@Deprecated
	public static String ChatPluginNotFound;
	@Deprecated
	public static String PermissionPluginNotFound;
	@Deprecated
	public static String UnsupportedPermissionPlugin;
	@Deprecated
	public static String Reloading;
	@Deprecated
	public static String Reload;
	@Deprecated
	public static String AutomessageDisabled;
	@Deprecated
	public static String isLongerThanAllowed;
	@Deprecated
	public static String AutomessageEnabled;
	@Deprecated
	public static String CanceledTask;
	
	//Player
	@Deprecated
	public static String CanNotPerms;
	@Deprecated
	public static String AutomessageUsage;
	@Deprecated
	public static String SidebarDisabled;
	@Deprecated
	public static String CountDownRun;
	@Deprecated
	public static String CountDownNotRunning;
	@Deprecated
	public static String CountDownRunning;
	@Deprecated
	public static String ReloadedDone;
	@Deprecated
	public static String CountDownCancel;
	@Deprecated
	public static String AutomessageRunning;
	@Deprecated
	public static String AutomessageStoped;
	@Deprecated
	public static String AutomessageNotRun;
	@Deprecated
	public static String SidebarShow;
	@Deprecated
	public static String SidebarHide;
	@Deprecated
	public static String AutomessageRun;
	
	
	protected static MyServer plugin;
	
	public MessagesList(MyServer instance){
		plugin = instance;
		
		init();
		
		CreateConfig = plug + " Creating config file";
		CreateBadwords = plug + " Creating config file";
		CreateMessages = plug + " Creating config file";
		CreateLang = plug + " Creating config file";
	}
	
	public static void initMessages(){
		UnableVault = getMessage("UnableVault", "Plugin can´t be enabled because it was unable to locate Vault.");
	}
	
	@Deprecated
	public static void init(){
		SimpleClans_Unsupported_Version = plug + getLang().getString("SimpleClans_Unsupported_Version", "SimpleClans version under version Legacy isn't supported");
		Factions_Unsupported_Version = plug + getLang().getString("Factions_Unsupported_Version", "Factions version under 2.0 isn't supported");
		GeoIPTools_Load_ex = plug + getLang().getString("GeoIPTools_Load_ex", "Exception happened during GeoIPTools load: ");
		ContactDevelopers = plug + getLang().getString("ContactDevelopers", "Please contact the developers");
		DisablingPlugin = plug + getLang().getString("DisablingPlugin", "Disabling...");
		DisablePlugin = plug + getLang().getString("DisablePlugin", "is disable!");
		UnableVault = plug + getLang().getString("UnableVault", "Plugin can´t be enabled because it was unable to locate Vault.");
		VaultFound = plug + getLang().getString("VaultFound", "Vault found.");
		SidebarName = plug + getLang().getString("SidebarName", "Sidebar name is too long!");
		CreateConfig = plug + getLang().getString("CreateConfig", "Creating config file");
		CreateBadwords = plug + getLang().getString("CreateBadwords", "Creating config file");
		CreateMessages = plug + getLang().getString("CreateMessages", "Creating config file");
		CreateLang = plug + getLang().getString("CreateLang", "Creating config file");
		Version = plug + getLang().getString("Version", "Version") + " " + plugin.getDescription().getVersion();
		Authors = plug + getLang().getString("Authors", "Authors")+ " " + plugin.getDescription().getAuthors();
		Enable = plug + getLang().getString("Enable", "is Enable");
		OutdateConfig = plug + warning + getLang().getString("OutdateConfig", "Your configuration file is out of date, please update it.");
		JoinMessageNull = plug + warning + getLang().getString("JoinMessageNull", "Problem has been detected! Join message is null!");
		QuitnMessageNull = plug + warning + getLang().getString("QuitnMessageNull", "Problem has been detected! Quit message is null!");
		KickMessageNull = plug + warning + getLang().getString("KickMessageNull", "Problem has been detected! Kick message is null!");
		Successfulhooked = plug + getLang().getString("Successfulhooked", "Successful hooked to") + " ";
		EconomyPluginNotFound = plug + warning + getLang().getString("EconomyPluginNotFound", "Problem has been detected! I can't find an economy plugin. Is it enable ?");
		ChatPluginNotFound = plug + warning + getLang().getString("ChatPluginNotFound", "Problem has been detected! I can't find a plugin for chat management. Is it enable ?");
		PermissionPluginNotFound = plug + warning + getLang().getString("PermissionPluginNotFound", "Problem has been detected! I can't find a permission plugin. Is it enable ?");
		UnsupportedPermissionPlugin = plug + warning + getLang().getString("UnsupportedPermissionPlugin", "Problem has been detected! I can't hook to 'SuperPerms' plugin! This permission plugin is Unsupported");
		Reloading = plug + getLang().getString("Reloading", "Reloading configuration...");
		Reload = plug + getLang().getString("Reload", "Configuration reloaded!");
		AutomessageDisabled = plug + getLang().getString("AutomessageDisabled", "Automessages is disabled!");
		AutomessageEnabled = plug + getLang().getString("AutomessageEnabled", "Automessages is enabled!");
		isLongerThanAllowed = plug + getLang().getString("isLongerThanAllowed", "is longer than") + " ";
		CanceledTask = plug + getLang().getString("CanceledTask", "Task is Canceled");
			
		AutomessageUsage = getLang().getString("AutomessageUsage", "Use /cm am <start/stop>");
		CanNotPerms = getLang().getString("CanNotPerms", "You dont have permission for this!");
		AutomessageNotRun = getLang().getString("AutomessageNotRun", "Automessage not running!");
		AutomessageStoped = getLang().getString("AutomessageStoped", "Automessage has been stopped!");
		AutomessageRunning = getLang().getString("AutomessageRunning", "Automessage is already running!");
		AutomessageRun = getLang().getString("AutomessageRun", "Automessage has been started!");
		SidebarShow = getLang().getString("SidebarShow", "Sidebar displayed!");
		SidebarHide = getLang().getString("SidebarHide", "The Sidebar is hidden!");
		SidebarDisabled = getLang().getString("SidebarDisabled", "The Sidebar is disabled!");
		ReloadedDone = getLang().getString("ReloadedDone", "Configuration reloaded!");
		CountDownCancel = getLang().getString("CountDownCancel", "The countdown is canceled!");
		CountDownRunning = getLang().getString("CountDownRunning", "Countdown is already running!");
		CountDownRun = getLang().getString("CountDownRun", "Countdown has been started!");
		CountDownNotRunning = getLang().getString("CountDownNotRunning", "Countdown not running!");
		
		
		
	}
	
	private static FileConfiguration getLang() {
		return plugin.getConfigManager().getLang().getConfig();
	}
	
	private static String getMessage(String path, String defaultmessage) {
		return plugin.getConfigManager().getLang().getConfig().getString(path, defaultmessage);
	}
	

}
