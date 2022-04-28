package components;

import java.util.function.Predicate;
import monster.SuperMonster;

public class MonsterTest<T> implements Predicate<T> {

	@Override
	public boolean test(T t) {
		return t instanceof SuperMonster;
	}

}
