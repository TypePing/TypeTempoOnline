package typestore.typetempoonline.models.cache;

import java.util.HashMap;
import java.util.Map;

import typestore.typetempoonline.models.User;

public class UserCache {
	
	private Map<String, User> u;
	
	public UserCache() {
		u = new HashMap<>();
	}
	public Map<String, User> get() {
		return u;
	}

}
