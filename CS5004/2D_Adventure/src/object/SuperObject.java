package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.*;
import entity.*;

public abstract class SuperObject extends GameObject {

	
	
	//protected GamePanel gp;
	//public int worldX, worldY;
	//public Rectangle solidArea = new Rectangle(0, 0, 48, 48);  // modify later to follow tileSize instead of literal
	//public int solidAreaDefaultX = 0;
	//public int solidAreaDefaultY = 0;
	UtilityTool uTool = new UtilityTool();
	
	public SuperObject(GamePanel gp, String name) {
		super(gp);
		this.name = name;
		//this.gp = gp;
		addImage(name);
	}
	
	public void addImage(String name) {
		try {
			image1 = ImageIO.read(getClass().getResourceAsStream("/objects/" + name + ".png"));
			image1 = uTool.scaleImage(image1, gp.tileSize, gp.tileSize);
			
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
			image1 = ImageIO.read(getClass().getResourceAsStream("/objects/" + name1 + ".png"));
			image2 = ImageIO.read(getClass().getResourceAsStream("/objects/" + name2 + ".png"));
			image3 = ImageIO.read(getClass().getResourceAsStream("/objects/" + name3 + ".png"));
			image1 = uTool.scaleImage(image1, gp.tileSize, gp.tileSize);
			image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
			image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
