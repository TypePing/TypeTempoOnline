package typestore.typetempoonline.minecraft.menus;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import typestore.typetempoonline.Main;
import typestore.typetempoonline.utils.Scroller;

public class MenuTopJogadores {

	private Main m;
	private Inventory inv;
	public MenuTopJogadores(Main main) {
		m = main;
		inv = Bukkit.createInventory(null, 6 * 9, "§7TempoOnline > Top");
	
		
		    
		    ItemStack item1 = new ItemStack(Material.BARRIER);
		    ItemMeta meta1 = item1.getItemMeta();
		    meta1.setDisplayName("§cNenhum player encontrado no banco de dados");
		    item1.setItemMeta(meta1);
		    inv.setItem(10, item1);
		    
		  
	}

	public void open(Player p) {
		if (m.getTopCache().get().toString().equalsIgnoreCase("[]")) {
			p.openInventory(inv);
		} else {
			Scroller sc = (new Scroller.ScrollerBuilder())
					.withItemsSlots(new Integer[] { Integer.valueOf(10), Integer.valueOf(11), Integer.valueOf(12),
							Integer.valueOf(13), Integer.valueOf(14), Integer.valueOf(15), Integer.valueOf(16),
							Integer.valueOf(19), Integer.valueOf(20), Integer.valueOf(21), Integer.valueOf(22),
							Integer.valueOf(23), Integer.valueOf(24), Integer.valueOf(25), Integer.valueOf(28),
							Integer.valueOf(29), Integer.valueOf(30), Integer.valueOf(31), Integer.valueOf(32),
							Integer.valueOf(33), Integer.valueOf(34) })
					.withItems(m.getTopCache().get()).withSize(45).withArrowsSlots(18, 26)
					.withName("§7TempoOnline > Top").build();
			sc.open(p);
		}
	}

}
