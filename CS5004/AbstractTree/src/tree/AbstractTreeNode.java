package tree;

/*
AbstractTreeNode takes it's orders from TreeNode and sets up the basic, very simple, structure of a tree node. 
You'll have to fix the class declaration and complete this file. 
*/

/**
 * The abstract class which holds all defaults methods for the Tree collection.
 * 
 * @author Robert Wilson
 *
 * @param <T> Generic Type of Object
 */
public abstract class AbstractTreeNode<T> implements TreeNode<T> {
	// This holds the Object of this node
	protected T data;
	
	/**
	 * Creates a new node with the data provided of a generic type
	 * 
	 * @param data (Object) Objects to be stored in this node
	 */
	public AbstractTreeNode(T data) {
		this.data = data;
	}
	
	
}