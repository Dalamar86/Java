import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sentenceList.*;

public class SentenceTest {

	private Sentence s1;
	private Sentence s2;
	private Sentence s3;
	
	@Before
	public void setUp() throws Exception {
		s1 = new SentenceList();
		s2 = new SentenceList("Hello");
		s3 = new SentenceList("Hello World! My name is Tony.");
	}

	/**
	 * tests get method
	 */
	@Test
	public void getTest() {
		assertEquals("", s1.getWord());
		assertEquals("Hello", s2.getWord(0));
		assertEquals("Tony", s3.get(6).getWord());
	}
	
	/**
	 * tests add methods
	 */
	@Test
	public void addTest() {
		s1.add(0, "Hello", false);
		//s2.addBack(null);
		//s3.addFront(null);
		assertEquals("", s1.get(0));
		//assertEquals("Hello", s1.get(0));
		//assertEquals("Tony", s1.get(6));
	}
	
	/**
	 * tests remove method
	 */
	@Test
	public void removeTest() {
		s1.remove(null);
		//s2.remove(null);
		assertEquals("", s1.get(0));
		assertEquals("Hello", s1.get(0));
		assertEquals("Tony", s1.get(6));
	}

}
