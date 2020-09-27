package storage;

import java.util.Map;

import com.google.gson.*;

import beans.Entity;

import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;

import services.file.IOService;

/** Abstract class for Storage implementation. 
 *  Contains a GSON instance for serializing and deserializing a Map object from and into a JSON string, 
 *  alongisde a file path where the JSON string should be kept on the disk.
 *  @author Nikola
 *  @param <T>
 */
public abstract class Storage<T extends Entity> {

	/** Location of the file where files will be read from and written to */
	protected String fileStorageLocation;
	/** Static Gson object to be shared by all the DB classes for I/O operations */
	public static Gson GSON = new Gson();
	/** Type token for dynamic deserialization 
	 *  Defines which type of object is tied to this class at runtime. */
	protected TypeToken<Map<String, T>> targetType;
	
	/** Template class whose instance is in charge of reading and writing JSON formatted files to and from the files given to it.
	 *  On construction requires an database object type related to it, and the location of the storage where the data will be kept. */
	public Storage(Class<T> dataType, String fileStorageLocation) {
		this.fileStorageLocation = fileStorageLocation;
		
		targetType = new TypeToken<Map<String, T>>() {}
        			.where(new TypeParameter<T>() {}, dataType);
	}
	
	public abstract Map<String, T> readAll();
	
	public abstract void writeAll(Map<String, T> data);
	
	/** Load data from the file specified by the [fileStorageLocation] into a String */
	protected String readFromFile() {
		return IOService.readFile(fileStorageLocation);
	}
	
	/** Write the given string to the file at the location of [fileStorageLocation] */
	protected void writeToFile(String jsonData) {
		IOService.writeToFile(jsonData, fileStorageLocation);
	}
	
	/** Convert the given Json string into a map */
	protected Map<String, T> jsonToMap(String jsonData) {
		return GSON.fromJson(jsonData, targetType.getType());
	}
	
	/** Convert the given data map to a Json string */
	protected String createJsonData(Map<String, T> data) {
		return GSON.toJson(data);
	}
}