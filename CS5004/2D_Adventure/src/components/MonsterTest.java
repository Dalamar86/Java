package components;

import java.util.function.Predicate;

import monster.SuperMonster;

/**
 * The predicate test for determining if an object is a monster
 * 
 * @author Robert Wilson
 *
 * @param <T> Generic object being tested
 */
public class MonsterTest<T> implements Predicate<T> {

	@Override
	public boolean test(T t) {
		return t instanceof SuperMonster;
	}

}
