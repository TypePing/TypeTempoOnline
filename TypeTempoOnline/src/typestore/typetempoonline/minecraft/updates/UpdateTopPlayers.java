package typestore.typetempoonline.minecraft.updates;

import org.bukkit.Bukkit;

import typestore.typetempoonline.Main;

public class UpdateTopPlayers implements Runnable{
	
	private Main m;
	
	public UpdateTopPlayers(Main m) {
		this.m = m;
	}
	@Override
	public void run() {
		m.getTopManager().updateTop();
		if(m.getConfigLoader().mensagem_ao_atualizar) {
			for(String msg : m.getConfigLoader().top_atualizado) {
				Bukkit.broadcastMessage(msg);
			}
		}
	}

}
