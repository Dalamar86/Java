package tile;

import java.awt.image.BufferedImage;

/**
 * Tile object which holds the image associated with it and whether it is traversable.
 * 
 * @author Robert Wilson
 *
 */
public class Tile {
	// Tile image
	public BufferedImage image;
	
	// Can it be traversed
	public boolean collision = false;
}
