package Main;

//Simple black oval bullet with a set velocity
//3-7-19
//Dr. G

import java.awt.Graphics;
import java.awt.Color;

public class Bullet1 
{
	private int vx;
	private int vy;
	private int pos_x;
	private int pos_y;

	public Bullet1(int pos_x, int pos_y, int vx, int vy)
	{
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.vx=vx;
		this.vy=vy;
	}
			
	//renders our object to the screen
	public void drawImage(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillOval(pos_x, pos_y, 20,20);
		pos_x+=vx;
		pos_y+=vy;
	}
	
	//If we are going to be able to have enemies take damage, we need a way to know the bullet position
	public int getXpos()
	{return pos_x+vx;}
	
	public int getYpos()
	{return pos_y+vy;}
	
	
}