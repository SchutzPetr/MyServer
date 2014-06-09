package cz.Sicka_gp.MyServer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import cz.Sicka_gp.MyServer.API.ScoreBoardAPI;
import cz.Sicka_gp.MyServer.Automessages.Automessages;
import cz.Sicka_gp.MyServer.Commands.CommandManager;
import cz.Sicka_gp.MyServer.Configuration.ConfigSettings;
import cz.Sicka_gp.MyServer.Configuration.ConfigurationManager;
import cz.Sicka_gp.MyServer.HookedPlugins.PluginsManager;
import cz.Sicka_gp.MyServer.Listener.JQKListener;
import cz.Sicka_gp.MyServer.Motd.ChatMotd;
import cz.Sicka_gp.MyServer.Motd.ScoreBoardMOTD;
import cz.Sicka_gp.MyServer.Motd.ServerListMotd;
import cz.Sicka_gp.MyServer.Scoreboard.SBManager;
import cz.Sicka_gp.MyServer.Scoreboard.ScoreboardItemID;
import cz.Sicka_gp.MyServer.Scoreboard.ScoreboardItemsIDReplacer;
import cz.Sicka_gp.MyServer.Scoreboard.ScoreboardSettings;
import cz.Sicka_gp.MyServer.Scoreboard.CountDown.ScoreBoardCountDown;
import cz.Sicka_gp.MyServer.utils.AnsiColor;
import cz.Sicka_gp.MyServer.utils.ColouredConsoleSender;
import cz.Sicka_gp.MyServer.utils.HolographicDisplaysManager;
import cz.Sicka_gp.MyServer.utils.NewMessageList;
import cz.Sicka_gp.MyServer.utils.PlayerGroup;
import cz.Sicka_gp.MyServer.utils.Replacer;
import cz.Sicka_gp.MyServer.utils.ScoreItemsReplacerString;
import cz.Sicka_gp.MyServer.utils.ScoreboardLength;

public class MyServer extends JavaPlugin{
	private static MyServer plugin;
	private Logger log = Logger.getLogger("Minecraft");
	private ConfigurationManager configmanager;
	private RefreshTask refreshtask;
	private PluginsManager pluginmanager;
	private PlayerGroup playergroup;
	private AnsiColor ans;
	private JQKListener jqkl;
	private JoinQuitKickMessages jqkm;
	private ConfigSettings configsettings;
	private TabListManager cmtl;
	private ScoreboardItemID sid;
	private JoinQuitKickMessages cmjqk;
	private Permissions perm;
	private Automessages cma;
	private Replacer cmss;
	private SBManager score;
	private ScoreBoardCountDown sbcd;
	private ScoreBoardMOTD sbm;
	private ServerListMotd motd;
	private ScoreboardSettings sbset;
	private ChatMotd cm;
	private ScoreboardLength sl;
	private ScoreboardItemsIDReplacer sbidr;
	private ScoreBoardAPI sbapi;
	private ScoreItemsReplacerString scorestring;
	private static boolean DisableMyServer;
	
	
	public void onEnable(){
		plugin = this;
		ans = new AnsiColor();
		configmanager = new ConfigurationManager(this);
		configsettings = new ConfigSettings(this);
		
		refreshtask = new RefreshTask(this);
		pluginmanager = new PluginsManager(this);
		playergroup = new PlayerGroup(this);
		jqkl = new JQKListener(this);
		jqkm = new JoinQuitKickMessages(this);
		
		sid = new ScoreboardItemID(this);
		cmtl = new TabListManager(this);
		cmjqk = new JoinQuitKickMessages(this);
		perm = new Permissions();
		cmss = new Replacer(this);
		cma = new Automessages(this); 
		score = new SBManager(this);
		sbcd = new ScoreBoardCountDown(this);
		sbm = new ScoreBoardMOTD(this);
		motd = new ServerListMotd(this);
		sbset = new ScoreboardSettings(this);
		cm = new ChatMotd(this);
		sl = new ScoreboardLength(this);
		sbidr = new ScoreboardItemsIDReplacer(this);
		sbapi = new ScoreBoardAPI(this);
		scorestring = new ScoreItemsReplacerString(this);
		
		
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(jqkl, this);
		getCommand("configurablemessages").setExecutor(new CommandManager(this));
		getCommand("sidebar").setExecutor(new CommandManager(this));
		getCommand("countdown").setExecutor(new CommandManager(this));
		try{
			Metrics metrics = new Metrics(this);
			metrics.start();
			getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.GREEN, NewMessageList.Metrics));
		}catch(IOException e){
			getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.RED, NewMessageList.Unable_Metrics));
		}catch(Throwable t){
			getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.RED, NewMessageList.Unable_Metrics));
		}
		
		if(DisableMyServer){
			getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.DARK_AQUA, NewMessageList.Version));
			Bukkit.getServer().getPluginManager().disablePlugin(this);
			return;
		}
		getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.BLUE, "-------------------------------------------------------"));
		getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.YELLOW, NewMessageList.Authors));
		getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.YELLOW, NewMessageList.Version));
		getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.BLUE, "-------------------------------------------------------"));
		getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.GREEN, AnsiColor.BOLD + NewMessageList.Enable));
		getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.BLUE, "-------------------------------------------------------"));
	}
	
	public void onDisable(){
		configmanager.SaveConfigs();
		RefreshTask.StopScoreboardRefreshTask();
		if(getPluginsManager().getHolographicDisplays().isHolographicDisplays())HolographicDisplaysManager.onDisablePlugin();
		getServer().getScheduler().cancelTasks(plugin);
		getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.GREEN, NewMessageList.CancelAllTasks));
		getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.GREEN, NewMessageList.DisablePlugin));
	}
	
	public void setDisableMyServer(boolean dismyserver){
		DisableMyServer = dismyserver;
	}
	
	
	public static MyServer getPlugin(){
		return plugin;
	}

	public ConfigurationManager getConfigManager() {
		return configmanager;
	}

	public RefreshTask getRefreshtask() {
		return refreshtask;
	}
	
	public PluginsManager getPluginsManager() {
		return pluginmanager;
	}

	public Logger getLog() {
		return log;
	}

	public PlayerGroup getPlayergroup() {
		return playergroup;
	}
	
	public AnsiColor getAnsiColor() {
		return ans;
	}
}
