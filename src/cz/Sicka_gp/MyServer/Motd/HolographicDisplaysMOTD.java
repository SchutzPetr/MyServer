package cz.Sicka_gp.MyServer.Motd;

import org.bukkit.entity.Player;

import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.Configuration.ConfigSettings;
import cz.Sicka_gp.MyServer.utils.HolographicDisplaysManager;
import cz.Sicka_gp.MyServer.utils.HolographicSettings;
import cz.Sicka_gp.MyServer.utils.Replacer;

public class HolographicDisplaysMOTD {
	/*
	 * 
	 * @Not Compled
	 */
	private static MyServer plugin;
	private static int DisplayLength;
	private static boolean enable = true;
	
	
	
	public HolographicDisplaysMOTD(MyServer instance){
		plugin = instance;
		DisplayLength = ConfigSettings.HolographicMOTD_DisplayLength;
	}
	
	public static void CreateMOTD(final Player p){
		if(!enable){
			return;
		}
		for(String key : HolographicSettings.getHolographicMessagesMAP().keySet()){
			HolographicDisplaysManager.CreateHologram(p, key, HolographicSettings.getHolographicTitle(key));
			for(String message : HolographicSettings.getHolographicMessages(key)){
				HolographicDisplaysManager.AddLineHologram(p, key, Replacer.replaceString(message, p));
			}
			SetLocationHologram(p, key, HolographicSettings.getHolographicMessages(key).size(), HolographicSettings.getHolographicLocation(key).get("x"), HolographicSettings.getHolographicLocation(key).get("y"), HolographicSettings.getHolographicLocation(key).get("z"));
			HolographicDisplaysManager.UpdateHologram(p, key);
		}
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){

			@Override
			public void run() {
				HolographicDisplaysManager.DeleteAllPlayerHologram(p);
				
			}
			
		}, 20*DisplayLength);
	}
	
	public static void SetLocationHologram(Player p, String name, int size){
		final int bearing = (int)(p.getLocation().getYaw() + 180 + 360) % 360;
		double loc = (double)size / 4;
		if (bearing < 23){
			//N
			HolographicDisplaysManager.SetLocationHologram(p, name, p.getEyeLocation().add(0.0D, loc, -5.0D));
		}
		else if (bearing < 68){
			//NE
			HolographicDisplaysManager.SetLocationHologram(p, name, p.getEyeLocation().add(5.0D, loc, -5.0D));
		}
		else if (bearing < 113){
			//E
			HolographicDisplaysManager.SetLocationHologram(p, name, p.getEyeLocation().add(5.0D, loc, 0.0D));
		}
		else if (bearing < 158){
			//SE
			HolographicDisplaysManager.SetLocationHologram(p, name, p.getEyeLocation().add(5.0D, loc, 5.0D));
		}
		else if (bearing < 203){
			//S
			HolographicDisplaysManager.SetLocationHologram(p, name, p.getEyeLocation().add(0.0D, loc, 5.0D));
		}
		else if (bearing < 248){
			//SW
			HolographicDisplaysManager.SetLocationHologram(p, name, p.getEyeLocation().add(-5.0D, loc, 5.0D));
		}
		else if (bearing < 293){
			//W
			HolographicDisplaysManager.SetLocationHologram(p, name, p.getEyeLocation().add(-5.0D, loc, 0.0D));
		}
		else if (bearing < 338){
			//NW
			HolographicDisplaysManager.SetLocationHologram(p, name, p.getEyeLocation().add(-5.0D, loc, -5.0D));
		}
		else{
			//N
			HolographicDisplaysManager.SetLocationHologram(p, name, p.getEyeLocation().add(0.0D, loc, -5.0D));
		}
	}
	
	
	private static void NorthEast(Player p, String name, int size, int distance, int height, int displacement){
		double x = 5.0D + displacement + distance;
		double y = (size / 4) + height;
		double z = -5.0D + KratMinusJedna(distance) + displacement;
		
		HolographicDisplaysManager.SetLocationHologram(p, name, p.getEyeLocation().add(x, y, z));
	}
	
	private static void SouthEast(Player p, String name, int size, int distance, int height, int displacement){
		double x = 5.0D + KratMinusJedna(displacement) + distance;
		double y = (size / 4) + height;
		double z = 5.0D + displacement + distance;
		
		HolographicDisplaysManager.SetLocationHologram(p, name, p.getEyeLocation().add(x, y, z));
	}
	
	private static void SouthWest(Player p, String name, int size, int distance, int height, int displacement){
		double x = -5.0D + KratMinusJedna(displacement) + KratMinusJedna(distance);
		double y = (size / 4) + height;
		double z = 5.0D + KratMinusJedna(displacement) + distance;
		
		HolographicDisplaysManager.SetLocationHologram(p, name, p.getEyeLocation().add(x, y, z));
	}
	
	private static void NorthWest(Player p, String name, int size, int distance, int height, int displacement){
		double x = -5.0D + KratMinusJedna(distance) + displacement;
		double y = (size / 4) + height;
		double z = -5.0D + KratMinusJedna(displacement) + KratMinusJedna(distance);
		
		HolographicDisplaysManager.SetLocationHologram(p, name, p.getEyeLocation().add(x, y, z));
	}
	
	private static void North(Player p, String name, int size, int distance, int height, int displacement){
		double x = 0.0D + displacement;
		double y = (size / 4) + height;
		double z = -5.0D + KratMinusJedna(distance);
		
		HolographicDisplaysManager.SetLocationHologram(p, name, p.getEyeLocation().add(x, y, z));
	}
	
	private static void East(Player p, String name, int size, int distance, int height, int displacement){
		double x = 5.0D + distance;
		double y = (size / 4) + height;
		double z = 0.0D + displacement;
		
		HolographicDisplaysManager.SetLocationHologram(p, name, p.getEyeLocation().add(x, y, z));
	}
	
	private static void South(Player p, String name, int size, int distance, int height, int displacement){
		double x = 0.0D + KratMinusJedna(displacement);
		double y = (size / 4) + height;
		double z = 5.0D + distance;
		
		HolographicDisplaysManager.SetLocationHologram(p, name, p.getEyeLocation().add(x, y, z));
	}
	
	private static void West(Player p, String name, int size, int distance, int height, int displacement){
		double x = -5.0D + KratMinusJedna(distance);
		double y = (size / 4) + height;
		double z = 0.0D + KratMinusJedna(displacement);
		
		HolographicDisplaysManager.SetLocationHologram(p, name, p.getEyeLocation().add(x, y, z));
	}
	
	
	
	public static void SetLocationHologram(Player p, String name, int size, int distance, int y, int displacement){
		final int bearing = (int)(p.getLocation().getYaw() + 180 + 360) % 360;
		if (bearing < 23){
			//N
			North(p, name, size, distance, y, displacement);
		}
		else if (bearing < 68){
			//NE
			NorthEast(p, name, size, distance, y, displacement);
		}
		else if (bearing < 113){
			//E
			East(p, name, size, distance, y, displacement);
		}
		else if (bearing < 158){
			//SE
			SouthEast(p, name, size, distance, y, displacement);
		}
		else if (bearing < 203){
			//S
			South(p, name, size, distance, y, displacement);
		}
		else if (bearing < 248){
			//SW
			SouthWest(p, name, size, distance, y, displacement);
		}
		else if (bearing < 293){
			//W
			West(p, name, size, distance, y, displacement);
		}
		else if (bearing < 338){
			NorthWest(p, name, size, distance, y, displacement);
		}
		else{
			//N
			North(p, name, size, distance, y, displacement);
		}
	}
	
	
	private static double KratMinusJedna(double d){
		return d *= -1;
	}
	
	public static String getDirection(Player p){
		final int bearing = (int)(p.getLocation().getYaw() + 180 + 360) % 360;
		String dir;
		if (bearing < 23){
			dir = "N";
		}
		else if (bearing < 68){
			dir = "NE";
		}
		else if (bearing < 113){
			dir = "E";
		}
		else if (bearing < 158){
			dir = "SE";
		}
		else if (bearing < 203){
			dir = "S";
		}
		else if (bearing < 248){
			dir = "SW";
		}
		else if (bearing < 293){
			dir = "W";
		}
		else if (bearing < 338){
			dir = "NW";
		}
		else{
			dir = "N";
		}
		return dir;
	}
}
