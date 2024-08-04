package sesvdev.unittestsandbox.exception;

public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(long id) {
		super("Resource not found: " + id);
	}
}
