package cs5004;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Controller for the Tic Tac Toe game.  Communicates between the model, the view, and the ActionEvent Listener.
 * 
 * @author Robert Wilson
 * date: 05 APR 2022
 * class: CS5004 
 * 
 * updated 11 APR 2022
 * -added controller functionality
 * 	-controller
 * 	-reset
 * 	-update
 */

public class TicTacToeController {

	// Model for the game
	private TicTacToeModel model;
	// view for the game
	private TicTacToeView view;
	
	/**
	 * Controller creates an instance of the model, the view, and adds actionListener
	 */
	public TicTacToeController() {
		this.model = new TicTacToeModel();
		this.view = new TicTacToeView();
		this.view.setActionListener(this);
	}
	
	/**
	 * Resets the view and makes a new model to start the game from scratch
	 * 
	 * @param e (ActionEvent) the event notification from the button pressed
	 */
	public void reset(ActionEvent e) {
		model = new TicTacToeModel();
		view.resetButton(e);
		view.setActionListener(this);
	}
	
	/**
	 * Receives the location of the button pressed and gives it to the model, then tells the view to update itself with the given information from the model.  
	 * If the game ends because of the move, update the view.  If the game is over there is no updating to do.  
	 * 
	 * @param e (ActionEvent) the event notification from the button pressed
	 */
	public void update(ActionEvent e) {
		if(!model.isGameOver()) {
			ArrayList<Integer> playerMove = view.move(e);
			model.move(playerMove.get(0), playerMove.get(1));
			view.update(playerMove, model.getMarkAt(playerMove.get(0), playerMove.get(1)));
			if(model.isGameOver()) {
				view.setGameState(GameState.VICTORYSCREEN, model.getTurn());
			}
		}
	}
}
