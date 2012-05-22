package se.chalmers.tda367.std.tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import se.chalmers.tda367.std.core.DynamicLoader;
import se.chalmers.tda367.std.core.enemies.IEnemy;
import se.chalmers.tda367.std.core.exported.BasicEnemy;
import se.chalmers.tda367.std.utilities.Position;
import se.chalmers.tda367.std.utilities.SpriteCreator;

@RunWith(Parameterized.class)
public class TestIEnemy {

	private IEnemy enemy;

	public TestIEnemy(IEnemy enemy){
		this.enemy = enemy;
	}
	
	
	@SuppressWarnings("rawtypes") // JUnit expects a list of objects. Save to ignore.
	@Parameterized.Parameters
	public static Collection testEnemies(){
		SpriteCreator.setNativeSpriteClass(NativeDummySprite.class);
		
		List<IEnemy> enemiesToBeTested = new ArrayList<IEnemy>();
		List<Class<IEnemy>> enemies = DynamicLoader.getEnemies();
		
		for(Class<IEnemy> e : enemies){
			enemiesToBeTested.add(DynamicLoader.createInstance(e));
		}
		
		Object[][] coll = new Object[enemiesToBeTested.size()][1];

		for(int i = 0; i < coll.length; i++){
			coll[i][0] = enemiesToBeTested.get(i);
		}
		
		return Arrays.asList(coll);
	}
	
	@Before
	public void before(){
		enemy = DynamicLoader.createInstance(enemy.getClass());
	}

	
	@Test
	public void testGetArmor() {
		//getArmor and getBaseArmor should return same when no effects are applied
		assertTrue(enemy.getArmor() == enemy.getBaseArmor());
		
		//0.0 in armor modifier
//		IEffect effect = new DummyEffectSecond();
//		enemy.addEffect(effect);
		
		enemy = DynamicLoader.createInstance(BasicEnemy.class);
		//2.0 in armor modifier
//		enemy.addEffect(new DummyArmorIncEffect());
		assertTrue(enemy.getArmor() == enemy.getBaseArmor() * 2);
		
	}
	
	@Test
	public void testGetHealth() {
		//getHealth and getBaseHealth should return same when no effects are applied
		assertTrue(enemy.getHealth() == enemy.getBaseHealth());
	}
	

	@Test
	public void testMoveTowardsWaypoint(){
		final int X = 10000;
		final int Y = 10000;
		final int DELTA = 1000;
		
		enemy.placeOnBoard(new Position(0, 0), createSingleWayPointList(X, 0));
		
		
		enemy.moveTowardsWaypoint(DELTA);
		float distance = enemy.getDistanceTraveled();
		assertTrue(distance - DELTA*enemy.getSpeed() < 0.001);
		enemy.moveTowardsWaypoint(DELTA);
		assertTrue(enemy.getDistanceTraveled() - distance * 2 < 0.001);
		
		//New instance to be sure the enemy is at start position
		enemy = DynamicLoader.createInstance(BasicEnemy.class);
		enemy.placeOnBoard(new Position(0, 0), createSingleWayPointList(X, 0));
		
		enemy.moveTowardsWaypoint(DELTA);
		assertTrue(enemy.getPosition().getX() - (X-enemy.getSpeed()*DELTA) < 0.001); 
		
		//New instance to be sure the enemy is at start position
		enemy = DynamicLoader.createInstance(BasicEnemy.class);
		enemy.placeOnBoard(new Position(0, 0), createSingleWayPointList(Y, 0));
		
		enemy.moveTowardsWaypoint(DELTA);
		assertTrue(enemy.getPosition().getY() - (Y-enemy.getSpeed()*DELTA) < 0.001); 
		
	}
	
	private List<Position> createSingleWayPointList(int x, int y){
		List<Position> waypoints = new ArrayList<Position>();
		waypoints.add(new Position(x,y));
		return  waypoints;
	}
	
	@Test
	public void testCompareTo() {
		IEnemy enemy2 = DynamicLoader.createInstance(BasicEnemy.class);
		enemy.placeOnBoard(new Position(0, 0), createSingleWayPointList(10000, 0));
		enemy2.placeOnBoard(new Position(0, 0), createSingleWayPointList(10000, 0));
		
		assertTrue(enemy.compareTo(enemy2) == 0);
		
		enemy.moveTowardsWaypoint(1000);
		assertTrue(enemy.compareTo(enemy2) > 0);
		assertTrue(enemy2.compareTo(enemy) < 0);
		
	}
}
