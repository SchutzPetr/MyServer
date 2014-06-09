package cz.Sicka_gp.MyServer;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import cz.Sicka_gp.MyServer.utils.Material_ID_List;

public class NewbieKit {
	private static MyServer plugin;
	
	public NewbieKit(MyServer instance) {
		plugin = instance;
	}
	
	public static void setKits(Player p){
		for(String item : plugin.getConfig().getStringList("Newbies.Items")){
            String[] itemdata = item.split("-",2);
            int id, amount, itemsdatabyte;
            String[] itemsdata = itemdata[0].split(":", 2);
            try{
            	itemsdatabyte = Integer.parseInt(itemsdata[1]);
                id = Integer.parseInt(itemsdata[0]);
                amount = Integer.parseInt(itemdata[1]);
                p.getInventory().addItem(new ItemStack(Material_ID_List.getMaterialById(id), amount, (byte)itemsdatabyte));
            }
            catch(NumberFormatException e){
               //TODO: ConfigurableMessages.log.info("[ConfigurableMessages] Error! : Incorrect item format '"+ item +"', skipping!");
            }
        }
	}

}
