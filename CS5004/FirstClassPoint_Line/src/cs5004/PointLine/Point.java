/* 
 * Created by: "Robert Wilson"
 * Date: "05 February 2022"
 */

package cs5004.PointLine;
/**
* A class that describes a point with two integers (x, y)
* 
* @author Robert Wilson
*/
public class Point {

	private int x;
	private int y;
	private String quadrant;

	/**
	* Construct a Point object that has the default values of x and y.
	*/
	public Point() {
		setX(0);
		setY(0);
	}
	
	/**
	* Construct a Point object that takes in two parameters
	* which will be passed to setter methods for int x and int y.
	*
	* @param x The integer value for the x component (first number) of the point.
	* @param y The integer value for the y component (second number) of the point.
	*/
	public Point(int x, int y) {
		setX(x);
		setY(y);
	}

	/**
	 * Sets the x value of this Point
	 * 
	 * @param x The integer to save to the x field.
	 */
	public void setX(int x) {
		if (x >= 100) {
			x = 99;
		} else if(x <= -100) {
			x = -99;
		}
		this.x = x;
	}
	
	/**
	 * Sets the y value of this Point
	 * 
	 * @param y The integer to save to the y field.
	 */
	public void setY(int y) {
		if (y >= 100) {
			y = 99;
		} else if(y <= -100) {
			y = -99;
		}
		this.y = y;
	}
	
	/**
	 * Returns the x value of this Point
	 * 
	 * @return x The integer saved to the x field.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Returns the y value of this Point
	 * 
	 * @return y The integer saved to the y field.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Returns the (x,y) values of this Point as an array
	 * 
	 * @return [x, y] The integer array saved to the x and y fields.
	 */
	public int[] getXY() {
		int[] arr = {x, y};
		return arr;
	}
	
	/**
	 * Returns the quadrant of this Point as a String if it lies in one of the quadrants, 
	 * origin if it sits at (0,0), and border if it sits on the border.
	 * 
	 * @return quadrant The String representing the quadrant the point is in.
	 */
	public String getQuadrant() {
		quadrant = "None";
		if(x==0 && y==0) {
			quadrant = "Origin";
		} else if(x == 0 || y == 0) {
			quadrant = "Border";
		} else if(x > 0 && y > 0) {
			quadrant = "Quadrant A";
		} else if(x < 0 && y > 0) {
			quadrant = "Quadrant B";
		} else if(x < 0 && y < 0) {
			quadrant = "Quadrant C";
		} else if(x > 0 && y < 0) {
			quadrant = "Quadrant D";
		}
		
		System.out.println(quadrant);
		return quadrant;
	}
	
	/**
	 * Prints the x and y coordinates of this Point.
	 */
	public void printPoint() {
		System.out.println("X Coordinate: " + getX());
		System.out.println("Y Coordinate: " + getY());
	}
}
