package bwyap.familyfeud.game.state;

/**
 * This exception is thrown a method has been supplied the incorrect data arguments. 
 * @author bwyap
 *
 */
public class InvalidDataException extends RuntimeException {
	
	private static final long serialVersionUID = -6216092045642880718L;
	
	/**
	 * Create a new exception with the specified message
	 * @param msg
	 */
	public InvalidDataException(String msg) {
		super(msg);
	}
	
}
