package se.chalmers.tda367.std.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import se.chalmers.tda367.std.core.DynamicLoader;
import se.chalmers.tda367.std.core.effects.IEffect;
import se.chalmers.tda367.std.core.enemies.IEnemy;
import se.chalmers.tda367.std.core.exported.BasicEnemy;
import se.chalmers.tda367.std.utilities.Position;
import se.chalmers.tda367.std.utilities.SpriteCreator;
import se.chalmers.tda367.std.tests.NativeDummySprite;

public class TestBasicEnemy {
	IEnemy enemy;
	private final int BASIC_ENEMY_LOOT_VALUE = 50;
	private final int BASIC_ENEMY_ARMOR = 5;
	
	@Before
	public void before(){
		SpriteCreator.setNativeSpriteClass(NativeDummySprite.class);
		enemy = DynamicLoader.createInstance(BasicEnemy.class);
	}
	
	@Test
	public void testGetBaseSpeed() {
		//0.2F standard speed of enemy
		assertTrue(enemy.getBaseSpeed() - 0.2F < 0.0001 );
	}
	
	@Test
	public void testGetArmor() {
		//getArmor and getBaseArmor should return same when no effects are applied
		assertTrue(enemy.getArmor() == enemy.getBaseArmor());
		
		//0.0 in armor modifier
		IEffect effect = new DummyEffectSecond();
		enemy.addEffect(effect);
		
		enemy = DynamicLoader.createInstance(BasicEnemy.class);
		//2.0 in armor modifier
		enemy.addEffect(new DummyArmorIncEffect());
		assertTrue(enemy.getArmor() == BASIC_ENEMY_ARMOR * 2);
		
	}
	
	@Test
	public void testGetHealth() {
		//getHealth and getBaseHealth should return same when no effects are applied
		assertTrue(enemy.getHealth() == enemy.getBaseHealth());
	}
	
	@Test
	public void testGetLootValue() {
		assertTrue(enemy.getLootValue() == BASIC_ENEMY_LOOT_VALUE);
	}
	
	@Test
	public void testDecHealth() {
		int damage = 100;
		enemy.decreaseHealth(damage);
		assertTrue(enemy.getHealth() == enemy.getBaseHealth() - (damage - BASIC_ENEMY_ARMOR));
		
		enemy.decreaseHealth(Integer.MAX_VALUE);
		enemy.decreaseHealth(Integer.MAX_VALUE);
		assertTrue(enemy.getHealth() == 0);
	}
	
	@Test
	public void testGetEmptyEffect() {
		assertTrue(enemy.getEffects().size() == 0);
	}
	
	@Test
	public void testAddEffect() {
		IEffect effect = new DummyEffect();
		enemy.addEffect(effect);
		assertTrue(enemy.getEffects().size() == 1);
		enemy.addEffect(effect);
		assertTrue(enemy.getEffects().size() == 1);
		effect = new DummyEffectSecond();
		enemy.addEffect(effect);
		assertTrue(enemy.getEffects().size() == 2);
	}
	
	@Test
	public void testRemoveEffect() {
		IEffect effect1 = new DummyEffect();
		IEffect effect2 = new DummyEffectSecond();
		
		enemy.removeEffect(effect1);
		enemy.removeEffect(effect2);
		
		enemy.addEffect(effect1);
		enemy.removeEffect(effect1);
		assertTrue(enemy.getEffects().size() == 0);
		
		enemy.addEffect(effect1);
		enemy.addEffect(effect2);
		enemy.removeEffect(effect1);
		assertTrue(enemy.getEffects().size() == 1);
		assertTrue(enemy.getEffects().get(0) == effect2);
	
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
