package object;

import enums.GameState;
import enums.ObjectType;
import gameobject.GameObject;
import main.GamePanel;

/**
 * Blue potion replenishes player mana. Single use item
 * 
 * @author Robert Wilson
 *
 */
public class OBJ_PotionBlue extends SuperObject {
	
	/**
	 * Creates an instance of the blue potion object.
	 * 
	 * @param gp (GamePanel) Current game panel
	 */
	public OBJ_PotionBlue(GamePanel gp) {
		super(gp, "Potion_blue");
		this.setDescription("[" + getName() + "]\nWhat does this mysterious blue liquid do?\n I guess we won't know till you try it.");
		setType(ObjectType.CONSUMABLE);
		setMana(2);
	}
	
	//#####################################################################
	// 								Components
	//#####################################################################
	
	@Override
	public void use(GameObject gameObject) {
		gp.setGameState(GameState.DIALOGUESTATE);
		gp.ui.setCurrentDialogue("You drin the mysterious potion\n and gain " + getMana() + " mana!");
		gameObject.setMana(gameObject.getMana() + getMana());
		gp.playSE(2);
	}
}
