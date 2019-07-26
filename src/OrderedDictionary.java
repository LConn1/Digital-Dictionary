/**
 * This class implements an ordered dictionary using a binary search tree.
 * You must use a Record object to store the data contained in each node of the tree.
 * In your binary search tree only the internal nodes will store information.
 * The leaves are nodes storing null Record objects. The key for an internal node 
 * is the Pair object from the Record stored in that node.
 * @author Leland Conn, , Student List #: 45, Student #: 250946924
 *
 */
public class OrderedDictionary implements OrderedDictionaryADT {
	
	private Node root;
	
	/**
	 * A constructor which initializes a new OrderedDictionaryObject
	 * with a root Node storing null values.
	 */
	public OrderedDictionary() {
		root = new Node(null, null, null);
	}
	
	/**
	 * Returns the Record object with key k, or it returns null if
	 * such a record is not in the dictionary.
	 * @return the data stored in the node with Pair k
	 */
	public Record get(Pair k) {
		return getNode(this.root, k).getData();
	}
	
	/**
	 * Returns the Node object with key k, or it returns null if
	 * such a record is not in the dictionary.
	 * @param r is the root node
	 * @param k is the Pair object stored in the desired node
	 * @return the requested Node or a leaf Node
	 */
	private Node getNode(Node r, Pair k) {
		if (r.getData() == null) {
			return r;
		}
		else {
			//if Pair k is equal to Pair object stored in Node r
			if (k.compareTo(r.getData().getKey()) == 0) {
				return r;
			}
			//if Pair k is less than the Pair object stored in Node r
			else if (k.compareTo(r.getData().getKey()) < 0) {
				return getNode(r.getLeft(), k);
			}
			else {
				return getNode(r.getRight(), k);
			}
		}
	}
	
	/**
	 * Inserts r into the ordered dictionary. It throws a DictionaryException
	 * if a record with the same key as r is already in the dictionary.
	 * @param r is the Record object stored in the Node being inserted
	 */
	public void put(Record r) throws DictionaryException {
		Node p = getNode(this.root, r.getKey());
		if (p.getData() != null) {
			throw new DictionaryException("A record with the given key (" + r.getKey().getWord() + ", " 
		    + r.getKey().getType() + ") is already in the ordered dictionary.");
		}
		else {
			p.setRecord(r);
			Node n1 = new Node(null, null, null);
			Node n2 = new Node(null, null, null);
			p.setLeft(n1);
			p.setRight(n2);
		}
	}
	
	/**
	 * Removes the record with key k from the dictionary. It throws a DictionaryException
	 * if the record is not in the dictionary.
	 * @param k is the Pair object stored in the Node being removed
	 */
	public void remove(Pair k) throws DictionaryException {
		//Find the node storing Pair k
		Node p = getNode(this.root, k);
		//If p is a leaf node
		if (p.getData() == null) {
			throw new DictionaryException("No record in the ordered dictionary has key (" + k.getWord() + ", " + k.getType() + ")");
		}
		else {
			if (p.getLeft() == null || p.getRight() == null) {
				//q is the parent of p
				Node q = parent(this.root, p.getData().getKey());
				Node c;
				//If the left child of p is a leaf
				if (p.getLeft().getData() == null) {
					c = p.getRight();
				}
				else {
					c = p.getLeft();
				}
				//if p is equal to the root
				if (p.compare(this.root) == 0) {
					this.root = c;
					c = null;
				}
				else {
					//if the right child of q is equal to p
					if (q.getRight().compare(p) == 0) {
						q.setRight(c);
					}
					else {
						q.setLeft(c);
					}
				}
			}
			else {
				Node s = smallestNode(p.getRight());
				p.setRecord(s.getData());
				
			}
		}
	}
	
	/**
	 * Returns the successor of k (the record from the ordered
	 * dictionary with smallest key larger than k);
	 * it returns null if the given key has no successor.
	 * The given key DOES NOT need to be in the dictionary.
	 * @param k is the Pair object stored in the Node we are trying
	 * to find the successor of.
	 * @return the Record object stored in the successor Node with Pair object k.
	 */
	public Record successor(Pair k) {
		Node result = successorNode(this.root, k);
		if (result != null) {
			return result.getData();
		}
		return null;
	}
	
	/**
	 * Returns the successor node of the Node storing Pair k.
	 * It returns null if the given key has no successor.
	 * @param r is the root node
	 * @param k is the Pair object stored the node we are trying
	 * to find the successor of.
	 * @return null or the successor node
	 */
	private Node successorNode(Node r, Pair k) {
		if (r.getData() == null) {
			return null;
		}
		else {
			//Find the node storing Pair k
			Node p = getNode(r, k);
			if (p.getData() != null && p.getRight().getData() != null) {
				return smallestNode(p.getRight());
			}
			else {
				//q is the parent of the node storing the object with Pair k
				Node q = parent(r, k);
				while (q.getRight().getData() != null && p.compare(r) != 0 && q.getRight().compare(p) == 0) {
					//p becomes its parent node
					p = q;
					//q becomes its parent node
					q = parent(this.root, p.getData().getKey());
				}
				//If p is equal to the root
				if (p.compare(r) == 0) {
					return null;
				}
				else {
					return q;
				}
			}
		}
	}
	
	/**
	 * Returns the parent of Node with object Pair k
	 * @param r is the root node
	 * @param k is the Pair object stored in the node we
	 * are trying to find the parent of
	 * @return the parent Node
	 */
	private Node parent(Node r, Pair k) {
		if (r.getLeft().getData() == null && r.getRight().getData() == null) {
			return r;
		}
		if ((r.getRight().getData() != null && k.compareTo(r.getRight().getData().getKey()) == 0) 
		   || (r.getLeft().getData() != null && k.compareTo(r.getLeft().getData().getKey()) == 0)) {
			return r;
		}
		else {
			if (r.getLeft().getData() != null && k.compareTo(r.getData().getKey()) < 0) {
				return parent(r.getLeft(), k);
			}
			else {
				return parent(r.getRight(), k);
			}
		}
	}
	
	/**
	 * Returns the predecessor of k (the record from the ordered dictionary with largest key
	 * smaller than k; it returns null if the given key has no predecessor. The given key DOES
	 * NOT need to be in the dictionary.
	 * @param k is the Pair object stored in the node we are trying to find the predecessor of
	 * @return the predecessor node or null
	 */
	public Record predecessor(Pair k) {
		Node result = predecessorNode(this.root, k);
		if (result != null) {
			return result.getData();
		}
		return null;
	}
	
	/**
	 * Returns the predecessor Node of the Node storing Pair object k; returns null if the given key
	 * has no predecessor
	 * @param r is the root of the tree
	 * @param k is the Pair object stored in the node we are trying to find the predecessor of
	 * @return the predecessor node or null
	 */
	private Node predecessorNode(Node r, Pair k) {
		if (r.getData() == null) {
			return r;
		}
		else {
			//Find the node storing Pair k
			Node p = getNode(r, k);
			if (p.getData().getData() != null && p.getLeft().getData() != null) {
				return largestNode(p.getLeft());
			}
			else {
				//q is the parent of the Node storing Pair k
				Node q = parent(r, k);
				while (p.compare(r) != 0 && q.getLeft().getData() != null && q.getLeft().compare(p) == 0) {
					//p is assigned to be its parents
					p = q;
					//If p is equal to the root
					if (p.compare(this.root) == 0) {
						return null;
					}
					//q is assigned to be its parent
					q = parent(this.root, p.getData().getKey());
				}
				return q;
			}
		}
	}
	
	/**
	 * Returns the record with smallest key in the ordered dictionary.
	 * Returns null if the dictionary is empty.
	 * @return Record object or null
	 */
	public Record smallest() {
		return smallestNode(this.root).getData();
	}
	
	/**
	 * Returns the Node with the smallest key in the ordered dictionary.
	 * @param r is the root node or leaf node
	 * @return the requested node
	 */
	private Node smallestNode(Node r) {
		if (r.getData() == null) {
			return r;
		}
		else {
			//Assign p to the root node
			Node p = r;
			//while the left child of p is not a leaf node
			while (p.getLeft().getData() != null) {
				p = p.getLeft();
			}
			return p;
		}
	}
	
	/**
	 * Returns the record with largest key in the ordered dictionary.
	 * Returns null if the dictionary is empty.
	 * @return Record object or null
	 */
	public Record largest() {
		return largestNode(this.root).getData();
	}
	
	/**
	 * Returns the Node with the largest key in the ordered dictionary
	 * @param r is the root node
	 * @return the requested node or leaf node
	 */
	private Node largestNode(Node r) {
		if (r.getData() == null) {
			return r;
		}
		else {
			//Assign p to the root node
			Node p = r;
			//while the right child of p is not a leaf node
			while (p.getRight().getData() != null) {
				p = p.getRight();
			}
			return p;
		}
	}
}
