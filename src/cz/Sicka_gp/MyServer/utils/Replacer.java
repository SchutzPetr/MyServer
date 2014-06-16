package cz.Sicka_gp.MyServer.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import cz.Sicka_gp.MyServer.MyServer;

public class Replacer{
	private static MyServer plugin;
	private static String message;
	private static String tablist;
	private static String colormsg;
	private static String spmotd;
	private static String string;
	private static String cdstring;
	private static String CountdownSidebarName;
	private static String title;
	
	public Replacer(MyServer instance){
		plugin = instance;
	}
	
	//For join/quit/kick message
	public static String replaceJoinQuitmsg(final Player p, String path){
		message = plugin.getConfigManager().getCustomMessageConfig().getConfig().getString(path);
		if(message == null){
			message = null;
		}else{
			message = message.replace("{NAME}", p.getName());
			message = message.replace("{DISPLAYNAME}", VaultDisplayName(p));
			message = message.replace("{LAST_SEEN}", LastSeen(p));
			message = message.replace("{WORLD}", p.getWorld().getName());
			message = message.replace("{BIOME}", p.getLocation().getBlock().getBiome().toString());
			message = message.replace("{ONLINE}", Bukkit.getServer().getOnlinePlayers().length + "");
			message = message.replace("{MAX_ONLINE}", Bukkit.getServer().getMaxPlayers() + "");
			message = message.replace("{SERVER_NAME}", Bukkit.getServer().getName());
			message = message.replace("{COUNTRY}", GeoIP(p));
			message = ChatColor.translateAlternateColorCodes("&".charAt(0), message);
			message = message.replace("{NEW_LINE}", ChatColor.RESET + "\n");
		}
		return message;
	}
	
	//For chat
	public static String replaceString(String str, Player p){
		string = str;
		if(string == null){
			string = null;
		}else{
			string = string.replace("{WORLD}", p.getWorld().getName());
			string = string.replace("{BIOME}", p.getLocation().getBlock().getBiome().toString());
			
			string = string.replace("{ONLINE}", Bukkit.getServer().getOnlinePlayers().length + "");
			string = string.replace("{MAX_ONLINE}", Bukkit.getServer().getMaxPlayers() + "");
			string = string.replace("{ONLINE_PLAYERS}", onlinePlayers());
			
			string = string.replace("{SERVER_NAME}", Bukkit.getServer().getName());
			string = string.replace("{SERVER_IP}", getServerIP());
			
			string = string.replace("{DISPLAYNAME}", p.getDisplayName());
			string = string.replace("{NAME}", p.getName());
			
			string = string.replace("{TIME}", getRealTime());
			string = string.replace("{GAME_TIME}", parseTime(p.getWorld().getName()));
			
			string = ChatColor.translateAlternateColorCodes("&".charAt(0), string);
			string = string.replace("{NEW_LINE}", ChatColor.RESET + "\n");
		}
		return ScoreItemsReplacerString.getReplacedString(string, p);
	}
	
	//Only color and Line replacer replace
	public static String replaceColor(String str) {
		colormsg = str;
		if(colormsg == null){
			colormsg = null;
		}else{
			colormsg = ChatColor.translateAlternateColorCodes("&".charAt(0), colormsg);
		    colormsg = colormsg.replace("{NEW_LINE}", ChatColor.RESET + "\n");
		}
		return colormsg;
	}
	
	//Only for tab
	public static String replaceTabList(final Player p, String path){
		tablist = plugin.getConfig().getString(path);
		if(tablist == null){
			tablist = null;
		}else{
			tablist = ChatColor.translateAlternateColorCodes("&".charAt(0), tablist);
		}
		return tablist;
	}
	
	public static String replaceServePingMOTD(String str, String p) {
		spmotd = str;
		if(spmotd == null){
			spmotd = null;
		}else{
			spmotd = spmotd.replace("{NAME}", p);
			spmotd = spmotd.replace("{SERVER_IP}", getServerIP());
			spmotd = spmotd.replace("{SERVER_NAME}", Bukkit.getServer().getName());
			spmotd = ChatColor.translateAlternateColorCodes("&".charAt(0), spmotd);
			spmotd = spmotd.replace("{NEW_LINE}", ChatColor.RESET + "\n");
		}
		return spmotd;
	}
	
	public static String replaceServePingMOTD(String str) {
		spmotd = str;
		if(spmotd == null){
			spmotd = null;
		}else{
			spmotd = spmotd.replace("{SERVER_IP}", getServerIP());
			spmotd = spmotd.replace("{SERVER_NAME}", Bukkit.getServer().getName());
			spmotd = ChatColor.translateAlternateColorCodes("&".charAt(0), spmotd);
			spmotd = spmotd.replace("{NEW_LINE}", ChatColor.RESET + "\n");
		}
		return spmotd;
	}
	
	public static String CountdownStringReplacer(String str, Player p, int i){
		cdstring = str;
		if(cdstring == null){
			cdstring = null;
		}else{
			cdstring = cdstring.replace("{WORLD}", p.getWorld().getName());
			cdstring = cdstring.replace("{BIOME}", p.getLocation().getBlock().getBiome().toString());
			cdstring = cdstring.replace("{ONLINE}", Bukkit.getServer().getOnlinePlayers().length + "");
			cdstring = cdstring.replace("{MAX_ONLINE}", Bukkit.getServer().getMaxPlayers() + "");
			cdstring = cdstring.replace("{SERVER_NAME}", Bukkit.getServer().getName());
			cdstring = cdstring.replace("{ONLINE_PLAYERS}", onlinePlayers());
			cdstring = cdstring.replace("{TIME}", getRealTime());
			cdstring = cdstring.replace("{DISPLAYNAME}", p.getDisplayName());
			cdstring = cdstring.replace("{NAME}", p.getName());
			cdstring = cdstring.replace("{GAME_TIME}", parseTime(p.getWorld().getName()));
			cdstring = cdstring.replace("{CD}", MMSS(i));
			cdstring = ChatColor.translateAlternateColorCodes("&".charAt(0), cdstring);
		}
		return cdstring;
	}
	
	public static String CountdownSidebarName(String name, int i){
		CountdownSidebarName = name;
		if(CountdownSidebarName == null){
			return null;
		}else{
			CountdownSidebarName = CountdownSidebarName.replace("{CD}", MMSS(i));
			CountdownSidebarName = ChatColor.translateAlternateColorCodes("&".charAt(0), CountdownSidebarName);
		}
		return ScoreboardLength.checkLength(CountdownSidebarName, 32);
		
	}
	
	public static String replaceSidebarName(String name){
		title = name;
		if(title == null){
			title = null;
		}else{
			//ONLINE
			title = title.replace("{1}", Bukkit.getServer().getOnlinePlayers().length + "");
			//MAX_ONLINE
			title = title.replace("{2}", Bukkit.getServer().getMaxPlayers() + "");
			//SERVER
			title = title.replace("{3}", Bukkit.getServer().getName());
			//TIME
			title = title.replace("{5}", getRealTime());
			title = ChatColor.translateAlternateColorCodes("&".charAt(0), title);
		}
		return ScoreboardLength.checkLength(title, 32);
	}
	
	public static String replaceSidebarName(String name, Player p){
		title = name;
		if(title == null){
			title = null;
		}else{
			//ONLINE
			title = title.replace("{1}", Bukkit.getServer().getOnlinePlayers().length + "");
			//MAX_ONLINE
			title = title.replace("{2}", Bukkit.getServer().getMaxPlayers() + "");
			//SERVER
			title = title.replace("{3}", Bukkit.getServer().getName());
			//TIME
			title = title.replace("{5}", getRealTime());
			//WORLD
			title = title.replace("{6}", p.getWorld().getName());
			//BIOME
			title = title.replace("{7}", p.getLocation().getBlock().getBiome().toString());
			//DISPLAYNAME
			title = title.replace("{8}", p.getDisplayName());
			//NAME
			title = title.replace("{9}", p.getName());
			//GAME_TIME
			title = title.replace("{10}", parseTime(p.getWorld().getName()));
			title = ChatColor.translateAlternateColorCodes("&".charAt(0), title);
		}
		return ScoreboardLength.checkLength(title, 32);
	}
	
	public static String onlinePlayers(){
		StringBuilder players = new StringBuilder();
		for(Player player : Bukkit.getOnlinePlayers()){
			if(players.length() > 0){
				players.append(", ");
			}
			String prefix = plugin.getPluginsManager().getVault().getChat().getPlayerPrefix(player);
			prefix = prefix.concat(player.getName());
			players.append(prefix);
		}
		return players.toString();
	}
	
	public static String getRealTime(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(cal.getTime());
	}
	
	public static String parseTime(String world){
		long time = Bukkit.getWorld(world).getTime();
	    long gameTime = time;
	    long hours = gameTime / 1000L + 6L;
	    long minutes = gameTime % 1000L * 60L / 1000L;
	    String ampm = "AM";
	    if (hours >= 12L){
	    	hours -= 12L;
	    	ampm = "PM";
	    }
	    if (hours >= 12L){
	    	hours -= 12L;
		    ampm = "AM";
	    }
	    if (hours == 0L) {
	    	hours = 12L;
	    }
	    String mm = "0" + minutes;
	    mm = mm.substring(mm.length() - 2, mm.length());
	    return hours + ":" + mm + " " + ampm;
	}
	
	public static String MMSS(int secsIn){
		int remainder = secsIn % 3600;
		int minutes = remainder / 60;
		int seconds = remainder % 60; 

		return ((minutes < 10 ? "0" : "") + minutes + ":" + (seconds< 10 ? "0" : "") + seconds ); 

	}
	
	public static String VaultDisplayName(Player p){
		if(plugin.getPluginsManager().getVault().isChatFound() && plugin.getPluginsManager().getVault().getChat() != null){
			String prefix = plugin.getPluginsManager().getVault().getChat().getPlayerPrefix(p);
			prefix = prefix.concat(p.getName());
			return prefix;
		}else{
			plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.RED, MessageList.ChatPluginNotFound));
			return p.getDisplayName();
		}
	}
	
	public static String LastSeen(Player p){
		long l = p.getLastPlayed();
		Date date = new Date(l);
		return date + "";
	}
	
	public static String GeoIP(Player p){
		if(plugin.getPluginsManager().getGeoIPTools().isGeoIPTools()){
			return plugin.getPluginsManager().getGeoIPTools().getGeoIPLookup().getCountry(p.getAddress().getAddress()).getName();
		}else{
			return "";
		}
	}
	
	private static String getServerIP(){
		String s = Bukkit.getServer().getIp();
		if(s == ""){
			return "localhost" + ":" + Bukkit.getServer().getPort();
		}else{
			return s + ":" + Bukkit.getServer().getPort();
		}
	}
}
