package services.file;

import java.util.HashMap;
import java.util.Map;

public class ResourceCacheService {

	private Map<String, String> pages;
	private String relativePathToFiles;
	
	protected ResourceCacheService(String pathToFiles) {
		pages = new HashMap<String, String>();
		relativePathToFiles = pathToFiles;
	}
	
	public String get(String resourceName) {
		String page = pages.get(resourceName);
		if (page == null) { // Page has not yet been loaded
			page = IOService.readSourceFile(relativePathToFiles + resourceName);
			pages.put(resourceName, page);
		}
		
		if (page.isEmpty()) 
			System.out.println("Couldn't find file: " + resourceName);
		
		return page;
	}
}
