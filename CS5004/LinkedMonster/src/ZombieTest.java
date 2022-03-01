import java.util.function.Predicate;

public class ZombieTest implements Predicate<Monster>{
	
	public boolean test(Monster m) {
		return m instanceof Zombie;
	}
}
