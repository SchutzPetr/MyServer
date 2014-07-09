package cz.Sicka_gp.MyServer.Scoreboard;

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
import cz.Sicka_gp.MyServer.utils.TicksPerSecondTask;

public class ScoreboardItemsIDReplacer {
	private static MyServer plugin;
	
	public ScoreboardItemsIDReplacer(MyServer instance){
		plugin = instance;
	}
	//base
	 public static int getReplacedInt(byte key, Player p){
		 if(key >= 1 && key <= 19){
			 int score = getLocalItems(key, p);
			 if(score != -1){
				 return score;
			 }
		 }
		 else if(key >= 20 && key <= 24){
			 if(plugin.getPluginsManager().getVault().getEconomy() != null){
				 int score = getEconomyItems(key, p);
				 if(score != -1){
					 return score;
				 }
			 }
		 }
		 else if(key >= 25 && key <= 44){
			 if(plugin.getPluginsManager().getmcMMO().isMcmmo()){
				 int score =  getMcmmoItems(key, p);
				 if(score != -1){
					 return score;
				 }
			 }
         }
         else if(key >= 45 && key <= 49){
        	 if(plugin.getPluginsManager().getHeroes().isHeroes()){
    			 int score =  getHeroesItems(key, p);
    			 if(score != -1){
    				 return score;
    			 }
    		 }
         }
         else if(key >= 50 && key <= 69){
        	 if(plugin.getPluginsManager().getStats().isStats()){
    			 int score =  getStatsItems(key, p);
    			 if(score != -1){
    				 return score;
    			 }
    		 }
         }
         else if(key >= 70 && key <= 74){
        	 if(plugin.getPluginsManager().getPvPStats().isPvpstats()){
    			 int score =  getPvPStatsItems(key, p);
    			 if(score != -1){
    				 return score;
    			 }
    		 }
         }
         else if(key >= 75 && key <= 79){
        	 if(plugin.getPluginsManager().getFactions().isFactions()){
    			 int score =  getFactionItems(key, p);
    			 if(score != -1){
    				 return score;
    			 }
    		 }
         }
         else if(key >= 80 && key <= 94){
        	 if(plugin.getPluginsManager().getSimpleClans().getClanManager() != null){
    			 int score =  SimpleClansItems(key, p);
    			 if(score != -1){
    				 return score;
    			 }
    		 }
         }
		 return -1;
	 }

	//ConfigurableMessages
	private static int getLocalItems(byte key, Player p){
		if(key == 1){
			return (int) TicksPerSecondTask.getTPS(20);
		}
		
		else if(key == 2){
			return Bukkit.getOnlinePlayers().length;
		}
		
		else if(key == 3){
			return CompatibilityManager.getPing(p);
		}
		
		else if(key == 4){
			return (int) (Runtime.getRuntime().freeMemory() / 1024 / 1024);
	    }
		
		else if(key == 5){
	    	return (int) Runtime.getRuntime().maxMemory() / 1024 / 1024;
	    }
		
		else if(key == 6){
	    	return (int) (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024;
	    }
		
		else if(key == 7){
	        return (int) ((Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory()) * 100 / Runtime.getRuntime().maxMemory());
	    }
		
		else if(key == 8){
	    	Calendar cal = Calendar.getInstance();
	    	int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
	    	return dayOfMonth;
	    }
		
		else if(key == 9){
            return p.getTotalExperience();
        }
		
		else if(key == 10){
            return p.getExpToLevel();
        }
		
		else if(key == 11){
            return (int) p.getHealth();
        }
		
		else if(key == 12){
        	return (int) p.getLocation().getX();
        }
		
		else if(key == 13){
        	return (int) p.getLocation().getY();
        }
		
		else if(key == 14){
        	return (int) p.getLocation().getZ();
        }
		
		return -1;
	}
	
	 //Economy
	private static int getEconomyItems(byte key, Player p){
		if(key == 20){
			return (int) plugin.getPluginsManager().getVault().getEconomy().getBalance(p);
		}
		
		return -1;
	}
	
	//mcMMO
	private static int getMcmmoItems(byte key, Player p) {
		if(key == 25){
			return ExperienceAPI.getPowerLevel(p);
        }

		else if(key == 26){
            return ExperienceAPI.getLevel(p, "WOODCUTTING");
        }

		else if(key == 27){
            return ExperienceAPI.getLevel(p, "ACROBATICS");
        }

		else if(key == 28){
            return ExperienceAPI.getLevel(p, "ARCHERY");
        }

		else if(key == 29){
            return ExperienceAPI.getLevel(p, "AXES");
        }

		else if(key == 30){
            return ExperienceAPI.getLevel(p, "EXCAVATION");
        }

		else if(key == 31){
            return ExperienceAPI.getLevel(p, "FISHING");
        }

		else if(key == 32){
            return ExperienceAPI.getLevel(p, "HERBALISM");
        }

		else if(key == 33){
            return ExperienceAPI.getLevel(p, "MINING");
        }
		
		else if(key == 34){
            return ExperienceAPI.getLevel(p, "SMELTING");
        }

		else if(key == 35){
            return ExperienceAPI.getLevel(p, "SWORDS");
        }
        
		else if(key == 36){
            return ExperienceAPI.getLevel(p, "TAMING");
        }

		else if(key == 37){
            return ExperienceAPI.getLevel(p, "UNARMED");
        }
        
		else if(key == 38){
            return ExperienceAPI.getLevel(p, "REPAIR");
        }
		
		else if(key == 39){
            return ExperienceAPI.getLevel(p, "ALCHEMY");
        }
		
		else if(key == 40){
            return ExperienceAPI.getLevel(p, "SALVAGE");
        }
        
        return -1;
    }
	
	//Heroes
	private static int getHeroesItems(byte key, Player p) {
		if(key == 45){
            return plugin.getPluginsManager().getHeroes().getCharacterManager().getHero(p).getMana();
        }
        
		else if(key == 46){
            return  plugin.getPluginsManager().getHeroes().getCharacterManager().getHero(p).getMaxMana();
        }
        
		else if(key == 47){
            return plugin.getPluginsManager().getHeroes().getCharacterManager().getHero( p).getLevel();
        }

		else if(key == 48){
            return  plugin.getPluginsManager().getHeroes().getCharacterManager().getHero(p).getManaRegen();
        }

        return -1;
    }

	private static int getStatsItems(byte key, Player p){
		if(key == 50){
			double playtime = plugin.getPluginsManager().getStats().getStatsAPI().getPlaytime(p.getName());
			int a = (int) (playtime/3600);
			return a;
		}
		else if(key == 51){
			return (int) plugin.getPluginsManager().getStats().getStatsAPI().getTotalBlocksBroken(p.getName());
		}
		else if(key == 52){
			return (int) plugin.getPluginsManager().getStats().getStatsAPI().getTotalBlocksPlaced(p.getName());
		}
		else if(key == 53){
			return (int)plugin.getPluginsManager().getStats().getStatsAPI().getTotalDeaths(p.getName());
		}
		else if(key == 54){
			return (int) plugin.getPluginsManager().getStats().getStatsAPI().getTotalKills(p.getName());
		}
		return -1;
		
	}
	
	private static int getPvPStatsItems(byte key, Player p) {
		if(key == 70){
			return PSMySQL.getEntry(p.getName(), "deaths");
		}
		else if(key == 71){
			return PSMySQL.getEntry(p.getName(), "kills");
		}
		else if(key == 72){
			return PSMySQL.getEntry(p.getName(), "streak");
		}
		return -1;
	}
	
	private static int getFactionItems(byte key, Player p){
		UPlayer uplayer = UPlayer.get(p);
		if(uplayer != null){
			if(key == 75){
		        return uplayer.getPowerRounded();
		    }
			
			Faction faction = uplayer.getFaction();
			if(faction != null){
				if(key == 76){
			        return faction.getPowerRounded();
			    }
				
				else if(key == 77){
			        return faction.getOnlinePlayers().size();
			    }
				
				else if(key == 78){
			        return faction.getUPlayers().size();
			    }
				return -1;
			}
			return -1;
		}
		return -1;
	}
	
	private static int SimpleClansItems(byte key, Player p){
		ClanPlayer clanPlayer = plugin.getPluginsManager().getSimpleClans().getClanManager().getClanPlayer(p);
		if(clanPlayer != null){
			if(key == 80){
				return clanPlayer.getCivilianKills() + clanPlayer.getRivalKills() + clanPlayer.getNeutralKills();
			}
			
			else if(key == 81){
	        	return clanPlayer.getDeaths();
			}
	        
			else if(key == 82){
	        	return (int) clanPlayer.getKDR();
	        }
	        
			Clan clan = clanPlayer.getClan();
			if(clan != null){
				if(key == 83){
		        	return (int) clan.getTotalKDR();
		        }
		        
				else if(key == 84){
		        	return clan.getMembers().size();
		        }
		        
				else if(key == 85){
		        	return (int) clan.getBalance();
		        }
		        
				else if(key == 86){
		        	return clan.getRivals().size();
		        }
		        
				else if(key == 87){
		        	return clan.getAllies().size();
		        }
		        
				else if(key == 88){
		        	return clan.getOnlineMembers().size();
		        }
		        
				else if(key == 89){
		        	return clan.getAllAllyMembers().size();
		        }
		        
				else if(key == 90){
		        	return clan.getTotalCivilian() + clan.getTotalNeutral() + clan.getTotalRival();
		        }
		        
				else if(key == 91){
		        	return clan.getTotalDeaths();
		        }
				
				return -1;
			}
			
			return -1;
		}

		return -1;
		
	}
	
	
	
}
