package cz.Sicka_gp.MyServer.HookedPlugins.Plugins;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;




import com.herocraftonline.heroes.Heroes;
import com.herocraftonline.heroes.characters.CharacterManager;

import cz.Sicka_gp.MyServer.MyServer;

public class HeroesPlugin {
	private static CharacterManager heroes;
	private static boolean isHeroes;
	private static MyServer plugin;
	
	public HeroesPlugin(MyServer instance){
		plugin = instance;
		CheckHeroes();
	}

	private static void CheckHeroes(){
    	final PluginManager pm = Bukkit.getServer().getPluginManager();
    	Plugin heroespl = pm.getPlugin("Heroes");
    	
    	if(heroespl != null && heroespl.isEnabled()){
    		heroes = Heroes.getInstance().getCharacterManager();
    		isHeroes = pm.getPlugin("Heroes") != null;
    		plugin.getPluginsManager().getPluginList().add(heroespl);
    	}
	}

	public CharacterManager getCharacterManager() {
		return heroes;
	}

	public boolean isHeroes() {
		return isHeroes;
	}
}
