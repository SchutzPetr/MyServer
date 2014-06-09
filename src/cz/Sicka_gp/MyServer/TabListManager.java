package cz.Sicka_gp.MyServer;

import org.bukkit.entity.Player;

import cz.Sicka_gp.MyServer.Configuration.ConfigSettings;
import cz.Sicka_gp.MyServer.utils.Replacer;

public class TabListManager {
	static MyServer plugin;
	private static String playername;
	private static boolean Messages_EnableTabList;
	private static boolean Messages_UseDefaultTabList;
	private static boolean Messages_UseDefaultTabListColor;
	
	public TabListManager(MyServer instance){
		plugin = instance;
		init();
	}
	
	public static void init(){
		Messages_EnableTabList = ConfigSettings.Messages_EnableTabList;
		Messages_UseDefaultTabList = ConfigSettings.Messages_UseDefaultTabList;
		Messages_UseDefaultTabListColor = ConfigSettings.Messages_UseDefaultTabListColor;
	}
	
	public static String Group(Player p){
		if((plugin.getPluginsManager().getVault().getChat() == null) || (plugin.getPluginsManager().getVault().getPermission() == null)){
			//TODO: ConfigurableMessages.log.info(MessagesList.PermissionPluginNotFound);
			return "Default";
		}
		if(Messages_UseDefaultTabList){
			return "Default";
		}
		return plugin.getPluginsManager().getVault().getPermission().getPrimaryGroup(p);
	}
	
	public static void TabList(Player p){
		if(!Messages_EnableTabList){
			return;
		}
		if(Messages_UseDefaultTabListColor){
			TabListDefaultGroup(p);
		}else{
			TabListGroup(p);
		}
	}
	
	private static void TabListGroup(Player p){
		playername = p.getName();
		String color = plugin.getConfigManager().getCustomMessageConfig().getConfig().getString("Messages.Groups." + Group(p) + ".TabColor");
		String prefix = plugin.getConfigManager().getCustomMessageConfig().getConfig().getString("Messages.Groups." + Group(p) + ".TabPrefix");
		String tabname = prefix + color + playername;
		if(!(tabname.length() < 16)){
			return;
		}
		p.setPlayerListName(Replacer.replaceColor(tabname));
	}
	
	private static void TabListDefaultGroup(Player p){
		String prefix = plugin.getPluginsManager().getVault().getChat().getPlayerPrefix(p);
		playername = p.getName();
		String TabPrefix = plugin.getConfigManager().getCustomMessageConfig().getConfig().getString("Messages.Groups." + Group(p) + ".TabPrefix");
		String tabname = TabPrefix + prefix + playername;
		if(tabname.length() < 16){
			return;
		}
		p.setPlayerListName(Replacer.replaceColor(tabname));
	}
}
