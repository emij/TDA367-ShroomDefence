package se.chalmers.tda367.std.core.exported;

import java.util.List;

import se.chalmers.tda367.std.core.anno.Enemy;
import se.chalmers.tda367.std.core.enemies.AbstractEnemy;
import se.chalmers.tda367.std.utilities.Position;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Represents an enemy that moves very fast but has little health.
 * <p>Start health: <b>50</b>
 * <p>Speed: <b>3</b>
 * <p>Gold received: <b>5</b>
 * @author Johan Gustafsson
 * @date   19 apr 2012
 */
@Enemy(name = "Speedy gonzales", description = "Represents an enemy slightly faster than usual.", enemyStrength = 1)
public class FastEnemy extends AbstractEnemy {
	
	/**
	 *  Creates a fast enemy.
	 */
	public FastEnemy() {
		super(50, 3, 5, new Sprite("/images/gameplay/enemy.png")); //TODO: change sprite
	}
	
	/**
	 * The textual representation on a text based game board.
	 */
	@Override
	public String toString(){
		return "f";
	}

	@Override
	public Sprite getSprite() {
		// TODO Auto-generated method stub
		return null;
	}
}
