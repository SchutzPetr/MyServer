package cz.Sicka_gp.MyServer.Compatibility;

import net.minecraft.server.v1_7_R3.EntityPlayer;

import org.bukkit.craftbukkit.v1_7_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Bukkitv1_7_R3 implements Compatibility{
	
	public int getPing(Player p) {
		CraftPlayer cp = (CraftPlayer) p; 
		EntityPlayer ep = cp.getHandle();
		return ep.ping; 
	}
}
