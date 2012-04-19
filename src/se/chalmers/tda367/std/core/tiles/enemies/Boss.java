package se.chalmers.tda367.std.core.tiles.enemies;

import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Represents a boss enemy.
 * <p>Start health: <b>1000</b>
 * <p>Speed: <b>5</b>
 * <p>Gold received: <b>50</b>
 * @author Johan Gustafsson
 * @date   19 apr 2012
 */
public class Boss extends AbstractEnemy {
	
	/**
	 *  Creates a boss enemy.
	 */
	public Boss() {
		super(1000, 5, 50, new Sprite());
	}
	
	/**
	 * The textual representation on a text based game board.
	 */
	@Override
	public String toString(){
		return "b";
	}
}
