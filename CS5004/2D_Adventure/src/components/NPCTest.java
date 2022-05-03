package components;

import java.util.function.Predicate;

import npc.SuperNPC;

/**
 * The predicate test for determining if an object is an NPC
 * 
 * @author Robert Wilson
 *
 * @param <T> Generic object being tested
 */
public class NPCTest<T> implements Predicate<T> {

	@Override
	public boolean test(T t) {
		return t instanceof SuperNPC;
	}

}
