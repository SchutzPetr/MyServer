package cz.Sicka_gp.MyServer.utils;

import java.util.Calendar;

import net.sacredlabyrinth.phaed.simpleclans.Clan;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import praxis.slipcor.pvpstats.PSMySQL;

import com.gmail.nossr50.api.ExperienceAPI;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.UPlayer;

import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.Compatibility.CompatibilityManager;

public class ScoreItemsReplacerINT {
	//base
	 private static MyServer plugin;
	 
	 public ScoreItemsReplacerINT(MyServer instance){
		 plugin = instance;
	 }
	 
	public static int getReplacedInt(String key, Player p){
			
		 if(plugin.getPluginsManager().getVault().getEconomy() != null){
			 int score = getEconomyItems(key, p);
			 if(score != -1){
				 return score;
			 }
		 }
		 if(plugin.getPluginsManager().getHeroes().isHeroes()){
			 int score =  getHeroesItems(key, p);
			 if(score != -1){
				 return score;
			 }
		 }
		 if(plugin.getPluginsManager().getmcMMO().isMcmmo()){
			 int score =  getMcmmoItems(key, p);
			 if(score != -1){
				 return score;
			 }
		 }
		 if(plugin.getPluginsManager().getStats().isStats()){
			 int score =  getStatsItems(key, p);
			 if(score != -1){
				 return score;
			 }
		 }
		 if(plugin.getPluginsManager().getPvPStats().isPvpstats()){
			 int score =  getPvPStatsItems(key, p);
			 if(score != -1){
				 return score;
			 }
		 }
		 if(plugin.getPluginsManager().getFactions().isFactions()){
			 int score =  getFactionItems(key, p);
			 if(score != -1){
				 return score;
			 }
		 }
		 if(plugin.getPluginsManager().getSimpleClans().getClanManager() != null){
			 int score =  SimpleClansItems(key, p);
			 if(score != -1){
				 return score;
			 }
		 }
		return getLocalItems(key, p);
		 
	 }

	//ConfigurableMessages
	private static int getLocalItems(String key, Player p){
		if(ScoreItemsList.TPS.equals(key)){
			return (int) TicksPerSecondTask.getTPS(20);
		}
		if(ScoreItemsList.Online.equals(key)){
			return Bukkit.getOnlinePlayers().length;
		}
		if(ScoreItemsList.PING.equals(key)){
			return CompatibilityManager.getPing(p);
		}
		if(ScoreItemsList.FREE_RAM.equals(key)) {
			return (int) (Runtime.getRuntime().freeMemory() / 1024 / 1024);
	    }

	    if(ScoreItemsList.MAX_RAM.equals(key)) {
	    	return (int) Runtime.getRuntime().maxMemory() / 1024 / 1024;
	    }
	    
	    if(ScoreItemsList.DATE.equals(key)){
	    	Calendar cal = Calendar.getInstance();
	    	int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
	    	return dayOfMonth;
	    }

	    if(ScoreItemsList.USED_RAM.equals(key)) {
	    	return (int) (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024;
	    }

	    if(ScoreItemsList.USED_RAM_PERCENT.equals(key)) {
	            return (int) ((Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory()) * 100 / Runtime.getRuntime().maxMemory());
	    }
        if(ScoreItemsList.EXP.equals(key)) {
            return p.getTotalExperience();
        }
        if(ScoreItemsList.XPTOLEVEL.equals(key)) {
            return p.getExpToLevel();
        }
        if(ScoreItemsList.Health.equals(key)) {
            return (int) p.getHealth();
        }
        if(ScoreItemsList.X.equals(key)) {
        	return (int) p.getLocation().getX();
        }
        if(ScoreItemsList.Y.equals(key)) {
        	return (int) p.getLocation().getY();
        }
        if(ScoreItemsList.Z.equals(key)) {
        	return (int) p.getLocation().getZ();
        }
		return -1;
	}
	
	 //Economy
	private static int getEconomyItems(String key, Player p){
		if(ScoreItemsList.Balance.equals(key)){
			return (int) plugin.getPluginsManager().getVault().getEconomy().getBalance(p);
		}
		return -1;
	}
	
	//Heroes
	private static int getHeroesItems(String key, Player p) {
		if(ScoreItemsList.LEVEL.equals(key)) {
	            return plugin.getPluginsManager().getHeroes().getCharacterManager().getHero( p).getLevel();
	    }
        if(ScoreItemsList.MANA.equals(key)) {
            return plugin.getPluginsManager().getHeroes().getCharacterManager().getHero(p).getMana();
        }
        if(ScoreItemsList.MAX_MANA.equals(key)) {
            return plugin.getPluginsManager().getHeroes().getCharacterManager().getHero(p).getMaxMana();
        }

        if(ScoreItemsList.MANA_REGEN.equals(key)) {
            return plugin.getPluginsManager().getHeroes().getCharacterManager().getHero(p).getManaRegen();
        }

        return -1;
    }
	
	//mcMMO
	private static int getMcmmoItems(String key, Player p) {
        if(ScoreItemsList.POWLVL.equals(key)) {
            return ExperienceAPI.getPowerLevel(p);
        }

        if(ScoreItemsList.WOODCUTTING.equals(key)) {
            return ExperienceAPI.getLevel(p, "WOODCUTTING");
        }

        if(ScoreItemsList.ACROBATICS.equals(key)) {
            return ExperienceAPI.getLevel(p, "ACROBATICS");
        }

        if(ScoreItemsList.ARCHERY.equals(key)) {
            return ExperienceAPI.getLevel(p, "ARCHERY");
        }

        if(ScoreItemsList.AXES.equals(key)) {
            return ExperienceAPI.getLevel(p, "AXES");
        }

        if(ScoreItemsList.EXCAVATION.equals(key)) {
            return ExperienceAPI.getLevel(p, "EXCAVATION");
        }

        if(ScoreItemsList.FISHING.equals(key)) {
            return ExperienceAPI.getLevel(p, "FISHING");
        }

        if(ScoreItemsList.HERBALISM.equals(key)) {
            return ExperienceAPI.getLevel(p, "HERBALISM");
        }

        if(ScoreItemsList.MINING.equals(key)) {
            return ExperienceAPI.getLevel(p, "MINING");
        }

        if(ScoreItemsList.REPAIR.equals(key)) {
            return ExperienceAPI.getLevel(p, "REPAIR");
        }
        
        if(ScoreItemsList.TAMING.equals(key)) {
            return ExperienceAPI.getLevel(p, "TAMING");
        }

        if(ScoreItemsList.UNARMED.equals(key)) {
            return ExperienceAPI.getLevel(p, "UNARMED");
        }

        if(ScoreItemsList.SMELTING.equals(key)) {
            return ExperienceAPI.getLevel(p, "SMELTING");
        }

        if(ScoreItemsList.SWORDS.equals(key)) {
            return ExperienceAPI.getLevel(p, "SWORDS");
        }
        
        if(ScoreItemsList.SALVAGE.equals(key)) {
            return ExperienceAPI.getLevel(p, "SALVAGE");
        }
        
        if(ScoreItemsList.ALCHEMY.equals(key)) {
            return ExperienceAPI.getLevel(p, "ALCHEMY");
        }

        return -1;
    }

	private static int getStatsItems(String key, Player p){
		if(ScoreItemsList.PlayTime.equals(key)){
			double playtime = plugin.getPluginsManager().getStats().getStatsAPI().getPlaytime(p.getName());
			int a = (int) (playtime/3600);
			return a;
		}
		if(ScoreItemsList.TotalBlockBreake.equals(key)){
			return (int) plugin.getPluginsManager().getStats().getStatsAPI().getTotalBlocksBroken(p.getName());
		}
		if(ScoreItemsList.TotalBlockPlace.equals(key)){
			return (int) plugin.getPluginsManager().getStats().getStatsAPI().getTotalBlocksPlaced(p.getName());
		}
		if(ScoreItemsList.TotalDeaths.equals(key)){
			return (int) plugin.getPluginsManager().getStats().getStatsAPI().getTotalDeaths(p.getName());
		}
		if(ScoreItemsList.TotalKills.equals(key)){
			return (int) plugin.getPluginsManager().getStats().getStatsAPI().getTotalKills(p.getName());
		}
		return -1;
		
	}
	
	private static int getPvPStatsItems(String key, Player p) {
		if(ScoreItemsList.Deaths.equals(key)){
			return PSMySQL.getEntry(p.getName(), "deaths");
		}
		if(ScoreItemsList.Kills.equals(key)){
			return PSMySQL.getEntry(p.getName(), "kills");
		}
		if(ScoreItemsList.Streak.equals(key)){
			return PSMySQL.getEntry(p.getName(), "streak");
		}
		return -1;
	}
	
	private static int getFactionItems(String key, Player p){
		UPlayer uplayer = UPlayer.get(p);
		if(ScoreItemsList.FPower.equals(key)) {
	        return uplayer.getPowerRounded();
	    }
		final Faction faction = uplayer == null ? null : uplayer.getFaction();
		if(ScoreItemsList.FactionPower.equals(key)) {
	        return faction.getPowerRounded();
	    }
		if(ScoreItemsList.MembersOnline.equals(key)) {
	        return faction.getOnlinePlayers().size();
	    }
		if(ScoreItemsList.Members.equals(key)) {
	        return faction.getUPlayers().size();
	    }
		
		return -1;
	}
	
	private static int SimpleClansItems(String key, Player p){
		ClanPlayer clanPlayer = plugin.getPluginsManager().getSimpleClans().getClanManager().getClanPlayer(p);
		if(clanPlayer != null){
			Clan clan = clanPlayer.getClan();
			if(clan != null){
				if(ScoreItemsList.SimpleKills.equals(key)){
					return clanPlayer.getCivilianKills() + clanPlayer.getRivalKills() + clanPlayer.getNeutralKills();
				}
		        if(ScoreItemsList.SimpleDeaths.equals(key)){
		        	return clanPlayer.getDeaths();
				}
		        if(ScoreItemsList.SimpleKDR.equals(key)){
		        	return (int) clanPlayer.getKDR();
		        }
		        if(ScoreItemsList.SimpleClanKDR.equals(key)){
		        	return (int) clan.getTotalKDR();
		        }
		        if(ScoreItemsList.SimpleMembers.equals(key)){
		        	return clan.getMembers().size();
		        }
		        if(ScoreItemsList.SimpleMoney.equals(key)){
		        	return (int) clan.getBalance();
		        }
		        if(ScoreItemsList.SimpleRivals.equals(key)){
		        	return clan.getRivals().size();
		        }
		        if(ScoreItemsList.SimpleAllies.equals(key)){
		        	return clan.getAllies().size();
		        }
		        if(ScoreItemsList.SimpleMembersOnline.equals(key)){
		        	return clan.getOnlineMembers().size();
		        }
		        if(ScoreItemsList.SimpleAlliesTotal.equals(key)){
		        	return clan.getAllAllyMembers().size();
		        }
		        if(ScoreItemsList.SimpleClanKills.equals(key)){
		        	return clan.getTotalCivilian() + clan.getTotalNeutral() + clan.getTotalRival();
		        }
		        if(ScoreItemsList.SimpleClanDeaths.equals(key)){
		        	return clan.getTotalDeaths();
		        }
			}
			return -1;
		}

		return -1;
		
	}
	
	
	
}
