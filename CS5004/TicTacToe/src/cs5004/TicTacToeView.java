package cs5004;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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
	
	private Listener listener;
	private JFrame window;
	private JButton[][] board;
	private final int resolution = 48;
	private int screenWidth;
	private int screenHeight;
	private JTextArea importText;
	private JButton reset;
	private JPanel gamePanel;
	private JPanel messages;
	private JPanel options;
	private GameState gameState;
	
	public TicTacToeView() {
		this.window = new JFrame("Tic Tac Toe");
		this.board = new JButton[3][3];
		this.screenWidth = resolution * 10;
		this.screenHeight = resolution * 10;
		this.reset = new JButton("New Game");
		this.importText = new JTextArea();
		this.gamePanel = new JPanel(new FlowLayout());
		this.messages = new JPanel(new FlowLayout());
		this.options = new JPanel(new FlowLayout());
		this.gameState = GameState.TITLESCREEN;
		titleScreen();
	}
	
	void setActionListener(TicTacToeController controller) {
		switch(gameState) {
		case TITLESCREEN: 
			this.listener = new Listener(controller, this);
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
		}
		
	    
	}
	
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
		
		
		// add option to start game
		reset.setBackground(Color.cyan);
	    options.add(reset);
		
	    
	    // Add each component to the window in their respective positions
	    window.getContentPane().add(gamePanel, BorderLayout.NORTH);
	    window.getContentPane().add(options, BorderLayout.CENTER);
	    
		// Set the window to the center of the screen and make it visible
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
	
	private void setup() {
		gamePanel.removeAll();
		gamePanel.validate();
		
		
		// The game itself, a grid of n x n tiles
		JPanel game = new JPanel(new GridLayout(3,3));
		for(int row = 0; row<3 ;row++) {
	        for(int col = 0; col<3 ;col++) {
	            board[row][col] = new JButton();
	            board[row][col].setPreferredSize(new Dimension(resolution*2, resolution*2));
	            board[row][col].setText("");
	            game.add(board[row][col]);
		    }
		}
		
		// Center this game matrix on the gamePanel
	    gamePanel.add(game, BorderLayout.AFTER_LAST_LINE);
	    
	    // Options panel holds all options a user can take
	    reset.setBackground(Color.cyan);
	    options.add(reset);
	    
	    // Messages is the turn indicator and relays important messages about the game state.
	    messages.setBackground(Color.white);
	    messages.add(importText);
	    importText.setFont(importText.getFont().deriveFont(Font.BOLD, 20F));
	    importText.setText("Player 1 to play '" + Player.X + "'");
	    
	    // Add each component to the window in their respective positions
	    
	    window.getContentPane().add(gamePanel, BorderLayout.NORTH);
	    window.getContentPane().add(options, BorderLayout.CENTER);
	    window.getContentPane().add(messages, BorderLayout.SOUTH);
	    window.pack();
	    
	    this.gameState = GameState.GAMESCREEN;
	}
	
	private void victoryScreen(Player player) {
		if(player != null) {
			importText.setText("Player: '" + player + "' won!!");
		} else {
			importText.setText("Cat's Game!");
		}
		
	}
	
	private void setupNewGame() {
		for(int row = 0; row<3 ;row++) {
	        for(int col = 0; col<3 ;col++) {
	            board[row][col].setText("");
	            board[row][col].setEnabled(true);
		    }
		}
		importText.setText("Player 1 to play '" + Player.X + "'");
	}
	
	void resetButton(ActionEvent e) {
		switch(gameState) {
		case TITLESCREEN: 
			setup();
			break;
		case GAMESCREEN: 
			setupNewGame();
			break;
		case VICTORYSCREEN: 
			setupNewGame();
			break;
		}
	}

	public boolean isReset(ActionEvent e) {
		if(e.getSource() == reset) {
			return true;
		}  return false;
	}
	
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
	
	public void update(ArrayList<Integer> playerMove, Player player) {
		board[playerMove.get(0)][playerMove.get(1)].setText(player.toString());
		board[playerMove.get(0)][playerMove.get(1)].setEnabled(false);
		switch(player) {
		case X:
			player = Player.O;
			break;
		case O:
			player = Player.X;
			break;
		}
		importText.setText("Player 1 to play '" + player + "'");
	}
	
	void setGameState(GameState gameState, Player player) {
		this.gameState = gameState;
		switch(gameState) {
		case VICTORYSCREEN:
			victoryScreen(player);
			break;
		}
	}
}
