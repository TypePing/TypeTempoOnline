package typestore.typetempoonline.minecraft.placeholders;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import typestore.typetempoonline.Main;
import typestore.typetempoonline.models.User;

public class LegendChatAPI implements Listener {

	private Main m;

	public LegendChatAPI(Main main) {
		m = main;
	}

	@EventHandler
	public void aoFalar(ChatMessageEvent e) {
		if (e.getTags().contains("typetempoonline")) {
			Player p = e.getSender();
			User us = m.getUserManager().getUser(p.getName());
			if (us.getTag().equalsIgnoreCase(""))
				return;
			String a = "Itens." + us.getTag() + ".";
			String prefix = m.getMenuTag().getConfig().getString(a + "prefix").replace("&", "§");
			if(!prefix.equalsIgnoreCase("")) {
			e.setTagValue("typetempoonline", prefix);
			}
		}
	}
}