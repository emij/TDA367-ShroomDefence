package se.chalmers.tda367.std.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import se.chalmers.tda367.std.core.*;

/**
 * Unit test for {@link se.chalmers.tda367.std.core.GameController}.
 * @author Emil Johansson
 * @date   27 mar 2012
 */
	//TODO more extensive test
public class TestGameController {
	Player player = new Player();
	GameController gCont;
	@Before
	public void before(){
		gCont = new GameController(player);
	}
	
	@Test
	public void testNextWave() {
		gCont.nextWave();
		assertTrue(gCont.getWavesReleased() == 1);
	}
	
	@Test
	public void testGetWavesReleased(){
		assertTrue(gCont.getWavesReleased() == 0);
	}
	@Test
	public void testGetLevel(){
		assertTrue(gCont.getLevel() == 1);
	}
}
