/**
 * This class represents the key attribute of a record in the ordered dictionary.
 * Each key has two parts: word and type. Attribute word is a string of one or more letters;
 * the letters in word are converted to lower case. The attribute type can be “text”,
 * “audio”, or “image”, as specified above.
 * @author Leland Conn, , Student List #: 45, Student #: 250946924
 *
 */
public class Pair {

	private String word;
	private String type;
	
	/**
	 * A constructor which initializes a new Pair ob ject with the specified word and type.
	 * @param word is the word data
	 * @param type is the type of the data
	 */
	public Pair(String word, String type) {
		this.word = word.toLowerCase();
		this.type = type;
	}
	
	/**
	 * Returns the word stored in this Pair object.
	 * @return word
	 */
	public String getWord() {
		return this.word;
	}
	
	/**
	 * Returns the type stored in this Pair object.
	 * @return type
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * Returns 0 if the key stored in this Pair object is equal to k,
	 * returns -1 if the key stored in this Pair object is smaller than k,
	 * and it returns 1 otherwise.
	 * @param k is the Pair object being compared to
	 * @return 0, 1, or -1 depending on the equality of the Pair objects
	 */
	public int compareTo(Pair k) {
		if (this.word.equals(k.word) && this.type.equals(k.type)) {
			return 0;
		}
		int result1 = this.word.compareTo(k.word);
		int result2 = this.type.compareTo(k.type);
		if (result1 < 0 || (result1 == 0 && result2 < 0)) {
			return -1;
		}
		else {
			return 1;
		}
	}
	
	
}
