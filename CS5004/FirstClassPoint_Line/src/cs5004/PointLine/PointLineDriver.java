/* 
 * Created by: "Robert Wilson"
 * Date: "05 February 2022"
 */

package cs5004.PointLine;

/**
 * This class drives the PointLine project.
 * 
 * @author Robert Wilson
 *
 */
public class PointLineDriver {	
	/**
	 * Main method which drives the PointLine project. First it creates five Points, 
	 * One in each quadrant and one at the origin.  
	 * 
	 * @param args command line arguments which are not used
	 */
	public static void main(String[] args) {
		// Create and test five points
		Point p1;
		Point p2;
		Point p3;
		Point p4;
		Point p5;
		
		Line l1;
		Line l2;
		Line l3;
		
		Rectangle r1;
		Square 	  s1;
		
		p1 = new Point();
		p2 = new Point(1, 1);
		p3 = new Point(-1, 1);
		p4 = new Point(-1, -1);
		p5 = new Point(1, -1);
		
		// Manual test of quadrants
		testPoints(p1, p2, p3, p4, p5);
		
		p1.printPoint();
		p2.printPoint();
		p3.printPoint();
		p4.printPoint();
		p5.printPoint();
		
		// Create and test three lines
		l1 = new Line();
		l2 = new Line(p2, p4);
		l3 = new Line(-1, -1, 1, -1);
		
		l1.printPoints();
		l2.printPoints();
		l3.printPoints();
		
		// Manual test of lines
		testLines(l1, l2, l3);
		
		// Create a Rectangle and Square
		r1 = new Rectangle(new Point(-3, -1), new Point(3, 1));
		s1 = new Square(new Point(-1, -1), 2);
		
		r1.getLine(1).printPoints();
		s1.getLine(2).printPoints();
	}
	
	/**
	 * Test to see if each point is in the correct quadrant.
	 * 
	 * @param p1 Point one object
	 * @param p2 Point two object
	 * @param p3 Point three object
	 * @param p4 Point four object
	 * @param p5 Point five object
	 */
	public static void testPoints(Point p1, Point p2, Point p3, Point p4, Point p5) {
		if(p1.getQuadrant() == "Origin") { 
			System.out.println("Origin test passed"); 
		} else {
			System.out.println("Origin test failed");
		} 
		if(p2.getQuadrant() == "A") {
			System.out.println("Quadrant A test passed"); 
		} else {
			System.out.println("Quadrant A test failed");
		} 
		if(p3.getQuadrant() == "B") {
			System.out.println("Quadrant B test passed"); 
		} else {
			System.out.println("Quadrant B test failed");
		} 
		if(p4.getQuadrant() == "C") {
			System.out.println("Quadrant C test passed"); 
		} else {
			System.out.println("Quadrant C test failed");
		} 
		if(p5.getQuadrant() == "D") {
			System.out.println("Quadrant D test passed"); 
		} else {
			System.out.println("Quadrant D test failed");
		} 
	}
	
	/**
	 * Test to see if length of each line is as expected using the given formula sqrt((x2-1x)²+(y2-y1)²)
	 * 
	 * @param l1 line one object
	 * @param l2 line two object
	 * @param l3 line three object
	 */
	public static void testLines(Line l1, Line l2, Line l3) {
		if(l1.getLength() == 0) {
			System.out.println("Length test passed"); 
		} else {
			System.out.println("Length test failed");
		} 
		if(l2.getLength() == (2*Math.sqrt(2))) {
			System.out.println("Length test passed"); 
		} else {
			System.out.println("Length test failed");
		} 
		if(l3.getLength() == 2) {
			System.out.println("Length test passed"); 
		} else {
			System.out.println("Length test failed");
		} 
	}
}
