/**
 * This class represents a record in the ordered dictionary. Each record 
 * consists of two parts: a key and the data associated with the key.
 * @author Leland Conn, , Student List #: 45, Student #: 250946924
 *
 */
public class Record {
	
	private Pair key;
	private String data;
	
	/**
	 * A constructor which initializes a new Record object
	 * with the specified key and data. If the type in the key is
	 * “audio” or “image”, then data stores the name of the
	 * corresponding audio or image file.
	 * @param key is the Pair object storing the word and type
	 * @param data is the data storing text, audio file, or image file
	 */
	public Record(Pair key, String data) {
		this.key = key;
		this.data = data;
	}
	
	/**
	 * Returns the key stored in this Record object.
	 * @return key
	 */
	public Pair getKey() {
		return this.key;
	}
	
	/**
	 * Returns the data stored in this Record object.
	 * @return data
	 */
	public String getData() {
		return this.data;
	}
}
