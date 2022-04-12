package Main;

//Class object for towers
//Needs to contain all characteristics of the tower objects
//3-7-19
//Dr. G

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.io.IOException;

public class Tower extends MapObject{
	
	Bullet1 b1;
		
	public Tower(int posx, int posy, BufferedImage bi, int imageW, int imageH)
	{
		super(posx, posy, bi, imageW, imageH);
		b1 = new Bullet1(posx, posy, 5, 5);
	}
	
	//make the tower fire
	public void fire(Graphics g)
	{
		b1.drawImage(g);
	}

	/*public void drawImage(Graphics g)
	{
		g.drawImage(bi,posx, posy,imageW,imageH,null);
	}*/
	
}
