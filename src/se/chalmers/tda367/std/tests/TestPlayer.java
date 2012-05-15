package se.chalmers.tda367.std.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import se.chalmers.tda367.std.core.*;

/**
 * Unit test for {@link se.chalmers.tda367.std.core.Player}.
 * @author Emil Johansson
 * @date   15 may 2012
 */
public class TestPlayer {
	Player player;
	@Before
	public void before(){
		player = new Player();
	}
	@Test
	public void testScore() {
		assertTrue(player.getCurrentScore() == 0);
		player.setScore(99);
		assertTrue(player.getCurrentScore() == 99);
	}
	@Test
	public void testName(){
		assertTrue(player.getName().equals("Random Player"));
		Player tmp = new Player("Test");
		assertTrue(tmp.getName().equals("Test"));
	}
	@Test
	public void testMoney(){
		assertTrue(player.getMoney() == 500);
		player.addMoney(500);
		assertTrue(player.getMoney() == 1000);
		player.removeMoney(1000);
		assertTrue(player.getMoney() == 0);
	}
}
