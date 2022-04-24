package monster;

import entity.GameObject;
import main.GamePanel;

public class SuperMonster extends GameObject {

	public SuperMonster(GamePanel gp) {
		super(gp);
	}
	
	@Override
	public int takeDamage(int damage) {
		if(!invincible) {
			damage -= defense;
			if(damage < 0) {
				damage = 0;
			}
			
			setLife(getLife() - damage);
			gp.ui.addMessage(damage + " damage!");
			
			invincible = true;
			damageReaction();
			
			if(life <= 0) {
				gp.ui.addMessage("Killed the " + name + "!");
				gp.playSE(8);
				return exp;
			} else {
				gp.playSE(5);
			}
		}
		return 0;
	}
	
	public int attack() {
		return attack;
	}
}
