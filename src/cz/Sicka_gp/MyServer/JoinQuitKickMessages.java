package cz.Sicka_gp.MyServer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import cz.Sicka_gp.MyServer.Configuration.ConfigSettings;
import cz.Sicka_gp.MyServer.utils.AnsiColor;
import cz.Sicka_gp.MyServer.utils.ColouredConsoleSender;
import cz.Sicka_gp.MyServer.utils.NewMessageList;
import cz.Sicka_gp.MyServer.utils.Replacer;

public class JoinQuitKickMessages {
	static MyServer plugin;
	private static boolean Messages_UseDefaultMessage;
	private static boolean Messages_EnableJoinQuitKickMSG;

	private static List<Player> PlayersInGame = new ArrayList<Player>();
	
	public JoinQuitKickMessages(MyServer instance) {
		plugin = instance;
		init();
	}
	
	public static void init(){
		Messages_UseDefaultMessage = ConfigSettings.Messages_UseDefaultMessage;
		Messages_EnableJoinQuitKickMSG = ConfigSettings.Messages_EnableJoinQuitKickMSG;
	}
	
	public static List<Player> getPlayerInGame(){
		return PlayersInGame;
		
	}
	
	public static String Group(Player p){
		if((plugin.getPluginsManager().getVault().getChat() == null) || (plugin.getPluginsManager().getVault().getPermission() == null)){
			plugin.getLog().log(Level.WARNING, ColouredConsoleSender.sendConsoleMessage(AnsiColor.RED, NewMessageList.PermissionPluginNotFound));
			return "Default";
		}
		if(Messages_UseDefaultMessage){
			return "Default";
		}
		return plugin.getPluginsManager().getVault().getPermission().getPrimaryGroup(p);
	}
	
	public static void SendCustomJoinMessage(Player p){
		PlayersInGame.add(p);
		if (!Messages_EnableJoinQuitKickMSG){
			return;
		}
		String path = "Messages.Groups." + Group(p) + ".JoinMessage";
		String message = Replacer.replaceJoinQuitmsg(p, path);
	    for (Player pl : Bukkit.getServer().getOnlinePlayers()){
	    	if (pl.canSee(p)){
	    		if (message != null){
	    			pl.sendMessage(message);
	    		}else{
	    			plugin.getLog().log(Level.WARNING, ColouredConsoleSender.sendConsoleMessage(AnsiColor.RED, NewMessageList.JoinMessageNull));
	    		}
	    	}
	    }
	    plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.YELLOW, NewMessageList.pluginName + AnsiColor.GREEN + p.getName() + AnsiColor.BLUE + NewMessageList.JoinMessage));
	}
	
	public static void SendCustomQuitMessage(Player p){
		if(!PlayersInGame.contains(p)){
			return;
		}
		PlayersInGame.remove(p);
		if(!Messages_EnableJoinQuitKickMSG){
			return;
		}
		String path = "Messages.Groups." + Group(p) + ".QuitMessage";
		String message = Replacer.replaceJoinQuitmsg(p, path);
		for (Player pl : Bukkit.getServer().getOnlinePlayers()){
	    	if (pl.canSee(p)){
	    		if (message != null){
	    			pl.sendMessage(message);
	    		}else{
	    			plugin.getLog().log(Level.WARNING, ColouredConsoleSender.sendConsoleMessage(AnsiColor.RED, NewMessageList.QuitnMessageNull));
	    		}
	    	}
	    }
		plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.YELLOW, NewMessageList.pluginName + AnsiColor.GREEN + p.getName() + AnsiColor.BLUE + NewMessageList.QuitnMessage));
	}
	
	public static void SendCustomKickMessage(Player p, String reason){
		if(!PlayersInGame.contains(p)){
			return;
		}
		PlayersInGame.remove(p);
		if(!Messages_EnableJoinQuitKickMSG){
			return;
		}
		String path = "Messages.Groups." + Group(p) + ".KickMessage";
		String message = Replacer.replaceJoinQuitmsg(p, path);
		for (Player pl : Bukkit.getServer().getOnlinePlayers()){
	    	if (pl.canSee(p)){
	    		if (message != null){
	    			if (message.contains("{REASON}")){
	    				message = message.replace("{REASON}", reason);
	    	            pl.sendMessage(message);
	    			}else{
	    				pl.sendMessage(message);	
	    			}
	    		}else{
	    			plugin.getLog().log(Level.WARNING, ColouredConsoleSender.sendConsoleMessage(AnsiColor.RED, NewMessageList.KickMessageNull));
	    		}
	    	}
	    }
		plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.YELLOW, NewMessageList.pluginName + AnsiColor.GREEN + p.getName() + AnsiColor.BLUE + NewMessageList.KickMessage));
	}
}
