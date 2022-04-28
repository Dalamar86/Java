package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.*;
import gameobject.GameObject;

public abstract class SuperObject extends GameObject {

	
	
	//protected GamePanel gp;
	//public int worldX, worldY;
	//public Rectangle solidArea = new Rectangle(0, 0, 48, 48);  // modify later to follow tileSize instead of literal
	//public int solidAreaDefaultX = 0;
	//public int solidAreaDefaultY = 0;
	UtilityTool uTool = new UtilityTool();
	
	public SuperObject(GamePanel gp, String name) {
		super(gp);
		this.setName(name);
		addImage(name);
	}
	
	//#####################################################################
	// 								Setup
	//#####################################################################
	
	public void addImage(String name) {
		try {
			setImage1(ImageIO.read(getClass().getResourceAsStream("/objects/" + name + ".png")));
			setImage1(uTool.scaleImage(getImage1(), gp.tileSize, gp.tileSize));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void addImage(String name2, String name3) {
		try {
			setImage2(ImageIO.read(getClass().getResourceAsStream("/objects/" + name2 + ".png")));
			setImage3(ImageIO.read(getClass().getResourceAsStream("/objects/" + name3 + ".png")));
			setImage2(uTool.scaleImage(getImage2(), gp.tileSize, gp.tileSize));
			setImage3(uTool.scaleImage(getImage3(), gp.tileSize, gp.tileSize));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void addImage(String name1, String name2, String name3) {
		try {
			setImage1(ImageIO.read(getClass().getResourceAsStream("/objects/" + name1 + ".png")));
			setImage2(ImageIO.read(getClass().getResourceAsStream("/objects/" + name2 + ".png")));
			setImage3(ImageIO.read(getClass().getResourceAsStream("/objects/" + name3 + ".png")));
			setImage1(uTool.scaleImage(getImage1(), gp.tileSize, gp.tileSize));
			setImage2(uTool.scaleImage(getImage2(), gp.tileSize, gp.tileSize));
			setImage3(uTool.scaleImage(getImage3(), gp.tileSize, gp.tileSize));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
