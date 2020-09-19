package exceptions;

public class FailedDatabaseOperationException extends Exception {
	
	private String message;
	
	public FailedDatabaseOperationException(String errorMessage) {
		this.message = errorMessage;
	}
}
