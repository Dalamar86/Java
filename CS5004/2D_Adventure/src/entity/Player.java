package entity;


import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.*;
import main.GamePanel.GameState;
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
		
		solidArea = new Rectangle();
		solidArea.x = gp.tileSize/6;
		solidArea.y = gp.tileSize/3;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = (int) (gp.tileSize/1.5);
		solidArea.height = (int) (gp.tileSize/1.5);
		
		setDefaultValues();
		getImage();
		getPlayerAttackImage();
		setItems();
	}
	
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		
		setType(ObjectType.PLAYER);
		speed = 4;
		speeddiag = speed - 1;
		direction = "down";
		
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
	
	public int getAttack() {
		attackArea = currentWeapon.attackArea;
		return attack = getStrength() * currentWeapon.attackValue;
	}
	
	public int getDefense() { 
		return defense = getDexterity() * currentShield.defenseValue;
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
	
	public void update() {
		if(!isAlive()) {
			gp.gameState = GameState.DEADSTATE;
		}
		if(attacking) {
			attacking();
		} else if(keyH.upPressed == true || keyH.downPressed == true || keyH.ltPressed == true || keyH.rtPressed == true || keyH.enterPressed) {
			
			if(keyH.upPressed == true) {
				if(keyH.ltPressed) {
					direction = "uplt";
				} else if (keyH.rtPressed) {
					direction = "uprt";
				} else {
					direction = "up";
				}
			} else if(keyH.downPressed == true) {
				if(keyH.ltPressed) {
					direction = "downlt";
				} else if (keyH.rtPressed) {
					direction = "downrt";
				} else {
					direction = "down";
				}
			} else if(keyH.ltPressed == true) {
				if(keyH.upPressed) {
					direction = "uplt";
				} else if (keyH.downPressed) {
					direction = "downlt";
				} else {
					direction = "left";
				}
			} else if(keyH.rtPressed == true) {
				if(keyH.upPressed) {
					direction = "uprt";
				} else if (keyH.downPressed) {
					direction = "downrt";
				} else {
					direction = "right";
				}
			}  
			if(keyH.enterPressed) {
				attacking = true;
			}
			
			// Check tile collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			// Check object collision
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			// Check NPC collision
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);

			// Check monster collision
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);
			
			// Check events
			gp.eHandler.checkEvent();
			
			
			// if collision is False, Player can move
			if(collisionOn == false) {
	
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "uplt":
					worldY -= speeddiag;
					worldX -= speeddiag;
					break;
				case "uprt":
					worldY -= speeddiag;
					worldX += speeddiag;
					break;
				case "down":
					worldY += speed;
					break;
				case "downlt":
					worldY += speeddiag;
					worldX -= speeddiag;
					break;
				case "downrt":
					worldY += speeddiag;
					worldX += speeddiag;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
				
			} else if(collisionOn == true && direction == "uplt") {
				direction = "left";
				collisionOn = false;
				gp.cChecker.checkTile(this);
				gp.cChecker.checkObject(this, true);
				gp.cChecker.checkEntity(this, gp.npc);
				gp.cChecker.checkEntity(this, gp.monster);
				if(collisionOn == false) {
					worldX -= speed;
				} else {				
					direction = "up";
					collisionOn = false;
					gp.cChecker.checkTile(this);
					gp.cChecker.checkObject(this, true);
					gp.cChecker.checkEntity(this, gp.npc);
					gp.cChecker.checkEntity(this, gp.monster);
					if(collisionOn == false) {
						worldY -= speed;
					}
				}
			} else if(collisionOn == true && direction == "uprt") {
				direction = "right";
				collisionOn = false;
				gp.cChecker.checkTile(this);
				gp.cChecker.checkObject(this, true);
				gp.cChecker.checkEntity(this, gp.npc);
				gp.cChecker.checkEntity(this, gp.monster);
				if(collisionOn == false) {
					worldX += speed;
				} else {				
					direction = "up";
					collisionOn = false;
					gp.cChecker.checkTile(this);
					gp.cChecker.checkObject(this, true);
					gp.cChecker.checkEntity(this, gp.npc);
					gp.cChecker.checkEntity(this, gp.monster);
					if(collisionOn == false) {
						worldY -= speed;
					}
				}
			} else if(collisionOn == true && direction == "downlt") {
				direction = "left";
				collisionOn = false;
				gp.cChecker.checkTile(this);
				gp.cChecker.checkObject(this, true);
				gp.cChecker.checkEntity(this, gp.npc);
				gp.cChecker.checkEntity(this, gp.monster);
				if(collisionOn == false) {
					worldX -= speed;
				} else {
					direction = "down";
					collisionOn = false;
					gp.cChecker.checkTile(this);
					gp.cChecker.checkObject(this, true);
					gp.cChecker.checkEntity(this, gp.npc);
					gp.cChecker.checkEntity(this, gp.monster);
					if(collisionOn == false) {
						worldY += speed;
					}
				}
			} else if(collisionOn == true && direction == "downrt") {
				direction = "right";
				collisionOn = false;
				gp.cChecker.checkTile(this);
				gp.cChecker.checkObject(this, true);
				gp.cChecker.checkEntity(this, gp.npc);
				gp.cChecker.checkEntity(this, gp.monster);
				if(collisionOn == false) {
					worldX += speed;
				} else {
					direction = "down";
					collisionOn = false;
					gp.cChecker.checkTile(this);
					gp.cChecker.checkObject(this, true);
					gp.cChecker.checkEntity(this, gp.npc);
					gp.cChecker.checkEntity(this, gp.monster);
					if(collisionOn == false) {
						worldY += speed;
					}
				}
			}
			
			if(keyH.enterPressed && !attackCanceled) {
				gp.playSE(7);
				attacking = true;
				spriteCounter = 0;
			}
			
			attackCanceled = false;
			gp.keyH.enterPressed = false;
			
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
	
	public void attacking() {
		spriteCounter++;
		
		if(spriteCounter <= 5) {
			spriteNum = 1;
		} else if(spriteCounter > 5 && spriteCounter <= 25) {
			spriteNum = 2;
			
			// save current worldX, worldY, solidArea
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			// Adjust player's worldX/Y for the attackArea
			switch(direction) {
			case "up": worldY -= attackArea.height; break;
			case "down": worldY += attackArea.height; break;
			case "left": worldX -= attackArea.width; break;
			case "right": worldX += attackArea.width; break;
			}
			
			
			// Attack area becomes solid area
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			
			// Check collision of sword and monster
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
			damageMonster(monsterIndex);
			
		} else {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
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
	
	public void interactNPC(int index) {
		if(index != 999) {
			attackCanceled = true;
			gp.gameState = GameState.DIALOGUESTATE;
			gp.npc[index].speak();
		}
	}
	
	public void contactMonster(int index) {
		if(index != 999) {
			if(!invincible ) {
				int damage = gp.monster[index].attack - defense;
				if(damage < 0) {
					damage = 0;
				}
				setLife(getLife() - damage);
				invincible = true;
				gp.playSE(6);
			}
		}
	}
	
	public void damageMonster(int index) {
		if(index != 999) {
			if(!gp.monster[index].invincible) {
				int damage = attack - gp.monster[index].defense;
				if(damage < 0) {
					damage = 0;
				}
				
				gp.monster[index].life -= damage;
				gp.ui.addMessage(damage + " damage!");
				
				gp.monster[index].invincible = true;
				gp.monster[index].damageReaction();
				
				if(gp.monster[index].life <= 0) {
					gp.monster[index].setDying(true);
					gp.ui.addMessage("Killed the " + gp.monster[index].name + "!");
					exp += gp.monster[index].exp;
					checkLevelUp();
					gp.playSE(8);
				} else {
					gp.playSE(5);
				}
			}
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
			gp.gameState = GameState.DIALOGUESTATE;
			gp.ui.setCurrentDialogue("You are now level " + level + "\nYou feel stronger!");
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
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		
		int x = tempScreenX;
		int y = tempScreenY;
		int rightOffset = gp.screenWidth - screenX;
		int bottomOffset = gp.screenHeight - screenY;
		
		if(screenX > worldX) {
			x = worldX;
		} if(screenY > worldY) {
			y = worldY;
		} if(rightOffset > gp.worldWidth -worldX) {
			x = gp.screenWidth - (gp.worldWidth - worldX);
		} if(bottomOffset > gp.worldHeight - worldY) {
			y = gp.screenHeight - (gp.worldHeight - worldY);
		}
		
		switch(direction) {
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

	private void useKey() {
		int i;
		for(i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).getType() == ObjectType.KEY) {
				inventory.remove(i);
				break;
			}
		}
	}
	
	public int getHasKey() {
		return hasKey;
	}

	public void setHasKey(int hasKey) {
		this.hasKey = hasKey;
	}
}
