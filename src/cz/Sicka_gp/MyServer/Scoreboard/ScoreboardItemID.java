package cz.Sicka_gp.MyServer.Scoreboard;

import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.utils.ScoreItemsList;

public class ScoreboardItemID {
	MyServer plugin;
	
	public ScoreboardItemID(MyServer instance){
		plugin = instance;
	}
	
	
	public static byte ReplaceScoreboardItemToID(String key){
		if(ScoreItemsList.TPS.equals(key)){
			return 1;
		}
		else if(ScoreItemsList.Online.equals(key)){
			return 2;
		}
		else if(ScoreItemsList.PING.equals(key)){
			return 3;
		}
		else if(ScoreItemsList.FREE_RAM.equals(key)){
			return 4;
	    }
		else if(ScoreItemsList.MAX_RAM.equals(key)){
	    	return 5;
	    }
		else if(ScoreItemsList.USED_RAM.equals(key)){
	    	return 6;
	    }
		else if(ScoreItemsList.USED_RAM_PERCENT.equals(key)){
	    	return 7;
	    }
		else if(ScoreItemsList.DATE.equals(key)){
	    	return 8;
	    }
		else if(ScoreItemsList.EXP.equals(key)){
            return 9;
        }
		else if(ScoreItemsList.XPTOLEVEL.equals(key)){
            return 10;
        }
		else if(ScoreItemsList.Health.equals(key)){
        	return 11;
        }
		else if(ScoreItemsList.X.equals(key)) {
			return 12;
        }
		else if(ScoreItemsList.Y.equals(key)) {
			return 13;
        }
		else if(ScoreItemsList.Z.equals(key)) {
			return 14;
        }
        //Vault
		else if(ScoreItemsList.Balance.equals(key)){
			return 20;
		}
		//mcMMO
		else if(ScoreItemsList.POWLVL.equals(key)){
			return 25;
		}
		else if(ScoreItemsList.WOODCUTTING.equals(key)){
			return 26;
        }
		else if(ScoreItemsList.ACROBATICS.equals(key)){
			return 27;
        }
		else if(ScoreItemsList.ARCHERY.equals(key)){
			return 28;
        }
		else if(ScoreItemsList.AXES.equals(key)){
			return 29;
        }
		else if(ScoreItemsList.EXCAVATION.equals(key)){
			return 30;
        }
		else if(ScoreItemsList.FISHING.equals(key)){
			return 31;
        }
		else if(ScoreItemsList.HERBALISM.equals(key)){
			return 32;
        }
		else if(ScoreItemsList.MINING.equals(key)){
			return 33;
        }
		else if(ScoreItemsList.SMELTING.equals(key)){
			return 34;
        }
		else if(ScoreItemsList.SWORDS.equals(key)){
			return 35;
        }
		else if(ScoreItemsList.TAMING.equals(key)){
			return 36;
        }
		else if(ScoreItemsList.UNARMED.equals(key)){
			return 37;
        }
		else if(ScoreItemsList.REPAIR.equals(key)){
			return 38;
        }
		//Heroes
		else if(ScoreItemsList.MANA.equals(key)){
			return 45;
	    }
		else if(ScoreItemsList.MAX_MANA.equals(key)){
			return 46;
	    }
		else if(ScoreItemsList.LEVEL.equals(key)){
			return 47;
		}
		else if(ScoreItemsList.MANA_REGEN.equals(key)){
			return 48;
	    }
		//Stats
		else if(ScoreItemsList.PlayTime.equals(key)){
			return 50;
		}
		else if(ScoreItemsList.TotalBlockBreake.equals(key)){
			return 51;
		}
		else if(ScoreItemsList.TotalBlockPlace.equals(key)){
			return 52;
		}
		else if(ScoreItemsList.TotalDeaths.equals(key)){
			return 53;
		}
		else if(ScoreItemsList.TotalKills.equals(key)){
			return 54;
		}
		//PvpStats
		else if(ScoreItemsList.Deaths.equals(key)){
			return 70;
		}
		else if(ScoreItemsList.Kills.equals(key)){
			return 71;
		}
		else if(ScoreItemsList.Streak.equals(key)){
			return 72;
		}
		//Faction
		else if(ScoreItemsList.FPower.equals(key)) {
	        return 75;
	    }
		else if(ScoreItemsList.FactionPower.equals(key)) {
			return 76;
	    }
		else if(ScoreItemsList.MembersOnline.equals(key)) {
			return 77;
	    }
		else if(ScoreItemsList.Members.equals(key)) {
			return 78;
	    }
		//SimpleClans
		else if(ScoreItemsList.SimpleKills.equals(key)){
			return 80;
		}
		else if(ScoreItemsList.SimpleDeaths.equals(key)){
        	return 81;
		}
		else if(ScoreItemsList.SimpleKDR.equals(key)){
        	return 82;
        }
		else if(ScoreItemsList.SimpleClanKDR.equals(key)){
        	return 83;
        }
		else if(ScoreItemsList.SimpleMembers.equals(key)){
        	return 84;
        }
		else if(ScoreItemsList.SimpleMoney.equals(key)){
        	return 85;
        }
		else if(ScoreItemsList.SimpleRivals.equals(key)){
        	return 86;
        }
		else if(ScoreItemsList.SimpleAllies.equals(key)){
        	return 87;
        }
		else if(ScoreItemsList.SimpleMembersOnline.equals(key)){
        	return 88;
        }
		else if(ScoreItemsList.SimpleAlliesTotal.equals(key)){
        	return 89;
        }
		else if(ScoreItemsList.SimpleClanKills.equals(key)){
        	return 90;
        }
		else if(ScoreItemsList.SimpleClanDeaths.equals(key)){
        	return 91;
        }
		
		return -1;
	}

}
