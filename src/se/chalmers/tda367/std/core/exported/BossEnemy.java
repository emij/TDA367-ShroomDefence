package se.chalmers.tda367.std.core.exported;

import se.chalmers.tda367.std.core.anno.Enemy;
import se.chalmers.tda367.std.core.enemies.AbstractEnemy;
import se.chalmers.tda367.std.utilities.SpriteCreator;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Represents a boss enemy.
 * <p>Start health: <b>400</b>
 * <p>Speed: <b>2</b>
 * <p>Gold received: <b>50</b>
 * @author Johan Gustafsson
 * @date   19 apr 2012
 */
@Enemy(name = "El Jefe", description = "Represents a boss enemy.", enemyStrength = 2)
public class BossEnemy extends AbstractEnemy {
	//TODO: change sprite for the boss enemy.
	private final static Sprite sprite = SpriteCreator.create("/images/gameplay/enemy.png");
	/**
	 *  Creates a boss enemy.
	 */
	public BossEnemy() {
		super(400, 0.2F, 50, 10, sprite); //TODO: change sprite
	}
	
	/**
	 * The textual representation on a text based game board.
	 */
	@Override
	public String toString(){
		return "b";
	}
}
