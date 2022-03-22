package typestore.typetempoonline.models.cache;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

public class TopCache {
	
	private List<ItemStack> its;
	
	public TopCache() {
		its = new ArrayList<>();
	}
	public List<ItemStack> get() {
		return its;
	}

}
