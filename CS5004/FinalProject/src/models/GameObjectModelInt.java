package models;

import java.awt.image.BufferedImage;

public interface GameObjectModelInt {
	
	public BufferedImage setup(String imagePath);
	public void speak();
	public void setAction();
	public void damageReaction();
	public int takeDamage(int damage);
	public int attack();
	public void use(GameObjectModel gameObject);
}
