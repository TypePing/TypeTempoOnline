package typestore.typetempoonline.managers;

import java.util.Map;

import org.bukkit.Bukkit;

import typestore.typetempoonline.Main;
import typestore.typetempoonline.database.StorageDataBase;
import typestore.typetempoonline.models.User;

public class UserManager {

	private Main m;
	private Map<String, User> u;

	public UserManager(Main m) {
		this.m = m;
		u = m.getUserCache().get();
	}

	public User getUser(String player) {
		return u.get(player);
	}

	public boolean verificar(String player) {
		if (u.containsKey(player)) {
			return true;
		} else {
			return false;
		}
	}

	public void loadPlayer(String player) {
		Bukkit.getScheduler().runTaskAsynchronously(m, () -> {
			StorageDataBase db = m.getStorageDataBase();
			if (!verificar(player)) {
				if (db.verificarPlayer(player)) {
					User us = new User(player, db.getcoletadas(player), db.gettag(player), db.gettempo(player));
					u.put(player, us);
					if (m.getConfigLoader().console_log) {
						Bukkit.getConsoleSender()
								.sendMessage(m.getPrefix() + " §aPlayer §e" + player + " §acarregado com sucesso!");
					}
				} else {
					User us = new User(player, ".", "", 0);
					u.put(player, us);
					if (m.getConfigLoader().console_log) {
						Bukkit.getConsoleSender()
								.sendMessage(m.getPrefix() + " §aPlayer §e" + player + " §acarregado com sucesso!");
					}
				}
			}
		});
	}

	public void savePlayer(String player) {
		Bukkit.getScheduler().runTaskAsynchronously(m, () -> {
			StorageDataBase db = m.getStorageDataBase();
			if (verificar(player)) {
				User us = getUser(player);
				if (db.verificarPlayer(player)) {
					db.setcoletadas(player, us.getColetadas());
					db.settempo(player, us.getTempo());
					db.settag(player, us.getTag());
					u.remove(player);
					if (m.getConfigLoader().console_log) {
						Bukkit.getConsoleSender()
								.sendMessage(m.getPrefix() + " §aPlayer §e" + player + " §asalvo com sucesso!");
					}
				} else {
					db.setPlayer(player, us.getColetadas(), us.getTempo(), us.getTag());
					u.remove(player);
					if (m.getConfigLoader().console_log) {
						Bukkit.getConsoleSender()
								.sendMessage(m.getPrefix() + " §aPlayer §e" + player + " §asalvo com sucesso!");
					}
				}
			}
		});
	}

	public void saveAllDisable() {
		int i = 0;
		StorageDataBase db = m.getStorageDataBase();
		for (String player : u.keySet()) {
			if (verificar(player)) {
				User us = getUser(player);
				if (db.verificarPlayer(player)) {
					db.setcoletadas(player, us.getColetadas());
					db.settempo(player, us.getTempo());
					db.settag(player, us.getTag());
					u.remove(player);
					i++;
				} else {
					db.setPlayer(player, us.getColetadas(), us.getTempo(), us.getTag());
					u.remove(player);
					i++;
				}
			}
		}
		if (m.getConfigLoader().console_log) {
			Bukkit.getConsoleSender().sendMessage(m.getPrefix() + " §aForam salvos §e" + i + " §aplayers com sucesso!");
		}
	}

	public void saveAllTopUpdate() {
		StorageDataBase db = m.getStorageDataBase();
		for (String player : u.keySet()) {
			User us = getUser(player);
			if (db.verificarPlayer(player)) {
				db.settempo(player, us.getTempo());
			} else {
				db.setPlayer(player, us.getColetadas(), us.getTempo(), us.getTag());
			}
		}
	}

}
