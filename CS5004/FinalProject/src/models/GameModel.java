package models;

import main.Area;
import main.GamePanel;
import main.GameState;

public class GameModel {

	private GamePanel gp;
	private Area area;
	
	public GameModel(GamePanel gp) {
		this.gp = gp;
		this.area = Area.MAIN;
	}

	

}
