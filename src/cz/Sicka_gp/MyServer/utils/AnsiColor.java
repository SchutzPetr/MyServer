package cz.Sicka_gp.MyServer.utils;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Attribute;

public class AnsiColor {

	public static String BLACK;
	public static String DARK_BLUE;
	public static String DARK_GREEN;
	public static String DARK_AQUA;
	public static String RESET;
	public static String ITALIC;
	public static String UNDERLINE;
	public static String STRIKETHROUGH;
	public static String BOLD;
	public static String DARK_RED;
	public static String DARK_PURPLE;
	public static String GOLD;
	public static String GRAY;
	public static String DARK_GRAY;
	public static String BLUE;
	public static String GREEN;
	public static String AQUA;
	public static String RED;
	public static String LIGHT_PURPLE;
	public static String YELLOW;
	public static String WHITE;
	public static String MAGIC;
	
	public AnsiColor(){
		BLACK = Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.BLACK).boldOff().toString();
        DARK_BLUE = Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.BLUE).boldOff().toString();
        DARK_GREEN = Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.GREEN).boldOff().toString();
        DARK_AQUA = Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.CYAN).boldOff().toString();
        DARK_RED = Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.RED).boldOff().toString();
        DARK_PURPLE = Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.MAGENTA).boldOff().toString();
        GOLD = Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.YELLOW).boldOff().toString();
        GRAY = Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.WHITE).boldOff().toString();
        DARK_GRAY = Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.BLACK).bold().toString();
        BLUE = Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.BLUE).bold().toString();
        GREEN = Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.GREEN).bold().toString();
        AQUA = Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.CYAN).bold().toString();
        RED = Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.RED).bold().toString();
        LIGHT_PURPLE = Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.MAGENTA).bold().toString();
        YELLOW = Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.YELLOW).bold().toString();
        WHITE = Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.WHITE).bold().toString();
        MAGIC = Ansi.ansi().a(Attribute.BLINK_SLOW).toString();
        BOLD = Ansi.ansi().a(Attribute.UNDERLINE_DOUBLE).toString();
        STRIKETHROUGH = Ansi.ansi().a(Attribute.STRIKETHROUGH_ON).toString();
        UNDERLINE = Ansi.ansi().a(Attribute.UNDERLINE).toString();
        ITALIC = Ansi.ansi().a(Attribute.ITALIC).toString();
        RESET = Ansi.ansi().a(Attribute.RESET).toString();
	}
}
