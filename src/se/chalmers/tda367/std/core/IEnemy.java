package se.chalmers.tda367.std.core;

/**
 * Represents an enemy.
 * @author Unchanged
 * @date Mar 22, 2012
 */
public interface IEnemy extends IBoardTile{
	/**
	 * Returns the health of the enemy
	 * @return
	 */
	public int getHealth();
	/**
	 * Damage the enemy with the specified value
	 * @param dmg
	 */
	public void decreaseHealth(int dmg);
	

}