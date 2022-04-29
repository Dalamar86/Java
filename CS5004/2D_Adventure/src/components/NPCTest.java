package components;

import java.util.function.Predicate;

import npc.SuperNPC;

public class NPCTest<T> implements Predicate<T> {

	@Override
	public boolean test(T t) {
		return t instanceof SuperNPC;
	}

}
