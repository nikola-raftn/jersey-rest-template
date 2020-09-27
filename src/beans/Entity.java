package beans;

import exceptions.EntityValidationException;

public abstract class Entity {

	protected String key;
	/** A value which tells us whether this specific Entity has a key which represents its identity or not.
	 *  E.g. if a UserAccount object is created, it should have False value, because it is uniquely represented by its key (username can be key).
	 *  Uncountable objects are checked for existence before being added into the database through EntityDAO, while countable objects have their key
	 *  overwritten by the DAO.create method. 
	 */
	private boolean countable = true;
	
	public String getKey() {
		return key;
	}
	
	public boolean isCountable() {
		return countable;
	}

	public void setCountable(boolean countable) {
		this.countable = countable;
	}

	public void setKey(String newKey) {
		this.key = newKey;
	}
	
	public abstract void validate() throws EntityValidationException;
}
