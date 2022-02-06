import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import cs5004.PointLine.*;

public class PointLineTester {

	Point p1;
	Point p2;
	Point p3;
	Point p4;
	Point p5;
	
	Line l1;
	Line l2;
	Line l3;
	
	// Extension
	Point p6;
	Point p7;
	Point p8;
	Point p9;
	Line l4;
	Rectangle r1;
	Rectangle r2;
	Square s1;
	Square s2;
	Square s3;
	
	@Before
	public void setUp()
	{
		p1 = new Point();//Origin Point
		p2 = new Point(1,2);//Quadrant A Point
		p3 = new Point(-1,2); //Quadrant B Point
		p4 = new Point (-1,-2); //Quadrant C Point
		p5 = new Point (1,-2); //Quadrant D Point
		
		l1 = new Line();
		l2 = new Line(p1,p2);
		l3 = new Line(4,5,6,7);
		
		// Extension
		p6 = new Point(-3, -1);
		p7 = new Point(3, -1);
		p8 = new Point(3, 1);
		p9 = new Point(-3, 1);
		l4 = new Line(p6, p7);
		r1 = new Rectangle();
		r2 = new Rectangle(p6, p8); 
		
		s1 = new Square();
		s2 = new Square(p6, 3);
		s3 = new Square(-4, -4, 4);
	}
	
	@Test
	public void noArgsPointConstructorTest() {
		assertEquals(0,p1.getX() + p1.getY());
	}
	
	@Test
	public void twoArgsPointConstructorTest()
	{
		assertEquals(1,p2.getX());
		assertEquals(2,p2.getY());
	}
	
	@Test
	public void setterTestX()
	{
		p1.setX(42);
		assertEquals(42,p1.getX());
	}
	
	@Test
	public void setterTestY()
	{
		p1.setY(42);
		assertEquals(42,p1.getY());
	}
	
	@Test
	public void limitTestGTX()
	{
		p1.setX(999);
		assertEquals(99,p1.getX());
	}
	
	@Test
	public void limitTestLTX()
	{
		p1.setX(-999);
		assertEquals(-99,p1.getX());
	}
	
	@Test
	public void limitTestGTY()
	{
		p1.setY(999);
		assertEquals(99,p1.getY());
	}
	
	@Test
	public void limitTestLTY()
	{
		p1.setY(-999);
		assertEquals(-99,p1.getY());
	}
	
	@Test
	public void originTest()
	{
		assertEquals("Origin",p1.getQuadrant());
	}
	
	@Test
	public void quadrantATest()
	{
		assertEquals("Quadrant A",p2.getQuadrant());
	}
	
	@Test
	public void quadrantBTest()
	{
		assertEquals("Quadrant B",p3.getQuadrant());
	}
	
	@Test
	public void quadrantCTest()
	{
		assertEquals("Quadrant C",p4.getQuadrant());
	}
	
	@Test
	public void quadrantDTest()
	{
		assertEquals("Quadrant D",p5.getQuadrant());
	}
	
	
	@Test
	public void noArgsConstructorLine()
	{
		assertEquals(0,
				l1.getP1().getX() + 
				l1.getP1().getY() + 
				l1.getP2().getX() + 
				l1.getP2().getY());
	}
	
	@Test
	public void pointArgsConstructorLine()
	{
		assertEquals(0,l2.getP1().getX());
		assertEquals(0,l2.getP1().getY());
		assertEquals(1,l2.getP2().getX());
		assertEquals(2,l2.getP2().getY());
	}
	
	@Test
	public void intArgsConstructorLine()
	{
		assertEquals(4,l3.getP1().getX());
		assertEquals(5,l3.getP1().getY());
		assertEquals(6,l3.getP2().getX());
		assertEquals(7,l3.getP2().getY());
	}
	
	@Test
	public void getLengthTest()
	{
		assertEquals(0.0,l1.getLength(),0.1);
		assertEquals(2.2, l2.getLength(), 0.1);
		assertEquals(2.8, l3.getLength(), 0.1);
		
	}
	
	// Extension
	@Test
	public void getXYTest() {
		int[] arr = p5.getXY();
		assertEquals(1, arr[0]);
		assertEquals(-2, arr[1]);
	}
	
	@Test
	public void setPointTest() {
		l4.setP1(-4,0);
		l4.setP2(4, 0);
		assertEquals(-4, l4.getP1().getX());
		assertEquals(4, l4.getP2().getX());
	}
	
	@Test
	public void getLengthTest2() {
		int i;
		for(i = 1; i < 6; i++) {
			assertEquals(2.0, s1.getLine(i).getLength(), 0.1);
			assertEquals(3.0, s2.getLine(i).getLength(), 0.1);
			assertEquals(4.0, s3.getLine(i).getLength(), 0.1);
		}
		assertEquals(6.0, r2.getLine(1).getLength(), 0.1);
		assertEquals(6.0, r2.getLine(2).getLength(), 0.1);
		assertEquals(2.0, r2.getLine(3).getLength(), 0.1);
		assertEquals(2.0, r2.getLine(4).getLength(), 0.1);
		assertEquals(6.0, r2.getLine(5).getLength(), 0.1);
	}
	
	@Test
	public void getAreaTest() {
		assertEquals(8.0, r1.getArea(), 0.1);
		assertEquals(4.0, s1.getArea(), 0.1);
	}
	
	@Test
	public void perimeterTest() {
		assertEquals(12.0, r1.getPerimeter(), 0.1);
		assertEquals(8.0, s1.getPerimeter(), 0.1);
	}
}