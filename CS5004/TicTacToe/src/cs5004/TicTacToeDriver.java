package cs5004;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Robert Wilson
 * date: 05 APR 2022
 * class: CS5004 
 */

public class TicTacToeDriver {
	
	
	public static void main(String[] args) {
		TicTacToe ttt1 = new TicTacToeModel();
		
		
		ttt1.move(0, 0);
	    assertEquals(Player.O, ttt1.getTurn());
	    ttt1.move(1, 1);
	    assertEquals(Player.X, ttt1.getTurn());
	    ttt1.move(0, 2);
	    ttt1.move(0, 1);
	    ttt1.move(2, 1);
	    ttt1.move(1, 2);
	    ttt1.move(1, 0);
	    ttt1.move(2, 2);
	    ttt1.move(2, 0);
	    assertTrue(ttt1.isGameOver());
	    assertEquals(Player.X, ttt1.getWinner());
	    assertEquals( " X | O | X\n"
	            	+ "-----------\n"
	            	+ " X | O | O\n"
	            	+ "-----------\n"
	            	+ " X | X | O", ttt1.toString());

	}

}
