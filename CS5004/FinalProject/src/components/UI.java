package components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import main.*;

public class UI {

	// Setup
	GamePanel gp;
	Graphics2D g2;
	public Font arial_40;
	Font arial_80B;
	Font arial;
	Font baskerville;
	
	// Player overlay
	BufferedImage heart_full, heart_half, heart_blank; //, sword, shield;
	
	// Pop ups
	public boolean messageOn = false;
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	
	// Dialogue text
	private String currentDialogue = "";
	private String text;
	
	// Title screen
	int commandNum = 0;
	
	// End game
	public boolean levelFinished = false;
	int titleScreenState = 0;
	
	// Inventory
	public int slotColMax = 5;
	public int slotRowMax = 4;
	public int slotCol = 0;
	public int slotRow = 0;
	
	
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
		GameObjectModel heart = new OBJ_Heart(gp);
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
	
	public void addMessage(String text) {
		message.add(text);
		messageCounter.add(0);
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		switch(gp.gameState) {
		case PLAYSTATE:
			drawHUD();
			drawMessage();
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
			
			drawCharacterScreen();
			drawInventory();
			drawHUD();
			break;
		case DEADSTATE:
			drawDeathScreen();
			break;
		default:
			break;
		}
	}

	private void drawHUD() {
		
		// Empty hearts
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		
		while(i < gp.player.getMaxLife()/2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}
		int xKey = x;
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
		
		// Keys
		x = xKey; 
		x += gp.tileSize;
		y = gp.tileSize/2;
		i = 0;
		String text = " x " + gp.player.getHasKey();
		g2.drawImage(new OBJ_Key(gp).image1, x, y, null);
		x += gp.tileSize;
		y = (int) (gp.tileSize*1.5);
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
	}
	
	private void drawMessage() {
		int messageX = gp.tileSize;
		int messageY = gp.tileSize*4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));
		
		for(int i = 0; i < message.size(); i++) {
			if(message.get(i) != null ) {
				
				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX+2, messageY+2);
				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);
				
				int counter = messageCounter.get(i) + 1;
				messageCounter.set(i, counter);
				messageY += 50;
				
				if(messageCounter.get(i) > 120) {
					message.remove(i);
					messageCounter.remove(i);
				}
			}
		}
	}
	
	private void drawTitleScreen() {
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
	
	private void drawPauseScreen() {
		
		g2.setFont(arial_80B);
		text = "Paused";
		int x, y;
		x = getXforCenteredText(text);
		y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	
	private void drawDialogueScreen() {
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
	
	private void drawCharacterScreen() {
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
		g2.drawString(value, textX-15, textY);
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
	
	private void drawInventory() {
		
		// Frame
		int frameX = gp.tileSize*9;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize*6;
		int frameHeight = gp.tileSize*5;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		
		
		// Slot
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		int slotSize = gp.tileSize + 3;
		
		// Draw player inventory
		for(GameObject obj: gp.player.getInventory()) {
			if(obj == gp.player.currentWeapon || obj == gp.player.currentShield) {
				g2.setColor(new Color(240, 190, 90));
				g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
			}
			g2.drawImage(obj.image1, slotX, slotY, null);
			slotX += slotSize;
			if(slotX >= slotXstart + (slotSize*slotColMax)) {
				slotX = slotXstart;
				slotY += slotSize;
			}
		}
		
		// cursor
		int cursorX = slotXstart + (slotSize * slotCol);
		int cursorY = slotYstart + (slotSize * slotRow);
		int cursorWidth = gp.tileSize;
		int cursorHeight = gp.tileSize;
		
		// draw cursor
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
		
		// Description frame
		int dFrameX = frameX;
		int dFrameY = frameY + frameHeight + 20;
		int dFrameWidth = frameWidth;
		int dFrameHeight = gp.tileSize*3;
		
		
		
		// Description
		int textX = dFrameX + 20;
		int textY = dFrameY+ gp.tileSize;
		g2.setFont(g2.getFont().deriveFont(16F));
		int itemIndex = getItemIndex();
		
		
		if(itemIndex < gp.player.getInventory().size()){
			drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
			for(String line: gp.player.getInventory().get(itemIndex).getDescription().split("\n")) {
				g2.drawString(line, textX, textY);
				textY += 32;
			}
		}
	}
	
	public int getItemIndex() {
		int itemIndex = slotCol + (slotRow*5);
		return itemIndex;
	}
	
	private void drawDeathScreen() {
		int frameWidth = gp.tileSize*7;
		int frameHeight = gp.tileSize*3;
		int frameX = gp.screenWidth/2 - gp.tileSize*3;
		int frameY = gp.screenHeight/2;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		g2.setBackground(Color.black);
		g2.setFont(baskerville);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 42F));
		text = "You have died";
		int textX = getXforCenteredText(text) + 20;
		int textY = (int) (frameY + gp.tileSize*1.7);
		g2.drawString(text, textX , textY);
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
	
	public void setCurrentDialogue(String dialogue) {
		this.currentDialogue = dialogue;
	}
}