package se.chalmers.tda367.std.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import se.chalmers.tda367.std.core.DynamicLoader;
import se.chalmers.tda367.std.core.enemies.IEnemy;
import se.chalmers.tda367.std.core.exported.BasicEnemy;

public class TestBasicEnemy {
	IEnemy enemy;

	
	@Before
	public void before(){
		enemy = DynamicLoader.createInstance(BasicEnemy.class);
	}
	
	@Test
	public void testGetBaseSpeed() {
		assertTrue(enemy.getBaseSpeed() - 0.2F < 0.0001 );
	}

}
