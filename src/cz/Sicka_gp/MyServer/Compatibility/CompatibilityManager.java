package cz.Sicka_gp.MyServer.Compatibility;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CompatibilityManager {
	private static Compatibility compability;
	private static String bukkitVersion;
	static {
        final String packageName = Bukkit.getServer().getClass().getPackage().getName();
        bukkitVersion = packageName.substring(packageName.lastIndexOf('.') + 1);

        initcompability();
    }
	
	 private static void initcompability() {
		 try{
			 compability = (Compatibility)Class.forName("cz.Sicka_gp.MyServer.Compatibility.Bukkit" + bukkitVersion).newInstance();
			 
			 return;
		}catch(ClassNotFoundException e){
			 e.printStackTrace();
		} catch (InstantiationException e) {
			 e.printStackTrace();
		} catch (IllegalAccessException e) {
			 e.printStackTrace();
		}
	 }
	 
	 public static int getPing(Player p) {
		 return compability.getPing(p);
	 }
	
}
