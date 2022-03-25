package object;

import main.GamePanel;
import main.GamePanel.GameState;
import entity.*;

public class OBJ_PotionRed extends SuperObject {

	public OBJ_PotionRed(GamePanel gp) {
		super(gp, "Potion_red");
		this.setDescription("[" + name + "]\nWhat does this mysterious red liquid do?\n I guess we won't know till you try it.");
		setType(EntityType.CONSUMABLE);
		this.life = 2;
	}
	
	@Override
	public void use(Entity entity) {
		gp.gameState = GameState.DIALOGUESTATE;
		gp.ui.currentDialogue = "You drin the mysterious potion\n and gain " + life + " health!";
		entity.setLife(entity.getLife() + life);
		if(entity.getLife() > entity.getMaxLife()) {
			entity.setLife(entity.getMaxLife());
		}
		gp.playSE(2);
	}
}
