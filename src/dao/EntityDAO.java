package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.Entity;

public class EntityDAO <T extends Entity> {
	
	protected Map<String, T> database;
	protected String idHeader;
	private int counter;
	
	public EntityDAO() {
		database = new HashMap<String, T>();
		counter = 0;
	}
	
	/** Add object to the database
	 * @param 
	 * @return Object or null if they already exist.
	 */
	public T create(T object) {
		object.setKey(Integer.toString(++counter));
		database.put(object.getKey(), object);
		
		return object;
	}
	
	/** Get an object from the database with the specified key attached to it. If key is null or object is deleted, null is returned.
	 * @param 
	 * @return Object or null if the key doesn't exist.
	 */
	public T getByKey(String key) {
		return database.getOrDefault(key, null);
	}
	
	/** Returns a collection of all bean objects from the database */
	public List<T> getAll(){
		ArrayList<T> allEntries = new ArrayList<T>(); 
		
		for (T entry : database.values()) {
			allEntries.add(entry);
		}
		
		return allEntries;
	}
	
	/** Update an existing object 
	 *  @return Returns the updated object, or null if the object with such key doesn't already exist */
	public T update(T obj) {
		if (database.getOrDefault(obj.getKey(), null) == null)
			return null;
		
		database.put(obj.getKey(), obj);
		
		return obj;
	}
	
	/** Removes an object with the specified key from the database 
	 * 
	 * @param key
	 * @return The deleted object, or null if the object wasn't found
	 */
	public T deleteByID(String key) {
		return database.remove(key);
	}
}
