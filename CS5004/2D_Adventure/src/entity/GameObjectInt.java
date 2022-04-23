package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public interface GameObjectInt {

	
	public void draw(Graphics2D g2);
	public void update();
	public BufferedImage setup(String imagePath);
	public void speak();
}
