package components;

import java.util.function.Predicate;

import object.SuperObject;

/**
 * The predicate test for determining if an object is an object
 * 
 * @author Robert Wilson
 *
 * @param <T> Generic object being tested
 */
public class ObjectTest<T> implements Predicate<T> {

	@Override
	public boolean test(T t) {
		return t instanceof SuperObject;
	}
}
