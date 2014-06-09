package cz.Sicka_gp.MyServer.utils;

import org.bukkit.entity.Player;

import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.Scoreboard.ScoreboardSettings;

public class PlayerGroup {
	private static MyServer plugin;
	
	public PlayerGroup(MyServer instance){
		plugin = instance;
	}
	
	public static String getPlayerGroup(Player p){
		if((plugin.getPluginsManager().getVault().getChat() == null) || (plugin.getPluginsManager().getVault().getPermission() == null)){
			//TODO: Log
			//ConfigurableMessages.log.info(MessagesList.PermissionPluginNotFound);
			return "Default";
		}
		if(!ScoreboardSettings.getPermItems().containsKey(plugin.getPluginsManager().getVault().getPermission().getPrimaryGroup(p))){
			return "Default";
		}
		return plugin.getPluginsManager().getVault().getPermission().getPrimaryGroup(p);
	}

}
