package models;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;
import main.ObjectType;

public class PlayerModel extends GameObjectModel {
	
	public final int screenX;
	public final int screenY;
	private int hasKey = 0;
	
	private ArrayList<GameObjectModel> inventory = new ArrayList<>();
	private final int inventorySize = 20;
	
	// Describes an image with an accessible buffer of image data.
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
		
	
	public PlayerModel(GamePanel gp) { //, KeyHandler keyH) {
		super(gp);

		//this.keyH = keyH;
		
		screenX = gp.gv.screenWidth/2 - (gp.gv.tileSize/2);
		screenY = gp.gv.screenHeight/2 - (gp.gv.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = gp.gv.tileSize/6;
		solidArea.y = gp.gv.tileSize/3;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = (int) (gp.gv.tileSize/1.5);
		solidArea.height = (int) (gp.gv.tileSize/1.5);
		
		setDefaultValues();
		getImage();
		getPlayerAttackImage();
		setItems();
	}
	
	public void setDefaultValues() {
		
		worldX = gp.gv.tileSize * 23;
		worldY = gp.gv.tileSize * 21;
		
		setType(ObjectType.PLAYER);
		speed = 4;
		speeddiag = speed - 1;
		setDirection("down");
		
		// Player status
		setLevel(1);
		setMaxLife(6);
		setLife(getMaxLife());
		setStrength(1);
		setDexterity(1);
		setExp(0);
		setNextLevelExp(5);
		setCoin(0);
		currentWeapon = new OBJ_Sword_Normal(gp);
		currentShield = new OBJ_Shield_Wooden(gp);
		setAttack(getAttack());
		setDefense(getDefense());
	}
	
	private void setItems() {
		getInventory().add(currentWeapon);
		getInventory().add(currentShield);
		getInventory().add(new OBJ_Key(gp));
		hasKey++;
		getInventory().add(new OBJ_PotionRed(gp));
		getInventory().add(new OBJ_PotionRed(gp));
		getInventory().add(new OBJ_PotionRed(gp));
		//getInventory().add(new OBJ_Shield_Blue(gp));
		getInventory().add(new OBJ_Axe(gp));
	}
	
	
	public void getImage() {		
		up1 = setup("/player/boy_up_1");
		up2 = setup("/player/boy_up_2");
		down1 = setup("/player/boy_down_1");
		down2 = setup("/player/boy_down_2");
		left1 = setup("/player/boy_left_1");
		left2 = setup("/player/boy_left_2");
		right1 = setup("/player/boy_right_1");
		right2 = setup("/player/boy_right_2");
	}
	
	public void getPlayerAttackImage() {
		if(currentWeapon.name == "sword_normal") {
			attackUp1 = setup("/player/boy_attack_up_1", 		gp.tileSize,   gp.tileSize*2);
			attackUp2 = setup("/player/boy_attack_up_2", 		gp.tileSize,   gp.tileSize*2);
			attackDown1 = setup("/player/boy_attack_down_1",	gp.tileSize,   gp.tileSize*2);
			attackDown2 = setup("/player/boy_attack_down_2", 	gp.tileSize,   gp.tileSize*2);
			attackLeft1 = setup("/player/boy_attack_left_1", 	gp.tileSize*2, gp.tileSize);
			attackLeft2 = setup("/player/boy_attack_left_2", 	gp.tileSize*2, gp.tileSize);
			attackRight1 = setup("/player/boy_attack_right_1",	gp.tileSize*2, gp.tileSize);
			attackRight2 = setup("/player/boy_attack_right_2", 	gp.tileSize*2, gp.tileSize);
		} else if(currentWeapon.name == "Axe") {
			attackUp1 = setup("/player/boy_axe_up_1", 		gp.tileSize,   gp.tileSize*2);
			attackUp2 = setup("/player/boy_axe_up_2", 		gp.tileSize,   gp.tileSize*2);
			attackDown1 = setup("/player/boy_axe_down_1",	gp.tileSize,   gp.tileSize*2);
			attackDown2 = setup("/player/boy_axe_down_2", 	gp.tileSize,   gp.tileSize*2);
			attackLeft1 = setup("/player/boy_axe_left_1", 	gp.tileSize*2, gp.tileSize);
			attackLeft2 = setup("/player/boy_axe_left_2", 	gp.tileSize*2, gp.tileSize);
			attackRight1 = setup("/player/boy_axe_right_1",	gp.tileSize*2, gp.tileSize);
			attackRight2 = setup("/player/boy_axe_right_2", 	gp.tileSize*2, gp.tileSize);
		}
	}
	
	public ArrayList<GameObject> getInventory() {
		return inventory;
	}

	public void setInventory(ArrayList<GameObject> inventory) {
		this.inventory = inventory;
	}
	
	public int getAttack() {
		attackArea = currentWeapon.attackArea;
		return attack = getStrength() * currentWeapon.attackValue;
	}
	
	public int getDefense() { 
		return defense = getDexterity() * currentShield.defenseValue;
	}
	
	public int getHasKey() {
		return hasKey;
	}

	public void setHasKey(int hasKey) {
		this.hasKey = hasKey;
	}
	
	private void useKey() {
		int i;
		for(i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).getType() == ObjectType.KEY) {
				inventory.remove(i);
				break;
			}
		}
	}
	
	public void pickUpObject(int index) {
		if(index != 999) {
			if(inventory.size() != inventorySize) {
				
				String objectName = gp.obj[index].name;
				String text = "";
				if(objectName != "Door" && objectName != "Chest") {
					inventory.add(gp.obj[index]);
				}
				
				boolean doorUnlocked = false;
				
				switch(objectName) {
				case "Key":
					gp.playSE(1);
					setHasKey(getHasKey() + 1);
					text = "You got a key!";
					break;
				case "Door":
					if(getHasKey() > 0) {
						gp.playSE(3);
						setHasKey(getHasKey() - 1);
						text = "Door Unlocked!";
						doorUnlocked = true;
						useKey();
					} else {
						text = "Door Locked!";
					}
					break;
				case "Boots":
					gp.playSE(2);
					speed += 2;
					text = "Speed boost";
					break;
				case "Chest":
					gp.ui.levelFinished = true;
					gp.stopMusic();
					gp.playSE(4);
					break;
				case "Shield_blue":
					gp.playSE(2);
					text = "Beat up blue shield";
					break;
				case "shield_wood":
					gp.playSE(2);
					text = "Bark from a nearby tree";
					break;
				case "Axe":
					gp.playSE(2);
					text = "Wood cutting axe";
					break;
				case "Sword_normal":
					gp.playSE(2);
					text = "Rusty sword";
					break;
				case "Potion_red":
					gp.playSE(2);
					text = "mysterious liquid";
					break;
				}
				if(objectName == "Door") {
					if(doorUnlocked) {
						gp.obj[index] = null;
					}
				} else {
					gp.obj[index] = null;
				}
				
				gp.ui.addMessage(text);
			}
		}
	}
	
	public void equipItem() {
		int itemIndex = gp.ui.getItemIndex();
		
		if(itemIndex < inventory.size()) {
			GameObject selectedItem = inventory.get(itemIndex);
			if(selectedItem.getType() == ObjectType.WEAPON) {
				currentWeapon = selectedItem;
				attack = getAttack();
				getPlayerAttackImage();
			}
			if(selectedItem.getType() == ObjectType.SHIELD) {
				currentShield = selectedItem;
				defense = getDefense();
			}
			if(selectedItem.getType() == ObjectType.CONSUMABLE) {
				selectedItem.use(this);
				inventory.remove(itemIndex);
			}
		}
	}
	
	@Override
	public int takeDamage(int damage) {
		if(!invincible) {
			damage -= defense;
			if(damage < 0) {
				damage = 0;
			}
			setLife(getLife() - damage);
			invincible = true;
			gp.playSE(6);
		}
		return 0;
	}
}
