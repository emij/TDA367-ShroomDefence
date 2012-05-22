package se.chalmers.tda367.std.core.enemies;

import java.util.List;

import se.chalmers.tda367.std.core.Attackable;
import se.chalmers.tda367.std.utilities.Position;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Represents a killable enemy.
 * The {@code IEnemy} is sortable/comparable by the amount of distance traveled.
 * @author Emil Edholm
 * @modified Emil Johansson, Johan Andersson
 * @modified Emil Edholm (May 16, 2012)
 * @date Mar 25, 2012
 */
public interface IEnemy extends Attackable {
	
	/**
	 * Returns the base health of the enemy, enemy health without any effects on it.
	 * @return the base health of the enemy.
	 */
	public int getBaseHealth();
	
	/**
	 * Returns the health of the enemy.
	 * @return the current health of the enemy.
	 */
	public int getHealth();

	/**
	 * Returns the amount of gold you get for killing an enemy.
	 * @return the lootValue of the enemy.
	 */
	public int getLootValue();

	/**
	 * Returns the speed of the enemy.
	 * @return the speed of the enemy
	 */
	public float getSpeed();
	
	/**
	 * Returns the base speed of the enemy, enemy speed without any effects on it.
	 * @return the base speed of the enemy.
	 */
	public float getBaseSpeed();
	
	/**
	 * Returns the armor of the enemy.
	 * @return the armor of the enemy
	 */
	public int getArmor();
	
	/**
	 * Returns the base armor of the enemy, enemy armor without any effects on it.
	 * @return the base armor of the enemy.
	 */
	public int getBaseArmor();
	
	/**
	 * @return the sprite (image representation) of the Enemy.
	 */
	public Sprite getSprite();

	/**
	 * Move the enemy to the supplied start position and set the waypoints.
	 * @param p - the position to move to.
	 */
	public void placeOnBoard(Position start, List<Position> waypoints);
	
	/**
	 * Move the enemy towards the next {@code waypoint} based on 
	 * the {@code delta} value and the enemy speed.
	 * @param delta - the amount of time in milliseconds since the last walk update.
	 */
	public void moveTowardsWaypoint(int delta);
	
	/**
	 * @return the distance the {@code IEnemy} has traveled.
	 */
	public float getDistanceTraveled();
	
	/** @return a defensive copy of the Enemy position. */
	public Position getPosition();
	
	@Override
	public String toString();
}
