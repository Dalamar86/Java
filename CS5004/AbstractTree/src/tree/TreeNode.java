package tree;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/*
Basic tree node interface. I've left this body populated, but I've removed all comments. 
The only thing you'll have to do is add comments and imports. 
*/
 
 public interface TreeNode<T>
	{
	TreeNode<T> addChild(Predicate<T> identifier, TreeNode<T> child);
	List<T> toList();
	<R> TreeNode<R> map(Function<T,R> transform);
	T reduce(T initialValue, BiFunction<T,T,T> combiner);
	public void print();
	}
 
 