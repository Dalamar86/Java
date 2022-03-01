import java.util.function.Predicate;

public class WerewolfTest implements Predicate<Monster>{
	
	public boolean test(Monster m) {
		return m instanceof Werewolf;
	}
}
