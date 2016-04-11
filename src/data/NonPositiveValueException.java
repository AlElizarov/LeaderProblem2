package data;

public class NonPositiveValueException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NonPositiveValueException() {	
	}
	
	public NonPositiveValueException(String msg){
		super(msg);
	}

}
