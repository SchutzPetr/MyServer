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

public class ScoreItemsReplacerString {
	private static MyServer plugin;
	private static String message;
	
	public ScoreItemsReplacerString(MyServer instance){
		plugin = instance;
	}
	public static String getReplacedString(String key, Player p){
		message = key;
		if(plugin.getPluginsManager().getVault().getEconomy() != null){
			message = getEconomyItems(message, p);
		}
		if(plugin.getPluginsManager().getHeroes().isHeroes() && plugin.getPluginsManager().getHeroes().getCharacterManager() != null){
			message = getHeroesItems(message, p);
		}
		if(plugin.getPluginsManager().getmcMMO().isMcmmo() && plugin.getPluginsManager().getmcMMO().getExperienceAPI() != null){
			message = getMcmmoItems(message, p);
		}
		if(plugin.getPluginsManager().getStats().isStats() && plugin.getPluginsManager().getStats().getStatsAPI() != null){
			message = getStatsItems(message, p);
		}
		if(plugin.getPluginsManager().getPvPStats().isPvpstats() && plugin.getPluginsManager().getPvPStats().getPSMySQL() != null){
			message = getPvPStatsItems(message, p);
		}
		if(plugin.getPluginsManager().getFactions().isFactions() && plugin.getPluginsManager().getFactions().getUplayer(p) != null && plugin.getPluginsManager().getFactions().getFaction(p) != null){
			message = getFactionItems(message, p);
		}
		if(plugin.getPluginsManager().getSimpleClans().isIsclan() && plugin.getPluginsManager().getSimpleClans().getClanManager().getClanPlayer(p) != null && plugin.getPluginsManager().getSimpleClans().getClanManager().getClanPlayer(p).getClan() != null){
			message = SimpleClansItems(message, p);
		}
		//message = getLocalItems(message, p);
		return getLocalItems(message, p);
	}

	//ConfigurableMessages
	private static String getLocalItems(String key, Player p){
		message = key;
		message = message.replace(ScoreItemsList.TPS, TicksPerSecondTask.getTPS(20) + "");
		message = message.replace(ScoreItemsList.Online, Bukkit.getOnlinePlayers().length + "");
		message = message.replace(ScoreItemsList.PING, CompatibilityManager.getPing(p) + "");
		message = message.replace(ScoreItemsList.FREE_RAM, (Runtime.getRuntime().freeMemory() / 1024 / 1024) + "");
		message = message.replace(ScoreItemsList.MAX_RAM,Runtime.getRuntime().maxMemory() / 1024 / 1024 + "");
		message = message.replace(ScoreItemsList.USED_RAM, (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024 + "");
		message = message.replace(ScoreItemsList.USED_RAM_PERCENT, ((Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory()) * 100 / Runtime.getRuntime().maxMemory()) + "");
		message = message.replace(ScoreItemsList.DATE, Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "");
		message = message.replace(ScoreItemsList.EXP, p.getTotalExperience() + "");
		message = message.replace(ScoreItemsList.XPTOLEVEL, p.getExpToLevel() + "");
		message = message.replace(ScoreItemsList.Health,  p.getHealth() + "");
		return message;
	}
	
	 //Economy
	private static String getEconomyItems(String key, Player p){
		message = key;
		message = message.replace(ScoreItemsList.Balance, plugin.getPluginsManager().getVault().getEconomy().getBalance(p.getName()) + "");
		return message;
	}
	
	//Heroes
	private static String getHeroesItems(String key, Player p){
		message = key;
		message = message.replace(ScoreItemsList.LEVEL, plugin.getPluginsManager().getHeroes().getCharacterManager().getHero( p).getLevel() + "");
		message = message.replace(ScoreItemsList.MANA, plugin.getPluginsManager().getHeroes().getCharacterManager().getHero(p).getMana() + "");
		message = message.replace(ScoreItemsList.MAX_MANA, plugin.getPluginsManager().getHeroes().getCharacterManager().getHero(p).getMaxMana() + "");
		message = message.replace(ScoreItemsList.MANA_REGEN, plugin.getPluginsManager().getHeroes().getCharacterManager().getHero(p).getManaRegen() + "");
        return message;
    }
	
	//mcMMO
	private static String getMcmmoItems(String key, Player p){
		message = key;
		message = message.replace(ScoreItemsList.WOODCUTTING, ExperienceAPI.getLevel(p, "WOODCUTTING") + "");
		message = message.replace(ScoreItemsList.ACROBATICS, ExperienceAPI.getLevel(p, "ACROBATICS") + "");
		message = message.replace(ScoreItemsList.ARCHERY, ExperienceAPI.getLevel(p, "ARCHERY") + "");
		message = message.replace(ScoreItemsList.AXES, ExperienceAPI.getLevel(p, "AXES") + "");
		message = message.replace(ScoreItemsList.EXCAVATION, ExperienceAPI.getLevel(p, "EXCAVATION") + "");
		message = message.replace(ScoreItemsList.FISHING, ExperienceAPI.getLevel(p, "FISHING") + "");
		message = message.replace(ScoreItemsList.HERBALISM, ExperienceAPI.getLevel(p, "HERBALISM") + "");
		message = message.replace(ScoreItemsList.MINING, ExperienceAPI.getLevel(p, "MINING") + "");
		message = message.replace(ScoreItemsList.REPAIR, ExperienceAPI.getLevel(p, "REPAIR") + "");
		message = message.replace(ScoreItemsList.TAMING, ExperienceAPI.getLevel(p, "TAMING") + "");
		message = message.replace(ScoreItemsList.UNARMED, ExperienceAPI.getLevel(p, "UNARMED") + "");
		message = message.replace(ScoreItemsList.SMELTING, ExperienceAPI.getLevel(p, "SMELTING") + "");
		message = message.replace(ScoreItemsList.SWORDS, ExperienceAPI.getLevel(p, "SWORDS") + "");
		message = message.replace(ScoreItemsList.SALVAGE, ExperienceAPI.getLevel(p, "SALVAGE") + "");
		message = message.replace(ScoreItemsList.ALCHEMY, ExperienceAPI.getLevel(p, "ALCHEMY") + "");
		message = message.replace(ScoreItemsList.POWLVL, ExperienceAPI.getPowerLevel(p) + "");
        return message;
    }

	private static String getStatsItems(String key, Player p){
		message = key;
		message = message.replace(ScoreItemsList.PlayTime, plugin.getPluginsManager().getStats().getStatsAPI().getPlaytime(p.getName())/3600 + "");
		message = message.replace(ScoreItemsList.TotalBlockBreake, plugin.getPluginsManager().getStats().getStatsAPI().getTotalBlocksBroken(p.getName()) + "");
		message = message.replace(ScoreItemsList.TotalBlockPlace, plugin.getPluginsManager().getStats().getStatsAPI().getTotalBlocksPlaced(p.getName()) + "");
		message = message.replace(ScoreItemsList.TotalDeaths, plugin.getPluginsManager().getStats().getStatsAPI().getTotalDeaths(p.getName()) + "");
		message = message.replace(ScoreItemsList.TotalKills, plugin.getPluginsManager().getStats().getStatsAPI().getTotalKills(p.getName()) + "");
		return message;
		
	}
	
	private static String getPvPStatsItems(String key, Player p){
		message = key;
		message = message.replace(ScoreItemsList.Deaths, PSMySQL.getEntry(p.getName(), "deaths") + "");
		message = message.replace(ScoreItemsList.Kills, PSMySQL.getEntry(p.getName(), "kills") + "");
		message = message.replace(ScoreItemsList.Streak, PSMySQL.getEntry(p.getName(), "streak") + "");
		return message;
	}
	
	private static String getFactionItems(String key, Player p){
		message = key;
		UPlayer uplayer = UPlayer.get(p);
		Faction faction = uplayer.getFaction();
		message = message.replace(ScoreItemsList.FPower, uplayer.getPowerRounded() + "");
		message = message.replace(ScoreItemsList.FactionPower, faction.getPowerRounded() + "");
		message = message.replace(ScoreItemsList.MembersOnline, faction.getOnlinePlayers().size() + "");
		message = message.replace(ScoreItemsList.Members, faction.getUPlayers().size() + "");
		return message;
	}
	
	private static String SimpleClansItems(String key, Player p){
		message = key;
		ClanPlayer clanPlayer = plugin.getPluginsManager().getSimpleClans().getClanManager().getClanPlayer(p);
		if(clanPlayer == null)return "null!";
		Clan clan = clanPlayer.getClan();
		if(clan == null)return "null!";
		message = message.replace(ScoreItemsList.SimpleKills, clanPlayer.getCivilianKills() + clanPlayer.getRivalKills() + clanPlayer.getNeutralKills() + "");
		message = message.replace(ScoreItemsList.SimpleDeaths, clanPlayer.getDeaths() + "");
		message = message.replace(ScoreItemsList.SimpleKDR, clanPlayer.getKDR() + "");
		message = message.replace(ScoreItemsList.SimpleClanKDR, clan.getTotalKDR() + "");
		message = message.replace(ScoreItemsList.SimpleMembers, clan.getMembers().size() + "");
		message = message.replace(ScoreItemsList.SimpleMoney, clan.getBalance() + "");
		message = message.replace(ScoreItemsList.SimpleRivals, clan.getRivals().size() + "");
		message = message.replace(ScoreItemsList.SimpleAllies, clan.getAllies().size() + "");
		message = message.replace(ScoreItemsList.SimpleMembersOnline, clan.getOnlineMembers().size() + "");
		message = message.replace(ScoreItemsList.SimpleAlliesTotal, clan.getAllAllyMembers().size() + "");
		message = message.replace(ScoreItemsList.SimpleClanKills, clan.getTotalCivilian() + clan.getTotalNeutral() + clan.getTotalRival() + "");
		message = message.replace(ScoreItemsList.SimpleClanDeaths, clan.getTotalDeaths() + "");
		return message;
		
	}
	
	
	
}
