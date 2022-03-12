package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entities.*;

public class PlayerTest {

	Player player1;
	Player player2;
	int p2Hp = 105;
	int p2Str = 20;
	String p2Nm = "Bob";
	
	@Before
	public void setUp() throws Exception {
		player1 = new Player();
		player2 = new Player(p2Hp, p2Str, p2Nm);
	}

	@Test
	public void healthTest() {
		assertEquals(100,player1.getHealth());
		assertEquals(100, player2.getHealth());
	}
	
	@Test
	public void strengthTest() {
		assertEquals(10,player1.getStrength());
		assertEquals(20, player2.getStrength());
	}
	
	@Test
	public void nameTest() {
		assertEquals("Bard",player1.getName());
		assertEquals("Bob", player2.getName());
	}
}