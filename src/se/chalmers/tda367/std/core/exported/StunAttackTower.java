package se.chalmers.tda367.std.core.exported;

import java.util.List;

import se.chalmers.tda367.std.core.anno.Tower;
import se.chalmers.tda367.std.core.effects.StunEffect;
import se.chalmers.tda367.std.core.enemies.IEnemy;
import se.chalmers.tda367.std.core.tiles.towers.AbstractAttackTower;
import se.chalmers.tda367.std.utilities.Position;
import se.chalmers.tda367.std.utilities.Sprite;
import se.chalmers.tda367.std.utilities.SpriteCreator;

/**
 * Represents a Armor decreasing attack tower for use in a textual game board.
 * <p>
 * Base cost        = <b>300</b><br />
 * Base damage      = <b>0</b><br />
 * Effective radius = <b>3</b><br />
 * Damage radius    = <b>0</b><br />
 * Attack speed     = <b>500</b><br />
 * </p>
 * @author Johan Andersson
 * @date   13 may 2012
 */
@Tower(name = "Stuntower", description = "Tower that stun enemies for a brief amount of time", towerStrength = 3)
public final class StunAttackTower extends AbstractAttackTower {

	private static final int baseCost        = 300, 
							 baseDamage      = 0, 
							 effectiveRadius = 3, 
							 aoeRadius       = 2, 
							 attackSpeed     = 1000,
							 targetCount     = 1;
	
	//TODO: change sprite for the stuntower tower.
	private final static Sprite sprite = SpriteCreator.create("/images/gameplay/armor_tower_tile.png");
	
	public StunAttackTower() {
		super(baseCost, baseDamage, effectiveRadius, aoeRadius, attackSpeed,
				targetCount, new StunEffect(1), sprite);
	}
	
	/**
	 * The textual representation of the tower on a text based game board.
	 */
	@Override
	public String toString(){
		return "S";
	}

	@Override
	public void shoot(List<IEnemy> enemies, Position pos) {
		super.shoot(excludeEffect(enemies, StunEffect.class), pos);
	}
	
}