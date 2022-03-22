package typestore.typetempoonline.minecraft.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import typestore.typetempoonline.Main;

public class PlayerJoinQuit implements Listener{
	
	private Main m;
	
	public PlayerJoinQuit(Main m) {
		this.m = m;
	}
	@EventHandler
	public void aoEntrar(PlayerJoinEvent e) {
		String player = e.getPlayer().getName();
		m.getUserManager().loadPlayer(player);
	}
	@EventHandler
	public void aoSair(PlayerQuitEvent e) {
		String player = e.getPlayer().getName();
		m.getUserManager().savePlayer(player);
	}

}
