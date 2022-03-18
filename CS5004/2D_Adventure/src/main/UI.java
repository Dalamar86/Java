package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import object.*;
import entity.*;

public class UI {

	// Setup
	GamePanel gp;
	Graphics2D g2;
	Font arial_40, arial_80B, arial, baskerville;
	
	// Player overlay
	BufferedImage heart_full, heart_half, heart_blank; //, sword, shield;
	
	// Pop ups
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	
	// Dialogue text
	public String currentDialogue = "";
	private String text;
	
	// Title screen
	int commandNum = 0;
	
	// End game
	public boolean levelFinished = false;
	int titleScreenState = 0;
	
	
	
	public UI(GamePanel gp) {
		this.gp = gp;
		this.text = "";
		
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
		
		// Create overlay objects
		Entity heart = new OBJ_Heart(gp);
		heart_full = heart.image1;
		heart_half = heart.image2;
		heart_blank = heart.image3;
		
		/*
		// Create character screen items
		Entity char_sword = gp.player.currentWeapon;
		sword = char_sword.image1;
		Entity char_shield = gp.player.currentShield;
		shield= char_shield.image1;
		*/
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
			drawHUD();
			break;
		case PAUSESTATE:
			drawPauseScreen();
			break;
		case DIALOGUESTATE:
			drawDialogueScreen();
			break;
		case TITLESTATE:
			drawTitleScreen();
			break;
		case CHARACTERSTATE:
			g2.setFont(baskerville);
			g2.setColor(Color.white);
			drawHUD();
			drawCharacterScreen();
			break;
		default:
			break;
		}
	}
	
	public void drawHUD() {
		
		// Empty hearts
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		
		while(i < gp.player.getMaxLife()/2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}
		
		// Full hearts
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		i = 0;
		
		while(i < gp.player.getLife()) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if(i < gp.player.getLife() ) {
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x += gp.tileSize;
		}
	}
	
	public void drawTitleScreen() {
		if(titleScreenState == 0) {
			g2.setColor(new Color(25, 25, 185));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 68F));
			text = "Adventures of LowRule";
			int x = getXforCenteredText(text), y = gp.tileSize*3;
			
			// Shadow
			g2.setColor(Color.gray);
			g2.drawString(text, x+5, y+5);
			
			// Main Colour
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			// Display Character
			x = gp.screenWidth/2 - (gp.tileSize*2)/2;
			y += gp.tileSize*2;
			g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
			
			// Menu
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));
			text = "New Game";
			x = getXforCenteredText(text); y += gp.tileSize*4;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Load Game";
			x = getXforCenteredText(text); y += gp.tileSize*1;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Quit";
			x = getXforCenteredText(text); y += gp.tileSize*1;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x-gp.tileSize, y);
			}
		} else if (titleScreenState == 1) {
			// Class selection screen
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(42F));
			
			text = "Select your class!";
			int x = getXforCenteredText(text);
			int y = gp.tileSize*3;
			g2.drawString(text,  x,  y);
			
			text = "Fighter";
			x = getXforCenteredText(text);
			y += gp.tileSize*3;
			g2.drawString(text,  x,  y);
			if(commandNum == 0) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Sorcerer";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text,  x,  y);
			if(commandNum == 1) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Rogue";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text,  x,  y);
			if(commandNum == 2) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Back";
			x = getXforCenteredText(text);
			y += gp.tileSize*2;
			g2.drawString(text,  x,  y);
			if(commandNum == 3) {
				g2.drawString(">", x-gp.tileSize, y);
			}
		}
		
	}
	
	public void drawPauseScreen() {
		
		g2.setFont(arial_80B);
		text = "Paused";
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
	
	public void drawCharacterScreen() {
		final int frameX = gp.tileSize/2;
		final int frameY = (int)(gp.tileSize*1.5);
		final int frameWidth = gp.tileSize*5;
		final int frameHeight = gp.tileSize*10;
		
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// Text
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
		
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 35;
		
		// Names
		g2.drawString("Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Life", textX, textY);
		textY += lineHeight;
		g2.drawString("Strength", textX, textY);
		textY += lineHeight;
		g2.drawString("Dexterity", textX, textY);
		textY += lineHeight;
		g2.drawString("Attack", textX, textY);
		textY += lineHeight;
		g2.drawString("Defense", textX, textY);
		textY += lineHeight;
		g2.drawString("Exp", textX, textY);
		textY += lineHeight;
		g2.drawString("Next Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Coin", textX, textY);
		textY += lineHeight + 15;
		g2.drawString("Weapon", textX, textY);
		textY += lineHeight + 25;
		g2.drawString("Shield", textX, textY);
		textY += lineHeight;
		
		// Values
		int rightJust = (frameX + frameWidth) - 30;
		textY = frameY + gp.tileSize;
		String value;
		
		value = String.valueOf(gp.player.getLevel());
		textX = getXforRightJustifiedText(value, rightJust);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.getLife() + "/"+ gp.player.getMaxLife());
		textX = getXforRightJustifiedText(value, rightJust);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.getStrength());
		textX = getXforRightJustifiedText(value, rightJust);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.getDexterity());
		textX = getXforRightJustifiedText(value, rightJust);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.getAttack());
		textX = getXforRightJustifiedText(value, rightJust);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.getDefense());
		textX = getXforRightJustifiedText(value, rightJust);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.getExp());
		textX = getXforRightJustifiedText(value, rightJust);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.getNextLevelExp());
		textX = getXforRightJustifiedText(value, rightJust);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.getCoin());
		textX = getXforRightJustifiedText(value, rightJust);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		g2.drawImage(gp.player.currentWeapon.image1, rightJust-gp.tileSize, textY-15, null);
		textY += gp.tileSize;
		g2.drawImage(gp.player.currentShield.image1, rightJust-gp.tileSize, textY-4, null);
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
	public int getXforRightJustifiedText(String text, int rightJust) {
		return rightJust - (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth()/2;
	}
}
