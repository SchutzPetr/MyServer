package cz.Sicka_gp.MyServer.utils;

import org.fusesource.jansi.Ansi;

public class ColouredConsoleSender{
	
	public static String sendConsoleMessage(String ansicolor, String message){
		return ansicolor + message + Ansi.ansi().reset().toString();
	}
	
	public static String ReplaceAnsiColor( String message){
		message = message.replace("&1", AnsiColor.DARK_BLUE);
		message = message.replace("&2", AnsiColor.DARK_GREEN);
		message = message.replace("&3", AnsiColor.DARK_AQUA);
		message = message.replace("&4", AnsiColor.DARK_RED);
		message = message.replace("&5", AnsiColor.DARK_PURPLE);
		message = message.replace("&6", AnsiColor.GOLD);
		message = message.replace("&7", AnsiColor.GRAY);
		message = message.replace("&8", AnsiColor.DARK_GRAY);
		message = message.replace("&9", AnsiColor.BLUE);
		message = message.replace("&e", AnsiColor.YELLOW);
		message = message.replace("&a", AnsiColor.GREEN);
		message = message.replace("&b", AnsiColor.AQUA);
		message = message.replace("&d", AnsiColor.LIGHT_PURPLE);
		message = message.replace("&f", AnsiColor.WHITE);
		message = message.replace("&0", AnsiColor.BLACK);
		message = message.replace("&l", AnsiColor.BOLD);
		message = message.replace("&n", AnsiColor.UNDERLINE);
		message = message.replace("&o", AnsiColor.ITALIC);
		message = message.replace("&k", AnsiColor.MAGIC);
		message = message.replace("&m", AnsiColor.STRIKETHROUGH);
		message = message.replace("&r", AnsiColor.RESET);
		return message  + Ansi.ansi().reset().toString();
		
	}
}
