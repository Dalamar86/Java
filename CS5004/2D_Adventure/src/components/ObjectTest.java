package components;

import java.util.function.Predicate;

import object.SuperObject;

public class ObjectTest<T> implements Predicate<T> {

	@Override
	public boolean test(T t) {
		return t instanceof SuperObject;
	}
}
