package cz.Sicka_gp.MyServer.HookedPlugins.Plugins;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;

import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.utils.AnsiColor;
import cz.Sicka_gp.MyServer.utils.ColouredConsoleSender;
import cz.Sicka_gp.MyServer.utils.MessageList;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class VaultPlugin {
	private static MyServer plugin;
	private static Permission permission = null;
	private static Economy econ = null;
	private static Chat chat = null;
	
	private static boolean economyFound;
	private static boolean permissionFound;
	private static boolean chatFound;
	
	private static boolean vaultboolean;
	
	public VaultPlugin(MyServer instance){
		plugin = instance;
		CheckVault();
	}
	
	private static void CheckVault(){
		final PluginManager pm = Bukkit.getServer().getPluginManager();
		Plugin vault = pm.getPlugin("Vault");
		
		if(vault != null && vault.isEnabled()){
			economyFound = setupEconomy();
			permissionFound = setupPermissions();
			chatFound = setupChat();
			vaultboolean = true;
			plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.GREEN, MessageList.VaultFound));
			if(economyFound){
				plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.GREEN, MessageList.SuccessfulhookedEconomy)+ econ.getName());
			}else{
				plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.RED, MessageList.EconomyPluginNotFound));
			}
	        if(permissionFound){
	        	if(permission.getName().equals("SuperPerms")){
	        		plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.RED, MessageList.UnsupportedPermissionPlugin));
	        		permissionFound = false;
	        	}else{
	        		plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.GREEN, MessageList.SuccessfulhookedPermission)+ permission.getName());
	        	}
			}else{
				plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.RED, MessageList.PermissionPluginNotFound));
			}
	        if(chatFound){
	        	plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.GREEN, MessageList.SuccessfulhookedChat) + chat.getName());
			}else{
				plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.RED, MessageList.ChatPluginNotFound));
			}
		}else{
			plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.RED, MessageList.UnableVault));
			plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.DARK_RED, MessageList.DisablingPlugin));
			plugin.setDisableMyServer(true);
		}
	}
	
	
	private static boolean setupEconomy() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
	
	private static boolean setupPermissions(){
        RegisteredServiceProvider<Permission> permissionProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }

	private static boolean setupChat(){
        RegisteredServiceProvider<Chat> chatProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }
        return (chat != null);
    }
	
	
	public boolean isChatFound() {
		return chatFound;
	}

	public boolean isEconomyFound() {
		return economyFound;
	}
	
	public boolean isPermissionFound() {
		return permissionFound;
	}
	
	public Economy getEconomy() {
        return econ;
    }
    
    public Permission getPermission() {
        return permission;
    }
    
    public Chat getChat() {
        return chat;
    }

	public boolean isVault() {
		return vaultboolean;
	}
}
