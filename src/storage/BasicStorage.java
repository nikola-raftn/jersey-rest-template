package storage;

import java.util.Map;

import beans.Entity;

/**  Class whose instance is in charge of reading and writing JSON formatted files to and from the files given to it.
 *  On construction requires an database object type related to it, and the location of the storage where the data will be kept.
 *  Files are stored in a simple JSON format with no preprocessing. */
public class BasicStorage<T extends Entity> extends Storage<T>{
	
	public BasicStorage(Class<T> dataType, String fileStorageLocation) {
		super(dataType, fileStorageLocation);
	}

	/** Return a Map of all the JSON objects which were saved in the specified file. */
	public Map<String, T> readAll() {
		String jsonData = super.readFromFile();						// This fetches the JSON-format objects from the text file 
        return super.jsonToMap(jsonData);		// This deserializes the JSON string into a List of objects
	}
	
	/** Write all objects from the specified map to a file specified by the location parameter. */
	public void writeAll(Map<String, T> data) {
		String jsonData = super.createJsonData(data);	// Create a JSON string from the given Map<String, T> object
		super.writeToFile(jsonData);		// Write the newly created JSON string to the file specified by the fileStorageLocation
	}
}
