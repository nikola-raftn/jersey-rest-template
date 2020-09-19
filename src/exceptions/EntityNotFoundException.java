package exceptions;

public class EntityNotFoundException extends Exception{

	public String message;
	
	public EntityNotFoundException() {
		this.message = "Entity not found.";
	}
	
	public EntityNotFoundException(String errorMessage) {
		this.message = errorMessage;
	}
}
