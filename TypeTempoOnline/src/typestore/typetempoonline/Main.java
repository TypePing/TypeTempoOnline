package typestore.typetempoonline;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import typestore.typetempoonline.config.ConfigLoader;
import typestore.typetempoonline.database.CredentialsDataBase;
import typestore.typetempoonline.database.MySqlSqlLiteDataBase;
import typestore.typetempoonline.database.StorageDataBase;
import typestore.typetempoonline.managers.TopManager;
import typestore.typetempoonline.managers.UserManager;
import typestore.typetempoonline.minecraft.commands.CommandTempoOnline;
import typestore.typetempoonline.minecraft.events.PlayerClickEvent;
import typestore.typetempoonline.minecraft.events.PlayerJoinQuit;
import typestore.typetempoonline.minecraft.menus.MenuMeuStatus;
import typestore.typetempoonline.minecraft.menus.MenuPrincipal;
import typestore.typetempoonline.minecraft.menus.MenuRecompensas;
import typestore.typetempoonline.minecraft.menus.MenuTag;
import typestore.typetempoonline.minecraft.menus.MenuTopJogadores;
import typestore.typetempoonline.minecraft.placeholders.LegendChatAPI;
import typestore.typetempoonline.minecraft.placeholders.PlaceholderAPI;
import typestore.typetempoonline.minecraft.updates.UpdateSeconds;
import typestore.typetempoonline.minecraft.updates.UpdateTopPlayers;
import typestore.typetempoonline.models.cache.TopCache;
import typestore.typetempoonline.models.cache.UserCache;
import typestore.typetempoonline.utils.ConfigUtils;

public class Main extends JavaPlugin {

	private String prefix;

	// yamls
	private ConfigLoader configloader;
	private ConfigUtils MenuPrincipal;
	private ConfigUtils MenuRecompensas;
	private ConfigUtils MenuMeusStatus;
	private ConfigUtils MenuTag;
	
	// Mysql and SqlLite
	private MySqlSqlLiteDataBase mysqlsqllitedatabase;
	private StorageDataBase storagedatabase;

	// caches
	private UserCache usercache;
	private TopCache topcache;

	// managers
	private UserManager usermanager;
	private TopManager topmanager;

	// menus
	private MenuPrincipal MenuPrincipalInventario;
	private MenuRecompensas MenuRecompensasInventario;
	private MenuMeuStatus MenuMeusStatusInventario;
	private MenuTopJogadores menutopjogadoresinventario;
	private MenuTag menutaginventario;
	@Override
	public void onEnable() {
		long before = System.currentTimeMillis();
		loadAll();
		long now = System.currentTimeMillis();
		long total = now - before;
		CommandSender sc = Bukkit.getConsoleSender();
		sc.sendMessage("§7[§bTypeStore§7]");
		sc.sendMessage("");
		sc.sendMessage(prefix+" §aPlugin iniciado com sucesso em §e"+total+" §ams!");
		sc.sendMessage("§7Para reportar bugs e da sugestoes acesse:");
		sc.sendMessage("");
		sc.sendMessage("§ehttps://discord.gg/MpRMgzEBJN");
		sc.sendMessage("");
	}

	@Override
	public void onDisable() {
		usermanager.saveAllDisable();
	}

	private void loadAll() {
		prefix = "§e[§aTypeTempoOnline§e]";
		loadYamls();
		loadDataBase();
		loadCaches();
		loadManagers();
		loadMenus();
		loadListenerAndCommands();
		loadPlaceHolders();
		run();
		runTop();
	}
	
	//load files .yamls
	private void loadYamls() {
		getConfig().options().copyDefaults(false);
		saveDefaultConfig();
		configloader = new ConfigLoader(this);
		createFolder("menus");
		MenuPrincipal = new ConfigUtils(this, "menus/MenuPrincipal.yml");
		MenuRecompensas = new ConfigUtils(this, "menus/MenuRecompensas.yml");
		MenuMeusStatus = new ConfigUtils(this, "menus/MenuMeusStatus.yml");
		MenuTag = new ConfigUtils(this, "menus/MenuTag.yml");
	}

	// load Mysql and SqlLite
	private void loadDataBase() {
		String host = configloader.host;
		int port = configloader.port;
		String user = configloader.user;
		String password = configloader.password;
		String database = configloader.database;

		CredentialsDataBase credentials = new CredentialsDataBase(host, port, user, password, database);

		mysqlsqllitedatabase = new MySqlSqlLiteDataBase(credentials, this);
		storagedatabase = new StorageDataBase(mysqlsqllitedatabase);
	}

	// load caches
	private void loadCaches() {
		usercache = new UserCache();
		topcache = new TopCache();
	}

	// load managers
	private void loadManagers() {
		usermanager = new UserManager(this);
		topmanager = new TopManager(this);
	}

	// load menus
	private void loadMenus() {
		MenuPrincipalInventario = new MenuPrincipal(this);
		MenuRecompensasInventario = new MenuRecompensas(this);
		MenuMeusStatusInventario = new MenuMeuStatus(this);
		menutopjogadoresinventario = new MenuTopJogadores(this);
		menutaginventario = new MenuTag(this);
	}

	// register listener and commands
	private void loadListenerAndCommands() {
		Bukkit.getPluginManager().registerEvents(new PlayerJoinQuit(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerClickEvent(this), this);
		((CraftServer) getServer()).getCommandMap().register(getConfig().getString("Comandos.comando_principal"),
				(Command) new CommandTempoOnline(getConfig().getString("Comandos.comando_principal"), this));

	}
	//register placeholders
	private void loadPlaceHolders() {
		new PlaceholderAPI(this).register();
		Bukkit.getPluginManager().registerEvents(new LegendChatAPI(this), this);
	}

	// get prefix
	public String getPrefix() {
		return prefix;
	}

	// get yamls
	public ConfigLoader getConfigLoader() {
		return configloader;
	}

	public ConfigUtils getMenuPrincipal() {
		return MenuPrincipal;
	}
	public ConfigUtils getMenuRecompensas() {
		return MenuRecompensas;
	}
	public ConfigUtils getMenuMeusStatus() {
		return MenuMeusStatus;
	}
	public ConfigUtils getMenuTag() {
		return MenuTag;
	}
	// get Mysql and SqlLite
	public MySqlSqlLiteDataBase getMysqlSqlLiteDataBase() {
		return mysqlsqllitedatabase;
	}

	public StorageDataBase getStorageDataBase() {
		return storagedatabase;
	}

	// get caches
	public UserCache getUserCache() {
		return usercache;
	}
	public TopCache getTopCache() {
		return topcache;
	}

	// get managers
	public UserManager getUserManager() {
		return usermanager;
	}
	public TopManager getTopManager() {
		return topmanager;
	}

	// get inventorys
	public MenuPrincipal getMenuPrincipalInventario() {
		return MenuPrincipalInventario;
	}
	public MenuRecompensas getMenuRecompensasInventario() {
		return MenuRecompensasInventario;
	}
	public MenuMeuStatus getMenuMeuStatusInventario() {
		return MenuMeusStatusInventario;
	}
	public MenuTopJogadores getmenutopjogadoresinventario() {
		return menutopjogadoresinventario;
	}
	public MenuTag getMenuTagInventario() {
		return menutaginventario;
	}
	// method create folders
	private void createFolder(String folder) {
		try {
			File langsFolder = new File(getDataFolder() + File.separator + folder + File.separator);
			if (!langsFolder.exists())
				langsFolder.mkdirs();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	// start runnable
	public void run() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin) this, (Runnable) new UpdateSeconds(this), 20L, 20L);
	}
	public void runTop() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin) this, (Runnable) new UpdateTopPlayers(this), 20L, getConfig().getInt("Utildades.atualizar_top")*20L*60);
	}

}
