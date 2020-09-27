package beans;

import exceptions.EntityValidationException;

public class Account extends Entity {

	public String password;
	
	@Override
	public void validate() throws EntityValidationException {
		if (key == null)
			throw new EntityValidationException("Username cannot be null");
		if (key.length() < 5)
			throw new EntityValidationException("Username must be longer than 5 characters");
		if (password == null)
			throw new EntityValidationException("Password cannot be null");
		if (password.length() < 5)
			throw new EntityValidationException("Password must be longer than 5 characters");
	}
}
