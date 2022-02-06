/* 
 * Created by: "Robert Wilson"
 * Date: "05 February 2022"
 */

package cs5004.PointLine;
/**
 * A class that describes a Rectangle with four points, and two sets of parallel lines.
 * 
 * @author Robert Wilson 
 */

public class Rectangle {

	private Line l1;
	private Line l2;
	private Line l3;
	private Line l4;
	private double area;
	private double perimeter;
	
	/**
	 * Construct a Rectangle with default points starting bottom left moving counter clockwise
	 * 
	 * p1 = (-2,1)
	 * p2 = (2, -1)
	 * p3 = (2, 1)
	 * p4 = (-2, 1)
	 */
	public Rectangle() {
		// x axis
		l1 = new Line(-2, 1, 2, 1);
		l2 = new Line(-2, -1, 2, -1);
		// y axis
		l3 = new Line(-2, -1, -2, 1);
		l4 = new Line(2, -1, 2, 1);
	}
	
	/**
	 * Construct a Rectangle with default points starting bottom left moving counter clockwise
	 * 
	 * @param p1 bottom left point
	 * @param p3 top right point
	 */
	public Rectangle(Point p1, Point p3) {
		Point p2 = new Point( p3.getX(), p1.getY());
		Point p4 = new Point(p1.getX(), p3.getY());
		l1 = new Line(p1, p2);
		l2 = new Line(p4, p3);
		l3 = new Line(p1, p4);
		l4 = new Line(p2, p3);
	}
	
	/**
	 * Return line with the given points
	 * 
	 * @param n number associated to a line
	 * @return line line specified by the points
	 */
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
	 * Returns the area by multiplying l1 by l3
	 * 
	 * @return area Area of the rectangle
	 */
	public double getArea() {
		area = l1.getLength() * l3.getLength();
		return area;
	}
	
	/**
	 * Returns the perimeter of the rectangle by adding all sides together
	 * 
	 * @return perimeter perimeter of the rectangle
	 */
	public double getPerimeter() {
		perimeter = l1.getLength() + l2.getLength() + l3.getLength() + l4.getLength();
		return perimeter;
	}
}
