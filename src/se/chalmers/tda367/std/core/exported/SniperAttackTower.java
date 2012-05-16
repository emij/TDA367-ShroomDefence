package se.chalmers.tda367.std.core.exported;

import se.chalmers.tda367.std.core.anno.Tower;
import se.chalmers.tda367.std.core.effects.NoEffect;
import se.chalmers.tda367.std.core.tiles.towers.AbstractAttackTower;
import se.chalmers.tda367.std.utilities.Sprite;
import se.chalmers.tda367.std.utilities.SpriteCreator;

/**
 * Represents a Armor decreasing attack tower for use in a textual game board.
 * <p>
 * Base cost        = <b>300</b><br />
 * Base damage      = <b>100</b><br />
 * Effective radius = <b>6</b><br />
 * Damage radius    = <b>0</b><br />
 * Attack speed     = <b>1500</b><br />
 * </p>
 * @author Johan Andersson
 * @date   13 may 2012
 */
@Tower(name = "Sniper", description = "Tower with high range and damage but low attackspeed", towerStrength = 3)
public final class SniperAttackTower extends AbstractAttackTower {

	private static final int baseCost        = 300, 
							 baseDamage      = 100, 
							 effectiveRadius = 6, 
							 aoeRadius       = 0, 
							 attackSpeed     = 1500;
	
	//TODO: change sprite for the sniper tower.
	private final static Sprite sprite = SpriteCreator.create("/images/gameplay/armor_tower_tile.png");
	
	public SniperAttackTower() {
		super(baseCost, baseDamage, effectiveRadius, aoeRadius, attackSpeed,
				1, NoEffect.getInstance(), sprite);
	}
	
	/**
	 * The textual representation of the tower on a text based game board.
	 */
	@Override
	public String toString(){
		return "S";
	}
}