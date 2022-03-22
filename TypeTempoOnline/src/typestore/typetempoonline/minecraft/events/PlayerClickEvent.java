package typestore.typetempoonline.minecraft.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import typestore.typetempoonline.Main;
import typestore.typetempoonline.database.StorageDataBase;
import typestore.typetempoonline.models.User;
import typestore.typetempoonline.utils.GlowUtils;

public class PlayerClickEvent implements Listener {
	private Main m;

	public PlayerClickEvent(Main m) {
		this.m = m;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getInventory() == null)
			return;
		Player p = (Player) e.getWhoClicked();
		String title = m.getMenuPrincipal().getConfig().getString("nome").replace("&", "§");
		if (e.getView().getTitle().equals(title)) {

			e.setCancelled(true);
			if (e.getCurrentItem() == null)
				return;
			if (e.getCurrentItem().getType() == Material.AIR)
				return;
			for (String key : m.getMenuPrincipal().getConfig().getConfigurationSection("Itens").getKeys(false)) {
				String a = "Itens." + key + ".";
				String nome = m.getMenuPrincipal().getConfig().getString(a + "nome").replace("&", "§");
				String funcao = m.getMenuPrincipal().getConfig().getString(a + "funcao");
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals(nome)) {
					if (funcao.equalsIgnoreCase("open:MenuTopJogadores.java")) {
						m.getmenutopjogadoresinventario().open(p);
						if (m.getConfigLoader().exibir_som) {
							p.playSound(p.getLocation(), Sound.CLICK, 3f, 3f);
						}
					} else if (funcao.equalsIgnoreCase("open:MenuMeuStatus.java")) {
						m.getMenuMeuStatusInventario().open(p);
						if (m.getConfigLoader().exibir_som) {
							p.playSound(p.getLocation(), Sound.CLICK, 3f, 3f);
						}
					} else if (funcao.equalsIgnoreCase("open:MenuRecompensas.java")) {
						m.getMenuRecompensasInventario().open(p);
						if (m.getConfigLoader().exibir_som) {
							p.playSound(p.getLocation(), Sound.CLICK, 3f, 3f);
						}
					}
				}
			}
		} else {
			String title1 = m.getMenuRecompensas().getConfig().getString("nome").replace("&", "§");
			if (e.getView().getTitle().equals(title1)) {
				e.setCancelled(true);
				if (e.getCurrentItem() == null)
					return;
				if (e.getCurrentItem().getType() == Material.AIR)
					return;
				for (String key : m.getMenuRecompensas().getConfig().getConfigurationSection("Itens").getKeys(false)) {
					String a = "Itens." + key + ".";
					String nome = m.getMenuRecompensas().getConfig().getString(a + "nome").replace("&", "§");

					if (e.getCurrentItem().getItemMeta().getDisplayName().equals(nome)) {
						GlowUtils glow = new GlowUtils();
						if (!glow.hasGlow(e.getCurrentItem())) {
							User us = m.getUserManager().getUser(p.getName());
							int preco = m.getMenuRecompensas().getConfig().getInt(a + "preco");
							String coletada = us.getColetadas();
							if (us.getTempo() >= preco) {
								String coletadaJunta = coletada + ";" + key + ";";
								us.setColetadas(coletadaJunta);
								p.closeInventory();
								for (String msg : m.getConfigLoader().recompensa_coletada_com_sucesso) {
									p.sendMessage(msg);
								}
								if (m.getConfigLoader().exibir_som) {
									p.playSound(p.getLocation(), Sound.LEVEL_UP, 5f, 5f);
								}
								for (String recompensa : m.getMenuRecompensas().getConfig()
										.getStringList(a + "recompensa")) {
									String comando = recompensa.replace("{player}", p.getName());
									Bukkit.dispatchCommand(Bukkit.getConsoleSender(), comando);
								}
								return;

							} else {
								p.closeInventory();
								for (String msg : m.getConfigLoader().tempo_insuficiente) {
									p.sendMessage(msg);
								}
								if (m.getConfigLoader().exibir_som) {
									p.playSound(p.getLocation(), Sound.VILLAGER_NO, 5f, 5f);
								}
								return;
							}

						} else {
							p.closeInventory();
							for (String msg : m.getConfigLoader().recompensa_ja_coletada) {
								p.sendMessage(msg);
							}
							if (m.getConfigLoader().exibir_som) {
								p.playSound(p.getLocation(), Sound.VILLAGER_NO, 5f, 5f);
							}
							return;
						}
					}

				}
			} else {
				String title11 = m.getMenuMeusStatus().getConfig().getString("nome").replace("&", "§");
				if (e.getView().getTitle().equals(title11)) {
					e.setCancelled(true);
					if (e.getCurrentItem() == null)
						return;
					if (e.getCurrentItem().getType() == Material.AIR)
						return;
					for (String key : m.getMenuMeusStatus().getConfig().getConfigurationSection("Itens")
							.getKeys(false)) {
						String a = "Itens." + key + ".";
						String nome = m.getMenuMeusStatus().getConfig().getString(a + "nome").replace("&", "§");
						String funcao = m.getMenuMeusStatus().getConfig().getString(a + "funcao");

						if (e.getCurrentItem().getItemMeta().getDisplayName().equals(nome)) {

							if (funcao.equalsIgnoreCase("open:MenuTag.java")) {

								m.getMenuTagInventario().open(p);
								if (m.getConfigLoader().exibir_som) {
									p.playSound(p.getLocation(), Sound.CLICK, 3f, 3f);
								}
							}
						}
					}
				} else {

					String title111 = m.getMenuTag().getConfig().getString("nome").replace("&", "§");
					if (e.getView().getTitle().equals(title111)) {
						e.setCancelled(true);
						if (e.getCurrentItem() == null)
							return;
						if (e.getCurrentItem().getType() == Material.AIR)
							return;

						for (String key : m.getMenuTag().getConfig().getConfigurationSection("Itens").getKeys(false)) {
							String a = "Itens." + key + ".";
							String nome = m.getMenuTag().getConfig().getString(a + "nome").replace("&", "§");
							boolean top_online = m.getMenuTag().getConfig().getBoolean(a + "top_online");
							if (e.getCurrentItem().getItemMeta().getDisplayName().equals(nome)) {
								if (top_online == false) {
									User us = m.getUserManager().getUser(p.getName());
									int preco = m.getMenuTag().getConfig().getInt(a + "preco");
									GlowUtils glow = new GlowUtils();
									if (!glow.hasGlow(e.getCurrentItem())) {
										if (us.getTempo() >= preco) {
											us.setTag(key);
											p.closeInventory();
											for (String msg : m.getConfigLoader().tag_alterada_com_sucesso) {
												p.sendMessage(msg);
											}
											if (m.getConfigLoader().exibir_som) {
												p.playSound(p.getLocation(), Sound.LEVEL_UP, 5f, 5f);
											}

											return;
										} else {
											p.closeInventory();
											for (String msg : m.getConfigLoader().tempo_insuficiente) {
												p.sendMessage(msg);
											}
											if (m.getConfigLoader().exibir_som) {
												p.playSound(p.getLocation(), Sound.VILLAGER_NO, 5f, 5f);
											}
											return;
										}
									} else {
										int slot_inv = m.getMenuTag().getConfig().getInt(a + "slot_inv");
										glow.removeGlow(e.getInventory().getItem(slot_inv));
										us.setTag("");
										p.closeInventory();
										for (String msg : m.getConfigLoader().tag_remove_com_sucesso) {
											p.sendMessage(msg);
										}
										if (m.getConfigLoader().exibir_som) {
											p.playSound(p.getLocation(), Sound.LEVEL_UP, 5f, 5f);
										}

										return;
									}
								} else {
									StorageDataBase db = m.getStorageDataBase();
									if (!db.getTopTempo().toString().equalsIgnoreCase("[]")) {
										if (db.getTopTempo().get(0).equalsIgnoreCase(p.getName())) {
											User us = m.getUserManager().getUser(p.getName());
											
											GlowUtils glow = new GlowUtils();
											if (!glow.hasGlow(e.getCurrentItem())) {
												
													us.setTag(key);
													p.closeInventory();
													for (String msg : m.getConfigLoader().tag_alterada_com_sucesso) {
														p.sendMessage(msg);
													}
													if (m.getConfigLoader().exibir_som) {
														p.playSound(p.getLocation(), Sound.LEVEL_UP, 5f, 5f);
													}

													return;
												
											} else {
												int slot_inv = m.getMenuTag().getConfig().getInt(a + "slot_inv");
												glow.removeGlow(e.getInventory().getItem(slot_inv));
												us.setTag("");
												p.closeInventory();
												for (String msg : m.getConfigLoader().tag_remove_com_sucesso) {
													p.sendMessage(msg);
												}
												if (m.getConfigLoader().exibir_som) {
													p.playSound(p.getLocation(), Sound.LEVEL_UP, 5f, 5f);
												}

												return;
											}
										} else {
											p.closeInventory();
											for (String msg : m.getConfigLoader().tag_apenas_para_o_top_1) {
												p.sendMessage(msg);
											}
											if (m.getConfigLoader().exibir_som) {
												p.playSound(p.getLocation(), Sound.VILLAGER_NO, 5f, 5f);
											}

										}
									} else {
										p.closeInventory();
										for (String msg : m.getConfigLoader().tag_apenas_para_o_top_1) {
											p.sendMessage(msg);
										}
										if (m.getConfigLoader().exibir_som) {
											p.playSound(p.getLocation(), Sound.VILLAGER_NO, 5f, 5f);
										}

									}
								}
							}
						}

					}

				}
			}
		}
	}
}
