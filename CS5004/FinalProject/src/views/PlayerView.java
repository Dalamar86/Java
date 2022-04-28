package views;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import controllers.PlayerController;
import main.GamePanel;

public class PlayerView extends GameObjectView {
	
	PlayerController playerController;
	
	public PlayerView(GamePanel gp, PlayerController playerController) {
		super(gp);
		
		this.playerController = playerController;
	}
	
	public void draw(Graphics2D g2, PlayerModel pm) {
		
		BufferedImage image = null;
		int tempScreenX = pm.screenX;
		int tempScreenY = pm.screenY;
		
		int x = tempScreenX;
		int y = tempScreenY;
		int rightOffset = gp.screenWidth - pm.screenX;
		int bottomOffset = gp.screenHeight - pm.screenY;
		
		if(pm.screenX > worldX) {
			x = worldX;
		} if(pm.screenY > worldY) {
			y = worldY;
		} if(rightOffset > gp.worldWidth -worldX) {
			x = gp.screenWidth - (gp.worldWidth - worldX);
		} if(bottomOffset > gp.worldHeight - worldY) {
			y = gp.screenHeight - (gp.worldHeight - worldY);
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
}
