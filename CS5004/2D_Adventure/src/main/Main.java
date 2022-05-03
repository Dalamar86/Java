package main;

import javax.swing.JFrame;

/**
 * The driver that creates the window for the game and the instance of the GamePanel
 * 
 * @author Robert Wilson
 *
 */
public class Main {

	/**
	 * Main method starts the game.
	 * 
	 * @param args (String) list of striong arguments denoted at runtime in the command prompt
	 */
	public static void main(String[] args) {
		// Create the window for the game
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("2D RPG");
		
		// Create an instance of the gamePanel
		GamePanel gamePanel = new GamePanel(window);
		window.add(gamePanel);
		
		// refresh with gamePanel added and make everything fit nicely in the window
		window.pack(); 
		
		// center the window and make it visible
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		// run the games setup methods
		gamePanel.setupGame();
		gamePanel.startGameThread();
	}	
}
