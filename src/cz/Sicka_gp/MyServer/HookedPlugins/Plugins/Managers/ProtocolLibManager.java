package cz.Sicka_gp.MyServer.HookedPlugins.Plugins.Managers;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedServerPing;

import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.Configuration.ConfigSettings;
import cz.Sicka_gp.MyServer.utils.Replacer;

public class ProtocolLibManager {
	private static MyServer plugin;
	private static List<String> serverlist;
	private static boolean PlayerList_Enable;
	private static ProtocolManager protocolManager;


	public ProtocolLibManager(MyServer instance) {
		plugin = instance;
		PlayerList_Enable = ConfigSettings.PlayerList_Enable;
		ServerList();
	}
	
	private void ServerList() {
		if(!PlayerList_Enable)return;
		serverlist = plugin.getConfigManager().getConfig().getConfig().getStringList("MOTD.ServerListMotd.PlayerList.Messages");
		protocolManager = ProtocolLibrary.getProtocolManager();
	    protocolManager.addPacketListener(new PacketAdapter(plugin, ListenerPriority.NORMAL, Arrays.asList(new PacketType[] { PacketType.Status.Server.OUT_SERVER_INFO })){
	    	
	    	public void onPacketSending(PacketEvent e) {
	    		handlePing((WrappedServerPing)e.getPacket().getServerPings().read(0));
	    	}
	    	
	    	private void handlePing(WrappedServerPing ping){
	    		List<WrappedGameProfile> profiles = new ArrayList<WrappedGameProfile>();
	    		int i = 0;
	    		for(String s : serverlist){
	    			s = Replacer.replaceColor(s);
	    			profiles.add(new WrappedGameProfile("id" + i, s));
	    			
	    			i++;
	    		}
	    		ping.setPlayers(profiles);
	    	}
	    });
	    		
	}

}
