package data;

public class EmptyDataException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmptyDataException() {
	}
	
	public EmptyDataException(String msg){
		super(msg);
	}

}
