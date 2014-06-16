package cz.Sicka_gp.MyServer.Commands;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import cz.Sicka_gp.MyServer.JoinQuitKickMessages;
import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.Permissions;
import cz.Sicka_gp.MyServer.RefreshTask;
import cz.Sicka_gp.MyServer.TabListManager;
import cz.Sicka_gp.MyServer.API.ScoreBoardAPI;
import cz.Sicka_gp.MyServer.Automessages.Automessages;
import cz.Sicka_gp.MyServer.Configuration.ConfigSettings;
import cz.Sicka_gp.MyServer.Configuration.ConfigurationManager;
import cz.Sicka_gp.MyServer.HookedPlugins.PluginsManager;
import cz.Sicka_gp.MyServer.Listener.JQKListener;
import cz.Sicka_gp.MyServer.Motd.ChatMotd;
import cz.Sicka_gp.MyServer.Motd.HolographicDisplaysMOTD;
import cz.Sicka_gp.MyServer.Motd.ScoreBoardMOTD;
import cz.Sicka_gp.MyServer.Motd.ServerListMotd;
import cz.Sicka_gp.MyServer.Scoreboard.SBManager;
import cz.Sicka_gp.MyServer.Scoreboard.ScoreboardItemID;
import cz.Sicka_gp.MyServer.Scoreboard.ScoreboardItemsIDReplacer;
import cz.Sicka_gp.MyServer.Scoreboard.ScoreboardPlayerData;
import cz.Sicka_gp.MyServer.Scoreboard.ScoreboardSettings;
import cz.Sicka_gp.MyServer.Scoreboard.CountDown.ScoreBoardCountDown;
import cz.Sicka_gp.MyServer.utils.AnsiColor;
import cz.Sicka_gp.MyServer.utils.ColouredConsoleSender;
import cz.Sicka_gp.MyServer.utils.MessageList;
import cz.Sicka_gp.MyServer.utils.PlayerGroup;
import cz.Sicka_gp.MyServer.utils.Replacer;
import cz.Sicka_gp.MyServer.utils.ScoreItemsReplacerString;
import cz.Sicka_gp.MyServer.utils.ScoreboardLength;

public class CommandManager implements CommandExecutor{
	public static MyServer plugin;

	public CommandManager(MyServer instance){ 
		plugin = instance;
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmds, String commandLabel, String[] args) {
		String cmd = cmds.getName();
		if(sender instanceof Player){
			Player p = (Player)sender;
			if(cmd.equalsIgnoreCase("myserver")){
				if((args == null) || (args.length < 1)){
					DefaultCMDNullMessage(p);
					return true;
				}
				else if(args[0].equalsIgnoreCase("automessage") || args[0].equalsIgnoreCase("am") && args.length <= 2){
					if(p.hasPermission(Permissions.automessagecommand)){
						if ((args == null) || (args.length < 2)) {
							p.sendMessage(Replacer.replaceColor(MessageList.AutomessageUsage));
							return true;
						}
						if(args[1].equalsIgnoreCase("stop")){
							if(Automessages.running == 1){
								Bukkit.getServer().getScheduler().cancelTask(Automessages.tid);
								p.sendMessage(Replacer.replaceColor(MessageList.AutomessageStoped));
								Automessages.running = 0;
							}else{
								p.sendMessage(Replacer.replaceColor(MessageList.AutomessageNotRun));
							}
							return true;
						}
						if(args[1].equalsIgnoreCase("start")){
							if(Automessages.running == 1){
								p.sendMessage(Replacer.replaceColor(MessageList.AutomessageRunning));
							}else{
								Automessages.StartBroadcast();
								p.sendMessage(Replacer.replaceColor(MessageList.AutomessageRun));
								Automessages.running = 1;
							}
							return true;
						}
					}else{
						p.sendMessage(Replacer.replaceColor(MessageList.CanNotPerms));
					}
				}
				else if(args[0].equalsIgnoreCase("d") && args.length == 1){
					HolographicDisplaysMOTD.CreateMOTD(p);
				}
				else if(args[0].equalsIgnoreCase("reload") && args.length == 1){
					if(p.hasPermission(Permissions.reload)){
						Reload();
						p.sendMessage(Replacer.replaceColor(MessageList.ReloadedDone));
					}else{
						p.sendMessage(Replacer.replaceColor(MessageList.CanNotPerms));
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
							p.sendMessage(Replacer.replaceColor(MessageList.ScoreboardShow));
						}else{
							SBManager.HideScoreboard(p);
							p.sendMessage(Replacer.replaceColor(MessageList.ScoreboardHide));
						}
					}else{
						p.sendMessage(Replacer.replaceColor(MessageList.ScoreboardDisabled));
					}
				}else{
					p.sendMessage(Replacer.replaceColor(MessageList.CanNotPerms));
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
							p.sendMessage(Replacer.replaceColor(MessageList.CountDownRunning));
						}else{
							ScoreBoardCountDown.SidebarCountDownStop(p, Integer.valueOf(args[1]));
						}
						return true;
					}
					else if(args[0].equalsIgnoreCase("stop")&& args.length == 1){
						if(ScoreBoardCountDown.running == 1){
							p.sendMessage(Replacer.replaceColor(MessageList.CountDownRunning));
						}else{
							ScoreBoardCountDown.SidebarCountDownStop(p, 10);
						}
						return true;
					}
					else if(args[0].equalsIgnoreCase("cancel")&& args.length == 1){
						if(ScoreBoardCountDown.running == 1){
							ScoreBoardCountDown.SidebarCountDownCancel(p);
							p.sendMessage(Replacer.replaceColor(MessageList.CountDownCancel));
						}else{
							p.sendMessage(Replacer.replaceColor(MessageList.CountDownNotRunning));;
						}
						return true;
					}
					else if(args[0].equalsIgnoreCase("reload")&& args.length == 2){
						if(ScoreBoardCountDown.running == 1){
							p.sendMessage(Replacer.replaceColor(MessageList.CountDownRunning));
						}else{
							ScoreBoardCountDown.SidebarCountDownReload(p, Integer.valueOf(args[1]));
						}
						return true;
					}
					else if(args[0].equalsIgnoreCase("reload")&& args.length == 1){
						if(ScoreBoardCountDown.running == 1){
							p.sendMessage(Replacer.replaceColor(MessageList.CountDownRunning));
						}else{
							ScoreBoardCountDown.SidebarCountDownReload(p, 10);
						}
						return true;
					}
					else if(args[0].equalsIgnoreCase("timer")&& args.length == 1){
						if(ScoreBoardCountDown.running == 1){
							p.sendMessage(Replacer.replaceColor(MessageList.CountDownRunning));
						}else{
							ScoreBoardCountDown.SidebarCountDown(p, 10);
						}
						return true;
					}
					else if(args[0].equalsIgnoreCase("timer")&& args.length == 2){
						if(ScoreBoardCountDown.running == 1){
							p.sendMessage(Replacer.replaceColor(MessageList.CountDownRunning));
						}else{
							ScoreBoardCountDown.SidebarCountDown(p, Integer.valueOf(args[1]));
						}
						return true;
					}else{
						CountDownUsageMessage(p);
					}
					return true;
				}else{
					p.sendMessage(Replacer.replaceColor(MessageList.CanNotPerms));
				}
				
			}
		}
		else if(sender instanceof ConsoleCommandSender){
			ConsoleCommandSender con = (ConsoleCommandSender)sender;
			if(cmd.equalsIgnoreCase("myserver")){
				if((args == null) || (args.length < 1)){
					con.sendMessage(AnsiColor.GREEN + MessageList.pluginName + AnsiColor.GRAY + "++++" + AnsiColor.DARK_GREEN + "-----------[ " + AnsiColor.GOLD + plugin.getName() + AnsiColor.DARK_GREEN + " ]-----------" + AnsiColor.GRAY + "++++");
					con.sendMessage(AnsiColor.GREEN + MessageList.pluginName + AnsiColor.AQUA + "Author" + AnsiColor.GRAY + ": " + AnsiColor.GOLD + plugin.getDescription().getAuthors());
					con.sendMessage(AnsiColor.GREEN + MessageList.pluginName + AnsiColor.AQUA + "Version" + AnsiColor.GRAY + ": " + AnsiColor.GOLD + plugin.getDescription().getVersion());
					con.sendMessage(AnsiColor.GREEN + MessageList.pluginName + AnsiColor.AQUA + "Reload plugin " + AnsiColor.GRAY + ": " + AnsiColor.GOLD + "/my reload");
					return true;
				}
				else if(args[0].equalsIgnoreCase("reload") && args.length == 1){
					Reload();
					con.sendMessage(Replacer.replaceColor(MessageList.ReloadedDone));
					return true;
				}else{
					con.sendMessage(AnsiColor.GREEN + MessageList.pluginName + AnsiColor.GRAY + "++++" + AnsiColor.DARK_GREEN + "-----------[ " + AnsiColor.GOLD + plugin.getName() + AnsiColor.DARK_GREEN + " ]-----------" + AnsiColor.GRAY + "++++");
					con.sendMessage(AnsiColor.GREEN + MessageList.pluginName + AnsiColor.AQUA + "Author" + AnsiColor.GRAY + ": " + AnsiColor.GOLD + plugin.getDescription().getAuthors());
					con.sendMessage(AnsiColor.GREEN + MessageList.pluginName + AnsiColor.AQUA + "Version" + AnsiColor.GRAY + ": " + AnsiColor.GOLD + plugin.getDescription().getVersion());
					con.sendMessage(AnsiColor.GREEN + MessageList.pluginName + AnsiColor.AQUA + "Reload plugin " + AnsiColor.GRAY + ": " + AnsiColor.GOLD + "/my reload");
				}
			}
		}
		return false;
		
	}
	
	private static void CountDownUsageMessage(Player p){
		p.sendMessage(ChatColor.WHITE + "++++" + ChatColor.DARK_GREEN + "-----------[ " + ColouredConsoleSender.ReplaceAnsiColor(MessageList.CountdownUsage) + ChatColor.DARK_GREEN + " ]-----------" + ChatColor.WHITE + "++++");
		p.sendMessage(ColouredConsoleSender.ReplaceAnsiColor(MessageList.CountdownCommandtimer) + "/cd timer");
		p.sendMessage(ColouredConsoleSender.ReplaceAnsiColor(MessageList.CountdownCommandtimersec) + "/cd timer <sec>");
		p.sendMessage(ColouredConsoleSender.ReplaceAnsiColor(MessageList.CountdownCommandreload) + "/cd reload");
		p.sendMessage(ColouredConsoleSender.ReplaceAnsiColor(MessageList.CountdownCommandreloadsec) + "/cd reload <sec>");
		p.sendMessage(ColouredConsoleSender.ReplaceAnsiColor(MessageList.CountdownCommandshutdown) + "/cd stop");
		p.sendMessage(ColouredConsoleSender.ReplaceAnsiColor(MessageList.CountdownCommandshutdownsec) + "/cd stop <sec>");
	}
	
	private static void DefaultCMDNullMessage(Player p){
		p.sendMessage(ChatColor.WHITE + "++++" + ChatColor.DARK_GREEN + "-----------[ " + ChatColor.GOLD + plugin.getName() + ChatColor.DARK_GREEN + " ]-----------" + ChatColor.WHITE + "++++");
		p.sendMessage(ChatColor.GREEN + "Author" + ChatColor.WHITE + ": " + ChatColor.GOLD + plugin.getDescription().getAuthors());
		p.sendMessage(ChatColor.GREEN + "Version" + ChatColor.WHITE + ": " + ChatColor.GOLD + plugin.getDescription().getVersion());
		p.sendMessage(ChatColor.GREEN + "Automessage Enable/Disable" + ChatColor.WHITE + ": " + ChatColor.GOLD + "/my am <start/stop>");
		p.sendMessage(ChatColor.GREEN + "Reload plugin" + ChatColor.WHITE + ": " + ChatColor.GOLD + "/my reload");
		p.sendMessage(ChatColor.GREEN + "Hide Sidebar/View sideba" + ChatColor.WHITE + ": " + ChatColor.GOLD + "/s" + ChatColor.WHITE + " or " + ChatColor.GOLD + "/side");
		p.sendMessage(ChatColor.GREEN + "Countdown" + ChatColor.WHITE + ": " + ChatColor.GOLD + "/cd" + ChatColor.WHITE + " or " + ChatColor.GOLD + "/count" + ChatColor.WHITE + " or " + ChatColor.GOLD + "/countdown");
	}

	private static void Reload(){
		plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.YELLOW, MessageList.Reloading));
		for(Player pl : Bukkit.getOnlinePlayers()){
			SBManager.RemoveScoreboard(pl);
		}
		if(Automessages.running == 1){
			Bukkit.getServer().getScheduler().cancelTask(Automessages.tid);
			plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.RED, MessageList.AutomessageDisabled));
		}
		plugin.getConfigManager().ReloadConfigs();
		new AnsiColor();
		new ConfigurationManager(plugin);
		new ConfigSettings(plugin);
		
		new RefreshTask(plugin);
		new PluginsManager(plugin);
		new PlayerGroup(plugin);
		new JQKListener(plugin);
		new JoinQuitKickMessages(plugin);
		
		new ScoreboardItemID(plugin);
		new TabListManager(plugin);
		new JoinQuitKickMessages(plugin);
		new Permissions();
		new Replacer(plugin);
		new Automessages(plugin); 
		new SBManager(plugin);
		new ScoreBoardCountDown(plugin);
		new ScoreBoardMOTD(plugin);
		new ServerListMotd(plugin);
		new ScoreboardSettings(plugin);
		new ChatMotd(plugin);
		new ScoreboardLength(plugin);
		new ScoreboardItemsIDReplacer(plugin);
		new ScoreBoardAPI(plugin);
		new ScoreItemsReplacerString(plugin);
		
		if(Automessages.running == 1){
			Automessages.StartBroadcast();
			plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.GREEN, MessageList.AutomessageEnabled));
		}
		plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.GREEN, MessageList.Reload));
	}

}
