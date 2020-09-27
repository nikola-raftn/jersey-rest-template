package services.file;

import util.Config;

/** Caching mechanism for fetching and storing script files */
public class ScriptService extends ResourceCacheService {

	private static ScriptService instance = null;

	public static ScriptService getInstance() {
		if (instance == null)
			instance = new ScriptService();
		return instance;
	}
	
	private ScriptService() {
		super(Config.SCRIPT_ROOT_LOCATION);
	}
}
