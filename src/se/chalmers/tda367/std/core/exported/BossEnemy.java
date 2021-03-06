package se.chalmers.tda367.std.core.exported;

import se.chalmers.tda367.std.core.anno.Enemy;
import se.chalmers.tda367.std.core.enemies.AbstractEnemy;
import se.chalmers.tda367.std.utilities.SpriteCreator;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Represents a boss enemy.
 * <p>Start health:  <b>400</b>
 * <p>Speed:         <b>0.083</b>
 * <p>Gold received: <b>250</b>
 * <p>Armor:         <b>10</b>
 * 
 * @author Johan Gustafsson
 * @date   19 apr 2012
 */
@Enemy(name = "El Jefe", description = "Represents a boss enemy.", enemyStrength = 4)
public class BossEnemy extends AbstractEnemy {
	
	private final static Sprite sprite = SpriteCreator.create("/images/gameplay/boss_enemy.png");
	
	/** Creates a boss enemy. */
	public BossEnemy() {
		super(400, 0.083F, 7, 250, sprite);
	}
	
	/**
	 * The textual representation on a text based game board.
	 */
	@Override
	public String toString(){
		return "b";
	}
}
