package cz.Sicka_gp.MyServer.utils;

import org.fusesource.jansi.Ansi;

public class ColouredConsoleSender{
	
	public static String sendConsoleMessage(String ansicolor, String message){
		return ansicolor + message + Ansi.ansi().reset().toString();
	}
}
