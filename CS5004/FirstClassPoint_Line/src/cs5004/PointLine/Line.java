/* 
 * Created by: "Robert Wilson"
 * Date: "05 February 2022"
 */

package cs5004.PointLine;
import java.lang.Math;

/**
 * A class that describes a line with two points, start and end.
 * 
 * @author Robert Wilson 
 */

public class Line {
	private Point p1;
	private Point p2;
	private double length;
	
	/**
	 * Construct a Line object with two default points at origin
	 */
	public Line() {
		p1 = new Point();
		p2 = new Point();
	}
	
	/**
	 * Construct a Line object with two points set by the user.
	 * 
	 * @param p1 Point 1 of this Line.
	 * @param p1 Point 2 of this Line.
	 */
	public Line(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	/**
	 * Construct a Line object with two points set by the user.
	 * 
	 * @param x1 x of p1.
	 * @param y1 y of p1.
	 * @param x2 x of p2.
	 * @param y2 y of p2.
	 * 
	 */
	public Line(int x1, int y1, int x2, int y2) {
		p1 = new Point(x1, y1);
		p2 = new Point(x2, y2);
	}
	
	/**
	 * Set x and y of p1 in this line to the parameters x, y
	 * 
	 * @param x first value of p1
	 * @param y second value of p1
	 */
	public void setP1(int x, int y) {
		p1.setX(x);
		p1.setY(y);
	}
	
	/**
	 * Set x and y of p2 in this line to the parameters x, y
	 * 
	 * @param x first value of p2
	 * @param y second value of p2
	 */
	public void setP2(int x, int y) {
		p2.setX(x);
		p2.setY(y);
	}
	
	/**
	 * Returns p1 in this line.
	 * 
	 * @return p1 First point of this line
	 */
	public Point getP1() {
		return p1;
	}
	
	/**
	 * Returnsp2 in this line.
	 * 
	 * @return p2 Second point of this line
	 */
	public Point getP2() {
		return p2;
	}
	
	/**
	 * Prints the points of this Line by calling the printPoint method
	 */
	public void printPoints() {
		System.out.print("Point One: ");
		p1.printPoint();
		System.out.print("Point Two: ");
		p2.printPoint();
	}
	
	/**
	 * Returns the length of this line using the formula: sqrt[(x2 - x1)² + (y2 - y1)²]
	 * 
	 * @return length length using sqrt[(x2 - x1)2 + (y2 - y1)2]
	 */
	public double getLength() {
		length = Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
		return length;
	}
}
