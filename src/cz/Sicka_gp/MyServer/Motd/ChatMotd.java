package cz.Sicka_gp.MyServer.Motd;

import java.util.List;

import org.bukkit.entity.Player;

import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.Configuration.ConfigSettings;
import cz.Sicka_gp.MyServer.utils.Replacer;

public class ChatMotd {
	static MyServer plugin;
	private static List<String> list;
	public static boolean ChatMotd_Enable;
	
	public ChatMotd(MyServer instance) {
		plugin = instance;
		init();
	}
	
	public static void init(){
		ChatMotd_Enable = ConfigSettings.ChatMotd_Enable;
		list = ConfigSettings.ChatMotd_List;
		if(list.isEmpty())list.add("&4Error!");
	}
	
	public static void sendMotdMessage(Player p){
		if(!ChatMotd_Enable)return;
		for(String message : list){
			p.sendMessage(Replacer.replaceString(message, p));
		}
		
	}

}
