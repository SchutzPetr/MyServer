package cz.Sicka_gp.MyServer.Listener;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;

import cz.Sicka_gp.MyServer.JoinQuitKickMessages;
import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.NewbieKit;
import cz.Sicka_gp.MyServer.TabListManager;
import cz.Sicka_gp.MyServer.Configuration.ConfigSettings;
import cz.Sicka_gp.MyServer.Motd.ChatMotd;
import cz.Sicka_gp.MyServer.Motd.HolographicDisplaysMOTD;
import cz.Sicka_gp.MyServer.Motd.ScoreBoardMOTD;
import cz.Sicka_gp.MyServer.Motd.ServerListMotd;
import cz.Sicka_gp.MyServer.Scoreboard.SBManager;
import cz.Sicka_gp.MyServer.Scoreboard.ScoreboardPlayerData;
import cz.Sicka_gp.MyServer.utils.HolographicDisplaysManager;
import cz.Sicka_gp.MyServer.utils.Replacer;

public class JQKListener implements Listener{
	private static MyServer plugin;
	private static boolean Scoreboard_Enable;
	private static boolean Kits;
	private static boolean FirstMessageEnable;
	private static String Messages_WhitelistMessage;
	private static String Messages_FullServer;
	private static boolean ScoreBoardMotd_Enable;
	private static boolean PersonalMOTD_Faces;
	private static boolean DisableAllJoinQuitKickMSG;
	private static boolean EnableJoinQuitKickMSG;
	private static boolean PersonalMOTD_Enable;
	private static boolean ChatMotd_Enable;
	private Map<String, Long> cooldown = new HashMap<String, Long>();
	private static boolean Messages_EnableTabList;
	
	public JQKListener(MyServer instance) {
		plugin = instance;
		Scoreboard_Enable = ConfigSettings.Scoreboard_Enable;
		Kits = ConfigSettings.Kits;
		FirstMessageEnable = ConfigSettings.FirstMessageEnable;
		Messages_WhitelistMessage = ConfigSettings.Messages_WhitelistMessage;
		Messages_FullServer = ConfigSettings.Messages_FullServer;
		ScoreBoardMotd_Enable = ConfigSettings.ScoreboardMotd_Enable;
		PersonalMOTD_Faces = ConfigSettings.PersonalMOTD_Faces;
		DisableAllJoinQuitKickMSG = ConfigSettings.Messages_DisableAllJoinQuitKickMSG;
		EnableJoinQuitKickMSG = ConfigSettings.Messages_EnableJoinQuitKickMSG;
		PersonalMOTD_Enable = ConfigSettings.PersonalMOTD_Enable;
		ChatMotd_Enable = ConfigSettings.ChatMotd_Enable;
		Messages_EnableTabList = ConfigSettings.Messages_EnableTabList;
		if(DisableAllJoinQuitKickMSG)EnableJoinQuitKickMSG = false;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void  onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(Scoreboard_Enable)SBManager.Sidebar(p);
		if(PersonalMOTD_Enable)ServerListMotd.AddToPData(p);
		if(ScoreBoardMotd_Enable)ScoreBoardMOTD.CreateMotdSidebar(p);
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(p.getUniqueId());
		if(!(offlinePlayer.hasPlayedBefore())){
			if(FirstMessageEnable){
				for(Player pl : Bukkit.getServer().getOnlinePlayers()){
					if(pl.canSee(p)){
						String message = Replacer.replaceJoinQuitmsg(p, "Newbies.FirstMessage");
						pl.sendMessage(message);
					}
				}
			}
			if(Kits){
				NewbieKit.setKits(p);
			}
		}
		if(Messages_EnableTabList){
			TabListManager.TabList(p);
		}
		if(DisableAllJoinQuitKickMSG)e.setJoinMessage(null);
		if(EnableJoinQuitKickMSG){
			e.setJoinMessage(null);
			JoinQuitKickMessages.SendCustomJoinMessage(p);
		}
		if(ChatMotd_Enable)ChatMotd.sendMotdMessage(p);
		if(plugin.getPluginsManager().getHolographicDisplays().isHolographicDisplays()){
			HolographicDisplaysMOTD.CreateMOTD(p);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlayerQuit(PlayerQuitEvent e){
		Player p = e.getPlayer();
		SBManager.RemoveScoreboardAndPlayerData(p);
	    ScoreBoardMOTD.CancelTask(p);
	    if(EnableJoinQuitKickMSG){
			e.setQuitMessage(null);
			JoinQuitKickMessages.SendCustomQuitMessage(p);
		}
	    if(plugin.getPluginsManager().getHolographicDisplays().isHolographicDisplays()){
	    	HolographicDisplaysManager.DeleteAllPlayerHologram(p);
	    }
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlayerKick(PlayerKickEvent e){
		Player p = e.getPlayer();
		SBManager.RemoveScoreboardAndPlayerData(p);
	    ScoreBoardMOTD.CancelTask(p);
	    if(EnableJoinQuitKickMSG){
			e.setLeaveMessage(null);
			JoinQuitKickMessages.SendCustomKickMessage(p, e.getReason());
		}
	    if(plugin.getPluginsManager().getHolographicDisplays().isHolographicDisplays()){
	    	HolographicDisplaysManager.DeleteAllPlayerHologram(p);
	    }
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerLoginEvent(PlayerLoginEvent e){
		Player p = e.getPlayer();
		if(Bukkit.getServer().hasWhitelist() && !p.isWhitelisted()){
			e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, Replacer.replaceColor(Messages_WhitelistMessage));
		}
		if(e.getResult() == PlayerLoginEvent.Result.KICK_FULL){
			e.disallow(PlayerLoginEvent.Result.KICK_FULL, Replacer.replaceColor(Messages_FullServer));
		}
	}
	@EventHandler(priority=EventPriority.NORMAL)
	public void onPlayerChangedWorl(PlayerChangedWorldEvent e){
		Player p = e.getPlayer();
		ScoreboardPlayerData.ReloadPlayerData(e.getFrom().getName(), p);
		ScoreboardPlayerData.CheckPlayerDisableWorlds(p, e.getFrom().getName());
		if(Messages_EnableTabList){
			TabListManager.TabList(p);
		}
	}
	
	@EventHandler(ignoreCancelled=true)
	public void onServerListPingEvent(ServerListPingEvent e){
		if(PersonalMOTD_Enable){
			String ip = e.getAddress().toString();
			ip = ip.replaceAll("/", "");
		    ip = ip.replaceAll("\\.", "-");
		    if(PersonalMOTD_Faces){
		    	Long time = System.currentTimeMillis();
			    if(cooldown.containsKey(ip)){
		    		Long lastUsage = cooldown.get(ip);
					if(lastUsage + 5*1000 > time){
						if(ServerListMotd.pdata.containsKey(ip)){
							e.setMotd(Replacer.replaceServePingMOTD(ServerListMotd.ServerPingMOTD(), ServerListMotd.pdata.get(ip)));
						}else{
							e.setMotd(Replacer.replaceColor(ServerListMotd.ServerPingMOTD()));
						}
					}else{
						if(ServerListMotd.pdata.containsKey(ip)){
					    	try {
								e.setServerIcon(Bukkit.loadServerIcon(ServerListMotd.getPlayerUniqueImg(ip, ServerListMotd.pdata.get(ip))));
								e.setMotd(Replacer.replaceServePingMOTD(ServerListMotd.ServerPingMOTD(), ServerListMotd.pdata.get(ip)));
								cooldown.put(ip, time);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
					    }else{
					    	try {
								e.setServerIcon(Bukkit.loadServerIcon(ServerListMotd.getPlayerUniqueImg(ip, "char")));
								e.setMotd(Replacer.replaceColor(ServerListMotd.ServerPingMOTD()));
								cooldown.put(ip, time);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
					    }
					}
		    	}else{
		    		if(ServerListMotd.pdata.containsKey(ip)){
				    	try {
							e.setServerIcon(Bukkit.loadServerIcon(ServerListMotd.getPlayerUniqueImg(ip, ServerListMotd.pdata.get(ip))));
							e.setMotd(Replacer.replaceServePingMOTD(ServerListMotd.ServerPingMOTD(), ServerListMotd.pdata.get(ip)));
							cooldown.put(ip, time);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
				    }else{
				    	try {
							e.setServerIcon(Bukkit.loadServerIcon(ServerListMotd.getPlayerUniqueImg(ip, "char")));
							e.setMotd(Replacer.replaceColor(ServerListMotd.ServerPingMOTD()));
							cooldown.put(ip, time);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
				    }
		    	}
		    }else{
		    	if(ServerListMotd.pdata.containsKey(ip)){
					e.setMotd(Replacer.replaceServePingMOTD(ServerListMotd.ServerPingMOTD(), ServerListMotd.pdata.get(ip)));
				}else{
					e.setMotd(Replacer.replaceServePingMOTD(ServerListMotd.ServerPingMOTDGuest()));
				}
		    }
		}else{
			e.setMotd(Replacer.replaceServePingMOTD(ServerListMotd.ServerPingMOTD()));
		}
	}
	
}