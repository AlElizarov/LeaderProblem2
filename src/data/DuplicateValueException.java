package data;

public class DuplicateValueException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DuplicateValueException() {
	}

	public DuplicateValueException(String msg) {
		super(msg);
	}

}
