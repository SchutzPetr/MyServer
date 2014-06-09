package cz.Sicka_gp.MyServer.Motd;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.bukkit.entity.Player;

import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.Configuration.ConfigSettings;

public class ServerListMotd {
	private static MyServer plugin;
	private static List<String> motdlist;
	public static boolean PersonalMOTD_Enable;
	public static Map<String, String> pdata = new HashMap<String, String>();
	private static String PersonalMOTD_Unknown;
	
	public ServerListMotd(MyServer instance) {
		plugin = instance;
		init();
	}
	
	public static void init(){
		PersonalMOTD_Enable = ConfigSettings.PersonalMOTD_Enable;
		PersonalMOTD_Unknown = ConfigSettings.PersonalMOTD_Unknown;
		if(PersonalMOTD_Enable){
			motdlist = plugin.getConfigManager().getMOTDConfig().getConfig().getStringList("ServerListMotd.PersonalMOTD.Messages");
			LoadPlayerData();
			if(motdlist.isEmpty()){
				motdlist.add("&4Error!");
			}
		}else{
			motdlist = plugin.getConfigManager().getMOTDConfig().getConfig().getStringList("ServerListMotd.DefaultMOTD.Messages");
			if(motdlist.isEmpty()){
				motdlist.add("&4Error!");
			}
		}
		
	}
	
	public static void AddToPData(Player p){
		String ip = p.getAddress().getAddress().toString();
		ip = ip.replaceAll("/", "");
	    ip = ip.replaceAll("\\.", "-");
	    if(!pdata.containsKey(ip)){
	    	pdata.put(ip, p.getName());
	    }
	}
	
	public static String ServerPingMOTD(){
		int i = (int) (Math.random() * motdlist.size());
		String motd = motdlist.get(i);
		return motd;
	}
	
	public static String ServerPingMOTDGuest(){
		return PersonalMOTD_Unknown;
	}
	
	public static String PlayerUniqueMOTD(){
		return null;
	}
	
	
	public static BufferedImage getPlayerUniqueImg(String ip, String name){
		try{
			URL url = new URL("http://s3.amazonaws.com/MinecraftSkins/" + name + ".png");
	    	BufferedImage bimg = ImageIO.read(url);
	    	Image img = bimg.getSubimage(8, 8, 8, 8);
	    	Image img2 = bimg.getSubimage(40, 8, 8, 8);
	    	BufferedImage fimg = new BufferedImage(64, 64, 4);
	    	fimg.getGraphics().drawImage(img, 0, 0, 64, 64, null);
	    	fimg.getGraphics().drawImage(img2, 0, 0, 64, 64, null);
	    	return fimg;
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static void LoadPlayerData(){
		File pldata = new File(plugin.getDataFolder(), "PlayerData");
		if (!pldata.exists()) {
			plugin.saveResource("PlayerData", true);
			//TODO: ConfigurableMessages.log.info(MessagesList.CreateMessages);
		}
		try{
			BufferedReader br = new BufferedReader(new FileReader("plugins/MyServer/PlayerData"));
			String line;
			while ((line = br.readLine()) != null){
				String[] args = line.split("[,]", 2);
				if(args.length == 2){
					String ip = args[0].replaceAll(" ", "");
					String pname = args[1].replaceAll(" ", "");
					pdata.put(ip, pname);
				}
			}
			br.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void savePlayerData(){
		if(!PersonalMOTD_Enable || pdata.isEmpty()){
			return;
		}
		File pldata = new File(plugin.getDataFolder(), "PlayerData");
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(pldata));
			for(String pname : pdata.keySet()){
				bw.write(pname + "," + pdata.get(pname));
				bw.newLine();
			}
			bw.flush();
			bw.close();
		}catch(IOException e){
			e.printStackTrace();
			
		}
	}

}
