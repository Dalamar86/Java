package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.*;
import gameobject.GameObject;

/**
 * Object gameObject parent wrapper.Holds specific methods for updating and controlling objects.
 * 
 * @author Robert Wilson
 *
 */
public abstract class SuperObject extends GameObject {
	
	/**
	 * Super constructor of object children.
	 * 
	 * @param gp (GamePanel) Current game panel
	 */
	public SuperObject(GamePanel gp, String name) {
		super(gp);
		this.setName(name);
		addImage(name);
	}
	
	//#####################################################################
	// 								Setup
	//#####################################################################
	
	/**
	 * Add an image1 to be buffered using the scaleImage method in parent gameObject. 
	 * 
	 * @param name (String) name to be added to path for image1 load
	 */
	public void addImage(String name) {
		try {
			setImage1(ImageIO.read(getClass().getResourceAsStream("/objects/" + name + ".png")));
			setImage1(scaleImage(getImage1(), gp.tileSize, gp.tileSize));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Add an image2 and image3 to be buffered using the scaleImage method in parent gameObject. 
	 * 
	 * @param name2 (String) name to be added to path for image2 load
	 * @param name3 (String) name to be added to path for image3 load
	 */
	public void addImage(String name2, String name3) {
		try {
			setImage2(ImageIO.read(getClass().getResourceAsStream("/objects/" + name2 + ".png")));
			setImage3(ImageIO.read(getClass().getResourceAsStream("/objects/" + name3 + ".png")));
			setImage2(scaleImage(getImage2(), gp.tileSize, gp.tileSize));
			setImage3(scaleImage(getImage3(), gp.tileSize, gp.tileSize));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Add an image1, image2, and image3 to be buffered using the scaleImage method in parent gameObject. 
	 * 
	 * @param name (String) name to be added to path for image1 load
	 * @param name2 (String) name to be added to path for image2 load
	 * @param name3 (String) name to be added to path for image3 load
	 */
	public void addImage(String name1, String name2, String name3) {
		try {
			setImage1(ImageIO.read(getClass().getResourceAsStream("/objects/" + name1 + ".png")));
			setImage2(ImageIO.read(getClass().getResourceAsStream("/objects/" + name2 + ".png")));
			setImage3(ImageIO.read(getClass().getResourceAsStream("/objects/" + name3 + ".png")));
			setImage1(scaleImage(getImage1(), gp.tileSize, gp.tileSize));
			setImage2(scaleImage(getImage2(), gp.tileSize, gp.tileSize));
			setImage3(scaleImage(getImage3(), gp.tileSize, gp.tileSize));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
