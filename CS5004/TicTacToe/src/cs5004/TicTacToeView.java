package cs5004;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The GUI of the Tic Tac Toe game.  Creates a JFrame, with sub JPanels to hold the various game objects
 * 
 * @author Robert Wilson
 * date: 05 APR 2022
 * class: CS5004 
 * 
 * updated: 11 APR 2022
 * -add complete functionality
 */

public class TicTacToeView extends JPanel {
	
	// Serial Version UID
	private static final long serialVersionUID = 1L;
	
	// JFrame and window components
	private JFrame window;
	private JButton[][] board;
	private JLabel importText;
	private JButton reset;
	private JPanel gamePanel;
	private JPanel messages;
	private JPanel options;
	
	// Window settings
	private final int resolution = 48;
	private int screenWidth = resolution * 10;
	private int screenHeight = resolution * 10;
	
	// Other important fields
	private GameState gameState;
	private Listener listener;
	
	/**
	 * Constructor creates new instances of JFrame and its subcomponents, finally calling the titleScreen setup method.
	 */
	public TicTacToeView(TicTacToeController controller) {
		// JFrame initialization
		this.window = new JFrame("Tic Tac Toe");
		
		// JPanel initializations
		this.gamePanel = new JPanel(new FlowLayout());
		this.messages = new JPanel(new FlowLayout());
		this.options = new JPanel(new FlowLayout());
		
		// JButton initializations
		this.board = new JButton[3][3];
		this.reset = new JButton("New Game");
		
		// JLabel and ActionListener initialization
		this.importText = new JLabel();
		this.listener = new Listener(controller, this);
		
		// Set the gameState before calling setActionListener
		this.gameState = GameState.TITLESCREEN;
		
		// Call setup methods
		setActionListener();
		titleScreen();
	}
	
	/**
	 * Sets the action controller for the view
	 * 
	 * @param controller (TicTacToeController) this games controller
	 */
	void setActionListener() {
		switch(gameState) {
		case TITLESCREEN:
			reset.addActionListener(listener);
			break;
		case GAMESCREEN: 
			for(int row = 0; row<3 ;row++) {
		        for(int col = 0; col<3 ;col++) {
		        		board[row][col].addActionListener(listener);
		        }
			}
			break;
		case VICTORYSCREEN:
			break;
		default:
			break;
		}
	}
	
	/**
	 * Creation method for the title screen, initializing window settings 
	 * and then drawing the title screen components.
	 */
	private void titleScreen() {
		// Window settings
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(new Dimension(screenWidth, screenHeight));
		window.setBackground(Color.black);
		window.setResizable(true);
		window.setFocusable(true);
		
		// Title Message
		messages.setBackground(Color.white);
		Font arial_80B = new Font("Arial", Font.BOLD, 80);
		importText.setFont(arial_80B);
		importText.setText("Tic Tac Toe");
		importText.setSize(new Dimension(resolution*2, resolution*2));
		messages.add(importText);
		gamePanel.add(messages, BorderLayout.CENTER);
		
		// Add option to start game.
		reset.setBackground(Color.cyan);
	    options.add(reset);
		
	    // Add each component to the window in their respective positions.
	    window.getContentPane().add(gamePanel, BorderLayout.NORTH);
	    window.getContentPane().add(options, BorderLayout.CENTER);
	    
		// Set the window to the center of the screen, pack it tight, and make it visible.
		window.setLocationRelativeTo(null);
		window.pack();
		window.setVisible(true);
	}
	
	/**
	 * Creates the play state of the game by first removing all title related components 
	 * and then loading in the play state components which includes the board, reset button, 
	 * and turn indicator.
	 */
	private void setup() {
		// Remove all title screen components and validates to make sure it can still be used.
		gamePanel.removeAll();
		gamePanel.validate();
		
		// The game itself, a grid of n x n tiles.
		JPanel game = new JPanel(new GridLayout(3,3));
		for(int row = 0; row<3 ;row++) {
	        for(int col = 0; col<3 ;col++) {
	            board[row][col] = new JButton();
	            board[row][col].setPreferredSize(new Dimension(resolution*2, resolution*2));
	            board[row][col].setText("");
	            game.add(board[row][col]);
		    }
		}
		
		// Center this game matrix on the gamePanel.
	    gamePanel.add(game, BorderLayout.AFTER_LAST_LINE);
	    
	    // Options panel holds all options a user can take.
	    reset.setBackground(Color.cyan);
	    options.add(reset);
	    
	    // Messages is the turn indicator and relays important messages about the game state.
	    messages.setBackground(Color.white);
	    messages.add(importText);
	    importText.setFont(importText.getFont().deriveFont(Font.BOLD, 20F));
	    importText.setText("Player 1 to play '" + Player.X + "'");
	    
	    // Add each component to the window in their respective positions.
	    window.getContentPane().add(gamePanel, BorderLayout.NORTH);
	    window.getContentPane().add(options, BorderLayout.CENTER);
	    window.getContentPane().add(messages, BorderLayout.SOUTH);
	    window.pack();
	    
	    // change the gameState to play state.
	    gameState = GameState.GAMESCREEN;
	}
	
	/**
	 * Changes the turn indicator to display the player which has won the game.  
	 * Text is set to 'Cat's Game' if there is a tie. 
	 * 
	 * @param player (Player) the player which has won, null if game is a tie.
	 */
	private void victoryScreen(Player player) {
		// Checks if there is a player who has one.  If not, then the game was a tie.
		if(player != null) {
			importText.setText("Player '" + player + "' won!!");
		} else {
			importText.setText("Cat's Game!");
		}
	}
	
	/**
	 * Resets the current game board displayed 
	 */
	private void setupNewGame() {
		for(int row = 0; row<3 ;row++) {
	        for(int col = 0; col<3 ;col++) {
	            board[row][col].setText("");
	            board[row][col].setEnabled(true);
		    }
		}
		importText.setText("Player 1 to play '" + Player.X + "'");
	}
	
	/**
	 * Changes the action taken by the reset button. If on the title screen button should 
	 * call the play state setup method and update the Actionlistener to listen to board buttons.
	 * If on game screen then reset the board text displayed and enable buttons. If victory screen
	 * then perform same action as play screen.  
	 */
	void resetButton() {
		switch(gameState) {
		case TITLESCREEN: 
			setup();
			setActionListener();
			break;
		case GAMESCREEN: 
			setupNewGame();
			break;
		case VICTORYSCREEN: 
			setupNewGame();
			break;
		}
	}

	/**
	 * Checks to see if the reset button was pressed last.
	 * 
	 * @param e (ActionEvent) button pressed.
	 * @return boolean of whether reset button was pressed.
	 */
	public boolean isReset(ActionEvent e) {
		if(e.getSource() == reset) {
			return true;
		}  return false;
	}
	
	/**
	 * Grabs the source of the button to send onto model as a two element arrayList.
	 * 
	 * @param e (ActionEvent) button pressed.
	 * @return ArrayList<Integer> a two element arrayList with 
	 * 			the row and column of the pressed button.
	 */
	ArrayList<Integer> move(ActionEvent e) {
		ArrayList<Integer> playerMove = new ArrayList<Integer>(); 
		for(int row = 0; row<3 ;row++) {
	        for(int col = 0; col<3 ;col++) {
	            if(e.getSource() == board[row][col]) {
	            	playerMove.add(row);
	            	playerMove.add(col);
	            }
		    }
		}
		return playerMove;
	}
	
	/**
	 * Updates the displayed game board from the model and prints the appropriate player turn.
	 * 
	 * @param playerMove (ArrayList<Integer>) the two element arrayList of the players last move.
	 * @param player (Player) the last player to make a move.
	 */
	public void update(ArrayList<Integer> playerMove, Player player) {
		board[playerMove.get(0)][playerMove.get(1)].setFont(new Font("Arial", Font.BOLD, 80));
		board[playerMove.get(0)][playerMove.get(1)].setText(player.toString());
		board[playerMove.get(0)][playerMove.get(1)].setEnabled(false);
		switch(player) {
		case X:
			player = Player.O;
			importText.setText("Player 2 to play '" + player + "'");
			break;
		case O:
			player = Player.X;
			importText.setText("Player 1 to play '" + player + "'");
			break;
		}
	}
	
	/**
	 * Sets the current gameState to parameter gameState. If victory state, then call victoryScreen method.
	 * 
	 * @param gameState (GameState) the gameState to switch to.
	 * @param player (Player) the current player whose turn it is.
	 */
	void setGameState(GameState gameState, Player player) {
		this.gameState = gameState;
		switch(gameState) {
		case VICTORYSCREEN:
			victoryScreen(player);
			break;
		default:
			break;
		}
	}
}
