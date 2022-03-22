package typestore.typetempoonline.minecraft.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import typestore.typetempoonline.Main;
import typestore.typetempoonline.utils.TimeFormatUtils;

public class CommandTempoOnline extends BukkitCommand {
	private Main m;

	public CommandTempoOnline(String name, Main main) {
		super(name);
		setAliases(new ArrayList<String>());
		m = main;
	}

	public boolean execute(CommandSender sender, String alias, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(m.getPrefix() + " §cComando apenas para jogadores!");
			return true;
		}
		Player p = (Player) sender;
		if (args.length == 0) {
			m.getMenuPrincipalInventario().open(p);
			if (m.getConfigLoader().exibir_som) {
				p.playSound(p.getLocation(), Sound.CLICK, 3f, 3f);
			}
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("ver")) {
				if (p.hasPermission("typetempoonline.ver")) {
					TimeFormatUtils timeformat = new TimeFormatUtils();
					for (String msg : m.getConfigLoader().tempo_ver_mensagem) {
						String msg1 = msg.replace("{player}", p.getName());
						String msg2 = msg1.replace("{tempo}",
								timeformat.getTimeString(m.getUserManager().getUser(p.getName()).getTempo()));
						p.sendMessage(msg2);
					}
					if (m.getConfigLoader().exibir_som) {
						p.playSound(p.getLocation(), Sound.CLICK, 3f, 3f);
					}
				} else {
					for (String msg : m.getConfigLoader().sem_permissao) {
						p.sendMessage(msg);
					}
					if (m.getConfigLoader().exibir_som) {
						p.playSound(p.getLocation(), Sound.VILLAGER_NO, 3f, 3f);
					}
					return false;

				}
			}
		}
		if (args.length == 2) {
			Player target = Bukkit.getPlayerExact(args[1]);
			if (target != null) {
				if (p.hasPermission("typetempoonline.ver")) {
					TimeFormatUtils timeformat = new TimeFormatUtils();
					for (String msg : m.getConfigLoader().tempo_ver_mensagem) {
						String msg1 = msg.replace("{player}", target.getName());
						String msg2 = msg1.replace("{tempo}",
								timeformat.getTimeString(m.getUserManager().getUser(target.getName()).getTempo()));
						p.sendMessage(msg2);
					}
					if (m.getConfigLoader().exibir_som) {
						p.playSound(p.getLocation(), Sound.CLICK, 3f, 3f);
					}
				} else {
					for (String msg : m.getConfigLoader().sem_permissao) {
						p.sendMessage(msg);
					}
					if (m.getConfigLoader().exibir_som) {
						p.playSound(p.getLocation(), Sound.VILLAGER_NO, 3f, 3f);
					}

					return false;
				}
			} else {
				for (String msg : m.getConfigLoader().target_offline) {
					p.sendMessage(msg);
				}
				if (m.getConfigLoader().exibir_som) {
					p.playSound(p.getLocation(), Sound.VILLAGER_NO, 3f, 3f);
				}
				return false;
			}
		}
		return false;
	}
}
