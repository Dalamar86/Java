package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font arial_40, arial_80B, arial, baskerville;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean levelFinished = false;
	public String currentDialogue = "";
	
	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
		
		try {
			InputStream is = getClass().getResourceAsStream("/font/BASKVILL.TTF");
			baskerville = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/ARIAL.TTF");
			arial = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		switch(gp.gameState) {
		case PLAYSTATE:
			break;
		case PAUSESTATE:
			drawPauseScreen();
			break;
		case DIALOGUESTATE:
			drawDialogueScreen();
			break;
		}
	}
	
	public void drawPauseScreen() {
		
		g2.setFont(arial_80B);
		String text = "Paused";
		int x, y;
		x = getXforCenteredText(text);
		y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	
	public void drawDialogueScreen() {
		// Make a dialogue window
		int x = gp.tileSize*2, y = gp.tileSize/2;
		int width = gp.screenWidth - (gp.tileSize*4), height = gp.tileSize*4;
		
		drawSubWindow(x, y, width, height);
		
		g2.setFont(baskerville);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
		x += gp.tileSize; y += gp.tileSize;
		
		for(String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}
		
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		Color c = new Color(0, 0, 0, 200);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	
	public int getXforCenteredText(String text) {
		return gp.screenWidth/2 - (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth()/2;
	}
}
