package typestore.typetempoonline.managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import typestore.typetempoonline.Main;
import typestore.typetempoonline.database.StorageDataBase;
import typestore.typetempoonline.utils.TimeFormatUtils;

public class TopManager {
	
	private Main m;
	private List<ItemStack> its;
	
	public TopManager(Main m) {
		this.m = m;
		its = m.getTopCache().get();
		updateTop();
	}
	
	public void updateTop() {
		Bukkit.getScheduler().runTaskAsynchronously(m, ()->{
		its.clear();
		m.getUserManager().saveAllTopUpdate();
		TimeFormatUtils timeutils = new TimeFormatUtils();
		StorageDataBase db = m.getStorageDataBase();
		for(String player : db.getTopTempo()) {
		ItemStack it = new ItemStack(Material.SKULL_ITEM,1,(short)SkullType.PLAYER.ordinal());
		SkullMeta mt = (SkullMeta)it.getItemMeta();
		mt.setOwner(player);
		mt.setDisplayName("§e"+player);
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add("§e- §7Tempo Online: §e"+timeutils.getTimeString(db.gettempo(player)));
		lore.add("");
		mt.setLore(lore);
		it.setItemMeta(mt);
		its.add(it);
		
		}
		});
	}
	

}
