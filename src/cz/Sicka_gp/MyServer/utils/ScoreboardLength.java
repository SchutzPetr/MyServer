package cz.Sicka_gp.MyServer.utils;

import java.util.logging.Level;

import cz.Sicka_gp.MyServer.MyServer;

public class ScoreboardLength {
	private static MyServer plugin;
	
	public ScoreboardLength(MyServer instance){
		plugin = instance;
	}
	
	public static String checkLength(String check, int limit) {
        if(check.length() > limit){
            String cut = check.substring(0, limit + 1);
            plugin.getLog().log(Level.WARNING, ColouredConsoleSender.sendConsoleMessage(AnsiColor.RED, " String: " + check + NewMessageList.isLongerThanAllowed + limit + " {" + check.length() + " > " + limit + "}"));
            return cut;
        }
        return check;
    }

}
