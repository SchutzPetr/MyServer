package cz.Sicka_gp.MyServer.Listener;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.TabListManager;
import cz.Sicka_gp.MyServer.Configuration.ConfigSettings;

public class ChatListener implements Listener {
	
	private static MyServer plugin;
	private static boolean Badwords_Enable;
	private static List<String> badWords;
	private static List<String> ips;
	private static boolean Messages_EnableTabList;

	public ChatListener(MyServer m){
		  plugin = m;
		  init();
	}
	
	public static void init(){
		Badwords_Enable = ConfigSettings.Badwords_Enable;
		Messages_EnableTabList = ConfigSettings.Messages_EnableTabList;
		badWords = plugin.getConfigManager().getBadwordsConfig().getConfig().getStringList("badwords");
		ips = plugin.getConfigManager().getBadwordsConfig().getConfig().getStringList("IP");
	}

	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onPlayerChat(AsyncPlayerChatEvent event){
		Player p = event.getPlayer();
		String message = event.getMessage();
		if(Badwords_Enable){
			for(String word : badWords){
				if(message.toLowerCase().contains(word)){
					message = message.replace(word, "***");
					p.chat(message);
		    		event.setCancelled(true);
		    		break;
				}
			}
			for(String ip : ips){
				if(message.toLowerCase().contains(ip)){
					for(Player op : Bukkit.getOnlinePlayers()){
		    			if(op.isOp()){
		    				op.sendMessage(ChatColor.DARK_RED + p.getName() + ": " + message);
		    			}
		    		}
		    		event.setCancelled(true);
		    		break;
				}
			}
		}
		if(Messages_EnableTabList){
			TabListManager.TabList(p);
		}
		
	}

}
