/**
 * This is the class implementing the class of exceptions thrown by the put and remove method 
 * of HashDictionary.
 * @author Leland Conn, Student List #: 45, Student #: 250946924
 *
 */
public class DictionaryException extends RuntimeException {
	/**
	 * Creates a DictionaryException object that calls on its
	 * parent class RuntimeException to deal with exceptions
	 * as it does.
	 * @param s is the string made when the exception is throw.
	 */
	public DictionaryException(String s) {
		super(s);
	}

}

