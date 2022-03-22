package typestore.typetempoonline.minecraft.updates;

import org.bukkit.Bukkit;

import typestore.typetempoonline.Main;
import typestore.typetempoonline.models.User;

public class UpdateSeconds implements Runnable{
	
	private Main m;
	
	public UpdateSeconds(Main m) {
		this.m = m;
	}
	@Override
	public void run() {
		Bukkit.getScheduler().runTaskAsynchronously(m, ()->{	
		for(String player : m.getUserCache().get().keySet()) {
				User us = m.getUserManager().getUser(player);
				int conta = us.getTempo() + 1;
				us.setTempo(conta);
		}
		});
	}
}
