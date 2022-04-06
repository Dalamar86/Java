package cs5004;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * TicTacToe model contains all functionality of the game.
 * 
 * @author Robert Wilson
 * date: 05 APR 2022
 * class: CS5004 
 */
public class TicTacToeModel implements TicTacToe {
	// The Game Board
	private Player[][] board; 
	// Our current player
	private Player player;
	// GameState
	private boolean gameOver;
	// The size of our board[size][size]
	private final int size = 3;

	/**
	 * Constructor sets the first player to X, creates a new board of size*size, and sets the win condition to false.
	 */
	public TicTacToeModel() {
		this.player = Player.X;
		this.board = new Player[size][size];
		this.gameOver = false;
	}
	
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
		if(!gameOver) {
			if(r < 0 || r > size || c > size || c < 0) {
				throw new IllegalArgumentException("This move is out of bounds.");
			}
			if(this.board[r][c] == null) {
				this.board[r][c] = this.player;
				if(!isGameOver()) {
					switch(player) {
						case X: this.player = Player.O; break;
						case O: this.player = Player.X; break;
						default: break;
					}
				}
				return;
			} throw new IllegalArgumentException("Spot is already occupied.");
		} throw new IllegalStateException("Game is over.");
	}
	
	@Override
	public Player getTurn() {
		return this.player;
	}
	
	@Override
	public boolean isGameOver() {
		if(!gameOver) {
			// Our winning checks count
			int inARow = 0;
			int inACol = 0;
			int inADiag = 0;
			int isFull = 0;
			for(int r=0; r < this.board.length; r++) {
				for(int c=0; c < this.board.length; c++) {
					// Check for a win in the rows
					if(this.board[r][c] == this.player) {
						inARow++;
					}
					// Check for a win in the columns
					if(this.board[c][r] == this.player) {
						inACol++;
					}
					if(this.board[r][c] != null) {
						isFull++;
					}
				}
				// Check for a diagonal win from top left to bottom right
				if(this.board[r][r] == this.player) {
					inADiag++;
				}
				// If one of these equals three this player has won,
				// else reset to zero for the next row and column.
				if(inARow == size || inACol == size || inADiag == size) {
					this.gameOver = true;
					break;
				} else {
					inARow = 0;
					inACol = 0;
				}
			}
			// Check for a diagonal win from bottom left to top right
			if(!gameOver) {
				if(this.board[2][0] == this.player && this.board[1][1] == this.player && this.board[0][2] == this.player) {
					this.gameOver = true;
				}
			}
			// If the game is over then return true
			// else if the game is a draw, end the game and set player to null
			// else return false as the game has not ended
			if(gameOver) {
				return true;
			} else if(isFull == (size*size)) {
				this.gameOver = true;
				this.player = null;
				return true;
			}
			return false;
		}
		// if the game has previously ended then we need to return true
		return true;		
	}
	
	@Override
	public Player getWinner() {
		if(gameOver) {
			return this.player;
		}
		return null;
	}
	
	@Override
	public Player[][] getBoard() {
		Player[][] board = new Player[size][size];
		for(int r=0; r < this.board.length; r++) {
			for(int c=0; c < this.board.length; c++) {
				board[r][c] = this.board[r][c];
			}
		}
		return board;
	}
	
	@Override
	public Player getMarkAt(int r, int c) {
		if(r < 0 || r > size || c > size || c < 0) {
			throw new IllegalArgumentException("This move is out of bounds.");
		}
		return this.board[r][c];
	}
}
