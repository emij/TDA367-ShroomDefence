package se.chalmers.tda367.std.core.exported;

import se.chalmers.tda367.std.core.anno.Enemy;
import se.chalmers.tda367.std.core.enemies.AbstractEnemy;
import se.chalmers.tda367.std.utilities.SpriteCreator;
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
	
	//TODO: change sprite for the basic enemy.
	private final static Sprite sprite = SpriteCreator.create("/images/gameplay/enemy.png");
	
	/**
	 * Creates a basic enemy.
	 */
	public BasicEnemy() {
		super(100, 0.2F, 5, 50, sprite);
	}
	
	/**
	 * The textual representation on a text based game board.
	 */
	@Override
	public String toString(){
		return "e";
	}
}
