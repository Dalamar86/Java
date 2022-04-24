package object;

import main.GamePanel;
import main.GameState;
import main.ObjectType;
import entity.*;

public class OBJ_PotionRed extends SuperObject {

	public OBJ_PotionRed(GamePanel gp) {
		super(gp, "Potion_red");
		this.setDescription("[" + name + "]\nWhat does this mysterious red liquid do?\n I guess we won't know till you try it.");
		setType(ObjectType.CONSUMABLE);
		this.life = 2;
	}
	
	@Override
	public void use(GameObject gameObject) {
		gp.gameState = GameState.DIALOGUESTATE;
		gp.ui.setCurrentDialogue("You drin the mysterious potion\n and gain " + life + " health!");
		gameObject.setLife(gameObject.getLife() + life);
		gp.playSE(2);
	}
}
