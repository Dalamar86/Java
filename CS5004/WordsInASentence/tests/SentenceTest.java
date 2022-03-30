import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sentenceList.*;

public class SentenceTest {

	private SentenceList s1;
	private SentenceList s2;
	private SentenceList s3;
	private SentenceList s4;
	private SentenceList s5;
	private SentenceList s6;
	private SentenceList s7;
	private SentenceList s8;
	private SentenceList s9;
	private SentenceList s10;
	
	@Before
	public void setUp() throws Exception {
		s1 = new SentenceList();
		s2 = new SentenceList("Hello ");
		s3 = new SentenceList("Hello World! My name is Tony.");
		s4 = new SentenceList(s2);
		s5 = s2.clone();
		s6 = s5.clone();
		s7 = s1.clone();
		s8 = s3.clone();
		s9 = s3.clone();
		s10 = new SentenceList("Why this is atrotious!", s3);
	}

	/**
	 * tests that every constructor works
	 */
	@Test
	public void toStringTest() {
		assertEquals("",s1.toString());
		assertEquals("Hello ",s2.toString());
		assertEquals("Hello World! My name is Tony.",s3.toString());
		assertEquals("Hello ",s4.toString());
		assertEquals("Hello ",s5.toString());
		assertEquals("Hello ",s6.toString());
		assertEquals("",s7.toString());
		assertEquals("Hello World! My name is Tony.",s8.toString());
		assertEquals("Why this is atrotious!Hello World! My name is Tony.",s10.toString());
	}
	
	/**
	 * tests get methods
	 */
	@Test
	public void getTest() {
		assertEquals("", s1.getWord());
		assertEquals(" ", s2.getWord(1));
		assertEquals("Tony", s3.get(11).getWord());
	}
	
	/**
	 * tests addBack methods
	 */
	@Test
	public void addBackTest() {
		s1.addBack("Goodbye world.");
		s2.addBack(".");
		s3.addBack(" It isn't Mark!");
		s5.addBack("world");
		s6.addBack(" ");
		assertEquals("Goodbye world.", s1.toString());
		assertEquals("Hello .", s2.toString());
		assertEquals("Hello World! My name is Tony. It isn't Mark!", s3.toString());
		assertEquals("Hello world", s5.toString());
		assertEquals("Hello  ", s6.toString());
	}
	
	/**
	 * tests addFront methods
	 */
	@Test
	public void addFrontTest() {
		s1.addFront(" Goodbye world.");
		s2.addFront(".");
		s3.addFront("It isn't Mark! ");
		s5.addFront("world");
		s6.addFront(" ");
		assertEquals(" Goodbye world.", s1.toString());
		assertEquals(".Hello ", s2.toString());
		assertEquals("It isn't Mark! Hello World! My name is Tony.", s3.toString());
		assertEquals("worldHello ", s5.toString());
		assertEquals(" Hello ", s6.toString());
	}
	
	/**
	 * tests add methods
	 */
	@Test
	public void addTest() {
		s1.add(0, "Good.bye.bye. world.");
		s2.add(1, "World");
		s3.add( s3.getSize(), " It isn't Mark!");
		s5.add(0, "");
		s5.add(1, ".");
		s6.add(1, " ");
		s7.add(0, "word", true);
		s8.add(s8.getSize()-1, "Why hello Mark!");
		s9.add(1, "Why hello Mark!");
		assertEquals("Good.bye.bye. world.", s1.toString());
		assertEquals("HelloWorld ", s2.toString());
		assertEquals("Hello World! My name is Tony. It isn't Mark!", s3.toString());
		assertEquals("Hello. ",s5.toString());
		assertEquals("Hello  ", s6.toString());
		assertEquals("word", s7.toString());
		assertEquals("Hello World! My name is Tony.Why hello Mark!", s8.toString());
		assertEquals("Hello Why hello Mark!World! My name is Tony.", s9.toString());
	}
	
	/**
	 * tests getSize method
	 */
	@Test
	public void getSize() {
		assertEquals(0, s1.getSize());
		assertEquals(2, s2.getSize());
		assertEquals(13, s3.getSize());
	}
	
	/**
	 * tests remove methods
	 */
	@Test
	public void removeTest() {
		s2.remove("Hello");
		s3.remove(s3.getSize()-1);
		s5.remove(s5.getSize());
		s6.remove(".");
		assertEquals(" ", s2.toString());
		assertEquals("Hello World! My name is Tony", s3.toString());
		assertEquals("Hello ", s5.toString());
		assertEquals("Hello ", s6.toString());
	}

	/**
	 * tests getLongestWord method
	 */
	@Test
	public void longestWordTest() {
		assertEquals("", s1.longestWord());
		assertEquals("Hello", s2.longestWord());
		assertEquals("Hello", s3.longestWord());
	}
	
	/**
	 * tests getNumberOfWord method
	 */
	@Test
	public void numberWordTest() {
		assertEquals(0, s1.getNumberOfWords());
		assertEquals(1, s2.getNumberOfWords());
		assertEquals(6, s3.getNumberOfWords());
	}
	
	/**
	 * Testing the exceptions in the program
	 */
	@Test // (expected = IllegalArgumentException.class)
	public void exceptiontesting() {
		try {
			s1.get(1);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid index", e.getMessage());
		}
		
		try {
			s1.getWord(5);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid index", e.getMessage());
		}
		
		try {
			s1.getHead().get(1);
		} catch (IllegalArgumentException e) {
			assertEquals("wrong index", e.getMessage());
		}
		
		try {
			s1.getHead().getWord(1);
		} catch (IllegalArgumentException e) {
			assertEquals("wrong index", e.getMessage());
		}
		
		try {
			s1.add(1, "word");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid index to add an element", e.getMessage());
		}
		
		try {
			s1.getHead().add(1, "word");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid index to add an element", e.getMessage());
		}
	}
}
