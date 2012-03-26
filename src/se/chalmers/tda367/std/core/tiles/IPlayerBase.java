package se.chalmers.tda367.std.core.tiles;


/**
 * Represents the player base. 
 * i.e. the structure the enemies tries to destroy/get into.
 * @author Unchanged
 * @date Mar 22, 2012
 */
public interface IPlayerBase extends IBoardTile{
	/**
	 * Returns the health of the player base.
	 */
	public int getHealth();
	public int increaseHealth(int inc);
	public int decreaseHealth();
	public int decreaseHealth(int dmg);
}
