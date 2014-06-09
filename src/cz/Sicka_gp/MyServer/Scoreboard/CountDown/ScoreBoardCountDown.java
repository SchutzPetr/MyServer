package cz.Sicka_gp.MyServer.Scoreboard.CountDown;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;

import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.API.ScoreBoardAPI;
import cz.Sicka_gp.MyServer.Configuration.ConfigSettings;
import cz.Sicka_gp.MyServer.utils.Replacer;

public class ScoreBoardCountDown {
	private static int interval = 1;
	private static MyServer plugin;
	private static BukkitTask CMCountDowntask;
	public static int running = 0;
	
	private static String Countdown_ShutdownTitle;
	private static String Countdown_ReloadTitle;
	private static String Countdown_CancelTitle;
	private static String Countdown_ShutdownBroadcastMSG;
	private static String Countdown_CountdownBroadcastMSG;
	private static String Countdown_ReloadBroadcastMSG;
	private static String Countdown_CancelBroadcastMSG;
	private static String Countdown_CountdownTitle;
	private static String Countdown_CountdownEndTitle;
	private static String Countdown_ShutdownEndBroadcastMSG;
	private static String Countdown_ReloadingBroadcastMSG;
	private static boolean CountDown_EnableSounds;
	private static String Countdown_CountdownEndBroadcastMSG;
	private static String objname = "countdown";
	
	public ScoreBoardCountDown(MyServer instance){
		plugin = instance;
		init();
	}
	
	public static void init (){
		Countdown_ShutdownTitle = ConfigSettings.Countdown_ShutdownTitle;
		Countdown_CountdownTitle = ConfigSettings.Countdown_CountdownTitle;
		Countdown_CancelTitle = ConfigSettings.Countdown_CancelTitle;
		Countdown_ReloadTitle = ConfigSettings.Countdown_ReloadTitle;
		
		Countdown_ShutdownBroadcastMSG = ConfigSettings.Countdown_ShutdownBroadcastMSG;
		Countdown_CountdownBroadcastMSG = ConfigSettings.Countdown_CountdownBroadcastMSG;
		Countdown_CancelBroadcastMSG = ConfigSettings.Countdown_CancelBroadcastMSG;
		Countdown_ReloadBroadcastMSG = ConfigSettings.Countdown_ReloadBroadcastMSG;
		
		Countdown_ShutdownEndBroadcastMSG = ConfigSettings.Countdown_ShutdownEndBroadcastMSG;
		Countdown_CountdownEndBroadcastMSG = ConfigSettings.Countdown_CountdownEndBroadcastMSG;
		Countdown_CountdownEndTitle = ConfigSettings.Countdown_CountdownEndTitle;
		Countdown_ReloadingBroadcastMSG = ConfigSettings.Countdown_ReloadingBroadcastMSG;
		
		CountDown_EnableSounds = ConfigSettings.CountDown_EnableSounds;
		
	}
	
	public static void CancelTask(){
		CMCountDowntask.cancel();
		//TODO: ConfigurableMessages.log.info(MessagesList.CanceledTask);
		running = 0;
	}
	
	
	public static void SidebarCountDownReloadTask(final Player p, final int num){
		CMCountDowntask = Bukkit.getServer().getScheduler().runTaskTimer(plugin, new Runnable(){
			int number = num;
			@Override
			public void run() {
				if(number != -1) {
					  if(number !=0) {
						  ScoreBoardAPI.UpdateDefaultScoreForALL(objname);
						  for(Player player: Bukkit.getOnlinePlayers()){
							  ScoreBoardAPI.UpdateSidebarTitle(player, DisplaySlot.SIDEBAR, Replacer.CountdownSidebarName(Countdown_ReloadTitle, number));
							  if(CountDown_EnableSounds){
								  player.playSound(player.getLocation(), Sound.NOTE_PLING, 4.0F, player.getLocation().getPitch());
							  }
						  }
						  if(number == 4){
							  p.performCommand("save-all");
						  }
						  number--;
					  }else{
						  ScoreBoardAPI.UpdateDefaultScoreForALL(objname);
						  for(Player player: Bukkit.getOnlinePlayers()){
							  ScoreBoardAPI.UpdateSidebarTitle(player, DisplaySlot.SIDEBAR, Replacer.CountdownSidebarName(Countdown_ReloadTitle, number));
							  p.sendMessage(Replacer.CountdownStringReplacer(Countdown_ReloadingBroadcastMSG, p, number));
							  if(CountDown_EnableSounds){
								  player.playSound(player.getLocation(), Sound.LEVEL_UP, 4.0F, player.getLocation().getPitch());
							  }
						  }
						  number--;
						  CMCountDowntask.cancel();
						  ScoreBoardCountDown.running = 0;
						  Bukkit.reload();
					  }
				  }
			  }
			
		}, 0, interval *20);
	}
	
	public static void SidebarCountDownStopTask(final Player p, final int num){
		CMCountDowntask = Bukkit.getServer().getScheduler().runTaskTimer(plugin, new Runnable(){
			int number = num;
			@Override
			public void run() {
				if(number != -1) {
					  if(number !=0) {
						  ScoreBoardAPI.UpdateDefaultScoreForALL(objname);
						  for(Player player: Bukkit.getOnlinePlayers()){
							  ScoreBoardAPI.UpdateSidebarTitle(player, DisplaySlot.SIDEBAR, Replacer.CountdownSidebarName(Countdown_ShutdownTitle, number));
							  if(CountDown_EnableSounds){
								  player.playSound(player.getLocation(), Sound.NOTE_PLING, 4.0F, player.getLocation().getPitch());
							  }
						  }
						  if(number == 4){
							  p.performCommand("save-all");
						  }
						  number--;
					  }else{
						  ScoreBoardAPI.UpdateDefaultScoreForALL(objname);
						  for(Player player: Bukkit.getOnlinePlayers()){
							  ScoreBoardAPI.UpdateSidebarTitle(player, DisplaySlot.SIDEBAR, Replacer.CountdownSidebarName(Countdown_ShutdownTitle, number));
							  p.sendMessage(Replacer.CountdownStringReplacer(Countdown_ShutdownEndBroadcastMSG, p, number));
							  if(CountDown_EnableSounds){
								  player.playSound(player.getLocation(), Sound.LEVEL_UP, 4.0F, player.getLocation().getPitch());
							  }
						  }
						  number--;
						  CMCountDowntask.cancel();
						  ScoreBoardCountDown.running = 0;
						  Bukkit.shutdown();
					  }
				  }
			  }
			
		}, 0, interval *20);
	}
	
	public static void SidebarCountDownTask(final Player p, final int num){
		CMCountDowntask = Bukkit.getServer().getScheduler().runTaskTimer(plugin, new Runnable(){
			int number = num;
			@Override
			public void run() {
				if(number != -1) {
					  if(number !=0) {
						  ScoreBoardAPI.UpdateDefaultScoreForALL(objname);
						  for(Player player: Bukkit.getOnlinePlayers()){
							  ScoreBoardAPI.UpdateSidebarTitle(player, DisplaySlot.SIDEBAR, Replacer.CountdownSidebarName(Countdown_CountdownTitle, number));
							  if(CountDown_EnableSounds){
								  player.playSound(player.getLocation(), Sound.NOTE_PLING, 4.0F, player.getLocation().getPitch());
							  }
						  }
						  number--;
					  }else{
						  ScoreBoardAPI.UpdateDefaultScoreForALL(objname);
						  for(Player player: Bukkit.getOnlinePlayers()){
							  ScoreBoardAPI.UpdateSidebarTitle(player, DisplaySlot.SIDEBAR, Replacer.CountdownSidebarName(Countdown_CountdownTitle, number));
							  p.sendMessage(Replacer.CountdownStringReplacer(Countdown_CountdownEndBroadcastMSG, player, number));
							  if(CountDown_EnableSounds){
								  player.playSound(player.getLocation(), Sound.LEVEL_UP, 4.0F, player.getLocation().getPitch());
							  }
						  }
						  number--;
						  CMCountDowntask.cancel();
						  ScoreBoardCountDown.running = 0;
						  SidebarCountDownEndTask(p, 5);
					  }
				  }
			  }
			
		}, 0, interval *20);
	}
	
	public static void SidebarCountDownCancelTask(final Player p, final int num){
		CMCountDowntask = Bukkit.getServer().getScheduler().runTaskTimer(plugin, new Runnable(){
			int number = num;
			@Override
			public void run() {
				if(number != -1) {
					  if(number !=0) {
						  ScoreBoardAPI.UpdateDefaultScoreForALL(objname);
						  for(Player player: Bukkit.getOnlinePlayers()){
							  ScoreBoardAPI.UpdateSidebarTitle(player, DisplaySlot.SIDEBAR, Replacer.CountdownSidebarName(Countdown_CancelTitle, number));
						  }
						  number--;
					  }else{
						  ScoreBoardAPI.UpdateDefaultScoreForALL(objname);
						  for(Player player: Bukkit.getOnlinePlayers()){
							  ScoreBoardAPI.RemoveSidebarFromSlot(p, DisplaySlot.SIDEBAR);
							  if(CountDown_EnableSounds){
								  player.playSound(player.getLocation(), Sound.EXPLODE, 4.0F, player.getLocation().getPitch());
							  }
						  }
						  number--;
						  CMCountDowntask.cancel();
						  ScoreBoardCountDown.running = 0;
					  }
				  }
			  }
			
		}, 0, interval *20);
	}
	public static void SidebarCountDownEndTask(final Player p, final int num){
		CMCountDowntask = Bukkit.getServer().getScheduler().runTaskTimer(plugin, new Runnable(){
			int number = num;
			@Override
			public void run() {
				if(number != -1) {
					  if(number !=0) {
						  ScoreBoardAPI.UpdateDefaultScoreForALL(objname);
						  for(Player player: Bukkit.getOnlinePlayers()){
							  ScoreBoardAPI.UpdateSidebarTitle(player, DisplaySlot.SIDEBAR, Replacer.CountdownSidebarName(Countdown_CountdownEndTitle, number));
						  }
						  number--;
					  }else{
						  ScoreBoardAPI.UpdateDefaultScoreForALL(objname);
						  for(Player player: Bukkit.getOnlinePlayers()){
							  ScoreBoardAPI.RemoveSidebarFromSlot(p, DisplaySlot.SIDEBAR);
							  if(CountDown_EnableSounds){
								  player.playSound(player.getLocation(), Sound.EXPLODE, 4.0F, player.getLocation().getPitch());
							  }
						  }
						  number--;
						  CMCountDowntask.cancel();
						  ScoreBoardCountDown.running = 0;
					  }
				  }
			  }
			
		}, 0, interval *20);
	}

	public static void SidebarCountDownStop(Player p, int i) {
		ScoreBoardAPI.setSidebarANDPlayerData(p, "countdown", Countdown_ShutdownTitle, DisplaySlot.SIDEBAR);
		ScoreBoardCountDown.SidebarCountDownStopTask(p, i);
		for(Player player: Bukkit.getOnlinePlayers()){
			player.sendMessage(Replacer.CountdownStringReplacer(Countdown_ShutdownBroadcastMSG, p, i));
		}
		running = 1;
		
	}
	public static void SidebarCountDownReload(Player p, int i) {
		ScoreBoardAPI.setSidebarANDPlayerData(p, "countdown", Countdown_ReloadTitle + Replacer.MMSS(i), DisplaySlot.SIDEBAR);
		ScoreBoardCountDown.SidebarCountDownReloadTask(p, i);
		for(Player player: Bukkit.getOnlinePlayers()){
			player.sendMessage(Replacer.CountdownStringReplacer(Countdown_ReloadBroadcastMSG, p, i));
		}
		running = 1;
		
	}
	public static void SidebarCountDown(Player p, int i) {
		ScoreBoardAPI.setSidebarANDPlayerData(p, "countdown", Countdown_CountdownTitle, DisplaySlot.SIDEBAR);
		ScoreBoardCountDown.SidebarCountDownTask(p, i);
		for(Player player: Bukkit.getOnlinePlayers()){
			player.sendMessage(Replacer.CountdownStringReplacer(Countdown_CountdownBroadcastMSG, p, i));
		}
		running = 1;
		
	}
	public static void SidebarCountDownCancel(Player p) {
		ScoreBoardCountDown.CancelTask();
		ScoreBoardAPI.setSidebarANDPlayerData(p, "countdown", Countdown_CancelTitle, DisplaySlot.SIDEBAR);
		for(Player player: Bukkit.getOnlinePlayers()){
			player.sendMessage(Replacer.replaceSidebarName(Countdown_CancelBroadcastMSG, p));
			  if(CountDown_EnableSounds){
				  player.playSound(player.getLocation(), Sound.FUSE, 4.0F, player.getLocation().getPitch());
			  }
		  }
		ScoreBoardCountDown.SidebarCountDownCancelTask(p, 3);
	}
}
