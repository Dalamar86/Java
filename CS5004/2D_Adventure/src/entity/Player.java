package entity;


import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gameobject.GameObject;
import main.*;
import object.*;

/**
 * 
 * @author Robert Wilson
 *
 */
public class Player extends GameObject{
	
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	private int hasKey = 0;
	private int standCounter = 0;
	private boolean attackCanceled = false;
	private ArrayList<GameObject> inventory = new ArrayList<>();
	private final int inventorySize = 20;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);

		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		setSolidArea(new Rectangle());
		getSolidArea().x = gp.tileSize/6;
		getSolidArea().y = gp.tileSize/3;
		setSolidAreaDefaultX(getSolidArea().x);
		setSolidAreaDefaultY(getSolidArea().y);
		getSolidArea().width = (int) (gp.tileSize/1.5);
		getSolidArea().height = (int) (gp.tileSize/1.5);
		
		setDefaultValues();
		getImage();
		getPlayerAttackImage();
		setItems();
	}
	
	//#####################################################################
	// 									Setup
	//#####################################################################
	
	public void setDefaultValues() {
		
		setWorldX(gp.tileSize * 23);
		setWorldY(gp.tileSize * 21);
		
		setType(ObjectType.PLAYER);
		setSpeed(4);
		setSpeeddiag(getSpeed() - 1);
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
		setCurrentWeapon(new OBJ_Sword_Normal(gp));
		setCurrentShield(new OBJ_Shield_Wooden(gp));
		setAttack(getAttack());
		setDefense(getDefense());
	}
	
	private void setItems() {
		getInventory().add(getCurrentWeapon());
		getInventory().add(getCurrentShield());
		getInventory().add(new OBJ_Key(gp));
		hasKey++;
		getInventory().add(new OBJ_PotionRed(gp));
		getInventory().add(new OBJ_PotionRed(gp));
		getInventory().add(new OBJ_PotionRed(gp));
		//getInventory().add(new OBJ_Shield_Blue(gp));
		getInventory().add(new OBJ_Axe(gp));
	}
	
	public int getAttack() {
		setAttackArea(getCurrentWeapon().getAttackArea());
		return attack = getStrength() * getCurrentWeapon().getAttackValue();
	}
	
	public int getDefense() { 
		return defense = getDexterity() * getCurrentShield().getDefenseValue();
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
		if(getCurrentWeapon().getName() == "sword_normal") {
			attackUp1 = setup("/player/boy_attack_up_1", 		gp.tileSize,   gp.tileSize*2);
			attackUp2 = setup("/player/boy_attack_up_2", 		gp.tileSize,   gp.tileSize*2);
			attackDown1 = setup("/player/boy_attack_down_1",	gp.tileSize,   gp.tileSize*2);
			attackDown2 = setup("/player/boy_attack_down_2", 	gp.tileSize,   gp.tileSize*2);
			attackLeft1 = setup("/player/boy_attack_left_1", 	gp.tileSize*2, gp.tileSize);
			attackLeft2 = setup("/player/boy_attack_left_2", 	gp.tileSize*2, gp.tileSize);
			attackRight1 = setup("/player/boy_attack_right_1",	gp.tileSize*2, gp.tileSize);
			attackRight2 = setup("/player/boy_attack_right_2", 	gp.tileSize*2, gp.tileSize);
		} else if(getCurrentWeapon().getName() == "Axe") {
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
	
	//#####################################################################
	// 							Update and Draw
	//#####################################################################
	
	public void update() {
		if(isDying()) {
			setAlive(false);
			gp.setGameState(GameState.DEADSTATE);
		}
		if(attacking) {
			attacking();
		} else if(keyH.isUpPressed() == true || keyH.isDownPressed() == true || keyH.isLtPressed() == true || keyH.isRtPressed() == true || keyH.isEnterPressed()) {
			
			if(keyH.isUpPressed() == true) {
				if(keyH.isLtPressed()) {
					setDirection("uplt");
				} else if (keyH.isRtPressed()) {
					setDirection("uprt");
				} else {
					setDirection("up");
				}
			} else if(keyH.isDownPressed() == true) {
				if(keyH.isLtPressed()) {
					setDirection("downlt");
				} else if (keyH.isRtPressed()) {
					setDirection("downrt");
				} else {
					setDirection("down");
				}
			} else if(keyH.isLtPressed() == true) {
				if(keyH.isUpPressed()) {
					setDirection("uplt");
				} else if (keyH.isDownPressed()) {
					setDirection("downlt");
				} else {
					setDirection("left");
				}
			} else if(keyH.isRtPressed() == true) {
				if(keyH.isUpPressed()) {
					setDirection("uprt");
				} else if (keyH.isDownPressed()) {
					setDirection("downrt");
				} else {
					setDirection("right");
				}
			}  
			if(keyH.isEnterPressed()) {
				attacking = true;
			}
			
			// Check tile collision
			setCollisionOn(false);
			gp.cChecker.checkTile(this);
			
			// Check object collision
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			// Check NPC collision
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);

			// Check monster collision
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			if(monsterIndex != 999) {
				GameObject monster = gp.monster[monsterIndex];
				takeDamage(monster.attack());
			}
			
			
			// Check events
			gp.eHandler.checkEvent();
			
			
			// if collision is False, Player can move
			if(isCollisionOn() == false) {
	
				switch(getDirection()) {
				case "up":
					setWorldY(getWorldY() - getSpeed());
					break;
				case "uplt":
					setWorldY(getWorldY() - getSpeeddiag());
					setWorldX(getWorldX() - getSpeeddiag());
					break;
				case "uprt":
					setWorldY(getWorldY() - getSpeeddiag());
					setWorldX(getWorldX() + getSpeeddiag());
					break;
				case "down":
					setWorldY(getWorldY() + getSpeed());
					break;
				case "downlt":
					setWorldY(getWorldY() + getSpeeddiag());
					setWorldX(getWorldX() - getSpeeddiag());
					break;
				case "downrt":
					setWorldY(getWorldY() + getSpeeddiag());
					setWorldX(getWorldX() + getSpeeddiag());
					break;
				case "left":
					setWorldX(getWorldX() - getSpeed());
					break;
				case "right":
					setWorldX(getWorldX() + getSpeed());
					break;
				}
				
			} else if(isCollisionOn() == true && getDirection() == "uplt") {
				setDirection("left");
				setCollisionOn(false);
				gp.cChecker.checkTile(this);
				gp.cChecker.checkObject(this, true);
				gp.cChecker.checkEntity(this, gp.npc);
				gp.cChecker.checkEntity(this, gp.monster);
				if(isCollisionOn() == false) {
					setWorldX(getWorldX() - getSpeed());
				} else {				
					setDirection("up");
					setCollisionOn(false);
					gp.cChecker.checkTile(this);
					gp.cChecker.checkObject(this, true);
					gp.cChecker.checkEntity(this, gp.npc);
					gp.cChecker.checkEntity(this, gp.monster);
					if(isCollisionOn() == false) {
						setWorldY(getWorldY() - getSpeed());
					}
				}
			} else if(isCollisionOn() == true && getDirection() == "uprt") {
				setDirection("right");
				setCollisionOn(false);
				gp.cChecker.checkTile(this);
				gp.cChecker.checkObject(this, true);
				gp.cChecker.checkEntity(this, gp.npc);
				gp.cChecker.checkEntity(this, gp.monster);
				if(isCollisionOn() == false) {
					setWorldX(getWorldX() + getSpeed());
				} else {				
					setDirection("up");
					setCollisionOn(false);
					gp.cChecker.checkTile(this);
					gp.cChecker.checkObject(this, true);
					gp.cChecker.checkEntity(this, gp.npc);
					gp.cChecker.checkEntity(this, gp.monster);
					if(isCollisionOn() == false) {
						setWorldY(getWorldY() - getSpeed());
					}
				}
			} else if(isCollisionOn() == true && getDirection() == "downlt") {
				setDirection("left");
				setCollisionOn(false);
				gp.cChecker.checkTile(this);
				gp.cChecker.checkObject(this, true);
				gp.cChecker.checkEntity(this, gp.npc);
				gp.cChecker.checkEntity(this, gp.monster);
				if(isCollisionOn() == false) {
					setWorldX(getWorldX() - getSpeed());
				} else {
					setDirection("down");
					setCollisionOn(false);
					gp.cChecker.checkTile(this);
					gp.cChecker.checkObject(this, true);
					gp.cChecker.checkEntity(this, gp.npc);
					gp.cChecker.checkEntity(this, gp.monster);
					if(isCollisionOn() == false) {
						setWorldY(getWorldY() + getSpeed());
					}
				}
			} else if(isCollisionOn() == true && getDirection() == "downrt") {
				setDirection("right");
				setCollisionOn(false);
				gp.cChecker.checkTile(this);
				gp.cChecker.checkObject(this, true);
				gp.cChecker.checkEntity(this, gp.npc);
				gp.cChecker.checkEntity(this, gp.monster);
				if(isCollisionOn() == false) {
					setWorldX(getWorldX() + getSpeed());
				} else {
					setDirection("down");
					setCollisionOn(false);
					gp.cChecker.checkTile(this);
					gp.cChecker.checkObject(this, true);
					gp.cChecker.checkEntity(this, gp.npc);
					gp.cChecker.checkEntity(this, gp.monster);
					if(isCollisionOn() == false) {
						setWorldY(getWorldY() + getSpeed());
					}
				}
			}
			
			if(keyH.isEnterPressed() && !attackCanceled) {
				gp.playSE(7);
				attacking = true;
				spriteCounter = 0;
			}
			
			attackCanceled = false;
			//gp.keyH.enterPressed = false;
			
			spriteCounter++;
			if(spriteCounter > 12) {
				if(spriteNum == 1) {
					spriteNum = 2;
				} else {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
			
		} else {
			
			standCounter++;
			if(standCounter == 20) {
				spriteNum = 1;
				standCounter = 0;
			}
			
			if(invincible) {
				invincibleCounter++;
				if(invincibleCounter > 60) {
					invincible = false;
					invincibleCounter = 0;
				}
			}		
		}
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		
		int x = tempScreenX;
		int y = tempScreenY;
		int rightOffset = gp.screenWidth - screenX;
		int bottomOffset = gp.screenHeight - screenY;
		
		if(screenX > getWorldX()) {
			x = getWorldX();
		} if(screenY > getWorldY()) {
			y = getWorldY();
		} if(rightOffset > gp.worldWidth -getWorldX()) {
			x = gp.screenWidth - (gp.worldWidth - getWorldX());
		} if(bottomOffset > gp.worldHeight - getWorldY()) {
			y = gp.screenHeight - (gp.worldHeight - getWorldY());
		}
		
		switch(getDirection()) {
		case "up":
			if(!attacking) {
				if(spriteNum == 1) {image = up1;} 
				else {image = up2;}
			} else {
				y -= gp.tileSize;
				if(spriteNum == 1) {image = attackUp1;} 
				else {image = attackUp2;}
			}
			break;
		case "downlt":
		case "uplt":
			if(!attacking) {
				if(spriteNum == 1) {image = left1;} 
				else {image = left2;}
			} else {
				x -= gp.tileSize;
				if(spriteNum == 1) {image = attackLeft1;} 
				else {image = attackLeft2;}
			}
			break;
		case "downrt":
		case "uprt":
			if(!attacking) {
				if(spriteNum == 1) {image = right1;} 
				else {image = right2;}
			} else {
				if(spriteNum == 1) {image = attackRight1;} 
				else {image = attackRight2;}
			}
			break;
		case "down":
			if(!attacking) {
				if(spriteNum == 1) {image = down1;} 
				else {image = down2;}
			} else {
				if(spriteNum == 1) {image = attackDown1;} 
				else {image = attackDown2;}
			}
			break;
		case "left":
			if(!attacking) {
				if(spriteNum == 1) {image = left1;} 
				else {image = left2;}
			} else {
				x -= gp.tileSize;
				if(spriteNum == 1) {image = attackLeft1;} 
				else {image = attackLeft2;}
			}
			break;
		case "right":
			if(!attacking) {
				if(spriteNum == 1) {image = right1;} 
				else {image = right2;}
			} else {
				if(spriteNum == 1) {image = attackRight1;} 
				else {image = attackRight2;}
			}
			break;
		}
		
		if(invincible) {
			if(invincibleCounter <= 10) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3F));
			} else if(invincibleCounter <= 20) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1F));
			} 
		}
		
		g2.drawImage(image,  x,  y, null);
		
		
		// Reset alpha
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
	}
	
	//#####################################################################
	// 								Components
	//#####################################################################
	
	public void attacking() {
		spriteCounter++;
		
		if(spriteCounter <= 5) {
			spriteNum = 1;
		} else if(spriteCounter > 5 && spriteCounter <= 25) {
			spriteNum = 2;
			
			// save current worldX, worldY, solidArea
			int currentWorldX = getWorldX();
			int currentWorldY = getWorldY();
			int solidAreaWidth = getSolidArea().width;
			int solidAreaHeight = getSolidArea().height;
			
			// Adjust player's worldX/Y for the attackArea
			switch(getDirection()) {
			case "up": setWorldY(getWorldY() - getAttackArea().height); break;
			case "down": setWorldY(getWorldY() + getAttackArea().height); break;
			case "left": setWorldX(getWorldX() - getAttackArea().width); break;
			case "right": setWorldX(getWorldX() + getAttackArea().width); break;
			}
			
			
			// Attack area becomes solid area
			getSolidArea().width = getAttackArea().width;
			getSolidArea().height = getAttackArea().height;
			
			// Check collision of sword and monster
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			if(monsterIndex != 999) {
				GameObject monster = gp.monster[monsterIndex];
				damageMonster(monster);
			}
			
			setWorldX(currentWorldX);
			setWorldY(currentWorldY);
			getSolidArea().width = solidAreaWidth;
			getSolidArea().height = solidAreaHeight;
			
			
		} else {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}
	
	public void pickUpObject(int index) {
		if(index != 999) {
			if(inventory.size() != inventorySize) {
				
				String objectName = gp.obj[index].getName();
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
					setSpeed(getSpeed() + 2);
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
	
	public void interactNPC(int index) {
		if(index != 999) {
			attackCanceled = true;
			gp.setGameState(GameState.DIALOGUESTATE);
			gp.npc[index].speak();
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
	
	@Override 
	public void damageReaction() {
		// TODO apply animation
		// TODO move character back
	}
	
	public void damageMonster(GameObject monster) {
		int exp = monster.takeDamage(attack);
		if(exp != 0 ) {
			this.exp += exp;
			checkLevelUp();
		}
	}
	
	public void checkLevelUp() {
		if(exp >= nextLevelExp ) {
			level++;
			nextLevelExp *= 2;
			maxLife += 2;
			setLife(getMaxLife());
			setStrength(getStrength() + 1);
			setDexterity(getDexterity() +1);
			attack = getAttack();
			defense = getDefense();
			gp.playSE(9);
			gp.setGameState(GameState.DIALOGUESTATE);
			gp.ui.setCurrentDialogue("You are now level " + level + "\nYou feel stronger!");
		}
	}
	
	public void equipItem() {
		int itemIndex = gp.ui.getItemIndex();
		
		if(itemIndex < inventory.size()) {
			GameObject selectedItem = inventory.get(itemIndex);
			if(selectedItem.getType() == ObjectType.WEAPON) {
				setCurrentWeapon(selectedItem);
				attack = getAttack();
				getPlayerAttackImage();
			}
			if(selectedItem.getType() == ObjectType.SHIELD) {
				setCurrentShield(selectedItem);
				defense = getDefense();
			}
			if(selectedItem.getType() == ObjectType.CONSUMABLE) {
				selectedItem.use(this);
				inventory.remove(itemIndex);
			}
		}
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
	
	//#####################################################################
	// 							Getters and Setters
	//#####################################################################
	
	public boolean isAttackCanceled() {
		return attackCanceled;
	}

	public void setAttackCanceled(boolean attackCanceled) {
		this.attackCanceled = attackCanceled;
	}

	public ArrayList<GameObject> getInventory() {
		return inventory;
	}

	public void setInventory(ArrayList<GameObject> inventory) {
		this.inventory = inventory;
	}
	
	public int getHasKey() {
		return hasKey;
	}

	public void setHasKey(int hasKey) {
		this.hasKey = hasKey;
	}
}
