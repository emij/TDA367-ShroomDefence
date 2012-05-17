package se.chalmers.tda367.std.core.exported;

import se.chalmers.tda367.std.core.anno.Enemy;
import se.chalmers.tda367.std.core.enemies.AbstractEnemy;
import se.chalmers.tda367.std.utilities.SpriteCreator;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Represents a slow but hard tank enemy which moves slow but have lots of health.
 * <p>Start health:  <b>200</b>
 * <p>Speed:         <b>0.088</b>
 * <p>Gold received: <b>120</b>
 * <p>Armor:         <b>5</b>
 * 
 * @author Johan Gustafsson
 * @date   19 apr 2012
 */
@Enemy(name = "Sherman", description = "Represents a slow but hard tank enemy which moves slow but have lots of health.", enemyStrength = 1.5)
public class TankEnemy extends AbstractEnemy {
	//TODO: change sprite for the tank enemy.
	private final static Sprite sprite = SpriteCreator.create("/images/gameplay/enemy.png");

	/**
	 *  Creates a tank enemy.
	 */
	public TankEnemy() {
		super(200, 0.088F, 5, 120, sprite);
	}
	
	/**
	 * The textual representation on a text based game board.
	 */
	@Override
	public String toString(){
		return "t";
	}
}
