package object;

import main.GamePanel;
import main.GameState;
import main.ObjectType;
import gameobject.GameObject;

public class OBJ_PotionRed extends SuperObject {

	public OBJ_PotionRed(GamePanel gp) {
		super(gp, "Potion_red");
		this.setDescription("[" + getName() + "]\nWhat does this mysterious red liquid do?\n I guess we won't know till you try it.");
		setType(ObjectType.CONSUMABLE);
		this.life = 2;
	}
	
	//#####################################################################
	// 								Components
	//#####################################################################
	
	@Override
	public void use(GameObject gameObject) {
		gp.setGameState(GameState.DIALOGUESTATE);
		gp.ui.setCurrentDialogue("You drin the mysterious potion\n and gain " + life + " health!");
		gameObject.setLife(gameObject.getLife() + life);
		gp.playSE(2);
	}
}
