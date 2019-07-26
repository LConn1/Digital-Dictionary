/**
 * This class stores an object of type Record and the left and right Nodes in 
 * the Binary Search Tree
 * @author Leland Conn, Student List #: 45, Student #: 250946924
 *
 */

public class Node {
	
	private Record data;
	private Node left;
	private Node right;
	
	/**
	 * A constructor which returns a new Node object with the specified 
	 * data and the left and right node initialized
	 * @param d is the Record object
	 * @param n1 is the left node
	 * @param n2 is the right node
	 */
	public Node(Record d, Node n1, Node n2) {
		
		this.data = d;
		this.left = n1;
		this.right = n2;
		
	}

	/**
	 * Sets the left node.
	 * @param n is the new left node.
	 */
	public void setLeft(Node n) {
		this.left = n;
	}
	
	/**
	 * Sets the right node.
	 * @param n is the new right node
	 */
	public void setRight(Node n) {
		this.right = n;
	}
	
	/**
	 * Gets the left node
	 * @return the left node
	 */
	public Node getLeft() {
		return this.left;
	}
	
	/**
	 * Gets the right node
	 * @return the right node
	 */
	public Node getRight() {
		return this.right;
	}
	
	/**
	 * Returns the data stored in the Node
	 * @return the data
	 */
	public Record getData() {
		return this.data;
	}
	
	/**
	 * Returns 0 if the nodes being compared are equal, 
	 * return 1 if the node is greater than the node compared,
	 * return -1 if the node is less than the node compared.
	 * @param n is the node being compared
	 * @return 0, 1, -1 depending on the equality of the nodes
	 */
	public int compare(Node n) {
		return this.data.getKey().compareTo(n.data.getKey());
	}
	
	/**
	 * Sets the Record object for the node
	 * @param r is the Record object
	 */
	public void setRecord(Record r) {
		this.data = r;
	}
	
}