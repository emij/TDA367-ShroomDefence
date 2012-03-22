/**
 * TDA367-ShroomDefence
 * 
 * Class explanation goes here.
 */
package se.chalmers.tda367.std.core;

/**
 * @author Unchanged
 * @date Mar 22, 2012
 */
public interface IPlayerBase extends IBoardTile{
	
	public int getHealth();
	public int increaseHealth(int inc);
	public int decreaseHealth();
	public int decreaseHealth(int dmg);

}
