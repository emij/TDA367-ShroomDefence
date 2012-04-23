package se.chalmers.tda367.std.core.exported;

import java.util.List;

import se.chalmers.tda367.std.core.anno.Enemy;
import se.chalmers.tda367.std.core.enemies.AbstractEnemy;
import se.chalmers.tda367.std.utilities.Position;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Represents a very basic enemy for use in a text based game.
 * <p>Start health: <b>100</b>
 * <p>Speed: <b>2</b>
 * <p>Gold received: <b>5</b>
 * @author Emil Edholm
 * @modified Emil Johansson
 * @date   25 mar 2012
 */
@Enemy(name = "Basic enemy", description = "Represents the most common enemy.", enemyStrength = 1)
public final class BasicEnemy extends AbstractEnemy {

	/**
	 * Creates a basic enemy.
	 */
	public BasicEnemy(List<Position> waypoints, Position startPos) {
		super(100, 2, 5, new Sprite(), waypoints, startPos);
	}
	
	/**
	 * The textual representation on a text based game board.
	 */
	@Override
	public String toString(){
		return "e";
	}

	@Override
	public Sprite getSprite() {
		// TODO Auto-generated method stub
		return null;
	}

}
