package se.chalmers.tda367.std.core.exported;

import se.chalmers.tda367.std.core.anno.Tower;
import se.chalmers.tda367.std.core.effects.NoEffect;
import se.chalmers.tda367.std.core.tiles.towers.AbstractAttackTower;
import se.chalmers.tda367.std.utilities.SpriteCreator;
import se.chalmers.tda367.std.utilities.Sprite;

/**
 * Represents a basic attack tower for use in a textual game board.
 * <p>
 * Base cost           = <b>80</b><br />
 * Base damage         = <b>7</b><br />
 * Effective radius    = <b>2</b><br />
 * Damage radius (AOE) = <b>0</b><br />
 * Attack speed        = <b>225</b><br />
 * </p>
 * @author Emil Edholm
 * @date   25 mar 2012
 */
@Tower(name = "Basic", description = "The most common basic attack tower.", towerStrength = 1)
public final class BasicAttackTower extends AbstractAttackTower {

	private static final int baseCost        = 80, 
							 baseDamage      = 7, 
							 effectiveRadius = 2, 
							 aoeRadius       = 0, 
							 attackSpeed     = 225;
	
	//TODO: change sprite for the basic tower.
	private final static Sprite sprite = SpriteCreator.create("/images/gameplay/basic_tower_tile.png");
	
	public BasicAttackTower() {
		super(baseCost, baseDamage, effectiveRadius, aoeRadius, attackSpeed,
				1, NoEffect.getInstance(), sprite);
	}

	/**
	 * The textual representation of the tower on a text based game board.
	 */
	@Override
	public String toString(){
		return "T";
	}
}
