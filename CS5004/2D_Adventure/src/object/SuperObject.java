package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.*;

public abstract class SuperObject {

	public BufferedImage image, image2, image3;
	public String name;
	public boolean collision = false;
	protected GamePanel gp;
	public int worldX, worldY;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);  // modify later to follow tileSize instead of literal
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	UtilityTool uTool = new UtilityTool();
	
	public SuperObject(GamePanel gp, String name) {
		this.name = name;
		this.gp = gp;
		addImage(name);
	}
	
	public void addImage(String name) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/" + name + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void addImage(String name2, String name3) {
		try {
			image2 = ImageIO.read(getClass().getResourceAsStream("/objects/" + name2 + ".png"));
			image3 = ImageIO.read(getClass().getResourceAsStream("/objects/" + name3 + ".png"));
			image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
			image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void addImage(String name1, String name2, String name3) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/" + name1 + ".png"));
			image2 = ImageIO.read(getClass().getResourceAsStream("/objects/" + name2 + ".png"));
			image3 = ImageIO.read(getClass().getResourceAsStream("/objects/" + name3 + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
			image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2, GamePanel gp) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
		   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
		   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}
}
