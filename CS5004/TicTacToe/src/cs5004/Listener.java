package cs5004;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The listener for the TicTacToe game, contains the action to perform when buttons are clicked.
 * 
 * @author Robert Wilson
 * date: 11 APR 2022
 * class: cs5004
 *
 */
public class Listener implements ActionListener {

	// Model for the game
	private TicTacToeController controller;
	// view for the game
	private TicTacToeView view;
	
	/**
	 * Set's the listeners controller and view
	 * 
	 * @param controller (TicTacToeController) controller of the game. 
	 * @param view (TicTacToeView) view of the game.
	 */
	public Listener(TicTacToeController controller, TicTacToeView view) {
		this.controller = controller;
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Perform the requested action depending on button pressed
		if(view.isReset(e)) {
			controller.reset();
		} else {
			controller.update(e);
		}
	}

}
