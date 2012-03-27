package se.chalmers.tda367.std.core.tiles;

import se.chalmers.tda367.std.utilities.Sprite;


/**
 * Represents the player base. 
 * i.e. the structure the enemies tries to destroy/get into.
 * @author Emil Johansson
 * @date Mar 22, 2012
 */
public interface IPlayerBase extends IBoardTile{
	/**
	 * Returns the health of the player base.
	 * @return health of the player base
	 */
	public int getHealth();
	/**
	 * Increase health of the player base with specified value.
	 * @param inc
	 * @return health of player base
	 */
	public int increaseHealth(int inc);
	/**
	 * Decrease health of player base with one.
	 * @return health of player base
	 */
	public int decreaseHealth();
	/**
	 * Decrease health of player base with specified value.
	 * @param dmg
	 * @return health of player base
	 */
	public int decreaseHealth(int dmg);
	/**
	 * Returns the graphical representation of the player base.
	 * @return sprite of the player base
	 */
	public Sprite getSprite();
}
