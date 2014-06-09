package cz.Sicka_gp.MyServer.Automessages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.Permissions;
import cz.Sicka_gp.MyServer.Configuration.ConfigSettings;

public class Automessages {
	public static MyServer plugin;
	public static int currentLine = 0;
	public static int tid = 0;
	public static int running = 1;
	public static long AutoMessage_Interval;
	private static BufferedReader br;
	private static LineNumberReader lnr;
	private static String AutoMessage_Prefix;
	private static boolean AutoMessage_Enable;
	boolean enable;
	
	public Automessages(MyServer instance){
		plugin = instance;
		init();
	}
	
	public static void init(){
		AutoMessage_Interval = ConfigSettings.AutoMessage_Interval;
		AutoMessage_Prefix = ConfigSettings.AutoMessage_Prefix;
		AutoMessage_Enable = ConfigSettings.AutoMessage_Enable;
		if(!AutoMessage_Enable){
			if(Automessages.running == 1){
				Bukkit.getServer().getScheduler().cancelTask(Automessages.tid);
				Automessages.running = 0;
			}
		}else{
			if(Automessages.running != 1){
				Automessages.running = 1;
				StartBroadcast();
			}
		}
	}
	
	
	public static void StartBroadcast(){
		tid = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){

			@Override
			public void run() {
				try{
					Automessages.broadcastMessages("plugins/ConfigurableMessages/messages.txt");
				} catch(IOException e){
					
				}
				
			}
			
		}, 0, AutoMessage_Interval * 20);
	}


	protected static void broadcastMessages(String filename) throws IOException {
		FileInputStream fs;
		fs = new FileInputStream(filename);
		br = new BufferedReader(new InputStreamReader(fs));
		for(int i = 0; i < currentLine; ++i){
			br.readLine();
		}
		String line = br.readLine();
		line = ChatColor.translateAlternateColorCodes("&".charAt(0), line);
		for(Player p : Bukkit.getOnlinePlayers()){
			if(p.hasPermission(Permissions.automessageshow)){
				AutoMessage_Prefix = ChatColor.translateAlternateColorCodes("&".charAt(0), AutoMessage_Prefix);
				p.sendMessage(AutoMessage_Prefix + line);
			}
		}
		lnr = new LineNumberReader(new FileReader(new File(filename)));
		lnr.skip(Long.MAX_VALUE);
		int lastLine = lnr.getLineNumber();
		if(currentLine + 1 == lastLine + 1 ){
			currentLine = 0;
		}else{
			currentLine++;
		}
	}
}
