package cz.Sicka_gp.MyServer.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.Permissions;
import cz.Sicka_gp.MyServer.Automessages.Automessages;
import cz.Sicka_gp.MyServer.Motd.HolographicDisplaysMOTD;
import cz.Sicka_gp.MyServer.Scoreboard.SBManager;
import cz.Sicka_gp.MyServer.Scoreboard.ScoreboardPlayerData;
import cz.Sicka_gp.MyServer.Scoreboard.CountDown.ScoreBoardCountDown;
import cz.Sicka_gp.MyServer.utils.MessagesList;
import cz.Sicka_gp.MyServer.utils.Replacer;

public class CommandManager implements CommandExecutor{
	public static MyServer plugin;

	public CommandManager(MyServer instance){ 
		plugin = instance;
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmds, String commandLabel, String[] args) {
		String cmd = cmds.getName();
		if(sender instanceof Player){
			Player p = (Player)sender;
			if(cmd.equalsIgnoreCase("configurablemessages")){
				if((args == null) || (args.length < 1)){
					DefaultCMDNullMessage(p);
					return true;
				}
				else if(args[0].equalsIgnoreCase("automessage") || args[0].equalsIgnoreCase("am") && args.length <= 2){
					if(p.hasPermission(Permissions.automessagecommand)){
						if ((args == null) || (args.length < 2)) {
							p.sendMessage(Replacer.replaceColor(MessagesList.AutomessageUsage));
							return true;
						}
						if(args[1].equalsIgnoreCase("stop")){
							if(Automessages.running == 1){
								Bukkit.getServer().getScheduler().cancelTask(Automessages.tid);
								p.sendMessage(Replacer.replaceColor(MessagesList.AutomessageStoped));
								Automessages.running = 0;
							}else{
								p.sendMessage(Replacer.replaceColor(MessagesList.AutomessageNotRun));
							}
							return true;
						}
						if(args[1].equalsIgnoreCase("start")){
							if(Automessages.running == 1){
								p.sendMessage(Replacer.replaceColor(MessagesList.AutomessageRunning));
							}else{
								Automessages.StartBroadcast();
								p.sendMessage(Replacer.replaceColor(MessagesList.AutomessageRun));
								Automessages.running = 1;
							}
							return true;
						}
					}else{
						p.sendMessage(Replacer.replaceColor(MessagesList.CanNotPerms));
					}
				}
				else if(args[0].equalsIgnoreCase("d") && args.length == 1){
					HolographicDisplaysMOTD.CreateMOTD(p);
				}
				else if(args[0].equalsIgnoreCase("reload") && args.length == 1){
					if(p.hasPermission(Permissions.reload)){
						Reload();
						p.sendMessage(Replacer.replaceColor(MessagesList.ReloadedDone));
					}else{
						p.sendMessage(Replacer.replaceColor(MessagesList.CanNotPerms));
					}
					return true;
				}else{
					DefaultCMDNullMessage(p);
				}
			}
			else if(cmd.equalsIgnoreCase("sidebar")){
				if(p.hasPermission(Permissions.sidebar)){
					if(SBManager.isSidebar()){
						if(ScoreboardPlayerData.getHidePlayers().contains(p)){
							SBManager.Sidebar(p);
							ScoreboardPlayerData.AddPlayerToPlayerData(p);
							ScoreboardPlayerData.RemovePlayerFromHideList(p);
							p.sendMessage(Replacer.replaceColor(MessagesList.SidebarShow));
						}else{
							SBManager.HideScoreboard(p);
							p.sendMessage(Replacer.replaceColor(MessagesList.SidebarHide));
						}
					}else{
						p.sendMessage(Replacer.replaceColor(MessagesList.SidebarDisabled));
					}
				}else{
					p.sendMessage(Replacer.replaceColor(MessagesList.CanNotPerms));
				}
				return true;
			}
			else if(cmd.equalsIgnoreCase("countdown")){
				if(p.hasPermission(Permissions.countdown)){
					if ((args == null) || (args.length < 1)) {
						CountDownUsageMessage(p);
						return true;
					}
					else if(args[0].equalsIgnoreCase("stop")&& args.length == 2){
						if(ScoreBoardCountDown.running == 1){
							p.sendMessage(Replacer.replaceColor(MessagesList.CountDownRunning));
						}else{
							ScoreBoardCountDown.SidebarCountDownStop(p, Integer.valueOf(args[1]));
						}
						return true;
					}
					else if(args[0].equalsIgnoreCase("stop")&& args.length == 1){
						if(ScoreBoardCountDown.running == 1){
							p.sendMessage(Replacer.replaceColor(MessagesList.CountDownRunning));
						}else{
							ScoreBoardCountDown.SidebarCountDownStop(p, 10);
						}
						return true;
					}
					else if(args[0].equalsIgnoreCase("cancel")&& args.length == 1){
						if(ScoreBoardCountDown.running == 1){
							ScoreBoardCountDown.SidebarCountDownCancel(p);
							p.sendMessage(Replacer.replaceColor(MessagesList.CountDownCancel));
						}else{
							p.sendMessage(Replacer.replaceColor(MessagesList.CountDownNotRunning));;
						}
						return true;
					}
					else if(args[0].equalsIgnoreCase("reload")&& args.length == 2){
						if(ScoreBoardCountDown.running == 1){
							p.sendMessage(Replacer.replaceColor(MessagesList.CountDownRunning));
						}else{
							ScoreBoardCountDown.SidebarCountDownReload(p, Integer.valueOf(args[1]));
						}
						return true;
					}
					else if(args[0].equalsIgnoreCase("reload")&& args.length == 1){
						if(ScoreBoardCountDown.running == 1){
							p.sendMessage(Replacer.replaceColor(MessagesList.CountDownRunning));
						}else{
							ScoreBoardCountDown.SidebarCountDownReload(p, 10);
						}
						return true;
					}
					else if(args[0].equalsIgnoreCase("timer")&& args.length == 1){
						if(ScoreBoardCountDown.running == 1){
							p.sendMessage(Replacer.replaceColor(MessagesList.CountDownRunning));
						}else{
							ScoreBoardCountDown.SidebarCountDown(p, 10);
						}
						return true;
					}
					else if(args[0].equalsIgnoreCase("timer")&& args.length == 2){
						if(ScoreBoardCountDown.running == 1){
							p.sendMessage(Replacer.replaceColor(MessagesList.CountDownRunning));
						}else{
							ScoreBoardCountDown.SidebarCountDown(p, Integer.valueOf(args[1]));
						}
						return true;
					}else{
						CountDownUsageMessage(p);
					}
					return true;
				}else{
					p.sendMessage(Replacer.replaceColor(MessagesList.CanNotPerms));
				}
				
			}
		}
		else if(sender instanceof ConsoleCommandSender){
			ConsoleCommandSender con = (ConsoleCommandSender)sender;
			if(cmd.equalsIgnoreCase("configurablemessages")){
				if((args == null) || (args.length < 1)){
					con.sendMessage("/cm reload");
					return true;
				}
				else if(args[0].equalsIgnoreCase("reload") && args.length == 1){
					Reload();
					con.sendMessage(Replacer.replaceColor(MessagesList.ReloadedDone));
					return true;
				}else{
					con.sendMessage("/cm reload");
				}
			}
		}
		return false;
		
	}
	
	private static void CountDownUsageMessage(Player p){
		p.sendMessage(ChatColor.WHITE + "++++" + ChatColor.DARK_GREEN + "-----------[ " + ChatColor.GOLD + "Countdown usage" + ChatColor.DARK_GREEN + " ]-----------" + ChatColor.WHITE + "++++");
		p.sendMessage(ChatColor.GREEN + "Timer for 10 sec" + ChatColor.WHITE + ": " + ChatColor.GOLD + "/cd timer");
		p.sendMessage(ChatColor.GREEN + "Timer" + ChatColor.WHITE + ": " + ChatColor.GOLD + "/cd timer <sec>");
		p.sendMessage(ChatColor.GREEN + "Reload server in 10 sec" + ChatColor.WHITE + ": " + ChatColor.GOLD + "/cd reload");
		p.sendMessage(ChatColor.GREEN + "Reload server" + ChatColor.WHITE + ": " + ChatColor.GOLD + "/cd reload <sec>");
		p.sendMessage(ChatColor.GREEN + "ShutdownServer in 10 sec" + ChatColor.WHITE + ": " + ChatColor.GOLD + "/cd stop");
		p.sendMessage(ChatColor.GREEN + "ShutdownServer" + ChatColor.WHITE + ": " + ChatColor.GOLD + "/cd stop <sec>");
	}
	
	private static void DefaultCMDNullMessage(Player p){
		p.sendMessage(ChatColor.WHITE + "++++" + ChatColor.DARK_GREEN + "-----------[ " + ChatColor.GOLD + plugin.getName() + ChatColor.DARK_GREEN + " ]-----------" + ChatColor.WHITE + "++++");
		p.sendMessage(ChatColor.GREEN + "Author" + ChatColor.WHITE + ": " + ChatColor.GOLD + plugin.getDescription().getAuthors());
		p.sendMessage(ChatColor.GREEN + "Version" + ChatColor.WHITE + ": " + ChatColor.GOLD + plugin.getDescription().getVersion());
		p.sendMessage(ChatColor.GREEN + "Automessage Enable/Disable " + ChatColor.WHITE + ": " + ChatColor.GOLD + "/cm am <start/stop>");
		p.sendMessage(ChatColor.GREEN + "Reload plugin " + ChatColor.WHITE + ": " + ChatColor.GOLD + "/cm reload");
		p.sendMessage(ChatColor.GREEN + "Hide Sidebar/View sideba " + ChatColor.WHITE + ": " + ChatColor.GOLD + "/s" + ChatColor.WHITE + " or " + ChatColor.GOLD + "/side");
		p.sendMessage(ChatColor.GREEN + "Countdown " + ChatColor.WHITE + ": " + ChatColor.GOLD + "/cd" + ChatColor.WHITE + " or " + ChatColor.GOLD + "/count" + ChatColor.WHITE + " or " + ChatColor.GOLD + "/countdown");
	}

	private static void Reload(){
		/*ConfigurableMessages.log.info(MessagesList.Reloading);
		for(Player pl : Bukkit.getOnlinePlayers()){
			SBManager.RemoveScoreboard(pl);
		}
		if(Automessages.running == 1){
			Bukkit.getServer().getScheduler().cancelTask(Automessages.tid);
			ConfigurableMessages.log.info(MessagesList.AutomessageDisabled);
		}
		ConfigurableMessages.getPlugin().reloadConfiguration();
		ConfigurableMessages.getPlugin().reloadBadwords();
		
		ConfigSettings.initAll();
		
		ConfigurableMessages.getPlugin().init();
		ConfigurableMessagesListener.init();
		ChatListener.init();
		ChatMotd.init();
		JoinQuitKickMessages.init();
		PluginHook.Check();
		ServerListMotd.init();
		TabListManager.init();
		Automessages.init();
		SBManager.init();
		ScoreBoardCountDown.init();
		ScoreBoardMOTD.init();
		if(Automessages.running == 1){
			Automessages.StartBroadcast();
			ConfigurableMessages.log.info(MessagesList.AutomessageEnabled);
		}
		ConfigurableMessages.log.info(MessagesList.Reload);*/
	}

}
