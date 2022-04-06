package cs5004;

/**
 * Player enum, set to either X or O.  Contains a toString Override
 * 
 * @author Robert Wilson
 * date: 05 APR 2022
 * class: CS5004 
 */
public enum Player {
	X, 
	O;
	
	@Override
	public String toString() {
		switch(this) {
			case X: return "X";
			case O: return "O";
			default: throw new IllegalArgumentException("Player doesn't exist.");
		}
	}
}
