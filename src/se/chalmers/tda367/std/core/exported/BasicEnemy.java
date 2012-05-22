package se.chalmers.tda367.std.core.exported;

import se.chalmers.tda367.std.core.anno.Enemy;
import se.chalmers.tda367.std.core.enemies.AbstractEnemy;
import se.chalmers.tda367.std.utilities.SpriteCreator;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Represents a very basic enemy for use in a text based game.
 * <p>Start health: <b>70</b>
 * <p>Speed:        <b>0.09</b>
 * <p>Loot value:   <b>70</b>
 * <p>Armor:        <b>1</b>
 * 
 * @author Emil Edholm
 * @modified Emil Johansson
 * @date   25 mar 2012
 */
@Enemy(name = "Basic enemy", description = "Represents the most common enemy.", enemyStrength = 1)
public final class BasicEnemy extends AbstractEnemy {
	
	//TODO: change sprite for the basic enemy.
	private final static Sprite sprite = SpriteCreator.create("/images/gameplay/snail_sprite.png");
	
	/**
	 * Creates a basic enemy.
	 */
	public BasicEnemy() {
		super(70, 0.09F, 1, 70, sprite);
	}
	
	/**
	 * The textual representation on a text based game board.
	 */
	@Override
	public String toString(){
		return "e";
	}
}
