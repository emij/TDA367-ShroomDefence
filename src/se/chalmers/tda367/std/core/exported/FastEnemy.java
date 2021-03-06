package se.chalmers.tda367.std.core.exported;

import se.chalmers.tda367.std.core.anno.Enemy;
import se.chalmers.tda367.std.core.enemies.AbstractEnemy;
import se.chalmers.tda367.std.utilities.SpriteCreator;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Represents an enemy that moves very fast but has little health.
 * <p>Start health:  <b>55</b>
 * <p>Speed:         <b>0.12</b>
 * <p>Gold received: <b>75</b>
 * <p>Armor:         <b>3</b>
 * 
 * @author Johan Gustafsson
 * @date   19 apr 2012
 */
@Enemy(name = "Speedy gonzales", description = "Represents an enemy slightly faster than usual.", enemyStrength = 2)
public class FastEnemy extends AbstractEnemy {
	//TODO: change sprite for the fast enemy.
	private final static Sprite sprite = SpriteCreator.create("/images/gameplay/fast_enemy_sprite.png");

	/**
	 *  Creates a fast enemy.
	 */
	public FastEnemy() {
		super(55, 0.12F, 3, 75, sprite);
	}
	
	/**
	 * The textual representation on a text based game board.
	 */
	@Override
	public String toString(){
		return "f";
	}
}
