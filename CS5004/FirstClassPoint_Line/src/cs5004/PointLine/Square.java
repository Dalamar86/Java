/* 
 * Created by: "Robert Wilson"
 * Date: "05 February 2022"
 */

package cs5004.PointLine;

/**
 * A class that describes a square with four points, and four equal lines. 
 * Extends the rectangle class, methods area and perimeter.
 * 
 * @author Robert Wilson 
 */

public class Square {

	private Line l1;
	private Line l2;
	private Line l3;
	private Line l4;
	private double area;
	private double perimeter;
	
	/**
	 * Construct a Square with default points starting bottom left moving counter clockwise
	 * 
	 * p1 = (-1,1)
	 * p2 = (1, -1)
	 * p3 = (1, 1)
	 * p4 = (-1, 1)
	 */
	public Square() {
		l1 = new Line(-1, -1, 1, -1);
		l2 = new Line(-1, 1, 1, 1);
		l3 = new Line(-1, -1, -1, 1);
		l4 = new Line(1, -1, 1, 1);
		
	}
	
	/**
	 * Construct a square with bottom left point of p1 and sides of side
	 * 
	 * @param p1 starting bottom left of the square
	 * @param side length of square sides
	 */
	public Square(Point p1, int side) {
		Point p2 = new Point(p1.getX()+side, p1.getY());
		Point p3 = new Point(p1.getX()+side, p1.getY()+side);
		Point p4 = new Point(p1.getX(), p1.getY()+side);
		l1 = new Line(p1, p2);
		l2 = new Line(p4, p3);
		l3 = new Line(p1, p4);
		l4 = new Line(p2, p3);
	}
	
	/**
	 * Construct a square with bottom left point p1(x, y) and sides of side
	 * 
	 * @param x x of p1
	 * @param y y of p1
	 * @param side length of square sides
	 */
	public Square(int x, int y, int side) {
		Point p1 = new Point(x, y);
		Point p2 = new Point(p1.getX()+side, p1.getY());
		Point p3 = new Point(p1.getX()+side, p1.getY()+side);
		Point p4 = new Point(p1.getX(), p1.getY()+side);
		l1 = new Line(p1, p2);
		l2 = new Line(p4, p3);
		l3 = new Line(p1, p4);
		l4 = new Line(p2, p3);
	}	
	
	public Line getLine(int n) {
		if(n == 1) {
			return l1;
		} else if(n == 2) {
			return l2;
		} else if(n == 3) {
			return l3;
		} else if(n == 4) {
			return l4;
		} else {
			return l1;
		}
	}
	
	/**
	 * Returns the area by multiplying l1 by l1
	 * 
	 * @return area Area of the square
	 */
	public double getArea() {
		area = l1.getLength() * l1.getLength();
		return area;
	}
	
	/**
	 * Returns the perimeter of the Square by adding all sides together
	 * 
	 * @return perimeter perimeter of the square
	 */
	public double getPerimeter() {
		perimeter = l1.getLength() + l2.getLength() + l3.getLength() + l4.getLength();
		return perimeter;
	}
}
