package tree;
/*
LeafNode is a generic class and child of AbstractTreeNode. 
This node allows for a single data element. 

You'll have to include the needed imports, fix the class declaration, and then populate the body with the needed methods.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * The end of a GroupNode, the LeafNode is the last item in this line from the root.
 * 
 * @author Robert Wilson
 *
 * @param <T> Generic Type of Object
 */
public class LeafNode<T> extends AbstractTreeNode<T> {
	
	/**
	 * Creates a LeafNode with the passed Object
	 * 
	 * @param data (Object) Generic Type of Object
	 */
	public LeafNode(T data) {
		super(data);
	}

	@Override
	public TreeNode<T> addChild(Predicate<T> identifier, TreeNode<T> child) {
		// Test our predicate and a new GroupNode if the identifier passes
		if (identifier.test(data)) {
		      //promote this to a group tree node
		      GroupNode<T> newNode = new GroupNode<T>(this.data);
		      newNode.addChild(identifier, child);
		      return newNode;
		}
		// Otherwise return this LeafNode
		return this;
	}

	@Override
	public List<T> toList() {
		// Create a new ArrayList to return this data as part of.
		List<T> result = new ArrayList<T>();
	    result.add(this.data);
	    return result;
	}

	@Override
	public <R> TreeNode<R> map(Function<T, R> transform) {
		// End the recursive call by applying the transform on this objects data.
		return new LeafNode<R>(transform.apply(this.data));
	}

	@Override
	public T reduce(T initialValue, BiFunction<T, T, T> combiner) {
		// End the recursive call by combining the initial value with this node's object.
		return combiner.apply(initialValue, this.data);
	}

	@Override
	public void print() {
		System.out.println(this.data);		
	}
}
