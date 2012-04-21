package se.chalmers.tda367.std.core.tiles.enemies;

import java.util.List;

import se.chalmers.tda367.std.utilities.Position;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Represents a boss enemy.
 * <p>Start health: <b>1000</b>
 * <p>Speed: <b>2</b>
 * <p>Gold received: <b>50</b>
 * @author Johan Gustafsson
 * @date   19 apr 2012
 */
public class Boss extends AbstractEnemy {
	
	/**
	 *  Creates a boss enemy.
	 */
	public Boss(List<Position> waypoints, Position startPos) {
		super(1000, 2, 50, new Sprite(), waypoints, startPos);
	}
	
	/**
	 * The textual representation on a text based game board.
	 */
	@Override
	public String toString(){
		return "b";
	}

	@Override
	public Sprite getSprite() {
		// TODO Auto-generated method stub
		return null;
	}
}
