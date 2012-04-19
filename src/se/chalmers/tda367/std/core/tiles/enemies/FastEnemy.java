package se.chalmers.tda367.std.core.tiles.enemies;

import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Represents an enemy that moves very fast but has little health.
 * <p>Start health: <b>50</b>
 * <p>Speed: <b>10</b>
 * <p>Gold received: <b>5</b>
 * @author Johan Gustafsson
 * @date   19 apr 2012
 */
public class FastEnemy extends AbstractEnemy {
	
	/**
	 *  Creates a fast enemy.
	 */
	public FastEnemy() {
		super(50, 10, 5, new Sprite());
	}
	
	/**
	 * The textual representation on a text based game board.
	 */
	@Override
	public String toString(){
		return "f";
	}
}
