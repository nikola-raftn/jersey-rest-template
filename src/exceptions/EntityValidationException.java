package exceptions;

public class EntityValidationException extends Exception {

	public String message;
	
	public EntityValidationException(String errorMessage) {
		this.message = errorMessage;
	}
}
