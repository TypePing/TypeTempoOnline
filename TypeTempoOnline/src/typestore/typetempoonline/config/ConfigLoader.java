package typestore.typetempoonline.config;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import typestore.typetempoonline.Main;

public class ConfigLoader {

	@SuppressWarnings("unused")
	private Main m;
	private FileConfiguration config;

	// mysql and sqllite
	public boolean ativar;
	public String host;
	public String user;
	public String password;
	public String database;
	public int port;

	// utilidades
	public boolean console_log;
	public boolean exibir_som;
	public boolean glow_ao_coletar;
	public boolean mensagem_ao_atualizar;

	// mensagens
	public List<String> tempo_ver_mensagem;
	public List<String> sem_permissao;
	public List<String> target_offline;
	public List<String> top_atualizado;
	public List<String> tempo_insuficiente;
	public List<String> recompensa_ja_coletada;
	public List<String> recompensa_coletada_com_sucesso;
	public List<String> tag_alterada_com_sucesso;
	public List<String> tag_remove_com_sucesso;
	public List<String> tag_apenas_para_o_top_1;
	
	public ConfigLoader(Main m) {
		this.m = m;
		config = m.getConfig();
		try {
			loadMysqlConfig();
			loadUtilidadesConfig();
			loadMensagensConfig();
		} catch (Exception e) {
			e.printStackTrace();
			Bukkit.getConsoleSender().sendMessage(m.getPrefix() + " §cNao foi possivel carregar a config.yml!");
		}
	}

	private void loadMysqlConfig() {
		String a = "MySQL.";
		ativar = config.getBoolean(a + "ativar");
		host = config.getString(a + "host");
		user = config.getString(a + "user");
		password = config.getString(a + "password");
		database = config.getString(a + "database");
		port = config.getInt(a + "port");
	}

	private void loadUtilidadesConfig() {
		String a = "Utildades.";
		console_log = config.getBoolean(a + "console_log");
		exibir_som = config.getBoolean(a + "exibir_som");
		glow_ao_coletar = config.getBoolean(a + "glow_ao_coletar");
		mensagem_ao_atualizar = config.getBoolean(a + "mensagem_ao_atualizar");
	}

	private void loadMensagensConfig() {
		String a = "Mensagens.";
		List<String> tempo_ver_mensagem1 = config.getStringList(a + "tempo_ver_mensagem");
		tempo_ver_mensagem = (List<String>) tempo_ver_mensagem1.stream().map(s -> s.replace("&", "§"))
				.collect(Collectors.toList());

		List<String> sem_permissao1 = config.getStringList(a + "sem_permissao");
		sem_permissao = (List<String>) sem_permissao1.stream().map(s -> s.replace("&", "§"))
				.collect(Collectors.toList());

		List<String> target_offline1 = config.getStringList(a + "sem_permissao");
		target_offline = (List<String>) target_offline1.stream().map(s -> s.replace("&", "§"))
				.collect(Collectors.toList());

		List<String> top_atualizado1 = config.getStringList(a + "top_atualizado");
		top_atualizado = (List<String>) top_atualizado1.stream().map(s -> s.replace("&", "§"))
				.collect(Collectors.toList());

		List<String> tempo_insuficiente1 = config.getStringList(a + "tempo_insuficiente");
		tempo_insuficiente = (List<String>) tempo_insuficiente1.stream().map(s -> s.replace("&", "§"))
				.collect(Collectors.toList());

		List<String> recompensa_ja_coletada1 = config.getStringList(a + "recompensa_ja_coletada");
		recompensa_ja_coletada = (List<String>) recompensa_ja_coletada1.stream().map(s -> s.replace("&", "§"))
				.collect(Collectors.toList());

		List<String> recompensa_coletada_com_sucesso1 = config.getStringList(a + "recompensa_coletada_com_sucesso");
		recompensa_coletada_com_sucesso = (List<String>) recompensa_coletada_com_sucesso1.stream()
				.map(s -> s.replace("&", "§")).collect(Collectors.toList());

		List<String> tag_alterada_com_sucesso1 = config.getStringList(a + "tag_alterada_com_sucesso");
		tag_alterada_com_sucesso = (List<String>) tag_alterada_com_sucesso1.stream().map(s -> s.replace("&", "§"))
				.collect(Collectors.toList());

		List<String> tag_remove_com_sucesso1 = config.getStringList(a + "tag_remove_com_sucesso");
		tag_remove_com_sucesso = (List<String>) tag_remove_com_sucesso1.stream().map(s -> s.replace("&", "§"))
				.collect(Collectors.toList());
		
		List<String> tag_apenas_para_o_top_11 = config.getStringList(a + "tag_apenas_para_o_top_1");
		tag_apenas_para_o_top_1 = (List<String>) tag_apenas_para_o_top_11.stream().map(s -> s.replace("&", "§"))
				.collect(Collectors.toList());
	}
}
