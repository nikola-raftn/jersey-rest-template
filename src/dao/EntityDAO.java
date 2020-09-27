package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.Entity;
import javafx.collections.MapChangeListener;
import javafx.collections.MapChangeListener.Change;
import storage.Storage;

import com.sun.javafx.collections.ObservableMapWrapper;

@SuppressWarnings("all")
public class EntityDAO <T extends Entity> {
	
	protected ObservableMapWrapper<String, T> database;
	protected String idHeader;
	private int counter;
	private Storage storage;
	
	public EntityDAO(Storage storage) {
		this.storage = storage;
		
		Map<String, T> storedData = storage.readAll();
		if (storedData == null)
			storedData = new HashMap<String, T>();
		
		database = new ObservableMapWrapper<String, T>(storedData);
		database.addListener(new MapChangeListener<String, T>() {

			@Override
			public void onChanged(Change<? extends String, ? extends T> change) {
				forceUpdate();
			}
		});
		
		counter = database.size() + 100;
	}
	
	/** Add object to the database. If the object is countable, it is added to the database while having its key overwritten by the DAO class internal counter.
	 *  If the object is uncountable, it's checked for existence inside the database, and only added if there is no object in the database with the same key.
	 * @param 
	 * @return Object or null if they already exist.
	 */
	public T create(T object) {
		if (object.isCountable()) {
			object.setKey(Integer.toString(++counter));
			database.put(object.getKey(), object);
		}
		else {
			T previousValue = database.getOrDefault(object.getKey(), null);
			if (previousValue == null)
				database.put(object.getKey(), object);
			else
				return null;
		}
		return object;
	}
	
	/** Get an object from the database with the specified key attached to it.
	 * @param 
	 * @return Object or null if it doesn't exist in the database.
	 */
	public T getByKey(String key) {
		return database.getOrDefault(key, null);
	}
	
	/** Returns a collection of all objects from the database */
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
		forceUpdate();
		
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
	
	/** Writes the current state of the database into the file specified within the Storage object of this DAO */
	public void forceUpdate() {
		storage.writeAll(database);
	}
}
