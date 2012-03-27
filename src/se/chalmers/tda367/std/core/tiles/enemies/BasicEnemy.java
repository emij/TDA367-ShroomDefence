package se.chalmers.tda367.std.core.tiles.enemies;

import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Represents a very basic enemy for use in a text based game.
 * <p>Start health: <b>100</b>
 * <p>Gold received: <b>5</b>
 * @author Emil Edholm
 * @modified Emil Johansson
 * @date   25 mar 2012
 */
public final class BasicEnemy extends AbstractEnemy {

	/**
	 * Creates a basic enemy.
	 */
	public BasicEnemy() {
		super(100, 5, new Sprite());
	}
	
	/**
	 * The textual representation on a text based game board.
	 */
	@Override
	public String toString(){
		return "e";
	}

}
