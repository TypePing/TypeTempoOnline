package typestore.typetempoonline.minecraft.placeholders;

import org.bukkit.entity.Player;

import typestore.typetempoonline.Main;
import typestore.typetempoonline.utils.TimeFormatUtils;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class PlaceholderAPI extends PlaceholderExpansion {
	
	private Main m;
	public PlaceholderAPI(Main main) {
		m = main;
	}
	  public boolean canRegister() {
		    return true;
		  }
		  
		  public String getPlugin() {
		    return null;
		  }
		  
		  public String getAuthor() {
		    return "TypeStore";
		  }
		  
		  public String getIdentifier() {
		    return "typetempoonline";
		  }
		  
		  public String getVersion() {
		    return ((Main)Main.getPlugin(Main.class)).getDescription().getVersion();
		  }
		  
		  public String onPlaceholderRequest(Player player, String identifier) {
		    if (player == null)
		      return "-/-"; 
		    if (!m.getUserManager().verificar(player.getName()))
		      return "-/-"; 
		    if (identifier.equals("tempo")) {
		      if (m.getUserManager().getUser(player.getName()).getTempo() <= 0.0D)
		        return "0s"; 
		      TimeFormatUtils timeutils = new TimeFormatUtils();
		      return timeutils.getTimeString(m.getUserManager().getUser(player.getName()).getTempo());
		    } 
		    return "";
		  }
		}
