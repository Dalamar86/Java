package cs5004;

/**
 * @author Robert Wilson
 * date: 05 Apr 2022
 * class: CS5004 
 */

import java.util.Arrays;
import java.util.stream.Collectors;

public class TicTacToeModel implements TicTacToe {
  // add your implementation here

  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard()).map(
      row -> " " + Arrays.stream(row).map(
        p -> p == null ? " " : p.toString()).collect(Collectors.joining(" | ")))
          .collect(Collectors.joining("\n-----------\n"));
    // This is the equivalent code as above, but using iteration, and still using 
    // the helpful built-in String.join method.
    /**********
    List<String> rows = new ArrayList<>();
    for(Player[] row : getBoard()) {
      List<String> rowStrings = new ArrayList<>();
      for(Player p : row) {
        if(p == null) {
          rowStrings.add(" ");
        } else {
          rowStrings.add(p.toString());
        }
      }
      rows.add(" " + String.join(" | ", rowStrings));
    }
    return String.join("\n-----------\n", rows);
    ************/
  }

@Override
public void move(int r, int c) {
	// TODO Auto-generated method stub
	
}

@Override
public Player getTurn() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public boolean isGameOver() {
	// TODO Auto-generated method stub
	return false;
}

@Override
public Player getWinner() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Player[][] getBoard() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Player getMarkAt(int r, int c) {
	// TODO Auto-generated method stub
	return null;
}
}
