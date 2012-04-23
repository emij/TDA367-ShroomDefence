package se.chalmers.tda367.std.core.tiles.enemies;

import java.util.List;

import se.chalmers.tda367.std.utilities.Position;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Represents a slow but hard tank enemy which moves slow but have lots of health.
 * <p>Start health: <b>200</b>
 * <p>Speed: <b>1</b>
 * <p>Gold received: <b>5</b>
 * @author Johan Gustafsson
 * @date   19 apr 2012
 */
public class TankEnemy extends AbstractEnemy {

	/**
	 *  Creates a tank enemy.
	 */
	public TankEnemy() {
		super(200, 1, 5, new Sprite());
	}
	
	/**
	 * The textual representation on a text based game board.
	 */
	@Override
	public String toString(){
		return "t";
	}

	@Override
	public Sprite getSprite() {
		// TODO Auto-generated method stub
		return null;
	}
}
