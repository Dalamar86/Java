package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entities.*;

public class SkeletonTest {

	Entities skel1;
		
	@Before
	public void setUp() throws Exception {
		skel1 = new Skeleton();
	}

	@Test
	public void healthTest() {
		assertEquals(30,skel1.getHealth());
	}
	
	@Test
	public void strengthTest() {
		assertEquals(2,skel1.getStrength());
	}
	
	@Test
	public void nameTest() {
		assertEquals("skeleton",skel1.getName());
	}
}