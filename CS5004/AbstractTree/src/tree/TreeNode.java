package tree;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/*
Basic tree node interface. I've left this body populated, but I've removed all comments. 
The only thing you'll have to do is add comments and imports. 
*/

 /**
  * The interface detailing the ADT of the Tree hierarchical collection. 
  * 
  * @author Robert Wilson
  * @param <T> Generic Type of Object
  */
 public interface TreeNode<T>
	{
	 
	 /**
	  * Adds a new node to the Tree.  Which node is added depends on the identifier (supervisor) passed.
	  * 
	  * @param identifier (predicate) A predicate which determines which node gets added, group or leaf.
	  * @param child (Object) The object to save to the data field.
	  * @return
	  */
	TreeNode<T> addChild(Predicate<T> identifier, TreeNode<T> child);
	
	/**
	 * Returns a list of all Objects. First by the root, then the group, followed by their children.
	 * 
	 * @return (List<T>) List of all objects.
	 */
	List<T> toList();
	
	/**
	 * Maps this Tree of node Objects (T) onto a new Tree of type R, whose type is defined by the function passed in. 
	 * 
	 * @param <R> Type of the new Tree to be created
	 * @param transform (Function) The function which determines what the return type (R) will be
	 * @return (TreeNode) A new Tree which has been mapped from the original.
	 */
	<R> TreeNode<R> map(Function<T,R> transform);
	
	/**
	 * Reduces the tree down to one Object T using the combiner and the BiFunction interface.
	 * 
	 * @param initialValue (Object) Initial value to start the combination at.
	 * @param combiner (BiFunction) Function which takes in two arguments and combines them into one using the given function.
	 * @return (Object) The object to be returned after the combination has been completed.
	 */
	T reduce(T initialValue, BiFunction<T,T,T> combiner);
	
	/**
	 * Calls the toString of the Objects in the tree, starting at the root and moving in a DepthFirst search pattern.
	 */
	public void print();
	}
 
 