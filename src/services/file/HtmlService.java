package services.file;

import util.Config;

/** Caching mechanism for fetching and storing HTML files */
public class HtmlService extends ResourceCacheService {

	private static HtmlService instance = null;

	public static HtmlService getInstance() {
		if (instance == null)
			instance = new HtmlService();
		return instance;
	}
	
	private HtmlService() {
		super(Config.HTML_ROOT_LOCATION);
	}
}