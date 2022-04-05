package tree;
/*
GroupNode is a generic class and child of AbstractTreeNode. 
This node basically take the place of a supervisor. 
It allows for a single data element along with an array list of other elements.

You'll have to include the needed imports, fix the class declaration, and then populate the body with the needed methods.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * The generic class which represent a branch of the Tree collection, having children (leafNodes).  
 * 
 * @author Robert Wilson
 *
 * @param <T> Generic Type of Object
 */
public class GroupNode<T> extends AbstractTreeNode<T> {
	
	// A list of this branch's children
	protected List<TreeNode<T>> children;
	
	/**
	 * Creates a new instance of a GroupNode with an empty child list
	 * 
	 * @param data (Object) The object this node represents.
	 */
	public GroupNode(T data) {
		super(data);
		this.children = new ArrayList<TreeNode<T>>();
	}
	
	/**
	 * Creates a new instance of a GroupNode and populates the children field with the provided children list.
	 * 
	 * @param data (Object) The object this node represents.
	 * @param children (List<T>) A list of child Nodes.
	 */
	public GroupNode(T data, List<TreeNode<T>> children) {
		super(data);
		this.children = children;
	}
	
	@Override
	public TreeNode<T> addChild(Predicate<T> identifier, TreeNode<T> child) {
		// Test our predicate and return this object if our identifier passes
		if (identifier.test(this.data)) {
	      this.children.add(child); //add it here and return
	      return this;
	    }
		// Else check the children of this node and recursively check the entire list 
	    for (int i=0;i<this.children.size();i++) {
	      //add to child and mutate the child
	      this.children.set(i, this.children.get(i).addChild(identifier, child));
	    }
	    return this;
	}

	@Override
	public List<T> toList() {
		// The new list to return
		List<T> result = new ArrayList<T>();
		// Add this node's Object to the list
	    result.add(this.data);
	    // Recursively add this node's children to the list
	    for (TreeNode<T> child: children) {
	      result.addAll(child.toList());
	    }
	    return result;
	}

	@Override
	public <R> TreeNode<R> map(Function<T, R> transform) {
		GroupNode<R> newNode = new GroupNode<R>(transform.apply(this.data));
	    for (TreeNode<T> child:children) {
	      newNode.children.add(child.map(transform));
	    }
	    return newNode;
	}

	@Override
	public T reduce(T initialValue, BiFunction<T, T, T> combiner) {
		// Start with this node's Object
		T result = this.data;
		// Recursively call every other element in the tree
	    for (TreeNode<T> child:children) {
	      result = child.reduce(result, combiner);
	    }
	    //at this point result is the combination of this.data and reductions of its children
	    return combiner.apply(initialValue, result); //combine result and initialValue and return
	}

	@Override
	public void print() {
		System.out.println(this.data);
		for(TreeNode<T> child: children) {
			child.print();
		}
		
	}
}