package typestore.typetempoonline.minecraft.menus;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import typestore.typetempoonline.Main;
import typestore.typetempoonline.utils.ApiSkull;

public class MenuPrincipal {
	
	@SuppressWarnings("unused")
	private Main m;
	private FileConfiguration file;
	private Inventory inv;
	
	@SuppressWarnings("deprecation")
	public MenuPrincipal(Main m) {
		this.m = m;
		file = m.getMenuPrincipal().getConfig();
		String title = file.getString("nome").replace("&", "?");
		int tamanho = file.getInt("tamanho") * 9;
		
		inv = Bukkit.createInventory(null, tamanho, title);
		ApiSkull apiskull = new ApiSkull();
		for(String key : file.getConfigurationSection("Itens").getKeys(false)) {
			String a = "Itens."+key+".";
			
			String skull = file.getString(a+"skull");
			String nome = file.getString(a+"nome").replace("&", "?");
			int id = file.getInt(a+"id");
			int data = file.getInt(a+"data");
			int slot_inv = file.getInt(a+"slot_inv");
			  List<String> lore = (List<String>) file.getStringList(a+"lore").stream().map(s -> s.replace("&", "?")).collect(Collectors.toList());
		
			  
			  if(skull.equalsIgnoreCase("")) {
				  ItemStack it = new ItemStack(id,1,(short)data);
				  ItemMeta mt = it.getItemMeta();
				  mt.setDisplayName(nome);
				  mt.setLore(lore);
				  it.setItemMeta(mt);
				  inv.setItem(slot_inv, it);
			  }else {
				  ItemStack it = apiskull.getSkull(skull);
				  SkullMeta mt = (SkullMeta)it.getItemMeta();
				  mt.setDisplayName(nome);
				  mt.setLore(lore);
				  it.setItemMeta(mt);
				  inv.setItem(slot_inv, it);
			  }
		}
	}
	public void open(Player p) {
		p.openInventory(inv);
	}

}
