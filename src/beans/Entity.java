package beans;

import exceptions.EntityValidationException;

public abstract class Entity {

	protected String key;
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String newKey) {
		this.key = newKey;
	}
	
	public abstract void validate() throws EntityValidationException;
}
